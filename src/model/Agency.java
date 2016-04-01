package model;

/**
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 * @author Thomas Arnaud (thomas.arnaud@etu.univ-lyon1.fr)
 * @author Alexis Rabilloud (alexis.rabilloud@etu.univ-lyon1.fr)
 */
public class Agency {
    /**
     * The agency's id.
     */
    protected String id;
    
    /**
     * The agency's name.
     */
    protected String name;
    
    /**
     * The agency's zip code.
     */
    protected int zipCode;
    
    /**
     * The agency's latitude.
     */
    protected double latitude;
    
    /**
     * The agency's longitude.
     */
    protected double longitude;
    
    /**
     * The agency's number of employees.
     */
    protected int employeesNumber;
    
    /**
     * Creates a new agency.
     * 
     * @param id The agency's id.
     * @param name The agency's name.
     * @param zipCode The agency's zip code.
     * @param latitude The agency's latitude.
     * @param longitude The agency's longitude.
     * @param employeesNumber The agency's number of employees.
     */
    public Agency(String id, String name, int zipCode, double latitude, double longitude, int employeesNumber)
    {
        this.id = id;
        this.name = name;
        this.zipCode = zipCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.employeesNumber = employeesNumber;
    }

    /**
     * Gets the agency's id.
     * 
     * @return The agency's id.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the agency's id.
     * 
     * @param id The agency's id.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the agency's name.
     * 
     * @return The agency's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the agency's name.
     * 
     * @param name The agency's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the agency's zip code.
     * 
     * @return The agency's zip code.
     */
    public int getZipCode() {
        return zipCode;
    }

    /**
     * Sets the agency's zip code.
     * 
     * @param zipCode The agency's zip code.
     */
    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Gets the agency's latitude.
     * 
     * @return The agency's latitude.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Sets the agency's latitude.
     * 
     * @param latitude The agency's latitude.
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Gets the agency's longitude.
     * 
     * @return The agenct's longitude.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Sets the agenct's longitude.
     * 
     * @param longitude The agenct's longitude.
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Gets the agency's number of employees.
     * 
     * @return The agency's number of employees.
     */
    public int getEmployeesNumber() {
        return employeesNumber;
    }

    /**
     * Sets the agency's number of employees.
     * 
     * @param employeesNumber The agency's number of employees.
     */
    public void setEmployeesNumber(int employeesNumber) {
        this.employeesNumber = employeesNumber;
    }
}
