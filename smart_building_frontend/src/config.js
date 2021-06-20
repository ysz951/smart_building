const SMART_API_URL = process.env.REACT_APP_API_URL || "http://localhost:8888/api";

const DEMO_PASSWORD = process.env.REACT_APP_DEMO_PASSWORD;

const OAUTH2_REDIRECT_URI = 'http://localhost:3000/oauth2/redirect';

const API_BASE_URL = 'http://localhost:8888';

const GOOGLE_AUTH_URL = API_BASE_URL + '/oauth2/authorize/google?redirect_uri=' + OAUTH2_REDIRECT_URI;

const FACEBOOK_AUTH_URL = API_BASE_URL + '/oauth2/authorize/facebook?redirect_uri=' + OAUTH2_REDIRECT_URI;

const GITHUB_AUTH_URL = API_BASE_URL + '/oauth2/authorize/github?redirect_uri=' + OAUTH2_REDIRECT_URI;

export { SMART_API_URL, DEMO_PASSWORD, OAUTH2_REDIRECT_URI, API_BASE_URL, GOOGLE_AUTH_URL, FACEBOOK_AUTH_URL, GITHUB_AUTH_URL };