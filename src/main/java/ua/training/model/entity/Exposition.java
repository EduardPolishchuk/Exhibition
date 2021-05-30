package ua.training.model.entity;

import ua.training.model.entity.enums.Hall;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Exposition {
    private String theme;
    private String description;
    private int price;
    private Set<Hall> halls;
    private LocalDate date;
    private int id;
    private int current;
    private int max;


    public int getMax() {
        return max;
    }

    private void setMax() {
        for (Hall hall : halls) {
            this.max += hall.getPlaces();
        }
    }


    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Set<Hall> getHalls() {
        return halls;
    }

    public void setHalls(Set<Hall> halls) {
        this.halls = halls;
        this.setMax();
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Exposition() {
    }

    public Exposition(String theme, int price, Set<Hall> halls, LocalDate date) {
        this.theme = theme;
        this.price = price;
        this.halls = halls;
        this.date = date;
        setMax();
    }

    @Override
    public String toString() {
        return "Exposition{" +
                "theme = '" + theme + '\'' +
                ", price = " + price +
                ", halls = " + halls +
                ", date = " + date +
                ", id = " + id +
                ", current = " + current +
                ", max = " + max +
                '}';
    }

    public static void main(String[] args) {
        Set<Hall> halls = new HashSet<>();
        halls.add(Hall.BLUE);
        halls.add(Hall.GREEN);
                LocalDate calendar = LocalDate.of(2020, 1, 25);
        Exposition exposition = new Exposition("Theme", 120, halls, calendar);
//        Exposition exposition = new Exposition();
//        exposition.setDate(calendar);
//        exposition.setHalls(halls);
//        Set<Hall> halls2 = exposition.getHalls();
//        exposition.setPrice(125);
//        exposition.setTheme("Theme");
        System.out.println(exposition);
    }
}
