package tests;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import utils.CsvUtils;

/**
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 * @author Thomas Arnaud (thomas.arnaud@etu.univ-lyon1.fr)
 * @author Alexis Rabilloud (alexis.rabilloud@etu.univ-lyon1.fr)
 */
public class CsvTest
{
    public static void main(String[] args)
    {
        List<String[]> lines = CsvUtils.read(new File("D:\\LieuxPossibles.txt"), true);
        
        for(int i = 0, j = lines.size(); i < j; i++)
        {
            System.out.println(Arrays.toString(lines.get(i)));
        }
    }
}
