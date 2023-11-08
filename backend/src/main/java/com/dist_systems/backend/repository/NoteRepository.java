// src/main/java/com/dist_systems/backend/repository/NoteRepository.java

package com.dist_systems.backend.repository;

import com.dist_systems.backend.model.Note;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface NoteRepository extends MongoRepository<Note, String> {

    // Find notes with title containing the given string (case-insensitive)
    List<Note> findByTitleContainingIgnoreCase(String title);

    // Find notes by content matching a string (case-insensitive)
    List<Note> findByContentContainingIgnoreCase(String content);

    // Find notes created after a certain date
    List<Note> findByCreatedAtAfter(Date date);

    // Find notes created before a certain date
    List<Note> findByCreatedAtBefore(Date date);

    // Find notes between two dates
    List<Note> findByCreatedAtBetween(Date startDate, Date endDate);

    // Find the most recent notes
    default List<Note> findRecentNotes() {
        return findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    // Find notes by a custom query, notes with a specific tag
    @Query("{ 'tags' : ?0 }")
    List<Note> findByTag(String tag);
}
