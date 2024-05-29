public class Battleship {
    private boolean isSunk = false;
    private int remainingHealthOfShip;
    private int sizeOfShip;

    public Battleship(){
        this.remainingHealthOfShip = 2;
        this.sizeOfShip = 2;
    }
    public Battleship(int remainingHealthOfShip, int sizeOfShip){
        this.remainingHealthOfShip = remainingHealthOfShip;
        this.sizeOfShip = sizeOfShip;
    }

    public String toString(){
        System.out.println(String.format("The size of ship is %s and the current hp of the ship is %s", this.sizeOfShip, this.remainingHealthOfShip));
        return null;
    }

    //when the ship is hit this method is called. If the hp reaches 0 then the isSunk boolean will equal true
    public void hitShip(){
        remainingHealthOfShip--;
        if(remainingHealthOfShip == 0){
            isSunk = true;
        }
    }

    //SETTERS AND GETTERS
    public void setIsSunk(boolean isSunk){
        this.isSunk = isSunk;
    }

    public void setRemainingHealthOfShip(int remainingHealthOfShip){
        this.remainingHealthOfShip = remainingHealthOfShip;
    }

    public void setSizeOfShip(int sizeOfShip){
        this.sizeOfShip = sizeOfShip;
    }

    public boolean getIsSunk(){
        return this.isSunk;
    }

    public int getRemainingHealthOfShip(){
        return this.remainingHealthOfShip;
    }

    public int getSizeOfShip(){
        return this.sizeOfShip;
    }


}
