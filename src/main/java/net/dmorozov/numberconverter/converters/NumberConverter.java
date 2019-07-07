package net.dmorozov.numberconverter.converters;

public interface NumberConverter {
    ConverterType getConverterType();
    String convert(String value);
}
