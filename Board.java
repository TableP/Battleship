import java.util.Random;

public class Board {

    private Square[][] boardSpace;
    //this integer displays the number of ships on the board that have not been sunk
    private int numberOfShipsRemaining;
    public Board(int x, int y){
        //calls the generateBoard method to create a board with dimensions input when creating the object
        generateBoard(x, y);
        //generates the battleships randomly on the board
        generateBattleships();
    }
    public void generateBoard(int x, int y){
        //the format of the array is Square[y][x] for ease when printing the arrays out as they will print out in a row
        boardSpace = new Square[y][x];
        for(int i = 0; i < y; i++){
            for(int j = 0; j < x; j++){
                Square newSquare = new Square(i, j);
                boardSpace[i][j] = newSquare;
            }
        }
    }

    //generateBattleships will call the randomBattleshipPlacement method using the type of ship and its static attribute of shipLimit that
    //will dictate the number of ships to be placed onto the grid
    public void generateBattleships(){

        /*
        these for loops will call the randomBattleshipPlacement method a number of times, giving it a ship object
        based on that objects shipLimit. For example, a SmallBattleship will be fed into the randomBattleshipPlacement method
        3 times as the shipLimit of a SmallBattleship is 3. This is repeated for MediumBattleship but not LargeBattleship as
        LargeBattleship only has a shipLimit of 1
         */
        for(int i = 0; i < SmallBattleship.getShipLimit(); i++){
            randomBattleshipPlacement(new SmallBattleship());
        }
        for(int j = 0; j < MediumBattleship.getShipLimit(); j++){
            randomBattleshipPlacement(new MediumBattleship());
        }
        randomBattleshipPlacement(new LargeBattleship());
    }
    /*
    randomBattleshipPlacement will generate a random coordinate along with a boolean indicating if the ship is to be
    placed horizontally. It will then check if the squares horizontally or vertically are unoccupied.
    this will take into account the length of the ship and starting position to check how many squares will be needed
    */
    public void randomBattleshipPlacement(Battleship shipToAdd){
        boolean canShipFit;
        int shipSize = shipToAdd.getSizeOfShip();
        Random randomVal = new Random();
        //starts with the assumption that the ship can be fit. If it cannot be fit then the boolean will be set to
        //false as soon as it is known
        canShipFit = true;
        //random generation of x, y values that fit within the boards 10x10 dimensions and a boolean value
        //that dictates if the ship will be placed horizontally or not
        int intX = randomVal.nextInt(boardSpace.length);
        int intY = randomVal.nextInt(boardSpace.length);
        boolean isHorizontal = randomVal.nextBoolean();

        /*
        the addingForwards boolean decides if the ship will be using the squares infront of it with regard to array index
        e.g., if placing horizontal ship starting from (x, y) then it will add the rest of the ship in the direction (x + 1, y),
        (x + 2, y) until desired length.
        for vertical, it will be added like (x, y), (x, y + 1), (x, y + 2) until desired length.
        if the boolean is false then it will add backwards e.g., (x, y), (x - 1, y) or (x, y) (x, y - 1) based on isHorizontal boolean
         */
        boolean addingForwards = true;
        //checks if the randomly selected square is occupied
        if(!boardSpace[intY][intX].getIsOccupied()){
            //checks if the isHorizontal boolean is true to indicate the ship being placed horizontally
            if(isHorizontal){
                //checks if there is enough space on the right of the randomly selected square to fit the ship
                if(intX <= boardSpace.length - shipSize){
                    /*
                    checks if the squares to the right of the randomly selected square are occupied.
                    This for loop will only check enough squares to fit the ship and no more as it is not necessary
                    e.g. a ship with size two only needs one square to the right checked to fit it
                    if the square being checked is occupied then canShipFit is false and the loop ends
                    */
                    for(int i = 0; i < shipSize; i++){
                        if(boardSpace[intY][intX + i].getIsOccupied()){
                            canShipFit = false;
                            break;
                        }
                    }
                    /*
                    once the nested for loop ends(either via break or running the whole loop) if canShipFit is
                    true then the ship is added to the board
                    as the squares to the right of the randomly selected squares are not occupied the ship is added forwards
                    (x + 1) so addingForwards is true
                    */
                    if(canShipFit){
                        addNewBattleship(intX, intY, shipSize, isHorizontal, addingForwards, shipToAdd);
                    }
                }
                //checks if there is enough space on the left of the randomly selected square to add the ship if
                //there is not enough space on the right of the randomly selected square to physically fit the ship
                else if(intX <= boardSpace.length){
                    //this uses the same logic as checking if the squares on the right of the randomly selected
                    //square are occupied but this time it will check the squares on the left of the randomly selected square
                    for(int i = 0; i < shipSize; i++){
                        if(boardSpace[intY][intX - i].getIsOccupied()){
                            canShipFit = false;
                            break;
                        }
                    }
                    //once the nested for loop ends(either via break or running the whole loop) if canShipFit is true then the ship is added to the board
                    if(canShipFit){
                        //as the ship can fit using the squares to the left of it (x - 1 index) the addingForwards
                        //boolean is false as we are adding backwards with respect to array index
                        addingForwards = false;
                        addNewBattleship(intX, intY, shipSize, isHorizontal, addingForwards, shipToAdd);
                    }
                }
            }
            /*
            the same logic as above if statements but for if it is not horizontal (vertical). The only difference is instead of
            checking the squares on the sides of the randomly selected square the loop checks the squares above
            and below
            */
            else if(!isHorizontal){
                //checks if there is enough space below the randomly selected square to fit the ship
                if(intY <= boardSpace.length - shipSize){
                    for(int i = 0; i < shipSize; i++){
                        //checks if squares below ship are occupied
                        if(boardSpace[intY + i][intX].getIsOccupied()){
                            canShipFit = false;
                            break;
                        }
                    }
                    if(canShipFit){
                        addNewBattleship(intX, intY, shipSize, isHorizontal, addingForwards, shipToAdd);
                    }
                }
                //this will check if the squares above the randomly selected square are occupied if there
                //is not enough space below the ship to physically fit it
                else if(intY <= boardSpace.length){
                    for(int i = 0; i < shipSize; i++){
                        if(boardSpace[intY - i][intX].getIsOccupied()){
                            canShipFit = false;
                            break;
                        }
                    }
                    if(canShipFit){
                        /*
                        the addingForwards boolean is false as we are able to use the squares above the
                        randomly selected square (y - 1) which is adding backwards with
                        respect to array index
                        */
                        addingForwards = false;
                        addNewBattleship(intX, intY, shipSize, isHorizontal, addingForwards, shipToAdd);
                    }
                }
            }
            //if the ship cannot be placed with using the randomly selected square then the method is called again to
            //generate a new coordinate and isHorizontal boolean for the ship
            if(!canShipFit){
                randomBattleshipPlacement(shipToAdd);
            }
        }
        //if the randomly selected square is already occupied then this method is called again for the ship object to
        //attempt to generate a new coordinate and isHorizontal boolean that will allow placement of ship
        else{
            randomBattleshipPlacement(shipToAdd);
        }

    }

