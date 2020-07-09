# Spark eventReader And Session Finder

A Sample Project for reading tsv and json file and finding session duration.


## Prereuisite
java1.8
scala 2.11.8


# build and test Steps
git clone https://github.com/rezawasim/user-session-management.git
cd user-session-management
./gradlew clean build

## To Run the Application 
./gradlew run  
#### default Input --> ./sampleFile  
#### defaultOutput --> ./sessionDuration, ./userSession 

#### sessionDuration contains sessionId duration
#### userSession contains each user's average session duration and sessionCount              

#Cluser Deployemt
To deploy in a cluster we need cluster manager(eg yarn)
Added buildAndDeploy.sh for cluster deployment



## To run the testSuit
./gradlew test


# Future scope of this project
1. Dockerization
2. Add input output path from commandLine
3. Supporting higher Spark version
4. Improve test cases
5. Dataframe support addition for file reading
6. Adding test cases for file reading and writing
7. Testing application in multiple cluster
8. Salting of data for skedness
