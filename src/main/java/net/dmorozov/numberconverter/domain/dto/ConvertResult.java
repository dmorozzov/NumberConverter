package net.dmorozov.numberconverter.domain.dto;

public class ConvertResult {
    private String value;

    public ConvertResult(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
