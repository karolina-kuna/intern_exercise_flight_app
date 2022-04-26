package com.example.demo.services;

import com.example.demo.model.FlightShipment;

public interface CalculateWeightService {

    double convertKgToLbs(double kg);

    double convertLbsToKgs(double lbs);

    double calculateCargoWeight(FlightShipment flightShipment, String metric);

    double calculateBaggageWeight(FlightShipment flightShipment, String metric);

    double calculateTotalWeight(FlightShipment flightShipment, String metric);

    double roundUpWeight(Double value);

}
