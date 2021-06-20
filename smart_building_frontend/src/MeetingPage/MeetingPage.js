import { useState, useEffect } from 'react';
import MeetingRESTService from '../RESTService/MeetingRESTService';
import { TextField } from '@material-ui/core';
import { Link } from 'react-router-dom';
const moment = require('moment-timezone');
function MeetingPage() {
    const [meeting, setMeeting] = useState([]);
    const [err, setErr] = useState("");
    const [currentTime, setCurrentTime] = useState("");
    const [click, setClick] = useState(false);
    const [duration, setDuration] = useState(1);
    const [exceed, setExceed] = useState(true);
    const [selectedDate, setSelectedDate] = useState("");
    useEffect(() => {
        // let time = "2021-03-30 17:49:16";
        // console.log(moment(time).diff(moment().utc()))
        setCurrentTime(moment().format('YYYY-MM-DD HH:mm:ss'));
        setInterval(() => {
            setCurrentTime(moment().format('YYYY-MM-DD HH:mm:ss'));
        }, 1000)
        MeetingRESTService.getAllMeetings()
            .then(res => {
                const { data } = res;
                setMeeting(data);
            })
            .catch(err => {
                console.log(err);
                
            })

    }, []);
    const startMeeting = e => {
        e.preventDefault();
        
        const meetingId = e.target.name.split("-")[1];
        const startMeeting = {
            startAt: moment(selectedDate).utc().format('YYYY-MM-DD HH:mm:ss'),
            duration: duration
        }
        MeetingRESTService.startMeeting(meetingId, startMeeting)
            .then(res => {
                window.location.reload(true);
            })
            .catch(err => {
                console.log(err);
            })

    }
    const stopMeeting = e => {
        const meetingId = e.target.name.split("-")[1];
        MeetingRESTService.stopMeeting(meetingId)
            .then(res => {
                window.location.reload(true);
            })
            .catch(err => {
                console.log(err);
            })

    }
    const stopMeetingById = id => {
        console.log("stop")
        MeetingRESTService.stopMeeting(id)
            .then(res => {
                console.log("stop success")
            })
            .catch(err => {
                console.log(err);
            })
    }
    const timeOperation = (exceed, id) => {
        if (click) {
            return (
                <>
                    <form name={"meetingstartform-"+id} onSubmit={startMeeting}>
                    <TextField
                id="datetime-local"
                label="Alarm clock"
                type="datetime-local"
                value={selectedDate}
                onChange={e => {setSelectedDate(e.target.value)}}
                InputLabelProps={{
                shrink: true,
                }}
            />
                        <input type="number"  className="form-control" min={1} value={duration} onChange={e => {setDuration(e.target.value)}}/>
                        <button type="submit">Start</button>
                        <button type="button" onClick={() => {setClick(false)}}>Cancel</button>
                    </form>
                </>
            )
        }
        else {
            if (exceed) {
                return (
                    <button onClick={() => {setClick(true)}}>Start a Meeting</button>
                )
            }
            else {
                return <button name={"meetingendbutton-"+id} onClick={stopMeeting}>End a Meeting</button>
            }
        }
        
    }
    const renderMeetings = (
        <>
            {meeting.map(m => {
                const exceed = (m.endAt == null && m.startAt == null) || currentTime > moment.utc(m.endAt, 'YYYY-MM-DD HH:mm:ss').clone().local().format('YYYY-MM-DD HH:mm:ss');
                if (exceed && (m.startAt != null && m.endAt != null)){
                    stopMeetingById(m.id);
                    m.startAt = null;
                    m.endAt = null;
                }
                return (
                    <tr key = {m.id}>
                        <th scope="row">{m.id}</th>
                        <td>{m.startAt && moment.utc(m.startAt, 'YYYY-MM-DD HH:mm:ss').clone().local().format('YYYY-MM-DD HH:mm:ss')}</td>
                        <td>{m.endAt && moment.utc(m.endAt, 'YYYY-MM-DD HH:mm:ss').clone().local().format('YYYY-MM-DD HH:mm:ss')}</td>
                        <td>{exceed ? "Stop" : "Start"}</td>
                        <td><Link className="badge badge-secondary" to={`/meeting/${m.id}`}>View</Link></td>
                        {/* <td>{timeOperation(exceed, m.id)}</td> */}
                    </tr>
                )
                
            }
            )}
        </>
    )
    const createNewMeeting = () => {
        MeetingRESTService.createMeeting()
            .then(res => {
                
            })
            .catch(err => console.log(err));
    }
    const showSelectedDate = e => {
        const utctime = moment(selectedDate).format('YYYY-MM-DD HH:mm:ss');
        console.log(selectedDate)
        console.log(utctime);
    }
    return (
        <div>
            <p>{currentTime}</p>
            <form noValidate>
            
            </form>
            <button onClick={showSelectedDate}>Show selected Date</button>
            <button className="btn btn-info" onClick={createNewMeeting}>Create New Meeting</button>
            <table className="table">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Start Date</th>
                        <th scope="col">End Date</th>
                        <th scope="col">Status</th>
                        <th scope="col">Operation</th>
                    </tr>
                </thead>
                <tbody>
                    {renderMeetings}
                </tbody>
            </table>
        </div>
    )
}

export default MeetingPage;