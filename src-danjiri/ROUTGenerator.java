import java.util.Scanner;
import java.io.FileReader;
import java.util.Hashtable;
import java.util.Vector;
import java.lang.Double;
import java.lang.Boolean;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

//ROUT = Rhythm Of Unlimited Transposition
//This class also can generate symmetric rhythm!!!!!!
public class ROUTGenerator {

    private final int numberOfPulsesInACycle;
    private final int numberOfHitsInACycle;
    private final double minimum_PulsesToHits_Ratio;

    private Vector<Vector<Boolean>> combinationsOfAllPossibleRhythmPatternsInBoolean;
    private Vector<RhythmPattern> combinationsOfAllPossibleRhythmPatterns;
    
    private Vector<RhythmPattern> allPossibleROUTs;
    private Vector<RhythmPattern> allPossibleSymmetricalRhythms;
    private Vector<RhythmPattern> allPossibleSymmetricalROUTs;

    //=================================================================================================
    //-------------------------------------Constructors------------------------------------------------
    //=================================================================================================
    public ROUTGenerator (int numberOfPulsesInACycle, int numberOfHitsInACycle) throws FileNotFoundException{
	//Default Value For minimum-pulses-to-hits-ratio is 1/3  FREE TO CHANGE LATER
	this(numberOfPulsesInACycle, numberOfHitsInACycle, 1 / 3);
    }

    public ROUTGenerator (int numberOfPulsesInACycle, int numberOfHitsInACycle, double minimum_PulsesToHits_Ratio) throws FileNotFoundException{
	
	this.numberOfPulsesInACycle = numberOfPulsesInACycle;
	this.numberOfHitsInACycle = numberOfHitsInACycle;
	this.minimum_PulsesToHits_Ratio = minimum_PulsesToHits_Ratio;
	
        checkWhetherBalanceBetweenNumberOfPulsesAndHitsIsOkayOrNot(); //This Method Is Written At Bottom

	generateCombinationsOfAllPossibleRhythmPatternsInBoolean();
	setCombinationsOfAllPossibleRhythmPatterns();
	
	setAllPossibleROUTs();
	setAllPossibleSymmetricalRhythms();
	setAllPossibleSymmetricalROUTs();

	if(allPossibleROUTs.size() == 0) {
	    System.out.println("No ROUT can be made from the combination of " + numberOfPulsesInACycle + " pulses AND " + numberOfHitsInACycle + " hits");
	    System.out.println();
	}
	if(allPossibleSymmetricalRhythms.size() == 0) {
	    System.out.println("No symmetrical rhythm can be made from the combination of " + numberOfPulsesInACycle + " pulses AND " + numberOfHitsInACycle + " hits");
	    System.out.println();
	}
	if(allPossibleSymmetricalROUTs.size() == 0) {
	    System.out.println("No symmetrical ROUT can be made from the combination of " + numberOfPulsesInACycle + " pulses AND " + numberOfHitsInACycle + " hits");
	    System.out.println();
	}
    }

    //=====================================================================================================
    //--------------------------------public Methods-------------------------------------------------------
    //=====================================================================================================
    public RhythmPattern chooseAnROUTAndCopy (int whichROUT) {
	checkIfChoiceIsValid(allPossibleROUTs, whichROUT, "chooseAnROUTAndCopy()");
	return  allPossibleROUTs.elementAt(whichROUT).copyThisRhythmPatternObject();
    }

    public RhythmPattern chooseASymmetricalRhythmAndCopy(int whichSymmetricalRhythm) {
	checkIfChoiceIsValid(allPossibleSymmetricalRhythms, whichSymmetricalRhythm, "chooseASymmetricalRhythmAndCopy()");
	return allPossibleSymmetricalRhythms.elementAt(whichSymmetricalRhythm).copyThisRhythmPatternObject();
    }

    public RhythmPattern chooseASymmetricalROUTAndCopy(int whichSymmetricalROUT) {
	checkIfChoiceIsValid (allPossibleSymmetricalROUTs, whichSymmetricalROUT, "chooseASymmetricalROUTAndCopy()");
	return allPossibleSymmetricalROUTs.elementAt(whichSymmetricalROUT).copyThisRhythmPatternObject();
    }
    
