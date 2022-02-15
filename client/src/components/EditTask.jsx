import { React, useEffect, useState, useContext } from 'react';
import { useParams } from 'react-router';
import Axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { AuthContext } from '../contexts/Auth.jsx';


const EditTask = () => {
    const { loggedUser } = useContext(AuthContext);
    const [taskDescription, setTaskDescription] = useState('');
    const [task, setTask] = useState({});
    const navigate = useNavigate();

    const { idTask, idFolder } = useParams();


    useEffect(() => {
        if (!loggedUser) {
            navigate('/logout');
        }
        else {
            getTasksById();
        }

    }, [])


    const getTasksById = () => {

        try {
            Axios.get(`http://localhost:8080/tasks/${idTask}`)
                .then((resp => {
                    setTask(resp.data);
                }))

        } catch (e) { console.log(e); }

    }

    function saveTask() {
        let descripAux = null;
        if (taskDescription === '') {
            descripAux = task.description;
        }
        const taskEdited = {
            id: idTask,
            description: (descripAux !== null) ? descripAux : taskDescription,
            checked: task.checked,
            folder: {
                id: idFolder
            }
        }

        try {
            Axios.put(`http://localhost:8080/tasks/`, taskEdited)
                .then((resp => {
                    navigate(`/tasks/${idFolder}`)

                }))
        } catch (e) { console.log(e); }
    }

    function goBack() {
        navigate(`/tasks/${idFolder}`)
    }

    return (
        <div className="container m-5 p-2 rounded mx-auto bg-light shadow">

            <a onClick={() => goBack()} className="link-danger btn" ><i class="far fa-arrow-alt-circle-left "></i> Go Back</a>

            <div className="row m-1 p-4">
                <div className="col">
                    <div className="p-1 h1 text-primary text-center mx-auto display-inline-block">
                        <i className="fa fa-check bg-primary text-white rounded p-2"></i>
                        <u> Task</u>
                    </div>
                </div>
            </div>


            <div className="row m-1 p-3">
                <div className="col col-11 mx-auto">
                    <div className="row bg-white rounded shadow-sm p-2 add-todo-wrapper align-items-center justify-content-center">
                        <div className="col">
                            <input onChange={(e) => setTaskDescription(e.target.value)} className="form-control form-control-lg border-0 add-todo-input bg-transparent rounded" type="text" defaultValue={task.description} />
                        </div>

                        <div className="col-auto px-0 mx-0 mr-2">
                            <button onClick={() => saveTask()} type="button" className="btn btn-success">Save</button>
                        </div>
                    </div>
                </div>
            </div>
            <div className="p-2 mx-4 border-black-25 border-bottom"></div>
        </div>

    );
};

export default EditTask;
