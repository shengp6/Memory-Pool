import student.TestCase;
/**
 * Test the handle class
 * @author Sheng Peng <shengp6> & Wenjia Song <wenjia7>
 * @version 09/03/2016
 */

public class HandleTest extends TestCase {
    private Handle first;
    private Handle second;
    /**
     * set up the test
     * initialize the handles
     */
    public void setUp() {
        first = new Handle(0);
        second = new Handle(5);
    }
    /**
     * Test the getPos method 
     */
    public void testGetPos() {
        assertEquals(first.getPos(), 0);
        assertEquals(second.getPos(), 5);
    }

}
