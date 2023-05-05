package com.sockib.doctorofficeapp.entities.embeded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data

@Embeddable
public class VisitStatus {

    private Boolean canceled;

    @Column(name = "was_mail_send")
    private Boolean wasMailSend;

    @Column(name = "is_mail_scheduled")
    private Boolean isMailScheduled;

}
