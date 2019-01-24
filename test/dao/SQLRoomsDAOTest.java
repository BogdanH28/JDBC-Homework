package dao;

import db.TestBookingsDB;
import org.junit.*;
import siit.dao.sql.SQLRoomsDAO;
import siit.db.BookingsDbException;
import siit.model.RoomFair;

import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;


public class SQLRoomsDAOTest {

    private TestBookingsDB db;
    private SQLRoomsDAO roomsDAO;

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
        roomsDAO = new SQLRoomsDAO(db);
    }

    @After
    public void tearDown() throws BookingsDbException, SQLException {
        db.dropDataFromTables();
    }

    @Test
    public void whenNewSeasonsInsertedIntoDB_getReturnsThem() throws BookingsDbException, Exception {
        RoomFair roomFair1 = new RoomFair();
        roomFair1.setSeason("spring");
//        roomFair1.setValue(300d);

        RoomFair roomFair2 = new RoomFair();
        roomFair2.setSeason("autumn");
//        roomFair2.setValue(500d);

        roomsDAO.add(roomFair1);
        roomsDAO.add(roomFair2);

        List<RoomFair> all = roomsDAO.getAll();

        assertThat(all, notNullValue());
        assertThat(all.size(), is(2));
        assertThat(all.get(0).getSeason(), is("spring"));
        assertThat(all.get(1).getSeason(), is("autumn"));
    }
}
