import { useParams } from 'react-router-dom';
import { useState, useEffect } from 'react';
import MultiSelect from "react-multi-select-component";
import MeetingRESTService from '../RESTService/MeetingRESTService';
import RoomRESTService from '../RESTService/RoomRESTService';
function RoomMeeting(props) {
    const { id } = useParams();
    const {meeting = []} = props;
    const [roomMeeting, setRoomMeeting] = useState([]);
    const [idleMeeting, setIdleMeeting] = useState([]);
    useEffect(() => {
        MeetingRESTService.getIdleMeeting()
            .then(res => {
                const { data } = res;
                console.log(data);
                const options = [];
                for (let meetingItem of data) {
                    options.push({
                        label: "Meeting " + meetingItem.id,
                        value: meetingItem.id
                    })
                }
                setIdleMeeting(options);
                // setIdleRoom(data);
            })
            .catch(err => {
                console.log(err);
            })
    }, []);
    const handleSubmit = e => {
        e.preventDefault();
        console.log(roomMeeting);
        const roomMeetings = roomMeeting.map(item => item.value);
        const meetingList = {
            "meetingList": roomMeetings
        }
        RoomRESTService.assignMeeting(id, meetingList)
            .then(res => {
                window.location.reload(true);
                // history.push("/main");
            })
            .catch(err => {
                console.log(err);
            })
        // const roomList = departRoom.map(d => d.value);
        // const requestBody = {
        //     roomList: roomList
        // }
        // DepartmentRESTService.departmentAssignRoom(id, requestBody)
        //     .then(res => {
        //         window.location.reload(true);
        //     })
        //     .catch(err => {
        //         console.log(err);
        //     })
    }
    const releaseMeeting = e => {
        const meetingId = e.target.name.split("-")[1];
        MeetingRESTService.releaseMeeting(meetingId, id)
            .then(res => {
                window.location.reload(true);
                // console.log(res.data)
            })
            .catch(err => {
                console.log(err);
            })
    }
    return (
        <div>
            <form onSubmit={handleSubmit}>
            <MultiSelect
                options={idleMeeting}
                value={roomMeeting}
                onChange={setRoomMeeting}
                labelledBy="Select"
            />
            <button type="submit">Add</button>
            </form>
            <table className="table">
                <thead>
                    <tr>
                        <th scope="row">#</th>
                        <th scope="row">Operation</th>
                    </tr>
                </thead>
                <tbody>
                    {meeting.map((m, i) => (
                        <tr key={i}> 
                            <th scope="row" >
                                {m}
                            </th>
                            <td>
                                <button name={"meeting-" + m} onClick={e => releaseMeeting(e)}>Delete</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    )
}

export default RoomMeeting;