package com.sockib.doctorofficeapp.model.assemblers;

import com.sockib.doctorofficeapp.controllers.NotesController;
import com.sockib.doctorofficeapp.entities.Note;
import com.sockib.doctorofficeapp.model.dto.NoteDataDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class NoteModelAssembler extends RepresentationModelAssemblerSupport<Note, NoteDataDto> {

    private final ModelMapper modelMapper;

    public NoteModelAssembler(ModelMapper modelMapper) {
        super(NotesController.class, NoteDataDto.class);
        this.modelMapper = modelMapper;
    }

    @Override
    public NoteDataDto toModel(Note note) {
        return modelMapper.map(note, NoteDataDto.class)
                .add(linkTo(methodOn(NotesController.class).getNote(note.getId())).withSelfRel());
    }

    @Override
    public CollectionModel<NoteDataDto> toCollectionModel(Iterable<? extends Note> notes) {
        return super.toCollectionModel(notes)
                .add(linkTo(methodOn(NotesController.class).getDoctorNotes(null)).withSelfRel());
    }
}
