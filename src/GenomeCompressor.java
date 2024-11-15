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
    private static String TARGET = "ATGC";
    private static int LEN = TARGET.length();

    /**
     * Reads a sequence of 8-bit extended ASCII characters over the alphabet
     * { A, C, T, G } from standard input; compresses and writes the results to standard output.
     */
    public static void compress() {
        /*
            Below code inspired by Mr. Blick's slides and Sedgewick & Wayne:
         */
        String TARGET = "ATGC";
        int LEN = TARGET.length();
        int BITS_PER_CHAR = 2;
        String s = BinaryStdIn.readString();
        int n = s.length();
        BinaryStdOut.write(n);
        int index;
        for (int i = 0; i < n; i++) {
            index = getIndex(s.charAt(i));
            if(index > -1){
                BinaryStdOut.write(index, BITS_PER_CHAR);
            }
        }
        BinaryStdOut.close();
    }

    /*
        Inspired by DNA index code of Sedgewick & Wayne.
        Returns index of a char, which tells which type of letter it is.
        For A, it will be 0.
        For T, it will be 1.
        For G, it will be 2.
        For C, it will be 3.
     */
    public static int getIndex(char DNAChar){
        for(int i = 0; i < LEN; i++){
            if(DNAChar == TARGET.charAt(i)){
                return i;
            }
        }
        return -1;
    }

    public static char getChar(int index){
        if((index >= 0) && (index <= LEN)){
            return TARGET.charAt(index);
        }
        return ' ';
    }

    /**
     * Reads a binary sequence from standard input; expands and writes the results to standard output.
     */
    public static void expand(){
        boolean firstBit;
        boolean secondBit;
        int index;
        int header = 0;
        int count = 0;
        if(!BinaryStdIn.isEmpty()){
            header = BinaryStdIn.readInt();
        }
        while (!BinaryStdIn.isEmpty()) {
            firstBit = BinaryStdIn.readBoolean();
            if (!BinaryStdIn.isEmpty()) {
                secondBit = BinaryStdIn.readBoolean();
            }
            else {
                break;
            }
            /*
                Converting the first and second bits into int index
                Inspired by the code in BinaryStdOut with regards to
                Buffer and bits.
             */
            index = 0;
            if (firstBit) {
                index |= 1;
            }
            index <<= 1;
            if(secondBit) {
                index |= 1;
            }
            BinaryStdOut.write(getChar(index));
            count++;
            if(count >= header) {
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
