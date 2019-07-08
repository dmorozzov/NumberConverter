package net.dmorozov.numberconverter.domain;

public class ConverterTypeDto {
    private String caption;
    private String value;

    public ConverterTypeDto(String caption, String value) {
        this.caption = caption;
        this.value = value;
    }

    public String getCaption() {
        return caption;
    }

    public String getValue() {
        return value;
    }
}
