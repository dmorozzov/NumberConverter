import React from 'react';
import {Props} from './ConverterInputTypes';

const BinaryToRomanInput: React.FC<Props> = (props: Props) => {
    return (<label>Type binary number <input type='text' value={props.value} onChange={props.handleValueChange}/></label>)
};

export default BinaryToRomanInput;
