import React from 'react';
import {Props} from './ConverterInputTypes';

const DecimalToRomanInput: React.FC<Props> = (props: Props) => {

    return (<label>Type decimal number <input type='text' value={props.value} onChange={props.handleValueChange}/></label>)
};

export default DecimalToRomanInput;
