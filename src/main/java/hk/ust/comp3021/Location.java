package hk.ust.comp3021;

public class Location {
    private Double latitude;
    private Double altitude;
    public Double distanceTo(Location other){
        //Get the distance between two location
        return Math.sqrt(Math.pow(this.altitude- other.altitude,2)+Math.pow(this.latitude- other.latitude,2));
    }
}
