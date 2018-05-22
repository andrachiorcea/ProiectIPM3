import os
import sys
import dict2uml
import plantuml

root_dir = r'C:/dir'


def get_info():
    classes = list()
    implementations = list()

    relations = []
    found_list = list()

    for folder, dirs, files in os.walk(root_dir):
        for file in files:
            if file.endswith('.py'):
                full_path = os.path.join(folder, file)
                with open(full_path, 'r') as f:
                    for line in f:
                        if "// TODO" in line:
                            continue
                        if "class " in line:
                            class_line = (line[line.find("class") + len("class") + 1:line.find(":")].replace(',',''))
                            
                            if "(" not in line and ")" not in line: 
                                temp_dict = {class_line: {}}
                            else:
                                class_line = (line[line.find("class") + len("class") + 1:line.find("(")].replace(',',''))
                                temp_dict = {class_line: {}}
                            
                            
                            classes.append(class_line)
                            
                            if "(" in line and ")" in line: 
                                inherits = (line[line.find("(") + 1 : line.find(")")].replace(',',''))
                                implementation = (line[line.find("class") + len("class") + 1:line.find("(")].replace(',',''))
                                implementations.append(implementation) 
                                for name in implementations:
                                    if name in found_list:
                                        pass
                                    else:
                                        impl_dict = {inherits: temp_dict}
                                        found_list.append(name)
                                        relations.append(impl_dict)
                            else:
                                relations.append(temp_dict)
                                found_list.append(class_line)
    return relations

if __name__ == "__main__":
    info = get_info()
    print(info)
    
    uml = plantuml.PlantUML()
    uml_no = 0
    for d in info:
        uml_no += 1
        file_name = (str(uml_no) + '.png')
        with open(file_name, 'wb') as out:
            out.write(uml.processes(dict2uml.dict2plantuml(d)))
