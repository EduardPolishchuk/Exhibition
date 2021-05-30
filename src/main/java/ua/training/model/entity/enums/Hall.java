package ua.training.model.entity.enums;

public enum Hall {
    RED( 120),
    BLUE( 120),
    GREEN( 120);

    public int getPlaces() {
        return places;
    }

    private final int places;

       Hall(int places){
        this.places = places;
    }
}
