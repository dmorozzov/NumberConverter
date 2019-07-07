package net.dmorozov.numberconverter.converters;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DecimalToRomanConverterTest {

    private static final DecimalToRomanConverter DECIMAL_TO_ROMAN_CONVERTER = new DecimalToRomanConverter();

    @Test(expected = IllegalArgumentException.class)
    public void check_Null() {
        DECIMAL_TO_ROMAN_CONVERTER.convert(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void check_EmptyString() {
        DECIMAL_TO_ROMAN_CONVERTER.convert("");
    }

    @Test(expected = NumberFormatException.class)
    public void check_DecimalWrong() {
        DECIMAL_TO_ROMAN_CONVERTER.convert("1,2");
    }

    @Test
    public void check_Decimal() {
        assertEquals("XXIV", DECIMAL_TO_ROMAN_CONVERTER.convert("23.5"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void check_Zero() {
        DECIMAL_TO_ROMAN_CONVERTER.convertToRoman(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void check_Max() {
        DECIMAL_TO_ROMAN_CONVERTER.convertToRoman(5000);
    }

    @Test
    public void check_3() {
        assertEquals("III", DECIMAL_TO_ROMAN_CONVERTER.convertToRoman(3));
    }

    @Test
    public void check_86() {
        assertEquals("LXXXVI", DECIMAL_TO_ROMAN_CONVERTER.convertToRoman(86));
    }

    @Test
    public void check_100() {
        assertEquals("C", DECIMAL_TO_ROMAN_CONVERTER.convertToRoman(100));
    }

    @Test
    public void check_36() {
        assertEquals("XXXVI", DECIMAL_TO_ROMAN_CONVERTER.convertToRoman(36));
    }

    @Test
    public void check_1996() {
        assertEquals("MCMXCVI", DECIMAL_TO_ROMAN_CONVERTER.convertToRoman(1996));
    }
}
