package com.sockib.doctorofficeapp.model.assemblers;

import com.sockib.doctorofficeapp.controllers.NotesController;
import com.sockib.doctorofficeapp.controllers.PlannedVisitsController;
import com.sockib.doctorofficeapp.entities.Note;
import com.sockib.doctorofficeapp.entities.PlannedVisit;
import com.sockib.doctorofficeapp.model.dto.NoteDataDto;
import com.sockib.doctorofficeapp.model.dto.PlannedVisitDto;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PlannedVisitsModelAssembler extends RepresentationModelAssemblerSupport<PlannedVisit, PlannedVisitDto> {

    private final ModelMapper modelMapper;

    public PlannedVisitsModelAssembler(ModelMapper modelMapper) {
        super(PlannedVisitsController.class, PlannedVisitDto.class);
        this.modelMapper = modelMapper;
    }

    @Override
    public PlannedVisitDto toModel(PlannedVisit plannedVisit) {
        return modelMapper.map(plannedVisit, PlannedVisitDto.class)
                .add(linkTo(methodOn(PlannedVisitsController.class).getClientPlannedVisits(plannedVisit.getId())).withSelfRel());
    }

    @Override
    public CollectionModel<NoteDataDto> toCollectionModel(Iterable<? extends Note> notes) {
        return super.toCollectionModel(notes)
                .add(linkTo(methodOn(NotesController.class).getDoctorNotes(null)).withSelfRel());
    }
}
