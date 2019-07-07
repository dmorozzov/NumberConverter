package net.dmorozov.numberconverter.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BinaryToRomanConverter implements NumberConverter {

    private final DecimalToRomanConverter decimalToRomanConverter;

    @Autowired
    public BinaryToRomanConverter(DecimalToRomanConverter decimalToRomanConverter) {
        this.decimalToRomanConverter = decimalToRomanConverter;
    }

    @Override
    public ConverterType getConverterType() {
        return ConverterType.BINARY_TO_ROMAN;
    }

    @Override
    public String convert(String value) {
        if (value == null || "".equals(value)) {
            throw new IllegalArgumentException(String.format("Inappropriate parameter: [%s]", value));
        }
        int intValue = Integer.parseInt(value, 2);
        return decimalToRomanConverter.convertToRoman(intValue);
    }
}
