package com.example.demo.controllers;

import com.example.demo.model.Exercise1FormData;
import com.example.demo.model.Flight;
import com.example.demo.model.FlightShipment;
import com.example.demo.services.CalculateWeightService;
import com.example.demo.services.DataFilterService;
import com.example.demo.services.DataParsingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ExerciseOnePageController {

    List<Flight> flights;
    List<FlightShipment> flightsShipments;

    DataFilterService dataFilterService;
    CalculateWeightService calculateWeightService;
    DataParsingService dataParsingService;
    File flightDataFile = new File("src/main/resources/flight_data.json");
    File cargoDataFile = new File("src/main/resources/cargo_data.json");

    public ExerciseOnePageController(CalculateWeightService calculateWeightService, DataParsingService dataParsingService, DataFilterService dataFilterService) {

        this.calculateWeightService = calculateWeightService;
        this.flights = new ArrayList<>();
        this.flightsShipments = new ArrayList<>();
        this.dataParsingService = dataParsingService;
        this.dataFilterService = dataFilterService;
    }

    @GetMapping(value = {"/showFirstPage", "/"})
    public String showFirstPage(Model model) {

        model.addAttribute("exercise1FormData", new Exercise1FormData());
        return "exercise-1";
    }


    @PostMapping("/processForm")
    public String processForm(@ModelAttribute @Valid Exercise1FormData exercise1FormData, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "exercise-1";
        }


        flights = dataParsingService.parseFlightData(flightDataFile);
        flightsShipments = dataParsingService.parseFlightShipmentData(cargoDataFile);

        if (flights != null && flightsShipments != null) {
            if (dataFilterService.findFlight(flights, Integer.parseInt(exercise1FormData.getFlightNumber()), exercise1FormData.getDate()) == null) {
                model.addAttribute("flightNumber", "No data");
                return "exercise-1";
            } else {

                FlightShipment flightShipment = dataFilterService.findFlightShipment(Integer.parseInt(exercise1FormData.getFlightNumber()), exercise1FormData.getDate(), flights, flightsShipments);

                model.addAttribute("flightNumber", exercise1FormData.getFlightNumber());
                model.addAttribute("flightDate", exercise1FormData.getDate());
                if (flightShipment != null) {

                    model.addAttribute("cargoWeightInKg", calculateWeightService.calculateCargoWeight(flightShipment, "kg"));
                    model.addAttribute("cargoWeightInLb", calculateWeightService.calculateCargoWeight(flightShipment, "lb"));
                    model.addAttribute("baggageWeightInKg", calculateWeightService.calculateBaggageWeight(flightShipment, "kg"));
                    model.addAttribute("baggageWeightInLb", calculateWeightService.calculateBaggageWeight(flightShipment, "lb"));
                    model.addAttribute("totalWeightInKg", calculateWeightService.calculateTotalWeight(flightShipment, "kg"));
                    model.addAttribute("totalWeightInLb", calculateWeightService.calculateTotalWeight(flightShipment, "lb"));
                } else {
                    return "error-page";
                }
            }
        } else {
            return "error-page";
        }


        return "exercise-1-result";
    }


}
