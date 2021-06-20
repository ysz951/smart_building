import { useState, useEffect } from 'react';
import DepartmentRESTService from '../RESTService/DepartmentRESTService';
import { useHistory, useParams } from 'react-router-dom';
import DepartmentRoom from '../DepartmentRoom/DepartmentRoom';
function DepartmentItemPage() {
    const { id } = useParams();
    const history = useHistory();
    const [name, setName] = useState("");
    const [rooms, setRooms] = useState([]);
    useEffect(() => {
        DepartmentRESTService.getDepartmentById(id)
            .then(res => {
                const { data } = res;
                setName(data.name);
                setRooms(data.rooms);
                console.log(data.rooms);
            })
            .catch(err => {
                console.log(err);
            })
    }, []);
    const goBack = () => {
        history.push("/main");
    }

    return (
        <div>
            <h2>Department id: {id}</h2>
            <h3>Department name is {name}</h3>
            <table className="table table-dark">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Name</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th scope="row">{id}</th>
                        <td>{name}</td>
                    </tr>
                </tbody>
            </table>
            <DepartmentRoom rooms={rooms}/>
            <button onClick={goBack}>Back</button>
        </div>
    )
}

export default DepartmentItemPage;