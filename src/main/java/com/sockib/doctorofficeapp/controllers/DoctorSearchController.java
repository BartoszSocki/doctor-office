package com.sockib.doctorofficeapp.controllers;

import com.sockib.doctorofficeapp.entities.ClientInfo;
import com.sockib.doctorofficeapp.entities.DoctorInfo;
import com.sockib.doctorofficeapp.entities.Note;
import com.sockib.doctorofficeapp.model.assemblers.DoctorInfoModelAssembler;
import com.sockib.doctorofficeapp.model.assemblers.NoteModelAssembler;
import com.sockib.doctorofficeapp.model.dto.DoctorSearchDataDto;
import com.sockib.doctorofficeapp.repositories.DoctorInfoRepository;
import com.sockib.doctorofficeapp.repositories.RegisteredUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor

@CrossOrigin
@RestController
@RequestMapping(path = "/api/client/doctors")
public class DoctorSearchController {

    // TODO move logic to service
    private DoctorInfoRepository doctorInfoRepository;
    private PagedResourcesAssembler<DoctorInfo> pagedResourcesAssembler;
    private DoctorInfoModelAssembler doctorInfoModelAssembler;

    @GetMapping
    public ResponseEntity<PagedModel<DoctorSearchDataDto>> searchDoctors(@PageableDefault(value = 10) Pageable pageable) {
        var doctors = doctorInfoRepository.findDoctors(pageable);
        var doctorsDto = pagedResourcesAssembler.toModel(doctors, doctorInfoModelAssembler);

        return ResponseEntity.ok(doctorsDto);
    }

}
