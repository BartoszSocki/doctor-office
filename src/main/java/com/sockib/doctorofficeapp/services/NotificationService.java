package com.sockib.doctorofficeapp.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor

@Service
public class NotificationService {

    private MailService mailService;

    @Scheduled(cron = "@hourly")
    @Async
    public void sendNotificationsOnIncomingVisits() {
        log.info("hello");
    }

}
