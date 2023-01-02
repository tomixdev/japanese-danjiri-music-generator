import java.util.Vector;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileReader;

//This class also checks symmmetricity!!!!!!!!!!!!!!!!!
public class ROUTChecker {
    private RhythmPattern rhythmPattern;
    
    private Vector<RhythmPattern> allPossibleRhythmPatterns;
    
    private Vector<Vector<Boolean>> allPossibleHitPatternsInBoolean;
    private Vector<Vector<Boolean>> allPossibleAccentPatternsInBoolean;
    //Vector<Vector<Boolean>> allPossibleTaikoEdgeHitPatternsInBoolean;
    
    //======================================================================
    //------------------------Constructor-----------------------------------
    //=====================================================================
    public ROUTChecker (RhythmPattern rhythmPattern) {
	this.rhythmPattern = rhythmPattern;////I may need to copy
	setAllPossibleRhythmPatterns();
    }

    //======================================================================
    //------------------------Public Methods--------------------------------
    //======================================================================
    public boolean isHitPatternROUT() throws FileNotFoundException{ // NOT YET TESTED!!!!!!!!!!!!!!!!!!!!
	// Return True When Only One Or Zero RhythmEvent is existing in the rhythm pattern
	if(allPossibleHitPatternsInBoolean == null) {
	    setAllPossibleHitPatternsInBoolean();
	}
	
	Vector<Boolean> hitPatternBoolean = rhythmPattern.translateHitPatternIntoBooleanVector();
	
	if(isROUT(allPossibleHitPatternsInBoolean) == true && condition2(hitPatternBoolean) == true && condition3(hitPatternBoolean) == true) {
	    return true;
	}
	else {
	    return false;
	}
    }

    public boolean isAccentPatternROUT() throws FileNotFoundException{// NOT YET TESTED!!!!!!!!!!!!!!!!!!!!!!!
	if(allPossibleAccentPatternsInBoolean == null) {
	    setAllPossibleAccentPatternsInBoolean();
	}

	Vector<Boolean> accentPatternBoolean = rhythmPattern.translateAccentPatternIntoBooleanVector();
	
	if(isROUT(allPossibleAccentPatternsInBoolean) == true && condition2(accentPatternBoolean) == true && condition3(accentPatternBoolean) == true) {
	    return true;
	}
	else {
	    return false;
	}
    }

    public boolean isRhythmPatternROUT() throws FileNotFoundException{ //Tested August 16
	if(this.isHitPatternROUT() && this.isAccentPatternROUT()) {
	    return true;
	}
        else {
	    return false;
	}
    }

    public boolean isHitPatternSymmetrical() throws FileNotFoundException{ //Tested August 16
	Vector<Boolean> hitPatternBoolean = rhythmPattern.translateHitPatternIntoBooleanVector();
	
	if(isSymmetrical(hitPatternBoolean) == true && condition4(hitPatternBoolean) == true) {
	    return true;
	}
	else {
	    return false;
	}
	    
    }

    public boolean isAccentPatternSymmetrical() throws FileNotFoundException{//Tested August 16
	Vector<Boolean> accentPatternBoolean = rhythmPattern.translateAccentPatternIntoBooleanVector();
	       
	if (isSymmetrical (accentPatternBoolean) == true && condition4(accentPatternBoolean) == true) {
	    return true;
	}
	else {
	    return false;
	}
    }

    public boolean isRhythmPatternSymmetrical() throws FileNotFoundException{//Tested August 16
	if(this.isHitPatternSymmetrical() && this.isAccentPatternSymmetrical()) {
	    return true;
	}
	else {
	    return false;
	}
    }

    public boolean isHitPatternSymmetricalROUT() throws FileNotFoundException{//NOT Tested!!!!! (Probably Okay) August 16
	if(this.isHitPatternROUT() & this.isHitPatternSymmetrical()) {
	    return true;
	}
	else {
	    return false;
	}
    }

