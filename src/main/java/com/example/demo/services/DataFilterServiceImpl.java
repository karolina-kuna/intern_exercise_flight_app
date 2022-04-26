package com.example.demo.services;

import com.example.demo.model.Flight;
import com.example.demo.model.FlightShipment;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DataFilterServiceImpl implements DataFilterService {


    public Flight findFlight(List<Flight> flights, int flightNumber, String date) {

        return flights.stream()
                .filter(flight -> flight.getFlightNumber() == flightNumber && matchDate(date, flight))
                .findAny().orElse(null);
    }

    @Override
    public List<Flight> getFlightByIATACodeAndDate(List<Flight> flights, String IATACode, String date) {
        List<Flight> filteredFlights;
        filteredFlights = flights.stream()
                .filter(flight -> flight.getArrivalAirportIATACode().equals(IATACode) || flight.getDepartureAirportIATACode().equals(IATACode))
                .filter(flight -> matchDate(date,flight))
                .collect(Collectors.toList());
        return filteredFlights;
    }

    public FlightShipment findFlightShipment(int flightNumber, String date, List<Flight> flights, List<FlightShipment> flightShipments) {
        Flight flightElement = findFlight(flights, flightNumber, date);

        return flightShipments.stream()
                .filter(cargo -> cargo.getFlightId() == flightElement.getFlightId()).findAny().orElse(null);
    }

    public boolean matchDate(String flightDate, Flight flight) {

        String date = flight.getDepartureDate();
        int indexOfT = date.indexOf("T");
        date = date.substring(0, indexOfT);
        return date.equals(flightDate);
    }


    public List<Flight> getArrivals(String IATA, List<Flight> flights) {

        return flights.stream()
                .filter(flight -> flight.getArrivalAirportIATACode().equals(IATA))
                .collect(Collectors.toList());

    }

    public List<Flight> getDepartures(String IATA, List<Flight> flights) {

        List<Flight> filteredFlights;

        filteredFlights = flights.stream()
                .filter(flight -> flight.getDepartureAirportIATACode().equals(IATA))
                .collect(Collectors.toList());


        return filteredFlights;

    }




    public List<FlightShipment> getAirportShipment(List<Flight> flights, List<FlightShipment> shipments) {

        Set<Integer> collect = flights.stream()
                .map(Flight::getFlightId).collect(Collectors.toSet());

        return shipments.stream()
                .filter(shipment -> collect.contains(shipment.getFlightId()))
                .collect(Collectors.toList());

    }


}




