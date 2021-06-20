import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter } from 'react-router-dom';
import RoomListPage from './RoomListPage';
it('renders without crashing', () => {
  const div = document.createElement('div');
  ReactDOM.render(
    <BrowserRouter>
      <RoomListPage />
    </BrowserRouter>, div);
  ReactDOM.unmountComponentAtNode(div)
})