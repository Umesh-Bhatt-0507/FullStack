// Actions for my project 
// 1. Add platforms
// 2. Remove Platforms
// 3. set a post
// 4. Draft a post
// 5. Edit Draft
// 6. Delete a Draft
// 7. CLear a post

import { configureStore } from '@reduxjs/toolkit'
import todoReducer from "../features/post/postSlice";

export const store=configureStore({
    reducer:postReducer,
});