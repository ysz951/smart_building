import { useState, useEffect } from 'react';
import AuthApiService from '../services/auth-api-service';
import { useHistory, useLocation } from 'react-router-dom';
import { GOOGLE_AUTH_URL, FACEBOOK_AUTH_URL, GITHUB_AUTH_URL, DEMO_PASSWORD } from '../config';
import fbLogo from '../img/fb-logo.png';
import googleLogo from '../img/google-logo.png';
import githubLogo from '../img/github-logo.png';
import { Link } from 'react-router-dom';
import './Login.css';
function Login() {
    const [password, setPassword] = useState("");
    const [email, setEmail] = useState("");
    const [err, setErr] = useState(null);
    const history = useHistory();
    const location = useLocation();

    useEffect(() => {
        if (location.state && location.state.error) {
            alert(location.state.error);
        }
    }, [])

    const handleLoginSuccess = () => {
        const destination = (location.state || {}).from || '/main';
        history.push(destination);
    };
    const loginAction = member => {
        AuthApiService.login(member)
            .then(res => {
                console.log("success");
                setTimeout(handleLoginSuccess(), 500);
            })
            .catch(err => console.log(err))
    }
    const loginHandle = e => {
        e.preventDefault();
        setErr(null);
        const member = {
            usernameOrEmail: email,
            password: password
        }
        loginAction(member);
    }

    const demo_login = e => {
        setErr(null);
        const member = {
            usernameOrEmail: "lums@admin.com",
            password: DEMO_PASSWORD
        }
        loginAction(member);
    }

    const goBack = () => {
        history.push("/");
    }
    return (
        <div className="login-container">
            <div className="login-content">
                <h1 className="login-title">Login to SmartBuilding</h1>
                <SocialLogin />
                <div className="or-separator">
                    <span className="or-text">OR</span>
                </div>
                <form onSubmit={loginHandle} className="container">
                    <div className="form-group">
                        <label htmlFor="exampleInputEmail1">Email address</label>
                        <input type="email" className="form-control" id="exampleInputEmail1" name="email" aria-describedby="emailHelp"
                            value={email} onChange={e => setEmail(e.target.value)} />
                        <small id="emailHelp" className="form-text text-muted">We'll never share your email with anyone else.</small>
                    </div>
                    <div className="form-group">
                        <label htmlFor="exampleInputPassword1">Password</label>
                        <input type="password" className="form-control" id="exampleInputPassword1" name="password" onChange={e => setPassword(e.target.value)} />
                    </div>
                    {err && <p className="text-danger" >{err}</p>}
                    <div className="d-flex">
                        <button type="button" onClick={demo_login} className="btn btn-primary">Demo Login</button>
                        <button type="submit" className="btn btn-primary ml-2">Login</button>
                        <button onClick={goBack} type="button" className="btn btn-outline-info ml-2">Back</button>
                    </div>      
                    
                </form>
                <p className="d-flex ml-3">Don't have an acount yet? <Link to="/register_user">Sign up</Link></p>
            </div>
        </div>
    )
}


function SocialLogin() {
    return (
        <div className="social-login">
            <a className="social-link google border" href={GOOGLE_AUTH_URL}>            
                <img src={googleLogo} alt="Google" /> 
                <span>Log in with Google</span>
            </a>
            <a className="social-link facebook border" href={FACEBOOK_AUTH_URL}>
                <img src={fbLogo} alt="Facebook" /> 
                <span>Log in with Facebook</span></a>
            <a className="social-link github border" href={GITHUB_AUTH_URL}>
                <img src={githubLogo} alt="Github" /> 
                <span>Log in with Github</span></a>
        </div>
    );
}

export default Login;