import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kohsuke.github.GHRepository;
import traversal.*;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static junit.framework.TestCase.assertTrue;


@RunWith(JMockit.class)
public class TestTraverse {


    @Test
    public void testValidPath(){
        Path path = Paths.get("C:\\Users\\Cristina\\Desktop\\ipTEST\\ProiectIPM3\\CompositeSort\\src\\test\\java\\TestSortings.java");
        boolean exists = Files.exists(path);
        assertTrue(exists);
    }

    @Test
    public void testInvalidPath(){
        Path path = Paths.get("C:\\Users\\Cristina\\Desktop\\ProiectIPM3-master\\ 32'ljiuf uydyava");
        boolean exists = Files.notExists(path);
        assertTrue(exists);
    }


}