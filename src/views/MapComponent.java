package views;

import java.awt.Graphics;
import java.util.List;
import java.util.Map;
import javax.swing.JComponent;
import models.Agency;
import models.TrainingCenter;

/**
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 * @author Thomas Arnaud (thomas.arnaud@etu.univ-lyon1.fr)
 * @author Alexis Rabilloud (alexis.rabilloud@etu.univ-lyon1.fr)
 */
public class MapComponent extends JComponent
{
    protected List<Agency> agencies;
    
    protected List<TrainingCenter> trainingCenters;
    
    protected Map<Agency, TrainingCenter> solution;
    
    @Override
    protected void paintComponent(Graphics g)
    {
        
    }
}
