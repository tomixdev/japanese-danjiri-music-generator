import java.util.Vector;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

//=======================================================================
//Helper Class-----------------------------------------------------------
class IntegerCombinationNode {
    IntegerCombinationNode previousNode;
    Integer currentInt;

    public void buildCombinationTree (Vector<IntegerCombinationNode> lastNodes, int addFromWhatNumber, int fromOneToWhatNumber, int howManyToChoose) {
	if(howManyToChoose == 0) {
	    lastNodes.add(this);
	    return;
	}

	for(int i = addFromWhatNumber; i <= fromOneToWhatNumber - howManyToChoose + 1; i++) {
	    IntegerCombinationNode next = new IntegerCombinationNode();
	    next.previousNode = this;
	    next.currentInt = i;
	    next.buildCombinationTree(lastNodes, i + 1, fromOneToWhatNumber, howManyToChoose - 1);
	}
    }	
}
//------------------------------------------------------------------------
//========================================================================
//Public Class------------------------------------------------------------
public class HelperMethods {

    //Following Methof Calculates aCb
    public static int Combination (int a, int b) {	
	if(b > a) {
	    System.out.println("Error In Permutation Methods in HelperMethodsClass");
	    System.exit(1);
	    return -9999;
	}
	else {
	    float bunshi = 1;
	    float bunbo = 1;

	    for (int i = 0; i <= b - 1; i++) {
		bunshi = bunshi * (a - i);
	    }

	    for (int i = b; i >= 1; i--) {
		bunbo = bunbo * i;
	    }
	    
	    return (int)(bunshi  / bunbo);
	}
    }

    public static Vector<Vector<Integer>>  calculateAllPossibleNumberCombinations(int fromOneToWhatNumber, int howManyToChoose) {
	//System.out.println(fromOneToWhatNumber);
	//System.out.println(howManyToChoose);
	//System.out.println(Combination(fromOneToWhatNumber, howManyToChoose));
	Vector<Vector<Integer>> toReturn = new Vector<Vector<Integer>>(HelperMethods.Combination(fromOneToWhatNumber, howManyToChoose));

	IntegerCombinationNode dummy = new IntegerCombinationNode();
	dummy.previousNode = null;
	dummy.currentInt = null;

	Vector<IntegerCombinationNode> lastNodes = new Vector<IntegerCombinationNode>();

	dummy.buildCombinationTree(lastNodes, 1, fromOneToWhatNumber, howManyToChoose);
	

	for (int i = 0; i < lastNodes.size(); i++) {
	    IntegerCombinationNode currentNode = lastNodes.elementAt(i);
	    toReturn.add(i, new Vector<Integer>());
	    while(currentNode.currentInt != null) {
		toReturn.elementAt(i).add (currentNode.currentInt);
		currentNode = currentNode.previousNode;
	    }
	}

	return toReturn;
    }

    public static boolean areTwoBooleanVectorsIdenticalFromIndexOne (Vector<Boolean> a, Vector<Boolean> b) {
	if(a.size() != b.size()) {
	    return false;
	}
	
	for (int i = 1; i < a.size(); i++) {
	    if(a.elementAt(i) != b.elementAt(i)) {
		return false;
	    }
	}

	return true;
    }

    public static Vector<Vector<Integer>> getBeatPlaces(int numberOfPulses) {
	try {
	    Vector<Vector<Integer>> toReturn = new Vector<Vector<Integer>>();
	    
	    String filePlaceAndName = "./BeatPlaceInfo/BeatPlacesOf" + Integer.toString(numberOfPulses) + "Pulses.txt";////Not Sure If This Works!!!!!
	    Scanner infile = new Scanner(new FileReader(filePlaceAndName));
	    //infile.nextLine(); // read the first line
	    
	    while(infile.hasNextLine()) {
		String aLine = infile.nextLine();
		String[] allNumbersInALineInString = aLine.split("\\s+");
		
		Vector<Integer> allNumbersInALineInInteger = new Vector<Integer>();

		for(int i = 0; i < allNumbersInALineInString.length; i++) {
		    allNumbersInALineInInteger.add(Integer.valueOf(allNumbersInALineInString[i]));
		}

		toReturn.add(allNumbersInALineInInteger);
	    }
	    
	    infile.close();
	    return toReturn;
	}
	catch (java.io.FileNotFoundException e) {
	    System.out.println("File name of beat places of " + Integer.toString(numberOfPulses) + " pulses is wrong! Or the file for beat places of " + Integer.toString(numberOfPulses) + " pulses do not exist!!!!!!");
	    System.exit(1);
	    return null;
	}	
    }

    public static int getNumberOfTruesFromBooleanVector(Vector<Boolean> booleanVector) {
	int toReturn = 0;
	for (int i = 1; i < booleanVector.size(); i++) {
	    if(booleanVector.elementAt(i) == true) {
		toReturn++;
	    }
	}
	return toReturn;
    }

    public static int getNumberOfFalsesFromBooleanVector(Vector<Boolean> booleanVector) {
	int toReturn = 0;
	for (int i = 1; i < booleanVector.size(); i++) {
	    if(booleanVector.elementAt(i) == false) {
		toReturn++;
	    }
	}
	return toReturn;
    }

    public static boolean whetherTwoAdjacentTruesExist(Vector<Boolean> booleanVector) {
	for(int i = 1; i < booleanVector.size() - 1; i++) {
	    if(booleanVector.elementAt(i) == true && booleanVector.elementAt(i + 1) == true) {
		return true;
	    }
	}

	if(booleanVector.elementAt(booleanVector.size() - 1) == true && booleanVector.elementAt(1) == true) {
	    return true;
	}
	
	return false;
    }

