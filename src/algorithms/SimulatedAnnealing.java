/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import model.Agency;
import model.TrainingCenter;

/**
 *
 * @author bruno
 */
public class SimulatedAnnealing extends AbstractAlgorithm {
    protected Map<Agency, TrainingCenter> bestSolution;
    
    protected double bestPrice;
    
    public SimulatedAnnealing(double trainersFee, double trainingCenterFee, double pricePerKilometer, String agenciesFilePath, String trainingCentersFilePath) {
        super(trainersFee, trainingCenterFee, pricePerKilometer, agenciesFilePath, trainingCentersFilePath);
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
        
        // Iterate to get the next best solution
        // @todo Do it.
    }
}
