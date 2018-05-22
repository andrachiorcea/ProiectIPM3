package manager;

import org.kohsuke.github.GHRepository;
import sorting.*;


import java.util.List;

public class SortingManager {
    private String stringMethod;
    private SortingMethod sortingMethod;
    private SortingFactory sortingFactory = new SortingFactory();

    public void init(){
        sortingFactory.AddSortingMethod("SortByNumberOfContributorFollowers", new SortByNumberOfContributorFollowers());
        sortingFactory.AddSortingMethod("SortByNumberOfForks", new SortByNumberOfForks());
        sortingFactory.AddSortingMethod("SortByNumberOfReleases", new SortByNumberOfReleases());
        sortingFactory.AddSortingMethod("SortByNumberOfStars", new SortByNumberOfStars());
    }

    public SortingManager() {
        // Default sorting method
        init();
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
