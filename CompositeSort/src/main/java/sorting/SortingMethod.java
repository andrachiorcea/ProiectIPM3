package sorting;

import org.kohsuke.github.GHRepository;

import java.util.ArrayList;
import java.util.List;

public abstract class SortingMethod {
    public abstract List<GHRepository> sort(List<GHRepository> repositories);
}
