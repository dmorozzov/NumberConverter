import {ConversionType} from './types';

export default class Api {

    static fetchConversionTypes(handleFetching: (types: ConversionType[]) => void): void {
        new Promise((resolve, reject) => {
            setTimeout(() => {
                resolve([
                    {
                        caption: 'decimal->roman',
                        value: 'DecimalToRoman'
                    },
                    {
                        caption: 'binary->roman',
                        value: 'BinaryToRoman'
                    }
                ]);
            }, 1000);
        }).then((data) => {handleFetching(data as ConversionType[])})
            .catch(error => console.error(error));
    }

    static doConversion(value: string, conversionType: string): Promise<string> {
        return new Promise((resolve, reject) => {
            setTimeout(() => {
                resolve(value + ' --> ' + conversionType);
            }, 1000);
        });
    }
}
