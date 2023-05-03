package com.sockib.doctorofficeapp.services;

import com.sockib.doctorofficeapp.entities.ScheduledVisit;
import com.sockib.doctorofficeapp.enums.DayOfTheWeek;
import com.sockib.doctorofficeapp.model.dto.ScheduledVisitFormDto;
import com.sockib.doctorofficeapp.repositories.RegisteredUserRepository;
import com.sockib.doctorofficeapp.repositories.ScheduledVisitsRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor

@Service
public class ScheduledVisitsService {

    private RegisteredUserRepository registeredUserRepository;
    private ScheduledVisitsRepository scheduledVisitRepository;

    private ModelMapper modelMapper;

    public ScheduledVisit getScheduledVisit(Long visitId) {
        return scheduledVisitRepository.findById(visitId)
                .orElseThrow(() -> new RuntimeException("TODO"));
    }

    public List<ScheduledVisit> getAllScheduledVisits(Long doctorId) {
        return scheduledVisitRepository.findScheduledVisitsByRegisteredDoctorId(doctorId);
    }

    public List<ScheduledVisit> getEnabledScheduledVisits(Long doctorId) {
        return scheduledVisitRepository.findEnabledScheduledVisitsByDoctorId(doctorId);
    }

    @Transactional
    public void disableScheduledVisit(Long visitId, String username) {
        var scheduledVisit = scheduledVisitRepository.findByIdAndDoctorUsername(visitId, username)
                .orElseThrow(() -> new RuntimeException("TODO"));

        if (scheduledVisit.getDisabled().equals(Boolean.FALSE)) {
            throw new RuntimeException("TODO");
        }

        scheduledVisit.setDisabled(true);
        scheduledVisitRepository.save(scheduledVisit);
    }

    @Transactional
    public ScheduledVisit updateScheduledVisit(ScheduledVisitFormDto scheduledVisitFormDto, Long visitId, String username) {
        var scheduledVisit = scheduledVisitRepository.findByIdAndDoctorUsername(visitId, username)
                        .orElseThrow(() -> new RuntimeException("TODO"));

        scheduledVisit.setVisitBegTime(scheduledVisitFormDto.getVisitBegTime());
        scheduledVisit.setVisitEndTime(scheduledVisitFormDto.getVisitEndTime());
        scheduledVisit.setType(scheduledVisitFormDto.getType());
        scheduledVisit.setPrice(scheduledVisitFormDto.getPrice());

//         TODO needs validation
        scheduledVisit.setDayOfTheWeek(DayOfTheWeek.valueOf(scheduledVisitFormDto.getDayOfTheWeek()));
        scheduledVisit.setLocalization(scheduledVisitFormDto.getLocalization());

        return scheduledVisitRepository.save(scheduledVisit);
    }

    public ScheduledVisit createScheduledVisit(ScheduledVisitFormDto scheduledVisitFormDto, String username) {
        var registeredDoctor = registeredUserRepository.findRegisteredUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        var scheduledVisit = modelMapper.map(scheduledVisitFormDto, ScheduledVisit.class);

//         TODO needs validation
        scheduledVisit.setDayOfTheWeek(DayOfTheWeek.valueOf(scheduledVisitFormDto.getDayOfTheWeek()));
        scheduledVisit.setRegisteredDoctor(registeredDoctor);

        return scheduledVisitRepository.save(scheduledVisit);
    }

}
