import os
import sys
import dict2uml
import json
import plantuml


root_dir = r'D:/Anul 2/Java/lab6'


def get_info():
    classes = []
    interfaces = []
    implementations = []
    
    relations = []
    found_list = []

    for folder, dirs, files in os.walk(root_dir):
        for file in files:
            if file.endswith('.java'):
                full_path = os.path.join(folder, file)
                with open(full_path, 'r') as f:
                    for line in f:
                        if "class " in line:
                            class_line = line.split()[2]
#                             class_line = line.strip('\n')
                            classes += [class_line]

                            temp_dict = {class_line : {}}

                            if "extends" in line:
                                implementations += [name.replace(',', '') for name in line.rsplit()[4:]]
                                for name in implementations:
                                    if name in found_list:
                                        pass
                                    else:
                                        impl_dict = {name:temp_dict}
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
    uml_no = 1
    for d in info:
        with open(str(uml_no) + '.png', 'wb') as out:
            out.write(uml.processes(dict2uml.dict2plantuml(d)))
        uml_no += 1