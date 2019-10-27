## Ten Pin Bowling

### Software Requirements

- Java (JDK & JRE 8)
- Apache Maven

#### Coding notes
- This is a maven/java project
- First structure create with maven-archetype-quickstart archetype

### Build artifact

To build the JAR artifact, runs the next command line.

  `$ mvn clean package`
  
This will generate the executable target/bowling-0.1.jar

### Execute application

This is a JAR application and it's designed to run on console, to execute use the next command
and pass a file path as parameter

  `$ java -jar bowling-0.1.jar /path/to/bowling-data.txt`
  
#### Quick Linux
In order to build and run the base tests cases you can run the next command (tested only on linux terminal):

  `mvn clean package && java -jar target/bowling-0.1.jar ./bowling-data.txt`

  
## Test cases

The file should be composed by rows with player name and points as the next example:

```
Jeff 10
John 3
John 7
Jeff 7
Jeff 3
John 6
John 3
Jeff 9
Jeff 0
John 10
Jeff 10
John 8
John 1
Jeff 0
Jeff 8
John 10
Jeff 8
Jeff 2
John 10
Jeff F
Jeff 6
John 9
John 0
Jeff 10
John 7
John 3
Jeff 10
John 4
John 4
Jeff 10
Jeff 8
Jeff 1
John 10
John 9
John 0
Carl 10
Carl 10
Carl 10
Carl 10
Carl 10
Carl 10
Carl 10
Carl 10
Carl 10
Carl 10
Carl 10
Carl 10
Failed F
Failed F
Failed F
Failed F
Failed F
Failed F
Failed F
Failed F
Failed F
Failed F
Failed F
Failed F
Failed F
Failed F
Failed F
Failed F
Failed F
Failed F
Failed F
Failed F
Failed F
Zero 0
Zero 0
Zero 0
Zero 0
Zero 0
Zero 0
Zero 0
Zero 0
Zero 0
Zero 0
Zero 0
Zero 0
Zero 0
Zero 0
Zero 0
Zero 0
Zero 0
Zero 0
Zero 0
Zero 0
Zero 0

```