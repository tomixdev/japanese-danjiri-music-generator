import java.io.FileNotFoundException;

public class ThreeInSix {
    public static void main (String[] args) throws FileNotFoundException{
	ROUTGenerator a = new ROUTGenerator (12, 7);
	a.writeAllPossibleROUTsToFile();
    }
}
