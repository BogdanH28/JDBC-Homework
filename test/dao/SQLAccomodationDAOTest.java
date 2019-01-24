package dao;

import db.TestBookingsDB;
import org.junit.*;
import siit.dao.sql.SQLAccomodationDAO;
import siit.db.BookingsDbException;
import siit.model.Accomodation;

import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class SQLAccomodationDAOTest {

    private TestBookingsDB db;
    private SQLAccomodationDAO accomodationDAO;

    @BeforeClass
    public static void initTests() throws BookingsDbException, SQLException {
        TestBookingsDB.setUpTestDB();
    }

    @AfterClass
    public static void discardTests() throws BookingsDbException, SQLException {
        TestBookingsDB.dropTestDB();
    }

    @Before
    public void setUp() {
        db = new TestBookingsDB();
        accomodationDAO = new SQLAccomodationDAO(db);
    }

    @After
    public void tearDown() throws BookingsDbException, SQLException {
        db.dropDataFromTables();
    }

    @Test
    public void whenNewTypeInsertedIntoDB_getReturnsThem() throws BookingsDbException, Exception {
        Accomodation acc1 = new Accomodation();
        Accomodation acc2 = new Accomodation();

        acc1.setType("Motel");
        acc2.setType("Hotel");

        accomodationDAO.add(acc1);
        accomodationDAO.add(acc2);

        List<Accomodation> all = accomodationDAO.getAll();

        assertThat(all, notNullValue());
        assertThat(all.size(), is(2));
        assertThat(all.get(0).getType(), is("Motel"));
        assertThat(all.get(1).getType(), is("Hotel"));
    }

    public void whenRelationAreInsertedIntoDB_listAllPrintsThem(){

    }
}
