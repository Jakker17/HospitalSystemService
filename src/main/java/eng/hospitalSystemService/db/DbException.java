package eng.hospitalSystemService.db;

public class DbException extends RuntimeException{
    public DbException (String message, Throwable cause){
        super(message,cause);
    }
}
