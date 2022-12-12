package ba.unsa.etf.rpr;

public class App 
{
    public static void main( String[] args )
    {
        Prodavac p = new Prodavac(1, "Emir", "062336150", "ekalajdzij1@etf.unsa.ba");
        System.out.println(p);
        Karte k = new Karte(1, "utakmica", "12.02.2008", "London, Wembley Stadium", p, 200.50);
        System.out.println(k);



        Placanje payment = new Placanje(1, 200.5, "13.03.2007");
        System.out.println(payment);


    }
}
