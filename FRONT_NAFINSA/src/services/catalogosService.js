import axios from 'axios';
import errorInterceptor from '../interceptors/error';

const baseUrl = process.env.REACT_APP_BASE_URL;

errorInterceptor();

export async function getSocioLiquidador() {
    try {

        let token_insert=  sessionStorage.getItem('toke')
        const response = await axios({
            url: `${baseUrl}/sicader/catalogo/getSocioLiquidador`,
            method: 'get',
            headers: { "Authorization": `${token_insert}` }
        })
        return response;
    } catch (e) {
        console.log(e)
    }
}

export async function getContraparte() {
    try {
        let token_insert=  sessionStorage.getItem('toke')
        const response = await axios({
            url: `${baseUrl}/sicader/catalogo/getContraparte`,
            method: 'GET',
            headers: { "Authorization": `${token_insert}` }
        })
        return response;
    } catch (e) {
        console.log(e)
    }
}

export async function getCobertura() {
    try {
        let token_insert=  sessionStorage.getItem('toke')
        const response = await axios({
            url: `${baseUrl}/sicader/catalogo/getCobertura`,
            method: 'GET',
            headers: { "Authorization": `${token_insert}` }
        })
        console.log(response)
        return response;
    } catch (e) {
        console.log(e)
    }
}

export async function getTipoCuenta() {
    try {
        let token_insert=  sessionStorage.getItem('toke')
        const response = await axios({
            url: `${baseUrl}/sicader/catalogo/getTipoCuenta`,
            method: 'GET',
            headers: { "Authorization": `${token_insert}` }
        })
        return response;
    } catch (e) {
        console.log(e)
    }
}

export async function getTipoDerivado() {
    try {
        let token_insert=  sessionStorage.getItem('toke')
        const response = await axios({
            url: `${baseUrl}/sicader/catalogo/getTipoDerivado`,
            method: 'GET',
            headers: { "Authorization": `${token_insert}` }
        })
        return response;
    } catch (e) {
        console.log("respuesta:"+e.response())
    }
}

export async function postArchivoMensual() {
    try {
        let token_insert=  sessionStorage.getItem('toke')
        const response = await axios({
            url: `${baseUrl}/sicader/catalogo/archivoMensual`,
            method: 'POST',
            headers: { "Authorization": `${token_insert}` }
        })
        return response;
    } catch (e) {
        console.log(e)
    }
}

export async function cargaCobertura(params) {
    try {

        const request = {
            nombre: params.nombre,
            cuentaActiva: parseInt(params.cuentaActiva),
            cuentaPasiva: parseInt(params.cuentaPasiva),
            cuentaCapital: parseInt(params.cuentaCapital)
        }
        console.log('post: ' + JSON.stringify(request))

        let token_insert=  sessionStorage.getItem('toke')
        const response = await axios({
            url: `${baseUrl}/sicader/catalogo/cobertura`,
            method: 'POST',
            data: request,
            headers: { "Content-Type": "application/json",
                       "Authorization": `${token_insert}`
                     }
        })
        console.log('servicio: ' + response);
        return response;
    } catch (e) {
        console.log('error servicio: ' + JSON.stringify(e.response))
        return e.response;
    }
}

export async function getMoneda() {
    try {
        let token_insert=  sessionStorage.getItem('toke')
        const response = await axios({
            url: `${baseUrl}/sicader/catalogo/getMoneda`,
            method: 'GET',
            headers: { "Authorization": `${token_insert}` }
        })
        console.log('monedas: ' + response);
        return response;
    } catch (e) {
        console.log(e)
    }
}

export async function getCuentasConciliar() {
    try {
        let token_insert=  sessionStorage.getItem('toke')
        const response = await axios({
            url: `${baseUrl}/sicader/catalogo/getCuentasConciliar`,
            method: 'GET',
            headers: { "Authorization": `${token_insert}` }
        })
        console.log('cuentas: ' + response);
        return response;
    } catch (e) {
        console.log(e)
    }
}

export async function getCuentasConciliarReq10() {
    try {
        let token_insert=  sessionStorage.getItem('toke')
        const response = await axios({
            url: `${baseUrl}/sicader/catalogo/getCuentasConciliarReq10`,
            method: 'GET',
            headers: { "Authorization": `${token_insert}` }
        })
        console.log('cuentasR10: ' + response);
        return response;
    } catch (e) {
        console.log(e)
    }
}

export async function deletCobertura(params) {
    try {
        let token_insert=  sessionStorage.getItem('toke')
        const response = await axios({
            url: `${baseUrl}/sicader/catalogo/deleteCoberturaId?id=${params}`,
            method: 'POST',
            headers: {
                       "Authorization": `${token_insert}`
                     }
        })
        console.log('servicio: ' + response);
        return response;
    } catch (e) {
        console.log("erroorr el   "+e)
    }
}