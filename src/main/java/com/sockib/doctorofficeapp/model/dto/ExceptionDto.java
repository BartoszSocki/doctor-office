package com.sockib.doctorofficeapp.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExceptionDto {

    private String message;
    private LocalDateTime timestamp;

    public static ExceptionDto toDto(Exception ex) {
        var exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(ex.getMessage());
        exceptionDto.setTimestamp(LocalDateTime.now());

        return exceptionDto;
    }

}
