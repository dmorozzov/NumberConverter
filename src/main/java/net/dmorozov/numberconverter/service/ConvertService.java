package net.dmorozov.numberconverter.service;

import net.dmorozov.numberconverter.converters.ConverterType;
import net.dmorozov.numberconverter.converters.NumberConverter;
import net.dmorozov.numberconverter.domain.ConvertRequest;
import net.dmorozov.numberconverter.domain.ConvertResult;
import net.dmorozov.numberconverter.domain.IllegalParametersException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ConvertService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConvertService.class);

    private Map<ConverterType, NumberConverter> converterMap;

    public List<String> getAvailableConverterTypes() {
        return converterMap.keySet().stream().map(ConverterType::getValue).collect(Collectors.toList());
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
        return new ConvertResult(result);
    }

    @Autowired
    public void setConverterMap(List<NumberConverter> converters) {
        this.converterMap = converters.stream().collect(
                Collectors.toMap(NumberConverter::getConverterType, Function.identity())
        );
    }
}
