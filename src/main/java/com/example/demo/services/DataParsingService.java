package com.example.demo.services;

import com.example.demo.model.Flight;
import com.example.demo.model.FlightShipment;

import java.io.File;
import java.util.List;

public interface DataParsingService {

    List<FlightShipment> parseFlightShipmentData(File file);

    List<Flight> parseFlightData(File file);


}
