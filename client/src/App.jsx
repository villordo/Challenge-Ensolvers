import logo from './logo.svg';
import './App.css';
import {
  BrowserRouter as Router,
  Route, Routes,
} from 'react-router-dom';
import Folders from './components/Folders.jsx';
import NavBar from './components/NavBar.jsx';
import Task from './components/Task.jsx';
import EditTask from './components/EditTask.jsx';
import EditFolder from './components/EditFolder.jsx';
import AuthProvider from './contexts/Auth.jsx';
import LoginForm from './components/Login.jsx';
import Logout from './components/Logout.jsx';

function App() {
  return (
    <div className="App">
      <AuthProvider>
        <Router>
          <NavBar />
          <Routes>
            <Route exact path="/" element={<LoginForm/>}> </Route>
            <Route path="/logout" element={<Logout/>}> </Route>
            <Route path="/folders" element={<Folders />} />
            <Route path="/editFolder/:idFolder" element={<EditFolder />} />
            <Route path="/tasks/:idFolder" element={<Task />} />
            <Route path="/editTask/:idTask/:idFolder" element={<EditTask />} />
          </Routes>
        </Router>
      </AuthProvider>
    </div>
  );
}

export default App;
