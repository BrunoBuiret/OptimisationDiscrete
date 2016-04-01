/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import java.io.File;
import java.util.List;
import model.Agency;
import model.TrainingCenter;

/**
 *
 * @author bruno
 */
public abstract class AbstractAlgorithm {
    protected double trainersFee;
    
    protected double trainingCenterFee;
    
    protected double pricePerKilometer;
    
    protected List<Agency> agencies;
    
    protected List<TrainingCenter> trainingCenters;
    
    public AbstractAlgorithm(double trainersFee, double trainingCenterFee, double pricePerKilometer, String agenciesFilePath, String trainingCentersFilePath)
    {
        this.trainersFee = trainersFee;
        this.trainingCenterFee = trainingCenterFee;
        this.pricePerKilometer = pricePerKilometer;
        
        // Load agencies
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
        
        // @todo Load agencies
        
        // Load training centers
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
    }
}
