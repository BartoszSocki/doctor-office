package com.sockib.doctorofficeapp.controllers;

import com.sockib.doctorofficeapp.model.dto.NoteDataDto;
import com.sockib.doctorofficeapp.model.dto.NoteDataFormDto;
import com.sockib.doctorofficeapp.services.DoctorInfoService;
import com.sockib.doctorofficeapp.services.NotesService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@AllArgsConstructor

@PreAuthorize("hasRole('DOCTOR')")
@RestController
@RequestMapping(path = "/api/doctor/notes")
public class NotesController {

    private NotesService notesService;
    private ModelMapper modelMapper;
    private DoctorInfoService doctorInfoService;

    @GetMapping
    public ResponseEntity<Page<NoteDataDto>> getDoctorNotes(Pageable pageable, Principal principal) {
        var doctorId = doctorInfoService.getDoctorRegisteredUserId(principal.getName());
        var page = notesService.getNotesByDoctorId(doctorId, pageable)
                .map(n -> modelMapper.map(n, NoteDataDto.class))
                .map(n -> n.add(linkTo(methodOn(NotesController.class).getNote(n.getId(), principal)).withSelfRel()));

        return ResponseEntity.ok(page);
    }

    @GetMapping(path = "/{noteId}")
    public ResponseEntity<NoteDataDto> getNote(@PathVariable Long noteId, Principal principal) {
        var doctorId = doctorInfoService.getDoctorRegisteredUserId(principal.getName());
        var note = notesService.getOneNote(doctorId, noteId);
        var noteDataDto = modelMapper.map(note, NoteDataDto.class);

        noteDataDto = noteDataDto.add(linkTo(methodOn(NotesController.class).getNote(noteId, principal)).withSelfRel());

        return ResponseEntity.ok(noteDataDto);
    }

    @PutMapping(path = "/{noteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<NoteDataDto> editNote(@PathVariable Long noteId,
                                                @RequestBody NoteDataFormDto noteDataFormDto, Principal principal) {
        var doctorId = doctorInfoService.getDoctorRegisteredUserId(principal.getName());
        var note = notesService.editNote(doctorId, noteId, noteDataFormDto);
        var noteDataDto = modelMapper.map(note, NoteDataDto.class);

        noteDataDto = noteDataDto.add(linkTo(methodOn(NotesController.class).getNote(noteId, principal)).withSelfRel());

        return ResponseEntity.ok(noteDataDto);
    }

    @PostMapping(path = "/plannedVisit/{plannedVisitId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<NoteDataDto> createNote(@PathVariable Long plannedVisitId,
                                                  @RequestBody NoteDataFormDto noteDataFormDto, Principal principal) {
        var doctorId = doctorInfoService.getDoctorRegisteredUserId(principal.getName());
        var note = notesService.createNote(doctorId, plannedVisitId, noteDataFormDto);
        var noteDataDto = modelMapper.map(note, NoteDataDto.class);

        noteDataDto = noteDataDto.add(linkTo(methodOn(NotesController.class).getNote(note.getId(), principal)).withSelfRel());

        return ResponseEntity.ok(noteDataDto);
    }

    @DeleteMapping(path = "/{noteId}")
    public void deleteNote(@PathVariable Long noteId, Principal principal) {
        var doctorId = doctorInfoService.getDoctorRegisteredUserId(principal.getName());
        notesService.deleteNote(doctorId, noteId);
    }
}
