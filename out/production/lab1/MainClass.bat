echo off 
set path=C:\Program Files\Java\jdk1.8.0_251\bin
set include=C:\Program Files\Java\jdk1.8.0_251\include
set lib=C:\Program Files\Java\jdk1.8.0_251\lib
set link=C:\Program Files\Java\jdk1.8.0_251\bin
javac -version MainClass.java
java MainClass
javap -c MainClass>1.txt
javadoc MainClass.java -d 1
pause 