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

public class RhythmPattern extends RhythmicComponent{

    //double duration (inherited)
    private Vector<RhythmEvent> rhythmPattern; //Index 0 is EMPTY!!!!
    
    //I can write folloqing class
    //TaikoRhythmPattern extends RhythmPattern
    //fieled: private Vector<TaikoRhythmEvent> rhythmPattern;
   
   //Constructor 1: Designate Number Of Pulses and do not assign anything to rhythm pattern
    public RhythmPattern (int numberOfPulses) {
	rhythmPattern = new Vector<RhythmEvent>(numberOfPulses + 1);
	rhythmPattern.setSize(numberOfPulses + 1);
	for(int i = 1; i < rhythmPattern.size(); i++) {
	    RhythmEvent toAdd = new RhythmEvent();
	    rhythmPattern.set(i, toAdd);
	}
    }

    //Constructor 2: randomely assign ROUT to rhythm pattern
    public RhythmPattern (int numberOfPulses, int numberOfHits, int randomSeed) throws FileNotFoundException{
	ROUTGenerator anROUTGenerator = new ROUTGenerator(numberOfPulses, numberOfHits);
	this.rhythmPattern = anROUTGenerator.randomlyChooseAnROUTAndCopy(randomSeed).getThisRhythmPattern();
    }

    //Constructor 3: read rhythmPattern from a rhythm pattern file
    public RhythmPattern (String fileName) {
	try {
	    constructor3ReadFromFile(fileName);
	}
	catch (java.io.FileNotFoundException e) {
	    System.out.println("File name (" + fileName + ") is wrong in consttuctor3ReadFromFile() of rhythmPattern class.");
	    System.exit(1);
	}
    }

    //Constructor 4: read rhythmPattern from a list of possible rhythm patterns.
    public RhythmPattern (String fileName, int choice) {
	try{
	    constructor4ReadFromFile(fileName, choice);
	}
	catch (java.io.FileNotFoundException e) {
	    System.out.println("File name (" + fileName + ") is wrong in consttuctor4ReadFromFile() of rhythmPattern class.");
	    System.exit(1);
	}	
    }

    //Constructor 5: copy another rhythm pattern
    public RhythmPattern (RhythmPattern anotherRhythmPattern) {
	this.rhythmPattern = anotherRhythmPattern.copyThisRhythmPatternObject().getThisRhythmPattern();
    }

    //Constructor 6: set rhythm pattern from boolean vector
    public RhythmPattern (Vector<Boolean> booleanVector) {
	this.translateBooleanVectorIntoHitPattern(booleanVector);
    }
    
    //==========================================================================================
    //-------------------------------Public Methods---------------------------------------------
    //===========================================================================================
    public String toString() {
	String toReturn = "";
	for(int i = 1; i <= getNumberOfPulses(); i++) {
	    toReturn = toReturn + this.rhythmPattern.elementAt(i).toString();
	}
	return toReturn;
    }

    //===========================================================================================
    //----------------------------------Save to File--------------------------------------------
    //===========================================================================================
    public void saveRhythmPatternToFile() throws FileNotFoundException{
	saveRhythmPatternToFile("RhythmPattern" + this.rhythmPattern.hashCode() + ".txt");
    }
    
    public void saveRhythmPatternToFile (String fileName) throws FileNotFoundException {
	if(!fileName.contains(".txt")) {
	    fileName = fileName + ".txt";
	}
	try {
	    PrintWriter outfile = new PrintWriter("./RhythmPatternTextFiles/" + fileName);
	    String rhythmPatternName = (String)fileName.subSequence(0, fileName.length() - 4);// delete ".txt"
	    outfile.println(rhythmPatternName);
	    outfile.println(this);
	    outfile.close();
	}
	catch (java.io.FileNotFoundException e) {
	    System.out.println ("Folder Name or file name in saveRhythmPatternToFile() in RhythmPattern class is wrong!");
	    System.exit(1);
	}   
    }

    //============================================================================================
    //----------------------------------Getters--------------------------------------------------
    //===========================================================================================
    public Vector<RhythmEvent> getThisRhythmPattern() {
	return this.rhythmPattern;
    }

    public RhythmPattern copyThisRhythmPatternObject () {
	return this.rhythmicallyTransposeAndCopy(0);
    }

    public RhythmEvent getRhythmEventAt(int spot) {
	return this.rhythmPattern.elementAt(spot);
    }
    
    public int getNumberOfPulses() {
	return rhythmPattern.size() - 1;
    }

    public int getNumberOfHits() {
	int toReturn = 0;
	for(int i = 1; i < rhythmPattern.size(); i++) {
	    if(rhythmPattern.elementAt(i).getIsSound() == true) {
		toReturn++;
	    }
	}
	return toReturn;
    }

    public int getNumberOfNonAccentedHits() {
	return getNumberOfHits() - getNumberOfAccents();
    }

    public int getNumberOfAccents() {
	int toReturn = 0;
	for(int i = 1; i < rhythmPattern.size(); i++) {
	    if(rhythmPattern.elementAt(i).getIsAccented() == true) {
		toReturn++;
	    }
	}
	return toReturn;
    }

    public int getNumberOfRests() {
	return getNumberOfPulses() - getNumberOfHits();
    }

    public boolean getIsThereHitAt(int where) {
	return rhythmPattern.elementAt(where).getIsSound();
    }

    public boolean getIsThereAccentAt (int where) {
	return rhythmPattern.elementAt(where).getIsAccented();
    }

    public boolean getIsThereRestAt (int where) {
	if(getIsThereHitAt(where) == false && getIsThereAccentAt(where) == false) {
	    return true;
	}
	else if (getIsThereHitAt(where) == false && getIsThereAccentAt(where) == true) {
	    showErrorMessageAndExit("getIsThereRestAt()");
	    return false;
	}
	else {
	    return false;
	}
    }

    public boolean isHitPatternROUT() throws FileNotFoundException{
	ROUTChecker newChecker = new ROUTChecker(this);
	return newChecker.isHitPatternROUT();
    }

    public boolean isAccentPatternROUT() throws FileNotFoundException{
	ROUTChecker newChecker = new ROUTChecker(this);
	return newChecker.isAccentPatternROUT();
    }

    public boolean isRhythmPatternROUT() throws FileNotFoundException{
	ROUTChecker newChecker = new ROUTChecker(this);
	return newChecker.isRhythmPatternROUT();
    }

    public boolean isHitPatternSymmetrical() throws FileNotFoundException{
	ROUTChecker newChecker = new ROUTChecker (this);
	return newChecker.isHitPatternSymmetrical();
    }

    public boolean isAccentPatternSymmetrical() throws FileNotFoundException{
	ROUTChecker newChecker = new ROUTChecker(this);
	return newChecker.isAccentPatternSymmetrical();
    }

