import logo from './logo.svg';
import './App.css';
import {
  BrowserRouter as Router,
  Route, Routes,
} from 'react-router-dom';
import Folders from './components/Folders.jsx';
import Tasks from './components/Tasks.jsx';
import NavBar from './components/NavBar.jsx';
import TaskTest from './components/TaskTest.jsx';
import EditTask from './components/EditTask.jsx';
import EditFolder from './components/EditFolder.jsx';






function App() {
  return (
    <div className="App">
      
      <Router>
        <NavBar/>
        <Routes>
          <Route path="/folders" element={<Folders />} />
          <Route path="/editFolder/:idFolder" element={<EditFolder />} />
          <Route path="/tasks/:idFolder" element={<TaskTest />} />
          <Route path="/editTask/:idTask/:idFolder" element={<EditTask />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
