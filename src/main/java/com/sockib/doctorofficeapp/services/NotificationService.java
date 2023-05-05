package com.sockib.doctorofficeapp.services;

import com.sockib.doctorofficeapp.repositories.PlannedVisitsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

@Slf4j

@Service
public class NotificationService {

    private MailService mailService;
    private PlannedVisitsRepository plannedVisitsRepository;
    private ThreadPoolTaskScheduler taskScheduler;

    public NotificationService(MailService mailService, PlannedVisitsRepository plannedVisitsRepository, @Qualifier("threadPoolTaskScheduler") ThreadPoolTaskScheduler taskScheduler) {
        this.mailService = mailService;
        this.plannedVisitsRepository = plannedVisitsRepository;
        this.taskScheduler = taskScheduler;
    }

//    @Scheduled(cron = "0 * * * * *")
    @Scheduled(cron = "@hourly")
    public void sendNotificationsOnIncomingVisits() {
        var now = LocalDateTime.now();
        var beg = now.plus(15, ChronoUnit.MINUTES).minus(5, ChronoUnit.SECONDS);
        var end = beg.plus(1, ChronoUnit.HOURS).plus(5, ChronoUnit.SECONDS);

        var plannedVisits = plannedVisitsRepository.findActivePlannedVisitsByInterval(beg, end);

        plannedVisits.forEach(v -> {
            var timeToExecute = v.getDay().minus(15, ChronoUnit.MINUTES)
                    .toInstant(ZoneOffset.ofHours(2));
            var timeOfVisit = v.getDay()
                    .toInstant(ZoneOffset.ofHours(0));

            var doctorEmail = v.getRegisteredDoctor().getUsername();
            var clientEmail = v.getRegisteredClient().getUsername();

            v.getVisitStatus().setIsMailScheduled(true);
            taskScheduler.schedule(() -> {
                var subject = "Visit in 15 minutes";
                var message = "Your visit is in 15 minutes (" + timeOfVisit + ")";

                mailService.sendMail(doctorEmail, subject, message);
                mailService.sendMail(clientEmail, subject, message);

                v.getVisitStatus().setWasMailSend(true);
                plannedVisitsRepository.save(v);
            }, timeToExecute);
        });

    }

}
