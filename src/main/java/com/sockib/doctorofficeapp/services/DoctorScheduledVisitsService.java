package com.sockib.doctorofficeapp.services;

import com.sockib.doctorofficeapp.entities.ScheduledVisit;
import com.sockib.doctorofficeapp.enums.DayOfTheWeek;
import com.sockib.doctorofficeapp.model.dto.ScheduledVisitFormDto;
import com.sockib.doctorofficeapp.repositories.DoctorCredentialsRepository;
import com.sockib.doctorofficeapp.repositories.ScheduledVisitRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;

@Service
@AllArgsConstructor
public class DoctorScheduledVisitsService {

    private DoctorCredentialsRepository doctorCredentialsRepository;
    private ScheduledVisitRepository scheduledVisitRepository;

    public ScheduledVisit getScheduledVisits(Long visitId) {
        return scheduledVisitRepository.findById(visitId)
                .orElseThrow(() -> new RuntimeException("TODO"));
    }

    public void removeScheduledVisit(Long visitId) {
        scheduledVisitRepository.deleteById(visitId);
    }

    public void updateScheduledVisit(ScheduledVisitFormDto scheduledVisitFormDto, Long visitId) {
        var scheduledVisit = scheduledVisitRepository.findById(visitId).
                orElseThrow(() -> new RuntimeException("TODO"));

        scheduledVisit.setVisitBegTime(scheduledVisitFormDto.getVisitBegTime());
        scheduledVisit.setVisitEndTime(scheduledVisitFormDto.getVisitEndTime());
        scheduledVisit.setType(scheduledVisitFormDto.getType());
        scheduledVisit.setPrice(scheduledVisitFormDto.getPrice());

        // TODO needs validation
        scheduledVisit.setDayOfTheWeek(DayOfTheWeek.valueOf(scheduledVisitFormDto.getDayOfTheWeek()));
        scheduledVisit.setLocalization(scheduledVisitFormDto.getLocalization());

        scheduledVisitRepository.save(scheduledVisit);
    }

    public void createScheduledVisit(ScheduledVisitFormDto scheduledVisitFormDto, Principal principal) {
        var scheduledVisit = new ScheduledVisit();

        scheduledVisit.setVisitBegTime(scheduledVisitFormDto.getVisitBegTime());
        scheduledVisit.setVisitEndTime(scheduledVisitFormDto.getVisitEndTime());
        scheduledVisit.setType(scheduledVisitFormDto.getType());
        scheduledVisit.setPrice(scheduledVisitFormDto.getPrice());

        // TODO needs validation
        scheduledVisit.setDayOfTheWeek(DayOfTheWeek.valueOf(scheduledVisitFormDto.getDayOfTheWeek()));
        scheduledVisit.setLocalization(scheduledVisitFormDto.getLocalization());

        var registeredDoctor = doctorCredentialsRepository.findRegisteredDoctorByUsername(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException(principal.getName()));

        scheduledVisit.setRegisteredDoctor(registeredDoctor);
        scheduledVisitRepository.save(scheduledVisit);
    }

}
