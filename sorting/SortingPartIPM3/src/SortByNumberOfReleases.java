import com.jcabi.github.Github;
import com.jcabi.github.RtGithub;
import com.jcabi.http.response.JsonResponse;

import javax.json.JsonObject;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class SortByNumberOfReleases {
    public static void sortDescNumberOfReleases(){
         Github github = new RtGithub();
        try {
             JsonResponse resp = github.entry()
                    .uri().path("/search/repositories")
                    .queryParam("q", "java").back()
                    .fetch()
                    .as(JsonResponse.class);
            List<JsonObject> items = resp.json().readObject()
                    .getJsonArray("items")
                    .getValuesAs(JsonObject.class);
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
                    .sorted(Map.Entry.comparingByValue())
                    .collect(toMap(Map.Entry::getKey, Map.Entry::getValue,
                            (e1,e2) -> e1, LinkedHashMap::new));

            for (Map.Entry<JsonObject,Integer> entry : list.entrySet()) {
                System.out.println(
                        String.format(
                                "repository found: %s",
                                entry.getKey().get("full_name").toString()
                        ));

            }
        }
        catch (IOException e) {
            System.out.println("Problem encountered");
        }
    }
}
