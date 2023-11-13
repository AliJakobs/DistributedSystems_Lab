import { useState, useEffect } from "react";
import { nanoid } from "nanoid";
import NotesList from "./components/NotesList";
import Search from "./components/Search";
import Header from "./components/Header";
import {
  getAllNotes,
  createOrUpdateNote,
  deleteNote,
} from "./components/NoteService";

const App = () => {
  const [notes, setNotes] = useState([]);
  const [searchText, setSearchText] = useState("");
  const [darkMode, setDarkMode] = useState(false);

  useEffect(() => {
    const fetchNotes = async () => {
      const response = await getAllNotes();
      if (response) {
        setNotes(response);
      }
    };

    fetchNotes().catch(console.error);
  }, []);

  const handleAddNote = async (text) => {
    const newNote = {
      title: "title",
      content: text,
    };

    const response = await createOrUpdateNote(newNote);
    if (response) {
      setNotes([...notes, response]);
    }
  };

  const handleDeleteNote = async (id) => {
    await deleteNote(id);
    const newNotes = notes.filter((note) => note.id !== id);
    setNotes(newNotes);
  };

  return (
    <div className={`${darkMode && "dark-mode"}`}>
      <div className="container">
        <Header handleToggleDarkMode={setDarkMode} />
        <Search handleSearchNote={setSearchText} />
        <NotesList
          notes={notes.filter((note) =>
            note.content.toLowerCase().includes(searchText.toLowerCase()),
          )}
          handleAddNote={handleAddNote}
          handleDeleteNote={handleDeleteNote}
        />
      </div>
    </div>
  );
};

export default App;
