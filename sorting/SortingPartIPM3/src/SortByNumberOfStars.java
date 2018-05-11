import com.jcabi.github.*;
import com.jcabi.http.response.JsonResponse;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.io.IOException;
import java.util.List;

public class SortByNumberOfStars {
    public static void sortDescNumberOfStars(){
        final Github github = new RtGithub();
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
    }
}

