package traversal;

import java.io.File;
import java.io.IOException;

import concatenation.*;

public class Traverse {

    static Concat c = new Concat();
    public static void main (String args[]) throws IOException {

        displayIt(new File("C:\\Users\\hriscu ilie\\Desktop\\INFO\\JAVA\\IP\\Combine"));
        deleteIt (new File("C:\\Users\\hriscu ilie\\Desktop\\INFO\\JAVA\\IP\\Combine"));
    }

    public static void displayIt(File node) throws IOException {

        if(node.isDirectory() && !node.getName().startsWith(".")){
            String[] subNote = node.list();
            for(String filename : subNote){
                displayIt(new File(node, filename));
                c.run(node.getAbsoluteFile());
            }
        }
        else return;
    }

    public static void deleteIt(File node) throws IOException {
        if(node.isDirectory() && !node.getName().startsWith(".")){
            String[] subNote = node.list();
            for(String filename : subNote){
                deleteIt(new File(node, filename));
                c.delOriginal(node.getAbsoluteFile());
            }
        }
        else return;

    }
}
