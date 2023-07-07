cd framework/src
javac -d . *.java 
jar -cvf ../../etu1831.jar etu1831
copy ../../etu1831.jar ../../test-framework/WEB-INF/lib/

REM Chemin du fichier etu1831.jar
set "cheminJar=D:/Sprint/WebDynamique/Sprint4/etu1831.jar"

REM Verifier si la variable d'environnement CLASSPATH est deja definie
if not defined CLASSPATH (
  REM Si elle n'est pas definie, definir la variable CLASSPATH avec le chemin du fichier jar
  set "CLASSPATH=%cheminJar%"
) else (
  REM Si elle est deja definie, ajouter le chemin du fichier jar à la variable CLASSPATH
  set "CLASSPATH=%CLASSPATH%;%cheminJar%"
)

cd ../../test-framework/WEB-INF/classes
javac -d . *.java 
cd ../../
jar -cvf ../test-framework.war .
pause
