package net.dmorozov.numberconverter.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ConvertRequest {

    @NotBlank
    @Size(min = 1, max = 20)
    private String converterType;

    @NotBlank
    @Size(min = 1, max = 20)
    private String value;

    public String getConverterType() {
        return converterType;
    }

    public void setConverterType(String converterType) {
        this.converterType = converterType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
