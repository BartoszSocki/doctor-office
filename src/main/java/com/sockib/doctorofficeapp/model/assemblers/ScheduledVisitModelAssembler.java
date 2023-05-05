package com.sockib.doctorofficeapp.model.assemblers;

import com.sockib.doctorofficeapp.controllers.NotesController;
import com.sockib.doctorofficeapp.controllers.ScheduledVisitsController;
import com.sockib.doctorofficeapp.entities.Note;
import com.sockib.doctorofficeapp.entities.ScheduledVisit;
import com.sockib.doctorofficeapp.model.dto.NoteDataDto;
import com.sockib.doctorofficeapp.model.dto.ScheduledVisitDto;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ScheduledVisitModelAssembler extends RepresentationModelAssemblerSupport<ScheduledVisit, ScheduledVisitDto> {

    private final ModelMapper modelMapper;

    public ScheduledVisitModelAssembler(ModelMapper modelMapper) {
        super(ScheduledVisitsController.class, ScheduledVisitDto.class);
        this.modelMapper = modelMapper;
    }

    @Override
    public ScheduledVisitDto toModel(ScheduledVisit scheduledVisit) {
        return modelMapper.map(scheduledVisit, ScheduledVisitDto.class)
                .add(linkTo(methodOn(ScheduledVisitsController.class).getVisit(scheduledVisit.getId())).withSelfRel());
    }

    @Override
    public CollectionModel<ScheduledVisitDto> toCollectionModel(Iterable<? extends ScheduledVisit> scheduledVisits) {
        return super.toCollectionModel(scheduledVisits)
                .add(linkTo(methodOn(ScheduledVisitsController.class).getVisits(null)).withSelfRel());
    }
}



