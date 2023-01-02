import java.util.Random;
import java.io.FileNotFoundException;
import java.util.Vector;
import java.lang.Math;

public class RhythmCompositionTester {
    public static void main (String[] args) throws FileNotFoundException{
	String[] x = {"Part1", "Part2","Part3","Part4"};
	RhythmComposition test = new RhythmComposition("TestComposition", x, 60, 15);

	RhythmPattern ravel = new RhythmPattern("RavelPattern.txt");

	Vector<RhythmPart> rhythmParts = test.getRhythmPartsVector();

	RhythmPart part1 = rhythmParts.elementAt(0);
	RhythmPart part2 = rhythmParts.elementAt(1);
	RhythmPart part3 = rhythmParts.elementAt(2);
	RhythmPart part4 = rhythmParts.elementAt(3);

	part1.addRhythmPattern(ravel);
	part2.addRhythmPattern("RavelPattern.txt");
	part3.addRhythmPattern(16, 12);
	part4.addRhythmPattern( "All ROUTs of 12 pulses AND 7 hits.txt", 1);
	part4.addRestPulses(2);
	part4.addRhythmEvent('-');
	
	test.saveThisCompositionToFile();

	part1.addRestPulses(4);
	part2.addRestPulses(9);
	//test.lineUpAllPartsWithRandomRhythmEvent();

	test.saveThisCompositionToFile();

	test.changeTempoBetween(120, 12, 21);

	test.saveThisCompositionToFile();

	test.printOutTotalTimeSoFarToTerminal();
	

	

	/*
        test.addRhythmicComponent("Part1", ravel);
	test.addRhythmicComponent("Part2", ravel);
	test.addRhythmicComponent("Part3", ravel);
	test.addRhythmicComponent("Part4", ravel);

	test.saveThisCompositionToFile();

	test.addRhythmicComponent("Part1", ravel);
	test.addRhythmicComponent("Part1", ravel);
	test.changeTempoTo(80);
	test.addRhythmicComponent("Part1", ravel);
	test.addRhythmicComponent("Part2", ravel);
	test.addRhythmicComponent("Part3", ravel);
	test.addRhythmicComponent("Part4", ravel);

	test.saveThisCompositionToFile();
	test.addRhythmPattern("Part2", 16, 12);
	test.saveThisCompositionToFile();
	test.addRhythmPattern("Part3", "RavelPattern.txt");
	test.addRhythmPattern("Part4", "All ROUTs of 12 pulses AND 7 hits.txt", 1);
	test.saveThisCompositionToFile();
	test.AddRhythmPattern("Part2", ravel);
	test.Add
	*/
	
	
    }
}
	
	
