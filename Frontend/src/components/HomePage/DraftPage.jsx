import './DraftPage.css'
function DraftPage({draft,role,handleEditDraft,handleDeleteDraft}){
    return(
        <>
            <h4>Drafts</h4>
            {draft.map((el)=>{
                return  <div key={el.id}>
                            <p>Post : {el.post}</p>
                            <p>Platform : {el.platform+""}</p>
                            {role !=="viewer" && (<button type="button" onClick={()=> handleEditDraft(el)}>Edit</button>)}
                            {role ==="admin" && (<button type="button" onClick={()=> handleDeleteDraft(el)}>Delete</button>)}       
                        </div>
            })}
        </>
    )
}
export default DraftPage