    public RhythmPattern randomlyChooseAnROUTAndCopy(int randomSeed) {
	if(allPossibleROUTs == null || allPossibleROUTs.elementAt(0) == null) {
	    System.out.println("No ROUT can be made from " + numberOfPulsesInACycle + " pulses and " + numberOfHitsInACycle + " hits.");
	    System.out.println("Exit from randomlyChooseAnROUTAndCopy() in ROUTGenerator class");
	    System.exit(1);
	}
	
	Random rand = new Random (randomSeed);
	int choice = rand.nextInt(allPossibleROUTs.size());
	RhythmPattern toReturn = this.chooseAnROUTAndCopy(choice);
	
	//toReturn.randomlyTransposeAndReplace(rand.nextInt(numberOfPulsesInACycle));

	return toReturn;
    }

    public RhythmPattern randomlyChooseASymmetricalRhythmAndCopy (int randomSeed) {
	if(allPossibleSymmetricalRhythms == null || allPossibleSymmetricalRhythms.elementAt(0) == null) {
	    System.out.println("No Symmetrical rhythm can be made from " + numberOfPulsesInACycle + " pulses and " + numberOfHitsInACycle + " hits.");
	    System.out.println("Exit from randomlyChooseASymmetricalRhythmAndCopy() in ROUTGenerator class");
	    System.exit(1);
	}
	
	Random rand = new Random (randomSeed);
	int choice = rand.nextInt(allPossibleSymmetricalRhythms.size());
	return this.chooseASymmetricalRhythmAndCopy(choice);
    }

    public RhythmPattern randomlyChooseASymmetricalROUTAndCopy (int randomSeed) {
	if(allPossibleSymmetricalROUTs == null || allPossibleSymmetricalROUTs.elementAt(0) == null) {
	    System.out.println("No Symmetrical ROUT can be made from " + numberOfPulsesInACycle + " pulses and " + numberOfHitsInACycle + " hits.");
	    System.out.println("Exit from randomlyChooseASymmetricalROUTAndCopy() in ROUTGenerator class");
	    System.exit(1);
	}
	
	Random rand = new Random (randomSeed);
	int choice = rand.nextInt(allPossibleSymmetricalROUTs.size());
	return this.chooseASymmetricalROUTAndCopy(choice);
    }
	
    public void writeAllPossibleROUTsToFile() throws FileNotFoundException{
	String fileName = "All ROUTs of " + numberOfPulsesInACycle + " pulses AND " + numberOfHitsInACycle + " hits.txt";
	writeToFile(allPossibleROUTs, fileName);
    }

    public void writeAllPossibleSymmetricalRhythmsToFile() throws FileNotFoundException{
	String fileName = "All symmetrical rhythms of " + numberOfPulsesInACycle + " pulses AND " + numberOfHitsInACycle + " hits.txt";
	writeToFile(allPossibleSymmetricalRhythms, fileName);
    }

    public void writeAllPossibleSymmetricalROUTsToFile() throws FileNotFoundException{
	String fileName = "All symmetrical ROUTs of " + numberOfPulsesInACycle + " pulses AND " + numberOfHitsInACycle + " hits.txt";
	writeToFile(allPossibleSymmetricalROUTs, fileName);
    }

    public void writeAnROUTToFile (int choice) throws FileNotFoundException{
	checkIfChoiceIsValid(allPossibleROUTs, choice, "writeAnROUTTofile()");
	String nameOfRhythmPattern = "ROUT No." + choice + " from " + numberOfPulsesInACycle + " pulses AND " + numberOfHitsInACycle + " hits.txt";
	allPossibleROUTs.elementAt(choice).saveRhythmPatternToFile(nameOfRhythmPattern);
    }