    public boolean isRhythmPatternSymmetrical() throws FileNotFoundException{
	ROUTChecker newChecker = new ROUTChecker(this);
	return newChecker.isRhythmPatternSymmetrical();
    }

    public boolean isHitPatternSymmetricalROUT() throws FileNotFoundException{
	ROUTChecker newChecker = new ROUTChecker(this);
	return newChecker.isHitPatternSymmetricalROUT();
    }
    
    public boolean isRhythmPatternSymmetricalROUT() throws FileNotFoundException{
	ROUTChecker newChecker = new ROUTChecker(this);
	return newChecker.isRhythmPatternSymmetricalROUT();
    }

    //============================================================================================
    //-------------------------------Conversion To/From Boolean Vector----------------------------
    //============================================================================================
    public void translateBooleanVectorIntoHitPattern(Vector<Boolean> booleanVector) {
	if(this.rhythmPattern == null) {
	    this.rhythmPattern = new Vector<RhythmEvent>();
	    this.rhythmPattern.add(null);
	}

	for (int i = 1; i < booleanVector.size(); i++) {
	    RhythmEvent rhythmEventToSet = new RhythmEvent(booleanVector.elementAt(i), false);
	    this.rhythmPattern.add(rhythmEventToSet);
	}
    }

    public Vector<Boolean> translateHitPatternIntoBooleanVector() {
	Vector<Boolean> toReturn = new Vector<Boolean>();
	toReturn.setSize(this.getNumberOfPulses() + 1);
	
	for(int i = 1; i < this.rhythmPattern.size(); i++) {
	    toReturn.set(i, this.getIsThereHitAt(i));
	}

	return toReturn;
    }
	    
    public Vector<Boolean> translateAccentPatternIntoBooleanVector() {
	Vector<Boolean> toReturn = new Vector<Boolean>();
	toReturn.setSize(this.getNumberOfPulses() + 1);

	for(int i = 1; i < this.rhythmPattern.size(); i++) {
	    toReturn.set(i, this.getIsThereAccentAt(i));
	}

	return toReturn;
    }
    
	     
    //=============================================================================================
    //---------------------------------Setters-----------------------------------------------------
    //=============================================================================================
    public void setRhythmEventAt(int atWhere, RhythmEvent rhythmEventToSet) {
	checkIfThisPositionIsOutOfRange(atWhere, "setRhythmEventAt()");
	rhythmPattern.elementAt(atWhere).copyAnotherRhythmEventIntoThis(rhythmEventToSet);
    }

    public void setHitAt (int atWhere, boolean setToWhat) {
	checkIfThisPositionIsOutOfRange (atWhere, "setHitAt()");
	checkIfThereIsRhythmEventObjectAt(atWhere, "setHitAt()");
	
	if(setToWhat == getIsThereHitAt(atWhere)) {
	    System.out.println("You are trying to use setHitAt() in RhythmPattern class. The hit at the spot you choose (at pulse No." + atWhere + ") is " + setToWhat + ".  And you are trying to set it to " + setToWhat + ". Thus, if you choose to proceed, no change will happen.");
	    askWhetherOrNotProceed("setHitAt()");
	    return;
	}
	    
	if(setToWhat == false) {
	    rhythmPattern.elementAt(atWhere).setIsAccented(false);
	}

	rhythmPattern.elementAt(atWhere).setIsSound(setToWhat);
    }

    public void setAccentAt (int atWhere, boolean setToWhat) {
	checkIfThisPositionIsOutOfRange (atWhere, "setAccentAt()");
	checkIfThereIsRhythmEventObjectAt(atWhere, "setAccentAt()");

	if(setToWhat == getIsThereAccentAt(atWhere)) {
	    System.out.println("You are trying to use setAccentAt() in RhytmPattern class. The accent at the spot you choose (at pulse No." + atWhere + ") is " + setToWhat + ". And you are trying to set it to " + setToWhat + ". Thus, if you choose to proceed, no change will happen.");
	    askWhetherOrNotProceed("setAccentAt()");
	    return;
	}
	
	if(setToWhat == true) {
	    rhythmPattern.elementAt(atWhere).setIsSound(true);
	}
	
	rhythmPattern.elementAt(atWhere).setIsAccented(setToWhat);
    }

    public void turnIntoRestAt(int atWhere) {
	checkIfThisPositionIsOutOfRange (atWhere, "turnToRestAt");
	checkIfThereIsRhythmEventObjectAt (atWhere, "turnToAccentAt()");
	
	if (this.getIsThereRestAt(atWhere) == true) {
	    System.out.println("You are trying to use turnToRestAt() in RhythmPattern class. The spot you chose is alredy rest. If you proceed, nothing will be changed in this rhyth pattern");
	    askWhetherOrNotProceed("turnIntoRestAt()");
	    return;
	}
	
	if(getIsThereAccentAt(atWhere) == true) {
	    this.setAccentAt(atWhere, false);
	}
	
	this.setHitAt(atWhere, false);
	
    }
		    
    //================================================================================
    //-------------------Rhythmic Operations Methods---------------------------------
    //================================================================================
    public void convertAccentPatternIntoHitPatternAndReplace() {
	this.rhythmPattern = convertAccentPatternIntoHitPatternAndCopy().getThisRhythmPattern();
    }

    public RhythmPattern convertAccentPatternIntoHitPatternAndCopy() {
	Vector<Boolean> booleanAccentPattern = this.translateAccentPatternIntoBooleanVector();
	RhythmPattern toReturn = new RhythmPattern(booleanAccentPattern);
	return toReturn;
    }
    
    //Accent Pattern Will be igored in the following method
    public void switchHitsAndRestsAndReplace() {
	this.rhythmPattern = switchHitsAndRestsAndCopy().getThisRhythmPattern();
    }

    //Accent Pattern Will Be ignored in the following method
    public RhythmPattern switchHitsAndRestsAndCopy() {
	RhythmPattern toReturn = new RhythmPattern (this.getNumberOfPulses());
	for (int i = 1; i <= toReturn.getNumberOfPulses(); i++) {
	    RhythmEvent rhythmEventToSet = new RhythmEvent (!this.getRhythmEventAt(i).getIsSound(), false);
	    toReturn.setRhythmEventAt(i, rhythmEventToSet);
	}

	return toReturn;
    }

    public void insertRhythmEventBeforeAndReplace (RhythmEvent rhythmEventToInsert, int beforeWhichSpot) {
	checkIfThisPositionIsOutOfRange(beforeWhichSpot, "insertRhythmEvenfBeforeAndReplace()");
	checkIfThereIsRhythmEventObjectAt(beforeWhichSpot, "insertRhythmEvenfBeforeAndReplace()");
	this.rhythmPattern.insertElementAt(rhythmEventToInsert, beforeWhichSpot);
    }

