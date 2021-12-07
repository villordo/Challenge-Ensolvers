import { React, useEffect, useState } from 'react';
import Axios from 'axios';
import { useNavigate } from 'react-router-dom';

const Folders = () => {
    const [foldersList, setFoldersList] = useState([]);
    const [folderName, setFolderName] = useState('');

    const navigate = useNavigate();

    useEffect(() => {
        getAllFolders();
    }, [])

    const getAllFolders = () => {

        try {
            Axios.get(`http://localhost:8080/folders/`)
                .then((resp => {
                    setFoldersList(resp.data);
                 
                }))

        } catch (e) { console.log(e); }

    }

    function viewTasks(id){
        navigate(`/tasks/${id}`);
    }

    function editFolder(id){
        navigate(`/editFolder/${id}`);
    }

    function removeFolder(id){
        try {
            Axios.delete(`http://localhost:8080/folders/${id}` )
              .then((resp => {
                console.log(resp);
                getAllFolders();
              }))
          } catch (e) { console.log(e); }
    }
    function addFolder(){

        const newFolder = {
            name: folderName
          }
      
          try {
            Axios.post(`http://localhost:8080/folders/`, newFolder)
              .then((resp => {
                console.log(resp);
                getAllFolders();
              }))
          } catch (e) { console.log(e); }
    }

    return (
        <div className="container m-5 p-2 rounded mx-auto bg-light shadow">
            
      <div className="row m-1 p-4">
        <div className="col">
          <div className="p-1 h1 text-primary text-center mx-auto display-inline-block">
            <i className="fa fa-check bg-primary text-white rounded p-2"></i>
            <u> Folders</u>
          </div>
        </div>
      </div>


      <div className="row m-1 p-3">
        <div className="col col-11 mx-auto">
          <div className="row bg-white rounded shadow-sm p-2 add-todo-wrapper align-items-center justify-content-center">
            <div className="col">
              <input onChange={(e) => setFolderName(e.target.value)} className="form-control form-control-lg border-0 add-todo-input bg-transparent rounded" type="text" placeholder="Add new folder.." />
            </div>

            <div className="col-auto px-0 mx-0 mr-2">
              <button onClick={() => addFolder()} type="button" className="btn btn-primary">Add</button>
            </div>
          </div>
        </div>
      </div>
      <div className="p-2 mx-4 border-black-25 border-bottom"></div>


      {
        foldersList != [] ?
          (
            foldersList.map((e, i) => (
              <div className="row mx-1 px-5 pb-3 w-80" key={i}>
                <div className="col mx-auto">
                  <div className="row px-3 align-items-center todo-item rounded">
                    
                    <div className="col px-1 m-1 d-flex align-items-center">
                    <a onClick={(id) => viewTasks(e.id)} href="#" className="link-info form-control-lg border-0 edit-todo-input bg-transparent rounded px-3" >{e.name}</a>
                    </div>
                    <div className="col-auto m-1 p-0 px-3 d-none">
                    </div>
                    <div className="col-auto m-1 p-0 todo-actions">

                      <div className="row d-flex align-items-center justify-content-end">
                        <h5 className="m-0 p-0 px-2">
                          <i onClick={(id) => editFolder(e.id)} className="far fa-edit btn" data-toggle="tooltip" data-placement="bottom" title="Edit todo"></i>
                        </h5>
                        <h5 className="m-0 p-0 px-2">
                          <i onClick={(id) => removeFolder(e.id)} className="fa fa-trash-o text-danger btn m-0 p-0" data-toggle="tooltip" data-placement="bottom" title="Delete todo"></i>
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
            <p>No hay carpetas para mostrar</p>
          )
      }





    </div>








        
    );
};

export default Folders;

