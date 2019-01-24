package dao;

import db.TestBookingsDB;
import org.junit.*;
import siit.dao.sql.SQLAccomodationDAO;
import siit.dao.sql.SQLAccomodationRoomRelationDAO;
import siit.dao.sql.SQLRoomsDAO;
import siit.db.BookingsDbException;
import siit.model.Accomodation;
import siit.model.AccomodationRoomRelation;
import siit.model.RoomFair;

import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class SQLAccomodationRoomsDAOTest {

    private TestBookingsDB db;

    private SQLAccomodationRoomRelationDAO accomodationRoomRelationDAO;
    private AccomodationRoomRelation relation1;
    private AccomodationRoomRelation relation2;

    private SQLAccomodationDAO accomodationDAO;
    private Accomodation acc1;
    private Accomodation acc2;

    private SQLRoomsDAO roomsDAO;
    private RoomFair roomFair1;
    private RoomFair roomFair2;

    @BeforeClass
    public static void initTests() throws BookingsDbException, SQLException {
        TestBookingsDB.setUpTestDB();
    }

    @AfterClass
    public static void discardTests() throws BookingsDbException, SQLException {
        TestBookingsDB.dropTestDB();
    }

    @Before
    public void setUp() throws BookingsDbException, SQLException{
        db = new TestBookingsDB();
        accomodationRoomRelationDAO = new SQLAccomodationRoomRelationDAO(db);

        acc1 = new Accomodation();
        acc2 = new Accomodation();

        acc1.setType("Motel");
        acc1.setBed_type("Single");
        acc1.setMax_guests(10);
        acc1.setBed_type("Small");

        acc2.setType("Hotel");
        acc2.setBed_type("KingSize");
        acc2.setMax_guests(20);
        acc2.setDescription("Bigger");

        accomodationDAO = new SQLAccomodationDAO(db);
        accomodationDAO.add(acc1);
        accomodationDAO.add(acc2);

        roomFair1 = new RoomFair();
        roomFair2 = new RoomFair();

        roomFair1.setSeason("spring");
        roomFair2.setSeason("autumn");

        roomsDAO = new SQLRoomsDAO(db);
        roomsDAO.add(roomFair1);
        roomsDAO.add(roomFair2);

        relation1 = new AccomodationRoomRelation();
        relation2 = new AccomodationRoomRelation();

        relation1.setAccommodationId(acc1.getId());
        relation1.setRoomFairId(roomFair1.getId());
        relation2.setAccommodationId(acc2.getId());
        relation2.setRoomFairId(roomFair2.getId());

        accomodationRoomRelationDAO = new SQLAccomodationRoomRelationDAO(db);
        accomodationRoomRelationDAO.add(relation1);
        accomodationRoomRelationDAO.add(relation2);
    }

    @After
    public void tearDown() throws BookingsDbException, SQLException {
        db.dropDataFromTables();
    }

    @Test
    public void whenNewAccomodationRoomRelationsInsertedIntoDB_getReturnsThem() throws BookingsDbException, Exception {

        List<AccomodationRoomRelation> all = accomodationRoomRelationDAO.getAll();

        assertThat(all, notNullValue());
        assertThat(all.size(), is(2));
        assertThat(new AccomodationRoomRelation[]{relation1, relation2}, is(all.toArray()));
    }
}
