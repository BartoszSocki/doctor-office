package com.sockib.doctorofficeapp.model.dto;

import com.sockib.doctorofficeapp.annotations.MatchingPassword;
import com.sockib.doctorofficeapp.annotations.StrongPassword;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MatchingPassword(originalPasswordField = "password", matchingPasswordField = "passwordRepeated")
public class UserRegisterDataDto {

    @NotBlank
    private String username;

    @NotBlank
    @StrongPassword
    private String password;

    @NotBlank
    private String passwordRepeated;

}
