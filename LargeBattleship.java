public class LargeBattleship extends Battleship{
    private static int shipLimit = 1;
    public LargeBattleship(){
        super(3, 3);
    }

    //SETTERS AND GETTERS
    public static void setShipLimit(int newShipLimit){
        shipLimit = newShipLimit;
    }

    public static int getShipLimit(){
        return shipLimit;
    }
}
