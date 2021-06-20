import axios from 'axios';
import { SMART_API_URL }  from '../config';
import TokenService from '../services/token-service';

const DepartmentRESTService = {
    getHeader() {
        const option = {
            headers: {
                Authorization: 'Bearer ' + TokenService.getAuthToken()
            }
        };
        return option;
    },
    createDepartment(department) {
        return axios.post(SMART_API_URL + '/roommeeting/department/save', department, this.getHeader());
    },
    getAllDepartments() {
        return axios.get(SMART_API_URL + '/roommeeting/department/all');
    },
    getDepartmentById(id) {
        return axios.get(SMART_API_URL + `/roommeeting/department/${id}`);
    },
    departmentAssignRoom(id, roomList) {
        return axios.put(SMART_API_URL + `/roommeeting/department/${id}/assign`, roomList, this.getHeader());
    }
}

export default DepartmentRESTService;