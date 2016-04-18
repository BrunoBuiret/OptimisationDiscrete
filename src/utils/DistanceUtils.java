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
    
    /**
     * 
     * @param latitude
     * @param longitude
     * @param width
     * @param height
     * @return An array of two items, the first being the abscissa and the second
     * being the ordinate.
     * @see http://stackoverflow.com/questions/14329691/covert-latitude-longitude-point-to-a-pixels-x-y-on-mercator-projection
     */
    public static double[] degreesToPixels(double latitude, double longitude, int width, int height)
    {
        // Check parameters
        if(latitude < -90 || latitude > 90)
        {
            throw new IllegalArgumentException(String.format(
                "La latitude donnée, %f, est invalide.",
                latitude
            ));
        }
        
        if(longitude < -180 || longitude > 180)
        {
            throw new IllegalArgumentException(String.format(
                "La longitude donnée, %f, est invalide.",
                longitude
            ));
        }
        
        if(width <= 0)
        {
            throw new IllegalArgumentException(String.format(
                "La largeur ne peut pas être négative ou nulle."
            ));
        }
        
        if(height <= 0)
        {
            throw new IllegalArgumentException(String.format(
                "La hauteur ne peut pas être négative ou nulle."
            ));
        }
            
        // Transform latitude and longitude to abscissa and ordinate
        double[] pixels = new double[2];
        
        pixels[0] = (longitude + 180) * width / 360;
        pixels[1] = (height / 2) - (
            width * Math.log(
                Math.tan(
                    (Math.PI / 4) + (latitude * Math.PI / 360)
                )
            ) / (2 * Math.PI)
        );
        
        return pixels;
    }
}
