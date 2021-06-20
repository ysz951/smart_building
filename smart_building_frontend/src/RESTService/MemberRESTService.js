import axios from 'axios';
import { SMART_API_URL }  from '../config';
import TokenService from '../services/token-service';

const MemberRESTService = {
    getHeader() {
        const option = {
            headers: {
                Authorization: 'Bearer ' + TokenService.getAuthToken()
            }
        };
        return option;
    },
    createMember(member) {
        return axios.post(SMART_API_URL + '/auth/signup', member);
    },
    getCurrentUser() {
        return axios.get(SMART_API_URL + '/user/me', this.getHeader());
    }
}

export default MemberRESTService;