import entry.FileScanner;
import org.junit.jupiter.api.Test;

public class FileScannerTest {

    @Test
    public void testForNoInput(){
        FileScanner.main(new String[]{});
    }

    @Test
    public void testForValidInput(){
        FileScanner.main(new String[]{"src/test/resources/invalidInput.txt"});
    }

    @Test
    public void testForInValidInput(){
        FileScanner.main(new String[]{"src/test/resources/validInput.txt"});
    }

    @Test
    public void testNonExistInput(){
        FileScanner.main(new String[]{"src/test/resources/nonExistInput.txt"});
    }
}
