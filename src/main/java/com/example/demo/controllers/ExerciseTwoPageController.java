package com.example.demo.controllers;

import com.example.demo.model.Exercise2FormData;
import com.example.demo.model.Flight;
import com.example.demo.model.FlightShipment;
import com.example.demo.services.CalculateAmountService;
import com.example.demo.services.DataFilterService;
import com.example.demo.services.DataParsingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.util.List;

@Controller
public class ExerciseTwoPageController {


    DataParsingService dataParsingService;
    DataFilterService dataFilterService;
    CalculateAmountService calculateAmountService;

    File flightDataFile = new File("src/main/resources/flight_data.json");
    File cargoDataFile = new File("src/main/resources/cargo_data.json");

    public ExerciseTwoPageController(DataParsingService dataParsingService, DataFilterService dataFilterService,
    CalculateAmountService calculateAmountService)
     {
        this.dataParsingService = dataParsingService;
        this.dataFilterService = dataFilterService;
        this.calculateAmountService = calculateAmountService;
    }

    @GetMapping("/showSecondExercise")
    public String showSecondExercise(Model model) {

        model.addAttribute("exercise2FormData", new Exercise2FormData());
        return "exercise-2";
    }


    @PostMapping("/processForm2")
    public String processForm2(@ModelAttribute @Valid Exercise2FormData exercise2FormData, BindingResult bindingResult, Model model) {

        List<FlightShipment> shipmentDeparting;
        List<FlightShipment> shipmentArriving;

        if (bindingResult.hasErrors()) {
            return "exercise-2";
        }

        List<Flight> flights = dataParsingService.parseFlightData(flightDataFile);
        List<FlightShipment> flightsShipment = dataParsingService.parseFlightShipmentData(cargoDataFile);
        if(flights != null && flightsShipment != null) {
            List<Flight> flightsByAirportAndDate = dataFilterService.getFlightByIATACodeAndDate(flights,exercise2FormData.getIATACode().toUpperCase(),exercise2FormData.getDate());
            if(flightsByAirportAndDate == null || flightsByAirportAndDate.isEmpty()) {
                model.addAttribute("IATACode", "No data");
                return "exercise-2";
            }else{

                model.addAttribute("IATACode", exercise2FormData.getIATACode().toUpperCase());
                model.addAttribute("date", exercise2FormData.getDate());
                List<Flight> departures = dataFilterService.getDepartures(exercise2FormData.getIATACode().toUpperCase(), flightsByAirportAndDate);
                List<Flight> arrivals = dataFilterService.getArrivals(exercise2FormData.getIATACode().toUpperCase(), flightsByAirportAndDate);
                if (departures != null) {
                    model.addAttribute("numberOfPlainsDeparting", departures.size());
                    shipmentDeparting = dataFilterService.getAirportShipment(departures, flightsShipment);
                    model.addAttribute("amountOfBaggageDeparting", calculateAmountService.getNumberOfBaggage(shipmentDeparting));

                }

                if(arrivals != null){
                    model.addAttribute("numberOfPlainsArriving", arrivals.size());
                    shipmentArriving = dataFilterService.getAirportShipment(arrivals, flightsShipment);
                    model.addAttribute("amountOfBaggageArriving", calculateAmountService.getNumberOfBaggage(shipmentArriving));

                }

                return "exercise-2-result";
            }

        }else{
            return "error-page";
        }
    }

}
