import java.util.Random;

public class RhythmEventTester {
    public static void main (String[] args) {
	RhythmEvent test1 = new RhythmEvent();
	System.out.println("Test Construcctor1 and all other methods----------");
	System.out.println("False False Is");
	System.out.print(test1.getIsSound());
	System.out.println(test1.getIsAccented());

	System.out.println();

	System.out.println("1 is");
	System.out.println(test1.getNumberOfPulses());

	System.out.println();

	test1.setIsSound(true);
	test1.setIsAccented(true);
	System.out.println("True True is");
	System.out.println(test1.getIsSound());
	System.out.println(test1.getIsAccented());

	System.out.println();

	RhythmEvent test2 = test1.copyThisRhythmEvent();
	System.out.println("The following two rhythm events should be the same");
	System.out.println(test1);
	System.out.println(test2);

	System.out.println();

	test2.setIsSound(true);
	test2.setIsAccented(false);
	test1.copyAnotherRhythmEventIntoThis(test2);
	System.out.println("X is :");
	System.out.println(test2);
	
	System.out.println();

	System.out.println("Test randomly modify-----------");
	Random rand = new Random();	
	System.out.println(test1.randomlyModifyAndCopy(rand.nextInt()));
	System.out.println(test1.randomlyModifyAndCopy(rand.nextInt()));
	System.out.println(test1.randomlyModifyAndCopy(rand.nextInt()));
	System.out.println(test1.randomlyModifyAndCopy(rand.nextInt()));
	System.out.println(test1.randomlyModifyAndCopy(rand.nextInt()));
	System.out.println(test1.randomlyModifyAndCopy(rand.nextInt()));
	System.out.println(test1.randomlyModifyAndCopy(rand.nextInt()));
	System.out.println(test1.randomlyModifyAndCopy(rand.nextInt()));
	System.out.println(test1.randomlyModifyAndCopy(rand.nextInt()));
	System.out.println(test1.randomlyModifyAndCopy(rand.nextInt()));
	System.out.println(test1.randomlyModifyAndCopy(rand.nextInt()));
	System.out.println(test1.randomlyModifyAndCopy(rand.nextInt()));

	System.out.println(); 

	test1.setIsSound(false);
	test1.setIsAccented(true);
	System.out.println("Error message should appear:");

	System.out.println();

	test2.setIsSound(true);
	test2.setIsAccented(true);
	test2.setIsSound(false);
	System.out.println("Error message should appear*");

	System.out.println();
    }
}
	
	
	    

	
	
	
