import axios from 'axios';
import * as moment from "moment";

const baseUrl = process.env.REACT_APP_BASE_URL;

export async function cargaGanancia(params) {
    try {
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
            data: request
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

        const response = await axios({
            url: `${baseUrl}/sicader/ganancia/getGananciaPerdidaUDI`,
            method: 'GET',
            params: request
        })
        console.log(response)
        return response;
    } catch (e) {
        console.log(e)
        return e.response;
    }
}
