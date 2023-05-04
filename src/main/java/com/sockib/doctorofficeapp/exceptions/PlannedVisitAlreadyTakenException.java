package com.sockib.doctorofficeapp.exceptions;

public class PlannedVisitAlreadyTakenException extends RuntimeException {

    public PlannedVisitAlreadyTakenException(String message) {
        super(message);
    }

}