    public RhythmPattern insertRhythmEventBeforeAndCopy (RhythmEvent rhythmEventToInsert, int beforeWhichSpot) {
	RhythmPattern toReturn = new RhythmPattern(this);
	toReturn.insertRhythmEventBeforeAndReplace(rhythmEventToInsert, beforeWhichSpot);

	return toReturn;
    }

    public void insertRestPulseBeforeAndReplace (int beforeWhichSpot){
	this.rhythmPattern = this.insertRestPulseBeforeAndCopy(beforeWhichSpot).getThisRhythmPattern();
    }

    public RhythmPattern insertRestPulseBeforeAndCopy(int beforeWhichSpot) {
	RhythmEvent toInsert = new RhythmEvent(false, false);
	return insertRhythmEventBeforeAndCopy(toInsert, beforeWhichSpot);
    }

    public void insertRhythmEventAfterAndReplace(RhythmEvent rhythmEventToInsert, int afterWhichSpot) {
	checkIfThisPositionIsOutOfRange(afterWhichSpot, "insertRhythmEventAfterAndReplace()");
	checkIfThereIsRhythmEventObjectAt(afterWhichSpot, "insertRhythmEvenfAfterAndReplace()");
	if(afterWhichSpot == this.getNumberOfPulses()) {
	    this.addRhythmEventAndReplace(rhythmEventToInsert);
	}
	else {
	    this.rhythmPattern.insertElementAt(rhythmEventToInsert, afterWhichSpot + 1);
	}
    }

    public RhythmPattern  insertRhythmEventAfterAndCopy(RhythmEvent rhythmEventToInsert, int afterWhichSpot) {	
	RhythmPattern toReturn = new RhythmPattern(this);
	toReturn.insertRhythmEventAfterAndReplace(rhythmEventToInsert, afterWhichSpot);
	return toReturn;
    }
	
    public void insertRestPulseAfterAndReplace (int afterWhichSpot) {
	this.rhythmPattern = insertRestPulseAfterAndCopy(afterWhichSpot).getThisRhythmPattern();
    }

    public RhythmPattern insertRestPulseAfterAndCopy(int afterWhichSpot) {
	RhythmEvent toInsert = new RhythmEvent(false, false);
	return insertRhythmEventAfterAndCopy(toInsert, afterWhichSpot);
    }

    public void addRestPulseAndReplace() {
	RhythmEvent toAdd = new RhythmEvent(false, false);
	this.addRhythmEventAndReplace(toAdd);
    }

    public RhythmPattern addRestPulseAndCopy() {
	RhythmPattern toReturn = new RhythmPattern(this);
	toReturn.addRestPulseAndReplace();
	return toReturn;
    }

    public void addRhythmEventAndReplace(RhythmEvent rhythmEvent) {
	this.rhythmPattern.add(rhythmEvent);	
    }

    public RhythmPattern addRhythmEventAndCopy(RhythmEvent rhythmEvent) {
        RhythmPattern toReturn = new RhythmPattern(this);
	toReturn.addRhythmEventAndReplace(rhythmEvent);	
	return toReturn;
    }

    public void insertRestPulseAtTheTopAndReplace() {
	insertRestPulseBeforeAndReplace(1);
    }

    public RhythmPattern insertRestPulseAtTheTopAndCopy() {
	return insertRestPulseBeforeAndCopy(1);
    }

    public void insertRhythmEventAtTheTopAndReplace(RhythmEvent rhythmEvent) {
	insertRhythmEventBeforeAndReplace(rhythmEvent, 1);
    }

    public RhythmPattern insertRhythmEventAtTheTopAndCopy(RhythmEvent rhythmEvent) {
	return insertRhythmEventBeforeAndCopy (rhythmEvent, 1);
    }

    public void removeRhythmEventAtAndReplace(int atWhichSpot) {
	checkIfThisPositionIsOutOfRange(atWhichSpot, "removeRhythmEventAtAndReplace()");
	checkIfThereIsRhythmEventObjectAt(atWhichSpot, "removeRhythmEventAtAndReplace()");
	this.rhythmPattern.remove(atWhichSpot);
    }

    public RhythmPattern removeRhythmEventAtAndCopy(int atWhichSpot) {
	RhythmPattern toReturn = new RhythmPattern(this);
	toReturn.removeRhythmEventAtAndReplace(atWhichSpot);
	return toReturn;
    }

    public void removeRhythmEventAtTheTopAndReplace() {
	this.removeRhythmEventAtAndReplace(1);
    }

    public RhythmPattern removeRhythmEventAtTheTopAndCopy() {
	return this.removeRhythmEventAtAndCopy(1);
    }

    public void popRhythmEventAndReplace() {
	this.removeRhythmEventAtAndReplace(getNumberOfPulses());
    }

    public RhythmPattern popRhythmEventAndCopy() {
	return removeRhythmEventAtAndCopy(getNumberOfPulses());
    }

    public void rhythmicallyTransposeAndReplace (int transposeByHowManyPulses){
	this.rhythmPattern = rhythmicallyTransposeAndCopy(transposeByHowManyPulses).getThisRhythmPattern();
    }

    public RhythmPattern rhythmicallyTransposeAndCopy (int tranposeByHowManyPulses) {
	RhythmPattern toReturn = new RhythmPattern (this.getNumberOfPulses());
	for(int i = 1; i <= this.getNumberOfPulses(); i++) {
	    int transposeToWhere = (i + tranposeByHowManyPulses) % this.getNumberOfPulses();
	    
	    if (transposeToWhere == 0) {
		transposeToWhere = this.getNumberOfPulses();
	    }
	    
	    toReturn.getRhythmEventAt(transposeToWhere).copyAnotherRhythmEventIntoThis(this.rhythmPattern.elementAt(i));
	}
	
	return toReturn;
    }

    public void retrogradeAndReplace() {
	this.rhythmPattern = this.retrogradeAndCopy().getThisRhythmPattern();
    }

    public RhythmPattern retrogradeAndCopy() {
	RhythmPattern toReturn = new RhythmPattern (this.getNumberOfPulses());
	for(int i = 1; i <= toReturn.getNumberOfPulses(); i++) {
	    RhythmEvent rhythmEventToSet = this.getRhythmEventAt(this.getNumberOfPulses() - i + 1).copyThisRhythmEvent(); 
	    toReturn.setRhythmEventAt(i, rhythmEventToSet);
	}
	return toReturn;
    }

    public void augmentAndReplace(int ratio) {
	this.rhythmPattern = augmentAndCopy(ratio).getThisRhythmPattern();
    }

