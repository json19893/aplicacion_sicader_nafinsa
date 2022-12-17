import axios from 'axios';
import * as moment from "moment";

const baseUrl = process.env.REACT_APP_BASE_URL;



export async function cargaValuacionBanxico (params) {
    try{
        let token_insert=  sessionStorage.getItem('toke')
        const response = await axios({
            url: `${baseUrl}/sicader/dmta/complementoBanxico`,
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

        let token_insert=  sessionStorage.getItem('toke')
        const response = await axios({
            url: `${baseUrl}/sicader/dmta/getComplementoBanxico`,
            method: 'GET',
            params: {
                //fechaOperacion: '2022-11-18'
                fechaOperacion: fechaO
            },
            headers: { "Authorization": `${token_insert}` }
        })
        console.log(response)
        return response;
    } catch (e) {
        console.log(e)
    }
}

export async function cargaCuentaMargen (params) {
    try{
        let token_insert=  sessionStorage.getItem('toke')
        const response = await axios({
            url: `${baseUrl}/sicader/dmta/cuentaMargen`,
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

        let token_insert=  sessionStorage.getItem('toke')
        const response = await axios({
            url: `${baseUrl}/sicader/dmta/getCuentaMargen`,
            method: 'GET',
            params: {
                //fechaOperacion: '2022-11-18'
                fechaOperacion: fechaO
            },
            headers: { "Authorization": `${token_insert}` }
        })
        console.log(response)
        return response;
    } catch (e) {
        console.log(e)
    }
}


export async function cargaC10 (params) {
    try{
        let token_insert=  sessionStorage.getItem('toke')
        const response = await axios({
            url: `${baseUrl}/sicader/dmta/sicaderC10`,
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
        let token_insert=  sessionStorage.getItem('toke')
        const response = await axios({
            url: `${baseUrl}/sicader/dmta/getSicaderC10`,
            method: 'GET',
            params: {
                //fechaOperacion: '2022-11-18'
                fechaOperacion: fechaO
            },
            headers: { "Authorization": `${token_insert}` }
        })
        console.log(response)
        return response;
    } catch (e) {
        console.log(e)
    }
}

export async function cargaArchivoMensual (params) {
    try{
        let token_insert=  sessionStorage.getItem('toke')
        const response = await axios({
            url: `${baseUrl}/sicader/carga/archivoMensual`,
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
        let token_insert=  sessionStorage.getItem('toke')
        const response = await axios({
            url: `${baseUrl}/sicader/carga/archivoMensual`,
            method: 'GET',
            params: {
                //fechaOperacion: '2022-11-18'
                fechaOperacion: fechaO
            },
            headers: { "Authorization": `${token_insert}` }
        })
        console.log(response)
        return response;
    } catch (e) {
        console.log(e)
    }
}