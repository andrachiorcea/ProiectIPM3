import os
import sys
import dict2uml
import plantuml

root_dir = r'C:\Users\hriscu ilie\Desktop\INFO\PYTHON\IPreverse\src'


def get_info():
    classes = []
    interfaces = []
    implementations = []

    relations = []
    found_list = []

    for folder, dirs, files in os.walk(root_dir):
        for file in files:
            if file.endswith('.py'):
                full_path = os.path.join(folder, file)
                with open(full_path, 'r') as f:
                    for line in f:
                        if "// TODO" in line:
                            continue

                        if "class " in line:
                            class_line = line.split()[1]
                            class_line = class_line[:-1]
                            classes += [class_line]

                            temp_dict = {class_line: {}}

                            if "(" in line:
                                implementations += [name.replace(',', '') for name in line.rsplit()[1:]]
                                for name in implementations:
                                    if name in found_list:
                                        pass
                                    else:
                                        impl_dict = {name: temp_dict}
                                        found_list.append(name)
                                        relations.append(impl_dict)
                            else:
                                relations.append(temp_dict)
                                found_list.append(class_line)
    return relations

def concat_images(images):
    from PIL import Image
    images = map(Image.open, images)
    widths, heights = zip(*(i.size for i in images))
    total_width = sum(widths)
    max_height = max(heights)
    new_im = Image.new('RGB', (total_width, max_height), "white")
    x_offset = 0
    for im in images:
        new_im.paste(im, (x_offset, 0))
        x_offset += im.size[0]
    new_im.save('final_diagram.png')


if __name__ == "__main__":
    info = get_info()
    print(info)
    to_concat = []
    uml = plantuml.PlantUML()
    uml_no = 0
    for d in info:
        uml_no += 1
        file_name = (str(uml_no) + '.png')
        with open(file_name, 'wb') as out:
            out.write(uml.processes(dict2uml.dict2plantuml(d)))
        if os.path.isfile(file_name):
            to_concat += [file_name]

    concat_images(to_concat)