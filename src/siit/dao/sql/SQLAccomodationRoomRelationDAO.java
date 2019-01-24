package siit.dao.sql;

import siit.dao.AccomodationRoomsRelationDAO;
import siit.db.BookingDB;
import siit.db.BookingsDbException;
import siit.model.AccomodationRoomRelation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLAccomodationRoomRelationDAO implements AccomodationRoomsRelationDAO {

    private BookingDB db;

    public SQLAccomodationRoomRelationDAO(BookingDB db) {
        this.db = db;
    }

    @Override
    public List<AccomodationRoomRelation> getAll() throws Exception, BookingsDbException {
        try (Connection conn = db.connect()) {
            PreparedStatement selectPs = null;

            try {
                selectPs = conn.prepareStatement("SELECT * from accomodation_fair_relation;");
                ResultSet resultSet = selectPs.executeQuery();
                ArrayList<AccomodationRoomRelation> accomodationRoomRelations = new ArrayList<>();

                while (resultSet.next()) {
                    AccomodationRoomRelation accomodationRoomRelation = mapResultSetToClient(resultSet);
                    accomodationRoomRelations.add(accomodationRoomRelation);
                }
                return accomodationRoomRelations;
            } catch (SQLException e) {
                System.err.println("Cannot retrieve all Accommodation - Room Relations: " + e.getMessage());
            } finally {
                if (selectPs != null) {
                    try {
                        selectPs.close();
                    } catch (SQLException e) {
                        System.out.println("Prepared Statement could not be closed: " + e.getMessage());
                    }
                }
            }
        }
        return null;
    }

    private AccomodationRoomRelation mapResultSetToClient(ResultSet resultSet) throws SQLException {
        AccomodationRoomRelation accommodationRoomRelation = new AccomodationRoomRelation();
        accommodationRoomRelation.setId(resultSet.getInt("id"));
        accommodationRoomRelation.setAccommodationId(resultSet.getInt("id_accomodation"));
        accommodationRoomRelation.setRoomFairId(resultSet.getInt("id_room_fair"));
        return accommodationRoomRelation;
    }

    @Override
    public void add(AccomodationRoomRelation relation) throws BookingsDbException, SQLException {

        try (Connection connection = db.connect()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO  accomodation_fair_relation(id_accomodation, id_room_fair) values(" + relation.getAccommodationId() + ", '" + relation.getRoomFairId() + "')");
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT CURRVAL('accomodation_fair_relation_ids')");
            resultSet.next();
            relation.setId(resultSet.getInt(1));
        }
    }

    private void printEntry(ResultSet resultSet) throws Exception, BookingsDbException {
        System.out.println("Accommodation " +
                "id=" + resultSet.getInt(1) +
                ", type='" + resultSet.getString(2) + '\'' +
                ", bedType='" + resultSet.getString(3) + '\'' +
                ", maxGuests=" + resultSet.getString(4) +
                ", description='" + resultSet.getString(5) + '\'' +
                ", value=" + resultSet.getString(10) +
                ", season='" + resultSet.getString(11) + '\'');

    }
}
