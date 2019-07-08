package net.dmorozov.numberconverter.service;

import net.dmorozov.numberconverter.converters.ConverterType;
import net.dmorozov.numberconverter.converters.NumberConverter;
import net.dmorozov.numberconverter.domain.Audit;
import net.dmorozov.numberconverter.domain.IllegalParametersException;
import net.dmorozov.numberconverter.domain.dto.ConvertRequest;
import net.dmorozov.numberconverter.domain.dto.ConvertResult;
import net.dmorozov.numberconverter.domain.dto.ConverterTypeDto;
import net.dmorozov.numberconverter.repository.AuditRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ConvertService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConvertService.class);

    private Map<ConverterType, NumberConverter> converterMap;
    private AuditRepository auditRepository;

    @Resource
    private ConvertService convertService;

    public List<ConverterTypeDto> getAvailableConverterTypes() {
        return converterMap.keySet().stream()
                .map(converterType -> new ConverterTypeDto(converterType.getCaption(), converterType.getValue()))
                .collect(Collectors.toList());
    }

    public ConvertResult convert(ConvertRequest convertRequest) {
        String type = convertRequest.getConverterType();
        Optional<ConverterType> converterType = ConverterType.getConverterType(type);
        NumberConverter numberConverter = converterType.map(ctype -> converterMap.get(ctype))
                .orElseThrow(
                        () -> new IllegalParametersException("There is no converter for type " + type)
                );

        String result;
        try {
            result = numberConverter.convert(convertRequest.getValue());
        } catch (IllegalArgumentException e) {
            LOGGER.error("Exception occurred while conversion process", e);
            throw new IllegalParametersException(e);
        }
        convertService.saveAudit(converterType.get(), convertRequest.getValue(), result);
        return new ConvertResult(result);
    }

    @Transactional
    public void saveAudit(ConverterType type, String value, String result) {
        Audit audit = new Audit();
        audit.setConverterType(type);
        audit.setValue(value);
        audit.setResult(result);
        auditRepository.save(audit);
    }

    @Autowired
    public void setConverterMap(List<NumberConverter> converters) {
        this.converterMap = converters.stream().collect(
                Collectors.toMap(NumberConverter::getConverterType, Function.identity())
        );
    }

    @Autowired
    public void setAuditRepository(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }
}
