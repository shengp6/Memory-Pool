import student.TestCase;
/**
 * Test the functions in HashTable class
 * @author Sheng Peng <shengp6> & Wenjia Song <wenjia7>
 * @version 09/05/2016
 *
 */
public class HashTableTest extends TestCase {
    private HashTable hash;
    private HashTable hash2;
    private MemoryPool mp;
    private String s1 = "Sheng"; // hash 6
    private String s2 = "Wenjia"; //hash 6
    private String s3 = "sjohos"; //hash 2
    private String s4 = "sdfygf"; //hash 8
    private String s5 = "shohoj"; //hash 6  


    /**
     * Set up the test
     * initialize the hash table
     */
    public void setUp() {
        mp = new MemoryPool(32);
        Handle handle1 = mp.insert(s1);
        Handle handle2 = mp.insert(s2);
        hash = new HashTable(10, mp);
        hash2 = new HashTable(2, mp);        
        hash.insert(s1, handle1);        
        hash.insert(s2, handle2); 
        hash2.insert(s1, handle1);
        hash2.insert(s2, handle2);

    }
    /**
     * All strings inserted will have 
     * the value 6 from hash function
     * Try to insert them into hash table
     */
    public void testInsert() {
        String s6 = "abcd9"; //hash value 6
        String s7 = "abcd10"; //hash value 6
        Handle handle = mp.insert(s5);
        hash.insert(s5, handle);
        handle = mp.insert(s6);
        hash.insert(s6, handle);
        handle = mp.insert(s7);
        hash.insert(s7, handle);       
        assertEquals(hash.getNumOfEntries(), 5);
    }
    /**
     * Check the toString method
     */
    public void testToString() {        
        assertEquals(hash.toString(), "|Sheng| 6\n|Wenjia| 7\n");
    }
    /**
     * Check the method checkDuplicate
     */
    public void testCheckDuplicate() {
        assertTrue(hash.checkDuplicate(s1));
        assertTrue(hash.checkDuplicate(s2));
        assertFalse(hash.checkDuplicate(s5));
        assertFalse(hash.checkDuplicate(s3));
    }
    /**
     * Check the method remove
     */
    public void testRemove() {
        Handle remove = hash.remove(s1);
        assertEquals(remove.getPos(), 0);
        remove = hash.remove(s2);
        assertEquals(remove.getPos(), 7);
        remove = hash.remove(s3);
        assertNull(remove);
    }
    /**
     * check if the resize work
     */
    public void testResize() {
        Handle hadnle3 = mp.insert(s3);
        hash2.insert(s3, hadnle3);
        assertTrue(hash2.resized());        
        assertEquals(hash2.getNumOfEntries(), 3);
        assertEquals(hash2.getSize(), 8);
        Handle handle4 = mp.insert(s4);
        hash2.insert(s4, handle4);
        assertFalse(hash2.resized());
    }

}
