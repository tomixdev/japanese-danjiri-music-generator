import java.util.Vector;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Random;

public class RhythmPatternTester {
    public static void main (String[] args) throws FileNotFoundException{
	RhythmPattern test1 = new RhythmPattern(10);
	System.out.println("10 hyphens should be*");
	System.out.println(test1);

        System.out.println();

	RhythmPattern test2 = new RhythmPattern("RavelPattern.txt");
	System.out.println("Ravel Pattern should be*");
	System.out.println(test2);

	System.out.println();

	RhythmPattern test3 = new RhythmPattern(test2);
	System.out.println("(copied) Ravel Pattern should be");
	System.out.println(test3);

	System.out.println();

	Vector<Boolean> booleanVector = new Vector<Boolean>();
	booleanVector.add(false);
	booleanVector.add(true);
	booleanVector.add(false);
	booleanVector.add(false);
	RhythmPattern test4 = new RhythmPattern(booleanVector);
	System.out.println("X - - should be");
	System.out.println(test4);

	System.out.println();

	System.out.println("Testing two methods to save files. Check the rhythm pattern text file folder. Save Ravel RhythmPatternOne should be titled with meaningless hashcode. The other should be titled RavelRhythmPatternSaveTest");
	test2.saveRhythmPatternToFile();
	test2.saveRhythmPatternToFile("RavelRhythmPatternSaveTest");

	System.out.println();

	System.out.println("Test Basic Getter Methods----------------");
	System.out.println("NumberOfPulses 16 : " + test3.getNumberOfPulses());
	System.out.println("NumberOfHits 12: " + test3.getNumberOfHits());
	System.out.println("NumberOfAccents 5: " + test3.getNumberOfAccents());
	System.out.println("NumberOfRests 4: " + test3.getNumberOfRests());
	System.out.println("True should be: " + test3.getIsThereHitAt(9));
	System.out.println("False should be: " + test3.getIsThereHitAt(8));
	System.out.println("True should be: " + test3.getIsThereAccentAt(12));
	System.out.println("False should be: " + test3.getIsThereAccentAt(1));
	System.out.println("False should be: " + test3.getIsThereAccentAt(3));
	System.out.println("True should be: " + test3.getIsThereRestAt(11));
	System.out.println("False should be: " + test3.getIsThereRestAt(16));

	System.out.println();

	Vector<Boolean> test2Boolean = test2.translateHitPatternIntoBooleanVector();
	Iterator<Boolean> a = test2Boolean.iterator();
	System.out.println("test translateHitPatternIntoBooleanVector() by using Ravel Pattern----------------------------------");
	System.out.println("First spot should be null: " + a.next());
	System.out.print("HitPattern is: ");
	while(a.hasNext()) {
	    System.out.print(a.next() + " ");
	}

	System.out.println();
	System.out.println();

	Vector<Boolean> test2AccentBoolean = test2.translateAccentPatternIntoBooleanVector();
	Iterator<Boolean> b = test2AccentBoolean.iterator();
	System.out.println("test translateAccentPatternIntoBooleanVector() by using Ravel Pattern--------------------------------");
	System.out.println("First spot should be null: " + b.next());
	System.out.print("AccentPattern is: ");
	while(b.hasNext()){
	    System.out.print(b.next() + " ");
	}

	System.out.println();
	System.out.println();

	System.out.println("Test Four Setters--------------------------------------------");
	System.out.println("Here is 3 rests");
	RhythmPattern test5 = new RhythmPattern(3);
	System.out.println(test5);
	System.out.println("modify 1st pulse into hit");
	test5.setHitAt(1, true);
	System.out.println(test5);
	System.out.println("modify 3rd pulse into accented hit");
	test5.setAccentAt(3, true);
	System.out.println(test5);
	System.out.println("modify 2nd pulse into rest. Nothing should change!");

	test5.turnIntoRestAt(2);
	System.out.println(test5);
	System.out.println("modify 3rd pulse from accent to just a hit");
	test5.setAccentAt(3, false);
	System.out.println(test5);
	System.out.println("modify 1st pulse from hit to accent");
	test5.setAccentAt(1, true);
	System.out.println(test5);
	System.out.println("delete hit from 1st pulse.");
	test5.setHitAt(1, false);
	System.out.println(test5);
	System.out.println("delete accent from 1st pulse.Nothing should happen");
	test5.setAccentAt(1, false);
	System.out.println(test5);
	System.out.println("modify 2nd pulse into accented hit");
	test5.setAccentAt(2, true);
	System.out.println(test5);
	System.out.println("modify 2nd pulse into rest");
	test5.setHitAt(2, false);
	System.out.println(test5);

	RhythmEvent d = new RhythmEvent(true, true);
	System.out.println("modify 1st pulse into accent");
	test5.setRhythmEventAt(1, d);
	System.out.println(test5);

	d.setIsAccented(false);
	d.setIsSound(false);
	System.out.println("modify 3rd pulse into rest");
	test5.setRhythmEventAt(3, d);
	System.out.println(test5);
	
	System.out.println();

	System.out.println("Test Insertions, Adds And Removes Methods--------------------------------------------");
	RhythmPattern test6 = new RhythmPattern(3);
	System.out.println("Here is original 3-pulse rhythm pattern");
	System.out.println(test6);
	System.out.println("Insert hit before pulse 1");
	RhythmEvent g = new RhythmEvent(true, false);
	test6.insertRhythmEventBeforeAndReplace(g, 1);
	System.out.println(test6);

	System.out.println("insert rest before pulse 1");
	test6.insertRestPulseBeforeAndReplace(1);
	System.out.println(test6);

	System.out.println("add accent in the end");
	g = new RhythmEvent(true, true);
	test6.insertRhythmEventAfterAndReplace(g, test6.getNumberOfPulses());
	System.out.println(test6);

	System.out.println("insert rest pulse after pulse 2");
	test6.insertRestPulseAfterAndReplace(2);
	System.out.println(test6);

	System.out.println("add rest pulse in the end");
	test6.addRestPulseAndReplace();
	System.out.println(test6);

	System.out.println("add hit in the end");
	g = new RhythmEvent(true, false);
	test6.addRhythmEventAndReplace(g);
	System.out.println(test6);

	System.out.println("insert rest pulse at the top");
	test6.insertRestPulseAtTheTopAndReplace();
	System.out.println(test6);

	System.out.println("insert hit at the top");
	test6.insertRhythmEventAtTheTopAndReplace(g);
	System.out.println(test6);

	System.out.println("remove rhythm event at pulse 4");
	test6.removeRhythmEventAtAndReplace(4);
	System.out.println(test6);

	System.out.println("remove the rhythm event at top");
	test6.removeRhythmEventAtTheTopAndReplace();
	System.out.println(test6);

	System.out.println("pop(delete the end)");
	test6.popRhythmEventAndReplace();
	System.out.println(test6);

	System.out.println();

	System.out.println("Test convertAccentPatternIntoHitPatternAnd()----------------------------------------");
	//use test2 (ravel rhythm pattern)
	RhythmPattern x = test2.convertAccentPatternIntoHitPatternAndCopy();
	System.out.println(x);

	System.out.println("Test switchHitsAndRestsAnd()----------------------------------");
	test2.convertAccentPatternIntoHitPatternAndReplace();
	test2.switchHitsAndRestsAndReplace();
	System.out.println(test2);

	System.out.println();

	System.out.println("Test rhythm transposition by uising Ravel Pattern-----------------------------------------");
	System.out.println("Here is the original");
	System.out.println(test3);
	System.out.println("Below is all the transpositions from 1 transposition to 16 transposition (which is the same as original)");;
	for(int i = 1; i <= 16; i++) {
	    System.out.println(test3.rhythmicallyTransposeAndCopy(i));
	}
			       
	System.out.println();

	System.out.println("Test retrograde method----------------------------------------------");
	System.out.println("Below should be the retrograde of ravel pattern:");
	System.out.println(test3.retrogradeAndCopy());

	System.out.println();

	System.out.println("Another test of retrograde");
        RhythmPattern test8 = new RhythmPattern(1);
	test8.setHitAt(1, true);
	System.out.println("Here is the original");
	System.out.println(test8);
	test8.retrogradeAndReplace();
	System.out.println("Here is retrograde");
	System.out.println(test8);

	System.out.println();

	System.out.println("test randomness methods of already existing rhythm events-------------------------------------------------------------------------------------");
	Random rand = new Random();
	
	RhythmPattern test0 = new RhythmPattern("RavelPattern.txt");
	//RhythmPattern test0 = new RhythmPattern(0);
	System.out.println("Here is original");
	System.out.println(test0);
	System.out.println();
	System.out.println("turnRestIntoHitrandomlyAndCopy");
	System.out.println(test0.turnRestIntoHitRandomlyAndCopy(rand.nextInt()));
	
	System.out.println();
	//System.out.println("Below warning should appear 3 times!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	//RhythmPattern test100 = test0.turnRestIntoHitRandomlyAndCopy(rand.nextInt());test100.turnRestIntoHitRandomlyAndReplace(rand.nextInt());test100.turnRestIntoHitRandomlyAndReplace(rand.nextInt());test100.turnRestIntoHitRandomlyAndReplace(rand.nextInt());test100.turnRestIntoHitRandomlyAndReplace(rand.nextInt());test100.turnRestIntoHitRandomlyAndReplace(rand.nextInt());test100.turnRestIntoHitRandomlyAndReplace(rand.nextInt());
	
	System.out.println();
	System.out.println("turnHitIntoRestRandomlyAndcopy");
	System.out.println(test0.turnHitIntoRestRandomlyAndCopy(rand.nextInt()));
	System.out.println();
	//System.out.println("Below, warning should appear 3 times!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	//RhythmPattern test100 = test0.turnHitIntoRestRandomlyAndCopy(rand.nextInt());test100.turnHitIntoRestRandomlyAndReplace(rand.nextInt());test100.turnHitIntoRestRandomlyAndReplace(rand.nextInt());test100.turnHitIntoRestRandomlyAndReplace(rand.nextInt());test100.turnHitIntoRestRandomlyAndReplace(rand.nextInt());test100.turnHitIntoRestRandomlyAndReplace(rand.nextInt());test100.turnHitIntoRestRandomlyAndReplace(rand.nextInt());test100.turnHitIntoRestRandomlyAndReplace(rand.nextInt());test100.turnHitIntoRestRandomlyAndReplace(rand.nextInt());test100.turnHitIntoRestRandomlyAndReplace(rand.nextInt());
	System.out.println();
	
	System.out.println("turnHitIntoAccentrandomlyAndCopy");
	System.out.println(test0.turnHitIntoAccentRandomlyAndCopy(rand.nextInt()));
	System.out.println();
	//System.out.println("Below, warning should appear 3 times!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	//RhythmPattern test100 = test0.turnHitIntoAccentRandomlyAndCopy(rand.nextInt()); test100.turnHitIntoAccentRandomlyAndReplace(rand.nextInt()); test100.turnHitIntoAccentRandomlyAndReplace(rand.nextInt()); test100.turnHitIntoAccentRandomlyAndReplace(rand.nextInt()); test100.turnHitIntoAccentRandomlyAndReplace(rand.nextInt()); test100.turnHitIntoAccentRandomlyAndReplace(rand.nextInt()); test100.turnHitIntoAccentRandomlyAndReplace(rand.nextInt()); test100.turnHitIntoAccentRandomlyAndReplace(rand.nextInt()); test100.turnHitIntoAccentRandomlyAndReplace(rand.nextInt()); test100.turnHitIntoAccentRandomlyAndReplace(rand.nextInt());
	
	System.out.println();
	System.out.println("turnAccentIntoHitRandomlyAndCopy");
	System.out.println(test0.turnAccentIntoHitRandomlyAndCopy(rand.nextInt()));
	System.out.println();
	//System.out.println("Below, warning should appear 3 times!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	//RhythmPattern test100 = test0.turnAccentIntoHitRandomlyAndCopy(rand.nextInt()); test100.turnAccentIntoHitRandomlyAndReplace(rand.nextInt());test100.turnAccentIntoHitRandomlyAndReplace(rand.nextInt());test100.turnAccentIntoHitRandomlyAndReplace(rand.nextInt());test100.turnAccentIntoHitRandomlyAndReplace(rand.nextInt());test100.turnAccentIntoHitRandomlyAndReplace(rand.nextInt());test100.turnAccentIntoHitRandomlyAndReplace(rand.nextInt());test100.turnAccentIntoHitRandomlyAndReplace(rand.nextInt());
	System.out.println();
	System.out.println("turnRestIntoAccentRandomlyAndCopy");
	System.out.println(test0.turnRestIntoAccentRandomlyAndCopy(rand.nextInt()));
	System.out.println();
	//System.out.println("Below, warning should appear 3 times!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	//RhythmPattern test100 = test0.turnRestIntoAccentRandomlyAndCopy(rand.nextInt()); test100.turnRestIntoAccentRandomlyAndReplace(rand.nextInt());test100.turnRestIntoAccentRandomlyAndReplace(rand.nextInt());test100.turnRestIntoAccentRandomlyAndReplace(rand.nextInt());test100.turnRestIntoAccentRandomlyAndReplace(rand.nextInt());test100.turnRestIntoAccentRandomlyAndReplace(rand.nextInt());test100.turnRestIntoAccentRandomlyAndReplace(rand.nextInt());
	System.out.println();
	System.out.println("turnAccentIntoRestRandomlyAndcopy");
	System.out.println(test0.turnAccentIntoRestRandomlyAndCopy(rand.nextInt()));
	//System.out.println("Below, warning should appear 3 times!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	//RhythmPattern test100 = test0.turnAccentIntoRestRandomlyAndCopy(rand.nextInt()); test100.turnAccentIntoRestRandomlyAndReplace(rand.nextInt());test100.turnAccentIntoRestRandomlyAndReplace(rand.nextInt());test100.turnAccentIntoRestRandomlyAndReplace(rand.nextInt());test100.turnAccentIntoRestRandomlyAndReplace(rand.nextInt());test100.turnAccentIntoRestRandomlyAndReplace(rand.nextInt());test100.turnAccentIntoRestRandomlyAndReplace(rand.nextInt());test100.turnAccentIntoRestRandomlyAndReplace(rand.nextInt());
	System.out.println();
	System.out.println();

	System.out.println("Test 4 insert-randomly methods--------------------------------------------------------");

	System.out.println("Here is the original");
	RhythmPattern test11 = new RhythmPattern("RavelPattern.txt");
	//RhythmPattern test11 = new RhythmPattern(0);
	System.out.println(test11);

	System.out.println("insertHitRandomlyAnd()");
	System.out.println(test11.insertHitRandomlyAndCopy(rand.nextInt()));
	System.out.println();
	System.out.println("insertAccentRandomlyAnd()");
	System.out.println(test11.insertAccentRandomlyAndCopy(rand.nextInt()));
	System.out.println();
	System.out.println("insertRestRandomlAnd()");
	System.out.println(test11.insertRestRandomlyAndCopy(rand.nextInt()));
	System.out.println();
	System.out.println("insertRandomRhythmEventRandomlyAnd()");
	System.out.println(test11.insertRandomRhythmEventRandomlyAndCopy(rand.nextInt()));
	System.out.println();

	System.out.println("Test remove methods---------------------------------------------------------------");
	System.out.println("Here is the original");
        System.out.println(test11);
	System.out.println();
	System.out.println("removeHitRandomly");
	System.out.println(test11.removeHitRandomlyAndCopy(rand.nextInt()));
	System.out.println();
	System.out.println("removeAccentRandomly");
	System.out.println(test11.removeAccentRandomlyAndCopy(rand.nextInt()));
	System.out.println();
	System.out.println("removeRestRandomly");
	System.out.println(test11.removeRestRandomlyAndCopy(rand.nextInt()));
	System.out.println();
	System.out.println("removeRhyythmEventRandomly");
	System.out.println(test11.removeRhythmEventRandomlyAndCopy(rand.nextInt()));

	System.out.println();

	System.out.println("Test randomly transpose--------------------------------------------------------------");
	System.out.println("here is the original");
	System.out.println(test11);
	System.out.println("transpose randomly");
	System.out.println(test11.randomlyTransposeAndCopy(rand.nextInt()));

	System.out.println();
	
	
	    
	
    }
}
       
	
	
