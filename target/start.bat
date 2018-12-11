@ECHO OFF
REM change CHCP to UTF-8
CHCP 65001
CLS
java java -Dfile.encoding=UTF-8 -jar ISACore-0.0.1-SNAPSHOT.jar