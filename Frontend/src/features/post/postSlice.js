import { createSlice ,nanoid} from '@reduxjs/toolkit'

const initialState={
    platform:[],
    post:"",
    drafts:[],
}

export const postSlice=createSlice({
    name:"post",
    initialState,
    reducers:{
        addPlatform(state,action){
            state.platform.push(action.payload);
        },
        removePlatform(state,action){
            state.platform=state.platform.filter((item)=> item!==action.payload);
        },
        setPost(state,action){
            state.post=action.payload;
        },
        clearPost(state,action){
            state.post="";
            state.platform=[];
        },
        saveDrafts(state){
            state.drafts.push({
                id:nanoid(),
                post:state.post,
                platform:[...state.platform],
            })
            state.post="";
            state.platform=[];
        },
        deleteDraft(state,action) {
            state.drafts=state.drafts.filter((draft)=>draft.id !== action.payload);
        },
        editDrafts(state,action){
            const draft=state.drafts.find((item)=>item.id === action.payload);
            if(!draft){
                return;
            }
            if(state.post.length>0){
                state.drafts.push({
                    id:nanoid(),
                    post:state.post,
                    platform:[...state.platform],
                });
            }
            state.post=draft.post;
            state.platform=[...draft.platform];

            state.drafts=state.drafts.filter((item)=> item.id !== action.payload);
        },
    },
})

export const{
    addPlatform,
    removePlatform,
    setPost,
    clearPost,
    saveDrafts,
    deleteDraft,
    editDrafts,
}=postSlice.actions;

export default postSlice.reducer;