import React, { useRef } from 'react'
import axios from 'axios';

function AddData() {
    const textInput = useRef(null);
    const snackBar = useRef(null);
    let showSuccessSnackbar = () => {
        snackBar.current.innerText = "Something went wrong"
        snackBar.current.classList.add("show");
        setTimeout(() => {
            snackBar.current.classList.remove("show");
        }, 2000);
    }
    let showErrorSnackbar = () => {
        snackBar.current.innerText = "Something went wrong"
        snackBar.current.classList.add("show");
        setTimeout(() => {
            snackBar.current.classList.remove("show");
        }, 2000);
    }
    let handleAddItem = () => {
        try{
            let value = JSON.parse(textInput.current.value);
            console.log(value);
        axios.post("http://localhost:1234/data", value)
            .then((res) => {
                showSuccessSnackbar();
            }, (error) => {
                showErrorSnackbar();
            } )
        }catch(e){
            showErrorSnackbar();
        }
    }
    return (
        <div className='container'>
            <p>{"Add 'name' key in root, for showing on UI or else ID(autogenerated) will be shown"}</p>
            <textarea className='add-new-item' ref={textInput}/>
            <button style={{padding: "10px"}} onClick={handleAddItem}>Add Data</button>
            <div id="snackbar" ref={snackBar}></div>
        </div>
    )
}

export default AddData