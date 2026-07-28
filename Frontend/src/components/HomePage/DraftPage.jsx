function DraftPage({draft,handleEditDraft,handleDeleteDraft}){
    return(
        <>
            <h4>Drafts</h4>
            {draft.map((el)=>{
                return  <div key={el.id}>
                            <p>Post : {el.post}</p>
                            <p>Platform : {el.platform+""}</p>
                            <button type="button" onClick={()=> handleEditDraft(el)}>Edit</button> <br /><br />
                            <button type="button" onClick={()=> handleDeleteDraft(el)}>Delete</button> <br /><br />
                        </div>
            })}
        </>
    )
}
export default DraftPage