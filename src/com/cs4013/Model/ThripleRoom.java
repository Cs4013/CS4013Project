package Model;

public class ThripleRoom extends Room{
    public ThripleRoom(String hotelId){
        super(hotelId);
        this.type = "Thriple";
        this.minOccupancy = 1;
        this.maxOccupancy = 3;
    }

    @Override
    public bookRoom(String userId, long checkInTime, long checkOutTime){
     
    }
    
}