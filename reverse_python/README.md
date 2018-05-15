-V1-
Install:
1.python3
2.pylint
3.graphviz

Methods:
parseFiles (String path)
-Recursively reads all files in the given path. 
-The method cmd is called if a *.py file is encountered.
-The method generateUML is called if a *.dot file is encountered
 

cmd(String cPath, String command)
-A command prompt is opened and the 'pyreverse' command is used.
-The python file is parsed in DOT.

generateUML (String cPath, String command)
-DOT command is used in order to generate an UML diagram in png format


-V2-
install Pillow

