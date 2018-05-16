from subprocess import check_output
import ast


def convert_to_list(string):
    return ast.literal_eval(string.decode('utf-8').strip())


def test_1():
    out = check_output(["python2", "c#.py", "D:\\Faculty\\IP\\ProiectIPM3\\reverse_c#\\EasyHttp"])
    l = convert_to_list(out)
    all_good = [{'Exception': {'ConfigurationException:': {}}}, {'FileData': {}}, {':': {'HttpException': {}}}, {'class': {}}, {'PropertyValue': {}}, {'ObjectToUrl': {'ObjectToUrlParameters': {}}}, {'class': {}}, {'UriComposer': {}}]

    return l == all_good


def test_2():
    out = check_output(["python2", "c#.py", "D:\\Faculty\\IP\\ProiectIPM3\\reverse_c#\\empty_test"])
    out = out.split('\n')[0]
    l = convert_to_list(out)
    all_good = []

    return l == all_good        


def main():
    if test_1():
        print "Test 1 passed!"
    else:  
        print "Test 1 failed."

    if test_2():
        print "Test 2 passed!"
    else:
        print "Test 2 failed."


if __name__ == "__main__":
    main()