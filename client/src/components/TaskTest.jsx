import { React, useEffect, useState } from 'react';
import { useParams } from 'react-router';
import { useNavigate } from 'react-router-dom';
import Axios from 'axios';
import '../../src/css/TaskTest.css';


const Tasks = () => {
  const [tasksList, setTasksList] = useState([]);
  const [description, setDescription] = useState('');
  const navigate = useNavigate();

  const { idFolder } = useParams();


  useEffect(() => {
    getTasksByFolder();
  }, [])

  const getTasksByFolder = () => {

    try {
      Axios.get(`http://localhost:8080/tasks?folder=${idFolder}`)
        .then((resp => {
          setTasksList(resp.data);
        }))

    } catch (e) { console.log(e); }

  }

  function editTask(id) {
    navigate(`/editTask/${id}/${idFolder}`);
  }

  function removeTask(id) {

    try {
      Axios.delete(`http://localhost:8080/tasks/${id}`)
        .then((resp => {
          console.log(resp);
          getTasksByFolder();
        }))
    } catch (e) { console.log(e); }
  }

  function changeStatus(idP, checkedP, descriptionP) {
    checkedP = (checkedP) ? false : true;

    const taskEdited = {
      id: idP,
      description: descriptionP,
      checked: checkedP,
      folder: {
        id: idFolder
      }
    }

    try {
      Axios.put(`http://localhost:8080/tasks/`, taskEdited)
        .then((resp => {
          console.log(resp);
          getTasksByFolder();
        }))
    } catch (e) { console.log(e); }
  }

  function addTask() {

    const newTask = {
      description: description,
      folder: {
        id: idFolder
      }
    }

    try {
      Axios.post(`http://localhost:8080/tasks/`, newTask)
        .then((resp => {
          getTasksByFolder();
        }))
    } catch (e) { console.log(e); }

  }

  function goBack() {
    navigate(`/folders`);

  }
  return (
    <div className="container m-5 p-2 rounded mx-auto bg-light shadow">

      <a onClick={() => goBack()} className="link-danger btn" ><i class="far fa-arrow-alt-circle-left"></i> Go Back</a>

      <div className="row m-1 p-4">
        <div className="col">
          <div className="p-1 h1 text-primary text-center mx-auto display-inline-block">
            <i className="fa fa-check bg-primary text-white rounded p-2"></i>
            <u>My Todo-s</u>
          </div>
        </div>
      </div>


      <div className="row m-1 p-3">
        <div className="col col-11 mx-auto">
          <div className="row bg-white rounded shadow-sm p-2 add-todo-wrapper align-items-center justify-content-center">
            <div className="col">
              <input onChange={(e) => setDescription(e.target.value)} className="form-control form-control-lg border-0 add-todo-input bg-transparent rounded" type="text" placeholder="Add new .." />
            </div>

            <div className="col-auto px-0 mx-0 mr-2">
              <button onClick={() => addTask()} type="button" className="btn btn-primary">Add</button>
            </div>
          </div>
        </div>
      </div>
      <div className="p-2 mx-4 border-black-25 border-bottom"></div>


      {
        tasksList != [] ?
          (
            tasksList.map((e, i) => (
              <div className="row mx-1 px-5 pb-3 w-80" key={i}>
                <div className="col mx-auto">
                  <div className="row px-3 align-items-center todo-item rounded">
                    <div className="col-auto m-1 p-0 d-flex align-items-center">
                      <h2 className="m-0 p-0">
                        <input onClick={(id, checked, description) => changeStatus(e.id, e.checked, e.description)} type="checkbox" checked={e.checked} />
                      </h2>
                    </div>

                    <div className="col px-1 m-1 d-flex align-items-center">
                      <input value={e.description} type="text" className="form-control form-control-lg border-0 edit-todo-input bg-transparent rounded px-3" readonly />
                    </div>
                    <div className="col-auto m-1 p-0 px-3 d-none">
                    </div>
                    <div className="col-auto m-1 p-0 todo-actions">

                      <div className="row d-flex align-items-center justify-content-end">
                        <h5 className="m-0 p-0 px-6">
                          <i onClick={(id) => editTask(e.id)} className="far fa-edit btn" data-toggle="tooltip" data-placement="bottom" title="Edit todo"></i>
                        </h5>
                        <h5 className="m-0 p-0 px-2">
                          <i onClick={(id) => removeTask(e.id)} className="fa fa-trash-o text-danger btn m-0 p-0" data-toggle="tooltip" data-placement="bottom" title="Delete todo"></i>
                        </h5>
                      </div>



                    </div>
                    <div className="p-2 mx-4 border-black-25 border-bottom"></div>

                  </div>
                </div>
              </div>
            ))
          )
          :
          (
            <p>No hay tareas para mostrar</p>
          )
      }





    </div>
  );
};

export default Tasks;