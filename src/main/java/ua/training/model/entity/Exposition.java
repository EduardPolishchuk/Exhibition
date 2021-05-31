package ua.training.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.training.model.entity.enums.Hall;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@ToString
@Builder
public class Exposition {
    private String theme;
    private String description;
    private int price;
    private Set<Hall> halls;
    private LocalDate date;
    private int id;
    private int current;
    private int max;

    public void updateMax() {
        max = 0;
        for (Hall hall : halls) {
            max += hall.getPlaces();
        }
    }

    public void setHalls(Set<Hall> halls) {
        this.halls = halls;
        this.updateMax();
    }

//    public Exposition() {
//    }

//    public Exposition(String theme, int price, Set<Hall> halls, LocalDate date) {
//        this.theme = theme;
//        this.price = price;
//        this.halls = halls;
//        this.date = date;
//        setMax();
//    }





    public static void main(String[] args) {
//        Set<Hall> halls = new HashSet<>();
//        halls.add(Hall.BLUE);
//        halls.add(Hall.GREEN);
//                LocalDate calendar = LocalDate.of(2020, 1, 25);
//        Exposition exposition = new Exposition("Theme", 120, halls, calendar);
////        Exposition exposition = new Exposition();
////        exposition.setDate(calendar);
////        exposition.setHalls(halls);
////        Set<Hall> halls2 = exposition.getHalls();
////        exposition.setPrice(125);
////        exposition.setTheme("Theme");
//        System.out.println(exposition);
    }
}
