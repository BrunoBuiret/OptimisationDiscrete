package model;

/**
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 * @author Thomas Arnaud (thomas.arnaud@etu.univ-lyon1.fr)
 * @author Alexis Rabilloud (alexis.rabilloud@etu.univ-lyon1.fr)
 */
public class TrainingCenter {
    /**
     * The training center's id.
     */
    protected String id;
    
    /**
     * The training center's name.
     */
    protected String name;
    
    /**
     * The training center's zip code.
     */
    protected int zipCode;
    
    /**
     *  The training center's latitude.
     */
    protected double latitude;
    
    /**
     * The training center's longitude.
     */
    protected double longitude;
    
    /**
     * The training center's maximum capacity
     */
    protected int maximumCapacity = 60;
    
    /**
     * The training center's already occupied places
     */
    protected int occupiedPlace = 0;
    
    /**
     * Creates a new training center.
     * 
     * @param id The training center's id.
     * @param name The training center's name.
     * @param zipCode The training center's zip code.
     * @param latitude The training center's latitude.
     * @param longitude The training center's longitude.
     */
    public TrainingCenter(String id, String name, int zipCode, double latitude, double longitude)
    {
        this.id = id;
        this.name = name;
        this.zipCode = zipCode;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Gets the training center's id.
     * 
     * @return The training center's id.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the training center's id.
     * 
     * @param id The training center's id.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the training center's name.
     * 
     * @return The training center's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the training center's name.
     * 
     * @param name The training center's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the training center's zip code.
     * 
     * @return The training center's zip code.
     */
    public int getZipCode() {
        return zipCode;
    }

    /**
     * Sets the training center's zip code.
     * 
     * @param zipCode The training center's zip code.
     */
    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Gets the training center's latitude.
     * 
     * @return  The training center's latitude.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Sets the training center's latitude.
     * 
     * @param latitude The training center's latitude.
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Gets the training center's longitude.
     * 
     * @return The training center's longitude.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Sets the training center's longitude.
     * 
     * @param longitude The training center's longitude.
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
