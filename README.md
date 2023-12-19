# K-Means
Manual
1.	Download MySQL: https://dev.mysql.com/downloads/installer/ . 
2.	Open a terminal: key: Windows + key: R. Execute cmd, and introduce the following commands:
-	Enter: cd \. 
-	Enter: C:\Program Files\MySQL\MySQL Server 8.0\bin
-	Enter: mysql -h localhost -P 3306 -u root -p
-	Enter Your Password
-	Enter: source “mysql path of the file folder of the project: */Project guide/created.sql”
-	Enter: exit
-	Enter: cd \.
-	Enter: cd “path of the file folder of my project: cd */Project guide/”
-	Enter: start java -jar Server.jar
-	Enter: start java -jar Client.jar localhost 8080
If this is ok go to step 6.
IF SOMETHING GOES WRONG:
3.	Create MySQL table in your system: Ones you downloaded and set your MySQL then is time to create our table: Execute created.sql file, contained in this folder, in your workbench connected to localhost. Maybe you have a problem in line 6: 
-	GRANT CREATE, SELECT, INSERT, DELETE ON MapDB.* TO MapUser@localhost IDENTIFIED BY 'map';
I solved it removing the identified, as you previously identified the user with the password map, it is not necessary in this line. So, line 6 will remain like this:
-	GRANT CREATE, SELECT, INSERT, DELETE ON MapDB.* TO MapUser@localhost;
Then execute the program in File of the workbench click on Run SQL Script.

4.	Now our table is already defined in our MySQL database, is time to set the project.
The are two projects, KMeansServer which is the server of program. And KMeansClient, which is the client program version.
I uploaded the projects in github. To be able to get it, start Eclipse IDE java, File -> Import -> Projects From Git (with smart import) -> Clone URL. Then paste: 
https://github.com/gonzalodlr/KMeansServer.git
Click on Next -> Next -> Enter a directory of your eclipse to clone the project. (Notice: The name of this directory will be the name of the eclipse project). Be sure you have selected: -Search for nested projects, -Detect and configure project natures. Then click on finish.

Do the same to import the client project to eclipse, the URL of GitHub is:  
https://github.com/gonzalodlr/KMeansClient.git

5.	Now that you have the projects in Eclipse IDE, you can execute the server in eclipse environment or create the jar file: Select File -> Export -> Jar File. Select: Export sources and resources to also export the mysql connectors. Select: Export generated class files and resources. Introduce the directory and name of the jar. Click Finish. To run from the command line, open a terminal and executes: 
java -jar KMeansServer.jar
This is the command line for running the server. Must be initialise first.
java -jar KMeansClient.jar localhost 8080
This is the command line for running the client program.

6.	Now follow the terminal Information in the Client program.
