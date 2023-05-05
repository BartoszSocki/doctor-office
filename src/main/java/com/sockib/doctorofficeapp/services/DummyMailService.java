package com.sockib.doctorofficeapp.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Slf4j

@Profile("dev")
@Service
public class DummyMailService implements MailService {

    @Async
    @Override
    public void sendMail(String to, String subject, String message) {
        log.info("\nmail to " + to + "\nsubject: " + subject + "\nmessage: " + message);
    }

}
