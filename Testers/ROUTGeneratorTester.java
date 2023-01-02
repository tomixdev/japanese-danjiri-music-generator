import java.util.Vector;
import java.util.Random;
import java.io.FileNotFoundException;

public class ROUTGeneratorTester {
    public static void main (String[] args) throws FileNotFoundException{
	ROUTGenerator generateRavel = new ROUTGenerator(16, 12);
	System.out.println("Test ROUT methods ---------------------------------------------------------");
	System.out.println("Test writeAllPossibleROUTsToFile");
	generateRavel.writeAllPossibleROUTsToFile();
	
	System.out.println();

	System.out.println("Choose No.90");
	System.out.println(generateRavel.chooseAnROUTAndCopy(90));

	System.out.println();

	System.out.println("randomlyChooseAnROUTAndCopy()");
	Random rand = new Random();
	System.out.println(generateRavel.randomlyChooseAnROUTAndCopy(rand.nextInt()));

	System.out.println();

	System.out.println("Save ravel pattern to file. No 90");
	generateRavel.writeAnROUTToFile(90);

	System.out.println();

	System.out.println("Test Symmetrical methods-----------------------------------------------------");
	ROUTGenerator symmetricalTest = new ROUTGenerator(16, 6);
	System.out.println("writeAllPossibleSymmetricalRhythmsToFile()");
	symmetricalTest.writeAllPossibleSymmetricalRhythmsToFile();

	System.out.println();

	System.out.println("Choose a symmetrical rhythm no.0");
	System.out.println(symmetricalTest.chooseASymmetricalRhythmAndCopy(0));

	System.out.println();

	System.out.println("choose randomly");
	System.out.println(symmetricalTest.randomlyChooseASymmetricalRhythmAndCopy(rand.nextInt()));

	System.out.println();
	System.out.println("Save a symmetrical rhythm no.0");
	symmetricalTest.writeASymmetricalRhythmToFile(0);

	System.out.println();

	System.out.println("Test symmetricalROUT methods---------------------------------------------");
	ROUTGenerator symmetricalROUTTest = new ROUTGenerator(12,4);
	System.out.println("writeAllPossibleSymmetricalROUTsToFile");
	symmetricalROUTTest.writeAllPossibleSymmetricalROUTsToFile();
	System.out.println();
	
       
	
    }
}
	
	

	
	
