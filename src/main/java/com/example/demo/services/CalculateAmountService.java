package com.example.demo.services;

import com.example.demo.model.FlightShipment;

import java.util.List;

public interface CalculateAmountService {

    int getNumberOfBaggage(List<FlightShipment> flightShipment);
}
