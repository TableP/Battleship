public class MediumBattleship extends Battleship{
    private static int shipLimit = 2;
    public MediumBattleship(){
        super(2, 2);
    }

    //SETTERS AND GETTERS
    public static void setShipLimit(int newShipLimit){
        shipLimit = newShipLimit;
    }

    public static int getShipLimit(){
        return shipLimit;
    }
}
