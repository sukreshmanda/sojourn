import React, { useState } from 'react'
import useAPIHandler from '../hooks/useAPIHandler';
import axios from 'axios';
import syntaxHighlight from './BeautifyJSON';
import * as DOMPurify from 'dompurify';
import {Link} from 'react-router-dom';

function DataComponent() {
    const [loading, data, errored] = useAPIHandler(() => axios.get("http://localhost:1234/user/data", {
        auth:{
            username: "sukresh242",
            password: "abcd"
        }
    }));
    return (
        <div className='container'>
            <Link to="/add-data"><i className="fa fa-plus add-item" aria-hidden="true"> Add new Item </i></Link>
            {!loading && !errored && <div>
                {data.map(item => <RenderToggle key={item.id} item = {renderData(item)} id={ item.data.name != null ?  item.data.name :  item.id }/>)}
            </div>}
        </div>
    )
}

export function RenderToggle({item, id}){
    const [toggle, setToggle] = useState(false);

    let handleOnClick = () => setToggle(prev => !prev);

    return (
        <div className='data-item'>
            <button type="button" className="collapsible" onClick={handleOnClick} style={{ 
                backgroundColor : toggle ? "#eee" : "#ccc",
                boxShadow : null
            }}>{id}</button>
            <div className="content" style={{
                display: toggle ? "block" : "none"
            }}>
                <div>{item}</div>
            </div>
        </div>
    )
}

function renderData(data){
    let purifiedDOM = DOMPurify.sanitize(syntaxHighlight(data));
    return <pre dangerouslySetInnerHTML={{__html: purifiedDOM}}/>
}
export default DataComponent