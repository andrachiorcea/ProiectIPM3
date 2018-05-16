
import com.jcabi.github.*;
import com.jcabi.http.response.JsonResponse;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortByNumberOfStars {
    public static Map<JsonObject, Integer> sortDescNumberOfStars(){
        final Github github = new RtGithub();
        Map<JsonObject, Integer> list = new HashMap<JsonObject, Integer>();
        try {
            final JsonResponse resp = github.entry()
                    .uri().path("/search/repositories")
                    .queryParam("q", "java")
                    .queryParam("sort", "stars")
                    .queryParam("order", "desc").back()
                    .fetch()
                    .as(JsonResponse.class);
            final List<JsonObject> items = resp.json().readObject()
                    .getJsonArray("items")
                    .getValuesAs(JsonObject.class);
            for (final JsonObject item : items) {
                String name="/repos/"+item.get("full_name").toString()+"/stargazers";
                JsonResponse response = github.entry()
                        .uri().path(name.replace("\"","")).back()
                        .fetch()
                        .as(JsonResponse.class);
                JsonArray stargazers = response.json().readArray();
                int stargazers1= response.json().readArray().size();
                list.put(item,stargazers1);
                System.out.println(
                        String.format(
                                "repository found: %s",
                                item.get("full_name").toString()
                        )+" number of stars: "+stargazers.size()
                );

            }
        }
        catch (IOException e) {
            System.out.println("Problem encountered");
        }
        return list;
    }
}

