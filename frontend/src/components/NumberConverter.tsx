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
    conversionTypes: ConversionType[]
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
            this.setState({conversionTypes: [{caption: '-- Select conversion --', value: ''}].concat(data)});
        });
    }

    handleValueChange = (event: React.FormEvent<HTMLInputElement>) => {
        this.setState({value: event.currentTarget.value});
    };

    handleCurrentConversionChange = (event: ChangeEvent<HTMLSelectElement>) => {
        this.setState({currentConversionType: event.currentTarget.value})
    };

    handleConversion = () => {
        const value: string = this.state.value;
        const type: string = this.state.currentConversionType;
        if (!value || !type) {
            alert("Value and conversion type should be filled!");
            return;
        }
        Api.doConversion(value, type, (data) => this.setState({result: data}));
    };

    renderInput = (): React.ReactElement => {
        if (ConverterInputTypes.hasOwnProperty(this.state.currentConversionType)) {
            return React.createElement(ConverterInputTypes[this.state.currentConversionType], {
                value: this.state.value,
                handleValueChange: this.handleValueChange
            });
        }
        return <input type='text' value={this.state.value} onChange={this.handleValueChange} />;
    };
    
    render(): React.ReactElement {
        return <div>
            {this.renderInput()}
            <select value={this.state.currentConversionType} onChange={this.handleCurrentConversionChange}>
                {
                    this.state.conversionTypes.map((conversion) =>
                    <option key={conversion.value} value={conversion.value}>
                        {conversion.caption}
                    </option>)
                }
            </select>
            <button onClick={this.handleConversion}>Convert</button>
            <div>{this.state.result}</div>
        </div>;
    }
}

export default NumberConverter;