import React from "react";
import DecimalToRomanInput from './DecimalToRomanInput';
import BinaryToRomanInput from './BinaryToRomanInput';

export interface Props {
    value: string,
    handleValueChange: (event: React.FormEvent<HTMLInputElement>) => void
}

interface ConverterTypeDictionary {
    [key: string]: React.FC<Props>;
}

const InputTypes: ConverterTypeDictionary = {
    'DecimalToRoman': DecimalToRomanInput,
    'BinaryToRoman': BinaryToRomanInput
};

export default InputTypes;
