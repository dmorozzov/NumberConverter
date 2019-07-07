package net.dmorozov.numberconverter.converters;

import java.util.Optional;

public enum ConverterType {
    BINARY_TO_ROMAN("Binary -> Roman"),
    DECIMAL_TO_ROMAN("Decimal -> Roman");

    ConverterType(String caption) {
        this.caption = caption;
    }
    private String caption;

    public String getValue() { return name(); }

    public String getCaption() {
        return caption;
    }

    public static Optional<ConverterType> getConverterType(String value) {
        try {
            return Optional.of(valueOf(value));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
