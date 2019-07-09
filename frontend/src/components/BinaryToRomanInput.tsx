import React from 'react';
import {BinderProps} from './ConverterInputTypes';

const BinaryToRomanInput: React.FC<BinderProps> = (props: BinderProps) => {
    const placeholder = 'Type binary number e.g. 1011';
    return (<input placeholder={placeholder} className='u-full-width' type='text'
                   value={props.value}
                   onChange={props.handleValueChange}/>)
};

export default BinaryToRomanInput;
