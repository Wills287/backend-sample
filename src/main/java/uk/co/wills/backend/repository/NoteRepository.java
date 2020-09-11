package uk.co.wills.backend.repository;

import org.springframework.data.repository.CrudRepository;
import uk.co.wills.backend.model.Note;

public interface NoteRepository extends CrudRepository<Note, Long> {
}