    public RhythmPattern augmentAndCopy (int ratio) {
	RhythmPattern toReturn = new RhythmPattern(0);

	for(int i = 1; i <= this.getNumberOfPulses(); i++) {
	    RhythmEvent toAdd;
	    if(this.rhythmPattern.elementAt(i).getIsAccented() == true) {
		toAdd = new RhythmEvent(true, true);
	    }
	    else if (this.rhythmPattern.elementAt(i).getIsSound() == true && this.rhythmPattern.elementAt(i).getIsAccented() == false) {
		toAdd = new RhythmEvent(true, false);
	    }
	    else if (this.rhythmPattern.elementAt(i).getIsSound() == false) {
		toAdd = new RhythmEvent (false, false);
	    }
	    else {
		System.out.println("Something wrong in aarugmentAndCopy() in RhythmPattern class");
		toAdd = null;
	    }

	    toReturn.addRhythmEventAndReplace(toAdd);
	    
	    for(int x = 2; x <= ratio; x++) {
		toReturn.addRestPulseAndReplace();
	    }
	}

	return toReturn;
    }

    public void diminishAndReplace(int ratio) {
	this.rhythmPattern = diminishAndCopy(ratio).getThisRhythmPattern();
    }

    public RhythmPattern diminishAndCopy (int ratio) {
	if(this.getNumberOfPulses() % ratio != 0) {
	    System.out.println("ERROR in diminishAndCopy(). Number or pulses in this rhythm pattern is " + this.getNumberOfPulses() + " pulses. The designated ratio is " + ratio + ". You cannot divide " + this.getNumberOfPulses() + " by " + ratio + "!!! Exit from diminishAndCopy() in RhythmPattern Class");
	    System.exit(1);
	}
	
	RhythmPattern toReturn = new RhythmPattern(0);
	Vector<RhythmEvent> copyOfThisRhythmPattern = HelperMethods.copyRhythmEventVector(this.rhythmPattern);
	
	for(int i = 1; copyOfThisRhythmPattern.size() >= 2 ;i++) {
	    toReturn.addRhythmEventAndReplace(copyOfThisRhythmPattern.remove(1).copyThisRhythmEvent());

	    for(int x = 2; x <= ratio && copyOfThisRhythmPattern.size() >= 2; x++) {
		RhythmEvent aRhythmEvent = copyOfThisRhythmPattern.remove(1);
		if(aRhythmEvent.getIsSound() == true) {
		    System.out.print("ERROR in diminishAndCopy(). The following pattern cannot be diminished by the ratio of " + ratio + ": ");
		    System.out.println(this);
		    System.out.println("Exit from diminishAndCopy() in RhythmPatternclass");
		    System.exit(1);
		}
	    }
	}
    
	return toReturn;
    }

    //==================================================================================
    //----------------------Randomness Methods----------------------------------------
    //==================================================================================
    public void randomlyModifyAndReplace(int randomSeed) throws FileNotFoundException{
        Random rand = new Random(randomSeed);
	int randomNumber = rand.nextInt(6);

	if (randomNumber == 0) {
	    turnRestIntoHitRandomlyAndReplace(randomSeed);
	}
	else if (randomNumber == 1) {
	    turnHitIntoRestRandomlyAndReplace(randomSeed);
	}
	else if (randomNumber == 2) {
	    turnHitIntoAccentRandomlyAndReplace(randomSeed);
	}
	else if (randomNumber == 3) {
	    turnAccentIntoHitRandomlyAndReplace(randomSeed);
	}
	else if (randomNumber == 4) {
	    turnRestIntoAccentRandomlyAndReplace(randomSeed);
	}
	else if (randomNumber == 5) {
	    turnAccentIntoRestRandomlyAndReplace(randomSeed);
	}
	/*
	else if (randomNumber == 6) {
	    insertHitRandomlyAndReplace(randomSeed);
	}
	else if (randomNumber == 7) {
	    insertAccentRandomlyAndReplace(randomSeed);
	}
	else if (randomNumber == 8) {
	    insertRestRandomlyAndReplace(randomSeed);
	}
	else if (randomNumber == 9) {
	    removeHitRandomlyAndReplace(randomSeed);
	}
	else if (randomNumber == 10) {
	    removeAccentRandomlyAndReplace(randomSeed);
	}
	else if (randomNumber == 11) {
	    removeRestRandomlyAndReplace(randomSeed);
	}
	else if (randomNumber == 12) {
	    randomlyTransposeAndReplace(randomSeed);
	}
	else if (randomNumber == 13) {
	    retrogradeAndReplace();
	}
	else if (randomNumber == 14) {
	    convertAccentPatternIntoHitPatternAndReplace();
	}
	else if (randomNumber == 15) {
	    switchHitsAndRestsAndReplace();
	}
	*/
	else {
	    showErrorMessageAndExit("randomlyModify()");
	}
    }

    public RhythmPattern randomlyModifyAndCopy(int randomSeed) throws FileNotFoundException {
	RhythmPattern toReturn = new RhythmPattern(this);;
	toReturn.randomlyModifyAndReplace(randomSeed);
	return toReturn;
    }
    
    //The Method Below is originally named "addRhythmEvent"!!!!!
    public void turnRestIntoHitRandomlyAndReplace (int randomSeed) {
	this.rhythmPattern = turnRestIntoHitRandomlyAndCopy(randomSeed).getThisRhythmPattern();
    }

    //The Method Below is originally named "addRhythmEvent" !!!!!!
    public RhythmPattern turnRestIntoHitRandomlyAndCopy(int randomSeed) {
	if(getNumberOfHits() == getNumberOfPulses() || getNumberOfPulses() == 0) {
	    System.out.println("WARNING: all pulses contain hits or accents. There is no rests. You cannot turn rest into hit randomly. If you proceed, no rhythm event will be modified and this object of rhythm pattern will be returned (at tuenRestIntoHitRandomlyAndCopy() in RhythmPattern class  (Note: This error message will also appear when there is no rhythm event (0 pulse) in this rhythm pattern. Please DO NOT GET CONFUSED)");
	    askWhetherOrNotProceed("turnRestIntoHitRandomlyAndCopy()");
	    return this.copyThisRhythmPatternObject();
	}
	    
	RhythmPattern toReturn = new RhythmPattern(this);
	
	Random rand = new Random(randomSeed);
	int randomNumber = rand.nextInt(getNumberOfPulses()) + 1;
	while(getIsThereRestAt(randomNumber) == false) {
	    randomNumber = rand.nextInt(getNumberOfPulses()) + 1;
	}

	toReturn.setHitAt(randomNumber, true);
	    
	return toReturn;
    }

    public void turnHitIntoRestRandomlyAndReplace (int randomSeed) throws FileNotFoundException{
	this.rhythmPattern = turnHitIntoRestRandomlyAndCopy(randomSeed).getThisRhythmPattern();
    }

