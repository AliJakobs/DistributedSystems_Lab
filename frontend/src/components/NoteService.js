import axios from "axios";

const API_URL = "http://localhost:8080/api/notes"; // Replace with your actual backend URL

export const getAllNotes = async () => {
  try {
    const response = await axios.get(API_URL);
    return response.data;
  } catch (error) {
    console.error("Error fetching notes:", error);
    // Handle errors appropriately in your application
    return [];
  }
};

export const getNoteById = async (id) => {
  try {
    const response = await axios.get(`${API_URL}/${id}`);
    return response.data;
  } catch (error) {
    console.error(`Error fetching note with id ${id}:`, error);
    // Handle errors appropriately in your application
    return null;
  }
};

export const createOrUpdateNote = async (note) => {
  try {
    const response = await axios.post(API_URL, {
      title: "title", // Default title for every note
      content: note.content,
    });
    return response.data;
  } catch (error) {
    console.error("Error creating or updating note:", error);
    // Handle errors appropriately in your application
    return null;
  }
};

export const deleteNote = async (id) => {
  try {
    await axios.delete(`${API_URL}/${id}`);
  } catch (error) {
    console.error(`Error deleting note with id ${id}:`, error);
    // Handle errors appropriately in your application
  }
};
