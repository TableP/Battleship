public class Square {
    private int rowPos;
    private int colPos;
    //this boolean indicates if there is a Battleship object or part of a Battleship object within the square
    private boolean isOccupied = false;
    private Battleship occupyingShip;
    //this boolean indicates whether the square has been hit by a player or not during the battleships game
    private boolean isHit = false;


    //constructor assigns the square object its row and column position
    public Square(int x, int y){
        this.rowPos = x;
        this.colPos = y;
    }

    //the toString method will return the visual representation of the square based on the squares current state
    //it will take into account if the square is hit and if the square was occupied
    public String toString(){
        //checks if square has been hit
        if(isHit){
            //checks if square is occupied
            if(isOccupied){
                return "o";
            }
            else{
                return "x";
            }
        }
        //if square has not been hit then it will return the default visual state of "-"
        return "-";
    }

    //SETTERS AND GETTERS
    public void setRowPos(int rowPos) {
        this.rowPos = rowPos;
    }

    public void setColPos(int colPos){
        this.colPos = colPos;
    }

    public void setIsOccupied(boolean occupied){
        this.isOccupied = occupied;
    }

    public void setOccupyingShip(Battleship occupyingShip){
        this.occupyingShip = occupyingShip;
    }

    public void setIsHit(boolean isHit){
        this.isHit = isHit;
    }



    public int getRowPos() {
        return this.rowPos;
    }

    public int getColPos(){
        return this.colPos;
    }

    public boolean getIsOccupied(){
        return this.isOccupied;
    }

    public Battleship getOccupyingShip(){
        return this.occupyingShip;
    }

    public boolean getIsHit(){
        return this.isHit;
    }


}
