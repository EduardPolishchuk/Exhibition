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
}