    public boolean isRhythmPatternSymmetricalROUT() throws FileNotFoundException{ //NOT Tested!!!!! (Probably Okay) August 16
	if(isHitPatternSymmetricalROUT() && isAccentPatternROUT() && isAccentPatternSymmetrical()) {
	    return true;
	}
	else {
	    return false;
	}
    }		

    //========================================================================
    //---------------------------Private Methods------------------------------
    //========================================================================
    private void setAllPossibleRhythmPatterns() {
	allPossibleRhythmPatterns = new Vector<RhythmPattern>();
	for(int transposeByHowMany = 0; transposeByHowMany <= this.rhythmPattern.getNumberOfPulses() - 1; transposeByHowMany++) {
	    RhythmPattern toAdd = this.rhythmPattern.rhythmicallyTransposeAndCopy(transposeByHowMany);
	    allPossibleRhythmPatterns.add (toAdd);
	}   
    }

    private void setAllPossibleHitPatternsInBoolean() {
	allPossibleHitPatternsInBoolean = new Vector<Vector<Boolean>>();
	for (int i = 0; i < allPossibleRhythmPatterns.size(); i++) {
	    Vector<Boolean> toAdd = allPossibleRhythmPatterns.elementAt(i).translateHitPatternIntoBooleanVector();
	    allPossibleHitPatternsInBoolean.add(toAdd);
	}
    }

    private void setAllPossibleAccentPatternsInBoolean() {
	allPossibleAccentPatternsInBoolean = new Vector<Vector<Boolean>>();
	for (int i = 0; i < allPossibleRhythmPatterns.size(); i++) {
	    Vector<Boolean> toAdd = allPossibleRhythmPatterns.elementAt(i).translateAccentPatternIntoBooleanVector();
	    allPossibleAccentPatternsInBoolean.add(toAdd);
	}
    }

    private boolean isROUT(Vector<Vector<Boolean>> booleanVectors) {
	//NOTE!! Index 0 of all boolean Vectors Should Be Empty
	for(int i = 0; i < booleanVectors.size() - 1; i++) {
	    for (int j = i + 1; j < booleanVectors.size(); j++) {						    
		if(HelperMethods.areTwoBooleanVectorsIdenticalFromIndexOne(booleanVectors.elementAt(i), booleanVectors.elementAt(j))){
		    return false;
		}
	    }
	}

	return true;
    }

    private boolean condition2(Vector<Boolean> booleanVector) {

	int numberOfTrues = HelperMethods.getNumberOfTruesFromBooleanVector(booleanVector);
	int numberOfFalses = HelperMethods.getNumberOfFalsesFromBooleanVector(booleanVector);

	if(numberOfTrues <= numberOfFalses) {
	    if(HelperMethods.whetherTwoAdjacentTruesExist(booleanVector) == true) {
		return false;
	    }
	}
	else {		
	    if(HelperMethods.whetherTwoAdjacentFalsesExist(booleanVector) == true) {
		return false;
	    }
	}

	return true;
    }

    private boolean condition3(Vector<Boolean> booleanVector) throws FileNotFoundException{
	int numberOfTrues = HelperMethods.getNumberOfTruesFromBooleanVector(booleanVector);
	int numberOfPulses = booleanVector.size() - 1;

	if(numberOfTrues >= (int)(numberOfPulses * 1 / 3) && numberOfTrues <= (int)(numberOfPulses * 2 / 3)) {
	    return conditionA(booleanVector);
	}
	else {
	    return conditionB(booleanVector);
	}
    }

    private boolean conditionA (Vector<Boolean> booleanVector) throws FileNotFoundException {
	
	Vector<Vector<Integer>> beatPlaces = HelperMethods.getBeatPlaces(booleanVector.size() - 1);

	for (int i = 0; i < beatPlaces.size(); i++) {
	    int howManyBeats = beatPlaces.elementAt(i).size();
	    int howManyHitsAreOnBeats = 0;
	    
	    for (int j = 0; j < beatPlaces.elementAt(i).size(); j++) {
		int aBeatPosition = beatPlaces.elementAt(i).elementAt(j);
		if(booleanVector.elementAt(aBeatPosition) == true) {
		    howManyHitsAreOnBeats++;
		}
	    }

	    if(howManyBeats % 2 == 0) {
		if(howManyHitsAreOnBeats == (howManyBeats / 2)) {
		    //do nothing
		}
		else {
		    return false;
		}
	    }
	    else {
		if (howManyHitsAreOnBeats == (int)(howManyBeats / 2) || howManyHitsAreOnBeats ==  (int)(howManyBeats / 2) + 1) {
		    //do nothing
		}
		else {
		    return false;
		}
	    }
	}

	return true;
    }

