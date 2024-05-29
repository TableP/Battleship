public class SmallBattleship extends Battleship{
    private static int shipLimit = 3;
    public SmallBattleship(){
        super(1, 1);
    }

    //SETTERS AND GETTERS
    public static void setShipLimit(int newShipLimit){
        shipLimit = newShipLimit;
    }

    public static int getShipLimit(){
        return shipLimit;
    }
}
