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
Frame		    1		    2		    3		    4		    5		    6		    7		    8		    9		   10		
JEFF
Pinfalls	   X		 7 /		 9 0		   X		 0 8		 8 /		 F 6		   X		   X		 X 8 1
Score		   20		   39		   48		   66		   74		   84		   90		  120		  148		  167
JOHN
Pinfalls	 3 /		 6 3		   X		 8 1		   X		   X		 9 0		 7 /		 4 4		 X 9 0
Score		   16		   25		   44		   53		   82		  101		  110		  124		  132		  151
CARL
Pinfalls	   X		   X		   X		   X		   X		   X		   X		   X		   X		 X X X
Score		   30		   60		   90		  120		  150		  180		  210		  240		  270		  300
FAILED
Pinfalls	 F F		 F F		 F F		 F F		 F F		 F F		 F F		 F F		 F F		 F F F
Score		    0		    0		    0		    0		    0		    0		    0		    0		    0		    0
ZERO
Pinfalls	 0 0		 0 0		 0 0		 0 0		 0 0		 0 0		 0 0		 0 0		 0 0		 0 0 0
Score		    0		    0		    0		    0		    0		    0		    0		    0		    0		    0
INCOMPLETE [Invalid Game]
Pinfalls	   X		 F 8		 6 /
Score		    0		    0		    0
BIGGER [Invalid Game]
Pinfalls	
Score		
SMALLER [Invalid Game]
Pinfalls	
Score		

```

### Invalid cases

You can find some invalid test cases on ./invalid
Example Test File: exceeds-max-value-in-sum.txt

```
Jeff 10
Jeff 8 -- Consecutive sum of values 8 & 6 are invalid
Jeff 6
Jeff 9
Jeff 0
Jeff 10
Jeff 0
Jeff 8
Jeff 8
Jeff 2
Jeff F
Jeff 6
Jeff 10
Jeff 10
Jeff 10
Jeff 8
Jeff 1

```

#### Examples of Expected Outputs

```
ERROR | 2019-11-01 00:49:37 | [main] alenasoft.App (App.java:30) - Invalid Number of Attempts [3] for [INCOMPLETE] player

ERROR | 2019-11-01 01:31:47 | [main] alenasoft.App (App.java:30) - The current sum of points [14] in Frame [2] exceeds the max allowed (10)
```
