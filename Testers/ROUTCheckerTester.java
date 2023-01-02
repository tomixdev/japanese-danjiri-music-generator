import java.util.Vector;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Random;

public class ROUTCheckerTester {
    public static void main (String[] args) throws FileNotFoundException{
	System.out.println("Test isROUT()-------------------------------------------------------------------------");
	
	RhythmPattern ravel = new RhythmPattern("RavelPattern.txt");
	ROUTChecker ravelChecker = new ROUTChecker(ravel);
	System.out.println("Is Ravel ROUT? Should be true");
	//System.out.println(ravelChecker.isHitPatternROUT());
	System.out.println(ravel.isHitPatternROUT());
	System.out.println();

	RhythmPattern cuba = new RhythmPattern("CubaRhythm.txt");
	ROUTChecker cubaChecker = new ROUTChecker(cuba);
	System.out.println("Is Cuba ROUT? Should be true");
	//System.out.println(cubaChecker.isHitPatternROUT());
	System.out.println(cuba.isHitPatternROUT());
	System.out.println();

	RhythmPattern mozart = new RhythmPattern("Mozart.txt");
	ROUTChecker mozartChecker = new ROUTChecker(mozart);
	System.out.println("Is Mozart ROUT? Should be false");
	//System.out.println(mozartChecker.isHitPatternROUT());
	System.out.println(mozart.isHitPatternROUT());
	System.out.println();

	RhythmPattern twoPulses = new RhythmPattern("TwoPulses.txt");
	ROUTChecker twoPulsesChecker = new ROUTChecker(twoPulses);
	//System.out.println(twoPulsesChecker.getAllPossibleRhythmPatterns().size());
	//twoPulsesChecker.printAllPossibleRhythmPatterns();
	//System.out.println();
	//twoPulsesChecker.printAllPossibleHitPatternsInBoolean();
	//System.out.println();
	//twoPulsesChecker.printAllPossibleAccentPatternsInBoolean();
	//System.out.println();
	System.out.println();
	System.out.println("Is twoPulses ROUT?");
	System.out.println(twoPulses.isHitPatternROUT());
	System.out.println();

	RhythmPattern ravelVariation = new RhythmPattern("RavelVariation.txt");
	ROUTChecker ravelVariationChecker = new ROUTChecker(ravelVariation);
	System.out.println("Is RavelVariation's Accent pattern  ROUT? Should be true");
	//System.out.println(ravelVariationChecker.isAccentPatternROUT());
	System.out.println(ravelVariation.isAccentPatternROUT());
	System.out.println();

	RhythmPattern test = new RhythmPattern("Test.txt");
	ROUTChecker testChecker = new ROUTChecker(test);
	System.out.println("Is test's accent pattern ROUT?");
	System.out.println(testChecker.isAccentPatternROUT());
	System.out.println();

	RhythmPattern symmetrical1 = new RhythmPattern("SymmetricalRhythm1.txt");
	System.out.println("is symmetricalrhythm-1 hit pattern symmercial? Should be true");
	System.out.println(symmetrical1.isHitPatternSymmetrical());
	System.out.println();
	System.out.println("is symmetricalrhythm-1's accentpattern symmetrical? Should be true");
	System.out.println(symmetrical1.isAccentPatternSymmetrical());
    
	System.out.println();

	System.out.println("is symmetircalrhythm-1's rhythm symmetrical? Should be true");
	System.out.println(symmetrical1.isRhythmPatternSymmetrical());
	System.out.println();

	RhythmPattern nonSymmetrical = new RhythmPattern("NonSymmetrical.txt");
	System.out.println("Is NonSymmetrical's hit pattern symmetriclal? Should be false");
	System.out.println(nonSymmetrical.isHitPatternSymmetrical());
	System.out.println();

	System.out.println("Is NonSymmetrical's accent pattern symmetricla? Should be false");
	System.out.println(nonSymmetrical.isAccentPatternSymmetrical());
	System.out.println();

	System.out.println("Is NonSymmetrical's rhythm pattern symmetrical? should be false");
	System.out.println(nonSymmetrical.isRhythmPatternSymmetrical());
	System.out.println();

	RhythmPattern test0 = new RhythmPattern("Test0.txt");
	System.out.println("Is Test0's hit pattern symmetrical?");
	System.out.println(test0.isHitPatternSymmetrical());
	System.out.println();
	System.out.println("Is Test0's accent pattern symmetrical?");
	System.out.println(test0.isAccentPatternSymmetrical());
	System.out.println();
	System.out.println("Is Test0's rhythm pattern symmetrical?");
	System.out.println(test0.isRhythmPatternSymmetrical());
	System.out.println();	
    }
}
	
