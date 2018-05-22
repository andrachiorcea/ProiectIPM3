import manager.ReverseManager;
import manager.SortingManager;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class TestReverseManager {
    @Test
    public void reverseManagerNoParamConstructorTest(){
        ReverseManager manager=new ReverseManager();
        assertEquals(manager.getReverseArgs(),"default_folder");
        assertEquals(manager.getReverseString(), "c#.py");
    }

    @Test
    public void reverseManagerConstructorWithParamTest(){
        ReverseManager manager=new ReverseManager("ceva", "altceva");
        assertEquals(manager.getReverseArgs(),"altceva");
        assertEquals(manager.getReverseString(), "ceva");
    }
}
