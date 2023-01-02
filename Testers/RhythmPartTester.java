import java.util.Random;
import java.io.FileNotFoundException;
import java.util.Vector;
import java.lang.Math;

public class RhythmPartTester {
    public static void main (String[] args) throws FileNotFoundException{
	Random rand = new Random(5);
	RhythmPart test = new RhythmPart ("Test", rand);

	System.out.println("Add a ravel pattern ----------------------");
	RhythmPattern ravel = new RhythmPattern("RavelPattern.txt");
	test.addRhythmicComponent(ravel);

	System.out.println();
	
	System.out.println("And Print Out Ravel Pattern");
	System.out.println(test);

	System.out.println();

	System.out.println("Print out another ravel pattern named ravvvvvvvel");
	RhythmPart test2 = new RhythmPart ("ravevvvvvel", rand);
	test2.addRhythmicComponent(ravel);
	System.out.println(test2);

	System.out.println();

	System.out.println("Add another ravel pattern to ravel");
	test.addRhythmicComponent(ravel);
	System.out.println(test);

	System.out.println();
	
	//System.out.println("Add a modify ravel pattern, and see if it affects both ravel patterns");
	//ravel.popRhythmEventAndReplace();
	//System.out.println(test);

	System.out.println();

	System.out.println("Add a rest at the end");
	RhythmEvent toAdd = new RhythmEvent();
	test.addRhythmicComponent(toAdd);
	System.out.println(test);
	System.out.println();

	System.out.println("get number of pulses so far. Should be 33");
	System.out.println(test.getNumberOfPulsesSoFar());
	System.out.println();

	System.out.println("get number of rhythmic component so far. Should be 3");
	System.out.println(test.getNumberOfRhythmicComponentsSoFar());
	System.out.println();

	//System.out.println("get rhythm event at 14. Should be A");
	//System.out.println(test.getRhythmEventAt(14));
	//System.out.println();

	System.out.println("test randomize1Gaussian-----------------------");
	System.out.println("aveage frequency 4");
	test.randomize1Gaussian(5, 27, 4);
	System.out.println(test);
	System.out.println();

	System.out.println("test randomize2Gaussian-----------------------");
	test2.addRhythmicComponent(ravel);
	test2.addRhythmicComponent(toAdd);
	test2.randomize2Gaussian(2, 2, 1);
	System.out.println(test2);
	System.out.println();

	
    }
}
