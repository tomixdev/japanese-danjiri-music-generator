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

public class DanjiriSonata {   
    public static void main (String[] args) throws FileNotFoundException {
	//=================================================================================================
	//-------------------------------Set Up Composition------------------------------------------------
	//=================================================================================================
	String[] instruments = {"HighBell", "LowBell", "Taiko", "TaikoEdge", "Kakegoe", "Hue", "PoliceHue"};
	Random rand = new Random(100);
	RhythmComposition danjiriSonata = new RhythmComposition("DanjiriSonata with Childhood memory of Hanshin railway", instruments, 400, rand.nextInt());
	Vector<RhythmPart> rhythmParts = danjiriSonata.getRhythmPartsVector();

	RhythmPart highBell = rhythmParts.elementAt(0);
	RhythmPart lowBell = rhythmParts.elementAt(1);
	RhythmPart taiko = rhythmParts.elementAt(2);
	RhythmPart taikoEdge = rhythmParts.elementAt(3);
	RhythmPart kakegoe = rhythmParts.elementAt(4);
	RhythmPart hue = rhythmParts.elementAt(5);
	RhythmPart policeHue = rhythmParts.elementAt(6);
	
	//=================================================================================================
	//------------------------------Danjiri Sonata, Minato Sakamoto------------------------------------
	//-------------------------Model, Mozart Piano Sonata No.8 A minor, 1st movement-------------------
	//
	//Note: Pulse No.357, Add kakegoe's Hit!!!
	//=================================================================================================

	//mm. 1 - 4 2 Elements
	RhythmPattern commonRhythm1 = new RhythmPattern("CommonRhythm1");
	highBell.addRhythmPattern(commonRhythm1); lowBell.addRhythmPattern(commonRhythm1);taiko.addRhythmPattern(commonRhythm1);kakegoe.addRestPulses(commonRhythm1);

	commonRhythm1.turnRestIntoHitRandomlyAndReplace(rand.nextInt());
	commonRhythm1.turnHitIntoAccentRandomlyAndReplace(rand.nextInt());
	commonRhythm1.turnHitIntoRestRandomlyAndReplace(rand.nextInt());
	commonRhythm1.turnHitIntoRestRandomlyAndReplace(rand.nextInt());
	highBell.addRhythmPattern(commonRhythm1); lowBell.addRhythmPattern(commonRhythm1);taiko.addRhythmPattern(commonRhythm1);kakegoe.addRhythmPattern("Kakegoe1");
	
       
	//mm. 5 - 8 2 Elements
	RhythmPattern highBell2 = new RhythmPattern("HighBell2"); RhythmPattern taiko2 = new RhythmPattern("Taiko2");
	highBell.addRhythmPattern(highBell2); lowBell.addRestPulses(highBell2); taiko.addRhythmPattern(taiko2);
	
	highBell2.turnRestIntoAccentRandomlyAndReplace(rand.nextInt());  taiko2.turnRestIntoAccentRandomlyAndReplace(rand.nextInt());
	highBell2.turnRestIntoHitRandomlyAndReplace(rand.nextInt());  taiko2.turnRestIntoHitRandomlyAndReplace(rand.nextInt());
	highBell.addRhythmPattern(highBell2); lowBell.addRestPulses(highBell2); taiko.addRhythmPattern(taiko2);

	//mm. 9 - 12 // 2 elements
	commonRhythm1 = new RhythmPattern("CommonRhythm1");
	highBell.addRhythmPattern(commonRhythm1); lowBell.addRhythmPattern(commonRhythm1);taiko.addRhythmPattern(commonRhythm1);

	danjiriSonata.lineUpAllPartsWithRest();
	
	commonRhythm1.turnRestIntoAccentRandomlyAndReplace(rand.nextInt());
	highBell.addRhythmPattern(commonRhythm1); lowBell.addRhythmPattern(commonRhythm1);taiko.addRhythmPattern(commonRhythm1);kakegoe.addRhythmPattern("Kakegoe1");

	//mm. 13 - 15 // 1.5 elements
	commonRhythm1 = new RhythmPattern("CommonRhythm1");
	commonRhythm1.turnHitIntoRestRandomlyAndReplace(rand.nextInt());
	commonRhythm1.turnHitIntoRestRandomlyAndReplace(rand.nextInt());
	highBell.addRhythmPattern(commonRhythm1);

	commonRhythm1.turnHitIntoRestRandomlyAndReplace(rand.nextInt());
	commonRhythm1.turnHitIntoRestRandomlyAndReplace(rand.nextInt());
	lowBell.addRhythmPattern(commonRhythm1);

	commonRhythm1.turnHitIntoRestRandomlyAndReplace(rand.nextInt());
	commonRhythm1.turnHitIntoRestRandomlyAndReplace(rand.nextInt());
	taiko.addRhythmPattern(commonRhythm1);

	commonRhythm1.removeRhythmEventRandomlyAndReplace(rand.nextInt());
	commonRhythm1.removeRhythmEventRandomlyAndReplace(rand.nextInt());
	commonRhythm1.removeRhythmEventRandomlyAndReplace(rand.nextInt());
	commonRhythm1.removeRhythmEventRandomlyAndReplace(rand.nextInt());
	commonRhythm1.removeRhythmEventRandomlyAndReplace(rand.nextInt());// fixed (added) September 1
	commonRhythm1.removeRhythmEventRandomlyAndReplace(rand.nextInt());// fixed (added) September 1
	commonRhythm1.removeRhythmEventRandomlyAndReplace(rand.nextInt());// fixed (added) September 1
	commonRhythm1.removeRhythmEventRandomlyAndReplace(rand.nextInt());// fixed (added) September 1

	highBell.addRestPulses(commonRhythm1); lowBell.addRhythmPattern(commonRhythm1);
	taiko.addRhythmPattern(commonRhythm1.randomlyTransposeAndCopy(rand.nextInt()));

	//mm. 16 - 19 // 2 elements
	danjiriSonata.lineUpAllPartsWithRest();

	System.out.println(taiko.getNumberOfPulsesSoFar());
		
	commonRhythm1 = new RhythmPattern("CommonRhythm1");
	commonRhythm1.turnHitIntoRestRandomlyAndReplace(rand.nextInt());
	commonRhythm1.turnHitIntoRestRandomlyAndReplace(rand.nextInt());
	commonRhythm1.turnHitIntoRestRandomlyAndReplace(rand.nextInt());
	commonRhythm1.turnHitIntoRestRandomlyAndReplace(rand.nextInt());
	commonRhythm1.randomlyTransposeAndReplace(rand.nextInt());

	hue.addRhythmPattern("Hue1");//32 Pulses
	kakegoe.addRhythmPattern("Kakegoe2"); // 32 Pulses
	highBell.addRhythmPattern(commonRhythm1); lowBell.addRhythmPattern(commonRhythm1); taiko.addRhythmPattern(commonRhythm1.randomlyTransposeAndCopy(rand.nextInt()));
	highBell.addRhythmPattern(commonRhythm1); lowBell.addRhythmPattern(commonRhythm1); taiko.addRhythmPattern(commonRhythm1.randomlyTransposeAndCopy(rand.nextInt()));

	danjiriSonata.lineUpAllPartsWithRest();

	//mm. 20 - 22 // 1.5 elements
	taikoEdge.addRhythmPattern("TaikoEdge1");//16 pulses

	RhythmPattern hue1 = new RhythmPattern("Hue1");
	for (int i = 1; i <= 8; i++) {   hue1.popRhythmEventAndReplace(); }// Pop 8 times
	hue.addRhythmPattern(hue1);//24Pulses

	highBell.addRestPulses("Taiko7"); lowBell.addRhythmPattern("Taiko7"); taiko.addRhythmPattern("Taiko7"); // 8 pulses
	highBell.addRestPulses(8); lowBell.addRestPulses(8); taiko.addRestPulses(8);
	highBell.addRhythmPattern("Taiko7"); lowBell.addRhythmPattern("Taiko7"); taiko.addRhythmPattern("Taiko7"); // 8 pulses

	danjiriSonata.lineUpAllPartsWithRest();

	// mm. 23 - // Second theme // Change tempo
	danjiriSonata.changeTempo(320);

	// mm. 23 - 26 // 2 elements
	System.out.println("At the end of measure 22, pulse no." + taiko.getNumberOfPulsesSoFar());
	
	RhythmPattern commonRhythm2 = new RhythmPattern("CommonRhythm2");
	highBell.addRestPulses(commonRhythm2); lowBell.addRhythmPattern(commonRhythm2); taiko.addRhythmPattern(commonRhythm2);

	commonRhythm2.turnAccentIntoRestRandomlyAndReplace(rand.nextInt());
	commonRhythm2.turnRestIntoHitRandomlyAndReplace(rand.nextInt());

	highBell.addRestPulses(commonRhythm2); lowBell.addRhythmPattern(commonRhythm2); taiko.addRhythmPattern(commonRhythm2);

	// mm.27 0.5 elements
	commonRhythm2 = new RhythmPattern("CommonRhythm2");
	for (int i = 1 ; i <= 8; i++) { commonRhythm2.popRhythmEventAndReplace(); } // Pop 8 times
	highBell.addRestPulses(commonRhythm2); lowBell.addRhythmPattern(commonRhythm2); taiko.addRhythmPattern(commonRhythm2);

	danjiriSonata.lineUpAllPartsWithRest();
	
	// mm. 28 - 34 // 3.5 elements (56pulses)
        ROUTGenerator generator = new ROUTGenerator(16, 7);
	RhythmPattern randomROUT = generator.randomlyChooseAnROUTAndCopy(rand.nextInt());	
	taiko.addRhythmPattern(randomROUT); taikoEdge.addRestPulses(randomROUT);//1st element for taiko

	randomROUT = randomROUT.turnRestIntoHitRandomlyButKeepROUTAndCopy(rand.nextInt());
	taiko.addRhythmPattern(randomROUT); taikoEdge.addRestPulses(randomROUT);//2nd element for taiko

	randomROUT = randomROUT.turnRestIntoHitRandomlyButKeepROUTAndCopy(rand.nextInt());
	taiko.addRhythmPattern(randomROUT); taikoEdge.addRestPulses(randomROUT);//3rd element for taiko

	taikoEdge.addRhythmPattern("TaikoEdge1Variant");//3.5th lement for taiko
	taiko.addRestPulses(4);

	generator = new ROUTGenerator(16, 9);
	randomROUT = generator.randomlyChooseAnROUTAndCopy(rand.nextInt());
        lowBell.addRhythmPattern(randomROUT);

	randomROUT = randomROUT.turnHitIntoRestRandomlyButKeepROUTAndCopy(rand.nextInt());
	lowBell.addRhythmPattern(randomROUT);

	randomROUT = randomROUT.turnHitIntoRestRandomlyButKeepROUTAndCopy(rand.nextInt());
	lowBell.addRhythmPattern(randomROUT);

	hue.addRestPulses(36);
	hue.addRhythmPattern("Hue1");

	kakegoe.addRestPulses(44);
	kakegoe.addRhythmPattern("Kakegoe1");

	highBell.lineUpWithAnotherRhythmPartWithRest(taiko);
	lowBell.lineUpWithAnotherRhythmPartWithRest(taiko);


	//danjiriSonata.lineUpAllPartsWithRest();

	//mm.35 - 39 // 2.5 elements //(Instrumental exchange with mm. 40- 44) //40 pulses
	//Low Bell
	generator = new ROUTGenerator(16, 7);
	RhythmPattern randomROUT1 = generator.randomlyChooseAnROUTAndCopy(rand.nextInt());
	lowBell.addRhythmPattern(randomROUT1);

	RhythmPattern randomROUT2 = randomROUT1.turnRestIntoHitRandomlyButKeepROUTAndCopy(rand.nextInt());
	lowBell.addRhythmPattern(randomROUT2);

	lowBell.addRestPulses(8);
	
	//High Bell
	RhythmPattern highBell3_1 = new RhythmPattern("HighBell3");
	highBell.addRhythmPattern(highBell3_1);
	
	RhythmPattern highBell3_2 = highBell3_1.turnRestIntoHitRandomlyAndCopy(rand.nextInt());
	highBell.addRhythmPattern(highBell3_2);

	highBell.addRestPulses(8);

	//Hue
	hue.addRestPulses(4);/////////////////////////!!!!! originally 20
	hue.addRhythmPattern("Hue1");

	//Kakegoe
	kakegoe.addRestPulses(24); ////////////originally 20!!!
	kakegoe.addRhythmPattern("Kakegoe1");

	//TaikoEdge
	taikoEdge.addRestPulses(12);//// Originally "randomROUT1" = 16 pulses!!!!!!!!!!!!!!!!!!!!!
	taikoEdge.addRestPulses(randomROUT2);
	taikoEdge.addRhythmPattern("TaikoEdge1Variant");
	
	//mm. 40 - 44 // 2.5 elements //(instrumental exchange with mm. 35 - 39)
	highBell.addRhythmPattern(randomROUT1);
	highBell.addRhythmPattern(randomROUT2);

	lowBell.addRhythmPattern(highBell3_1);
	lowBell.addRhythmPattern(highBell3_2);

	danjiriSonata.lineUpAllPartsWithRest();

	hue.copyAndAdd(hue, hue.getNumberOfPulsesSoFar() - 39, hue.getNumberOfPulsesSoFar() - 24);
	kakegoe.copyAndAdd(kakegoe, lowBell.getNumberOfPulsesSoFar() - 39, lowBell.getNumberOfPulsesSoFar() - 24);
	taikoEdge.copyAndAdd(taikoEdge, lowBell.getNumberOfPulsesSoFar() - 39, lowBell.getNumberOfPulsesSoFar() - 24);

	highBell.addRestPulses(8);
	lowBell.addRestPulses(8);
	taiko.addRestPulses(8);

	System.out.println("At the end of measure 44, the pulse number is " + taiko.getNumberOfPulsesSoFar());

	//mm. 45 - 48 // 2 elements // Closing Phrase, Elements from the first theme (the first half of the first theme)

	commonRhythm2 = new RhythmPattern("CommonRhythm2");
	commonRhythm2.turnHitIntoRestRandomlyAndReplace(rand.nextInt());
	commonRhythm2.turnRestIntoHitRandomlyAndReplace(rand.nextInt());
	taiko.addRhythmPattern(commonRhythm2); 

	commonRhythm2.turnHitIntoRestRandomlyAndReplace(rand.nextInt());
	commonRhythm2.turnRestIntoHitRandomlyAndReplace(rand.nextInt());
	taiko.addRhythmPattern(commonRhythm2);

	taikoEdge.lineUpWithAnotherRhythmPartWithRest(taiko);

	commonRhythm1 = new RhythmPattern("CommonRhythm1");
	commonRhythm1.turnHitIntoRestRandomlyAndReplace(rand.nextInt());
	commonRhythm1.turnRestIntoHitRandomlyAndReplace(rand.nextInt());
	highBell.addRhythmPattern(commonRhythm1); lowBell.addRhythmPattern(commonRhythm1);

	commonRhythm1.turnHitIntoRestRandomlyAndReplace(rand.nextInt());
	commonRhythm1.turnRestIntoHitRandomlyAndReplace(rand.nextInt());
	highBell.addRhythmPattern(commonRhythm1); lowBell.addRhythmPattern(commonRhythm1);

	RhythmPattern kakegoe2 = new RhythmPattern("Kakegoe2");
	for(int i = 1; i <=4; i++) {kakegoe2.popRhythmEventAndReplace();} // pop 4 times
	
	kakegoe.addRhythmPattern(kakegoe2);
	
	hue.addRhythmPattern("Hue1");

	//mm. 49 // 0.5 elements // C major chord. closing

	highBell.addRhythmPattern("LowBell1");
	lowBell.addRhythmPattern("LowBell1");
	taiko.addRhythmPattern("LowBell1");

	danjiriSonata.lineUpAllPartsWithRest();

	System.out.println();
	System.out.println("==========================================================================");
	System.out.println("Exposition ends at Pulse No." + highBell.getNumberOfPulsesSoFar() + "!!");
	System.out.println("==========================================================================");
	System.out.println();

	//===================================================================================
	/////////////////////////////Beginning Development//////////////////////////////////////
	//==================================================================================
	danjiriSonata.changeTempo(400);

	//mm. 50 - 53
	commonRhythm1 = new RhythmPattern("CommonRhythm1");
	commonRhythm1.rhythmicallyTransposeAndReplace(6);
	
	highBell.addRhythmPattern(commonRhythm1); lowBell.addRhythmPattern(commonRhythm1);taiko.addRhythmPattern(commonRhythm1);

	commonRhythm1.turnHitIntoRestRandomlyAndReplace(rand.nextInt());
	commonRhythm1.turnRestIntoAccentRandomlyAndReplace(rand.nextInt());
	commonRhythm1.turnHitIntoRestRandomlyAndReplace(rand.nextInt());
	commonRhythm1.turnAccentIntoHitRandomlyAndReplace(rand.nextInt());
	commonRhythm1.turnRestIntoAccentRandomlyAndReplace(rand.nextInt());
	
	highBell.addRhythmPattern(commonRhythm1); lowBell.addRhythmPattern(commonRhythm1);taiko.addRhythmPattern(commonRhythm1);

	danjiriSonata.lineUpAllPartsWithRest();

	//mm/54-57
	generator = new ROUTGenerator (16, 13);
	RhythmPattern x = generator.randomlyChooseAnROUTAndCopy(rand.nextInt());
	
	lowBell.addRhythmPattern(x);
	lowBell.addRhythmPattern(x.turnHitIntoAccentRandomlyAndCopy(rand.nextInt()).turnHitIntoAccentRandomlyAndCopy(rand.nextInt()));
	
	taiko.addRhythmPattern(x.augmentAndCopy(2));
	
	hue.addRhythmPattern("Hue1");
	kakegoe.addRhythmPattern("Kakegoe2");

	danjiriSonata.lineUpAllPartsWithRest();

	//mm. 58 - 61 // 32 pulses
	System.out.println();
	System.out.println("===========================================================================================");
	System.out.println("From Pulse No. " + taiko.getNumberOfPulsesSoFar() + ", FORTESSIMO!! Correspond to mm. 58-61");
	System.out.println("===========================================================================================");
	System.out.println();

	///// Added September 1
	commonRhythm1 = new RhythmPattern("CommonRhythm1");
	for(int i = 1; i <= 8; i++) {commonRhythm1.popRhythmEventAndReplace();}
	commonRhythm1.rhythmicallyTransposeAndReplace(1);
	
	lowBell.addRhythmPattern(commonRhythm1); highBell.addRhythmPattern(commonRhythm1); // 8 pulses

	for(int i = 1; i <= 4; i++) {commonRhythm1.popRhythmEventAndReplace();}
	lowBell.addRhythmPattern(commonRhythm1); highBell.addRestPulses(commonRhythm1);  // 4 pulses

	commonRhythm1.rhythmicallyTransposeAndReplace(1);
	lowBell.addRestPulses(commonRhythm1); highBell.addRhythmPattern(commonRhythm1);  // 4 pulses

	commonRhythm1.rhythmicallyTransposeAndReplace(1);
	lowBell.addRhythmPattern(commonRhythm1); highBell.addRestPulses(commonRhythm1); // 4 pulses

	commonRhythm1.rhythmicallyTransposeAndReplace(1);
	lowBell.addRestPulses(commonRhythm1); highBell.addRhythmPattern(commonRhythm1); // 4 pulses

	commonRhythm1.rhythmicallyTransposeAndReplace(1);
	lowBell.addRhythmPattern(commonRhythm1); highBell.addRestPulses(commonRhythm1); // 4 pulses

	commonRhythm1.rhythmicallyTransposeAndReplace(1);
	lowBell.addRestPulses(commonRhythm1); highBell.addRhythmPattern(commonRhythm1); // 4 pulses
	    
	commonRhythm2 = new RhythmPattern("CommonRhythm2");
	commonRhythm2.retrogradeAndReplace();
	taiko.addRhythmPattern(commonRhythm2);
	commonRhythm2.turnRestIntoAccentRandomlyAndReplace(rand.nextInt());
	taiko.addRhythmPattern(commonRhythm2);

	/*
	commonRhythm1 = new RhythmPattern("CommonRhythm1");
	commonRhythm1.retrogradeAndReplace();
	for(int i = 1; i <= 8; i++) { commonRhythm1.popRhythmEventAndReplace(); }	
	commonRhythm1.turnHitIntoAccentRandomlyAndReplace(rand.nextInt());

	lowBell.addRhythmPattern(commonRhythm1); highBell.addRestPulses(commonRhythm1);//8 pulses

	commonRhythm1.rhythmicallyTransposeAndReplace(1);
	lowBell.addRestPulses(commonRhythm1);    highBell.addRhythmPattern(commonRhythm1); // 8 pulses

	commonRhythm1.rhythmicallyTransposeAndReplace(1);
	lowBell.addRhythmPattern(commonRhythm1); highBell.addRestPulses(commonRhythm1); // 8 pulses

	commonRhythm1.rhythmicallyTransposeAndReplace(1);
	lowBell.addRestPulses(commonRhythm1);    highBell.addRhythmPattern(commonRhythm1); // 8 pulses
	*/

	
	//danjiriSonata.lineUpAllPartsWithRest();

	//mm. 62 -65
	System.out.println();
	System.out.println("===========================================================================================");
	System.out.println("From Pulse No. " + taiko.getNumberOfPulsesSoFar() + ", Pianissimo!! Correspond to mm. 62-65");
	System.out.println("===========================================================================================");
	System.out.println();
	
	highBell.addRhythmPattern(highBell.transposeASectionAndCopy(highBell.getNumberOfPulsesSoFar() - 31, highBell.getNumberOfPulsesSoFar() - 24, 3));
	highBell.addRhythmPattern(highBell.transposeASectionAndCopy(highBell.getNumberOfPulsesSoFar() - 31, highBell.getNumberOfPulsesSoFar() - 28, 3));
	highBell.addRhythmPattern(highBell.transposeASectionAndCopy(highBell.getNumberOfPulsesSoFar() - 31, highBell.getNumberOfPulsesSoFar() - 28, 3));
	highBell.addRhythmPattern(highBell.transposeASectionAndCopy(highBell.getNumberOfPulsesSoFar() - 31, highBell.getNumberOfPulsesSoFar() - 28, 3));
	highBell.addRhythmPattern(highBell.transposeASectionAndCopy(highBell.getNumberOfPulsesSoFar() - 31, highBell.getNumberOfPulsesSoFar() - 28, 3));
	highBell.addRhythmPattern(highBell.transposeASectionAndCopy(highBell.getNumberOfPulsesSoFar() - 31, highBell.getNumberOfPulsesSoFar() - 28, 3));
	highBell.addRhythmPattern(highBell.transposeASectionAndCopy(highBell.getNumberOfPulsesSoFar() - 31, highBell.getNumberOfPulsesSoFar() - 28, 3));
		
	lowBell.addRhythmPattern(lowBell.transposeASectionAndCopy(lowBell.getNumberOfPulsesSoFar() - 31, lowBell.getNumberOfPulsesSoFar() - 24, 3));
	lowBell.addRhythmPattern(lowBell.transposeASectionAndCopy(lowBell.getNumberOfPulsesSoFar() - 31, lowBell.getNumberOfPulsesSoFar() - 28, 3));
	lowBell.addRhythmPattern(lowBell.transposeASectionAndCopy(lowBell.getNumberOfPulsesSoFar() - 31, lowBell.getNumberOfPulsesSoFar() - 28, 3));
	lowBell.addRhythmPattern(lowBell.transposeASectionAndCopy(lowBell.getNumberOfPulsesSoFar() - 31, lowBell.getNumberOfPulsesSoFar() - 28, 3));
	lowBell.addRhythmPattern(lowBell.transposeASectionAndCopy(lowBell.getNumberOfPulsesSoFar() - 31, lowBell.getNumberOfPulsesSoFar() - 28, 3));
	lowBell.addRhythmPattern(lowBell.transposeASectionAndCopy(lowBell.getNumberOfPulsesSoFar() - 31, lowBell.getNumberOfPulsesSoFar() - 28, 3));
	lowBell.addRhythmPattern(lowBell.transposeASectionAndCopy(lowBell.getNumberOfPulsesSoFar() - 31, lowBell.getNumberOfPulsesSoFar() - 28, 3));

	taiko.addRhythmPattern(taiko.transposeASectionAndCopy(taiko.getNumberOfPulsesSoFar() - 31, taiko.getNumberOfPulsesSoFar(), 3));

	danjiriSonata.lineUpAllPartsWithRest();
	
	//mm. 66-69
 	System.out.println();
	System.out.println("===========================================================================================");
	System.out.println("From Pulse No. " + taiko.getNumberOfPulsesSoFar() + ", FORTESSIMO!! Correspond to mm. 66-69");
	System.out.println("===========================================================================================");
	System.out.println();

	highBell.addRhythmPattern(highBell.transposeASectionAndCopy(highBell.getNumberOfPulsesSoFar() - 31, highBell.getNumberOfPulsesSoFar() - 24, 3));
	highBell.addRhythmPattern(highBell.transposeASectionAndCopy(highBell.getNumberOfPulsesSoFar() - 31, highBell.getNumberOfPulsesSoFar() - 28, 3));
	highBell.addRhythmPattern(highBell.transposeASectionAndCopy(highBell.getNumberOfPulsesSoFar() - 31, highBell.getNumberOfPulsesSoFar() - 28, 3));
	highBell.addRhythmPattern(highBell.transposeASectionAndCopy(highBell.getNumberOfPulsesSoFar() - 31, highBell.getNumberOfPulsesSoFar() - 28, 3));
	highBell.addRhythmPattern(highBell.transposeASectionAndCopy(highBell.getNumberOfPulsesSoFar() - 31, highBell.getNumberOfPulsesSoFar() - 28, 3));
	highBell.addRhythmPattern(highBell.transposeASectionAndCopy(highBell.getNumberOfPulsesSoFar() - 31, highBell.getNumberOfPulsesSoFar() - 28, 3));
	highBell.addRhythmPattern(highBell.transposeASectionAndCopy(highBell.getNumberOfPulsesSoFar() - 31, highBell.getNumberOfPulsesSoFar() - 28, 3));
		
	lowBell.addRhythmPattern(lowBell.transposeASectionAndCopy(lowBell.getNumberOfPulsesSoFar() - 31, lowBell.getNumberOfPulsesSoFar() - 24, 3));
	lowBell.addRhythmPattern(lowBell.transposeASectionAndCopy(lowBell.getNumberOfPulsesSoFar() - 31, lowBell.getNumberOfPulsesSoFar() - 28, 3));
	lowBell.addRhythmPattern(lowBell.transposeASectionAndCopy(lowBell.getNumberOfPulsesSoFar() - 31, lowBell.getNumberOfPulsesSoFar() - 28, 3));
	lowBell.addRhythmPattern(lowBell.transposeASectionAndCopy(lowBell.getNumberOfPulsesSoFar() - 31, lowBell.getNumberOfPulsesSoFar() - 28, 3));
	lowBell.addRhythmPattern(lowBell.transposeASectionAndCopy(lowBell.getNumberOfPulsesSoFar() - 31, lowBell.getNumberOfPulsesSoFar() - 28, 3));
	lowBell.addRhythmPattern(lowBell.transposeASectionAndCopy(lowBell.getNumberOfPulsesSoFar() - 31, lowBell.getNumberOfPulsesSoFar() - 28, 3));
	lowBell.addRhythmPattern(lowBell.transposeASectionAndCopy(lowBell.getNumberOfPulsesSoFar() - 31, lowBell.getNumberOfPulsesSoFar() - 28, 3));

	taiko.addRhythmPattern(taiko.transposeASectionAndCopy(taiko.getNumberOfPulsesSoFar() - 31, taiko.getNumberOfPulsesSoFar(), 3));
	
	///add irregularity in non-main instruments (instruments other than highBell, lowBell, or Taiko)!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! From mm.66-69
        hue.addRhythmPattern("Hue2");// 8 pulses
	hue.addRhythmPattern("Hue2");// 8 pulses
	hue.addRhythmPattern("Hue3"); // 16 pulses

	danjiriSonata.lineUpAllPartsWithRest();
	
	//mm.70 - 72 3 times of 8-pulse sequence 3 times
	generator = new ROUTGenerator(8, 3);
	generator.writeAllPossibleROUTsToFile();
	RhythmPattern aMotif = generator.chooseAnROUTAndCopy(3);
	aMotif.turnHitIntoAccentRandomlyAndReplace(rand.nextInt());
	
	System.out.println();
	System.out.println("============================================================================");
	System.out.println("From Taiko Pulse No. " + taiko.getNumberOfPulsesSoFar() + " , articulate accents with taiko edge. Use taiko edge!!");
	System.out.println("============================================================================");
	System.out.println();

	taiko.addRhythmPattern(aMotif);
	aMotif.rhythmicallyTransposeAndReplace(1);
	taiko.addRhythmPattern(aMotif);
	aMotif.rhythmicallyTransposeAndReplace(1);
	taiko.addRhythmPattern(aMotif);

	commonRhythm2 = new RhythmPattern("CommonRhythm2");
	for(int i = 1; i <= 8; i++) { commonRhythm2.removeRhythmEventAtTheTopAndReplace(); }
	
	lowBell.addRhythmPattern(commonRhythm2); highBell.addRhythmPattern(commonRhythm2);
	commonRhythm2.rhythmicallyTransposeAndReplace(1);
	lowBell.addRhythmPattern(commonRhythm2); highBell.addRhythmPattern(commonRhythm2);
	commonRhythm2.rhythmicallyTransposeAndReplace(1);
	lowBell.addRhythmPattern(commonRhythm2); highBell.addRhythmPattern(commonRhythm2);

	danjiriSonata.lineUpAllPartsWithRest();

	//mm. 73 transit (very strong, and intense)
	RhythmPattern successive8Accents = new RhythmPattern(0);
	for(int i = 1; i <= 8; i++){RhythmEvent toAdd = new RhythmEvent(true, true); successive8Accents.addRhythmEventAndReplace(toAdd);}

	highBell.addRhythmPattern(successive8Accents);
	lowBell.addRhythmPattern(successive8Accents);
	taikoEdge.addRhythmPattern(successive8Accents);
	taiko.addRestPulses(successive8Accents);
	hue.addRhythmPattern(successive8Accents);

	danjiriSonata.lineUpAllPartsWithRest();

	//mm.74 -77
	aMotif.rhythmicallyTransposeAndReplace(3);
	taiko.addRhythmPattern(aMotif);
	aMotif.rhythmicallyTransposeAndReplace(5);
	taiko.addRhythmPattern(aMotif);
	aMotif.rhythmicallyTransposeAndReplace(3);
	taiko.addRhythmPattern(aMotif);
	aMotif.rhythmicallyTransposeAndReplace(5);
	taiko.addRhythmPattern(aMotif);

       
	RhythmPattern aCadencialPattern = new RhythmPattern(0);
	RhythmEvent accent = new RhythmEvent(true, true);
	RhythmEvent hit = new RhythmEvent(true, false);
	RhythmEvent rest = new RhythmEvent(false, false);	
	aCadencialPattern.addRhythmEventAndReplace(accent);aCadencialPattern.addRhythmEventAndReplace(rest);aCadencialPattern.addRhythmEventAndReplace(rest);aCadencialPattern.addRhythmEventAndReplace(rest);aCadencialPattern.addRhythmEventAndReplace(rest);aCadencialPattern.addRhythmEventAndReplace(rest);
	aCadencialPattern.addRhythmEventAndReplace(hit);aCadencialPattern.addRhythmEventAndReplace(hit); // A - - - - - X X

	taikoEdge.addRhythmPattern(aCadencialPattern);
	taikoEdge.addRhythmPattern(aCadencialPattern);
	taikoEdge.addRhythmPattern(aCadencialPattern);
	taikoEdge.addRhythmPattern(aCadencialPattern);

	commonRhythm2.rhythmicallyTransposeAndReplace(3);
	lowBell.addRhythmPattern(commonRhythm2);
	highBell.addRestPulses(commonRhythm2);
	commonRhythm2.rhythmicallyTransposeAndReplace(5);
	lowBell.addRestPulses(commonRhythm2);
	highBell.addRhythmPattern(commonRhythm2);
	commonRhythm2.rhythmicallyTransposeAndReplace(3);
	lowBell.addRhythmPattern(commonRhythm2);
	highBell.addRestPulses(commonRhythm2);
	commonRhythm2.rhythmicallyTransposeAndReplace(5);
	lowBell.addRestPulses(commonRhythm2);
	highBell.addRhythmPattern(commonRhythm2);
	
	kakegoe.lineUpWithAnotherRhythmPartWithRest(highBell);
	hue.lineUpWithAnotherRhythmPartWithRest(lowBell);
	
	//mm.78 -79
	kakegoe.addRhythmPattern("Kakegoe1");  ///Kakegoe solo for 8 pulses (in total 16 pulses)

	taikoEdge.addRhythmPattern(successive8Accents);
	taiko.addRestPulses(successive8Accents);
	lowBell.addRhythmPattern(successive8Accents);
	highBell.addRestPulses(successive8Accents);
	hue.addRhythmPattern(successive8Accents);

	taikoEdge.addRestPulses(8); taiko.addRestPulses(8); lowBell.addRestPulses(8); highBell.addRestPulses(8);hue.addRestPulses(8);

	danjiriSonata.lineUpAllPartsWithRest();


	//================================================================================================
	//------------------------------Recapitulation---------------------------------------------------
	//===============================================================================================
	System.out.println();
	System.out.println("=========================================================================");
	System.out.println("Recapitulation starts at Pulse No. " + taiko.getNumberOfPulsesSoFar() + "!!!!");
	System.out.println("=========================================================================");
	System.out.println();

	//mm. 80- 87
	taiko.copyAndAdd(taiko, 1, 64);
	taikoEdge.copyAndAdd(taikoEdge, 1, 64);
	lowBell.copyAndAdd(lowBell, 1, 64);
	highBell.copyAndAdd(highBell, 1, 64);
	kakegoe.addRhythmPattern("Kakegoe2"); kakegoe.addRestPulses("Kakegoe2");
	System.out.println(hue.getNumberOfPulsesSoFar());
	hue.copyAndAdd(hue, 1, 64);

	danjiriSonata.lineUpAllPartsWithRest();

	//mm. 88 - 93 // 3 elements
        System.out.println("m.88,   " + taikoEdge.getNumberOfPulsesSoFar());
	commonRhythm2 = new RhythmPattern("CommonRhythm2");
	commonRhythm2.rhythmicallyTransposeAndReplace(5);
	commonRhythm2.turnHitIntoRestRandomlyAndReplace(rand.nextInt());
	commonRhythm2.turnAccentIntoRestRandomlyAndReplace(rand.nextInt());
	commonRhythm2.turnRestIntoHitRandomlyAndReplace(rand.nextInt());
	commonRhythm2.turnRestIntoAccentRandomlyAndReplace(rand.nextInt() + 64);	
	highBell.addRhythmPattern(commonRhythm2);

	commonRhythm2.rhythmicallyTransposeAndReplace(5);
	highBell.addRhythmPattern(commonRhythm2);

	commonRhythm2.rhythmicallyTransposeAndReplace(5);
	highBell.addRhythmPattern(commonRhythm2);

	taiko.addRhythmEvent('A'); taiko.addRestPulses(15); // in total 16 pulses

	commonRhythm1 = new RhythmPattern("CommonRhythm1");
	commonRhythm1.turnHitIntoRestRandomlyAndReplace(rand.nextInt());
	commonRhythm1.turnHitIntoRestRandomlyAndReplace(rand.nextInt());
	commonRhythm1.turnRestIntoAccentRandomlyAndReplace(rand.nextInt() +65);
	commonRhythm1.rhythmicallyTransposeAndReplace(3);
	taikoEdge.addRhythmPattern(commonRhythm1);

	commonRhythm1.turnRestIntoHitRandomlyAndReplace(rand.nextInt());
	commonRhythm1.rhythmicallyTransposeAndReplace(5);
	taikoEdge.addRhythmPattern(commonRhythm1); taiko.addRestPulses(commonRhythm1);

	commonRhythm1.turnHitIntoRestRandomlyAndReplace(rand.nextInt());
	commonRhythm1.turnHitIntoRestRandomlyAndReplace(rand.nextInt());
	commonRhythm1.turnRestIntoAccentRandomlyAndReplace(rand.nextInt() + 5);
	commonRhythm1.rhythmicallyTransposeAndReplace(3);
	taikoEdge.addRhythmPattern(commonRhythm1); taiko.addRestPulses(commonRhythm1);

	danjiriSonata.lineUpAllPartsWithRest();

	//mm. 94 - 95
	System.out.println("m94,," + taiko.getNumberOfPulsesSoFar());
	for(int i = 0; i < rhythmParts.size(); i++) {
	    RhythmPart aRhythmPart = rhythmParts.elementAt(i);
	    aRhythmPart.augmentedlyImitate(aRhythmPart, 105, 112, 0, 2);
	    //aRhythmPart.randomize1Gaussian(aRhythmPart.getNumberOfPulsesSoFar() - 7, aRhythmPart.getNumberOfPulsesSoFar(), 4);
	}

	//danjiriSonata.lineUpAllPartsWithRest();

	//mm. 96
	generator = new ROUTGenerator(8, 3);
	RhythmPattern a = generator.chooseAnROUTAndCopy(1);
	RhythmPattern b = generator.chooseAnROUTAndCopy(2);
	RhythmPattern c = generator.chooseAnROUTAndCopy(3);
	RhythmPattern z = generator.chooseAnROUTAndCopy(0);

	highBell.addRhythmPattern(z);
	taikoEdge.addRhythmPattern(c);
	taiko.addRhythmPattern(a);
	lowBell.addRhythmPattern(b);

	
	danjiriSonata.lineUpAllPartsWithRest();

     
	//mm. 97 - 103 // exactly transposed from exposition
	System.out.println("At the end of m. 96, pulse no." + taiko.getNumberOfPulsesSoFar());
	RhythmPart[] rhythmPartsToTransposeFromExposition = new RhythmPart[] {taiko, taikoEdge, lowBell, highBell};
	for(int i = 0; i < rhythmPartsToTransposeFromExposition.length; i++) {
	    RhythmPart aRhythmPart = rhythmPartsToTransposeFromExposition[i];
	    aRhythmPart.addRhythmPattern(aRhythmPart.getASection(120 + 1, 120 + 16).rhythmicallyTransposeAndCopy(4));
	    aRhythmPart.addRhythmPattern(aRhythmPart.getASection(136 + 1, 136 + 16).rhythmicallyTransposeAndCopy(4));
	    aRhythmPart.addRhythmPattern(aRhythmPart.getASection(152 + 1, 152 + 8).rhythmicallyTransposeAndCopy(4));
	    aRhythmPart.addRhythmPattern(aRhythmPart.getASection(160 + 1, 160 + 8).rhythmicallyTransposeAndCopy(4));
	    aRhythmPart.addRhythmPattern(aRhythmPart.getASection(168 + 1, 168 + 8).rhythmicallyTransposeAndCopy(4));
	}

	RhythmPart[] rhythmPartsToJustCopyFromExposition = new RhythmPart[] {hue, kakegoe};
	for(int i = 0; i < rhythmPartsToJustCopyFromExposition.length; i++) {
	    RhythmPart aRhythmPart = rhythmPartsToJustCopyFromExposition[i];
	    aRhythmPart.copyAndAdd(aRhythmPart, 121, 121 +55);
	}

	danjiriSonata.lineUpAllPartsWithRest();

	//mm. 104 - mm.125 // almost the exact transposition of exposition. But slightly different from the exposition.
	danjiriSonata.changeTempo(320);

	System.out.println("The beginning of the recapitulation second theme. Pulse No." + (taiko.getNumberOfPulsesSoFar() + 1));
	
	for(int i = 0; i < rhythmPartsToTransposeFromExposition.length; i++) {
	    RhythmPart aRhythmPart = rhythmPartsToTransposeFromExposition[i];	    
	    aRhythmPart.addRhythmPattern(aRhythmPart.getASection(176 + 1, 176 + 16).rhythmicallyTransposeAndCopy(4));
	    aRhythmPart.addRhythmPattern(aRhythmPart.getASection(192 + 1, 192 + 16).rhythmicallyTransposeAndCopy(4));
	    aRhythmPart.addRhythmPattern(aRhythmPart.getASection(208 + 1, 208 + 16).rhythmicallyTransposeAndCopy(4));
	    aRhythmPart.addRhythmPattern(aRhythmPart.getASection(224 + 1, 224 + 16).rhythmicallyTransposeAndCopy(4));
	    aRhythmPart.addRhythmPattern(aRhythmPart.getASection(240 + 1, 240 + 16).rhythmicallyTransposeAndCopy(4));
	    aRhythmPart.addRhythmPattern(aRhythmPart.getASection(256 + 1, 256 + 16).rhythmicallyTransposeAndCopy(4));
	    aRhythmPart.addRhythmPattern(aRhythmPart.getASection(272 + 1, 272 + 16).rhythmicallyTransposeAndCopy(4));
	    aRhythmPart.addRhythmPattern(aRhythmPart.getASection(288 + 1, 288 + 16).rhythmicallyTransposeAndCopy(4));
	    aRhythmPart.addRhythmPattern(aRhythmPart.getASection(304 + 1, 304 + 8).rhythmicallyTransposeAndCopy(4));
	    aRhythmPart.addRhythmPattern(aRhythmPart.getASection(312 + 1, 312 + 16).rhythmicallyTransposeAndCopy(4));
	    aRhythmPart.addRhythmPattern(aRhythmPart.getASection(328 + 1, 328 + 16).rhythmicallyTransposeAndCopy(4));
	    aRhythmPart.addRhythmPattern(aRhythmPart.getASection(344 + 1, 344 + 4).rhythmicallyTransposeAndCopy(4));
	    
	    aRhythmPart.randomize1Gaussian(aRhythmPart.getNumberOfPulsesSoFar() - (348 - 177 + 1), aRhythmPart.getNumberOfPulsesSoFar(), 10);
	}

	for (int i = 0; i < rhythmPartsToJustCopyFromExposition.length; i++) {
	    RhythmPart aRhythmPart = rhythmPartsToJustCopyFromExposition[i];
	    aRhythmPart.copyAndAdd(aRhythmPart, 177, 348);
	//aRhythmPart.randomize1Gaussian(aRhythmPart.getNumberOfPulsesSoFar() - (16 * 11) + 1, aRhythmPart.getNumberOfPulsesSoFar(), 10);
	}

	danjiriSonata.lineUpAllPartsWithRest();

	System.out.println("at the end of mm. 125, the pulese number is " + taiko.getNumberOfPulsesSoFar());

	//mm.126 -127// Sudden change to coda // 1 elements
	danjiriSonata.changeTempo(480); // Sudden tempo change
	
	RhythmPattern sixRests = new RhythmPattern(0);
	for(int i = 1; i <= 6; i++) {RhythmEvent toAdd = new RhythmEvent (false, false); sixRests.addRhythmEventAndReplace(toAdd); }

	taiko.addRhythmPattern(sixRests);
	lowBell.addRhythmPattern(sixRests);
	highBell.addRhythmPattern(sixRests);
	
	danjiriSonata.lineUpAllPartsWithRest();

	generator = new ROUTGenerator(16, 9); // ROUT no. 0 - 7 are generated
	generator.writeAllPossibleROUTsToFile();
	RhythmPattern d = generator.chooseAnROUTAndCopy(1);
	RhythmPattern e = generator.chooseAnROUTAndCopy(2);
	RhythmPattern f = generator.chooseAnROUTAndCopy(3);
	RhythmPattern g = generator.chooseAnROUTAndCopy(4);
	RhythmPattern h = generator.chooseAnROUTAndCopy(5);
	RhythmPattern m = generator.chooseAnROUTAndCopy(6);

	highBell.addRhythmPattern(d);
	lowBell.addRhythmPattern(h);
	taiko.addRhythmPattern(e);
	taikoEdge.addRhythmPattern(g);
	policeHue.addRhythmPattern(f);
	hue.addRhythmPattern(m);

	danjiriSonata.lineUpAllPartsWithRest();


	//mm. 128 // Very strong cadencial motion
	danjiriSonata.changeTempo(240);

	System.out.println();
	System.out.println("===================================================================");
	System.out.println("The section of tempo 240 needs rit.!!");
	System.out.println("===================================================================");
	System.out.println();

	RhythmPattern fourRestsAndFourAccents = new RhythmPattern (0);
        for(int i = 1; i <= 4; i++) {RhythmEvent toAdd = new RhythmEvent(false,false); fourRestsAndFourAccents.addRhythmEventAndReplace(toAdd);}
	for(int i = 1; i <= 4; i++) {RhythmEvent toAdd = new RhythmEvent(true, true); fourRestsAndFourAccents.addRhythmEventAndReplace(toAdd);}

	highBell.addRhythmPattern(fourRestsAndFourAccents);
	lowBell.addRhythmPattern(fourRestsAndFourAccents);
	taiko.addRhythmPattern(fourRestsAndFourAccents);
	taikoEdge.addRhythmPattern(fourRestsAndFourAccents);
	policeHue.addRhythmPattern(fourRestsAndFourAccents);
	hue.addRhythmPattern(fourRestsAndFourAccents);

	
	danjiriSonata.lineUpAllPartsWithRest();

	System.out.println("at the end of m 128, pulse no." + taiko.getNumberOfPulsesSoFar());

	//mm. 129 - 133 //Exact transposition of Exposition
	danjiriSonata.changeTempo(400);
	
	for(int i = 0; i < rhythmPartsToTransposeFromExposition.length; i++) {
	    RhythmPart aRhythmPart = rhythmPartsToTransposeFromExposition[i];
	    aRhythmPart.addRhythmPattern(aRhythmPart.getASection(349, 348 + 16).rhythmicallyTransposeAndCopy(4));
	    aRhythmPart.addRhythmPattern(aRhythmPart.getASection(348 + 16 + 1, 348 + 32).rhythmicallyTransposeAndCopy(4));
	}

	for (int i = 0; i < rhythmPartsToJustCopyFromExposition.length; i++) {
	    RhythmPart aRhythmPart = rhythmPartsToJustCopyFromExposition[i];
	    aRhythmPart.copyAndAdd(aRhythmPart, 349, 348 + 32);
	}

	for(int i = 0; i < rhythmParts.size(); i++) {
	    RhythmPart aRhythmPart = rhythmParts.elementAt(i);
	    aRhythmPart.copyAndAdd(aRhythmPart, 348 + 33, 348 + 40);
	}

	
	//One more thing added by me (not in mozart)
	highBell.copyAndAdd(highBell, 348 + 33, 348 + 40);
	lowBell.copyAndAdd(lowBell, 348 + 33, 348 +40);
	taiko.copyAndAdd(taiko, 348 + 33, 348 +40);
	hue.copyAndAdd(taiko, 348 +33, 348 +40);

	
	danjiriSonata.lineUpAllPartsWithRest();
	//*/
	//====================================================================================================================
	//---------------------Print Out Total Time So Far & Save to File-----------------------------------------------------
	//====================================================================================================================
	danjiriSonata.printOutTotalTimeSoFarToTerminal();	
	danjiriSonata.saveThisCompositionToFile();
    }
}

	
