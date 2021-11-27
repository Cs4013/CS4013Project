package Model;

public class QuadRoom extends Room{
    public QuadRoom(String hotedId){
        super(hotedId);
        this.type = "Quad";
        this.minOccupancy = 1;
        this.maxOccupancy = 4;
    }

    @Override
    public bookRoom(String userId, long checkInTime, long checkOutTime){
      
    }
    
}