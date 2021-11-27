package Model;

public class DoubleRoom extends Room{
    public DoubleRoom(String hotedId){
        super(hotedId);
        this.type = "Double";
        this.minOccupancy = 1;
        this.maxOccupancy = 2;
    }

    @Override
    public bookRoom(String userId, long checkInTime, long checkOutTime){
   
    }
    
}