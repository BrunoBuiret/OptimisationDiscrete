/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import static java.lang.Math.*;
import model.Agency;
import model.TrainingCenter;

/**
 *
 * @author Alexdef74307
 */
public class DistanceUtils {
    
    private static final Double earthRadius = 6371000.0;
    
    public static Double calculateDistanceLatLong(Double longitudeAgency, Double latitudeAgency, 
            Double longitudeTC, Double latitudeTC) {
        
        Double d = earthRadius * acos(sin(toRadians(latitudeAgency)) * sin(toRadians(latitudeTC)) + cos(toRadians(latitudeAgency)) * 
                cos(toRadians(latitudeTC)) * cos(toRadians(longitudeAgency) - toRadians(longitudeTC)));
        
         // $query .= '6371000 * ACOS(SIN(RADIANS(:userLatitude)) * SIN(RADIANS(lat)) + COS(RADIANS(:userLatitude)) * COS(RADIANS(lat)) * COS(RADIANS(:userLongitude) - RADIANS(lng))) AS distance ';
        
        return d;
    }
    
    public static Double calculateDistance(Agency agency, TrainingCenter trainingCenter) {
       return calculateDistanceLatLong(agency.getLongitude(), agency.getLatitude(), trainingCenter.getLongitude(), trainingCenter.getLatitude()); 
    }
}
