import { useState, useEffect } from 'react';
import { useHistory } from 'react-router-dom';
import AirpressureRESTService from '../RESTService/AirpressureRESTService';
function AirPressure() {
    const [air, setAir] = useState([]);
    useEffect(() => {
        AirpressureRESTService.getAllOperation()
            .then(res => {
                const {data} = res;
                setAir(data);
            })
            .catch(err => {
                console.log(err);
            })
    }, []);
    const newAir = () => {
        const control = {
            "remark": "Air pressure"
        }
        AirpressureRESTService.createAirOperation(control)
            .then(res => {
                window.location.reload(true);
            })
            .catch(err => {
                console.log(err);
            })
    }

    return (
        <>
            <button onClick={newAir}>new</button>
            <ul>
                {air.map(a => 
                    <li key = {a.id}>
                        {a.id}
                        {" "}
                        {a.control}
                    </li>
                )}
            </ul>
        </>
    )
}

export default AirPressure;