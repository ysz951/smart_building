import axios from 'axios';
import { SMART_API_URL }  from '../config';
import TokenService from '../services/token-service';

const MeetingRESTService = {
    getHeader() {
        const option = {
            headers: {
                Authorization: 'Bearer ' + TokenService.getAuthToken()
            }
        };
        return option;
    },
    getAllMeetings() {
        return axios.get(SMART_API_URL + '/roommeeting/meeting/all');
    },
    createMeeting() {
        return axios.post(SMART_API_URL + '/roommeeting/meeting/save', [], this.getHeader());
    },
    getIdleMeeting() {
        return axios.get(SMART_API_URL + '/roommeeting/meeting/idle');
    },
    releaseMeeting(meetingId, roomId) {
        return axios.put(SMART_API_URL + `/roommeeting/meeting/${meetingId}/release?room=${roomId}`, [], this.getHeader());
    },
    startMeeting(id, startMeeting) {
        return axios.put(SMART_API_URL + `/roommeeting/meeting/${id}/start`, startMeeting, this.getHeader());
    },
    stopMeeting(id) {
        return axios.put(SMART_API_URL + `/roommeeting/meeting/${id}/stop`, [], this.getHeader());
    },
    getMeetingById(id) {
        return axios.get(SMART_API_URL + `/roommeeting/meeting/${id}`);
    }
}

export default MeetingRESTService;