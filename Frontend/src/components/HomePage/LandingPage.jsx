import React, { useState } from "react";
import DraftPage from "./DraftPage";
import {v4 as uuidv4} from 'uuid'
import { useSelector, useDispatch } from "react-redux";

import {
    addPlatform,
    removePlatform,
    setPost,
    clearPost,
    saveDrafts,
    deleteDraft,
    editDrafts,
} from "../../features/post/postSlice";

export default function LandingPage(){
    const limits = {
        twitter: 280,
        instagram: 2200,
        facebook: 3000,
    };
    const dispatch = useDispatch();
    const {platform,post,drafts } = useSelector(
        (state) => state.post
    );
    const count=post.length;
    
    const min=Math.min(...platform.map((item)=> limits[item]));

    let handlePlatform=(event)=>{
        const {value,checked}=event.target;
        if(checked){
            dispatch(addPlatform(value));
        }else{
            dispatch(removePlatform(value));
        }
    }
    let handleChange=(event)=>{
        dispatch(setPost(event.target.value));
    }
    let handleSubmit=(event)=>{
        event.preventDefault();
        alert("Post submitted successfully!");
        dispatch(clearPost());
    }
    let handleDraft=(event)=>{
        dispatch(saveDrafts());
        alert("Draft saved!");
    }
    let handleEditDraft=(draft)=>{
        dispatch(editDrafts(draft.id));
    }
    let handleDeleteDraft=(draft)=>{
        dispatch(deleteDraft(draft.id));
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
                <DraftPage draft={drafts} handleEditDraft={handleEditDraft} handleDeleteDraft={handleDeleteDraft}/>
            </form>
        </>
    )
}