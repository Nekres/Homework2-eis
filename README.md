# Homework2-eis
Junit test profiles:

mvn -P skip-compile clean install - to skip compilation and execution<br>
mvn -P skip-execution clean install - to skip execution, but not compilation<br>
mvn clean install - default, compilation and execution

Integration test profiles:

mvn -P it-execution clean install - to enable compilation and execution


common module used for common resources like properties files
