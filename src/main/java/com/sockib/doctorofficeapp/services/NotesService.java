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

import java.time.LocalDate;

@AllArgsConstructor

@Service
public class NotesService {

    private NotesRepository notesRepository;
    private PlannedVisitsRepository plannedVisitsRepository;
    private ModelMapper modelMapper;
    private RegisteredUserRepository registeredUserRepository;

    public Note getOneNote(Long doctorId, Long noteId) {
        var note = notesRepository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("TODO"));

        if (!doctorId.equals(note.getRegisteredDoctor().getId())) {
            throw new AccessDeniedException("TODO");
        }

        return note;
    }

    public Page<Note> getNotesByDoctorId(Long doctorId, Pageable pageable) {
        return notesRepository.findAllByDoctorId(doctorId, pageable);
    }

    public Note editNote(Long doctorId, Long noteId, NoteDataFormDto noteDataFormDto) {
        if (noteDataFormDto == null) {
            throw new RuntimeException("TODO");
        }

        var note = this.getOneNote(doctorId, noteId);

        note.setName(noteDataFormDto.getName());
        note.setContent((noteDataFormDto.getContent()));

        return notesRepository.save(note);
    }

    public void deleteNote(Long doctorId, Long noteId) {
        var note = notesRepository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("TODO"));

        if (!doctorId.equals(note.getRegisteredDoctor().getId())) {
            throw new AccessDeniedException("TODO");
        }

        notesRepository.delete(note);
    }

    public Note createNote(Long doctorId, Long plannedVisitId, NoteDataFormDto noteDataFormDto) {
        var plannedVisit = plannedVisitsRepository.findById(plannedVisitId)
                .orElseThrow(() -> new RuntimeException("TODO"));

        if (!doctorId.equals(plannedVisit.getRegisteredDoctor().getId())) {
            throw new AccessDeniedException("TODO");
        }

        var registeredDoctor = registeredUserRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("TODO"));

        var note = modelMapper.map(noteDataFormDto, Note.class);
        note.setRegisteredDoctor(registeredDoctor);
        note.setPlannedVisit(plannedVisit);

        return notesRepository.save(note);
    }

}
