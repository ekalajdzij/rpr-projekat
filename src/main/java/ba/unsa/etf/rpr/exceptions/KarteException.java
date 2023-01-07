package ba.unsa.etf.rpr.exceptions;

public class KarteException extends Exception{
    public KarteException(String poruka, Exception razlog) {
        super(poruka,razlog;
    }
    public KarteException(String poruka) {
        super(poruka);
    }
}
