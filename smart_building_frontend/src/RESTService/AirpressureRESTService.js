import axios from 'axios';
import { SMART_API_URL }  from '../config';
import TokenService from '../services/token-service';

const AirpressureRESTService = {
    getHeader() {
        const option = {
            headers: {
                Authorization: 'Bearer ' + TokenService.getAuthToken()
            }
        };
        return option;
    },
    createAirOperation(control) {
        return axios.post(SMART_API_URL + `/airpressure/save`, control, this.getHeader());
    },
    getAllOperation() {
        return axios.get(SMART_API_URL + `/airpressure/all`);
    },

}

export default AirpressureRESTService;