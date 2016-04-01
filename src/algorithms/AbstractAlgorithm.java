package algorithms;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Agency;
import model.TrainingCenter;
import utils.DistanceUtils;
import utils.ReadCSV;

/**
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 * @author Thomas Arnaud (thomas.arnaud@etu.univ-lyon1.fr)
 * @author Alexis Rabilloud (alexis.rabilloud@etu.univ-lyon1.fr)
 */
public abstract class AbstractAlgorithm {
    /**
     * The maximum number of employees per training center.
     */
    protected int employeesPerTrainingCenter;
    
    /**
     * A training center's trainers' fee.
     */
    protected double trainersFee;
    
    /**
     * A training center's additional fee.
     */
    protected double trainingCenterFee;
    
    /**
     * The cost of a kilometer by car.
     */
    protected double pricePerKilometer;
    
    /**
     * A list of every agencies with employees to train.
     */
    protected List<Agency> agencies;
    
    /**
     * A list of every available training centers across France.
     */
    protected List<TrainingCenter> trainingCenters;
    
    /**
     * A map linking each agency to every training centers with the distance between them.
     */
    protected Map<Agency, Map<TrainingCenter, Double>> distances;
    
    /**
     * 
     * @param employeesPerTrainingCenter The maximum number of employees per training center.
     * @param trainersFee A training center's trainers' fee.
     * @param trainingCenterFee  A training center's additional fee.
     * @param pricePerKilometer The cost of a kilometer by car.
     * @param agenciesFilePath The path to the CSV file containing the agencies.
     * @param trainingCentersFilePath The path to the CSV file containing the training centers;
     */
    public AbstractAlgorithm(int employeesPerTrainingCenter, double trainersFee, double trainingCenterFee, double pricePerKilometer, String agenciesFilePath, String trainingCentersFilePath)
    {
        // Initialize properties
        this.employeesPerTrainingCenter = employeesPerTrainingCenter;
        this.trainersFee = trainersFee;
        this.trainingCenterFee = trainingCenterFee;
        this.pricePerKilometer = pricePerKilometer;
        
        // Test the existence of the agencies' file
        File agenciesFile = new File(agenciesFilePath);
        
        if(!agenciesFile.exists())
        {
            throw new IllegalArgumentException(String.format(
                "The agencies file \"%s\" doesn't exist.",
                agenciesFilePath
            ));
        }
        
        if(!agenciesFile.isFile())
        {
            throw new IllegalArgumentException(String.format(
                "\"%s\" isn't a valid file.",
                agenciesFilePath
            ));
        }
        
        if(!agenciesFile.canRead())
        {
            throw new IllegalArgumentException(String.format(
                "File \"%s\" can't be read.",
                agenciesFilePath
            ));
        }
        
        // Load agencies
        this.agencies = new ArrayList<>();
        List<String[]> agenciesList = ReadCSV.readFile(agenciesFile, Boolean.FALSE);
        
        for(String[] agencyLine : agenciesList)
        {
            try
            {
                this.agencies.add(new Agency(
                    agencyLine[0],
                    agencyLine[1],
                    Integer.parseInt(agencyLine[2]),
                    Double.parseDouble(agencyLine[4]),
                    Double.parseDouble(agencyLine[3]),
                    Integer.parseInt(agencyLine[5])
                ));
            }
            catch(NumberFormatException ex)
            {
                Logger.getLogger(AbstractAlgorithm.class.getName()).log(
                    Level.SEVERE,
                    "Couldn't parse a string."
                );
            }
        }
        
        // Test existence of the training centers' file
        File trainingCentersFile = new File(trainingCentersFilePath);
        
        if(!trainingCentersFile.exists())
        {
            throw new IllegalArgumentException(String.format(
                "The training centers file \"%s\" doesn't exist.",
                trainingCentersFilePath
            ));
        }
        
        if(!trainingCentersFile.isFile())
        {
            throw new IllegalArgumentException(String.format(
                "\"%s\" isn't a valid file.",
                trainingCentersFilePath
            ));
        }
        
        if(!trainingCentersFile.canRead())
        {
            throw new IllegalArgumentException(String.format(
                "File \"%s\" can't be read.",
                trainingCentersFilePath
            ));
        }
        
        // @todo Load training centers
        this.trainingCenters = new ArrayList<>();
        List<String[]> trainingCentersList = ReadCSV.readFile(trainingCentersFile, Boolean.FALSE);
        
        for(String[] trainingCenterLine : trainingCentersList)
        {
            try
            {
                this.trainingCenters.add(new TrainingCenter(
                    trainingCenterLine[0],
                    trainingCenterLine[1],
                    Integer.parseInt(trainingCenterLine[2]),
                    Double.parseDouble(trainingCenterLine[4]),
                    Double.parseDouble(trainingCenterLine[3])
                ));
            }
            catch(NumberFormatException ex)
            {
                Logger.getLogger(AbstractAlgorithm.class.getName()).log(
                    Level.SEVERE,
                    "Couldn't parse a string."
                );
            }
        }
        
        // Compute the distance between each agency and every training center
        for(Agency agency : this.agencies) {
            Map<TrainingCenter, Double> centers = new HashMap();
            for(TrainingCenter trainingCenter : this.trainingCenters) {
                centers.put(trainingCenter, DistanceUtils.calculateDistance(agency, trainingCenter));
            }
            this.distances.put(agency, centers);
        }
    }
    
    /**
     * Gets the distance between an agency and a training center.
     * 
     * @param agency The agency.
     * @param trainingCenter The training center.
     * @return The distance between the agency and the training center.
     */
    protected double getDistance(Agency agency, TrainingCenter trainingCenter)
    {
        if(this.distances.containsKey(agency) && this.distances.get(agency).containsKey(trainingCenter)) {
            return this.distances.get(agency).get(trainingCenter);
        }
        
        return -1;
    }
    
    /**
     * Computes the price for a solution.
     * 
     * @param solution The solution to get the price from.
     * @return The price.
     */
    protected double getPrice(Map<Agency, TrainingCenter> solution)
    {
        double kilometersPrice = 0.;
        Set<TrainingCenter> usedTrainingCenters = new HashSet<>();
        
        for(Map.Entry<Agency, TrainingCenter> entry : solution.entrySet())
        {
            kilometersPrice += entry.getKey().getEmployeesNumber() * this.getDistance(entry.getKey(), entry.getValue());
            usedTrainingCenters.add(entry.getValue());
        }
        
        return usedTrainingCenters.size() * (this.trainersFee + this.trainingCenterFee) + kilometersPrice;
    }
}
