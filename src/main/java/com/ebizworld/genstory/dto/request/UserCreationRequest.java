package com.ebizworld.genstory.dto.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min = 3, message = "INVALID_REQUEST_USERNAME")
    @JsonProperty("username")
    String username;

    @Size(min = 8, message = "INVALID_REQUEST_PASSWORD")
    @JsonProperty("password")
    String password;

    @Size(min = 2, message = "INVALID_REQUEST_FIRSTNAME")
    @JsonProperty("firstName")
    String firstName;

    @Size(min = 2, message = "INVALID_REQUEST_LASTNAME")
    @JsonProperty("lastName")
    String lastName;

    // @DobConstraint(min = 16, message = "INVALID_DOB")
    LocalDate dob;
}