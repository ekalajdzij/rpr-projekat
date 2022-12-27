package ba.unsa.etf.rpr.exceptions;

public class KarteException extends Exception{
    public KarteException(String msg, Exception reason) {
        super(msg,reason);
    }
    public KarteException(String msg) {
        super(msg);
    }
}
