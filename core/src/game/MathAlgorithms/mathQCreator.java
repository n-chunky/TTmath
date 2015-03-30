package game.MathAlgorithms;


import java.util.Random;


/*
 * Math Algorithm program
 * Author: Nathan Nguyen
 * Description: This class generates math problems
 * parameters: path, level, number of doors and gamemode
 * path is the chosen difficulty of the player
 * level is their current level, and gamemode is the mode they have chosen
 * there will be an array of questions for the num of doors
 * the answers will be stored in a 2d array, where the first row represents the question
 * and the second row represents the answers with the 5th column representing the correct index answer
 * might be private and an interface might be used to retrieve data
 * might split to two classes, one for questions and one for answers
 * don't really know what to do with it yet, but this class is kinda huge
 * 
 * NOTES ON STUFF THAT HAS NOT BEEN TAKEN INTO CONSIDERATION:
 * the real answer can never be negative, however, the randomly generated answer array might produce a fake negative answer
 */
public class mathQCreator{


	private int numOfDoors;
	private int mode;
	private int operations[];
	private int[][] answers;
	private String[] questions;
	//standard initial number used with level for creating questions
	private static final int initial = 6;

	mathQCreator(){}

	/*
	 * This method runs the operations that creates the questions and answers
	 * gamemode: regular maze is 1, mental math is 2, future endless maze 3
	 */

	public void runOperation(int path, int level, int doors, int gamemode){
		//if needed
		numOfDoors = doors;
		answers = new int[numOfDoors][5];
		questions = new String[numOfDoors];
		operations = new int[numOfDoors];
		mode = gamemode;

		//if its gamemode 2 mental math, there is actually only one question
		for(int i=0;i<numOfDoors;i++){
			switch(mode){
			case 1: operations[i] = operationGenerator(path);
			break;
			case 2: operations[i] = path;
			break;
			default: 
				System.err.println("error getting operations");
				break;
			}
		}
		questions = generateMathQuestion(operations, level);

	}
	/*
	 * This method gets the math operation for the question in the level
	 * parameters: path
	 * returns: int math operation
	 * int[] operations: first index is adding, second index is subtracting, third is multiplying, fourth is division
	 * each index holds their respective index
	 */
	private int operationGenerator(int path){
		Random rand = new Random();
		int difficulty;	
		int[] operations = getPathsArray(path);
		do{
			difficulty = rand.nextInt(operations.length);
			if(operations[difficulty] != -1){
				break;
			}
		}while(operations[difficulty] == -1);

		return operations[difficulty];
	}

	//maybe not needed
	public int[] getOperation(){
		return operations;
	}

	/*
	 * this method gets the operations based on the path chosen by user. returns int array
	 * int[] operations: first index is adding, second index is subtracting, third is multiplying, fourth is division
	 * each index holds an integer, where -1 means not present
	 */
	private int[] getPathsArray(int path){
		int[] operations = new int[4];			//the math operations
		switch(path){
		case 1:
			operations[0] = 0;
			operations[1] = 1;
			operations[2] = -1;
			operations[3] = -1;
			break;
		case 2:
			operations[0] = 0;
			operations[1] = -1;
			operations[2] = 2;
			operations[3] = -1;
			break;
		case 3:
			operations[0] = -1;
			operations[1] = -1;
			operations[2] = 2;
			operations[3] = 3;
			break;
		case 4:
			operations[0] = 0;
			operations[1] = -1;
			operations[2] = 2;
			operations[3] = 3;
			break;
		case 5:
			operations[0] = -1;
			operations[1] = 1;
			operations[2] = 2;
			operations[3] = 3;
			break;
		case 6:
			operations[0] = 0;
			operations[1] = 1;
			operations[2] = 2;
			operations[3] = 3;
			break;
		default:
			System.err.println("operations mix not successful: ");
			for(int i=0;i<operations.length;i++){
				System.err.print(operations[0] + " ");
			}
			break;
		}

		return operations;
	}

