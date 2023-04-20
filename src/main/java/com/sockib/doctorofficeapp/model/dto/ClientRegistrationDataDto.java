package com.sockib.doctorofficeapp.model.dto;

import com.sockib.doctorofficeapp.annotations.MatchingPassword;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@MatchingPassword(passwordField = "password", matchingPasswordField = "passwordRepeated")
public class ClientRegistrationDataDto {

    @NotBlank
    private String username;

    @NotBlank
//    @StrongPassword
    private String password;

    @NotBlank
    private String matchingPassword;

}

