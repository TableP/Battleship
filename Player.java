import java.util.Scanner;

public class Player {
    private String name;
    private int score;
    private Board currentBoard;

    public Player(String name, Board playingBoard){
        this.name = name;
        this.currentBoard = playingBoard;
    }

    //main function for handling the user input and checking the state of square being selected by user. Will return false
    //unless the last ship is sunk then it will return true.
    public boolean takeTurn(){
        //at the start of the turn the board will be printed to show the state of the game
        System.out.println(currentBoard);
        //gets the multidimensional array of squares from the board class
        Square[][] currentBoardSquares = this.currentBoard.getBoardSpace();
        //boolean represents if the game is over or not
        boolean isGameOver = false;

        Scanner playerInput = new Scanner(System.in);
        System.out.println(String.format("It is your turn %s!\nPlease choose a square using format \"x y\" (e.g. \"0 0\")", this.name));
        //assumes player will correctly enter x and y coordinate in desired format
        int xInput = playerInput.nextInt();
        int yInput = playerInput.nextInt();
        //for the sake of readability, a variable representing the square selected has been made
        Square squareSelected = currentBoardSquares[yInput][xInput];

        //checks if square has been hit before, if it has then the player's turn is skipped
        if(squareSelected.getIsHit()){
            System.out.println(String.format("Square has been selected before, %s's turn will be skipped (INVALID)", this.name));
        }
        else{
            //sets the square's isHit variable to true as it has now been hit
            squareSelected.setIsHit(true);
            //if the square is occupied then this if statement block will be run
            if(squareSelected.getIsOccupied()){
                System.out.println(String.format("%s has hit a ship! (HIT)", this.name));
                //creates a variable for the current ship occupying the square for readability
                Battleship currentShip = squareSelected.getOccupyingShip();
                //reduces the remaining hp of the ship by 1
                currentShip.hitShip();

                //if the ship has sunk (hp is now 0) then this if statement block is run
                if(currentShip.getIsSunk()){
                    System.out.println(String.format("%s has sunk a ship!", this.name));
                    //the player will get a point for destroying the ship
                    this.score++;
                    //grabs the number of ships remaining
                    int numberOfShipsRemaining = currentBoard.getNumberOfShipsRemaining();
                    //the number of ships remaining on the Board will be decreased by 1
                    currentBoard.setNumberOfShipsRemaining(--numberOfShipsRemaining);
                    System.out.println(String.format("Number of ships remaining: %d", numberOfShipsRemaining));
                    //if the number of ships remaining hits 0 then the method will return true, signalling the game has ended
                    if(numberOfShipsRemaining == 0){
                        System.out.println(String.format("%s has sunken the last ship!\nThe game will now end.", this.name));
                        isGameOver = true;
                    }
                }
            }
            //if the square is not occupied then it will be a miss
            else{
                System.out.println(String.format("%s has missed! (MISS)", this.name));
            }
        }
        //this will return false to indicate that the game has not ended yet
        return isGameOver;
    }
    //SETTERS AND GETTERS
    public void setName(String name){
        this.name = name;
    }
    public void setScore(int score){
        this.score = score;
    }
    public void setCurrentBoard(Board currentBoard){
        this.currentBoard = currentBoard;
    }
    public String getName(){
        return this.name;
    }
    public int getScore(){
        return this.score;
    }
    public Board getCurrentBoard(){
        return this.currentBoard;
    }

}
