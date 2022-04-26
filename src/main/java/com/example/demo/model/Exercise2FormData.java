package com.example.demo.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Exercise2FormData {

    @NotBlank(message = "IATA code is required")
    @NotNull(message = "IATA code is required")
    @Size(min=3,max=3,message = "IATA code has to consist of 3 characters")
    private String IATACode;

    @NotBlank(message = "Date is required")
    @NotNull(message = "Date is required")
    @DateTimeFormat(pattern="dd-MMM-yyyy")
    private String date;


    public Exercise2FormData() {
    }

    public Exercise2FormData(@NotBlank(message = "IATA code is required") @NotNull(message = "IATA code is required") @Size(min = 3, max = 3, message = "IATA code has to consist of 3 characters") String IATACode, @NotBlank(message = "Date is required") @NotNull(message = "Date is required") String date) {
        this.IATACode = IATACode;
        this.date = date;
    }

    public String getIATACode() {
        return IATACode;
    }

    public void setIATACode(String IATACode) {
        this.IATACode = IATACode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
