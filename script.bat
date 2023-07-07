cd framework/src
javac -parameters -d . *.java
jar -cvf ../../etu1831.jar etu1831
copy ../../etu1831.jar ../../test-framework/WEB-INF/lib/

set CLASSPATH=.;D:/Sprint/WebDynamique/Sprint7-2/framework/etu1831.jar

cd ../../test-framework/WEB-INF/classes
javac -parameters -d . *.java
cd ../../
jar -cvf ../test-framework.war .

cd ../
pause
