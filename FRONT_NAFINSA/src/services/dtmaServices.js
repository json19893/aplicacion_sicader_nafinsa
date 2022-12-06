import axios from 'axios';
import * as moment from "moment";

const baseUrl = process.env.REACT_APP_BASE_URL;



export async function cargaValuacionBanxico (params) {
    try{
      
        const response = await axios({
            url: `${baseUrl}/sicader/dmta/complementoBanxico`,
            method: 'POST',
            data: params
        })
        console.log(response);
        return response;
    } catch (e) {
        console.log(e)
    }
}


export async function getAllSicaderBanxico (fechaOpe) {
    try{
        let fechaO;
        if(fechaOpe==null){
        const fecha = new Date();
        const fechaS = moment(fecha).format("YYYY-MM-DD")
        fechaO=fechaS;
        }else{
            fechaO=moment(fechaOpe).format("YYYY-MM-DD");
        }
    
        
        const response = await axios({
            url: `${baseUrl}/sicader/dmta/getComplementoBanxico`,
            method: 'GET',
            params: {
                //fechaOperacion: '2022-11-18'
                fechaOperacion: fechaO
            }
        })
        console.log(response)
        return response;
    } catch (e) {
        console.log(e)
    }
}

export async function cargaCuentaMargen (params) {
    try{
      
        const response = await axios({
            url: `${baseUrl}/sicader/dmta/cuentaMargen`,
            method: 'POST',
            data: params
        })
        console.log(response);
        return response;
    } catch (e) {
        console.log(e)
    }
}

export async function getAllSicaderInteresMargen (fechaOpe) {
    try{
        let fechaO;
        if(fechaOpe==null){
        const fecha = new Date();
        const fechaS = moment(fecha).format("YYYY-MM-DD")
        fechaO=fechaS;
        }else{
            fechaO=moment(fechaOpe).format("YYYY-MM-DD");
        }
    
        
        const response = await axios({
            url: `${baseUrl}/sicader/dmta/getCuentaMargen`,
            method: 'GET',
            params: {
                //fechaOperacion: '2022-11-18'
                fechaOperacion: fechaO
            }
        })
        console.log(response)
        return response;
    } catch (e) {
        console.log(e)
    }
}


export async function cargaC10 (params) {
    try{
        const response = await axios({
            url: `${baseUrl}/sicader/dmta/sicaderC10`,
            method: 'POST',
            data: params
        })
        console.log(response);
        return response;
    } catch (e) {
        console.log(e)
    }
}

export async function getAllC10 (fechaOpe) {
    try{
        let fechaO;
        if(fechaOpe==null){
        const fecha = new Date();
        const fechaS = moment(fecha).format("YYYY-MM-DD")
        fechaO=fechaS;
        }else{
            fechaO=moment(fechaOpe).format("YYYY-MM-DD");
        }
        const response = await axios({
            url: `${baseUrl}/sicader/dmta/getSicaderC10`,
            method: 'GET',
            params: {
                //fechaOperacion: '2022-11-18'
                fechaOperacion: fechaO
            }
        })
        console.log(response)
        return response;
    } catch (e) {
        console.log(e)
    }
}

export async function cargaArchivoMensual (params) {
    try{
        var FormData = require('form-data');
        const formData = new FormData();
        formData.append('file', params.file)
        const fechaS = moment(params.fechaOperacion).format("YYYY-MM-DD")
console.log("service:: ",params.forzar);
        const response = await axios({
            url: `${baseUrl}/sicader/carga/archivoMensual?fechaOperacion=${fechaS}&forzar=${params.forzar}&usuario=${params.usuario}`,
            method: 'POST',
            data: formData,
            //headers: { "Content-Type": "multipart/form-data" },

        })
        console.log(response);
        return response;
    } catch (e) {
        console.log(e)
    }
}

export async function getArchivoMensual (fechaOpe) {
    try{
        let fechaO;
        if(fechaOpe==null){
        const fecha = new Date();
        const fechaS = moment(fecha).format("YYYY-MM-DD")
        fechaO=fechaS;
        }else{
            fechaO=moment(fechaOpe).format("YYYY-MM-DD");
        }
        const response = await axios({
            url: `${baseUrl}/sicader/carga/archivoMensual`,
            method: 'GET',
            params: {
                //fechaOperacion: '2022-11-18'
                fechaOperacion: fechaO
            }
        })
        console.log(response)
        return response;
    } catch (e) {
        console.log(e)
    }
}