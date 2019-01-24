package siit.dao;

import siit.db.BookingsDbException;
import siit.model.RoomFair;

import java.sql.SQLException;
import java.util.List;

public interface RoomsDAO {
    List<RoomFair> getAll() throws Exception, BookingsDbException;

    void add(RoomFair roomFair) throws BookingsDbException, SQLException;
}
