import os
import sys
import dict2uml
import json
import plantuml


classes = []
# interfaces = []
implementations = []
relations = []
found_list = []


try:
    root_dir = sys.argv[1]
except Exception as err:
    print "Exception caught:", err
    print "Try passing an argument"
    exit()


if not os.path.exists(root_dir):
    print "Invalid path"
    exit()


def parse_file(file):
    global classes, implementations, relations, found_list

    with open(file, 'r') as f:
        for line in f:
            if "//" in line:
                continue

            if "class " in line:
                class_line = line.rsplit()[2]
                classes += [class_line]

                temp_dict = {class_line: {}}

                if ":" in line:
                    implementations += [name.replace(',', '') for name in line.rsplit()[3:]]
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


def get_info():
    for folder, dirs, files in os.walk(root_dir):
        for file in files:
            if file.endswith('.cs'):
                full_path = os.path.join(folder, file)
                try:
                    parse_file(full_path)
                except Exception as err:
                    print "Exception caught:", err
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

    if len(to_concat) is 0:
        print "No images found."
        print "Folder may be empty."
        exit()
    
    concat_images(to_concat)
