package com.example.demo.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Exercise1FormData {

    @NotBlank(message = "Flight number is required")
    @NotNull(message = "Flight number is required")
    @Size(min=4,max=4,message = "Flight number has to consist of at least 4 characters")
    private String flightNumber;

    @NotBlank(message = "Date is required")
    @NotNull(message = "Date is required")
    @DateTimeFormat(pattern="dd-MMM-yyyy")
    private String date;

    public Exercise1FormData(@NotBlank(message = "Flight number is required") @NotNull(message = "Flight number is required") @Size(min = 4, max = 4, message = "Flight number has to consist of at least 4 characters") String flightNumber, @NotBlank(message = "Date is required") @NotNull(message = "Date is required") String date) {
        this.flightNumber = flightNumber;
        this.date = date;
    }

    public Exercise1FormData() {
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
