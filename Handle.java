/**
 * The handle class
 * Store the start position of the record
 * @author Sheng Peng <shengp6> & Wenjia Song <wenjia7>
 * @version 09/03/2016
 */
public class Handle {
    private int pos;
    /**
     * Constructor 
     * Create a new handle object
     * @param startPos The start position of the record
     */
    public Handle(int startPos) {
        pos = startPos;
    }
    /**
     * Accessor method, get the start position of the record
     * @return pos The start position of the record
     */
    public int getPos() {
        return pos;
    }

}
