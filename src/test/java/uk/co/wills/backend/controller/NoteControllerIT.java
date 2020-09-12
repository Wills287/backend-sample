package uk.co.wills.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import uk.co.wills.backend.model.Note;
import uk.co.wills.backend.repository.NoteRepository;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class NoteControllerIT {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void beforeEach(
            @Autowired NoteRepository noteRepository
    ) {
        noteRepository.deleteAll();
        noteRepository.save(note());
    }

    @Test
    void shouldList() throws Exception {
        mockMvc.perform(
                get("/api/note")
        )
                .andExpect(
                        status().isOk()
                )
                .andExpect(
                        content().contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(
                        jsonPath("$", hasSize(greaterThan(0)))
                );
    }

    @Test
    void shouldCreate() throws Exception {
        mockMvc.perform(
                post("/api/note")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(note()))
        )
                .andExpect(
                        status().isOk()
                )
                .andExpect(
                        content().contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(
                        jsonPath("$.title", equalTo("Title"))
                )
                .andExpect(
                        jsonPath("$.body", equalTo("Body"))
                );
    }

    private Note note() {
        Note note = new Note();
        note.setTitle("Title");
        note.setBody("Body");
        return note;
    }
}
