package sorting;

import java.util.HashMap;
import java.util.Map;

public class SortingFactory {

    private Map<String, SortingMethod> sortingMethods = new HashMap<>();

    public SortingMethod getSortingMethod(String sortingMethod) {
        if(!sortingMethods.containsKey(sortingMethod))
            return null; // Aici creati o exceptie proprie pe care o aruncati
        return sortingMethods.get(sortingMethod);
    }

}
