import student.TestCase;
/**
 * Test the methods in memory pool
 * @author Sheng Peng <shengp6> & Wenjia Song <wenjia7>
 * @version 09/06/2016
 */
public class MemoryPoolTest extends TestCase {
    private MemoryPool mp1;
    /**
     * set up the test
     */
    public void setUp() {
        mp1 = new MemoryPool(32);        
    }
    /**
     * test all the public methods in Memory Pool
     */
    public void testAll() {
        Handle handle1 = mp1.insert("Blind Lemon Jefferson");
        assertEquals(mp1.printBlocks(), "(23,9)");
        assertEquals(mp1.size(), 32);
        assertEquals(mp1.resized(), 0);        
        Handle handle2 = mp1.insert("Ma Rainey");
        assertEquals(mp1.resized(), 1);
        Handle handle3 = mp1.insert("Charley Patton");        
        String longStr = "abcdefghijklmnopqrstuvwxyz"
                + "abcdefghijklmnopqrstuvwxyz";  
        mp1.insert(longStr);
        assertEquals(mp1.printExpandMemory(), 
                "Memory pool expanded to be 96 bytes.\n"
                + "Memory pool expanded to be 128 bytes.\n");
        assertEquals(mp1.size(), 128);
        assertEquals(mp1.resized(), 2);
        assertTrue(mp1.getEntry(handle1).equals("Blind Lemon Jefferson"));
        assertTrue(mp1.getEntry(handle2).equals("Ma Rainey"));
        assertTrue(mp1.getEntry(handle3).equals("Charley Patton"));
        mp1.remove(handle1);        
        mp1.remove(handle2);
        mp1.remove(handle3);
        
    }
}
