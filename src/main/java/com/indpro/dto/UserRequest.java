package com.indpro.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;

@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Data
public class UserRequest {

    @NotNull(message = "Please provide username while registrating")
    private String username;

    @NotNull(message = "Please provide first name while registrating")
    private String firstName;

    private String lastName;

    @NotNull(message = "Please provide password while registrating")
    private String password;

    @Email
    private String email;

    @NotNull(message = "Please provide valid mobile no while registrating")
    @Pattern(regexp = "^\\d{10}$")
    private String mobileNo;

    private String roles;

    private Boolean canEdit;

}
