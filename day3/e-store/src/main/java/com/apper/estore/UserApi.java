package com.apper.estore;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;

@RestController
@RequestMapping("user")
public class UserApi {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateUserResponse createUser(@RequestBody @Valid CreateUserRequest request) {
        LocalDate birthDate = LocalDate.parse(request.getBirthDate());
        LocalDate now = LocalDate.now();
        if (Period.between(birthDate, now).getYears() < 15){
            throw new InvalidUserAgeException("Age must be at least 15 years old");
        }
        return new CreateUserResponse("RANDOM_CODE_FOR_NOW");
    }

}