package executable;

import algorithms.SimulatedAnnealing;

/**
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 * @author Thomas Arnaud (thomas.arnaud@etu.univ-lyon1.fr)
 * @author Alexis Rabilloud (alexis.rabilloud@etu.univ-lyon1.fr)
 */
public class Executable {
    public static void main(String[] args) {
        SimulatedAnnealing algorithm = new SimulatedAnnealing(60, 2000, 1000, 0.4, "C:\\Users\\thomas\\Documents\\cours 2015-2016\\Optimisation discrète\\Projet\\OptimisationDiscrete\\resources\\ListeAgences_100.txt", "C:\\Users\\thomas\\Documents\\cours 2015-2016\\Optimisation discrète\\Projet\\OptimisationDiscrete\\resources\\LieuxPossibles.txt");
        algorithm.run(100);
    }
}
