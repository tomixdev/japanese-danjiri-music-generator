import java.util.Vector;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Random;

public class RhythmEvent extends RhythmicComponent {

    private boolean isSound; //if false, this musical event is rest
    private boolean isAccented;

    //double duration is inherited!!Precision Problem!!Solve Later!!
    
    //int intensity; //// 0 pppp, 1 ppp, 2 pp......
    //Instrument instrument//////
    //Following Fields Can Be Added in TaikoRhythmEvent extends RhythmEvent
    //private boolean isOdaiko;
    //private boolean isKodaiko;
    //private boolean isEdgeHit;
    //private boolean isTokushuSouhou;
    //....create a lot of boolean fields for a lot of tokusyu souhou (search on You tube)    

    //Constructor 1
    public RhythmEvent() {
	this(false, false);
    }

    //constructor 2
    public RhythmEvent(char rhythmEventChar) {
	if(rhythmEventChar == 'A') {
	    isSound = true;
	    isAccented = true;
	}
	else if (rhythmEventChar == 'X') {
	    isSound = true;
	    isAccented = false;
	}
	else if (rhythmEventChar == '-') {
	    isSound = false;
	    isAccented = false;
	}
	else {
	    System.out.println("From constructor 2 of RhythmEvent Class. The character is not A, X, or -. Exit!");
	    System.exit(1);
	    isSound = false;
	    isAccented = false;
	}
    }
		 
    //Constuctor 3
    public RhythmEvent (boolean isSound, boolean isAccented) {
	this.isSound = isSound;
	this.isAccented = isAccented;
    }

    //Getters-----------------------------------------------------
    public boolean getIsSound() {
	return isSound;
    }

    public boolean getIsAccented() {
	return isAccented;
    }

    public int getNumberOfPulses() {
	return 1;
    }

    ///Setters-----------------------------------------------------
    public void setIsSound(boolean _boolean) {
	isSound = _boolean;
	checkConsistency();
    }

    public void setIsAccented(boolean _boolean) {
	isAccented = _boolean;
	checkConsistency();
    }

    //toString---------------------------------------------
    public String toString() {
	if(isSound == true && isAccented == true) {
	    return "A ";
	}
	else if (isSound == true && isAccented == false) {
	    return "X ";
	}
	else if (isSound == false && isAccented == false) {
	    return "- ";
	}
	else {
	    System.out.println("Something Wrong In toString() in rhythmEvent class");
	    System.exit(1);
	    return "";
	}
    }

    //public methods-------------------------------------------
    public void copyAnotherRhythmEventIntoThis(RhythmEvent another) {
	this.isSound = another.getIsSound();
	this.isAccented = another.getIsAccented();
	//this.instrument = copyInstrument(another);
	//this.intensity = another.intensity
	//.........
    }

    public RhythmEvent copyThisRhythmEvent() {
	RhythmEvent toReturn = new RhythmEvent (this.isSound, this.isAccented);
	return toReturn;
    }

    public void randomlyModifyAndReplace(int randomSeed) {
	Random rand = new Random(randomSeed);
	isSound = rand.nextBoolean();
	isAccented = rand.nextBoolean();
	
	if(isAccented == true) {
	    isSound = true;
	}
	if(isSound == false) {
	    isAccented = false;
	}
    }

    public RhythmEvent randomlyModifyAndCopy(int randomSeed) {
	RhythmEvent toReturn = new RhythmEvent();
	toReturn.randomlyModifyAndReplace(randomSeed);
	return toReturn;
    }

    //private methods----------------------------------------
    private void checkConsistency() {
	if(isSound == false && isAccented == true) {
	    System.out.println("isSound() = false, but isAccented = true. Something Wrong!! Exit from checkConsistency() in RhythmEvent class");
	    System.exit(1);
	}
    }
}
	
	     
		
	    
/*	    
	if(randomNumber == 0) {
	    modifyIsSound();
	}
	else if(randomNumber == 1) {
	    modifyIsAccented();
	}
	else if(randomNumber == 2) {
	    modifyIsSoundAndIsAccented();
	}
	else {
	    System.out.println("Something wrong in randomlyModify() in rhythmEvent class");
	    System.exit(1);
	}
    }
}
*/

/* Gomi-------------------------------------------
    //private methods-------------------------------------------------------
    private void modifyIsSound() {
	isSound = !isSound;
        if(isSound == false) {
	    isAccented = false;
	}
    }

    private void isAccented() {
	isAccented = !isAccented;
	if(isAaccented == true) {
	    isSound = true;
	}
    }

    private void modifyIsSoundAndIsAccented() {
        isSound = !isSound;
	isAccented = !isAccented;	    
	
	
}

*/
