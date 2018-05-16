from subprocess import check_output
import ast

out = check_output(["python2", "c#.py", "D:\\Faculty\\IP\\ProiectIPM3\\reverse_c#\\EasyHttp"])
l = ast.literal_eval(out.decode('utf-8').strip())
all_good = [{'Exception': {'ConfigurationException:': {}}}, {'FileData': {}}, {':': {'HttpException': {}}}, {'class': {}}, {'PropertyValue': {}}, {'ObjectToUrl': {'ObjectToUrlParameters': {}}}, {'class': {}}, {'UriComposer': {}}]

if l == all_good:
    print "Test passed!"
else:
    print "Test failed."