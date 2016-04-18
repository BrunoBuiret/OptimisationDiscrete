package utils;

import models.Agency;
import models.TrainingCenter;

/**
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 * @author Thomas Arnaud (thomas.arnaud@etu.univ-lyon1.fr)
 * @author Alexis Rabilloud (alexis.rabilloud@etu.univ-lyon1.fr)
 */
public abstract class DistanceUtils
{
    /**
     * The Earth's radius in meters.
     */
    protected static final double EARTH_RADIUS = 6371000.0;
    
    /**
     * Computes the distance, in meters, between two points A and B using latitude and longitude.
     * 
     * @param latitudeA A's latitude.
     * @param longitudeA A's longitude.
     * @param latitudeB B's latitude.
     * @param longitudeB B's longitude.
     * @return The computed distance, in meters.
     */
    public static double computeDistance(double latitudeA, double longitudeA, double latitudeB, double longitudeB)
    {
        return DistanceUtils.EARTH_RADIUS * Math.acos(
            Math.sin(Math.toRadians(latitudeA)) * Math.sin(Math.toRadians(latitudeB)) +
            Math.cos(Math.toRadians(latitudeA)) * Math.cos(Math.toRadians(latitudeB)) *
            Math.cos(Math.toRadians(longitudeA) - Math.toRadians(longitudeB))
        );
    }
    
    /**
     * Computes the distance, in meters, between an agency and a training center.
     * 
     * @param agency The agency to use.
     * @param trainingCenter The training center to use.
     * @return The computed distance, in meters.
     */
    public static double computeDistance(Agency agency, TrainingCenter trainingCenter)
    {
        return DistanceUtils.computeDistance(
            agency.getLatitude(),
            agency.getLongitude(),
            trainingCenter.getLatitude(),
            trainingCenter.getLongitude()
        );
    }
}
