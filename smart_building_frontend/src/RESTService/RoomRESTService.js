import axios from 'axios';
import { SMART_API_URL }  from '../config';
import TokenService from '../services/token-service';

const RoomRESTService = {
    getHeader() {
        const option = {
            headers: {
                Authorization: 'Bearer ' + TokenService.getAuthToken()
            }
        };
        return option;
    },

    createRoom(room) {
        return axios.post(SMART_API_URL + '/roommeeting/room/save', room, this.getHeader());
    },
    getAllRooms() {
        return axios.get(SMART_API_URL + '/roommeeting/room/all');
    },
    getRoomById(id) {
        return axios.get(SMART_API_URL + `/roommeeting/room/${id}`);
    },
    getIdleRoom() {
        return axios.get(SMART_API_URL + '/roommeeting/room/idle');
    },
    assignRoom(roomId, departmentId) {
        return axios.put(SMART_API_URL + `/roommeeting/room/${roomId}/assign?department=${departmentId}`, [], this.getHeader());
    },
    releaseRoom(roomId, departmentId) {
        return axios.put(SMART_API_URL + `/roommeeting/room/${roomId}/release?department=${departmentId}`, [], this.getHeader());
    },
    assignMeeting(roomId, roomMeeting) {
        return axios.put(SMART_API_URL + `/roommeeting/room/${roomId}/assign_meeting`, roomMeeting, this.getHeader());
    },
}

export default RoomRESTService;