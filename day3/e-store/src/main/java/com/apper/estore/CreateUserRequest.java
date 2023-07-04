package com.apper.estore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserRequest {

    @NotBlank(message = "'email' is required")
    @Email(message = "invalid email format")
    private String email;

    @NotBlank(message = "password is required")
    @Size(min = 8, message = "password must be 8 characters")
    private String password;

    @JsonProperty("first_name")
    @NotBlank(message = "first name is required")
    private String firstName;

    @JsonProperty("middle_name")
    private String middleName;

    @JsonProperty("last_name")
    @NotBlank(message = "last name is required")
    private String lastName;

    @JsonProperty("birth_date")
    @NotBlank(message = "birth date is required")
    @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}", message = "birth date must be YYYY-MM-DD")
    private String birthDate;
}
