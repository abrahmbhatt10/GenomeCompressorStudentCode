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
        // TODO: complete the compress() method
        /*
            Below code taken from Mr. Blick's slides:
         */
        String TARGETA = "A";
        String TARGETT = "T";
        String TARGETG = "G";
        String TARGETC = "C";
        int LEN = TARGETA.length();
        int BITS_PER_CHAR = 2;
        String s;
        int n;
        while(true){
            s = BinaryStdIn.readString();
            System.out.println(s);
            n = s.length();
            if(n <= 0){
                break;
            }
            for(int i = 0; i < n; i++) {
                if (i + LEN <= n && s.substring(i,i+LEN).equals(TARGETA)){
                    BinaryStdOut.writeBit(false);
                    BinaryStdOut.writeBit(false);
                    i += LEN - 1;
                }
                if (i + LEN <= n && s.substring(i,i+LEN).equals(TARGETT)){
                    BinaryStdOut.writeBit(false);
                    BinaryStdOut.writeBit(true);
                    i += LEN - 1;
                }
                if (i + LEN <= n && s.substring(i,i+LEN).equals(TARGETG)){
                    BinaryStdOut.writeBit(true);
                    BinaryStdOut.writeBit(false);
                    i += LEN - 1;
                }
                if (i + LEN <= n && s.substring(i,i+LEN).equals(TARGETC)){
                    BinaryStdOut.writeBit(true);
                    BinaryStdOut.writeBit(true);
                    i += LEN - 1;
                }
            }
        }
        BinaryStdOut.close();
    }

    /**
     * Reads a binary sequence from standard input; expands and writes the results to standard output.
     */
    public static void expand() {
        // TODO: complete the expand() method
        /*
            Below code taken from Mr. Blick's slides:
         */
        boolean firstBit;
        boolean secondBit;
        while (!BinaryStdIn.isEmpty()){
            firstBit = BinaryStdIn.readBoolean();
            if(!BinaryStdIn.isEmpty()){
                secondBit = BinaryStdIn.readBoolean();
            }
            else{
                break;
            }
            if((firstBit == false) && (secondBit == false)){
                BinaryStdOut.write('A');
            }
            if((firstBit == false) && (secondBit == true)){
                BinaryStdOut.write('T');
            }
            if((firstBit == true) && (secondBit == false)){
                BinaryStdOut.write('G');
            }
            if((firstBit == true) && (secondBit == true)){
                BinaryStdOut.write('C');
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