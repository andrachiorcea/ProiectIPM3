package manager;

import org.kohsuke.github.GHRepository;
import sorting.SortingFactory;
import sorting.SortingMethod;

import java.util.List;

public class SortingManager {
    private String stringMethod;
    private SortingMethod sortingMethod;
    private SortingFactory sortingFactory = new SortingFactory();

    public SortingManager() {
        // Default sorting method
        this.stringMethod = "SortByNumberOfReleases";
        this.sortingMethod = sortingFactory.getSortingMethod(stringMethod);
    }

    public SortingManager(String stringMethod) {
        this.stringMethod = stringMethod;
        this.sortingMethod = sortingFactory.getSortingMethod(stringMethod);
    }

    public String getStringMethod() {
        return stringMethod;
    }

    public void setStringMethod(String stringMethod) {
        this.stringMethod = stringMethod;
    }

    public List<GHRepository> callSortMethod(List<GHRepository> repositoryList) {
        return sortingMethod.sort(repositoryList);
    }
}
