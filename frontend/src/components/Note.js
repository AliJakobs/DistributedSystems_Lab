import { MdDeleteForever } from "react-icons/md";

const Note = ({ id, content, createdAt, handleDeleteNote }) => {
  return (
    <div className="note">
      <span>{content}</span>
      <div className="note-footer">
        <small>{new Date(createdAt).toLocaleDateString()}</small>
        <MdDeleteForever
          onClick={() => handleDeleteNote(id)}
          className="delete-icon"
          size="1.3em"
        />
      </div>
    </div>
  );
};

export default Note;
