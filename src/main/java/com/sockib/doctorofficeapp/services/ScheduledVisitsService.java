package com.sockib.doctorofficeapp.services;

import com.sockib.doctorofficeapp.entities.RegisteredUser;
import com.sockib.doctorofficeapp.entities.ScheduledVisit;
import com.sockib.doctorofficeapp.entities.embeded.Address;
import com.sockib.doctorofficeapp.enums.DayOfTheWeek;
import com.sockib.doctorofficeapp.exceptions.ScheduledVisitCollisionException;
import com.sockib.doctorofficeapp.exceptions.UnableToGetResourceException;
import com.sockib.doctorofficeapp.exceptions.UserNotFoundException;
import com.sockib.doctorofficeapp.model.dto.ScheduledVisitFormDto;
import com.sockib.doctorofficeapp.repositories.RegisteredUserRepository;
import com.sockib.doctorofficeapp.repositories.ScheduledVisitsRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
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
                .orElseThrow(() -> new UnableToGetResourceException("cannot locate scheduledVisit " + visitId));
    }

    public List<ScheduledVisit> getEnabledScheduledVisitsForDoctorId(Long doctorId) {
        return scheduledVisitRepository.findEnabledScheduledVisitsByDoctorId(doctorId);
    }

    public List<ScheduledVisit> getEnabledScheduledVisitsByDoctorUsername(String username) {
        return scheduledVisitRepository.findEnabledScheduledVisitsByDoctorUsername(username);
    }

    @Transactional
    public void disableScheduledVisit(Long visitId, String username) {
        var scheduledVisit = scheduledVisitRepository.findByIdAndDoctorUsername(visitId, username)
                .orElseThrow(() -> new UnableToGetResourceException("cannot locate scheduledVisit " + visitId + " for " + username));

        scheduledVisit.setDisabled(true);
        scheduledVisitRepository.save(scheduledVisit);
    }

    @Transactional
    public ScheduledVisit updateScheduledVisit(ScheduledVisitFormDto scheduledVisitFormDto, Long visitId, String username) {
        var scheduledVisit = scheduledVisitRepository.findByIdAndDoctorUsername(visitId, username)
                        .orElseThrow(() -> new UnableToGetResourceException("cannot locate scheduledVisit " + visitId + " for " + username));

        scheduledVisit.setVisitBegTime(scheduledVisitFormDto.getVisitBegTime());
        scheduledVisit.setVisitEndTime(scheduledVisitFormDto.getVisitEndTime());
        scheduledVisit.setType(scheduledVisitFormDto.getType());
        scheduledVisit.setPrice(scheduledVisitFormDto.getPrice());

//         TODO needs validation
//        scheduledVisit.setDayOfTheWeek(DayOfTheWeek.valueOf(scheduledVisitFormDto.getDayOfTheWeek()));

        var address = modelMapper.map(scheduledVisitFormDto, Address.class);
        scheduledVisit.setAddress(address);

        return scheduledVisitRepository.save(scheduledVisit);
    }

    public ScheduledVisit createScheduledVisit(ScheduledVisitFormDto scheduledVisitFormDto, String username) {
        var registeredDoctor = registeredUserRepository.findRegisteredUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("user " + username + " not found"));

        var scheduledVisit = modelMapper.map(scheduledVisitFormDto, ScheduledVisit.class);
        if (isColliding(scheduledVisit, registeredDoctor)) {
            throw new ScheduledVisitCollisionException("time window " + scheduledVisit.getVisitBegTime() + " - " + scheduledVisit.getVisitEndTime() + "is colliding with another visit");
        }

//         TODO needs validation
        scheduledVisit.setRegisteredDoctor(registeredDoctor);
//        scheduledVisit.setDayOfTheWeek(DayOfTheWeek.valueOf(scheduledVisitFormDto.getDayOfTheWeek()));

        return scheduledVisitRepository.save(scheduledVisit);
    }

    private boolean isColliding(ScheduledVisit scheduledVisit, RegisteredUser registeredDoctor) {
        var beg = scheduledVisit.getVisitBegTime();
        var end = scheduledVisit.getVisitEndTime();
        var day = scheduledVisit.getDayOfTheWeek();
        var visits = scheduledVisitRepository.findByTimeIntervalAndDoctorUsername(registeredDoctor.getUsername(), beg, end, day);
        return !visits.isEmpty();
    }
}
