package com.mindata.riu.domain.model;

import com.mindata.riu.domain.exception.availability.InvalidAgeException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record Availability(

        @NotBlank
        String hotelId,

        @NotNull
        LocalDate checkIn,

        @NotNull
        LocalDate checkOut,

        @NotNull
        @NotEmpty
        List<Integer> ages
) {

        public Availability {
                ages.forEach(age -> {
                        if (age == null || age <= 0) throw new InvalidAgeException(age);
                });
        }

}