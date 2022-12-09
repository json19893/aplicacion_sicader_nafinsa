import axios from 'axios';
import * as moment from "moment";

const baseUrl = process.env.REACT_APP_BASE_URL;

export async function validaConciliacion (params) {
    try{
      
        const response = await axios({
            url: `${baseUrl}/sicader/conciliacion/ejecutaValidacion`,
            method: 'POST',
            data: params
        })
        console.log(response);
        return response;
    } catch (e) {
        console.log(e)
    }
}

export async function ejecutaConciliacion (params) {
    try{
      
        const response = await axios({
            url: `${baseUrl}/sicader/conciliacion/ejecutaConciliacion`,
            method: 'POST',
            data: params
        })
        console.log(response);
        return response;
    } catch (e) {
        console.log(e)
    }
}

export async function ejecutaValidacion(params) {
    try{
      
        const response = await axios({
            url: `${baseUrl}/sicader/conciliacion/getValidacion`,
            method: 'GET'
        })
        console.log(response);
        return response;
    } catch (e) {
        console.log(e)
    }
}

export async function getListaConciliacion() {
    try {
        const response = await axios({
            url: `${baseUrl}/sicader/conciliacion/getConciliacionFecha`,
            method: 'GET'
        })
        return response;
    } catch (e) {
        console.log(e)
    }
}