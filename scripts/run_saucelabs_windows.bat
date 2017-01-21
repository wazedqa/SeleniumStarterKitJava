@ECHO OFF
REM Usage : mvn test -D[country]=domain(de/it etc) -D[suiteXmlFile]=targeted xml file for sauce labs
REM saucelabs related configs can be edited inside the xml.
REM SAUCE_USERNAME and SAUCE_ACCESS_KEY needs to be set in environment variables.

mvn test -Dcountry=de -DsuiteXmlFile=testng-saucelabs.xml -Ddriver.url=http://%SAUCE_USERNAME%:%SAUCE_ACCESS_KEY%@ondemand.saucelabs.com:80/wd/hub