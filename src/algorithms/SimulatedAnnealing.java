package algorithms;

import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import model.Agency;
import model.TrainingCenter;

/**
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 * @author Thomas Arnaud (thomas.arnaud@etu.univ-lyon1.fr)
 * @author Alexis Rabilloud (alexis.rabilloud@etu.univ-lyon1.fr)
 */
public class SimulatedAnnealing extends AbstractAlgorithm {
    protected Map<Agency, TrainingCenter> bestSolution;
    
    protected double bestPrice;
    
    public SimulatedAnnealing(int employeesPerTrainingCenter, double trainersFee, double trainingCenterFee, double pricePerKilometer, String agenciesFilePath, String trainingCentersFilePath) {
        super(employeesPerTrainingCenter, trainersFee, trainingCenterFee, pricePerKilometer, agenciesFilePath, trainingCentersFilePath);
    }

    public void run(int iterationsNumber) {
        // Initialize vars
        double temperature = 10;
        double temperatureDiminisher = 0.8;
        Map<Agency, TrainingCenter> currentSolution = new HashMap<>();
        double currentPrice = 0.;
        Map<Agency, TrainingCenter> nextSolution = new HashMap<>();
        double nextPrice = 0.;
        Random random = new Random();
        
        this.bestSolution = currentSolution;
        this.bestPrice = currentPrice;
        
        // Find a solution by linking every agency to the closest training center
        // with enough available seats;
        // @todo Do it.
        
        for (Agency a : this.agencies) {
            boolean placed = false;
            Map<TrainingCenter,Double> temp = new HashMap<>(this.distances.get(a));
            Double distMin = Double.MAX_VALUE;
            TrainingCenter closestTC = null;
            do {
                for (TrainingCenter tc : temp.keySet()) {
                    if (temp.get(tc) < distMin) {
                        distMin = temp.get(tc);
                        closestTC = tc;
                    }
                }
                if ((closestTC.getCapacity() - closestTC.getOccupiedPlaces()) >= a.getEmployeesNumber()) {
                    currentSolution.put(a, closestTC);
                }
                else {
                    temp.remove(closestTC);
                }
            } while (!placed);
        }
        
        // Iterate to get the next best solution
        // @todo Do it.
    }
}
