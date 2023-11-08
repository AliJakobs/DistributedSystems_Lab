// src/main/java/com/dist_systems/backend/controller/NoteController.java

package com.dist_systems.backend.controller;

import com.dist_systems.backend.model.Note;
import com.dist_systems.backend.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {
    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable String id) {
        return noteService.getNoteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Note createOrUpdateNote(@RequestBody Note note) {
        return noteService.createOrUpdateNote(note);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable String id) {
        noteService.deleteNote(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/search")
    public ResponseEntity<List<Note>> searchNotes(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "content", required = false) String content) {
        List<Note> notes;

        if (title != null && !title.isEmpty()) {
            notes = noteService.searchNotesByTitle(title);
        } else if (content != null && !content.isEmpty()) {
            notes = noteService.searchNotesByContent(content);
        } else {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(notes);
    }

    @GetMapping("/date")
    public List<Note> getNotesByDate(
            @RequestParam("after") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date after,
            @RequestParam("before") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date before) {
        return noteService.getNotesCreatedBetween(after, before);
    }

    @GetMapping("/recent")
    public List<Note> getRecentNotes() {
        return noteService.getRecentNotes();
    }
}