    private boolean conditionB (Vector<Boolean> booleanVector) throws FileNotFoundException {
	if(conditionB1(booleanVector) == true && conditionB2(booleanVector) == true) {
	    return true;
	}
	else {
	    return false;
	}
    }

    private boolean conditionB1 (Vector<Boolean> booleanVector) throws FileNotFoundException {

	int numberOfPulses = booleanVector.size() - 1;
	int numberOfTrues = HelperMethods.getNumberOfTruesFromBooleanVector(booleanVector);
	int numberOfFalses = HelperMethods.getNumberOfFalsesFromBooleanVector(booleanVector);

	int maximumAdjacentTrues = HelperMethods.getMaximumNumberOfHowManyTruesAreAdjacent(booleanVector);
	int maximumAdjacentFalses = HelperMethods.getMaximumNumberOfHowManyFalsesAreAdjacent(booleanVector);

	int sanbunNoHachiOfNumberOfPulses = (int)(numberOfPulses * 3 / 8);
	if(numberOfPulses % 8 != 0) {
	    sanbunNoHachiOfNumberOfPulses = sanbunNoHachiOfNumberOfPulses + 1;
	}
	
	
	if(numberOfTrues >= numberOfFalses) {
	    if(maximumAdjacentTrues >= (int)(numberOfPulses * 3 / 8)) { /// the ratio 3 / 8 can be adjusted later!!!!August 18
		return false;
	    }
	}
        else {
	    if(maximumAdjacentFalses >= (int)(numberOfPulses * 3 / 8)) { // the ratio 3 / 8 can be adjusted later!!! August 18
		return false;
	    }
	}

	return true;
    }

    private boolean conditionB2 (Vector<Boolean> booleanVector) throws FileNotFoundException {
	
	Vector<Vector<Integer>> beatPlaces = HelperMethods.getBeatPlaces(booleanVector.size() - 1);
	int numberOfPulses = booleanVector.size() - 1;
	int numberOfTrues = HelperMethods.getNumberOfTruesFromBooleanVector(booleanVector);
	int numberOfFalses = HelperMethods.getNumberOfFalsesFromBooleanVector(booleanVector);
	    	    	    
	for(int i = 0; i < beatPlaces.size(); i++) {
	    int numberOfTruesOnBeat = 0;
	    int numberOfFalsesOnBeat = 0;
	    Vector<Integer> beatPositions = beatPlaces.elementAt(i);

	    for (int x = 1; x < booleanVector.size(); x++) {
		if(booleanVector.elementAt(x) == true && beatPositions.contains(x)) {
		    numberOfTruesOnBeat++;
		}
		else if(booleanVector.elementAt(x) == false && beatPositions.contains(x)){
		    numberOfFalsesOnBeat++;
		}
	    }

	    if(numberOfTrues <= numberOfFalses) {
		if(numberOfTrues % 2 == 0) {
		    if(numberOfTruesOnBeat == numberOfTrues / 2) {
			//do nothing
		    }
		    else {
			return false;
		    }
		}
		else {
		    if(numberOfTruesOnBeat == (int)(numberOfTrues / 2) || numberOfTruesOnBeat == (int)(numberOfTrues / 2) + 1) {
			//do nothing
		    }
		    else {
			return false;
		    }
		}		
	    }
	    else {// if numberOfFalses < numberOfTrues
		if(numberOfFalses % 2 == 0) {
		    if(numberOfFalsesOnBeat == numberOfFalses / 2) {
			//do nothing
		    }
		    else {
			return false;
		    }
		}
		else {
		    if(numberOfFalsesOnBeat == (int)(numberOfFalses / 2) || numberOfFalsesOnBeat == (int)(numberOfFalses / 2) + 1) {
			//do nothing
		    }
		    else {
			return false;
		    }
		}
	    }	    
	}
	    
	return true;		
    }
	
