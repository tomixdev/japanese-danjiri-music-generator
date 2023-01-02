import java.util.Random;
import java.io.FileNotFoundException;
import java.util.Vector;
import java.lang.Math;

public class RhythmPart {
    private final String partName;
    private final Random rand;
    private Vector<RhythmicComponent> rhythmSequence; // Index 0  is Empty
    private final RhythmComposition belongingWork;

    public RhythmPart (String partName, Random rand, RhythmComposition toBelong) {
	this.partName = partName;
	this.rand = rand;
	rhythmSequence = new Vector<RhythmicComponent>();
	rhythmSequence.add(null);
	this.belongingWork = toBelong;
    }

    //correspond to constructor 2
    public void addRhythmPattern (int numberOfPulses, int numberOfHits) throws FileNotFoundException{
        RhythmPattern toAdd = new RhythmPattern(numberOfPulses, numberOfHits, rand.nextInt());
	addRhythmicComponent(toAdd);
    }

    //correspond to constructor 3
    public void addRhythmPattern (String nameOfRhythmPattern) {
	RhythmPattern toAdd = new RhythmPattern(nameOfRhythmPattern);
	this.addRhythmicComponent(toAdd);
    }

    //correspond to construtor 4
    public void addRhythmPattern (String fileName, int choice) {
	RhythmPattern toAdd = new RhythmPattern(fileName, choice);
	this.addRhythmicComponent(toAdd);
    }

    //correspond to constructor 5
    public void addRhythmPattern (RhythmPattern toAdd) {
	RhythmPattern toAddCopied = new RhythmPattern(toAdd);
	this.addRhythmicComponent(toAddCopied);
    }

    public void addRhythmEvent(char rhythmEventChar) {
	RhythmEvent toAdd = new RhythmEvent(rhythmEventChar);
	addRhythmicComponent(toAdd);
    }

    public void addRestPulses(int howMany) {
	for(int i = 1; i <= howMany; i++) {
	    RhythmEvent toAdd = new RhythmEvent(false, false);
	    addRhythmicComponent(toAdd);
	}
    }

    // The following method add rest pulses whose length is equivalent to RhythmPattern a.
    //For example, if the number of pulses of RhythmPattern a is 4, then, add 4 rest pulses.
    public void addRestPulses(RhythmPattern a) {
	addRestPulses(a.getNumberOfPulses());
    }

    public void addRestPulses(String rhythmPatternName) throws FileNotFoundException{
	RhythmPattern temp = new RhythmPattern(rhythmPatternName);
	addRestPulses(temp.getNumberOfPulses());
    }

    public void randomize1Gaussian(int fromWhichPulse, int toWhichPulse, int averageRandomFrequency) {
	checkIfDesignatedPulseIsWithinRange(fromWhichPulse, "randomize1Gaussian()");
	checkIfDesignatedPulseIsWithinRange(toWhichPulse, "randomize1Gaussian()");
	
	this.getRhythmEventAt(fromWhichPulse).randomlyModifyAndReplace(rand.nextInt());

	int i = fromWhichPulse;
	while(i <= toWhichPulse && i <= this.getNumberOfPulsesSoFar()) {	    
	    int intervalOfRandomness = calculateNextIntervalOfRandomnessGaussian(averageRandomFrequency);
	    if(i + intervalOfRandomness <= toWhichPulse && i + intervalOfRandomness <= this.getNumberOfPulsesSoFar()) {
		this.getRhythmEventAt(i + intervalOfRandomness).randomlyModifyAndReplace(rand.nextInt());
	    }
	    i = i + intervalOfRandomness;
	}	    
    }

