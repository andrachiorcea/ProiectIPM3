import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kohsuke.github.GHRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

@RunWith(JMockit.class)
public class TestSortings {
    @Test
    public void releasesSortingTest(){
        List<String> languages=new ArrayList<>();
        List<String> set=new ArrayList<>();
        set.add("sudoku");
        set.add("number");
        set.add("puzzle");
        languages.add("java");
        List<GHRepository> repositories=new SortByNumberOfReleases().sort(RepoCrawler.getReposList(set,languages,10));
        for (int i = 0; i < repositories.size() - 1 ; i++) {
            try {
                assertTrue(repositories.get(i).listReleases().asList().size() >= (repositories.get(i+1).listReleases().asList().size()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void forksSortingTest(){
        List<String> languages=new ArrayList<>();
        List<String> set=new ArrayList<>();
        set.add("sudoku");
        set.add("number");
        set.add("puzzle");
        languages.add("java");
        List<GHRepository> repositories=new SortByNumberOfForks().sort(RepoCrawler.getReposList(set,languages,10));
        for (int i = 0; i < repositories.size() - 1 ; i++) {
            assertTrue(repositories.get(i).getForks()>= (repositories.get(i+1).getForks()));
        }
    }

    @Test
    public void starsSortingTest(){
        List<String> languages=new ArrayList<>();
        List<String> set=new ArrayList<>();
        set.add("sudoku");
        set.add("number");
        set.add("puzzle");
        languages.add("java");
        List<GHRepository> repositories=new SortByNumberOfStars().sort(RepoCrawler.getReposList(set,languages,10));
        for (int i = 0; i < repositories.size() - 1 ; i++) {
            assertTrue(repositories.get(i).getStargazersCount()>= (repositories.get(i+1).getStargazersCount()));
        }
    }

    @Test
    public void contributorFollowersSortingTest(){
        List<String> languages=new ArrayList<>();
        List<String> set=new ArrayList<>();
        set.add("sudoku");
        set.add("number");
        set.add("puzzle");
        languages.add("python");
        List<GHRepository> repositories=new SortByNumberOfContributorFollowers().sort(RepoCrawler.getReposList(set,languages,10));
        try {
            for (int i = 0; i < repositories.size() - 1 ; i++) {
                List<GHRepository.Contributor> contributors1= repositories.get(i).listContributors().asList();
                int followers1=0;
                for(GHRepository.Contributor contributor:contributors1){

                    followers1+=contributor.getFollowersCount();
                }
                List<GHRepository.Contributor> contributors2= repositories.get(i).listContributors().asList();
                int followers2=0;
                for(GHRepository.Contributor contributor:contributors2){

                    followers2+=contributor.getFollowersCount();
                }

                assertTrue(followers1>=followers2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
