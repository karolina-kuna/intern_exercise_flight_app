package com.example.demo.services;

import com.example.demo.model.Baggage;
import com.example.demo.model.Cargo;
import com.example.demo.model.FlightShipment;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class CalculateWeightServiceImpl implements CalculateWeightService {


    double baggageWeight = 0;
    double cargoWeight = 0;

    public double roundUpWeight(Double value){
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public double convertKgToLbs(double kg){
        return  kg * 2.20462262;
    }

    public double convertLbsToKgs(double lbs){
        return lbs * 0.454;
    }


    public double calculateCargoWeight(FlightShipment flightShipment, String metric){

        double cargoWeightKgs = 0;
        double cargoWeightLbs = 0;

        List<Cargo> cargos = flightShipment.getCargo();

        for(Cargo cargo : cargos){

           if(cargo.getWeightUnit().equals("kg")){
               cargoWeightKgs += cargo.getWeight();
           }else{
               cargoWeightLbs += cargo.getWeight();
           }
        }

        if(metric.equals("kg")){
            this.cargoWeight = roundUpWeight(cargoWeightKgs + convertLbsToKgs(cargoWeightLbs));

        }else{
            this.cargoWeight = roundUpWeight(cargoWeightLbs + convertKgToLbs(cargoWeightKgs));
        }


        return cargoWeight;

    }


    public double calculateBaggageWeight(FlightShipment flightShipment, String metric){

        int baggageWeightKgs = 0;
        int baggageWeightLbs = 0;
        List<Baggage> baggageList = flightShipment.getBaggage();
        for(Baggage baggage : baggageList){

            if(baggage.getWeightUnit().equals("kg")){
                baggageWeightKgs += baggage.getWeight();
            }else{
                baggageWeightLbs += baggage.getWeight();
            }
        }

        if(metric.equals("kg")){
            this.baggageWeight = roundUpWeight(baggageWeightKgs + convertLbsToKgs(baggageWeightLbs));

        }else{
            this.baggageWeight = roundUpWeight(baggageWeightLbs + convertKgToLbs(baggageWeightKgs));
        }


        return baggageWeight;
    }


    public double calculateTotalWeight(FlightShipment flightShipment, String metric){

    if(metric.equals("kg")){
        return roundUpWeight(calculateBaggageWeight(flightShipment,"kg") + calculateCargoWeight(flightShipment,"kg"));
    }else{
        return roundUpWeight(calculateBaggageWeight(flightShipment,"lb") + calculateCargoWeight(flightShipment,"lb"));

    }


    }

}
