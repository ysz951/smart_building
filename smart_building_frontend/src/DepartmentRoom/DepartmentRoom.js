import { useParams } from 'react-router-dom';
import { useState, useEffect } from 'react';
import RoomRESTService from '../RESTService/RoomRESTService';
import MultiSelect from "react-multi-select-component";
import DepartmentRESTService from '../RESTService/DepartmentRESTService';
function DepartmentRoom(props) {
    const { rooms = [] } = props;
    const { id } = useParams();
    const [departRoom, setDepartRoom] = useState([]);
    const [idleRoom, setIdleRoom] = useState([]);
    useEffect(() => {
        RoomRESTService.getIdleRoom()
            .then(res => {
                const { data } = res;
                const options = [];
                for (let roomItem of data) {
                    options.push({
                        label: "Room " + roomItem.id,
                        value: roomItem.id
                    })
                }
                setIdleRoom(options);
                // setIdleRoom(data);
            })
            .catch(err => {
                console.log(err);
            })
    }, []);
    const handleAssign = e => {
        setDepartRoom(e.target.value);
    }
    const handleSubmit = e => {
        e.preventDefault();
        console.log(departRoom);
        const roomList = departRoom.map(d => d.value);
        const requestBody = {
            roomList: roomList
        }
        DepartmentRESTService.departmentAssignRoom(id, requestBody)
            .then(res => {
                window.location.reload(true);
            })
            .catch(err => {
                console.log(err);
            })
    }
    const releaseRoom = e => {
        console.log(e.target.name)
        const roomId = e.target.name.split("-")[1];
        RoomRESTService.releaseRoom(roomId, id)
            .then(res => {
                window.location.reload(true);
            })
            .catch(err => {
                console.log(err);
            })
    }
    return (
        <div>
            <h2>Department rooms</h2>
            <p>Assign a room</p>
            <form onSubmit={handleSubmit}>
            <MultiSelect
                options={idleRoom}
                value={departRoom}
                onChange={setDepartRoom}
                labelledBy="Select"
            />
            <button type="submit">Add</button>
            </form>
            <table className="table">
                <thead>
                    <tr>
                        <th scope="row">#</th>
                        <th scope="row">Capacity</th>
                        <th scope="row">Operation</th>
                    </tr>
                </thead>
                <tbody>
                    {rooms.map((r, i) => (
                        <tr key={i}> 
                            <th scope="row" >
                                {r.id}
                            </th>
                            <td>
                                {r.capacity}
                            </td>
                            <td>
                                <button name={"room-" + r.id} onClick={e => releaseRoom(e)}>Delete</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    )
}

export default DepartmentRoom;