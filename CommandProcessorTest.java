import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import student.TestCase;
/**
 * Test the handle class
 * @author Sheng Peng <shengp6> & Wenjia Song <wenjia7>
 * @version 09/05/2016
 */
public class CommandProcessorTest extends TestCase { 
    private Scanner output;
    /**
     * Set up the test
     */
    public void setUp() {
        //Nothing here        
    }
    /**
     * test the class with standard input and output
     * from professor
     */
    public void testStandardOutput() {
        CommandProcessor cp = 
                new CommandProcessor(10, 32, "P1sampleInput.txt");
        
        assertEquals(cp.getSong().getNumOfEntries(), 9);
        assertEquals(cp.getArtist().getNumOfEntries(), 8); 
        try {
            output = new Scanner(new File("P1sampleOutput.txt"));
        } 
        catch (FileNotFoundException e) {            
            System.out.println("File is not found");
        }
        StringBuilder sb = new StringBuilder();
        while (output.hasNextLine()) {
            sb.append(output.nextLine() + "\n") ;
        }
        String standardOutput = sb.toString();
        assertFuzzyEquals(systemOut().getHistory(), standardOutput);        
    }  
    /**
     * test the special cases which were not 
     * happened in standard input & output
     */
    public void testSpecialCase() {        
        new CommandProcessor(10, 32, "input.txt");
        try {
            output = new Scanner(new File("output.txt"));
        } 
        catch (FileNotFoundException e) {            
            System.out.println("File is not found");
        }
        StringBuilder sb = new StringBuilder();
        while (output.hasNextLine()) {
            sb.append(output.nextLine() + "\n") ;
        }
        String specialCase = sb.toString();
        assertFuzzyEquals(systemOut().getHistory(), specialCase);         
    }

}
