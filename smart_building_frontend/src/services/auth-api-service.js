import { SMART_API_URL }  from '../config';
import TokenService from './token-service';
import axios from 'axios';
import IdleService from '../services/idle-service';

const option = {
  headers: {
      Authorization: 'Bearer ' + TokenService.getAuthToken()
  }
};
const AuthApiService = {
    async login(member) {
        return axios.post(SMART_API_URL + '/auth/signin', member)
          .then(res => {
            TokenService.saveAuthToken(res.data.accessToken);
            IdleService.regiserIdleTimerResets();
            TokenService.queueCallbackBeforeExpiry(() => {
                AuthApiService.postRefreshToken();
            })
            return res;
          })
    },
    async postRefreshToken() {
      console.log("refresh")
      return axios.post(SMART_API_URL + '/jwt/refresh', {}, option)
        .then(res => {
          /*
            similar logic to whenever a user logs in, the only differences are:
            - we don't need to queue the idle timers again as the user is already logged in.
            - we'll catch the error here as this refresh is happening behind the scenes
          */
          
          TokenService.saveAuthToken(res.data.accessToken);
          TokenService.queueCallbackBeforeExpiry(() => {
            AuthApiService.postRefreshToken()
          });
          return res;
        })
        .catch(err => {
          TokenService.clearAuthToken();
          window.location.reload(true);
        })
    },
}

export default AuthApiService;