    public void randomize2Gaussian(int fromWhichRhythmPattern, int toWhichRhythmPattern, int averageRandomFrequency) throws FileNotFoundException{
	checkIfDesignatedRhythmicComponentExists(fromWhichRhythmPattern, "randomize2Gaussian()");
	checkIfDesignatedRhythmicComponentExists(toWhichRhythmPattern, "randomize2Gaussian()");

	System.out.println(rhythmSequence.elementAt(fromWhichRhythmPattern));
	this.rhythmSequence.elementAt(fromWhichRhythmPattern).randomlyModifyAndReplace(rand.nextInt());
	System.out.println(rhythmSequence.elementAt(fromWhichRhythmPattern));

	int i = fromWhichRhythmPattern;
	while(i <= toWhichRhythmPattern && i < rhythmSequence.size()) {
	    int intervalOfRandomness = calculateNextIntervalOfRandomnessGaussian(averageRandomFrequency);
	    if(i + intervalOfRandomness <= toWhichRhythmPattern && i + intervalOfRandomness < rhythmSequence.size()) {
		this.rhythmSequence.elementAt(i + intervalOfRandomness).randomlyModifyAndReplace(rand.nextInt());
	    }
	    i = i + intervalOfRandomness;
	}
    }

    //======================================================================================================
    //--------------------Public Methods which I will not probably use directly-----------------------------
    //======================================================================================================
    public String toString () {
	String toReturn = partName;
	toReturn = String.format("%20s", toReturn);
	toReturn = toReturn + " ";
	for(int i = 1; i < rhythmSequence.size(); i++) {
	    toReturn = toReturn + rhythmSequence.elementAt(i).toString();// maybe need  + " "
	}
	return toReturn;
    }
    
    public void addRhythmicComponent (RhythmicComponent toAdd) {
	if(toAdd instanceof RhythmEvent) {
	    RhythmEvent rhythmEventToAdd = (RhythmEvent)toAdd;
	    rhythmEventToAdd = rhythmEventToAdd.copyThisRhythmEvent();
	    rhythmSequence.add(rhythmEventToAdd);
	}
	if(toAdd instanceof RhythmPattern) {
	    RhythmPattern rhythmPatternToAdd = (RhythmPattern)toAdd;
	    rhythmPatternToAdd = rhythmPatternToAdd.copyThisRhythmPatternObject();
	    rhythmSequence.add(rhythmPatternToAdd);	    
	}

	belongingWork.fillInTempoSequence();
    }
 
    public void augmentedlyImitate(RhythmPart rhythmPartToImitate, int imitateFromWhatPulse, int imitateToWhatPulse, int behindHowManyPulses, int canonRatio){

	RhythmPattern rhythmPatternToAdd = new RhythmPattern(0);

	for(int i = imitateFromWhatPulse; i <= imitateToWhatPulse; i++) {
	    RhythmEvent aRhythmEvent = rhythmPartToImitate.getRhythmEventAt(i);
	    rhythmPatternToAdd.addRhythmEventAndReplace(aRhythmEvent);
	}

        if(canonRatio >= 1) {
	    rhythmPatternToAdd.augmentAndReplace(canonRatio);
	}
	else {
	    System.out.println("Canon Ratio (" + canonRatio + ") is less than 1!! Illegal. Exit from augmentedlyImitate() in RhythmPart class");
	    System.exit(1);
	}

	for(int i = 1; i <= behindHowManyPulses; i++) {
	    rhythmPatternToAdd.insertRestPulseAtTheTopAndReplace();
	}
        
	this.addRhythmicComponent(rhythmPatternToAdd);
    }

    public void diminishedlyImitate(RhythmPart rhythmPartToImitate, int imitateFromWhatPulse, int imitateToWhatPulse, int behindHowManyPulses, int canonRatio){

	RhythmPattern rhythmPatternToAdd = new RhythmPattern(0);

	for(int i = imitateFromWhatPulse; i <= imitateToWhatPulse; i++) {
	    RhythmEvent aRhythmEvent = rhythmPartToImitate.getRhythmEventAt(i);
	    rhythmPatternToAdd.addRhythmEventAndReplace(aRhythmEvent);
	}

        if(canonRatio >= 1) {
	    rhythmPatternToAdd.diminishAndReplace(canonRatio);
	}
	else {
	    System.out.println("Canon Ratio (" + canonRatio + ") is less than 1!! Illegal. Exit from diminishedlyImitate() in RhythmPart class");
	    System.exit(1);
	}

	for(int i = 1; i <= behindHowManyPulses; i++) {
	    rhythmPatternToAdd.insertRestPulseAtTheTopAndReplace();
	}
        
	this.addRhythmicComponent(rhythmPatternToAdd);
    }

