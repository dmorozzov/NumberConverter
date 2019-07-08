package net.dmorozov.numberconverter.controller;

import net.dmorozov.numberconverter.domain.ConvertRequest;
import net.dmorozov.numberconverter.domain.ConvertResult;
import net.dmorozov.numberconverter.domain.ConverterTypeDto;
import net.dmorozov.numberconverter.domain.IllegalParametersException;
import net.dmorozov.numberconverter.service.ConvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ConvertController {

    private ConvertService convertService;

    @PostMapping(path = "/convert", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ConvertResult convert(@Valid @RequestBody ConvertRequest convertRequest) {
        return convertService.convert(convertRequest);
    }

    @GetMapping(path = "/converters")
    public List<ConverterTypeDto> getAvailableConverterTypes() {
        return convertService.getAvailableConverterTypes();
    }

    @ExceptionHandler(IllegalParametersException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleIllegalParametersException(IllegalParametersException e) {
        return new ResponseEntity<>("Request is not valid due to " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @Autowired
    public void setConvertService(ConvertService convertService) {
        this.convertService = convertService;
    }
}
