import os
root_dir = r'C:\dir' 


def get_info():
    classes = []
    interfaces = []
    implementations = []
    extensions = []

    for folder, dirs, files in os.walk(root_dir):
        for file in files:
            if file.endswith('.java'):
                full_path = os.path.join(folder, file)
                with open(full_path, 'r') as f:
                    for line in f:
                        if "class " in line:
                            if " extends " not in line:
                                line = line.split(' ', 1)[1]
                                classes += [line.rstrip()]
                            else:
                                implementations += [line.rstrip()]
                        elif "interface " in line:
                            line = line.split(' ', 1)[1]
                            interfaces += [line.rstrip()]

    return classes, \
        interfaces,\
        implementations,\
        extensions


if __name__ == "__main__":
    print(get_info())