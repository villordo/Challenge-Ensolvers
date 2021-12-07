import { React, useEffect, useState }  from 'react';
import { useParams } from 'react-router';
import Axios from 'axios';

const Tasks = () => {
    const [tasksList, setTasksList] = useState([]);
    const {idFolder} = useParams();


    useEffect(() => {
        getTasksByFolder();
    }, [])

    const getTasksByFolder = () => {

        try {
            Axios.get(`http://localhost:8080/tasks?folder=${idFolder}`)
                .then((resp => {
                    console.log(resp.data);
                    setTasksList(resp.data);
                 
                }))

        } catch (e) { console.log(e); }

    }

    function editTask(id){
        
    }

    return (
        <div className="container">
        <table class="table">
        <thead>
          <tr>
            <th scope="col">Description</th>
            <th scope="col">Done</th>
            <th scope="col">Edit</th>
          </tr>
        </thead>
        <tbody>
            {
                tasksList != [] ?
                (
                    tasksList.map((e, i) => (

                        <tr key={i}>
                            <td>{e.description}</td>
                            <td><input type="checkbox" checked={e.checked} value=""/></td>
                            <td><div className="container d-flex text-align-center justify-content-center">
                                <button onClick={(id) => (editTask(e.id))} className="btn btn-primary btn-block mx-2">Edit</button>
                            </div></td>
                        </tr>
                    ))
                    )
                    :
                    (
                        <tr>
                            
                            <td className="bg-secondary">Vacio</td>
                            
                        </tr>
                    )
            }
            </tbody>
            </table>
            </div>
    );
};

export default Tasks;