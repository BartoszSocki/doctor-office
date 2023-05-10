package com.sockib.doctorofficeapp.services;

import com.sockib.doctorofficeapp.entities.Note;
import com.sockib.doctorofficeapp.exceptions.UnableToGetResourceException;
import com.sockib.doctorofficeapp.exceptions.UserNotFoundException;
import com.sockib.doctorofficeapp.model.dto.NoteDataFormDto;
import com.sockib.doctorofficeapp.repositories.NotesRepository;
import com.sockib.doctorofficeapp.repositories.PlannedVisitsRepository;
import com.sockib.doctorofficeapp.repositories.RegisteredUserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor

@Service
public class NotesService {

    private NotesRepository notesRepository;
    private PlannedVisitsRepository plannedVisitsRepository;
    private ModelMapper modelMapper;
    private RegisteredUserRepository registeredUserRepository;

    public Note getNote(String username, Long noteId) {
        return notesRepository.findNoteByDoctorUsernameAndId(username, noteId)
                .orElseThrow(() -> new UnableToGetResourceException("cannot locate note " + noteId + " for " + username));
    }

    public Page<Note> getNotesByDoctorUsername(String username, Pageable pageable) {
        return notesRepository.findAllByDoctorUsername(username, pageable);
    }

    public Page<Note> getDoctorNotesForClientId(String username, Long clientId, Pageable pageable) {
        return notesRepository.getDoctorNotesForClientId(username, clientId, pageable);
    }

    public Note editNote(String username, Long noteId, NoteDataFormDto noteDataFormDto) {
        var note = getNote(username, noteId);

        note.setName(noteDataFormDto.getName());
        note.setContent((noteDataFormDto.getContent()));

        return notesRepository.save(note);
    }

    public void deleteNote(String username, Long noteId) {
        var note = notesRepository.findNoteByDoctorUsernameAndId(username, noteId)
                .orElseThrow(() -> new UnableToGetResourceException("cannot locate note " + noteId + " for " + username));

        notesRepository.delete(note);
    }

    public Note createNote(String username, Long plannedVisitId, NoteDataFormDto noteDataFormDto) {
        var plannedVisit = plannedVisitsRepository.findPlannedVisitByDoctorUsernameAndVisitId(username, plannedVisitId)
                .orElseThrow(() -> new UnableToGetResourceException("cannot locate plannedVisit " + plannedVisitId + " for " + username));

        var registeredDoctor = registeredUserRepository.findRegisteredUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("user " + username + " not found"));

        var note = modelMapper.map(noteDataFormDto, Note.class);
        note.setRegisteredDoctor(registeredDoctor);
        note.setPlannedVisit(plannedVisit);

        return notesRepository.save(note);
    }

}
