import React, {ChangeEvent} from 'react';
import Api from '../Api';
import {ConversionType} from '../types';
import ConverterInputTypes from './ConverterInputTypes';

interface Props {
}

interface State {
    value: string,
    result: string,
    currentConversionType: string,
    conversionTypes: ConversionType[],
    validator?: (value: string) => string,
    validationMessage?: string
}

class NumberConverter extends React.Component<Props, State> {

    constructor(props: Props) {
        super(props);
        this.state = {
            value: '',
            result: '',
            currentConversionType: '',
            conversionTypes: []
        };
        this.handleValueChange = this.handleValueChange.bind(this);
        this.handleConversion = this.handleConversion.bind(this);
        this.handleCurrentConversionChange = this.handleCurrentConversionChange.bind(this);
    }

    componentDidMount(): void {
        Api.fetchConversionTypes((data: ConversionType[]) => {
            this.setState({conversionTypes: [{caption: '-- Select type --', value: ''}].concat(data)});
        });
    }

    handleValueChange = (event: React.FormEvent<HTMLInputElement>) => {
        const value = event.currentTarget.value;
        const validator = this.state.validator;
        if (!!validator) {
            const validationMessage = validator(value);
            this.setState({validationMessage: validationMessage !== '' ? validationMessage : undefined});
        }
        this.setState({value: value});
    };

    handleCurrentConversionChange = (event: ChangeEvent<HTMLSelectElement>) => {
        const currentConversionType = event.currentTarget.value;

        if (ConverterInputTypes.hasOwnProperty(currentConversionType)) {
            const validator = ConverterInputTypes[currentConversionType].validate;
            const validationMessage = validator(this.state.value);
            this.setState({currentConversionType, validator, validationMessage});
        } else {
            this.setState({currentConversionType,validator: undefined, validationMessage: undefined});
        }
    };

    handleConversion = () => {
        const value: string = this.state.value;
        const type: string = this.state.currentConversionType;
        if (!value || !type) {
            alert('Value and conversion type should be filled!');
            return;
        }
        Api.doConversion(value, type, (data) => this.setState({result: data}));
    };

    renderInput = (): React.ReactElement => {
        const conversionType = this.state.currentConversionType;
        let input;
        if (ConverterInputTypes.hasOwnProperty(conversionType)) {
            input = React.createElement(ConverterInputTypes[conversionType].binder, {
                value: this.state.value,
                handleValueChange: this.handleValueChange
            });
        } else {
            input = (<input type='text' className='u-full-width'
                            value={this.state.value}
                            onChange={this.handleValueChange}/>);
        }
        return (<div>
            <label>Select conversion type</label>
            {input}
        </div>);
    };

    render(): React.ReactElement {
        return <div className='container'>
            <div className='row'>
                <div className='six columns'>
                    {this.renderInput()}
                </div>
                <div className='six columns'>
                    <label htmlFor='selector'>Select conversion type</label>
                    <select id='selector' className='u-full-width'
                            value={this.state.currentConversionType} onChange={this.handleCurrentConversionChange}>
                        {
                            this.state.conversionTypes.map((conversion) =>
                                <option key={conversion.value} value={conversion.value}>
                                    {conversion.caption}
                                </option>)
                        }
                    </select>
                </div>
            </div>
            <div className='row'>
                {!!this.state.validationMessage && <div className='u-pull-left error'>{this.state.validationMessage}</div>}
                <button className='u-pull-right' onClick={this.handleConversion} disabled={!!this.state.validationMessage}>
                    Convert
                </button>
            </div>
            <div className='row'>
                <div className='three columns'>Result: {this.state.result}</div>
            </div>
        </div>;
    }
}

export default NumberConverter;