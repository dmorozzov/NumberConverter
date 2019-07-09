import React from 'react';
import {BinderProps} from './ConverterInputTypes';

const DecimalToRomanInput: React.FC<BinderProps> = (props: BinderProps) => {
    const placeholder = 'Type decimal number e.g. 45.6';
    return (<input placeholder={placeholder} className='u-full-width' type='text'
                   value={props.value}
                   onChange={props.handleValueChange}/>)
};

export default DecimalToRomanInput;
