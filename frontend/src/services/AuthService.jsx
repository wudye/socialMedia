import axios from 'axios';

export default class AuthService {

    register (userData) {
        return axios.post(import.meta.env.VITE_REACT_APP_API + 'auth/register', userData)
    }
    login(userData) {
        return axios.post(import.meta.env.VITE_REACT_APP_API + 'auth/login', userData)
    }
}
