package com.sockib.doctorofficeapp.services;

import com.sockib.doctorofficeapp.entities.Note;
import com.sockib.doctorofficeapp.model.dto.NoteDataFormDto;
import com.sockib.doctorofficeapp.repositories.NotesRepository;
import com.sockib.doctorofficeapp.repositories.PlannedVisitsRepository;
import com.sockib.doctorofficeapp.repositories.RegisteredUserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor

@Service
public class NotesService {

    private NotesRepository notesRepository;
    private PlannedVisitsRepository plannedVisitsRepository;
    private ModelMapper modelMapper;
    private RegisteredUserRepository registeredUserRepository;

    public Note getNote(String username, Long noteId) {
        return notesRepository.findNoteByDoctorUsernameAndId(username, noteId)
                .orElseThrow(() -> new RuntimeException("TODO"));
    }

    public Page<Note> getNotesByDoctorId(String username, Pageable pageable) {
        return notesRepository.findAllByDoctorUsername(username, pageable);
    }

    public Note editNote(String username, Long noteId, Optional<NoteDataFormDto> optionalNoteDataFormDto) {
        var noteDataFormDto = optionalNoteDataFormDto.orElseThrow(() -> new RuntimeException("TODO"));
        var note = this.getNote(username, noteId);

        note.setName(noteDataFormDto.getName());
        note.setContent((noteDataFormDto.getContent()));

        return notesRepository.save(note);
    }

    public void deleteNote(String username, Long noteId) {
        var note = notesRepository.findNoteByDoctorUsernameAndId(username, noteId)
                .orElseThrow(() -> new RuntimeException("TODO"));

        notesRepository.delete(note);
    }

    public Note createNote(String username, Long plannedVisitId, NoteDataFormDto noteDataFormDto) {
        var plannedVisit = plannedVisitsRepository.findPlannedVisitByDoctorUsernameAndVisitId(username, plannedVisitId)
                .orElseThrow(() -> new RuntimeException("TODO"));

        var registeredDoctor = registeredUserRepository.findRegisteredUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("TODO"));

        var note = modelMapper.map(noteDataFormDto, Note.class);
        note.setRegisteredDoctor(registeredDoctor);
        note.setPlannedVisit(plannedVisit);

        return notesRepository.save(note);
    }

}
