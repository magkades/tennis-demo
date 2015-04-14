# tennis-demo
App for tracking scores of tennis matches

## Prerequisites
* MySQL 5.5 or 5.6
* JDK 1.7 or 1.8
* Maven 3.*

## Install and run the tests
1. Download/clone the project
2. Prepare the database
  * import in MySQL the file that sets up the DB - [/src/ main/resources/input_data/DumpTennisDB.sql](https://github.com/magkades/tennis-demo/blob/master/src/main/resources/input_data/DumpTennisDB.sql)
  * username/password - `tennis`/`tennis`
3. Change to the root folder of the project and execute the unit tests
  * `mvn clean test`
