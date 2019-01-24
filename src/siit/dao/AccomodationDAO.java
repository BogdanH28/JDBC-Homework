package siit.dao;

import siit.db.BookingsDbException;
import siit.model.Accomodation;

import java.sql.SQLException;
import java.util.List;

public interface AccomodationDAO {


    List<Accomodation> getAll() throws Exception, BookingsDbException;

    void delete(Accomodation accomodation) throws BookingsDbException, SQLException;

    void add(Accomodation accomodation) throws BookingsDbException, SQLException;

    void update(Accomodation accomodation) throws BookingsDbException, SQLException;
}
