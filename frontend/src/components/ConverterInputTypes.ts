import React from "react";
import DecimalToRomanInput from './DecimalToRomanInput';
import BinaryToRomanInput from './BinaryToRomanInput';

export interface BinderProps {
    value: string,
    handleValueChange: (event: React.FormEvent<HTMLInputElement>) => void
}

interface Input {
    binder: React.FC<BinderProps>,
    validate: (value: string) => string
}

interface ConverterTypeDictionary {
    [key: string]: Input
}

const InputTypes: ConverterTypeDictionary = {
    'DECIMAL_TO_ROMAN': {
        binder: DecimalToRomanInput,
        validate: (value: string) => {
            const digitCheck = /^[0-9.]*$/.test(value) ? '' : 'Value should contain only digits and/or dots';
            if (!!digitCheck) {
                return digitCheck;
            }
            const number = parseFloat(value);
            if (isNaN(number) || number === 0 || number > 4999) {
                return 'Value should be in range (0, 4999]';
            }
            return '';
        }
    },
    'BINARY_TO_ROMAN': {
        binder: BinaryToRomanInput,
        validate: (value: string) => {
            const digitCheck = /^[0-1]*$/.test(value) ? '' : 'Value should contain only digits in range [0,1]';
            if (!!digitCheck) {
                return digitCheck;
            }
            const number = parseInt(value, 2);
            if (number === 0 || number > 4999) {
                return 'Value should be in range (1, 1001110000111]';
            }
            return '';
        }
    }
};

export default InputTypes;
