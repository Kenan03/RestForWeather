package models;

public class Weather {
    public int city_id;
    public int temperature;
    public int feels_like;
    public String main;
    public String description;

    public Weather(int city_id, int temperature, int feels_like, String main, String description) {
        this.city_id = city_id;
        this.temperature = temperature;
        this.feels_like = feels_like;
        this.main = main;
        this.description = description;
    }


    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getFeels_like() {
        return feels_like;
    }

    public void setFeels_like(int feels_like) {
        this.feels_like = feels_like;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "city_id=" + city_id +
                ", temperature=" + temperature +
                ", feels_like=" + feels_like +
                ", main='" + main + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
