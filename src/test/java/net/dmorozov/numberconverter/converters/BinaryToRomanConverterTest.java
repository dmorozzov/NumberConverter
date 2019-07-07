package net.dmorozov.numberconverter.converters;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BinaryToRomanConverterTest {

    private static BinaryToRomanConverter binaryToRomanConverter;

    @BeforeClass
    public static void init() {
        binaryToRomanConverter = new BinaryToRomanConverter(new DecimalToRomanConverter());
    }

    @Test(expected = IllegalArgumentException.class)
    public void check_Null() {
        binaryToRomanConverter.convert(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void check_EmptyString() {
        binaryToRomanConverter.convert("");
    };

    @Test(expected = NumberFormatException.class)
    public void check_NonBinaryString() {
        binaryToRomanConverter.convert("123");
    };

    @Test
    public void check_111() {
        assertEquals("VII", binaryToRomanConverter.convert("111"));
    }

    @Test
    public void check_1001() {
        assertEquals("IX", binaryToRomanConverter.convert("1001"));
    }

    @Test
    public void check_111001101() {
        assertEquals("CDLXI", binaryToRomanConverter.convert("111001101"));
    }
}
