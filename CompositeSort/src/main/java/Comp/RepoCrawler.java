package Comp;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHRepositorySearchBuilder;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RepoCrawler {

    public final static int MAX_SIZE = 100;

    public static List<GHRepository> getReposList(List<String> set, List<String> languages, int repoListSize) {

        List<GHRepository> repositories = new ArrayList<>();

        try {
            GitHub gitHub = GitHub.connectUsingPassword("ProiectIP2018", "ProiectIP2018-19");
            GHRepositorySearchBuilder ghRepositorySearchBuilder = gitHub.searchRepositories();
            for(String language : languages)
                ghRepositorySearchBuilder.language(language);

            for (String element : set) {
                ghRepositorySearchBuilder.q(element);
            }
            ghRepositorySearchBuilder.sort(GHRepositorySearchBuilder.Sort.STARS);
            for (GHRepository repo : ghRepositorySearchBuilder.list()) {
                if(repoListSize>=MAX_SIZE)
                    return repositories;
                repositories.add(repo);
                repoListSize++;
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return repositories;
    }
}