    //addNewBattleship takes in the starting square (based on x, y coordinate), if it is being placed horizontal or not,
    //if the direction of square being added is forwards or backwards and the ship to add.
    public void addNewBattleship(int startX, int startY, int shipSize, boolean isHorizontal, boolean addingForwards, Battleship shipToAdd){

        //loops the coordinates with respect to if it is being placed horizontally, or not to add the ship. This will also factor in if
        //addingForwards is true or not while adding the desired ship type and setting the squares isOccupied boolean to true
        if(addingForwards) {
            for(int i = 0; i < shipSize; i++){
                if (isHorizontal) {
                    boardSpace[startY][startX + i].setOccupyingShip(shipToAdd);
                    boardSpace[startY][startX + i].setIsOccupied(true);
                }
                else if(!isHorizontal){
                    boardSpace[startY + i][startX].setOccupyingShip(shipToAdd);
                    boardSpace[startY + i][startX].setIsOccupied(true);
                }
            }
        }
        else{
            for(int i = 0; i < shipSize; i++){
                if(isHorizontal){
                    boardSpace[startY][startX - i].setOccupyingShip(shipToAdd);
                    boardSpace[startY][startX - i].setIsOccupied(true);
                }
                else if(!isHorizontal){
                    boardSpace[startY - i][startX].setOccupyingShip(shipToAdd);
                    boardSpace[startY - i][startX].setIsOccupied(true);
                }
            }
        }
        //the total number of ships on this board is now incremented by one as a ship has been added
        this.numberOfShipsRemaining++;
    }

    public String toString(){
        //the initial String has 2 spaces for formatting reasons to properly align the top row with the array being printed
        String currentBoardState = "  ";
        //populates first row of the print function to display the x values that can be selected by the player
        for(int i = 0; i < boardSpace.length; i++){
            currentBoardState += String.format(" %d ", i);
        }
        currentBoardState += "\n";

        //this for loop will go through each column of the grid
        for(int j = 0; j < boardSpace.length; j++){
            //populates first column of the print function to display the y values that can be selected by the player
            currentBoardState += String.format("%d ", j);
            //this for loop will print all squares of a row out
            for(int k = 0; k < boardSpace.length; k++){
                //prints the square
                currentBoardState += String.format(" %s ", boardSpace[j][k]);
            }
            currentBoardState += "\n";
        }
        return currentBoardState;
    }
    //SETTERS AND GETTERS
    public void setBoardSpace(Square[][] boardSpace){
        this.boardSpace = boardSpace;
    }

    public void setNumberOfShipsRemaining(int numberOfShipsRemaining){
        this.numberOfShipsRemaining = numberOfShipsRemaining;
    }

    public Square[][] getBoardSpace(){
        return this.boardSpace;
    }

    public int getNumberOfShipsRemaining(){
        return numberOfShipsRemaining;
    }
}
