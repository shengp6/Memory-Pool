
// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.
// <Sheng Peng & Wenjia Song>
/**
 * This project is to store the songs and artists in a memory
 * pool. The most important feature of this project is that we
 * minimize the usage of memory.
 * We use a list to trace the free blocks, and insert 
 * the information into free blocks when possible. We store 
 * the start position of strings in hash table, which is very
 * efficient when searching
 */
/**
 * The class containing the main method.  
 * @author Sheng Peng <shengp6> & Wenjia Song <wenjia7>
 * @version 09/03/2016
 */

public class Memman {
    /**
     * @param args
     * The name of the command file passed in as a command
     * line argument.
     */
    public static void main(String[] args) {
        new CommandProcessor(Integer.parseInt(args[0]),
                Integer.parseInt(args[1]), args[2]);
    }
}
