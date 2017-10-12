set projectLocation=C:\Users\OM\Downloads\Programs\GmailCucumber
cd %projectLocation%
set classpath=%projectLocation%\bin;%projectLocation%\lib\*
java org.testng.TestNG %projectLocation%\testng.xml
pause