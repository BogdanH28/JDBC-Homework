package siit.dao;

import siit.db.BookingsDbException;
import siit.model.AccomodationRoomRelation;

import java.sql.SQLException;
import java.util.List;

public interface AccomodationRoomsRelationDAO {

    List<AccomodationRoomRelation> getAll() throws Exception, BookingsDbException;

    void add(AccomodationRoomRelation relation) throws BookingsDbException, SQLException;
}
