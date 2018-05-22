package traversal;

import java.io.File;
import java.io.IOException;

import concatenation.*;

public class Traverse {

        static Concat c = new Concat();
        public static void main (String args[]) throws IOException {

            displayIt(new File("D:\\Anul 2\\ProiectIP\\ProiectIPM3"));
        }

        public static void displayIt(File node) throws IOException {



            if(node.isDirectory() && !node.getName().startsWith(".")){
//                System.out.println(node.getAbsoluteFile());
                String[] subNote = node.list();
                for(String filename : subNote){
//                    System.out.println(filename);
                    displayIt(new File(node, filename));
                    c.run(node.getAbsoluteFile());
                }
            }
            else return;
        }
}
