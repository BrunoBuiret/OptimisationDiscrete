package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 * @author Thomas Arnaud (thomas.arnaud@etu.univ-lyon1.fr)
 * @author Alexis Rabilloud (alexis.rabilloud@etu.univ-lyon1.fr)
 */
public class CsvUtils
{
    /**
     * The default character that delimits columns.
     */
    public static final String DEFAULT_DELIMITER = ";";
    
    /**
     * The default character that encloses columns.
     */
    public static final String DEFAULT_ENCLOSER = "\"";
    
    /**
     * Reads a CSV file.
     * 
     * @param file The file to read.
     * @param removeFirstLine <code>true</code> to remove the first line, <code>false</code>
     * otherwise.
     * @return 
     */
    public static List<String[]> read(File file, boolean removeFirstLine)
    {
        List<String[]> lines = new ArrayList();
        
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            
            if(removeFirstLine)
            {
                br.readLine();
            }
            
            while((line = br.readLine()) != null)
            {
                String[] splitLine = line.split(CsvUtils.DEFAULT_DELIMITER);
                
                for(int i = 0; i < splitLine.length; i++)
                {
                    if(splitLine[i].startsWith(CsvUtils.DEFAULT_ENCLOSER))
                    {
                        splitLine[i] = splitLine[i].substring(1, splitLine[i].length() - 1);
                    }
                }
                
                lines.add(splitLine);
            }
            
            br.close();
        }
        catch(IOException ex)
        {
            
        }
        
        return lines;
    }
}
