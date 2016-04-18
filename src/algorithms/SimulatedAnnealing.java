package algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import models.Agency;
import models.TrainingCenter;

/**
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 * @author Thomas Arnaud (thomas.arnaud@etu.univ-lyon1.fr)
 * @author Alexis Rabilloud (alexis.rabilloud@etu.univ-lyon1.fr)
 */
public class SimulatedAnnealing extends AbstractAlgorithm
{
    /**
     * The number of iterations the algorithm will do.
     */
    protected int iterationsNumber;

    /**
     * The number of choices between neighbors the algorithm will make each step.
     */
    protected int neighborsChoicesNumber;

    /**
     * The temperature.
     */
    protected double temperature;
    
    /**
     * The modifier used to update the temperature at the end of each iteration.
     */
    protected double temperatureModifier;

    /**
     * Creates a new simulated annealing algorithm.
     * 
     * @param trainingCentersCapacity A training center's capacity.
     * @param trainersFee The fee for trainers.
     * @param trainingCentersFee The fee for training centers.
     * @param pricePerKilometer The price for a kilometer.
     * @param agencies A list of every agency needing to train its employees.
     * @param trainingCenters A list of every available training centers across France.
     * @param iterationsNumber The number of iterations the algorithm will do.
     * @param neighborsChoicesNumber The number of items a neighbor will be chosen for
     * a specific temperature.
     * @param initialTemperature The starting temperature.
     * @param temperatureModifier The temperature's modifier.
     */
    public SimulatedAnnealing(
        int trainingCentersCapacity, double trainersFee, double trainingCentersFee,
        double pricePerKilometer, List<Agency> agencies, List<TrainingCenter> trainingCenters,
        int iterationsNumber, int neighborsChoicesNumber, double initialTemperature,
        double temperatureModifier
    )
    {
        // Call super constructor
        super(trainingCentersCapacity, trainersFee, trainingCentersFee, pricePerKilometer, agencies, trainingCenters);

        // Initialize properties
        this.iterationsNumber = iterationsNumber;
        this.neighborsChoicesNumber = neighborsChoicesNumber;
        this.temperature = initialTemperature;
        this.temperatureModifier = temperatureModifier;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run()
    {
        // Initialize vars
        Map<Agency, TrainingCenter> lastSolution, currentSolution, bestSolution;
        double lastPrice, currentPrice, bestPrice;
        Random randomizer = new Random();

        // Create the initial solution by linking every agency to the closest
        // training center
        lastSolution = bestSolution = new HashMap<>();

        for(Agency agency : this.agencies)
        {
            Map<TrainingCenter, Double> trainingCentersList = this.distances.get(agency);
            TrainingCenter closestTrainingCenter = null;
            double minimumDistance = Double.MAX_VALUE;

            for(Map.Entry<TrainingCenter, Double> entry : trainingCentersList.entrySet())
            {
                if(entry.getValue() < minimumDistance && (entry.getKey().getCapacity() - entry.getKey().getTraineesNumber()) >= agency.getEmployeesToTrainNumber())
                {
                    closestTrainingCenter = entry.getKey();
                    minimumDistance = entry.getValue();
                }
            }

            if(closestTrainingCenter != null)
            {
               lastSolution.put(agency, closestTrainingCenter);
            }
            else
            {
                throw new RuntimeException(
                    "Impossible de trouver un centre de formation pour l'agence \"%s\"."
                );
            }
        }

        lastPrice = bestPrice = this.getPrice(lastSolution);
        
        // Notify the observers so that the first solution can be known
        for(AlgorithmListenerInterface observer : this.observers)
        {
            observer.onStepEnd(lastSolution, lastPrice);
        }

        // Main algorithm loop
        for(int k = 0; k < this.iterationsNumber; k++)
        {
            for(int l = 0; l < this.neighborsChoicesNumber; l++)
            {
                currentSolution = new HashMap<>(lastSolution);

                // Get a neighbor solution by randomly linking an agency to another training center
                Agency agency = this.agencies.get(randomizer.nextInt(this.agencies.size()));
                TrainingCenter lastTrainingCenter = lastSolution.get(agency), currentTrainingCenter = null;
                
                do
                {
                    currentTrainingCenter = this.trainingCenters.get(randomizer.nextInt(this.trainingCenters.size()));
                }
                while(lastTrainingCenter.equals(currentTrainingCenter));
                
                currentSolution.put(agency, currentTrainingCenter);
                currentPrice = this.getPrice(currentSolution);
                
                // Compute prices difference
                double deltaPrice = currentPrice - lastPrice;
                
                if(deltaPrice <= 0)
                {
                    lastSolution = currentSolution;
                    lastPrice = currentPrice;
                    
                    if(currentPrice < bestPrice)
                    {
                        bestSolution = currentSolution;
                        bestPrice = currentPrice;
                    }
                }
                else
                {
                    double p = randomizer.nextDouble();
                    
                    if(p <= Math.exp(-deltaPrice / this.temperature))
                    {
                        lastSolution = currentSolution;
                        lastPrice = currentPrice;
                    }
                }
                
                // Notify the observers so that the current solution can be known
                for(AlgorithmListenerInterface observer : this.observers)
                {
                    observer.onStepEnd(lastSolution, lastPrice);
                }
            }

            // Temperature should be updated there
            this.temperature *= this.temperatureModifier;
        }

        // Notity the observers so that the best solution can be known
        for(AlgorithmListenerInterface observer : this.observers)
        {
            observer.onEnd(bestSolution, bestPrice);
        }
    }
}
