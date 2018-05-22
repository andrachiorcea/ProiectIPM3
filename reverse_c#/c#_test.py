import os
from subprocess import check_output
import ast

print (os.getcwd())
proj_dir = os.getcwd()  # "C:\\Users\\cpoenaru\\Documents\\GitHub\\Facultate\\ProiectIPM3\\reverse_c#"
python_path = "C:\Python27"


def convert_to_list(string):
    return ast.literal_eval(string.decode('utf-8').strip())


def check_good_file():
    out = check_output([os.path.join(python_path, "python.exe"), "c#.py",
                        os.path.join(proj_dir, "EasyHttp")])
    expected_output = [{'Exception': {'ConfigurationException:': {}}}, {'FileData': {}}, {':': {'HttpException': {}}},
                       {'class': {}}, {'PropertyValue': {}}, {'ObjectToUrl': {'ObjectToUrlParameters': {}}},
                       {'class': {}}, {'UriComposer': {}}]

    actual_output = convert_to_list(out)

    return actual_output == expected_output


def check_good_file_bad_output():
    out = check_output([os.path.join(python_path, "python.exe"), "c#.py",
                        os.path.join(proj_dir, "EasyHttp")])
    expected_output = [{'Exception': {'ConfigurationException:': {}}}, {'FileData': {}}, {':': {'HttpException': {}}},
                       {'class': {}}, {'PropertyValue': {}}, {'ObjectToUrl': {'ObjectToUrlParameters': {}}},
                       {'class': {}}]

    actual_output = convert_to_list(out)

    return actual_output != expected_output


def check_no_class():
    out = check_output([os.path.join(python_path, "python.exe"), "c#.py",
                        os.path.join(proj_dir, "Tests\\no_class")])
    out_list = out.split('\n')[0]
    expected_output = []

    actual_output = convert_to_list(out_list)

    return actual_output == expected_output


def check_invalid_directory():
    out = check_output([os.path.join(python_path, "python.exe"), "c#.py",
                        os.path.join(proj_dir, "Tests\\invalid_directory")])
    out = out.split('\n')[0]
    actual_output = convert_to_list(out)
    expected_output = []

    return actual_output == expected_output


def check_empty_directory():
    out = check_output([os.path.join(python_path, "python.exe"), "c#.py",
                        os.path.join(proj_dir, "Tests\\empty_dir")])
    out = out.split('\n')[0]
    actual_output = convert_to_list(out)
    expected_output = []

    return actual_output == expected_output


def check_invalid_file():
    out = check_output([os.path.join(python_path, "python.exe"), "c#.py",
                        os.path.join(proj_dir, "Tests\\png_file")])
    out_list = out.split('\n')[0]
    actual_output = convert_to_list(out_list)
    expected_output = []

    return actual_output == expected_output and "No images found" in out


def check_empty_file():
    out = check_output([os.path.join(python_path, "python.exe"), "c#.py",
                        os.path.join(proj_dir, "Tests\\empty_file")])
    out_list = out.split('\n')[0]
    actual_output = convert_to_list(out_list)
    expected_output = []

    return actual_output == expected_output and "No images found" in out


def main():
    if check_good_file():
        print "1. [PASSED] Good file was parsed correctly!"
    else:
        print "1. [FAILED] Good file was parsed incorrectly!"

    if check_good_file_bad_output():
        print "2. [PASSED] File was parsed correctly, bad output detected!"
    else:
        print "[FAILED] File was parsed incorrectly!"

    if check_invalid_directory():
        print "3. [PASSED] Invalid directory was parsed correctly!"
    else:
        print "3. [FAILED] Invalid directory was parsed incorrectly!"

    if check_empty_directory():
        print "4. [PASSED] Invalid directory was parsed correctly!"
    else:
        print "4. [FAILED] Invalid directory was parsed incorrectly!"

    if check_invalid_file():
        print "5. [PASSED] Invalid file was parsed correctly!"
    else:
        print "6. [FAILED] Invalid file was parsed incorrectly!"

    if check_empty_file():
        print "7. [PASSED] Empty file was parsed correctly!"
    else:
        print "7. [FAILED] Empty file was parsed incorrectly!"

    if check_no_class():
        print "8. [PASSED] File with no classes was parsed correctly!"
    else:
        print "8. [FAILED] File with no classes was parsed incorrectly!"


if __name__ == "__main__":
    main()
