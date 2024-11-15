/******************************************************************************
 *  Compilation:  javac GenomeCompressor.java
 *  Execution:    java GenomeCompressor - < input.txt   (compress)
 *  Execution:    java GenomeCompressor + < input.txt   (expand)
 *  Dependencies: BinaryIn.java BinaryOut.java
 *  Data files:   genomeTest.txt
 *                virus.txt
 *
 *  Compress or expand a genomic sequence using a 2-bit code.
 ******************************************************************************/

/**
 *  The {@code GenomeCompressor} class provides static methods for compressing
 *  and expanding a genomic sequence using a 2-bit code.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *  @author Zach Blick
 **/
public class GenomeCompressor {

    /**
     * Reads a sequence of 8-bit extended ASCII characters over the alphabet
     * { A, C, T, G } from standard input; compresses and writes the results to standard output.
     */
    public static void compress() {
        /*
            Below code taken from Mr. Blick's slides:
         */
        String TARGET_A = "A";
        String TARGET_T = "T";
        String TARGET_G = "G";
        String TARGET_C = "C";

        int LEN = TARGET.length();
        int BITS_PER_CHAR = 7;
        String s = BinaryStdIn.readString();
        int n = s.length();
        BinaryStdOut.write(n);
        for (int i = 0; i < n; i++) {
            if (i + LEN <= n && s.substring(i,i+LEN).equals(TARGET)){
                BinaryStdOut.write(ESC, 7);
                i += LEN - 1;
            }
            else {
                BinaryStdOut.write(s.charAt(i), 7);
            }
        }
        BinaryStdOut.close();
    }

    /**
     * Reads a binary sequence from standard input; expands and writes the results to standard output.
     */
    public static void expand() {
        /*
            Below code taken from Mr. Blick's slides:
         */
        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar(7);
            if (c == ESC) {
                BinaryStdOut.write(TARGET);
            }
            else {
                BinaryStdOut.write(c);
            }
        }
        BinaryStdOut.close();
    }


    /**
     * Main, when invoked at the command line, calls {@code compress()} if the command-line
     * argument is "-" an {@code expand()} if it is "+".
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {

        if      (args[0].equals("-")) compress();
        else if (args[0].equals("+")) expand();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}