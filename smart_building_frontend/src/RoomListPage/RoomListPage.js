import { useState, useEffect } from 'react';
import RoomRESTService from '../RESTService/RoomRESTService';
import { Link } from 'react-router-dom';
const moment = require('moment');
function RoomListPage() {
    const [rooms, setRooms] = useState([]);
    const [capacity, setCapacity] = useState(1);
    const [createRoom, setCreateRoom] = useState(false);
    useEffect(() => {
        RoomRESTService.getAllRooms()
            .then(res => {
                const {data} = res;
                console.log(data);
                setRooms(data);
            })
    }, []);
    const renderRooms = (
        <>
            {rooms.map(room => (
                <tr key = {room.id}>
                    <th scope="row">{room.id}</th>
                    <td>{room.capacity}</td>
                    <td>{moment(room.createdAt).format('YYYY-MM-DD HH:mm:ss')}</td>
                    <td>{moment(room.updatedAt).format('YYYY-MM-DD HH:mm:ss')}</td>
                    <td><Link className="badge badge-secondary" to={`/room/${room.id}`}>View</Link></td>
                </tr>
            ))}
        </>
    )
    const handleSubmit = e => {
        e.preventDefault();
        const room = {
            capacity: capacity
        }
        RoomRESTService.createRoom(room)
            .then(res => {
                // console.log(res.data);
                window.location.reload(true);
            })
    }
    return (
        <div>
            {!createRoom ? 
            <button className="btn btn-primary mb-2" onClick={() => setCreateRoom(true)}>Create Room</button> :
            <form onSubmit={handleSubmit}>
                <label>Capacity</label>
                <input type="number" min={1} value={capacity} className="form-control" placeholder="Enter name" onChange={e => {setCapacity(e.target.value)}}/>
                <button type="submit" className="btn btn-primary mr-2">Submit</button>
                <button onClick={() => {setCreateRoom(false)}}>Cancle</button>
            </form>}
            <br />
            <table className="table table-dark">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Capacity</th>
                        <th scope="col">Created Date</th>
                        <th scope="col">Modified Date</th>
                        <th scope="col">Detail</th>
                    </tr>
                </thead>
                <tbody>
                    {renderRooms}
                </tbody>
            </table>
        </div>
        
    )
}

export default RoomListPage;