    public void copyAndAdd(RhythmPart toCopyFrom, int fromWhere, int toWhere) {
	this.augmentedlyImitate(toCopyFrom, fromWhere, toWhere, 0, 1);
    }

    public int getNumberOfPulsesSoFar() {
	int toReturn = 0;
	for(int i = 1; i < rhythmSequence.size(); i++) {
	    toReturn = toReturn + rhythmSequence.elementAt(i).getNumberOfPulses();
	}
	return toReturn;
    }

    public int getNumberOfRhythmicComponentsSoFar() {
	return rhythmSequence.size() - 1;
    }
        
    public RhythmEvent getRhythmEventAt(int atWhere) {
	checkIfDesignatedPulseIsWithinRange(atWhere, "getRhythmEventAt()");
	    
	int numberOfPulsesFromTop = 0;
	for(int i = 1; i < rhythmSequence.size(); i++) {
	    numberOfPulsesFromTop = numberOfPulsesFromTop + rhythmSequence.elementAt(i).getNumberOfPulses();
	    if(numberOfPulsesFromTop < atWhere) {
		//continue for-loop
	    }
	    else if(atWhere <= numberOfPulsesFromTop) {
		if(rhythmSequence.elementAt(i) instanceof RhythmEvent) {
		    RhythmEvent aRhythmEvent = (RhythmEvent)rhythmSequence.elementAt(i);
		    return aRhythmEvent;
		}
		else if (rhythmSequence.elementAt(i) instanceof RhythmPattern) {
		    RhythmPattern aRhythmPattern = (RhythmPattern)(rhythmSequence.elementAt(i));
		    return aRhythmPattern.getRhythmEventAt(rhythmSequence.elementAt(i).getNumberOfPulses() - (numberOfPulsesFromTop - atWhere));
		}
	    }
	    else {
		System.out.println("Something wrong getRhythmEventAt() in RhythmPart class");
	    }
	}

	System.out.println("Something wrong getRhythmEventAt() in RhythmPart class");
	return null;
    }

    public void lineUpWithAnotherRhythmPartWithRest(RhythmPart withWhichPart) {
	if(this.getNumberOfPulsesSoFar() > withWhichPart.getNumberOfPulsesSoFar()) {
	    System.out.println("The part you are trying to fill in with rests have more pulses ( " + this.getNumberOfPulsesSoFar() + " pulses) than the part you are comparing (" + withWhichPart.getNumberOfPulsesSoFar() + " pulses ).  Exit from lineUpWithAnotherRhythmPartWithRest() in RhythmPart class.");
	    System.exit(1);
	}

	for(int i = this.getNumberOfPulsesSoFar() + 1; i <= withWhichPart.getNumberOfPulsesSoFar(); i++) {
	    RhythmEvent toAdd = new RhythmEvent (false, false);
	    this.addRhythmicComponent(toAdd);
	}
    }

    public RhythmPattern getASection(int fromWhatPulse, int toWhatPulse) {
	checkIfDesignatedPulseIsWithinRange(fromWhatPulse, "getASection()");
	checkIfDesignatedPulseIsWithinRange(toWhatPulse, "getASection()");

	RhythmPattern toReturn = new RhythmPattern(0);

	for(int i = fromWhatPulse; i <= toWhatPulse; i++) {
	    toReturn.addRhythmEventAndReplace(this.getRhythmEventAt(i).copyThisRhythmEvent());
	}

	return toReturn;
    }

