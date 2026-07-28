import React, { useState } from "react";
import DraftPage from "./DraftPage";
import {v4 as uuidv4} from 'uuid'

export default function LandingPage(){
    const limits = {
        twitter: 280,
        instagram: 2200,
        facebook: 3000,
    };
    let [platform,setPlatform]=useState([]);
    let [post,setPost]=useState("");
    let [draft,setDraft]=useState([]);
    const count=post.length;
    
    const min=Math.min(...platform.map((item)=> limits[item]));

    let handlePlatform=(event)=>{
        const {value,checked}=event.target;
        if(checked){
            setPlatform((prev)=> [...prev,value]);
        }else{
            setPlatform((prev)=> prev.filter((item) => item!==value));
        }
    }
    let handleChange=(event)=>{
        setPost(event.target.value);
    }
    let handleSubmit=(event)=>{
        event.preventDefault();
        alert("Post submitted successfully!");
        setPlatform([]);
        setPost("");
    }
    let handleDraft=(event)=>{
        event.preventDefault();
        setDraft((prevDrafts)=>{
            return [...prevDrafts,{id:uuidv4(),post:post,platform:platform}]
        })
        setPost("");
        setPlatform([]);
        alert("Draft saved!");
    }
    let handleEditDraft=(draft)=>{
        if(post.length >0){
            setDraft((prevDrafts)=>{
                return [...prevDrafts,{id:uuidv4(),post:post,platform:platform}]
            })
        }
        setPost(draft.post);
        setPlatform(draft.platform);
        setDraft((prevDrafts)=>prevDrafts.filter((item) => item.id !== draft.id))
    }
    let handleDeleteDraft=(draft)=>{
        setDraft((prevDrafts)=> prevDrafts.filter((item)=> item.id !== draft.id));
    }
    
    return(
        <>
            <form onSubmit={handleSubmit}>
                <h1>Post Composer</h1><hr />
                <div className="platforms">
                    <label htmlFor="instagram">Instagram</label>
                    <input type="checkbox" name="platform" id="instagram" value="instagram" onChange={handlePlatform} checked={platform.includes("instagram")}/>
                    <label htmlFor="facebook">Facebook</label>
                    <input type="checkbox" name="platform" id="facebook" value="facebook" onChange={handlePlatform} checked={platform.includes("facebook")}/>
                    <label htmlFor="twitter">Twitter</label>
                    <input type="checkbox" name="platform" id="twitter" value="twitter" onChange={handlePlatform} checked={platform.includes("twitter")}/>
                </div>
                <textarea name="post" id="post" placeholder="Write your post here..." onChange={handleChange} value={post}></textarea>
                <p >Count : {count}/ {platform.length>0?min:0}</p>
                <p>{count>min && "reduce size" }</p>
                <button type="submit" disabled={platform.length==0 || count>min || post.trim()===""}>Submit</button>
                <button type="button" onClick={handleDraft} disabled={post.trim() === "" || platform.length==0}>Draft</button>
                <hr />
                <DraftPage draft={draft} setDraft={setDraft} handleEditDraft={handleEditDraft} handleDeleteDraft={handleDeleteDraft}/>
            </form>
        </>
    )
}