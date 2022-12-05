import axios from 'axios';

export const setReqAuthHeader = token => {
    if (token) {
        delete axios.defaults.headers.common["Authorization"];
        axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;
    }
}

export const setToken = (token) => {
    localStorage.setItem("token", token);
}

export const getToken = () => {
    return localStorage.getItem("token");
}