    private  boolean isSymmetrical (Vector<Boolean> booleanVector) {
	for(int i = 1; i < booleanVector.size(); i++) {
	    if (booleanVector.elementAt(i) != booleanVector.elementAt(booleanVector.size() - i)) {
		return false;
	    }
	}

	return true;
    }

    private boolean condition4 (Vector<Boolean> booleanVector) throws FileNotFoundException{
	return conditionB1(booleanVector);
    }
    //=======================================================================
    //------------------Public Methods For Debugging---------------------
    //======================================================================
    public RhythmPattern getRhythmPattern() {
	return rhythmPattern;
    }

    public Vector<RhythmPattern> getAllPossibleRhythmPatterns() {
	if(allPossibleRhythmPatterns == null) {
	    setAllPossibleRhythmPatterns();
	}
	return allPossibleRhythmPatterns;
    }

    public Vector<Vector<Boolean>> getAllPossibleHitPatternsInBoolean() {
	if(allPossibleHitPatternsInBoolean == null) {
	    setAllPossibleHitPatternsInBoolean();
	}
	return allPossibleHitPatternsInBoolean;
    }

    public Vector<Vector<Boolean>> getAllPossibleAccentPatternsInBoolean() {
	if(allPossibleAccentPatternsInBoolean == null) {
	    setAllPossibleAccentPatternsInBoolean();
	}
	return allPossibleAccentPatternsInBoolean;
    }

    public void printAllPossibleRhythmPatterns() {
	if(allPossibleRhythmPatterns == null) {
	    setAllPossibleRhythmPatterns();
	}
	for(int i = 0; i < allPossibleRhythmPatterns.size(); i++) {
	    System.out.println(allPossibleRhythmPatterns.elementAt(i));
	}
    }	

    public void printAllPossibleHitPatternsInBoolean() {
	if(allPossibleHitPatternsInBoolean == null) {
	    setAllPossibleHitPatternsInBoolean();
	}
	
	for(int i = 0; i < allPossibleHitPatternsInBoolean.size(); i++) {
	    for(int j = 1; j < allPossibleHitPatternsInBoolean.elementAt(i).size(); j++) {
		if(allPossibleHitPatternsInBoolean.elementAt(i).elementAt(j) == true) {
		    System.out.print("T ");
		}
		else {
		    System.out.print("F ");
		}
	    }
	    System.out.println();
	}
	System.out.println();
    }

    public void printAllPossibleAccentPatternsInBoolean() {
	if(allPossibleAccentPatternsInBoolean == null) {
	    setAllPossibleAccentPatternsInBoolean();
	}
	for(int i = 0; i < allPossibleAccentPatternsInBoolean.size(); i++) {
	    for(int j = 1; j < allPossibleAccentPatternsInBoolean.elementAt(i).size(); j++) {
		if(allPossibleAccentPatternsInBoolean.elementAt(i).elementAt(j) == true) {
		    System.out.print("T ");
		}
		else {
		    System.out.print("F ");
		}
	    }
	    System.out.println();
	}
	System.out.println();
    }
      
	    
    
}


//Gomi
/*
    private void setBooleanRhythmPattern() {
	booleanRhythmPattern = new Vector<Boolean>();
	for(int i = 1; i < rhythmPattern.size(); i++) {
	    booleanRhythmPattern.add(rhythmPattern.elementAt(i).getIsSound);
	}
    }

    private void setBooleanAccentPattern() {
	booleanAccentPattern = new Vector<Boolean>();
	for(int i = 1; i < rhythmPattern.size(); i++) {
	    booleanRhythmPattern.add(rhythmPattern.elemenatAt(i).getIsAccented);
	}
    }
*/
