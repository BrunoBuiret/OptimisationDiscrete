package algorithms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import models.Agency;
import models.TrainingCenter;
import utils.DistanceUtils;

/**
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 * @author Thomas Arnaud (thomas.arnaud@etu.univ-lyon1.fr)
 * @author Alexis Rabilloud (alexis.rabilloud@etu.univ-lyon1.fr)
 */
public abstract class AbstractAlgorithm
{
    /**
     * A training center's capacity.
     */
    protected int trainingCentersCapacity;
    
    /**
     * The fee for trainers.
     */
    protected double trainersFee;
    
    /**
     * The fee for training centers.
     */
    protected double trainingCentersFee;
    
    /**
     * The price for a kilometer.
     */
    protected double pricePerKilometer;
    
    /**
     * A list of every agency needing to train its employees.
     */
    protected List<Agency> agencies;
    
    /**
     * A list of every available training centers across France.
     */
    protected List<TrainingCenter> trainingCenters;
    
    /**
     * The distances between each agency and every training centers.
     */
    protected Map<Agency, Map<TrainingCenter, Double>> distances;
    
    /**
     * A list of observers.
     */
    protected Set<AlgorithmListenerInterface> observers;
    
    /**
     * Creates a new abstract algorithm for this kind of problem.
     * 
     * @param trainingCentersCapacity A training center's capacity.
     * @param trainersFee The fee for trainers.
     * @param trainingCentersFee The fee for training centers.
     * @param pricePerKilometer The price for a kilometer.
     * @param agencies A list of every agency needing to train its employees.
     * @param trainingCenters A list of every available training centers across France.
     */
    public AbstractAlgorithm(
        int trainingCentersCapacity, double trainersFee, double trainingCentersFee,
        double pricePerKilometer, List<Agency> agencies, List<TrainingCenter> trainingCenters
    )
    {
        // Initialize properties
        this.trainingCentersCapacity = trainingCentersCapacity;
        this.trainersFee = trainersFee;
        this.trainingCentersFee = trainingCentersFee;
        this.pricePerKilometer = pricePerKilometer;
        this.agencies = agencies;
        this.trainingCenters = trainingCenters;
        this.observers = new HashSet<>();
        
        // Compute distances
        this.distances = new HashMap<>();
        
        for(Agency agency : this.agencies)
        {
            Map<TrainingCenter, Double> currentDistances = new HashMap<>();
            
            for(TrainingCenter trainingCenter : this.trainingCenters)
            {
                currentDistances.put(trainingCenter, DistanceUtils.computeDistance(agency, trainingCenter));
            }
            
            this.distances.put(agency, currentDistances);
        }
    }
    
    /**
     * Runs the algorithm.
     */
    public abstract void run();
    
    /**
     * Adds an observer if it isn't already observing.
     * 
     * @param observer The observer to add.
     */
    public void addObserver(AlgorithmListenerInterface observer)
    {
        this.observers.add(observer);
    }
    
    /**
     * Removes an observer if it has been observing.
     * 
     * @param observer The observer to remove.
     */
    public void removeObserver(AlgorithmListenerInterface observer)
    {
        this.observers.remove(observer);
    }
    
    /**
     * Gets the distance between an agency and a training center.
     * 
     * @param agency The agency to use.
     * @param trainingCenter The training center to use.
     * @return The distance between the agency and the training center.
     */
    public double getDistance(Agency agency, TrainingCenter trainingCenter)
    {
        if(this.distances.containsKey(agency) && this.distances.get(agency).containsKey(trainingCenter)) {
            return this.distances.get(agency).get(trainingCenter);
        }
        
        throw new IllegalArgumentException(String.format(
            "Distance between agency \"%s\" and training center \"%s\" hasn't been computed.",
            agency.getName(),
            trainingCenter.getName()
        ));
    }
    
    /**
     * Computes the price for a solution.
     * 
     * @param solution The solution to compute the price from.
     * @return The computed price.
     */
    public double getPrice(Map<Agency, TrainingCenter> solution)
    {
        double kilometersPrice = 0.;
        Set<TrainingCenter> usedTrainingCenters = new HashSet<>();
        
        for(Map.Entry<Agency, TrainingCenter> entry : solution.entrySet())
        {
            kilometersPrice += entry.getKey().getEmployeesToTrainNumber() * this.getDistance(entry.getKey(), entry.getValue()) / 1000;
            usedTrainingCenters.add(entry.getValue());
            
            /*
            System.out.println(String.format(
                "%s (%f ; %f ;; %d) -> %s (%f ; %f) = %f € (%f km)",
                entry.getKey().getName(),
                entry.getKey().getLatitude(),
                entry.getKey().getLongitude(),
                entry.getKey().getEmployeesToTrainNumber(),
                entry.getValue().getName(),
                entry.getValue().getLatitude(),
                entry.getValue().getLongitude(),
                entry.getKey().getEmployeesToTrainNumber() * this.getDistance(entry.getKey(), entry.getValue()) / 1000 * this.pricePerKilometer,
                this.getDistance(entry.getKey(), entry.getValue()) / 1000
            ));
            */
        }
        
        return usedTrainingCenters.size() * (this.trainersFee + this.trainingCentersFee) + kilometersPrice * this.pricePerKilometer;
    }
}