    public static boolean whetherTwoAdjacentFalsesExist(Vector<Boolean> booleanVector) {
	for(int i = 1; i < booleanVector.size() - 1; i++) {
	    if(booleanVector.elementAt(i) == false && booleanVector.elementAt(i + 1) == false) {
		return true;
	    }
	}

	if(booleanVector.elementAt(booleanVector.size() - 1) == false && booleanVector.elementAt(1) == false) {
	    return true;
	}
	
	return false;
    }

    public static int getMaximumNumberOfHowManyTruesAreAdjacent (Vector<Boolean> booleanVector) {
	int maximumNumberOfAdjacentTrues = 0;

	//STEP 1: Checks Normal Adjacency
	for(int i = 1; i < booleanVector.size(); i++) {
	    int numberOfAdjacentTrues = 0;
	    
	    if(booleanVector.elementAt(i) == true) {
		numberOfAdjacentTrues++;
		while(i + 1 < booleanVector.size() && booleanVector.elementAt(i + 1) == true) {
		    numberOfAdjacentTrues++;
		    i++;
		}
	    }

	    if(numberOfAdjacentTrues > maximumNumberOfAdjacentTrues) {
		maximumNumberOfAdjacentTrues = numberOfAdjacentTrues;
	    }
	}

	//STEP 2: Check Edge-to-edge Adhacency
	int numberOfEdgeAdjacentTrues = 0;
	if(booleanVector.elementAt(booleanVector.size() - 1) == true && booleanVector.elementAt(1) == true) {
	    numberOfEdgeAdjacentTrues = 2;
	    for(int i = booleanVector.size() - 2; i >= 2 ; i = i - 1) {
		if(booleanVector.elementAt(i) == true) {
		    numberOfEdgeAdjacentTrues++;
		}
		else {
		    i = -99999999;
		}
	    }
	    
	    for(int i = 2; i < booleanVector.size() - 1; i++) {
		if(booleanVector.elementAt(i) == true) {
		    numberOfEdgeAdjacentTrues++;
		}
		else {
		    i = 99999999;
		}
	    }

	    if(numberOfEdgeAdjacentTrues > booleanVector.size() - 1) { //if all elements are true, this condition could happen
		numberOfEdgeAdjacentTrues = booleanVector.size() - 1;
	    }
	}
	
	if(numberOfEdgeAdjacentTrues > maximumNumberOfAdjacentTrues) {
	    maximumNumberOfAdjacentTrues = numberOfEdgeAdjacentTrues;
	}

	return maximumNumberOfAdjacentTrues;		
    }

    public static int getMaximumNumberOfHowManyFalsesAreAdjacent (Vector<Boolean> booleanVector) {
	int maximumNumberOfAdjacentFalses = 0;

	//STEP1: Check Normal Adjacency
	for(int i = 1; i < booleanVector.size(); i++) {
	    int numberOfAdjacentFalses = 0;

	    if(booleanVector.elementAt(i) == false) {
		numberOfAdjacentFalses++;
		while(i + 1 < booleanVector.size() && booleanVector.elementAt(i + 1) == false) {
		    numberOfAdjacentFalses++;
		    i++;
		}
	    }

	    if(numberOfAdjacentFalses > maximumNumberOfAdjacentFalses) {
		maximumNumberOfAdjacentFalses = numberOfAdjacentFalses;
	    }
	}

	//STEP2: Check Edge-to-edge Adjacency
	int numberOfEdgeAdjacentFalses = 0;
	if(booleanVector.elementAt(booleanVector.size() - 1) == false && booleanVector.elementAt(1) == false) {
	    numberOfEdgeAdjacentFalses = 2;
	    for(int i = booleanVector.size() - 2; i >= 2 ; i = i - 1) {
		if(booleanVector.elementAt(i) == false) {
		    numberOfEdgeAdjacentFalses++;
		}
		else {
		    i = -99999999;
		}
	    }
	    
	    for(int i = 2; i < booleanVector.size() - 1; i++) {
		if(booleanVector.elementAt(i) == false) {
		    numberOfEdgeAdjacentFalses++;
		}
		else {
		    i = 99999999;
		}
	    }

	    if(numberOfEdgeAdjacentFalses > booleanVector.size() - 1) { //if all elements are true, this condition could happen
		numberOfEdgeAdjacentFalses = booleanVector.size() - 1;
	    }
	}
	
	if(numberOfEdgeAdjacentFalses > maximumNumberOfAdjacentFalses) {
	    maximumNumberOfAdjacentFalses = numberOfEdgeAdjacentFalses;
	}
	
	return maximumNumberOfAdjacentFalses;	
    }

    public static Vector<RhythmEvent> copyRhythmEventVector(Vector<RhythmEvent> original) {
	Vector<RhythmEvent> toReturn = new Vector<RhythmEvent>();
	toReturn.add(null);

	for(int i = 1; i < original.size(); i++) {
	    toReturn.add(original.elementAt(i).copyThisRhythmEvent());
	}

	return toReturn;
    }	    

    //===================================================================================================
    //-------------------------------Method Just for debugging-------------------------------------------
    //==================================================================================================
    public static void printOutBooleanVector(Vector<Boolean> a) {
	// Exclude the index 1, too

	for (int i = 1; i < a.size(); i++) {
	    if(a.elementAt(i) == true) {
		System.out.print("T ");
	    }
	    else {
		System.out.print("F ");
	    }
	}

	System.out.println();
    }
	
}

	    
