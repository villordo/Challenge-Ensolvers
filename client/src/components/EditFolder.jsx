import { React, useEffect, useState, useContext } from 'react';
import { useParams } from 'react-router';
import Axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { AuthContext } from '../contexts/Auth.jsx';


const EditFolder = () => {
    const { loggedUser } = useContext(AuthContext);
    const [folderName, setFolderName] = useState('');
    const [folder, setFolder] = useState({});
    const navigate = useNavigate();

    const { idFolder } = useParams();


    useEffect(() => {
        if (!loggedUser) {
            navigate('/logout');
        }
        else {
            getFolderById();
        }
        
    }, [])

    const getFolderById = () => {

        try {
            Axios.get(`http://localhost:8080/folders/${idFolder}`)
                .then((resp => {
                    setFolder(resp.data);
                }))

        } catch (e) { console.log(e); }

    }

    function saveFolder() {
        let nameAux = null;
        if(folderName === ''){
            nameAux = folder.name;
        }

        const folderEdited = {
            id: idFolder,
            name: (nameAux !== null)? nameAux : folderName,
          }
      
          try {
            Axios.put(`http://localhost:8080/folders/`, folderEdited)
              .then((resp => {
                navigate(`/folders`);

              }))
          } catch (e) { console.log(e); }
    }

    function goBack(){
        navigate(`/folders`);
    }

    return (
        <div className="container m-5 p-2 rounded mx-auto bg-light shadow">

        <a onClick={() => goBack()} className="link-danger btn" ><i class="far fa-arrow-alt-circle-left "></i> Go Back</a>

            <div className="row m-1 p-4">
                <div className="col">
                    <div className="p-1 h1 text-primary text-center mx-auto display-inline-block">
                        <i className="fa fa-check bg-primary text-white rounded p-2"></i>
                        <u>{folder.name}</u>
                    </div>
                </div>
            </div>


            <div className="row m-1 p-3">
                <div className="col col-11 mx-auto">
                    <div className="row bg-white rounded shadow-sm p-2 add-todo-wrapper align-items-center justify-content-center">
                        <div className="col">
                            <input defaultValue={folder.name}  onChange={(e) => setFolderName(e.target.value)} className="form-control form-control-lg border-0 add-todo-input bg-transparent rounded" type="text"  />
                        </div>

                        <div className="col-auto px-0 mx-0 mr-2">
                            <button onClick={() => saveFolder()} type="button" className="btn btn-success">Save</button>
                        </div>
                    </div>
                </div>
            </div>
            <div className="p-2 mx-4 border-black-25 border-bottom"></div>
        </div>

    );
};

export default EditFolder;