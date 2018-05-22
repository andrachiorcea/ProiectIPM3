package manager;

import reverse.RunScript;

public class ReverseManager {
    String reverseString;
    String reverseArgs;

    public ReverseManager() {
        reverseString = "c#.py";
        reverseArgs = "default_folder";
    }

    public ReverseManager(String reverseString, String reverseArgs) {
        this.reverseString = reverseString;
        this.reverseArgs = reverseArgs;
    }

    public void callReverse() {
        RunScript.run(reverseString, reverseArgs);
    }
}
