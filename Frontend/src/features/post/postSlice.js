import { createSlice, createAsyncThunk, nanoid } from "@reduxjs/toolkit";

const API_URL = "http://localhost:8080/api/posts";

// CREATE POST
export const createPost = createAsyncThunk(
    "post/createPost",
    async ({ post, platform }) => {

        const response = await fetch(API_URL, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                content: post,
                platform: platform.join(","),
            }),
        });

        if (!response.ok) {
            throw new Error("Failed to create post");
        }

        return await response.json();
    }
);

// GET ALL POSTS
export const fetchPosts = createAsyncThunk(
    "post/fetchPosts",
    async () => {

        const response = await fetch(API_URL);

        if (!response.ok) {
            throw new Error("Failed to fetch posts");
        }

        return await response.json();
    }
);

const initialState = {
    platform: [],
    post: "",
    drafts: JSON.parse(localStorage.getItem("drafts")) || [],

    // Backend posts
    posts: [],
    loading: false,
    error: null,
};

export const postSlice = createSlice({

    name: "post",

    initialState,

    reducers: {

        addPlatform(state, action) {
            state.platform.push(action.payload);
        },

        removePlatform(state, action) {
            state.platform = state.platform.filter(
                item => item !== action.payload
            );
        },

        setPost(state, action) {
            state.post = action.payload;
        },

        clearPost(state) {
            state.post = "";
            state.platform = [];
        },

        saveDrafts(state) {

            const newDraft = {
                id: nanoid(),
                post: state.post,
                platform: [...state.platform],
            };

            state.drafts.push(newDraft);

            localStorage.setItem(
                "drafts",
                JSON.stringify(state.drafts)
            );

            state.post = "";
            state.platform = [];
        },

        deleteDraft(state, action) {

            state.drafts = state.drafts.filter(
                draft => draft.id !== action.payload
            );

            localStorage.setItem(
                "drafts",
                JSON.stringify(state.drafts)
            );
        },

        editDrafts(state, action) {

            const draft = state.drafts.find(
                item => item.id === action.payload
            );

            if (!draft) return;

            if (state.post.length > 0) {

                state.drafts.push({
                    id: nanoid(),
                    post: state.post,
                    platform: [...state.platform],
                });
            }

            state.post = draft.post;
            state.platform = [...draft.platform];

            state.drafts = state.drafts.filter(
                item => item.id !== action.payload
            );

            localStorage.setItem(
                "drafts",
                JSON.stringify(state.drafts)
            );
        },
    },

    extraReducers: (builder) => {

        // CREATE POST
        builder
            .addCase(createPost.pending, (state) => {
                state.loading = true;
                state.error = null;
            })

            .addCase(createPost.fulfilled, (state, action) => {
                state.loading = false;

                // Backend response:
                // { success, message, data }

                state.posts.push(action.payload.data);
            })

            .addCase(createPost.rejected, (state, action) => {
                state.loading = false;
                state.error = action.error.message;
            });

        // GET POSTS
        builder
            .addCase(fetchPosts.pending, (state) => {
                state.loading = true;
                state.error = null;
            })

            .addCase(fetchPosts.fulfilled, (state, action) => {
                state.loading = false;

                state.posts = action.payload.data;
            })

            .addCase(fetchPosts.rejected, (state, action) => {
                state.loading = false;
                state.error = action.error.message;
            });
    },
});

export const {
    addPlatform,
    removePlatform,
    setPost,
    clearPost,
    saveDrafts,
    deleteDraft,
    editDrafts,
} = postSlice.actions;

export default postSlice.reducer;