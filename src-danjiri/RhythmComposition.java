import java.util.Scanner;
import java.io.FileReader;
import java.util.Hashtable;
import java.util.Vector;
import java.lang.Double;
import java.lang.Boolean;
import java.lang.String;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class RhythmComposition {
    private final String nameOfComposition;
    private String[] orderOfParts;
    private int currentTempo;
    private Vector<Integer> tempoSequence;// Index 0 is Empty!
    private final Random rand;
    private Hashtable<String, RhythmPart> rhythmParts; // Usually, use this one. Contain the same RhythmPart obejcts with rhythmPartsVector
    private Vector<RhythmPart> rhythmPartsVector; // Use this when I need to operate across parts. Contain the same RhythmPart objects with rhythmParts (Hashtable)

    //the arry of part names need to be in the order of parts from top to bottom
    public RhythmComposition (String nameOfComposition, String[] partNames, int tempoAtBeginning, int randomSeed) {
	this.nameOfComposition = nameOfComposition;
	this.orderOfParts = partNames; ///may need to copy!!!!!!!!!!!
	this.currentTempo = tempoAtBeginning;
	this.tempoSequence = new Vector<Integer>();
	this.tempoSequence.add(null);
	this.rand = new Random(randomSeed);
	
	this.rhythmParts = new Hashtable<String, RhythmPart>();
	for(int i = 0; i < partNames.length; i++) {
	    RhythmPart aNewPart = new RhythmPart(partNames[i], rand, this);
	    rhythmParts.put(partNames[i], aNewPart);
	}

	this.rhythmPartsVector = new Vector<RhythmPart>();
	for(int i = 0; i < partNames.length; i++) {
	    rhythmPartsVector.add(rhythmParts.get(partNames[i]));
	}	    
    }

    public Hashtable<String, RhythmPart> getRhythmPartsHashtable() {
	return rhythmParts;
    }

    public Vector<RhythmPart> getRhythmPartsVector() {
	return rhythmPartsVector;
    }

    public void saveThisCompositionToFile () {
	try {
	    PrintWriter outfile = new PrintWriter("./CompositionTextFiles/" + nameOfComposition + ".txt");

	    outfile.print("               TEMPO "); //In total 20 spaces
	    String toPrint0 = Integer.toString(tempoSequence.elementAt(1));
	    toPrint0 = String.format("%-4s", toPrint0);
	    outfile.print(toPrint0);
	    for(int i = 3; i < tempoSequence.size(); i++) {
		if(tempoSequence.elementAt(i) == tempoSequence.elementAt(i - 1)) {
		    outfile.print("- ");
		}
		else {
		    String toPrint = Integer.toString(tempoSequence.elementAt(i));
		    toPrint = String.format("%-4s", toPrint);
		    outfile.print(toPrint);
		    i = i + 1;
		}
	    }
	    outfile.println();

	    outfile.print("        Pulse Number ");//In total 20spaces
	    outfile.print("1       ");
	    for(int i = 2; i < tempoSequence.size();i++) {
		if(i % 5 == 0) {
		    String toPrint = Integer.toString(i);
		    toPrint = String.format("%-10s", toPrint);
		    outfile.print(toPrint);
		}
	    }
	    outfile.println();
	    	    
	    for(int i = 0; i < orderOfParts.length; i++) {
		outfile.println(rhythmParts.get(orderOfParts[i]));
	    }
	    
	    outfile.close();

	    System.out.println("SaveToFile Success!!");
	}
	catch (java.io.FileNotFoundException e) {
	    System.out.println ("Folder Name or file name in printOutThisComposition() in RhythmComposition class is wrong!");
	    System.exit(1);
	}       
    }

    public void changeTempo(int toWhichTempo) {
	this.currentTempo = toWhichTempo;
    }
    
    public void changeTempoBetween (int toWhichTempo, int fromWhichPulse, int toWhichPulse) {
        checkIfSuchAPulseExists(fromWhichPulse);
	checkIfSuchAPulseExists(toWhichPulse);
	for(int i = fromWhichPulse; i <= toWhichPulse; i++) {
	    tempoSequence.setElementAt(toWhichTempo, i);
	}
    }

    public void changeTempoFrom (int toWhichTempo, int fromWhichPulse) {
	checkIfSuchAPulseExists(fromWhichPulse);
	for(int i = fromWhichPulse; i < tempoSequence.size(); i++) {
	    tempoSequence.setElementAt(toWhichTempo, i);
	}
    }
    
    public void lineUpAllPartsWithHit() {
	RhythmEvent designatedRhythmEvent = new RhythmEvent(true, false);
	lineUpAllPartsWithDesignatedRhythmEvent(designatedRhythmEvent);
    }

    public void lineUpAllPartsWithAccent() {
	RhythmEvent designatedRhythmEvent = new RhythmEvent(true, true);
	lineUpAllPartsWithDesignatedRhythmEvent(designatedRhythmEvent);
    }

    public void lineUpAllPartsWithRest() {
	RhythmEvent designatedRhythmEvent = new RhythmEvent(false, false);
	lineUpAllPartsWithDesignatedRhythmEvent(designatedRhythmEvent);
    }

    private void lineUpAllPartsWithDesignatedRhythmEvent(RhythmEvent designatedRhythmEvent) {
	//checkIfNumberOfPulsesAreTooDifferentAcrossParts();
	//STEP2: if not, fill in the differeces among parts
	int fillInTillWhere = getMaximumNumberOfPulsesAcrossParts();
	for(int i = 0; i < rhythmPartsVector.size(); i++) {
	    RhythmPart aRhythmPart = rhythmPartsVector.elementAt(i);
	    for(int j = aRhythmPart.getNumberOfPulsesSoFar() + 1; j <= fillInTillWhere; j++) {
		aRhythmPart.addRhythmicComponent(designatedRhythmEvent.copyThisRhythmEvent());
	    }
	}
    }
		
    public void lineUpAllPartsWithRandomRhythmEvent() {
	//checkIfNumberOfPulsesAreTooDifferentAcrossParts();
	int fillInTillWhere = getMaximumNumberOfPulsesAcrossParts();
	for(int i = 0; i < rhythmPartsVector.size(); i++) {
	    RhythmPart aRhythmPart = rhythmPartsVector.elementAt(i);
	    for(int j = aRhythmPart.getNumberOfPulsesSoFar() + 1; j <= fillInTillWhere; j++) {
		RhythmEvent toAdd = new RhythmEvent();
		int randomNum = rand.nextInt(3);
		if(randomNum == 1 || randomNum == 2) {
		    toAdd.randomlyModifyAndReplace(rand.nextInt());
		}
		aRhythmPart.addRhythmicComponent(toAdd);
	    }
	}
    }
    
    //This method print out approximate seconds! Becasuse converting double to int
    public void printOutTotalTimeSoFarToTerminal() {
	int totalSecondsSoFarInt = (int)this.totalSecondsSoFar();
	System.out.println("Total time so far (Kiriste!) is " + totalSecondsSoFarInt + " seconds, or " + (int)(totalSecondsSoFarInt / 60) + " minutes " + totalSecondsSoFarInt % 60 + " seconds.");
    }

    public void fillInTempoSequence() {
	int fillInTillWhatPulse = getMaximumNumberOfPulsesAcrossParts();
	for(int i = (tempoSequence.size() - 1) + 1; i <= fillInTillWhatPulse; i++) {
	    tempoSequence.add(currentTempo);
	}
    }
    
    //=========================================================================================================
    //-------------------------------------Probably Useless Public Methods-------------------------------------
    //========================================================================================================
    public void printOutNumberOfPulsesAndRhythmicComponentsToTerminal(String partName) {
	System.out.println("The number of pulses so far in " + partName + " is: " + rhythmParts.get(partName).getNumberOfPulsesSoFar());
	System.out.println("The number of rhythmic components so far in " + partName + " is: " + rhythmParts.get(partName).getNumberOfRhythmicComponentsSoFar());
    }

    public void addRhythmicComponent (String partName, RhythmicComponent toAdd) {
	rhythmParts.get(partName).addRhythmicComponent(toAdd);
	fillInTempoSequence();
    }
    
    //randomize1 = randomize by pulse
    public void randomize1Gaussian(String partName, int fromWhichPulse, int toWhichPulse, int averageRandomFrequency) {
	rhythmParts.get(partName).randomize1Gaussian(fromWhichPulse, toWhichPulse, averageRandomFrequency);
    }

    //randomize2 = randomize by rhythm pattern
    public void randomize2Gaussian(String partName, int fromWhichRhythmicComponent, int toWhichRhythmicComponent, int averageRandomFrequency) throws FileNotFoundException{
	rhythmParts.get(partName).randomize2Gaussian(fromWhichRhythmicComponent, toWhichRhythmicComponent, averageRandomFrequency);	
    }

    //=========================================================================================================
    //----------------------------Private Methods--------------------------------------------------------------
    //=========================================================================================================
    private void  checkIfSuchAPulseExists(int pulseNum) {
	if(pulseNum > tempoSequence.size() - 1) {
	    System.out.println("Pulse No." + pulseNum + " does not exist! Exit from checkIfSuchAPulseExists()");
	    System.exit(1);
	}
    }
    
    // The method below does not line up all parts. So, the total seconds returned from this method is the total time of the longest part
    private double totalSecondsSoFar() {
	double toReturn = 0;
	for(int i = 1; i < this.tempoSequence.size(); i++) {
	    toReturn = toReturn + (60.0 / tempoSequence.elementAt(i));
	}
	return toReturn;
    }

    private void checkIfNumberOfPulsesAreTooDifferentAcrossParts() {
	int maximumNumberOfPulsesAcrossParts = getMaximumNumberOfPulsesAcrossParts();
	int minimumNumberOfPulsesAcrossParts = getMinimumNumberOfPulsesAcrossParts();
	
	if(maximumNumberOfPulsesAcrossParts - minimumNumberOfPulsesAcrossParts > 10) {
	    System.out.println("Number of pulses are different more than 10 across parts. So, you cannot ise lineUpAllParts(). Now the largest part contains " + maximumNumberOfPulsesAcrossParts + " pulses, while the smallest part only contains " + minimumNumberOfPulsesAcrossParts + " pulses.");
	    System.out.println("If you would like to proceed, press y. If not, press n");
	    
	    Scanner keyboard = new Scanner(System.in);
	    String yOrN = keyboard.nextLine();

	    while(!yOrN.equals("y") && !yOrN.equals("n")){
		System.out.println("Please press y or n!!!");
		yOrN = keyboard.nextLine();
	    }

	    if(yOrN.equals ("n")) {
		System.out.println("Exit from lineUpAllParts() in RhythmComposition Class");
		System.exit(1);
	    } 
	}

	System.out.println();
    }
    
    private int getMaximumNumberOfPulsesAcrossParts() {
	int toReturn = rhythmPartsVector.elementAt(0).getNumberOfPulsesSoFar();
	for(int i = 1; i < rhythmPartsVector.size(); i++) {
	    if(rhythmPartsVector.elementAt(i).getNumberOfPulsesSoFar() > toReturn) {
		toReturn = rhythmPartsVector.elementAt(i).getNumberOfPulsesSoFar();
	    }
	}
	return toReturn;
    }

    private int getMinimumNumberOfPulsesAcrossParts() {
	int toReturn = rhythmPartsVector.elementAt(0).getNumberOfPulsesSoFar();
	for(int i = 1; i <rhythmPartsVector.size(); i++) {
	    if(rhythmPartsVector.elementAt(i).getNumberOfPulsesSoFar() < toReturn) {
		toReturn = rhythmPartsVector.elementAt(i).getNumberOfPulsesSoFar();
	    }
	}
	return toReturn;
    }
}






    /*
    public void randomize1ChangingFrequency (String partName, int fromWhichPulse, int toWhichPulse, int originalRandomFrequency, int goalRandomFrequency){
    }

    public void randomize2ChangingFrequency (String partName, int fromWhichRhythmicComponent, int toWhichRhythmicComponent, int originalRandomFrequency, int goalRandomFrequency) {
    }

    public void intensify1 (String partName, int fromWhichPulse, int toWhichPulse) {
    }

    public void abate1 (String partName, int fromWhichPulse, int toWhichPulse) {
    }
    */

