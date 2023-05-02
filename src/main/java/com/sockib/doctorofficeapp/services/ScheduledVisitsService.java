package com.sockib.doctorofficeapp.services;

import com.sockib.doctorofficeapp.entities.ScheduledVisit;
import com.sockib.doctorofficeapp.enums.DayOfTheWeek;
import com.sockib.doctorofficeapp.model.dto.ScheduledVisitFormDto;
import com.sockib.doctorofficeapp.repositories.DoctorInfoRepository;
import com.sockib.doctorofficeapp.repositories.RegisteredUserRepository;
import com.sockib.doctorofficeapp.repositories.ScheduledVisitsRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@AllArgsConstructor
public class ScheduledVisitsService {

    private RegisteredUserRepository registeredUserRepository;
    private ScheduledVisitsRepository scheduledVisitRepository;

    public ScheduledVisit getScheduledVisit(Long visitId) {
        return scheduledVisitRepository.findById(visitId)
                .orElseThrow(() -> new RuntimeException("TODO"));
    }

    public List<ScheduledVisit> getScheduledVisits(Long doctorId) {
        return scheduledVisitRepository.findScheduledVisitsByRegisteredDoctorId(doctorId);
    }

    @Transactional
    public void removeScheduledVisit(Long visitId, String username) {
        var scheduledVisit = scheduledVisitRepository.findByIdAndDoctorUsername(visitId, username)
                .orElseThrow(() -> new RuntimeException("TODO"));

        scheduledVisitRepository.delete(scheduledVisit);
    }

    @Transactional
    public void updateScheduledVisit(ScheduledVisitFormDto scheduledVisitFormDto, Long visitId, String username) {
        var scheduledVisit = scheduledVisitRepository.findByIdAndDoctorUsername(visitId, username).
                orElseThrow(() -> new RuntimeException("TODO"));

        scheduledVisit.setVisitBegTime(scheduledVisitFormDto.getVisitBegTime());
        scheduledVisit.setVisitEndTime(scheduledVisitFormDto.getVisitEndTime());
        scheduledVisit.setType(scheduledVisitFormDto.getType());
        scheduledVisit.setPrice(scheduledVisitFormDto.getPrice());

//         TODO needs validation
        scheduledVisit.setDayOfTheWeek(DayOfTheWeek.valueOf(scheduledVisitFormDto.getDayOfTheWeek()));
        scheduledVisit.setLocalization(scheduledVisitFormDto.getLocalization());

        scheduledVisitRepository.save(scheduledVisit);
    }

    public ScheduledVisit createScheduledVisit(ScheduledVisitFormDto scheduledVisitFormDto, Principal principal) {
        var scheduledVisit = new ScheduledVisit();

        scheduledVisit.setVisitBegTime(scheduledVisitFormDto.getVisitBegTime());
        scheduledVisit.setVisitEndTime(scheduledVisitFormDto.getVisitEndTime());
        scheduledVisit.setType(scheduledVisitFormDto.getType());
        scheduledVisit.setPrice(scheduledVisitFormDto.getPrice());

//         TODO needs validation
        scheduledVisit.setDayOfTheWeek(DayOfTheWeek.valueOf(scheduledVisitFormDto.getDayOfTheWeek()));
        scheduledVisit.setLocalization(scheduledVisitFormDto.getLocalization());

        var username = principal.getName();
        var registeredDoctor = registeredUserRepository.findRegisteredUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        scheduledVisit.setRegisteredDoctor(registeredDoctor);
        return scheduledVisitRepository.save(scheduledVisit);
    }

}
