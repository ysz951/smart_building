import { useState, useEffect } from 'react';
import { useHistory } from 'react-router-dom';
import DepartmentRESTService from '../RESTService/DepartmentRESTService';
import RoomRESTService from '../RESTService/RoomRESTService';
import MultiSelect from "react-multi-select-component";
function CreateDepartment() {
    const [name, setName] = useState("");
    const [rooms, setRooms] = useState([]);

    const [idleRoom, setIdleRoom] = useState([]);
    const history = useHistory();
    useEffect(() => {
        RoomRESTService.getIdleRoom()
            .then(res => {
                const { data } = res;
                // console.log(data)
                const options = [];
                for (let roomItem of data) {
                    options.push({
                        label: "Room " + roomItem.id,
                        value: roomItem.id
                    })
                }
                setIdleRoom(options);
            })
    }, []);
    const createDepartment = e => {
        e.preventDefault();
        console.log(rooms)
        const departRooms = rooms.map(item => item.value);
        const department = {
            "name": name,
            "rooms": departRooms
        }
        DepartmentRESTService.createDepartment(department)
            .then(res => {
                history.push("/main");
            })
            .catch(err => {
                console.log(err);
            })
    }
    const goBack = () => {
        history.push("/main");
    }
    return (
        <div>
        <form onSubmit={createDepartment}> 
            <div className="form-group">
                <label htmlFor="exampleInputEmail1">Name</label>
                <input type="text" className="form-control" placeholder="Enter name" onChange={e => {setName(e.target.value)}}/>
            </div>
            <MultiSelect
                options={idleRoom}
                value={rooms}
                onChange={setRooms}
                labelledBy="Select"
            />
            <button type="submit" className="btn btn-primary">Submit</button>
            <button type="button" className="btn btn-info ml-2" onClick={goBack}>Back</button>  
        </form>
        </div>
    )
}

export default CreateDepartment;