    public void writeASymmetricalRhythmToFile(int choice) throws FileNotFoundException{
	checkIfChoiceIsValid(allPossibleSymmetricalRhythms,choice, "writeASymmetricalRhythmToFile");
	String nameOfRhythmPattern = "Symmetrical Rhythm No." + choice + " from " + numberOfPulsesInACycle + " pulses AND " + numberOfHitsInACycle + " hits.txt";
	allPossibleSymmetricalRhythms.elementAt(choice).saveRhythmPatternToFile(nameOfRhythmPattern);
    }

    public void writeASymmetricalROUTToFile(int choice) throws FileNotFoundException{
	checkIfChoiceIsValid(allPossibleSymmetricalROUTs, choice, "writeASymmetricalROUTToFile");
	String nameOfRhythmPattern = "Symmetrical ROUT No." + choice + " from " + numberOfPulsesInACycle + " pulses AND " + numberOfHitsInACycle + " hits.txt";
	allPossibleSymmetricalROUTs.elementAt(choice).saveRhythmPatternToFile(nameOfRhythmPattern);
    }

    private void writeToFile(Vector<RhythmPattern> rhythmPatterns, String fileName) throws FileNotFoundException {
	PrintWriter outfile = new PrintWriter("./PossibleRhythmPatternsListTextFiles/" + fileName);
	String rhythmPatternsName = (String)fileName.subSequence(0, fileName.length() - 4);// delete ".txt"
	outfile.println(rhythmPatternsName);
	for (int i = 0; i < rhythmPatterns.size(); i++) {
	    outfile.println("No." + i + " " + rhythmPatterns.elementAt(i));
	}

	outfile.close();
    }

    //===========================================================================================================
    //----------------------------------Private Methods----------------------------------------------------------
    //===========================================================================================================  
    private void checkIfChoiceIsValid(Vector<RhythmPattern> rhythmPatternVector, int choice, String fromWhatMethod) {
	if(rhythmPatternVector.elementAt(0) == null || rhythmPatternVector == null) {
	    System.out.println("There is no element in the rhythm pattern vector used in " + fromWhatMethod + " in ROUTGenerator.");
	    System.exit(1);
	}

	if (choice >= allPossibleROUTs.size() || choice < 0) {
	    System.out.println("(Choice No." + choice + ") There is no such a choice in the rhythm pattern vector used in " + fromWhatMethod + " in ROUT Generator.");
	    System.exit(1);
	}
    }
    
    private void generateCombinationsOfAllPossibleRhythmPatternsInBoolean () {
	Vector<Vector<Integer>> allPossibleNumberCombinations = HelperMethods.calculateAllPossibleNumberCombinations(numberOfPulsesInACycle, numberOfHitsInACycle);

	combinationsOfAllPossibleRhythmPatternsInBoolean = new Vector<Vector<Boolean>>();
	//combinationsOfAllPossibleRhythmPatternsInBoolean.setSize(HelperMethods.Combination(numberOfPulsesInACycle, numberOfHitsInACycle) + 1);///?????Why Need this??? Difference between set() and add(). capacity and size	
	
	for (int i = 0; i < allPossibleNumberCombinations.size(); i++) {
	    Vector<Boolean> booleanVector = new Vector<Boolean>();
	    booleanVector.setSize(numberOfPulsesInACycle + 1);
	    assignAllSpotsFalseExceptForZerothSpot(booleanVector);
	    
	    for(int j = 0; j < allPossibleNumberCombinations.elementAt(i).size(); j++) {
		booleanVector.set(allPossibleNumberCombinations.elementAt(i).elementAt(j), true);
	    }

	    combinationsOfAllPossibleRhythmPatternsInBoolean.add(booleanVector);
	}
    }

    private void assignAllSpotsFalseExceptForZerothSpot(Vector<Boolean> a) {
	for (int i = 1; i < a.size(); i++) {
	    a.set(i, false);
	}
    }

