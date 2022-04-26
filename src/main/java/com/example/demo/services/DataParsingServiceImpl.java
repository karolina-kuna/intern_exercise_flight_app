package com.example.demo.services;

import com.example.demo.model.Flight;
import com.example.demo.model.FlightShipment;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DataParsingServiceImpl implements DataParsingService{

    ObjectMapper objectMapper;

    public DataParsingServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<Flight> parseFlightData(File file)  {

        List<Flight> flightData = null;
        try {
            flightData = objectMapper.readerForListOf(Flight.class).readValue(file);
        }catch (IOException e){
            e.printStackTrace();
        }
        return flightData;

    }

    public List<FlightShipment> parseFlightShipmentData(File file)  {

        List<FlightShipment> flightShipmentData = null;
        try {
            flightShipmentData = objectMapper.readerForListOf(FlightShipment.class).readValue(file);

        }catch(IOException e){
            e.printStackTrace();
        }
        return flightShipmentData;

    }
    
}
