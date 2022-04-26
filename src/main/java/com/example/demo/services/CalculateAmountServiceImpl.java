package com.example.demo.services;

import com.example.demo.model.Baggage;
import com.example.demo.model.FlightShipment;

import java.util.List;

public class CalculateAmountServiceImpl implements CalculateAmountService {


    public CalculateAmountServiceImpl() {
    }

    public int getNumberOfBaggage(List<FlightShipment> flightShipment){

        int amount;
        amount = flightShipment.stream()
                .flatMap(flightShipmentObject -> flightShipmentObject.getBaggage().stream())
                .mapToInt(Baggage::getPieces).sum();

        return amount;
    }

}
