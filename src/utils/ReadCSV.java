package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thomas Arnaud
 */
public class ReadCSV {
    public static List<String[]> readFile(File file, Boolean hasHeader) {
        BufferedReader br;
        String line;
        List<String[]> fileRead = new ArrayList();
        
        try {
            br = new BufferedReader(new FileReader(file));
            
            line = br.readLine();
            if(!hasHeader) {
                String[] lineSpliped = line.split(";");
                for(int i = 0; i < lineSpliped.length; i++) {
                    if(lineSpliped[i].startsWith("\"")) {
                        lineSpliped[i] = lineSpliped[i].substring(0, lineSpliped[i].length() - 2);
                    }
                }
                fileRead.add(lineSpliped);
            }
            
            while (line != null) {
                line = br.readLine();
                String[] lineSpliped = line.split(";");
                for(int i = 0; i < lineSpliped.length; i++) {
                    if(lineSpliped[i].startsWith("\"")) {
                        lineSpliped[i] = lineSpliped[i].substring(0, lineSpliped[i].length() - 2);
                    }
                }
                fileRead.add(lineSpliped);
            }
        
        }   catch (IOException ex) {
            Logger.getLogger(ReadCSV.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fileRead;
    }
}
