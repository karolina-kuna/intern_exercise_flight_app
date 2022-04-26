package com.example.demo.services;

import com.example.demo.model.Flight;
import com.example.demo.model.FlightShipment;

import java.util.List;

public interface DataFilterService {

    List<Flight> getArrivals(String IATA, List<Flight> flights);

    List<Flight> getDepartures(String IATA, List<Flight> flights);

    FlightShipment findFlightShipment(int flightNumber, String date, List<Flight> flights, List<FlightShipment> flightShipments);

    List<FlightShipment> getAirportShipment(List<Flight> flights, List<FlightShipment> shipments);

    Flight findFlight(List<Flight> flights, int flightNumber, String date);

    List<Flight> getFlightByIATACodeAndDate(List<Flight> flights, String IATACode, String date);

}
