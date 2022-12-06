import axios from 'axios';

const baseUrl = process.env.REACT_APP_BASE_URL;


export async function getSocioLiquidador() {
    try {
        const response = await axios({
            url: `${baseUrl}/sicader/catalogo/getSocioLiquidador`,
            method: 'GET'
        })
        return response;
    } catch (e) {
        console.log(e)
    }
}

export async function getContraparte() {
    try {
        const response = await axios({
            url: `${baseUrl}/sicader/catalogo/getContraparte`,
            method: 'GET'
        })
        return response;
    } catch (e) {
        console.log(e)
    }
}

export async function getCobertura() {
    try {
        const response = await axios({
            url: `${baseUrl}/sicader/catalogo/getCobertura`,
            method: 'GET'
        })
        console.log(response)
        return response;
    } catch (e) {
        console.log(e)
    }
}

export async function getTipoCuenta() {
    try {
        const response = await axios({
            url: `${baseUrl}/sicader/catalogo/getTipoCuenta`,
            method: 'GET'
        })
        return response;
    } catch (e) {
        console.log(e)
    }
}

export async function getTipoDerivado() {
    try {
        const response = await axios({
            url: `${baseUrl}/sicader/catalogo/getTipoDerivado`,
            method: 'GET'
        })
        return response;
    } catch (e) {
        console.log(e)
    }
}

export async function postArchivoMensual() {
    try {
        const response = await axios({
            url: `${baseUrl}/sicader/catalogo/archivoMensual`,
            method: 'POST'
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


        const response = await axios({
            url: `${baseUrl}/sicader/catalogo/cobertura`,
            method: 'POST',
            data: request,
            headers: { "Content-Type": "application/json" }
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
        const response = await axios({
            url: `${baseUrl}/sicader/catalogo/getMoneda`,
            method: 'GET'
        })
        console.log('monedas: ' + response);
        return response;
    } catch (e) {
        console.log(e)
    }
}

export async function getCuentasConciliar() {
    try {
        const response = await axios({
            url: `${baseUrl}/sicader/catalogo/getCuentasConciliar`,
            method: 'GET'
        })
        console.log('cuentas: ' + response);
        return response;
    } catch (e) {
        console.log(e)
    }
}

export async function getCuentasConciliarReq10() {
    try {
        const response = await axios({
            url: `${baseUrl}/sicader/catalogo/getCuentasConciliarReq10`,
            method: 'GET'
        })
        console.log('cuentasR10: ' + response);
        return response;
    } catch (e) {
        console.log(e)
    }
}