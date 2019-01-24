package siit.model;

import java.util.Objects;

public class AccomodationRoomRelation {

    private int id;
    private int accommodationId;
    private int roomFairId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccommodationId() {
        return accommodationId;
    }

    public void setAccommodationId(int accommodationId) {
        this.accommodationId = accommodationId;
    }

    public int getRoomFairId() {
        return roomFairId;
    }

    public void setRoomFairId(int roomFairId) {
        this.roomFairId = roomFairId;
    }

    @Override
    public String toString() {
        return "AccomodationRoomRelation{" +
                "id=" + id +
                ", accommodationId=" + accommodationId +
                ", roomFairId=" + roomFairId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccomodationRoomRelation that = (AccomodationRoomRelation) o;
        return id == that.id &&
                accommodationId == that.accommodationId &&
                roomFairId == that.roomFairId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accommodationId, roomFairId);
    }
}
