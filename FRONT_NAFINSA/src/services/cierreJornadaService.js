import axios from 'axios';
import * as moment from "moment";

const baseUrl = process.env.REACT_APP_BASE_URL;

export async function getResumenDivisa (params) {
    let response = [];
    try{
        const request = {
            fechaIni: moment(params.fechaIni).format("YYYY-MM-DD"),
            fechaFin: moment(params.fechaFin).format("YYYY-MM-DD"),
            monClave: params.monClave
        }
        response = await axios({
            url: `${baseUrl}/sicader/cierreJornada/resumenDivisa`,
            method: 'GET',
            params: request
        })
        console.log(response)
        return response;
    } catch (e) {
        console.log('error servicio: '+JSON.stringify(e))
        return e.response;
    }
}