package Comp;

import org.kohsuke.github.GHRepository;

import java.util.ArrayList;
import java.util.List;

public abstract class Sorting {
    public static List<GHRepository> repositories = new ArrayList<>();

    public static void setRepositories(List<GHRepository> repository) {
        repositories = repository;
    }

    private List<Sorting> list;
    protected abstract List<GHRepository> sort();
    private List<GHRepository> sortList() {
        List<GHRepository> result = new ArrayList<>();
        for (Sorting element:list) {
            element.setRepositories(result);
            result = element.sort();
        }
        return result;
    }
}