    public RhythmPattern turnHitIntoRestRandomlyAndCopy(int randomSeed) {	
	if(this.getNumberOfNonAccentedHits() == 1) {
	    System.out.println("WARNING: Only one non-accented hit is remaining! You are tring to this last hit into rest by turnHitIntoRestRandomlyAndCopy()");
	    askWhetherOrNotProceed("turnHitIntoRestRandomlyAndCopy()");
	}

	if(this.getNumberOfNonAccentedHits() == 0 || getNumberOfPulses() == 0) {
	    System.out.println("WARNING: No non-accented hit to remove!!!!If you proceed, no rhythm event will be removed (at turnHitIntoRestRandomlyAndCopy()(Note: This error message will also appear when there is no rhythm event (0 pulse) in this rhythm pattern. Please DO NOT GET CONFUSED");
	    askWhetherOrNotProceed("turnHitIntoRestRandomlyAndCopy()");
	    
	    return this.copyThisRhythmPatternObject();
	}
	
	RhythmPattern toReturn = new RhythmPattern (this);
	Random rand = new Random(randomSeed);
	int randomNumber = rand.nextInt(getNumberOfPulses()) + 1;
	while(getIsThereRestAt(randomNumber) == true || getIsThereAccentAt(randomNumber) == true) {
	    randomNumber = rand.nextInt(getNumberOfPulses()) + 1;
	}

	toReturn.turnIntoRestAt(randomNumber);

	return toReturn;
    }
    
    public void turnRestIntoHitRandomlyButKeepROUTAndReplace (int randomSeed) throws FileNotFoundException {
	this.rhythmPattern = turnRestIntoHitRandomlyButKeepROUTAndCopy(randomSeed).getThisRhythmPattern();
    }
    
    public RhythmPattern turnRestIntoHitRandomlyButKeepROUTAndCopy(int randomSeed) throws FileNotFoundException {
	if(getNumberOfHits() == getNumberOfPulses() || getNumberOfPulses() == 0) {
	    System.out.println("WARNING: all pulses contain hits or accents. There is no rests. You cannot turn rest into hit randomly. If you proceed, no rhythm event will be modified and this object of rhythm pattern will be returned (at turnRestIntoHitRandomlyButKeepROUTAndCopy() in RhythmPattern class  (Note: This error message will also appear when there is no rhythm event (0 pulse) in this rhythm pattern. Please DO NOT GET CONFUSED)");
	    askWhetherOrNotProceed("turnRestIntoHitRandomlyButKeepROUTAndCopy()");
	    return this.copyThisRhythmPatternObject();
	}
		
	RhythmPattern toReturn = new RhythmPattern(this);
	for (int counter = 0; counter < getNumberOfPulses() * 10; counter++) {
	    toReturn.turnRestIntoHitRandomlyAndReplace(counter);
	    if(toReturn.isHitPatternROUT() == true) {
		counter = getNumberOfPulses() * 10 + 9999;
	    }
	    else {
		toReturn = new RhythmPattern(this);
		counter++;
		if(counter == getNumberOfPulses() * 10) {
		    System.out.println("You cannot turn rest into hit randomly without ROUTness. turnrestIntoHitRandomlyButKeepROUTAndCopy() in RhythmPattern Class");
		    System.out.println("If you choose to proceed, the program will add a rhythm event randomly without considering ROUTness");
		    askWhetherOrNotProceed("turnRestIntoHitRandomlyButKeepROUTAndCopy()");

		    toReturn.turnRestIntoHitRandomlyAndReplace(counter + 9999);
		}
	    }
	}

	return toReturn;
    }

    public void turnHitIntoRestRandomlyButKeepROUTAndReplace(int randomSeed) throws FileNotFoundException{
	this.rhythmPattern = turnHitIntoRestRandomlyButKeepROUTAndCopy(randomSeed).getThisRhythmPattern();	
    }

    public RhythmPattern turnHitIntoRestRandomlyButKeepROUTAndCopy (int randomSeed) throws FileNotFoundException {
	if(this.getNumberOfNonAccentedHits() == 1) {
	    System.out.println("WARNING: Only one non-accented hit is remaining! You are tring to this last hit into rest by turnHitIntoRestRandomlyButKeepROUTAndCopy()");
	    askWhetherOrNotProceed("turnHitIntoRestRandomlyButKeepROUTAndCopy()");
	}

	if(this.getNumberOfNonAccentedHits() == 0 || getNumberOfPulses() == 0) {
	    System.out.println("WARNING: No non-accented hit to remove!!!!If you proceed, no rhythm event will be removed (at turnHitIntoRestRandomlyButKeepROUTAndCopy()(Note: This error message will also appear when there is no rhythm event (0 pulse) in this rhythm pattern. Please DO NOT GET CONFUSED");
	    askWhetherOrNotProceed("turnHitIntoRestRandomlyButKeepROUTAndCopy()");
	    
	    return this.copyThisRhythmPatternObject();
	}	

	RhythmPattern toReturn = new RhythmPattern (this);
	for (int counter = 0; counter < getNumberOfPulses() * 10; counter++) {
	    toReturn.turnHitIntoRestRandomlyAndReplace(counter);
	    if(toReturn.isHitPatternROUT() == true) {
		counter = getNumberOfPulses() * 10 + 9999;
	    }
	    else {
		toReturn = new RhythmPattern(this);
		counter++;
		if(counter == getNumberOfPulses() * 10) {
		    System.out.println("You cannot turn hit into res randomly without breaking ROUTness. turnHitIntoRestRandomlyButKeepROUTAndCopy() in RhythmPattern class");
		    System.out.println("If you choose to proceed, the program will remove a rhythm event randomly without considering ROUTness");
		    toReturn.turnHitIntoRestRandomlyAndReplace(counter + 9999);
		}
	    }
	}

	return toReturn;	    
    }

    public void turnHitIntoAccentRandomlyAndReplace(int randomSeed) {
	this.rhythmPattern = turnHitIntoAccentRandomlyAndCopy(randomSeed).getThisRhythmPattern();
    }

    public RhythmPattern turnHitIntoAccentRandomlyAndCopy (int randomSeed) {
	if(getNumberOfNonAccentedHits() == 1) {
	    System.out.println("WARNING: Only one hit is remaining in this rhythm pattern. If you choose to proceed, the last hit will be removed! from turnHitIntoAccentRandomlyAndCopy() in RhythmPattern class");
	    askWhetherOrNotProceed("turnHitIntoAccentRandomlyAndCopy()");
	}
	if(getNumberOfNonAccentedHits() == 0 || getNumberOfPulses() == 0) {
	    System.out.println("WARNING: No hit is remaining. Thus, the method cannot turn hit into accent. If you choose to proceed, no change will be made on this rhythm pattern and returned.(Note: This error message will also appear when there is no rhythm event (0 pulse) in this rhythm pattern. Please DO NOT GET CONFUSED");
	    askWhetherOrNotProceed("turnHitIntoAccentRandomlyAndCopy()");
	    return this.copyThisRhythmPatternObject();
	}
	    	
	RhythmPattern toReturn = new RhythmPattern(this);
	Random rand = new Random(randomSeed);
	int randomPulse = rand.nextInt(toReturn.getNumberOfPulses()) + 1;
        while(getIsThereHitAt(randomPulse) == false || getIsThereAccentAt(randomPulse) == true) {
	    randomPulse = rand.nextInt(toReturn.getNumberOfPulses()) + 1;
	}
	toReturn.setAccentAt(randomPulse, true);
	return toReturn;
    }

