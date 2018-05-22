package sorting;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SortingFactory {

    private Map<String, SortingMethod> sortingMethods = new HashMap<>();

    public SortingMethod getSortingMethod(String sortingMethod) {
        try
        {
            if(!sortingMethods.containsKey(sortingMethod))
                throw (new IOException("no key"));
        }

        catch (IOException exception){
            System.out.println("Incorrect sorting method");
            return null;
        }

        return sortingMethods.get(sortingMethod);
    }

    public void AddSortingMethod(String name, SortingMethod implementation)
    {
        sortingMethods.put(name, implementation);
    }
}
