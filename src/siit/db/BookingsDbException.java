package siit.db;

public class BookingsDbException extends Throwable{
    public BookingsDbException(String s, Exception e) {
        super(s, e);
    }

    public BookingsDbException(String s) {
        super(s);
    }
}
