import {ConversionType} from './types';

export default class Api {

    static handleResponse(response: Response) {
        if (!response.ok) {
            return response.text().then(error => {
                return Promise.reject(error);
            });
        }
        return response.json();
    }

    static fetchConversionTypes(handleFetching: (types: ConversionType[]) => void): void {
        const headers = {'Content-Type': 'application/json'};
        fetch('/converters', {headers})
            .then(Api.handleResponse)
            .then((data) => {
                handleFetching(data as ConversionType[]);
            })
            .catch(error => {
                console.error(error);
                alert(error);
            });
    }

    static doConversion(value: string, converterType: string, handleFetching: (result: string) => void): void {
        const headers = {'Content-Type': 'application/json'};
        const body = JSON.stringify({converterType, value});
        const params = {method: 'POST', headers, body};

        fetch('/convert', params)
            .then(Api.handleResponse)
            .then((data) => {
                handleFetching(data.value);
            })
            .catch(error => {
                console.error(error);
                alert(error);
            });
    }
}
