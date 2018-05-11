import com.jcabi.github.Github;
import com.jcabi.github.RtGithub;
import com.jcabi.http.response.JsonResponse;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.io.IOException;
import java.util.List;

public class SortByNumberOfContributorFollowers {
    public static void retrieveOwner() {
        final Github github = new RtGithub();

        try {
            final JsonResponse resp = github.entry()
                    .uri().path("/search/repositories")
                    .queryParam("q", "java").back()
                    .fetch()
                    .as(JsonResponse.class);
            final List<JsonObject> items = resp.json().readObject()
                    .getJsonArray("items")
                    .getValuesAs(JsonObject.class);
            for (final JsonObject item : items) {
                String name="/repos/"+item.get("full_name").toString()+"/collaborators";
                String username = item.get("full_name").toString().split("/")[0];
                JsonResponse response = github.entry()
                        .uri().path(name.replace("\"","")).back()
                        .fetch()
                        .as(JsonResponse.class);
                JsonArray collaborators = response.json().readArray();
                for (int i=0; i<collaborators.size(); i++)
                {
                    String followers_path ="/users/"+username+"/followers";
                    JsonResponse response_c = github.entry()
                            .uri().path(name.replace("\"","")).back()
                            .fetch()
                            .as(JsonResponse.class);
                    JsonArray followers = response.json().readArray();
                }
                System.out.println(
                        String.format(
                                "repository found: %s",
                                item.get("full_name").toString()
                        )+" number of stars: "+collaborators.size()
                );

            }
        }
        catch (IOException e) {
            System.out.println("Problem encountered");
        }
    }
}
