package IPpyparser;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;

public class Parser {

	static void parseFiles (String path)
	{
		File dir = new File(path);
		String modifPath=path;
		//get all files from the directory
		File[] files = dir.listFiles();
		if (files.length == 0) {
			System.out.println("The directory is empty");
		} else {
			for (File aFile : files) {
				if (aFile.isDirectory())
				{
					System.out.println(aFile.getName() + " is a directory. Entering directory...");
					modifPath=path + '\\' + aFile.getName();
					parseFiles(modifPath);
				}	
				else if (aFile.getName().endsWith(".py")) {
					String name =aFile.getName();
					int pos = name.lastIndexOf(".");
					if (pos > 0) {
					    name = name.substring(0, pos);
					}
					System.out.println(aFile.getName() + " python file");
					cmd ("cd "+modifPath,"pyreverse -o "+name+".dot "+ aFile.getName());

					
					generateUML("cd "+modifPath, "dot -Tpng  classes."+ name +".dot -o " + name+".png");
				}
				else System.out.println(aFile.getName() + " is something else");
			}
		
		}
	}
	
	static void generateUML (String cPath,String command)
	{
		try {
			Runtime rt = Runtime.getRuntime();
			Process pr = rt.exec("cmd /c start cmd.exe /K \"" + cPath +" && "+command+" && taskkill /f /im cmd.exe\"");
			pr.waitFor();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	static void cmd (String cPath,String command)
	{
		try {
			Runtime rt = Runtime.getRuntime();
			//Process pr = rt.exec("cmd /c start cmd.exe /K \"" + cPath +"\"");
			Process pr = rt.exec("cmd /c start cmd.exe /K \"" + cPath +" && "+command+" && taskkill /f /im cmd.exe\"");
			pr.waitFor();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws ExecuteException, IOException {
		parseFiles("C:\\Users\\hriscu ilie\\eclipse-workspace\\JAVA\\UMLpy\\src\\uml");
	}

}
