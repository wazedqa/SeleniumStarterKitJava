#!/bin/bash

# Usage : mvn test -D[country]=domain(de/it etc) -D[driver.browser]=chrome/firefox/iexplorer
# if driver.browser option is not given, chrome is chosen as the default browser.

mvn test -Dcountry=de