package siit.model;

import java.util.Objects;

public class RoomFair {

    private int id;
    private Double value;
    private String season;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    @Override
    public String toString() {
        return "RoomFair{" +
                "id=" + id +
                ", value=" + value +
                ", season='" + season + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomFair roomFair = (RoomFair) o;
        return id == roomFair.id &&
                Objects.equals(value, roomFair.value) &&
                Objects.equals(season, roomFair.season);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, season);
    }
}