    public void turnAccentIntoHitRandomlyAndReplace (int randomSeed){
	this.rhythmPattern = turnAccentIntoHitRandomlyAndCopy(randomSeed).getThisRhythmPattern();
    }

    public RhythmPattern turnAccentIntoHitRandomlyAndCopy (int randomSeed) {
        if(getNumberOfAccents() == 0 || getNumberOfPulses() == 0) {
	    System.out.println("WARNING: No accent is remaining. No accent can be removed. If you choose to proceed, no change will be made on this rhythm pattern and returned.(Note: This error message will also appear when there is no rhythm event (0 pulse) in this rhythm pattern. Please DO NOT GET CONFUSED");
	    askWhetherOrNotProceed("turnAccentIntoHitRandomlyAndCopy()");
	    return this.copyThisRhythmPatternObject();
	}

	RhythmPattern toReturn = new RhythmPattern(this);
	Random rand = new Random(randomSeed);
	int randomPulse = rand.nextInt(toReturn.getNumberOfPulses()) + 1;
	while(getIsThereAccentAt(randomPulse) == false) {
	    randomPulse = rand.nextInt(toReturn.getNumberOfPulses()) + 1;
	}
	toReturn.setAccentAt(randomPulse, false);
	return toReturn;
    }

    public void turnRestIntoAccentRandomlyAndReplace (int randomSeed) {
	this.rhythmPattern = turnRestIntoAccentRandomlyAndCopy(randomSeed).getThisRhythmPattern();
    }

    public RhythmPattern turnRestIntoAccentRandomlyAndCopy(int randomSeed) {	
	if(getNumberOfRests() == 0 || getNumberOfPulses() == 0) {
	    System.out.println("WARNING: There is no rest remaining. In other words, all pulses are filled with hits or accents. If you choose to proceed, no change will be made on this rhythm pattern and returned. (From turnRestIntoAccentRandomlyAndCopy() in RhythmPattern class) (Note: This error message will also appear when there is no rhythm event (0 pulse) in this rhythm pattern. Please DO NOT GET CONFUSED");
	    askWhetherOrNotProceed("turnRestIntoAccentRandomlyAndCopy()");
	    return this.copyThisRhythmPatternObject();
	}

	RhythmPattern toReturn = new RhythmPattern(this);
	Random rand = new Random(randomSeed);
	int randomPulse = rand.nextInt(toReturn.getNumberOfPulses()) + 1;
	while(getIsThereRestAt(randomPulse) == false) {
	    randomPulse = rand.nextInt(toReturn.getNumberOfPulses()) + 1;
	}
	toReturn.setAccentAt(randomPulse, true);
	return toReturn;
    }

    public void turnAccentIntoRestRandomlyAndReplace(int randomSeed) {
	this.rhythmPattern = turnAccentIntoRestRandomlyAndCopy(randomSeed).getThisRhythmPattern();
    }

    public RhythmPattern turnAccentIntoRestRandomlyAndCopy(int randomSeed) {	
	if(getNumberOfAccents() == 0 || getNumberOfPulses() == 0) {
	    System.out.println("WARNING*: There is no accent to turn into rest. If you choose to proceed, no accent will be turned into a rest. (from turnAccentIntoRestRandomlyAndCopy()(Note: This error message will also appear when there is no rhythm event (0 pulse) in this rhythm pattern. Please DO NOT GET CONFUSED");
	    askWhetherOrNotProceed("turnAccenIntoRestRandomlyAndCopy()");
	    return this.copyThisRhythmPatternObject();
	}

	RhythmPattern toReturn = new RhythmPattern(this);
	Random rand = new Random(randomSeed);
	int randomPulse = rand.nextInt(toReturn.getNumberOfPulses()) + 1;
	while(getIsThereAccentAt(randomPulse) == false) {
	    randomPulse = rand.nextInt(toReturn.getNumberOfPulses()) + 1;
	}
	toReturn.setHitAt(randomPulse, false);
	return toReturn;
    }

    public void insertHitRandomlyAndReplace (int randomSeed) {
	this.rhythmPattern = insertHitRandomlyAndCopy(randomSeed).getThisRhythmPattern();
    }

    public RhythmPattern insertHitRandomlyAndCopy(int randomSeed) {
	RhythmEvent toInsert = new RhythmEvent (true, false);
	return insertARhythmEventRandomlyAndCopy(randomSeed, toInsert);
    }

    public void insertAccentRandomlyAndReplace(int randomSeed) {
	this.rhythmPattern = insertAccentRandomlyAndCopy(randomSeed).getThisRhythmPattern();
    }

    public RhythmPattern insertAccentRandomlyAndCopy (int randomSeed){
	RhythmEvent toInsert = new RhythmEvent (true, true);
	return insertARhythmEventRandomlyAndCopy(randomSeed, toInsert);
    }

    public void insertRestRandomlyAndReplace (int randomSeed) {
	this.rhythmPattern = insertRestRandomlyAndCopy(randomSeed).getThisRhythmPattern();
    }

    public RhythmPattern insertRestRandomlyAndCopy (int randomSeed) {
	RhythmEvent toInsert = new RhythmEvent (false, false);
	return insertARhythmEventRandomlyAndCopy(randomSeed, toInsert);
    }

    public void insertRandomRhythmmEventRandomlyAndReplace (int randomSeed) {
	this.rhythmPattern = insertRandomRhythmEventRandomlyAndCopy(randomSeed).getThisRhythmPattern();
    }

    public RhythmPattern insertRandomRhythmEventRandomlyAndCopy (int randomSeed) {
	Random rand = new Random(randomSeed);
	int randomNumber = rand.nextInt(2);
	if(randomNumber == 0) {
	    return insertHitRandomlyAndCopy(randomSeed);
	}
	else if (randomNumber == 1) {
	    return insertAccentRandomlyAndCopy(randomSeed);
	}
	else {
	    return insertRestRandomlyAndCopy(randomSeed);
	}
    }

