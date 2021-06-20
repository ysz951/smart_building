import { useState, useEffect } from 'react';
import MeetingRESTService from '../RESTService/MeetingRESTService';
import { TextField } from '@material-ui/core';
import { useHistory, useParams } from 'react-router-dom';
const moment = require('moment-timezone');
function MeetingItemPage() {
    const { id } = useParams();
    const history = useHistory();
    const [meeting, setMeeting] = useState({});
    const [currentTime, setCurrentTime] = useState("");
    const [click, setClick] = useState(false);
    const [duration, setDuration] = useState(1);
    const [selectedDate, setSelectedDate] = useState("");
    useEffect(() => {
        // let time = "2021-03-30 17:49:16";
        // console.log(moment(time).diff(moment().utc()))
        setCurrentTime(moment().format('YYYY-MM-DD HH:mm:ss'));
        setInterval(() => {
            setCurrentTime(moment().format('YYYY-MM-DD HH:mm:ss'));
        }, 1000)
        MeetingRESTService.getMeetingById(id)
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
        const startMeeting = {
            startAt: moment(selectedDate).utc().format('YYYY-MM-DD HH:mm:ss'),
            duration: duration
        }
        MeetingRESTService.startMeeting(id, startMeeting)
            .then(res => {
                window.location.reload(true);
            })
            .catch(err => {
                console.log(err);
            })

    }
    const stopMeeting = () => {
        MeetingRESTService.stopMeeting(id)
            .then(res => {
                window.location.reload(true);
            })
            .catch(err => {
                console.log(err);
            })

    }
    const timeOperation = (exceed) => {
        if (click) {
            return (
                <>
                    <form onSubmit={startMeeting}>
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
    const renderMeetings = () => {
        const exceed = (meeting.endAt == null && meeting.startAt == null) || currentTime > moment.utc(meeting.endAt, 'YYYY-MM-DD HH:mm:ss').clone().local().format('YYYY-MM-DD HH:mm:ss');
        if (exceed && (meeting.startAt != null && meeting.endAt != null)){
            stopMeeting();
            meeting.startAt = null;
            meeting.endAt = null;
        }
        return(
            <>
                <tr key = {meeting.id}>
                    <th scope="row">{meeting.id}</th>
                    <td>{meeting.startAt && moment.utc(meeting.startAt, 'YYYY-MM-DD HH:mm:ss').clone().local().format('YYYY-MM-DD HH:mm:ss')}</td>
                    <td>{meeting.endAt && moment.utc(meeting.endAt, 'YYYY-MM-DD HH:mm:ss').clone().local().format('YYYY-MM-DD HH:mm:ss')}</td>
                    <td>{exceed ? "Stop" : "Start"}</td>
                    <td>{timeOperation(exceed)}</td>
                </tr>

            </>
        )
    }
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
    const goBack = () => {
        history.push("/meeting");
    }
    return (
        <div>
            <p>{currentTime}</p>
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
                    {renderMeetings()}
                </tbody>
            </table>
            <button onClick={goBack}>Back</button>
        </div>
    )
}

export default MeetingItemPage;