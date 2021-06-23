package ua.training.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@ToString
@Builder
public class Exhibition {
    private String theme;
    private String themeUk;
    private String description;
    private String descriptionUk;
    private String imageUrl;
    private BigDecimal price;
    private Set<Hall> halls;
    private LocalDate date;
    private int id;
    private int currentPlaces;
    private int maxPlaces;
    private boolean isCanceled;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getThemeUk() {
        return themeUk;
    }

    public void setThemeUk(String themeUk) {
        this.themeUk = themeUk;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionUk() {
        return descriptionUk;
    }

    public void setDescriptionUk(String descriptionUk) {
        this.descriptionUk = descriptionUk;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public int getCurrentPlaces() {
        return currentPlaces;
    }

    public void setCurrentPlaces(int currentPlaces) {
        this.currentPlaces = currentPlaces;
    }

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
