package net.dmorozov.numberconverter.converters;

import org.springframework.stereotype.Component;

@Component
public class DecimalToRomanConverter implements NumberConverter {

    private static final String[] ROMAN_SYMBOLS = new String[]{"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};
    private static final int[] DECIMALS = new int[]{1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};

    @Override
    public ConverterType getConverterType() {
        return ConverterType.DECIMAL_TO_ROMAN;
    }

    @Override
    public String convert(String value) {
        if (value == null || "".equals(value)) {
            throw new IllegalArgumentException(String.format("Inappropriate parameter: [%s]", value));
        }

        float floatValue = Float.parseFloat(value);
        int decimal = Math.round(floatValue);

        return convertToRoman(decimal);
    }

    String convertToRoman(int intValue) {
        if (intValue <= 0 || intValue > 4999) {
            throw new IllegalArgumentException(String.format("Inappropriate parameter: [%s]. Parameter should be in range (0,4999]", intValue));
        }

        StringBuilder sb = new StringBuilder();
        int times;

        for (int i = DECIMALS.length - 1; i >= 0; i--) {
            times = intValue / DECIMALS[i];
            intValue %= DECIMALS[i];
            while (times > 0) {
                sb.append(ROMAN_SYMBOLS[i]);
                times--;
            }
        }
        return sb.toString();
    }
}