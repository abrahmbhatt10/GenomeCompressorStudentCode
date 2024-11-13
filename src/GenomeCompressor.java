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
        char TARGETA = 'A';
        char TARGETT = 'T';
        char TARGETG = 'G';
        char TARGETC = 'C';
        int BITS_PER_CHAR = 3;
        char s;
        while(!BinaryStdIn.isEmpty()) {
            s = BinaryStdIn.readChar();
            if (s == TARGETA) {
                BinaryStdOut.write(false);
                BinaryStdOut.write(false);
                BinaryStdOut.write(false);
            }
            if (s == TARGETT) {
                BinaryStdOut.write(false);
                BinaryStdOut.write(false);
                BinaryStdOut.write(true);
            }
            if (s == TARGETG) {
                BinaryStdOut.write(false);
                BinaryStdOut.write(true);
                BinaryStdOut.write(false);
            }
            if (s == TARGETC) {
                BinaryStdOut.write(false);
                BinaryStdOut.write(true);
                BinaryStdOut.write(true);
            }
        }
        /* write end of file indicator*/
        BinaryStdOut.write(true);
        BinaryStdOut.write(true);
        BinaryStdOut.write(true);
        BinaryStdOut.flush();
        BinaryStdOut.close();
    }

    /**
     * Reads a binary sequence from standard input; expands and writes the results to standard output.
     */
    public static void expand() {
        /*
            Below code taken from Mr. Blick's slides:
         */
        boolean firstBit;
        boolean secondBit;
        boolean thirdBit;
        while (!BinaryStdIn.isEmpty()){
            firstBit = BinaryStdIn.readBoolean();
            if(!BinaryStdIn.isEmpty()){
                secondBit = BinaryStdIn.readBoolean();
            }
            else{
                break;
            }
            if(!BinaryStdIn.isEmpty()){
                thirdBit = BinaryStdIn.readBoolean();
            }
            else{
                break;
            }
            if((firstBit == false) && (secondBit == false) && (thirdBit == false)){
                BinaryStdOut.write('A');
            }
            if((firstBit == false) && (secondBit == false) && (thirdBit == true)){
                BinaryStdOut.write('T');
            }
            if((firstBit == false) && (secondBit == true) && (thirdBit == false)){
                BinaryStdOut.write('G');
            }
            if((firstBit == false) && (secondBit == true) && (thirdBit == true)){
                BinaryStdOut.write('C');
            }
            if((firstBit == true) && (secondBit == true) && (thirdBit == true)){
                break;
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