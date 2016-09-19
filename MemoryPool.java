/**
 * The class of memory pool
 * It's a byte array, which store the strings
 * @author Sheng Peng <shengp6> & Wenjia Song <wenjia7>
 * @version 09/09/2016
 *
 */
public class MemoryPool {
    private FreeBlockList freeBlockList;
    private byte[] byteArray;
    private int resize;
    private int initSize;
    /**
     * Constructor 
     * Create a new byte array 
     * Create a new free block list
     * Add a node to free block list
     * @param blockSize The size of memory pool
     */
    public MemoryPool(int blockSize) {
        initSize = blockSize;
        byteArray = new byte[initSize];
        freeBlockList = new FreeBlockList(initSize);        
        resize = 0;
    }
    /**
     * Insert a string into the memory pool
     * @param str The string we want to insert into the memory pool
     * @return handle The handle of the string
     */
    public Handle insert(String str) {
        this.resetResize();
        byte[] entry = str.getBytes();
        int length = entry.length;
        byte[] input = new byte[length + 2];
        input[0] = (byte)(length >> 8);
        input[1] = (byte)(length);
        System.arraycopy(entry, 0, input, 2, length);
        int inputPos;
        int listPos = freeBlockList.checkFreeBlock(input.length);
        while ( listPos == -1) { //no available block
            resizeMemory();
            resize++;
            listPos = freeBlockList.checkFreeBlock(input.length);
        }        
        inputPos = freeBlockList.get(listPos).getStartPos();
        //shrink the free block
        freeBlockList.shrinkBlock(listPos, length + 2);
        //copy the bytes array into memory pool
        System.arraycopy(input, 0, byteArray, inputPos, length + 2);        
        return new Handle(inputPos);
    }
    /**
     * Remove the string bytes from memory pool
     * @param handle The handle corresponds to the string
     */
    public void remove(Handle handle) {        
        int pos = handle.getPos();
        //Calculate the length of the string
        int length1 = byteArray[pos] << 8;
        int length2 = byteArray[pos + 1];
        int length = length1 + length2;
        //remove the data in byteArray
        byte[] emptyArray = new byte[length + 2];
        System.arraycopy(emptyArray, 0, byteArray, pos, length + 2);
        //deal with the free block list
        //add a free block into free block list
        freeBlockList.addBlock(new FreeBlock(pos, length + 2));
        
    }

    /**
     * add 32 bytes to the memory pool
     * update the free block list
     */
    private void resizeMemory() { 
        //add new block into list
        freeBlockList.addBlock(
                new FreeBlock(byteArray.length, initSize));
        
        //resize the memory pool
        byte[] temp = new byte[byteArray.length + initSize];
        System.arraycopy(byteArray, 0, temp, 0, byteArray.length);
        byteArray = temp;         
        
    }    

    /**
     * Return the string corresponding to the handle
     * @param handle The handle of strings
     * @return The string stored in the memory pool 
     *          corresponding to the handle
     */
    public String getEntry(Handle handle) {
        int pos = handle.getPos();
        int first = byteArray[pos] << 8;
        int second = byteArray[pos + 1];
        int length = first + second;
        StringBuilder sb = new StringBuilder(); 
        pos += 2;
        for (int i = 0; i < length; i++) {
            sb.append((char)byteArray[pos + i]);
        }
        return sb.toString();
    }
    /**
     * Print the information about expand memory
     * @return The string shows that the memory pool expanded
     */
    public String printExpandMemory() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < resize; i++) {
            sb.append("Memory pool expanded to be " 
                    + (this.size() - initSize * (resize
                            - i - 1) + " bytes.\n"));
        }
        return sb.toString();
    }

    /**
     * Reset the resize to false 
     */
    private void resetResize() {
        resize = 0;
    }
    /**
     * The memory pool resized or not
     * @return resize The memory pool resized or not
     */
    public int resized() {
        return resize;
    }
    /**
     * Get the size of memory pool
     * @return The length of the byte array
     */
    public int size() {
        return byteArray.length;
    }
    /**
     * To string the free blocks
     * @return The string lists all the free blocks
     */
    public String printBlocks() {
        return freeBlockList.printBlocks();
    }
}
