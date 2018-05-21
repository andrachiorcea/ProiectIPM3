package Comp;
import org.kohsuke.github.GHRepository;

import java.util.*;

import static java.util.stream.Collectors.toMap;

public class SortByNumberOfContributorFollowers extends Sorting {
    public static List<GHRepository> repositories = new ArrayList<>();
    public static List<GHRepository> getRepositories() {
        return repositories;
    }

    public static void setRepositories(List<GHRepository> repository) {
        repositories = repository;
    }

    public List<GHRepository> sort(){
        List<GHRepository> list=getRepositories();
        Map<GHRepository, Integer> map = new HashMap<>();
        for(GHRepository repo : list){
            try{
                List<GHRepository.Contributor> contributors= repo.listContributors().asList();
                int followers=0;
                for(GHRepository.Contributor contributor:contributors){
                    followers+=contributor.getFollowersCount();
                }
                map.put(repo,followers);
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
                            entry.getKey().getFullName()+" followers "+entry.getValue()
                    ));

        }
        list=new ArrayList<>(map.keySet());
        return list;

    }
}
