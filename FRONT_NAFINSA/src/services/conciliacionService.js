import axios from 'axios';
import * as moment from "moment";
import errorInterceptor from '../interceptors/error';

const baseUrl = process.env.REACT_APP_BASE_URL;

errorInterceptor();

export async function validaConciliacion (params) {
    try{
        let token_insert=  sessionStorage.getItem('toke')
        const response = await axios({
            url: `${baseUrl}/sicader/conciliacion/ejecutaValidacion`,
            method: 'POST',
            data: params,
            headers: { "Authorization": `${token_insert}` }
        })
        console.log(response);
        return response;
    } catch (e) {
        console.log(e)
    }
}

export async function ejecutaConciliacion (params) {
    try{
        let token_insert=  sessionStorage.getItem('toke')
        const response = await axios({
            url: `${baseUrl}/sicader/conciliacion/ejecutaConciliacion`,
            method: 'POST',
            data: params,
            headers: { "Authorization": `${token_insert}` }
        })
        console.log(response);
        return response;
    } catch (e) {
        console.log(e)
    }
}

export async function ejecutaValidacion(params) {
    try{
        let token_insert=  sessionStorage.getItem('toke')
        const response = await axios({
            url: `${baseUrl}/sicader/conciliacion/getValidacion`,
            method: 'GET',
            headers: { "Authorization": `${token_insert}` }
        })
        console.log(response);
        return response;
    } catch (e) {
        console.log(e)
    }
}

export async function getListaConciliacion(fechaconcilia) {
    try {
        let fechaO;
        if (fechaconcilia == null) {
            const fecha = new Date();
            const fechaS = moment(fecha).format("YYYY-MM-DD")
            fechaO = fechaS;
        } else {
            fechaO = moment(fechaconcilia).format("YYYY-MM-DD")
        }

        const request = {
            fechaOperacion: fechaO
        }

        console.log(request)
        let token_insert=  sessionStorage.getItem('toke')
        const response = await axios({
            url: `${baseUrl}/sicader/conciliacion/getConciliacionFecha`,
            method: 'GET',
            params: request,
            headers: { "Authorization": `${token_insert}` }
        })
        console.log(response)
        return response;
    } catch (e) {
        console.log(e.response)
        return e.response;
    }
}

export async function getEstatusConciliacion (params) {
    try{
      console.log('parametros:'+params)
        let token_insert=  sessionStorage.getItem('toke')
        const response = await axios({
            url: `${baseUrl}/sicader/conciliacion/getEstatusConciliacion`,
            method: 'POST',
            data: params,
            headers: {
                'Content-Type': 'application/json',
                "Authorization": `${token_insert}` }
        })
        console.log("response:"+response);
        return response;
    } catch (e) {
        console.log(e.response)
        return e.response;
    }
}