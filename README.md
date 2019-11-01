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

  `$ java -jar bowling-0.1.jar /path/to/bowling-data-tab-separated.txt`
  
### About the txt file
- The file should contains only valid formatted rows (Name and Value)
- The row could be tab-separated or space-separated
  (Check both test files bowling-data-tab-separated.txt & bowling-data-space-separated.txt)
  
#### Quick Build/Run tip for Linux
In order to build and run the base tests cases you can run the next command (tested only on linux terminal):

  `mvn clean package && java -jar target/bowling-0.1.jar ./bowling-data-tab-separated.txt`

  
## Test cases

The file should be composed by rows with player name and points as the next example:

#### Valid cases

Test File: bowling-data-tab-separated.txt

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

#### Expected output

```
Frame		1		2		3		4		5		6		7		8		9		10
JEFF
Pinfalls		X	7	/	9	0		X	0	8	8	/	F	6		X		X	X	8	1
Score		20		39		48		66		74		84		90		120		148		167
JOHN
Pinfalls	3	/	6	3		X	8	1		X		X	9	0	7	/	4	4	X	9	0
Score		16		25		44		53		82		101		110		124		132		151
CARL
Pinfalls		X		X		X		X		X		X		X		X		X	X	X	X
Score		30		60		90		120		150		180		210		240		270		300
FAILED
Pinfalls	F	F	F	F	F	F	F	F	F	F	F	F	F	F	F	F	F	F	F	F	F
Score		0		0		0		0		0		0		0		0		0		0
ZERO
Pinfalls	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
Score		0		0		0		0		0		0		0		0		0		0

```

### Invalid cases

You can find some invalid test cases on ./invalid

Example Test File: bowling-data-space-separated-with-errors.txt

  `java -jar target/bowling-0.1.jar ./bowling-data-space-separated-with-errors.txt`

#### Example of Outputs with invalid PlayerGames

```
INFO  | 2019-11-01 09:54:25 | [main] commons.ScoreParserImpl (ScoreParserImpl.java:40) - Invalid Score found [20], this invalidates the PlayerGame
INFO  | 2019-11-01 09:54:25 | [main] commons.ScoreParserImpl (ScoreParserImpl.java:40) - Invalid Score found [-20], this invalidates the PlayerGame
Frame		1		2		3		4		5		6		7		8		9		10
JOHN
Pinfalls	3	/	6	3		X	8	1		X		X	9	0	7	/	4	4	X	9	0
Score		16		25		44		53		82		101		110		124		132		151
CARL
Pinfalls		X		X		X		X		X		X		X		X		X	X	X	X
Score		30		60		90		120		150		180		210		240		270		300
FAILED
Pinfalls	F	F	F	F	F	F	F	F	F	F	F	F	F	F	F	F	F	F	F	F	F
Score		0		0		0		0		0		0		0		0		0		0
ZERO
Pinfalls	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
Score		0		0		0		0		0		0		0		0		0		0
[JEFF does not have a valid game]
[BIGGER does not have a valid game]
[INCOMPLETE does not have a valid game]
[SMALLER does not have a valid game]
```

Notice that PlayerGames with error are not calculated, but they are list at the bottom of the output 