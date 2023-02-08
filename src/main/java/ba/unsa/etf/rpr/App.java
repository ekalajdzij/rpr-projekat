package ba.unsa.etf.rpr;


import ba.unsa.etf.rpr.bussines.KarteManager;
import ba.unsa.etf.rpr.domain.Karte;
import ba.unsa.etf.rpr.exceptions.KarteException;
import org.apache.commons.cli.*;

import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;

public class App {
    private static final Option kupiKartu = new Option("k","kupi-kartu", false, "Kupovina nove karte iz baze podataka");
    private static final Option prodajKartu = new Option("p","prodaj-kartu", false,"Registrovanje karte za prodaju u bazu podataka");


    public static void printFormattedOptions(Options opcije) {
        HelpFormatter helpFormatter = new HelpFormatter();
        PrintWriter printWriter = new PrintWriter(System.out);
        helpFormatter.printUsage(printWriter, 150, "java -jar rpr-projekat-cli-jar-with-dependencies.jar [option] (parameters)" );
        helpFormatter.printOptions(printWriter, 150, opcije, 2, 7);
        printWriter.close();
    }

    public static Options addOptions() {
        Options options = new Options();
        options.addOption(kupiKartu);
        options.addOption(prodajKartu);
        return options;
    }

    public static Karte searchThroughKarte(List<Karte> lista, String k) {
        Karte karta = null;
        karta = lista.stream().filter(kar -> kar.getVrsta().toLowerCase().equals(k.toLowerCase())).findAny().get();
        return karta;
    }

    private static boolean isDigit(String string) {
        try {
            int intValue = Integer.parseInt(string);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) throws KarteException, ParseException {
        Options options = addOptions();
        CommandLineParser commandLineParser = new DefaultParser();
        CommandLine cl = commandLineParser.parse(options, args);

        if (cl.hasOption(kupiKartu.getOpt()) || cl.hasOption(kupiKartu.getLongOpt())) {
            KarteManager karteManager = new KarteManager();
            Karte karta = null;
            try {
                karta = searchThroughKarte(karteManager.getAll(), cl.getArgList().get(1));
                System.out.println("Uspjesno ste kupili kartu. Cijena karte je: "+ karta.getCijena());
            } catch (Exception e) {
                System.out.println("U bazi nema karte sa tim imenom! Pokusajte ponovo!");
                System.exit(1);
            }
        }
        else if (cl.hasOption(prodajKartu.getOpt()) || cl.hasOption(prodajKartu.getLongOpt())) {
            KarteManager karteManager = new KarteManager();
            Karte karta = new Karte();
            karta.setVrsta(cl.getArgList().get(0));
            karta.setDatum(cl.getArgList().get(1));
            karta.setAdresa(cl.getArgList().get(2));
            if (!isDigit(cl.getArgList().get(3))) {
                System.out.println("Morate unijeti cijenu karte!");
                return;
            }
            karta.setCijena(Double.valueOf(cl.getArgList().get(3)));
            karteManager.add(karta);
        }
        else {
            printFormattedOptions(options);
            System.exit(-1);
        }


    }


}
