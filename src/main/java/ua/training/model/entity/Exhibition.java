package ua.training.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;


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

    public int getMaxPlaces() {
        return maxPlaces;
    }

    public void setMaxPlaces(int maxPlaces) {
        this.maxPlaces = maxPlaces;
        if (halls != null) {
            this.updateMax();
        }
    }

    public boolean getIsCanceled() {
        return isCanceled;
    }

    public void setCanceled(boolean canceled) {
        this.isCanceled = canceled;
    }

    private void updateMax() {
        int hallsCapacity = 0;
        for (Hall hall : halls) {
            hallsCapacity += hall.getPlaces();
        }
        if (maxPlaces == 0 || maxPlaces > hallsCapacity) {
            this.maxPlaces = hallsCapacity;
        }
    }

    public void setHalls(Set<Hall> halls) {
        this.halls = halls;
        this.updateMax();
    }

    public Set<Hall> getHalls() {
        return halls;
    }

    public static ExhibitionBuilder builder() {
        return new ExhibitionBuilder();
    }

    @Override
    public String toString() {
        return "Exhibition{" +
                "theme=" + theme +
                ", themeUk=" + themeUk +
                ", description=" + description +
                ", descriptionUk=" + descriptionUk +
                ", imageUrl=" + imageUrl +
                ", price=" + price +
                ", halls=" + halls +
                ", date=" + date +
                ", id=" + id +
                ", currentPlaces=" + currentPlaces +
                ", maxPlaces=" + maxPlaces +
                ", isCanceled=" + isCanceled +
                '}';
    }

    public static class ExhibitionBuilder {
        private Exhibition newExhibition;

        public ExhibitionBuilder() {
            newExhibition = new Exhibition();
            newExhibition.isCanceled = false;
        }

        public ExhibitionBuilder theme(String theme) {
            newExhibition.theme = theme;
            return this;
        }

        public ExhibitionBuilder themeUk(String themeUk) {
            newExhibition.themeUk = themeUk;
            return this;
        }

        public ExhibitionBuilder description(String description) {
            newExhibition.description = description;
            return this;
        }

        public ExhibitionBuilder descriptionUk(String descriptionUk) {
            newExhibition.descriptionUk = descriptionUk;
            return this;
        }

        public ExhibitionBuilder date(LocalDate date) {
            newExhibition.date = date;
            return this;
        }

        public ExhibitionBuilder price(BigDecimal price) {
            newExhibition.price = price;
            return this;
        }

        public ExhibitionBuilder id(int id) {
            newExhibition.id = id;
            return this;
        }

        public ExhibitionBuilder halls(Set<Hall> halls) {
            newExhibition.setHalls(halls);
            return this;
        }

        public ExhibitionBuilder currentPlaces(int currentPlaces) {
            newExhibition.currentPlaces = currentPlaces;
            return this;
        }

        public ExhibitionBuilder maxPlaces(int maxPlaces) {
            newExhibition.setMaxPlaces(maxPlaces);
            return this;
        }

        public ExhibitionBuilder imageUrl(String imageUrl) {
            newExhibition.imageUrl = imageUrl;
            return this;
        }

        public ExhibitionBuilder isCanceled(boolean isCanceled) {
            newExhibition.isCanceled = isCanceled;
            return this;
        }

        public Exhibition build() {
            return newExhibition;
        }
    }
}
