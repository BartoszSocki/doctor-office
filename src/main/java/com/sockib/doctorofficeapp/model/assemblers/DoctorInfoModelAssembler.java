package com.sockib.doctorofficeapp.model.assemblers;

import com.sockib.doctorofficeapp.controllers.DoctorSearchController;
import com.sockib.doctorofficeapp.controllers.PlannedVisitsController;
import com.sockib.doctorofficeapp.entities.DoctorInfo;
import com.sockib.doctorofficeapp.entities.PlannedVisit;
import com.sockib.doctorofficeapp.model.dto.DoctorSearchDataDto;
import com.sockib.doctorofficeapp.model.dto.PlannedVisitDto;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DoctorInfoModelAssembler extends RepresentationModelAssemblerSupport<DoctorInfo, DoctorSearchDataDto> {
    private final ModelMapper modelMapper;

    public DoctorInfoModelAssembler(ModelMapper modelMapper) {
        super(DoctorInfo.class, DoctorSearchDataDto.class);
        this.modelMapper = modelMapper;
    }

    @Override
    public DoctorSearchDataDto toModel(DoctorInfo doctorInfo) {
        var doctorSearchDataDto = modelMapper.map(doctorInfo.getRegisteredUser(), DoctorSearchDataDto.class);
        doctorSearchDataDto.setSpecialization(doctorInfo.getSpecialization());
        return doctorSearchDataDto;
    }

    @Override
    public CollectionModel<DoctorSearchDataDto> toCollectionModel(Iterable<? extends DoctorInfo> doctorInfos) {
        return super.toCollectionModel(doctorInfos);
    }
}
