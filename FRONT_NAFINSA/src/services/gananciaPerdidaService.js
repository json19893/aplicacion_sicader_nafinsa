import axios from 'axios';
import * as moment from "moment";
import errorInterceptor from '../interceptors/error';

const baseUrl = process.env.REACT_APP_BASE_URL;

errorInterceptor();

export async function cargaGanancia(params) {
    try {
        let token_insert=  sessionStorage.getItem('toke')
        const request = {
            fechaOperacion: moment(params.fechaVencimiento).format("YYYY-MM-DD"),
            fechaVencimiento: moment(params.fechaVencimiento).format("YYYY-MM-DD"),
            forzar: params.forzar,
            perdida: params.perdida,
            usuario: params.usuario,
            valorUid: params.valorUid
        }
        const response = await axios({
            url: `${baseUrl}/sicader/ganancia/gananciaPerdidaUDI`,
            method: 'POST',
            data: request,
            headers: { "Authorization": `${token_insert}` }
        })
        console.log(response);
        return response;
    } catch (e) {
        console.log(e)
        return e.response;
    }
}

export async function getGananciaPerdidaUDI(fechaVenc) {
    try {
        let fechaO;
        if (fechaVenc == null) {
            const fecha = new Date();
            const fechaS = moment(fecha).format("YYYY-MM-DD")
            fechaO = fechaS;
        } else {
            fechaO = moment(fechaVenc).format("YYYY-MM-DD")
        }

        const request = {
            fechaOperacion: fechaO
        }
        let token_insert=  sessionStorage.getItem('toke')
        const response = await axios({
            url: `${baseUrl}/sicader/ganancia/getGananciaPerdidaUDI`,
            method: 'GET',
            params: request,
            headers: { "Authorization": `${token_insert}` }
        })
        console.log(response)
        return response;
    } catch (e) {
        console.log(e)
        return e.response;
    }
}
