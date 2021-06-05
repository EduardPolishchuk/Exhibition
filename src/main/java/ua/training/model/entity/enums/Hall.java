package ua.training.model.entity.enums;

public enum Hall {
    RED(120, 1),
    BLUE(80,2),
    GREEN(50,3);

    private final int places;
    private final int id;


    public int getId() {
        return id;
    }

    public int getPlaces() {
        return places;
    }

       Hall(int places, int id){
        this.places = places;
        this.id = id;
    }

}
