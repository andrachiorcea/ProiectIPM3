//package sorting;
import org.kohsuke.github.GHRepository;

import java.util.*;

import static java.util.stream.Collectors.toMap;

public class SortByNumberOfStars extends SortingMethod {

    @Override
    public List<GHRepository> sort(List<GHRepository> repositories){
        Map<GHRepository, Integer> map = new HashMap<>();
        for(GHRepository repo : repositories){
            try{
                map.put(repo,repo.getStargazersCount());
            }
            catch(Exception e)
            {
                System.out.println("An error occurred: "+repo.getFullName());
            }
        }
        map=map.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1,e2) -> e1, LinkedHashMap::new));
        for (Map.Entry<GHRepository,Integer> entry : map.entrySet()) {
            System.out.println(
                    String.format(
                            "repository found: %s",
                            entry.getKey().getFullName()+" stargazers "+entry.getValue()
                    ));

        }
        return new ArrayList<>(map.keySet());
    }
}