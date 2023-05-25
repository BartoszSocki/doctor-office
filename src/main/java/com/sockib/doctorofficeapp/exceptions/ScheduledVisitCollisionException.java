package com.sockib.doctorofficeapp.exceptions;

public class ScheduledVisitCollisionException extends RuntimeException {
    public ScheduledVisitCollisionException(String message) {
        super(message);
    }
}
