package reverse;

import java.io.IOException;

public class RunScript {

    public void run(String path, String...arguments) {
        try {
            Process p = Runtime.getRuntime().exec("python " + path + " "
                    + (arguments == null ? " " : String.join(" ", arguments)) );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
