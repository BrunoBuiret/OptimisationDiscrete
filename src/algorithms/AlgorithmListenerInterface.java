package algorithms;

import java.util.Map;
import models.Agency;
import models.TrainingCenter;

/**
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 * @author Thomas Arnaud (thomas.arnaud@etu.univ-lyon1.fr)
 * @author Alexis Rabilloud (alexis.rabilloud@etu.univ-lyon1.fr)
 */
public interface AlgorithmListenerInterface
{
    /**
     * Called at the end of each step of the algorithm.
     * 
     * @param solution The solution found at that step.
     * @param price The price for that solution.
     */
    public void onStepEnd(Map<Agency, TrainingCenter> solution, double price);
    
    /**
     * Called at the of the algorithm.
     * 
     * @param bestSolution The best solution that was found.
     * @param bestPrice The price for that solution.
     */
    public void onEnd(Map<Agency, TrainingCenter> bestSolution, double bestPrice);
}