    private RhythmPattern insertARhythmEventRandomlyAndCopy(int randomSeed, RhythmEvent toInsert) {
	RhythmPattern toReturn = new RhythmPattern(this);

	Random rand = new Random(randomSeed);
	int randomNumber = rand.nextInt(toReturn.getNumberOfPulses() + 1) + 1;
	if (1<= randomNumber && randomNumber <= getNumberOfPulses()) {
	    toReturn.insertRhythmEventBeforeAndReplace(toInsert, randomNumber);
	}
	else if (randomNumber == getNumberOfPulses() + 1) {
	    toReturn.addRhythmEventAndReplace(toInsert);
	}
	else {
	    System.out.println("randomNumber in insertARhythmEventRandomlyAndCopy() is wrong in some way!");
	    System.exit(1);
	}

	return toReturn;
    }
	    
    public void removeHitRandomlyAndReplace(int randomSeed) {
	this.rhythmPattern = removeHitRandomlyAndCopy(randomSeed).getThisRhythmPattern();
    }

    public RhythmPattern removeHitRandomlyAndCopy(int randomSeed) {
	if(checkIfRemoveIsPossible("removeHitRandomlyAndCopy()") == false) {
	    return this.copyThisRhythmPatternObject();
	}

	if(getNumberOfHits() == 0) {
	    System.out.println("This rhythm pattern contains no hit. No hit to remove. If you choose to proceed, this rhythm pattern will be simply copied and returned without modification");
	    askWhetherOrNotProceed("removeHitRandomlyAndCopy()");
	    return this.copyThisRhythmPatternObject();
	}
	
	if(getNumberOfPulses() == 1 && getNumberOfHits() == 1) {
	    System.out.println("Only one rhythm event (or pulse) is remaining. If you choose to proceed, the last rhythm event (hit) of this rhythm pattern will be removed");
	    askWhetherOrNotProceed("removeHitRandomlyAndCopy()");
	}

	RhythmPattern toReturn = new RhythmPattern(this);

	Vector<Integer> hitLocations = new Vector<Integer>();
	for(int i = 1; i <= getNumberOfPulses(); i++) {
	    if(getRhythmEventAt(i).getIsSound() == true && getRhythmEventAt(i).getIsAccented() == false) {
		hitLocations.add((Integer)i);
	    }
	}
	Random rand = new Random(randomSeed);
	int randomNumber = rand.nextInt(hitLocations.size());
	int locationOfHitToRemove = hitLocations.elementAt(randomNumber);

	toReturn.removeRhythmEventAtAndReplace(locationOfHitToRemove);
	return toReturn;
    }

    public void removeAccentRandomlyAndReplace(int randomSeed) {
	this.rhythmPattern = removeAccentRandomlyAndCopy(randomSeed).getThisRhythmPattern();
    }

    public RhythmPattern removeAccentRandomlyAndCopy(int randomSeed) {
	if(checkIfRemoveIsPossible("removeAccentRandomlyAndCopy()") == false) {
	    return this.copyThisRhythmPatternObject();
	}

	if(getNumberOfAccents() == 0) {
	    System.out.println("This rhythm pattern contains no accent. Noaccent to remove. If you choose to proceed, this rhythm pattern will be simply copied and returned without modification");
	    askWhetherOrNotProceed("removeAccentRandomlyAndCopy()");
	    return this.copyThisRhythmPatternObject();
	}

	if(getNumberOfPulses() == 1 && getNumberOfAccents() == 1) {
	    System.out.println("Only one rhythm event (or pulse) is remaining. If you choose to proceed, the last rhythm event (accented hit) of this rhythm pattern will be removed");
	    askWhetherOrNotProceed("removeAccentRandomlyAndCopy()");
	}

	RhythmPattern toReturn = new RhythmPattern(this);

	Vector<Integer> accentLocations = new Vector<Integer>();
	for(int i = 1; i <= getNumberOfPulses(); i++) {
	    if(getRhythmEventAt(i).getIsAccented() == true) {
		accentLocations.add((Integer)i);
	    }
	}
	Random rand = new Random(randomSeed);
	int randomNumber = rand.nextInt(accentLocations.size());
	int locationOfAccentToRemove = accentLocations.elementAt(randomNumber);

	toReturn.removeRhythmEventAtAndReplace(locationOfAccentToRemove);
	return toReturn;
    }

    public void removeRestRandomlyAndReplace(int randomSeed) {
	this.rhythmPattern = removeRestRandomlyAndCopy(randomSeed).getThisRhythmPattern();
    }

    public RhythmPattern removeRestRandomlyAndCopy(int randomSeed) {	
	if(checkIfRemoveIsPossible("removeRestRandomlyAndCopy()") == false) {
	    return this.copyThisRhythmPatternObject();
	}

	if(getNumberOfRests() == 0) {
	    System.out.println("This rhythm pattern contains no rest. No rest to remove. If you choose to proceed, this rhythm pattern will be simply copied and returned without modification");
	    askWhetherOrNotProceed("removeRestRandomlyAndCopy()");
	    return this.copyThisRhythmPatternObject();
	}

	if(getNumberOfPulses() == 1 && getNumberOfRests() == 1) {
	    System.out.println("Only one rhythm event (or pulse) is remaining. If you choose to proceed, the last rhythm event (rest) of this rhythm pattern will be removed");
	    askWhetherOrNotProceed("removeRestRandomlyAndCopy()");
	}

	RhythmPattern toReturn = new RhythmPattern(this);

	Vector<Integer> restLocations = new Vector<Integer>();
	for(int i = 1; i <= getNumberOfPulses(); i++) {
	    if(getRhythmEventAt(i).getIsSound() == false) {
		restLocations.add((Integer)i);
	    }
	}
	Random rand = new Random(randomSeed);
	int randomNumber = rand.nextInt(restLocations.size());
	int locationOfRestToRemove = restLocations.elementAt(randomNumber);

	toReturn.removeRhythmEventAtAndReplace(locationOfRestToRemove);
	return toReturn;
    }

    public void removeRhythmEventRandomlyAndReplace(int randomSeed) {
	this.rhythmPattern = removeRhythmEventRandomlyAndCopy(randomSeed).getThisRhythmPattern();
    }

    public RhythmPattern removeRhythmEventRandomlyAndCopy(int randomSeed) {
	if(checkIfRemoveIsPossible("removeRhythmEventRandomlyAndCopy()") == false) {
	    return this.copyThisRhythmPatternObject();
	}

	RhythmPattern toReturn = new RhythmPattern(this);
	
	Random rand = new Random(randomSeed);
	int positionToRemove = rand.nextInt(getNumberOfPulses()) + 1;

	toReturn.removeRhythmEventAtAndReplace(positionToRemove);

	return toReturn;
    }

    private boolean checkIfRemoveIsPossible (String fromWhatMethod) {
	if(this.getNumberOfPulses() == 0) {
	    System.out.println("No rhythm event (or pulse) is remaining. If you choose to proceed, no remove will be made and this rhythm pattern will be simply copied and returned.");
	    askWhetherOrNotProceed(fromWhatMethod);
	    return false;
	}

	return true;
    }
	      
