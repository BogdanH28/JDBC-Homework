package siit.dao.sql;

import siit.dao.RoomsDAO;
import siit.db.BookingDB;
import siit.db.BookingsDbException;
import siit.model.RoomFair;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLRoomsDAO implements RoomsDAO {

    private BookingDB db;

    public SQLRoomsDAO(BookingDB db) {
        this.db = db;
    }

    @Override
    public List<RoomFair> getAll() throws Exception, BookingsDbException {
        try (Connection conn = db.connect()) {
/*            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from room_fair;");
            ArrayList<RoomFair> roomFairs = new ArrayList<>();*/

            PreparedStatement selectPs = null;

            try {
                selectPs = conn.prepareStatement("SELECT * from room_fair;");
                ResultSet resultSet = selectPs.executeQuery();
                ArrayList<RoomFair> roomFairs = new ArrayList<>();

                while (resultSet.next()) {
                    RoomFair roomFair = mapResultSetToClient(resultSet);
                    roomFairs.add(roomFair);
                }
                return roomFairs;
            }catch (SQLException e) {
                System.err.println("Cannot retrieve all Room Fairs: " + e.getMessage());
            } finally {
                if (selectPs != null) {
                    try {
                        selectPs.close();
                    } catch (SQLException e) {
                        System.out.println("Prepared Statement could not be closed: " + e.getMessage());
                    }
                }
            }
            return null;
        }
    }

    private RoomFair mapResultSetToClient(ResultSet resultSet) throws SQLException {
        RoomFair roomFair = new RoomFair();
        roomFair.setId(resultSet.getInt("id"));
        roomFair.setValue(resultSet.getDouble("value"));
        roomFair.setSeason(resultSet.getString("season"));
        return roomFair;
    }

    @Override
    public void add(RoomFair roomFair) throws BookingsDbException, SQLException {

        try (Connection connection = db.connect()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO room_fair(value, season) values(" + roomFair.getValue() + ", '" + roomFair.getSeason() + "')");
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT CURRVAL('room_fair_ids')");
            resultSet.next();
            roomFair.setId(resultSet.getInt(1));
        }
    }

}