	/*
	 * Method generates math questions in Strings
	 * Answers are also generated here in the 2d array
	 * returns a String array
	 */
	private String[] generateMathQuestion(int[] operation, int level){
		Random rand = new Random();
		String[] q = new String[operation.length];
		int firstN;
		int secondN;
		int answer = 0;
		if(level<=5){
			for(int i=0;i<operation.length;i++){
				switch(operation[i]){
				//adding
				case 0:
					firstN = rand.nextInt(initial + level);
					secondN = rand.nextInt(initial + level);
					answer = firstN + secondN;
					answers[i] = randomizeAnswer(answer);
					q[i] = firstN + " + " + secondN; 
					break;
					//subtracting
				case 1:
					firstN = rand.nextInt(initial + level);
					secondN = rand.nextInt(initial + level);
					if(firstN < secondN){
						answer = secondN - firstN;
						q[i] = secondN + " - " + firstN; 
					}
					else {
						answer = firstN - secondN;
						q[i] = firstN + " - " + secondN;
					}
					answers[i] = randomizeAnswer(answer);
					break;
					//multiplying
				case 2:
					firstN = rand.nextInt(initial + level);
					secondN = rand.nextInt(initial + level);
					answer = firstN * secondN;
					answers[i] = randomizeAnswer(answer);
					q[i] = firstN + " x " + secondN; 
					break;
					//dividing
				case 3:
					/*different, same thing as multiplying, but our parameter for randomizeAnswer is 
					*either the first or second number. We will use the Random rand to choose either
					*so the variable "answer" and one of the other numbers will be part of the question
					*/
					firstN = rand.nextInt(initial + level);
					secondN = rand.nextInt(initial + level);
					answer = firstN * secondN;
					if(rand.nextInt(2)==1 && firstN != 0){
						answers[i] = randomizeAnswer(secondN);
						q[i] = answer + " \u00F7 " + firstN;
					}
					else{
						answers[i] = randomizeAnswer(firstN);
						q[i] = answer + " \u00F7 " + secondN;
					}
					break;
				default:
					System.err.println("error occurred generating math question: operation:" 
							+ operation[i] + " index is: " + i);
					break;
				}
			}
		}
		else if(level>5){
			for(int i=0;i<operation.length;i++){
				switch(operation[i]){
				//adding
				case 0:
					firstN = rand.nextInt(initial + (level * 5) + 1);
					secondN = rand.nextInt(initial + (level * 5) + 1);
					answer = firstN + secondN;
					answers[i] = randomizeAnswer(answer);
					q[i] = firstN + " + " + secondN; 
					break;
					//subtracting
				case 1:
					firstN = rand.nextInt(initial + (level * 5) + 1);
					secondN = rand.nextInt(initial + (level * 5) + 1);
					if(firstN < secondN){
						answer = secondN - firstN;
						q[i] = secondN + " - " + firstN; 
					}
					else {
						answer = firstN - secondN;
						q[i] = firstN + " - " + secondN;
					}
					answers[i] = randomizeAnswer(answer);
					break;
					//multiplying
				case 2:
					firstN = rand.nextInt((level + 4) + 1) + initial;
					secondN = rand.nextInt((level + 4) + 1) + initial;
					answer = firstN * secondN;
					answers[i] = randomizeAnswer(answer);
					q[i] = firstN + " x " + secondN; 
					break;
					//dividing
				case 3:
					/*different, same thing as multiplying, but our parameter for randomizeAnswer is 
					 *either the first or second number. We will use the Random rand to choose either
					 *so the variable "answer" and one of the other numbers will be part of the question
					 */
					firstN = rand.nextInt((level + 4) + 1) + initial;
					secondN = rand.nextInt((level + 4) + 1) + initial;
					answer = firstN * secondN;
					if(rand.nextInt(2)==1 && firstN != 0){
						answers[i] = randomizeAnswer(secondN);
						q[i] = answer + " \u00F7 " + firstN;
					}
					else{
						answers[i] = randomizeAnswer(firstN);
						q[i] = answer + " \u00F7 " + secondN;
					}
					break;
				default:
					System.err.println("error occurred generating math question: operation:" 
							+ operation[i] + " index is: " + i);
					break;
				}
			}
		}

		return q;
	}

	/*
	 * Method creates randomized answers and returns an array of length 5
	 * the last index of the array holds the index of the correct answer
	 * returns int array
	 */
	private int[] randomizeAnswer(int answer){
		Random rand = new Random();
		int answers[] = new int[5];
		int max = answer+6;
		int min = answer-6;
		int realA = rand.nextInt(3+1);
		for(int i=0;i<4;i++){
			answers[i] = rand.nextInt(max-min+1)+min;
		}
		answers[realA] = answer;
		answers[4] = realA;
		return answers;
	}

	public int[][] getAnswers(){
		return answers;
	}

	public int getCorrectAnswer(int questionNumber){
		return answers[questionNumber][answers[questionNumber][4]];
	}

	public String[] getQuestions(){
		return questions;
	}
}