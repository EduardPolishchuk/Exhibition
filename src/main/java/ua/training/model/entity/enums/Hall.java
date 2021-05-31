package ua.training.model.entity.enums;

public enum Hall {
    RED( 120),
    BLUE( 80),
    GREEN( 50);

    public int getPlaces() {
        return places;
    }

    private final int places;

       Hall(int places){
        this.places = places;
    }

}
