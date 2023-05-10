package com.sockib.doctorofficeapp.controllers;

import com.sockib.doctorofficeapp.entities.Note;
import com.sockib.doctorofficeapp.model.assemblers.NoteModelAssembler;
import com.sockib.doctorofficeapp.model.dto.NoteDataDto;
import com.sockib.doctorofficeapp.model.dto.NoteDataFormDto;
import com.sockib.doctorofficeapp.services.NotesService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@AllArgsConstructor

@CrossOrigin
@PreAuthorize("hasRole('DOCTOR')")
@RestController
@RequestMapping(path = "/api/doctor/notes")
public class NotesController {

    private NotesService notesService;
    private PagedResourcesAssembler<Note> pagedResourcesAssembler;
    private NoteModelAssembler noteModelAssembler;

    @GetMapping
    public ResponseEntity<PagedModel<NoteDataDto>> getDoctorNotes(Pageable pageable) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var page = notesService.getNotesByDoctorUsername(authentication.getName(), pageable);
        var collectionModel = pagedResourcesAssembler.toModel(page, noteModelAssembler);

        return ResponseEntity.ok(collectionModel);
    }

    @GetMapping(path = "/client/{clientId}")
    public ResponseEntity<PagedModel<NoteDataDto>> getDoctorNotesForClient(@PathVariable Long clientId, Pageable pageable) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var page = notesService.getDoctorNotesForClientId(authentication.getName(), clientId, pageable);
        var collectionModel = pagedResourcesAssembler.toModel(page, noteModelAssembler);

        return ResponseEntity.ok(collectionModel);
    }

    @GetMapping(path = "/{noteId}")
    public ResponseEntity<NoteDataDto> getNote(@PathVariable Long noteId) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var note = notesService.getNote(authentication.getName(), noteId);
        var noteDataDto = noteModelAssembler.toModel(note);

        return ResponseEntity.ok(noteDataDto);
    }

    @PutMapping(path = "/{noteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<NoteDataDto> editNote(@PathVariable Long noteId,
                                                @RequestBody NoteDataFormDto noteDataFormDto) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(noteDataFormDto);
        var note = notesService.editNote(authentication.getName(), noteId, noteDataFormDto);
        var noteDataDto = noteModelAssembler.toModel(note);

        return ResponseEntity.ok(noteDataDto);
    }

    @PostMapping(path = "/planned-visit/{plannedVisitId}")
    public ResponseEntity<NoteDataDto> createNote(@PathVariable Long plannedVisitId,
                                                  @RequestBody NoteDataFormDto noteDataFormDto, Principal principal) {
        var note = notesService.createNote(principal.getName(), plannedVisitId, noteDataFormDto);
        var noteDataDto = noteModelAssembler.toModel(note);

        return ResponseEntity.status(HttpStatus.CREATED).body(noteDataDto);
    }

    @DeleteMapping(path = "/{noteId}")
    public void deleteNote(@PathVariable Long noteId, Principal principal) {
        notesService.deleteNote(principal.getName(), noteId);
    }
}
