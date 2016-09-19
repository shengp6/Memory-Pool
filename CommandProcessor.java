import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Process the command
 * Print standard output 
 * @author Sheng Peng <shengp6> & Wenjia Song <wenjia7>
 * @version 09/05/2016
 */
public class CommandProcessor {        
    private HashTable artist;
    private HashTable song;
    private MemoryPool mp;
    /**
     * Constructor
     * @param hashSize The size of hash tables
     * @param blockSize The initial size of the memory pool
     * @param fileName The input file
     */
    public CommandProcessor(int hashSize, int blockSize, String fileName) {
        mp = new MemoryPool(blockSize);        
        artist = new HashTable(hashSize, mp);
        song = new HashTable(hashSize, mp);
        Scanner scan;
        try {
            scan = new Scanner(new File(fileName));
            while (scan.hasNextLine()) {
                String command = scan.nextLine();
                parse(command);
            }       
        } 
        catch (FileNotFoundException e) {            
            e.printStackTrace();
        }
         
    }
    /**
     * Parse a line of command, execute it and print out the result
     * @param command A line of input command
     */
    private void parse(String command) {
        String content;
        //insert
        if (command.contains("insert")) {
            content = command.substring(7);
            String[] names = content.split("<SEP>");
            //insert artist
            if (artist.checkDuplicate(names[0])) {
                System.out.println("|" + names[0] + 
                        "| duplicates a record "
                        + "already in the artist database.");
            }
            else {
                Handle artistHandle = mp.insert(names[0]);
                artist.insert(names[0], artistHandle);
                if (artist.resized()) {
                    System.out.println("Artist hash table size doubled.");
                }
                if (mp.resized() > 0) {
                    System.out.print(mp.printExpandMemory());
                }
                System.out.println("|" + names[0] + 
                        "| is added to the artist database.");
            }
            //insert song
            if (song.checkDuplicate(names[1])) {
                System.out.println("|" + names[1] + 
                        "| duplicates a record already in the song database.");
            }
            else {
                Handle songHandle = mp.insert(names[1]);
                song.insert(names[1], songHandle);
                if (song.resized()) {
                    System.out.println("Song hash table size doubled.");
                }
                if (mp.resized() > 0) {
                    System.out.print(mp.printExpandMemory());                   
                }
                System.out.println("|" + names[1] + 
                        "| is added to the song database.");
            }
        }
        // print
        else if (command.contains("print")) {
            content = command.substring(6);
            if (content.contains("song")) {
                System.out.print(song.toString());
                System.out.println("total songs: " 
                        + song.getNumOfEntries());
            }
            else if (content.contains("artist")) {
                System.out.print(artist.toString());
                System.out.println("total artists: "
                        + artist.getNumOfEntries());
            }
            //print blocks
            else {
                if (mp.printBlocks() == null) {
                    System.out.println("(" + mp.size() + ",0)");
                }
                else {
                    System.out.println(mp.printBlocks());
                }
            }
        }
        //remove
        else {
            content = command.substring(7);
            //remove song
            if (content.contains("song")) {
                content = content.substring(5);
                Handle songHandle = song.remove(content);                
                if (songHandle == null) {
                    System.out.println("|" + content 
                            + "| does not exist in the song database.");
                }
                else {
                    mp.remove(songHandle);
                    System.out.println("|" + content 
                            + "| is removed from the song database.");
                }
            }
            //remove artist
            else {
                content = content.substring(7);
                Handle artistHandle = artist.remove(content);
                if (artistHandle == null) {
                    System.out.println("|" + content 
                            + "| does not exist in the artist database.");
                }
                else {
                    mp.remove(artistHandle);
                    System.out.println("|" + content 
                            + "| is removed from the artist database.");
                }
            }
        }
    }
    /**
     * Accessor method
     * @return Song The hash table of song
     */
    public HashTable getSong() {
        return song;
    }
    /**
     * Accessor method
     * @return Song The hash table of artist
     */
    public HashTable getArtist() {
        return artist;
    }
    
}
