import com.jcabi.github.Github;
import com.jcabi.github.RtGithub;
import com.jcabi.http.response.JsonResponse;

import javax.json.JsonObject;
import java.io.IOException;
import java.util.*;

import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.toMap;

public class SortByNumberOfReleases {
    public static List<JsonObject> getRepositoryList() {
        return repositoryList;
    }

    public static void setRepositoryList(List<JsonObject> repositories) {
        repositoryList = repositories;
    }

    private static List<JsonObject> repositoryList=new ArrayList<>();
    public static List<JsonObject> getRepositories() {
        Github github = new RtGithub();
        List<JsonObject> items=new ArrayList<>();
        try {
            JsonResponse resp = github.entry()
                    .uri().path("/search/repositories")
                    .queryParam("q", "java").back()
                    .fetch()
                    .as(JsonResponse.class);
            items= resp.json().readObject()
                    .getJsonArray("items")
                    .getValuesAs(JsonObject.class);

        } catch (IOException e) {
            System.out.println("Problem encountered");
        }
        return items;
    }
    public static Map<JsonObject, Integer> sortDescNumberOfReleases(){
        Github github = new RtGithub();
        List<JsonObject> items = getRepositoryList();
        Map<JsonObject, Integer> list = new HashMap<JsonObject, Integer>();
        for ( JsonObject item : items) {
            String name1="/repos/"+item.get("full_name").toString()+"/releases";
            JsonResponse response = null;
            try {
                response = github.entry()
                        .uri().path(name1.replace("\"","")).back()
                        .fetch()
                        .as(JsonResponse.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            int releases1= response.json().readArray().size();
            list.put(item,releases1);
            System.out.println(
                    String.format(
                            "repository found: %s",
                            item.get("full_name").toString()
                    )
            );

        }
        System.out.println("After sort");
        list=list.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1,e2) -> e1, LinkedHashMap::new));
        for (Map.Entry<JsonObject,Integer> entry : list.entrySet()) {
            System.out.println(
                    String.format(
                            "repository found: %s",
                            entry.getKey().get("full_name").toString()+" releases "+entry.getValue()
                    ));

        }
        return list;

    }
}