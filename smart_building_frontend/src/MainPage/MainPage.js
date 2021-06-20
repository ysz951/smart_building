import { useState, useEffect } from 'react';
import DepartmentRESTService from '../RESTService/DepartmentRESTService';
import MemberRESTService from '../RESTService/MemberRESTService';
import './MainPage.css'
import { Link } from 'react-router-dom';
function MainPage() {
    const [departments, setDepartments] = useState([]);
    useEffect(() => {
        DepartmentRESTService.getAllDepartments()
            .then(res => {
                setDepartments(res.data);
            })
    }, []);
    const departmentList = (
        <>
            {departments.map(depart => (
                <tr key={depart.id}>
                    <th scope="row">{depart.id}</th>
                    <td>{depart.name}</td>
                    <td><Link className="badge badge-secondary" to={`/department/${depart.id}`}>View</Link></td>
                </tr>
            ))}
        </>
    )
    return (
        <div className="home-div mt-3">
            <h3>Main</h3>
            <Link to={`/new_department`}>Create New Department</Link>
            <table className="table table-dark">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Name</th>
                        <th scope="col">Detail</th>
                    </tr>
                </thead>
                <tbody>
                    {departmentList}
                </tbody>
            </table>
        </div>
    )

}

export default MainPage;