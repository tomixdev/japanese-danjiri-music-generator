import java.util.Vector;
import java.io.FileNotFoundException;

public class Tester{
    public static void main (String[] args) throws FileNotFoundException{

	testImitateMethod();	
	//testThreeNewlyAddedMethods();	
	//testGetMaximumNumberOfHowManyTruesAreAdjacent();
	//testCombinationMethod();
	//testCalculateAllPossibleNumberCombinationsMethod();
	//testCombinationsOfAllPossibleRhythmPatterns();
	//testAllPossibleROUTs();
    }

    public static void testImitateMethod() throws FileNotFoundException {
	String[] x = new String[]{"part1", "part2"};
	RhythmComposition test = new RhythmComposition("Test", x, 60, 5);

	RhythmPart part1 = test.getRhythmPartsVector().elementAt(0);
	RhythmPart part2 = test.getRhythmPartsVector().elementAt(1);
    
	part1.addRhythmPattern("Ravel");
	part1.addRhythmPattern("Ravel");
	part2.addRhythmPattern("Ravel");

	part2.augmentedlyImitate(part1, 3, 18, 4, 2);

	test.saveThisCompositionToFile();

	part1.diminishedlyImitate(part2, 25, 52, 0, 2);

	test.saveThisCompositionToFile();
    }
	
    public static void testThreeNewlyAddedMethods() {
	RhythmPattern test = new RhythmPattern("Test");


	/////TEST AUGMENT AND DIMINISH in RhythmPattern Class
	System.out.println("Original");
	System.out.println(test);

	System.out.println();;
	
	System.out.println("Augumented by 2");
	System.out.println(test.augmentAndCopy(2));

	System.out.println();

	System.out.println("Augment by 3");
	System.out.println(test.augmentAndCopy(3));

	System.out.println();
	System.out.println("Augment by 4");
	System.out.println(test.augmentAndCopy(4));
	//System.out.println(test.augmentAndCopy(4).getNumberOfPulses());

	System.out.println();

	System.out.println("diminish by 2");
	System.out.println(test.augmentAndCopy(4).diminishAndCopy(2));

	System.out.println("augument by 3 and diminish by 3");
	System.out.println(test.augmentAndCopy(3).diminishAndCopy(3));

	System.out.println("diminish by 2");
	System.out.println(test.augmentAndCopy(3).diminishAndCopy(3).diminishAndCopy(2));
    }
}
    
    /*
    public static void testGetMaximumNumberOfHowManyTruesAreAdjacent () {
	Vector<Boolean> test = new Vector<Boolean>();
	test.add(null);
	test.add(true);
	test.add(false);
	test.add(true);
	//test.add(true);
	//test.add(true);
	//test.add(true);
	//test.add(true);
	//test.add(false);
	//test.add(true);
	//test.add(true);
	//test.add(false);
	//test.add(false);
	test.add(true);
	test.add(false);
	test.add(true);
	test.add(false);
	test.add(true);
	test.add(true);

	System.out.println(HelperMethods.getMaximumNumberOfHowManyTruesAreAdjacent(test));
    }
    */
	
    /*
    public static void testCombinationMethod() {
	int test1 = HelperMethods.Combination(7, 4);
	System.out.println("Should Be 35 Is: " + test1);

	int test2 = HelperMethods.Combination(9, 2);;
	System.out.println("Should Be 36 Is: " + test2);
    }

    public static void testCalculateAllPossibleNumberCombinationsMethod() {
	
	Vector<Vector<Integer>> test1 = HelperMethods.calculateAllPossibleNumberCombinations(5, 3);
	System.out.println("Combination Of Choosing 3 Numbers From 1 to 5 is:   ---------------------");
	for (int i = 0; i < test1.size(); i ++) {
	    for (int j = 0; j < test1.elementAt(i).size(); j++) {
		System.out.print(test1.elementAt(i).elementAt(j) + " ");
	    }
	    System.out.println();
	}

	System.out.print("-----------------------------------");

	Vector<Vector<Integer>> test2 = HelperMethods.calculateAllPossibleNumberCombinations(4, 2);
	System.out.println("Combination Of Choosing 2 Numbers From 1 to 4 is:   ---------------------");
	for (int i = 0; i < test2.size(); i ++) {
	    for (int j = 0; j < test2.elementAt(i).size(); j++) {
		System.out.print(test2.elementAt(i).elementAt(j) + " ");
	    }
	    System.out.println();
	}

	System.out.println("-----------------------------------");
	
    }

    public static void testCombinationsOfAllPossibleRhythmPatterns () {
	ROUT test1 = new ROUT (6, 4);

	System.out.println("All Possible Rhythmic Combinations Of 6 pulses and 4 hits are: ");
	//test1.displayCombinationsOfAllPossibleRhythmPatterns();

	System.out.println("-----------------------------------");
    
				   
	//for(int i = 1; i < test1.combinationsOfAllPossibleRhythmPatterns.size(); i++) {
	  //  for(int j = 1; j < test1.combinationsOfAllPossibleRhythmPatterns.elementAt(i).size(); j++) {
	//	System.out.print(test1.combinationsOfAllPossibleRhythmPatterns.elementAt(i).elementAt(j) + " ");
	  //  }
	    //System.out.println();
	//}
	
    }

    public static void testAllPossibleROUTs() {
	ROUT test1 = new ROUT (12, 7);
	System.out.println("All Possible ROUTs Of 12 pulses and 7 hits are: ");
	//test1.displayAllPossibleROUTs();
	System.out.println("---------------------------------");
    }
    */