    public RhythmPattern transposeASectionAndCopy(int fromWhatPulse, int toWhatPulse, int transposeByHowMany) {
	checkIfDesignatedPulseIsWithinRange(fromWhatPulse, "transposeASectionAndCopy()");
	checkIfDesignatedPulseIsWithinRange(toWhatPulse, "transposeASectionAndCopy()");

	RhythmPattern toReturn = this.getASection(fromWhatPulse, toWhatPulse);
	toReturn.rhythmicallyTransposeAndReplace(transposeByHowMany);

	return toReturn;
    }
	
    //=====================================================================================================
    //-----------------------------------Private Methods---------------------------------------------------
    //=====================================================================================================
    private void checkIfDesignatedPulseIsWithinRange(int pulseNum, String fromWhatMethod) {
	if (pulseNum <= 0) {
	    System.out.println("[Designated pulse No." + pulseNum + "] Designated pulse is less than or equal to 0. Exit from " + fromWhatMethod);
	    System.exit(1);
	}

	if (pulseNum > this.getNumberOfPulsesSoFar()) {
	    System.out.println("[Designated pulse No." + pulseNum + "] Designated pulse is beyond the last pulse so far. There is no such a pulse!! Exit from " + fromWhatMethod);
	    System.exit(1);
	}
    }

    private void checkIfDesignatedRhythmicComponentExists (int rhythmicComponentNum, String fromWhatMethod) {
	if(rhythmicComponentNum <= 0) {
	    System.out.println("Rhythmic component number is less than or equal to 0. Exit from " + fromWhatMethod);
	    System.exit(1);
	}

	if(rhythmicComponentNum > rhythmSequence.size() - 1) {
	    System.out.println("No such a rhythmic component exits. Exit from " + fromWhatMethod);
	    System.exit(1);
	}
    }

    private int calculateNextIntervalOfRandomnessGaussian(int averageRandomFrequency) {
	int intervalOfRandomness; //toReturn

	if(averageRandomFrequency == 1) {
	    intervalOfRandomness = 1;
	}
	else if(averageRandomFrequency == 2) {
	    int randomNumber = rand.nextInt(100) + 1;
	    if(1 <= randomNumber && randomNumber <= 68) {
		intervalOfRandomness = 2;
	    }
	    else if (69 <= randomNumber && randomNumber <= 84) {
		intervalOfRandomness = 1;
	    }
	    else if (85 <= randomNumber && randomNumber <= 100) {
		intervalOfRandomness = 3;
	    }
	    else {
		System.out.println("Something is wrong in calculateNextIntervalOfRandomnessGaussian in RhythmPart Class");
		System.exit(1);
		intervalOfRandomness = 99999999;
	    }
	}
	else {	    	
	    double intervalOfRandomnessDouble = rand.nextGaussian() * averageRandomFrequency + averageRandomFrequency; // Is this formula okay???????/

	    if(intervalOfRandomnessDouble <= 0) {
		intervalOfRandomness = 1;
	    }
	    else if (0 < intervalOfRandomnessDouble && intervalOfRandomnessDouble < averageRandomFrequency) {
		intervalOfRandomness =  (int)Math.ceil(intervalOfRandomnessDouble);// Kiriage
	    }
	    else if (intervalOfRandomnessDouble == (double) averageRandomFrequency) {
		intervalOfRandomness = (int) intervalOfRandomnessDouble;
	    }
	    else if (intervalOfRandomnessDouble > averageRandomFrequency) {
		intervalOfRandomness = (int)Math.floor(intervalOfRandomnessDouble);
	    }
	    else {
		System.out.println("Something wrong in calculateNextIntervalOfRandomnessGaussian() in RhythmPart Class");
		System.exit(1);
		intervalOfRandomness = 99999999;
	    }
	}

	return intervalOfRandomness;
    }
}
	
    

    /*
    public void randomize1ChangingFrequency(int fromWhichPulse, int toWhichPulse, int originalRandomFrequency, int goalRandomFrequency) {
    }

    public void randomize2ChangingFrequency (int fromWhichRhythmicComponent, int toWhichRhythmicComponent, int originalRandomFrequency, int goalRandomFrequency) {
    }
    */
