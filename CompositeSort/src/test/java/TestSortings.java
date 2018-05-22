import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import sorting.SortByNumberOfReleases;
import sorting.SortingMethod;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;

@RunWith(JMockit.class)
public class TestSortings {


    @Test
    public void releasesSortingTest(){
        List<JsonObject> items=new ArrayList<>();
        JsonObject object1 = Json.createObjectBuilder().add("full_name", "TheAlgorithms/Java").build();
        JsonObject object2 = Json.createObjectBuilder().add("full_name", "airbnb/javascript").build();
        JsonObject object3 = Json.createObjectBuilder().add("full_name", "structurizr/java").build();
        JsonObject object4 = Json.createObjectBuilder().add("full_name", "17mon/java").build();
        items.add(object4);
        items.add(object2);
        items.add(object3);
        items.add(object1);
        List<JsonObject> expected= new ArrayList<>();
        expected.add(object3);
        expected.add(object4);
        expected.add(object1);
        expected.add(object2);
        SortingMethod sortingMethod = new SortByNumberOfReleases();
        Map<JsonObject, Integer> list = sortingMethod.sort(items);
        List<JsonObject> sorted = new ArrayList<>(list.keySet());
        assertTrue(expected.equals(sorted));
    }

    @Test
    public void sortMethodTest() {

        Map<JsonObject, Integer> list = new HashMap<>();
         SortByNumberOfStars.sortDescNumberOfStars();
        for (int i = 0; i < list.size() - 1 ; i++) {
            assertTrue(list.get(i) >= (list.get(i+1)));
        }
    }
}
