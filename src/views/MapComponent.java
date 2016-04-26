package views;

import algorithms.AlgorithmListenerInterface;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import models.Agency;
import models.TrainingCenter;

/**
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 * @author Thomas Arnaud (thomas.arnaud@etu.univ-lyon1.fr)
 * @author Alexis Rabilloud (alexis.rabilloud@etu.univ-lyon1.fr)
 */
public class MapComponent extends JComponent implements AlgorithmListenerInterface
{
    /**
     * 
     */
    protected List<Agency> agencies;
    
    /**
     * 
     */
    protected List<TrainingCenter> trainingCenters;
    
    /**
     * 
     */
    protected Map<Agency, TrainingCenter> solution;
    
    
    
    protected BufferedImage image;
    
    protected static final double NORTH_BOUNDARY = 51.05;
    
    protected static final double SOUTH_BOUNDARY = 42.4;
    
    protected static final double WEST_BOUNDARY = -4.7;
    
    protected static final double EAST_BOUNDARY = 7.95;
    
    protected static final double LATITUDE_DELTA = MapComponent.NORTH_BOUNDARY - MapComponent.SOUTH_BOUNDARY;
    
    protected static final double LONGITUDE_DELTA = MapComponent.EAST_BOUNDARY - MapComponent.WEST_BOUNDARY;
    
    protected static final int FRANCE_WIDTH = 600;
    
    protected static final int FRANCE_HEIGHT = 615;
    
    /**
     * 
     */
    public MapComponent()
    {
        super();
        
        // Initialize properties
        this.agencies = new ArrayList<>();
        this.trainingCenters = new ArrayList<>();
        this.solution = new HashMap<>();
    }
    
    /**
     * 
     * @param agencies 
     */
    public void setAgencies(List<Agency> agencies)
    {
        this.agencies = agencies;
        
        // Trigger repaint
        this.repaint();
    }
    
    /**
     * 
     * @param trainingCenter 
     */
    public void setTrainingCenters(List<TrainingCenter> trainingCenter)
    {
        this.trainingCenters = trainingCenter;
        
        // Trigger repaint
        this.repaint();
    }
    
    protected int longitudeToAbscissa(double longitude)
    {
        return 36 + MapComponent.FRANCE_WIDTH - (int) ((MapComponent.EAST_BOUNDARY - longitude) / MapComponent.LONGITUDE_DELTA * MapComponent.FRANCE_WIDTH);
    }
    
    protected int latitudeToOrdinate(double latitude)
    {
        return 20 + (int) ((MapComponent.NORTH_BOUNDARY - latitude) / MapComponent.LATITUDE_DELTA * MapComponent.FRANCE_HEIGHT);
    }
    
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        
        // First, try drawing the map
        try
        {
            g.drawImage(this.image = ImageIO.read(this.getClass().getResource("/views/resources/map.gif")), 0, 0, this);
        }
        catch(IOException ex)
        {
            Logger.getLogger(MapComponent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Then, the agencies and the training centers
        if(this.agencies.size() > 0)
        {
            // Initialization
            g.setColor(Color.RED);
            
            // Draw every agency
            for(Agency agency : this.agencies)
            {
                g.fillRoundRect(
                    this.longitudeToAbscissa(agency.getLongitude()) - 2,
                    this.latitudeToOrdinate(agency.getLatitude()) - 2,
                    5,
                    5,
                    10,
                    10
                );
            }
        }
        
        if(this.trainingCenters.size() > 0)
        {
            // Initialization
            g.setColor(Color.BLUE);
            
            // Draw every training center
            for(TrainingCenter trainingCenter : this.trainingCenters)
            {
                g.fillRoundRect(
                    this.longitudeToAbscissa(trainingCenter.getLongitude()) - 2,
                    this.latitudeToOrdinate(trainingCenter.getLatitude()) - 2,
                    3,
                    3,
                    10,
                    10
                );
            }
        }
        
        // Finally, if there is a solution, draw it
        if(this.solution.size() > 0)
        {
           g.setColor(Color.BLACK);
           
            for(Map.Entry<Agency, TrainingCenter> entry : this.solution.entrySet())
            {
                g.drawLine(
                    this.longitudeToAbscissa(entry.getKey().getLongitude()),
                    this.latitudeToOrdinate(entry.getKey().getLatitude()),
                    this.longitudeToAbscissa(entry.getValue().getLongitude()),
                    this.latitudeToOrdinate(entry.getValue().getLatitude())
                );
            }
        }
    }

    @Override
    public void onStepEnd(Map<Agency, TrainingCenter> solution, double price)
    {
        // Register new solution
        this.solution = solution;
        
        // Trigger repaint
        this.repaint();
    }

    @Override
    public void onEnd(Map<Agency, TrainingCenter> bestSolution, double bestPrice)
    {
        // Register best solution
        this.solution = bestSolution;
        
        // Trigger repaint
        this.repaint();
    }
}
