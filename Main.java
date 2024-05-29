import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        playBattleships();
    }

    //this initialises the battleship game and starts the player takeTurn loop until the game ends in which the end score
    //is presented with a winner
    public static void playBattleships(){
        System.out.println("initialising game");
        //generates a board of 10x10 dimensions. Note only one board is generated unlike traditional battleship games.
        Board playingBoard = new Board(10, 10);
        //Requests the names for both players before beginning the game.
        System.out.println("Player 1 please select a name");
        //calls the getPlayerName method to retrieve a String that will be used in the Player class constructor for the
        //Players name. This will also give the newly constructed board to the Player class constructor
        Player playerOne = new Player(getPlayerName(), playingBoard);
        //Repeated for Player two as this is a two player game.
        System.out.println("Player 2 please select a name");
        Player playerTwo = new Player(getPlayerName(), playingBoard);

        /*
        this is the main gameplay loop that allows players to take turns after each other. Using an infinite while
        loop, the only way to break this is for either player one or player two to return true.
        The if statement works by checking playerOne's takeTurn first then if that is false it goes to playerTwo's
        takeTurn. If both return false then the if statement will not run its block so the while loop will repeat.
        The only way for this while loop to end is if either player returns true which can only be done when the
        total number of remaining ships reaches 0. If this happens the if statement block is run and the break is executed.
         */
        while(true){
            if(playerOne.takeTurn() || playerTwo.takeTurn()){
                break;
            }
        }

        //checks who the winner is based on who has the higher score.
        if(playerOne.getScore() > playerTwo.getScore()){
            System.out.println(String.format("The winner is %s!!!", playerOne.getName()));
        }
        else if(playerOne.getScore() < playerTwo.getScore()){
            System.out.println(String.format("The winner is %s!!!", playerTwo.getName()));
        }
        //This runs if there is a draw
        else{
            System.out.println("The score is a tie!");
        }

        //this is the end text that will play when the game ends, displaying the winner and the overall score by utilising getters in the Player class
        //the score format will look like - Player 1 Score : Score Player 2
        System.out.println(String.format("The final score is as follows:\n" +
        "%s %d : %d %s", playerOne.getName(), playerOne.getScore(), playerTwo.getScore(), playerTwo.getName()));
    }

    //retrieves the player name using a Scanner object
    public static String getPlayerName(){
        Scanner userInput = new Scanner(System.in);
        String userName = userInput.nextLine();
        System.out.println(String.format("Welcome %s to battleships!", userName));
        return userName;
    }
}