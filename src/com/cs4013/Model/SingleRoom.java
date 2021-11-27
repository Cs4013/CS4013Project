package Model;

public class SingleRoom extends Room{
    public SingleRoom(String hotedId){
        super(hotedId);
        this.type = "Single";
        this.minOccupancy = 1;
        this.maxOccupancy = 1;
    }

    @Override
    public bookRoom(String userId, long checkInTime, long checkOutTime){
        
    }

}