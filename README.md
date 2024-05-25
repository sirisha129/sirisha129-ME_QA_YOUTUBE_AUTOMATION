# Project Name - sirisha129-ME_QA_YOUTUBE_AUTOMATION

## Project Name and Description:
YouTube Automation , Automate the youtube and check the Films, Music and News tab. Using DataProvider to Search for various video views counts from a data-set as ExcelSheet.

## Installation Instructions:
setUp a local Environment,for that you need 
1. IDE(of your choice,i used VSCode)
2. Java(Latest Version)
3. Gradle(I used a build tool called Gradle in my Project)
4. Git(Latest Version for clone,add,commit,push and change the code)
5. TestNG(get the Latest version from MVNrepository) and check the attachment below
6. Under Test Folder u need to Create Resources Folder
7. Create testng.xml and provide your class path
8. ApachePOI(get the Latest version from MVNrepository) and check the attachment below
9. Under resources folder add your Excel file aswell.

> Example:
```
# java version 17 
java --version
# Gradle version 8.6
gradle --version
# Git 2.44
git --version
#VSCode 1.89.1
code --version
#TestNG 6.14.3
Add dependency into build.gradle
#ApachePOI 5.2.2 also log4j
Add dependency into build.gradle(check my build.gradle file for reference)
```

## Usage and Examples:
Iam providing some scenarios to showcase how the project works.

1. YouTube About Section: Go to YouTube.com, verify the URL, click "About" in the sidebar, and print the displayed message.

2. Top Selling in Movies Tab: Go to the "Movies" tab, scroll to the extreme right in the "Top Selling" section, and check if the movie is marked "A" for Mature and if it belongs to the "Comedy" or "Animation" genres.

3. 1st Section of Music Tab : Go to the "Music" tab, scroll to the extreme right in the first section, print the playlist name, and check if it has 50 or fewer tracks.

4. Latest Posts in News Tab : Go to the "News" tab, print the title and body of the first three "Latest News Posts," and sum the likes of all three posts (0 if no likes).

5. Excel Sheet Search: Search for each item from the Excel sheet(Using Dataprovider), scroll until the total views of videos reach 10 Crores (100 million).

TestNG Instructions:

1. I also want to tell, Follow TestNG setup folder structure into your 'build.gradle' is important to run your Unit Testacses,so please follow the Link which is under Important Links.

In breif: 
1. Add dependency of testNG (//https://mvnrepository.com/artifact/org.testng/testng/7.9.0
    testImplementation group: 'org.testng', name: 'testng', version: '6.14.3')
2. Cretae a test task like this (test{})
3. Inside test  give useTestNG() {provide your testng.xml path}
4. Make sure to save your build or refresh the build.
5. IMPORTANT( when u create a unit test file u will easily annotate using testNG annotations,if not ur testNG setup
   or xml is having issue.)


> Example:
```
# to run the project
./gradlew test

```

## Important Links:

Details about checklist to set up your project in Local,follow these:
https://docs.google.com/spreadsheets/d/1E-OeFwu5h0Jlxs_v0gktC3QEbytf65TjF5OhuUtm4A8/edit#gid=0


 
