import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter } from 'react-router-dom';
import RoomMeeting from './RoomMeeting';
it('renders without crashing', () => {
  const div = document.createElement('div');
  ReactDOM.render(
    <BrowserRouter>
      <RoomMeeting />
    </BrowserRouter>, div);
  ReactDOM.unmountComponentAtNode(div)
})