    public void randomlyTransposeAndReplace(int randomSeed) {
	this.rhythmPattern = randomlyTransposeAndCopy(randomSeed).getThisRhythmPattern();
    }

    public RhythmPattern randomlyTransposeAndCopy(int randomSeed) {
	Random rand = new Random(randomSeed);
	int transposeByHowManyPulses = rand.nextInt(this.getNumberOfPulses());
	while(transposeByHowManyPulses == 0) {
	    transposeByHowManyPulses = rand.nextInt(this.getNumberOfPulses());
	}
	return this.rhythmicallyTransposeAndCopy(transposeByHowManyPulses);
    }
    //==========================================================================================
    //-----------------------------------Private Method-----------------------------------------
    //==========================================================================================
    private void constructor3ReadFromFile (String fileName)throws FileNotFoundException {

	if(rhythmPattern == null) {
	    rhythmPattern = new Vector<RhythmEvent>();
	    rhythmPattern.add(null);
	}
	
	try {
	    if(!fileName.contains(".txt")) {
		fileName = fileName + ".txt";
	    }
	    
	    String filePlaceAndName = "./RhythmPatternTextFiles/" + fileName;////Not Sure If This Works!!!!!
	    Scanner infile = new Scanner(new FileReader(filePlaceAndName));
	    infile.nextLine();//read Name Of Rhythm Pattern
	    
	    while(infile.hasNext()) {
		RhythmEvent toAdd;
		
		String nextString = infile.next();

		if(nextString.equals("A")) {
		    toAdd = new RhythmEvent (true, true);
		}
		else if (nextString.equals("X")) {
		    toAdd = new RhythmEvent (true, false);
		}
		else if (nextString.equals("-")) {
		    toAdd = new RhythmEvent (false, false);
		}
		else {
		    toAdd = new RhythmEvent(false, true);//This line is just to avoid compiler error. This line is meaningless in this program
		    System.out.println("The rhythm pattern text file(" + fileName + ") contains wrong characters other than A, X, or -.");
		    System.exit(1);
		}
		
		this.addRhythmEventAndReplace(toAdd);
	    }
	    
	    infile.close();
	}
	catch (java.io.FileNotFoundException e) {
	    System.out.println("File name (" + fileName + ") is wrong in consttuctor3ReadFromFile() of rhythmPattern class.");
	    System.exit(1);
	}	
    }

     private void constructor4ReadFromFile(String fileName, int choice)  throws FileNotFoundException {
	try {
	    if(choice < 0) {
		System.out.println("choice is negative number. Invalid! Exit from RhythmPattern constructor 4");
		System.exit(1);
	    }
	    
	    String filePlaceAndName = "./PossibleRhythmPatternsListTextFiles/" + fileName;////Not Sure If This Works!!!!!
	    Scanner infile = new Scanner(new FileReader(filePlaceAndName));
	    
	    infile.nextLine();//read Name Of Rhythm Patterns List

	    for (int i = 0; i <= choice - 1; i++) {
		if(!infile.hasNext()) {
		    System.out.println ("Choice is out of range. Invalid! Exit from RhythmPattern constructor 4");
		    System.exit(1);
		}
		infile.nextLine();
	    }

	    String rhythmPatternLine = infile.nextLine(); // read number of the rhythm pattern you want
	    String[] eachWordInThisLine = rhythmPatternLine.split("\\s");
	    String rhythmPatternString = "";
	    for (int i = 1; i < eachWordInThisLine.length; i ++) {
		rhythmPatternString = rhythmPatternString + eachWordInThisLine[i] + " ";
	    }

	    infile.close();

	    String fileNameToWrite = fileName + " No." + choice;
	    	    
	    PrintWriter outfile = new PrintWriter("./RhythmPatternTextFiles/" + fileNameToWrite);
	    outfile.println(fileNameToWrite);
	    outfile.println(rhythmPatternString);
	    outfile.close();

	    constructor3ReadFromFile(fileNameToWrite);
	}		   
	catch (java.io.FileNotFoundException e) {
	    System.out.println("File name (" + fileName + ") is wrong in consttuctor4ReadFromFile() of rhythmPattern class.");
	    System.exit(1);
	}	
    }
    
    private void checkIfThisPositionIsOutOfRange (int position, String fromWhatMethod) {
	if(getNumberOfPulses() == 0 || this.rhythmPattern.size() == 1) {
	    System.out.println("No rhythm event is remaining. Thus rhythm pattern contains 0 pulses");
	    askWhetherOrNotProceed(fromWhatMethod);
	    return;
	}
	
	if(rhythmPattern.size() < position + 1) {
	    showErrorMessageAndExit(fromWhatMethod);
	}
    }
	    
    private void checkIfThereIsRhythmEventObjectAt (int position, String fromWhatMethod) {
	if(rhythmPattern.elementAt(position) == null) {
	    showErrorMessageAndExit(fromWhatMethod);
	}
    }

    private void showErrorMessageAndExit (String fromWhatMethod) {
	System.out.println("Something Wrong in " + fromWhatMethod + " at rhythmPattern class");
	System.exit(1);	
    }

    private void askWhetherOrNotProceed (String fromWhatMethod) {
	System.out.println("At " + fromWhatMethod + " in RhythmPattern Class: Do you like to proceed? Press y or n");
	Scanner keyboard = new Scanner(System.in);
	String yOrN = keyboard.nextLine();

	while(!yOrN.equals("y") && !yOrN.equals("n")){
	    System.out.println("Please press y or n!!!");
	    yOrN = keyboard.nextLine();
	}
	
	if(yOrN.equals("n")) {
	    System.out.println("You chose to exit from " + fromWhatMethod + " in RhythmPattern Class!!!");
	    System.exit(1);
	}	    
    }
}

    /*
    public void addRhythmEventRandomlyAndReplace() {
	this.addRhythmEventRandomlyAndReplace(1000);
    }

    public RhythmPattern addRhythmEventRandomlyAndCopy() {
	return this.addRhythmEventRandomlyAndCopy(10000);
    }

    public void removeRhythmEventRandomlyAndReplace() { `
	removeRhythmEventRandomlyAndReplace(100000);
    }

    public RhythmPattern removeRhythmEventRandomlyAndCopy() {
	return this.removeRhythmEventRandomlyAndCopy(500);
    }

    public void addRhythmEventRandomlyButKeepROUTAndReplace() {
	this.addRhythmEventRandomlyButKeepROUTAndReplace(15);
    }

    public RhythmPattern addRhythmEventRandomlyButKeepROUTAndCopy() {
	return addRhythmEventRandomlyButKeepROUTAndCopy(25);
    }
    
    public void randomlyTranposeAndReplace() {
	this.randomlyTransposeAndReplace(100);
    }

    public void randomlyTranposeAndCopy() {
	this.randomlyTranposeAndCopy(100);
    }

    */
   
