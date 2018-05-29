"""
This script runs in the directory that you give as argument and looks in all the files and folders, recursively,
for C# files in order to get all the classes, implementations etc it needs. Those will be used for building a number of
UML diagrams (one for each class). In the end, those diagrams will be concatenated into one.
"""

import os
import sys
import dict2uml
import plantuml
import time


classes = []
implementations = []
relations = []
found_list = []

#  root_dir can be given a path if you want to call it without any args
#  e.g.:   root_dir = "C:\TestingDir" or root_dir = os.getcwd()

root_dir = os.getcwd()

try:
    root_dir = sys.argv[1]
except Exception as err:
    print "Exception caught:", err
    print "Try passing an argument"
    exit()


if not os.path.exists(root_dir):
    print "Invalid path"
    exit()


def parse_file(filename):
    """
    This will parse the C# file given as argument and will get out of it all the classes, implementations etc
    :return: relations -> a dictionary with all the classes and their relations; implementations etc
    >> parse_file(path)
    """

    global classes, implementations, relations, found_list

    with open(filename, 'r') as f:
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
    """
    This will get all the necessary information as a dictionary for it to be used to get the UML diagram, recursively
    walking through the directory that was given as argument.
    parse_file function is used here for getting the information
    :return: relations -> a dictionary with all the classes and their relations; implementations etc
    >> info = get_info()
    """
    for folder, dirs, files in os.walk(root_dir):
        for filename in files:
            if filename.endswith('.cs'):
                full_path = os.path.join(folder, filename)
                try:
                    parse_file(full_path)
                except Exception as e:
                    print "Exception caught:", e
    return relations


def concat_images(images, unique):
    """
    This function will concatenate all the images given as the parameter in a single image and will save it
        in the running directory. It runs recursively in the root_dir directory.
    :param images: this is an array containing images' paths that will be used for concatenation
    :return: it creates a new image, final_diagram.png with everything combined
    >> concat_images(["C:\img1.png", "D:\imgX.png", "C:\Users\JohnDoe\Test\img.jpg", "F:\image.jpeg"])
    final_diagram.png
    """
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
    new_im.save('final_diagram-' + unique + '.png')


def main():
    """
    This function calls get_info() in order to get a dictionary with everything it needs.
    After printing that dictionary, using the PlantUML library, it creates a new UML diagram.
    For every image in the running directory, the function adds the paths to to_concat array and then calls
    concat_images with all those paths.
    :return: it creates uml_no images with a single UML diagram per image, then it calls concat_images
    >> main()
    """
    unique = str(time.time())
    info = get_info()
    print(info)
    to_concat = []
    uml = plantuml.PlantUML()
    uml_no = 0
    for d in info:
        uml_no += 1
        file_name = (str(uml_no) + '-' + unique + '.png')
        with open(file_name, 'wb') as out:
            out.write(uml.processes(dict2uml.dict2plantuml(d)))
        if os.path.isfile(file_name):
            to_concat += [file_name]

    if len(to_concat) is 0:
        print "No images found."
        print "Folder may be empty."
        exit()

    concat_images(to_concat, unique)


if __name__ == "__main__":
    main()
