package Comp;

import org.kohsuke.github.GHRepository;

import java.util.ArrayList;
import java.util.List;

public class CompositeSort extends Sorting {

    List<Sorting> sortOpt = new ArrayList<Sorting>();
    private static List<GHRepository> repositories = new ArrayList<>();

    public List<GHRepository> sort() {
        List<GHRepository> a = null;
        return a;
    }

    public void add (Sorting option) {
        sortOpt.add(option);
    }

    public Sorting get (int i) {
        return sortOpt.get(i);
    }

    public List<GHRepository> getRepos () {
        return repositories;
    }

    public void startSort(Sorting option) {
        option.setRepositories(repositories);
        option.sort();
    }

    public void sortAll () {
        for (Sorting type : sortOpt )
            startSort(type);
    }

    public static void main (String[] argc) {

        List<String> languages=new ArrayList<>();
        List<String> set=new ArrayList<>();
        set.add("sudoku");
        set.add("number");
        set.add("puzzle");
        languages.add("java");
        SortByNumberOfForks.setRepositories( RepoCrawler.getReposList(set,languages,10));

        CompositeSort sortArg = new CompositeSort();
        sortArg.add(new SortByNumberOfContributorFollowers());
        sortArg.add(new SortByNumberOfForks());
        sortArg.add(new SortByNumberOfReleases());
        sortArg.startSort(sortArg.get(1));
        sortArg.sortAll();
    }

}
