import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import student.TestCase;

/**
 * Test the class Menman 
 * @author Sheng Peng <shengp6> & Wenjia Song <wenjia7>
 * @version 09/03/2016
 */
public class MemmanTest extends TestCase {
    /**
     * This method sets up the tests that follow.
     */
    public void setUp() {
        // Nothing Here
    }

    /**
     * This method gets code coverage of the class declaration.
     */
    public void testMInit() {
        Memman men = new Memman();
        assertNotNull(men);
        Scanner output = null;
        String[] args = new String[3];
        args[0] = "10";
        args[1] = "32";
        args[2] = "P1sampleInput.txt";
        Memman.main(args);
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
}
