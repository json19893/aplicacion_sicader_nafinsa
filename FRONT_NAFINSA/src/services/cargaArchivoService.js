import axios from 'axios';
import * as moment from "moment";

const baseUrl = process.env.REACT_APP_BASE_URL;

export async function cargarArchivo(params) {
    console.log(params)
    try{
        var FormData = require('form-data');
        const formData = new FormData();
        formData.append('file', params.file)
        const fechaS = moment(params.fechaOperacion).format("YYYY-MM-DD")

        const response = await axios({
            url: `${baseUrl}/sicader/carga/archivo?fechaOperacion=${fechaS}&forzar=${params.forzar}&usuario=${params.usuario}`,
            method: 'POST',
            data: formData,
            headers: { "Content-Type": "multipart/form-data" },

        })
        console.log(response);
        return response;
    } catch (e) {
        console.log('error servicio: ' + JSON.stringify(e.response))
        return e.response;        
    }
}

export async function cargarArchivo06IRDT(request) {
   
    try{
        const response = await axios({
            url: `${baseUrl}/sicader/carga/archivo06IRDT`,
            method: 'POST',
            data: request,
            //headers: { "Content-Type": "application/json" }
        })
        console.log(response);
        return response;
    } catch (e) {
        console.log('error servicio: ' + JSON.stringify(e.response))
        return e.response;        
    }
}
export async function getArchivoFecha (fechaOperacion) {
    try{
        let fechaO;
        if(fechaOperacion==null){
        const fecha = new Date();
        const fechaS = moment(fecha).format("YYYY-MM-DD")
        fechaO=fechaS;
        }else{
            fechaO=moment(fechaOperacion).format("YYYY-MM-DD");
        }

        const fecha = new Date();
        const fechaS = moment(fecha).format("YYYY-MM-DD")
        const response = await axios({
            url: `${baseUrl}/sicader/carga/archivo`,
            method: 'GET',
            params: {
                fechaOperacion: fechaO
            }
        })
        console.log(response)
        return response;
    } catch (e) {
        console.log(e)
    }
}

export async function getArchivoDetalle (request) {
    try{
        
        const response = await axios({
            url: `${baseUrl}/sicader/carga/archivoDetalle?id=${parseInt(request.id)}&tipoReporte=${request.tipoReporte}`,
            method: 'POST',
            headers: { "Content-Type": "multipart/form-data" },
        })
        
        /*const request = {
            id: parseInt(params.id),
            tipoReporte: params.tipoReporte,
        }
        console.log('post: '+ JSON.stringify(request))
        const response = await axios({
            url: `${baseUrl}/sicader/catalogo/archivoDetalle`,
            method: 'POST',
            data: request,
            headers: { "Content-Type": "application/json" }
        })*/
        console.log(response)
        return response;
    } catch (e) {
        console.log(e)
    }
}