    private void setCombinationsOfAllPossibleRhythmPatterns() {
	combinationsOfAllPossibleRhythmPatterns = new Vector<RhythmPattern>();
	
	for (int i = 0; i < combinationsOfAllPossibleRhythmPatternsInBoolean.size(); i++) {
	    
	    RhythmPattern newRhythmPatternToAdd = new RhythmPattern(combinationsOfAllPossibleRhythmPatternsInBoolean.elementAt(i).size() - 1);

	    
	    for (int j = 0; j < combinationsOfAllPossibleRhythmPatternsInBoolean.elementAt(i).size(); j++) {
		//System.out.println(combinationsOfAllPossibleRhythmPatternsInBoolean.elementAt(i));
		//System.out.println(combinationsOfAllPossibleRhythmPatternsInBoolean.elementAt(i).elementAt(j));
		if(combinationsOfAllPossibleRhythmPatternsInBoolean.elementAt(i).elementAt(j) != null && combinationsOfAllPossibleRhythmPatternsInBoolean.elementAt(i).elementAt(j)== true) {
		    newRhythmPatternToAdd.setHitAt(j, true);
		}
	    }

	    combinationsOfAllPossibleRhythmPatterns.add(newRhythmPatternToAdd);
	}
    }
    
    private void setAllPossibleROUTs() throws FileNotFoundException{
	allPossibleROUTs = new Vector<RhythmPattern>();
	
	for (int i = 0; i < combinationsOfAllPossibleRhythmPatterns.size(); i++) {
	    RhythmPattern examinedRhythmPattern = combinationsOfAllPossibleRhythmPatterns.elementAt(i);

	    ROUTChecker _ROUTChecker = new ROUTChecker (examinedRhythmPattern);
	    if(_ROUTChecker.isHitPatternROUT()) {
		allPossibleROUTs.add(examinedRhythmPattern);
	    }
	}
    }

    private void setAllPossibleSymmetricalRhythms() throws FileNotFoundException {
	allPossibleSymmetricalRhythms = new Vector<RhythmPattern>();
	for  (int i = 0; i < combinationsOfAllPossibleRhythmPatterns.size(); i++) {
	    RhythmPattern examinedRhythmPattern = combinationsOfAllPossibleRhythmPatterns.elementAt(i);
	    ROUTChecker _ROUTChecker = new ROUTChecker (examinedRhythmPattern);
	    if(_ROUTChecker.isHitPatternSymmetrical()){
		allPossibleSymmetricalRhythms.add(examinedRhythmPattern);
	    }
	}
    }

    private void setAllPossibleSymmetricalROUTs() throws FileNotFoundException{
	allPossibleSymmetricalROUTs = new Vector<RhythmPattern>();
	for (int i = 0; i < combinationsOfAllPossibleRhythmPatterns.size(); i++) {
	    RhythmPattern examinedRhythmPattern = combinationsOfAllPossibleRhythmPatterns.elementAt(i);
	    ROUTChecker _ROUTChecker = new ROUTChecker (examinedRhythmPattern);
	    if(_ROUTChecker.isHitPatternSymmetricalROUT()) {
		allPossibleSymmetricalROUTs.add(examinedRhythmPattern);
	    }
	}
    }
	
    private void checkWhetherBalanceBetweenNumberOfPulsesAndHitsIsOkayOrNot() {
	double minimumNumberOfHitsRequired = numberOfPulsesInACycle * minimum_PulsesToHits_Ratio;

	if(numberOfHitsInACycle >= numberOfPulsesInACycle) {
	    System.out.println("numberOfHitsInACycle >= numberOfPulsesInACycle !!!!! WRONG!!!!");
	    System.exit(1);
	}
	else if(numberOfHitsInACycle < minimumNumberOfHitsRequired) {
	    System.out.println("Balance between number of pulses and hits is not appropriate to generate good rhythm of unlimited transposition");
	    System.exit(1);
	}
	else {
	    //Do Nothing
	}
    }

