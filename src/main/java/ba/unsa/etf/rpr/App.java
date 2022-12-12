package ba.unsa.etf.rpr;

public class App 
{
    public static void main( String[] args )
    {
        Prodavac p = new Prodavac(2, "Emir", "062336150", "ekalajdzij1@etf.unsa.ba");
        System.out.println(p);
        Karte k = new Karte(1, "utakmica", "12.02.2008", "London, Wembley Stadium", p, 200.50);
        System.out.println(k);

    }
}
