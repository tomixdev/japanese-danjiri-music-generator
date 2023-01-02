import java.io.FileNotFoundException;
public class RhythmicComponent {
    double duration;

    public int getNumberOfPulses() {
	System.out.println("This method is not supposed to be read, and should be overwritten by subclasses!! Error from getNumberOfPulses() at RhythmicComponent Class");
	System.exit(1);
	return -9999;
    }

    public void randomlyModifyAndReplace(int randomSeed) throws FileNotFoundException{
	System.out.println("This method is not supposed to be read, and should be overwritten by subclasses!! Error from randomlyModifyAndReplace() at RhythmicComponent Class");
	System.exit(1);
    }

    public RhythmicComponent randomlyModifyAndCopy(int randomSeed) throws FileNotFoundException{
	System.out.println("This method is not supposed to be read, and should be overwritten by subclasses!! Error from randomlyModifyAndCopy() at RhythmicComponent Class");
	System.exit(1);
	return null;
    }	
	
}