    //====================================================================================================
    //---------------------------------METHODS FOR DEBUGGING----------------------------------------------
    //====================================================================================================
    public void displayCombinationsOfAllPossibleRhythmPatterns () {
	for (int i = 1; i < combinationsOfAllPossibleRhythmPatternsInBoolean.size(); i++) {
	    for (int j = 1; j < combinationsOfAllPossibleRhythmPatternsInBoolean.elementAt(i).size(); j++) {
		if (combinationsOfAllPossibleRhythmPatternsInBoolean.elementAt(i).elementAt(j) == true) {
		    System.out.print ("X ");
		}
		else {
		    System.out.print("- ");
		}
	    }

	    System.out.println();
	}
    }

    public void displayAllPossibleROUTs() {
	for (int i = 1; i < allPossibleROUTs.size(); i++) {
	    System.out.println(allPossibleROUTs.elementAt(i));
	}
    }
    
    public void displayAllTranspositionsOfAnROUT (int whichROUTToTranspose) {
	checkIfChoiceIsValid(allPossibleROUTs, whichROUTToTranspose, "displayAllTranspositionsOfAnROUT()");
        RhythmPattern selectedRhythmPattern = allPossibleROUTs.elementAt(whichROUTToTranspose);
	
	for (int i = 0; i <= numberOfPulsesInACycle - 1; i++) {
	    System.out.println(selectedRhythmPattern.rhythmicallyTransposeAndCopy(i));
	}
    }
}

///--------------------------------------GOMI-------------------------------------------------
/*


    public randomlyChooseAnROUTAndCopy () {
	this.randomlyChooseAnROUTAndCopy(10);
    }




	this.combinationOfAllPossibleRhythmPatterns = new Vector<new Vector<Boolean> (this.numberOfHitsInACycle + 1)>(HelperMethods.Combination(numberOfPulsesInACycle, numberOfHitsinACycle) + 1);
combinationsOfAllPossibleRhythmPatterns<
	

	counterForLastNodes = 0;
	
	for(int i = 1; i <= combinationOfAllPossibleRhythmPatterns.size() - 1; i++) {
	    combinationOfAllPossibleRhythmPatterns.elementAt(i).add(lastNodes.elementAt(counterForLastNodes).currentInt;
								    
	
	IntegerCombinationNode dummy = new IntegerCombinationNode();
	dummy.previousNode = null;
	dummy.currentInt = null;
	
	dummy.buildCombinationTree(lastNodes);	
  

    }

    private void buildCobinationTree() {

	
	buildCombinationTree(dummy);
    }

    private 



	
	for (int i = 1; i <= numberOfPulsesInACycle - numberOfHitsInACycle + 1; i++) {
	    IntegerCombinationNode next = new IntegerCombinationNode();
	    next.currentInt = i;
	    next.previousNode = current;
	    
	}
	    

	Vector<Vector<Integer>> possibleNumberCombinations = new Vector<new Vector<Integer>(numberOfHitsInACycle + 1)>();
	placeNumbers(possibleNumberCombinations);
    }

    private void placeNumbers(Vector<Vector<Integer>> possibleNumberCombinations) {
	
	

	    
	placeHits(1, 1, 0);
    }

    private void placeHits(int placeOfCurrentBooleanVector, int spotToPutFirstHit, int numberOfHitsAlreadyPut) {

	if(numberOfHitsAlreadyPut == numberOfHitsInACycle) {
	    return;
	}
	
	for(int i = spotToToPutFirstHit; i <= numberOfPulsesInACycle - numberOfHitsInACycle + numberOfHitsAlreadyput) {
	    combinationOfAllPossibleRhythmPatterns.elementAt(placeOfCurrentBooleanVector).add(i, true);
	    placeOfCurrentBooleanVector = placeOfCurrentBooleanVector + 1;
	    placeHits(i + 1, nuberOfHitsAlreadyPut + 1);
	}
    }
	    
	    placeTrueToSpot(i);
	    for (int j = i + 1; int j = numberOfPulsesInACycle - numberOfHitsInACycle + 1) {
		placeTrueToSpot(j);
		for(int k = j + 1; int k = numberOfPulsesinACycle - numberOfHitsInACycle + 2) {
		    placeTrueToSpot(k);
		}
	    }
	}
    }
*/
    
    

	
	
	



















































    


    

    

    
	

       
