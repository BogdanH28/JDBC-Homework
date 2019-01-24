package siit.model;

import java.util.Objects;

public class Accomodation {

    private int id;
    private String type;
    private String bed_type;
    private int max_guests;
    private String description;

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getBed_type() {
        return bed_type;
    }

    public int getMax_guests() {
        return max_guests;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBed_type(String bed_type) {
        this.bed_type = bed_type;
    }

    public void setMax_guests(int max_guests) {
        this.max_guests = max_guests;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Accomodation{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", bed_type='" + bed_type + '\'' +
                ", max_guests=" + max_guests +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Accomodation that = (Accomodation) o;
        return id == that.id &&
                max_guests == that.max_guests &&
                Objects.equals(type, that.type) &&
                Objects.equals(bed_type, that.bed_type) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, bed_type, max_guests, description);
    }
}
