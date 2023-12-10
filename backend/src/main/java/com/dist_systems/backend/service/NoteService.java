// src/main/java/com/dist_systems/backend/service/NoteService.java

package com.dist_systems.backend.service;

import com.dist_systems.backend.model.Note;
import com.dist_systems.backend.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Note createOrUpdateNote(Note note) {
        if (note.getId() == null || !noteRepository.existsById(note.getId())) {
            // Create a new note
            note.setCreatedAt(new Date());
        } else {
            // Update existing note
            note.setUpdatedAt(new Date());
        }
        return noteRepository.save(note);
    }

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Optional<Note> getNoteById(String id) {
        return noteRepository.findById(id);
    }

    public void deleteNote(String id) {
        noteRepository.deleteById(id);
    }

    public List<Note> searchNotesByTitle(String title) {
        return noteRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Note> searchNotesByContent(String content) {
        return noteRepository.findByContentContainingIgnoreCase(content);
    }

    public List<Note> getNotesCreatedAfter(Date date) {
        return noteRepository.findByCreatedAtAfter(date);
    }

    public List<Note> getNotesCreatedBefore(Date date) {
        return noteRepository.findByCreatedAtBefore(date);
    }

    public List<Note> getNotesCreatedBetween(Date startDate, Date endDate) {
        return noteRepository.findByCreatedAtBetween(startDate, endDate);
    }

    public List<Note> getRecentNotes() {
        return noteRepository.findRecentNotes();
    }

    public List<Note> getNotesByTag(String tag) {
        return noteRepository.findByTag(tag);
    }
}


