import { useState, useEffect } from 'react';
import { useHistory, useParams } from 'react-router-dom';
import RoomRESTService from '../RESTService/RoomRESTService';
import RoomMeeting from '../RoomMeeting/RoomMeeting';
function RoomPage() {
    const { id } = useParams();
    const history = useHistory();
    const [capacity, setCapacity] = useState(0);
    const [roomMeeting, setRoomMeeting] = useState([]);
    useEffect(() => {
        RoomRESTService.getRoomById(id)
            .then(res => {
                const { data } = res;
                setCapacity(data.capacity);
                setRoomMeeting(data.meeting);
            })
            .catch(err => {
                console.log(err);
            })
    }, []);
    const goBack = () => {
        history.push("/room");
    }
    return (
        <div>
            <h2>Room id is: {id}</h2>
            <h2>Room capacity: {capacity}</h2>
            <RoomMeeting meeting={roomMeeting}/>
            <button onClick={goBack}>Back</button>
        </div>
    )
}

export default RoomPage;