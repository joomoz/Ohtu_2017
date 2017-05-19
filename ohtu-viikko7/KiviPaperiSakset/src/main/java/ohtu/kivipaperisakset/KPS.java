package ohtu.kivipaperisakset;

import java.util.Scanner;

// Kivi-Paperi-Sakset peli
public abstract class KPS {

    private static final Scanner scanner = new Scanner(System.in);

    protected IO io;
    protected Tuomari tuomari = new Tuomari();
    
    protected KPS(IO io) {
        this.io = io;
        this.tuomari = new Tuomari();
    }
    
    //Tehdasmetodit eri pelimuotojen luontiin
    public static KPS kaksinpeli(IO io) {
        return new KPSPelaajaVsPelaaja(io);
    }
    
    public static KPS helppoYksinpeli(IO io) {
        return new KPSTekoaly(io, false);
    }
    
    public static KPS vaikeaYksinpeli(IO io) {
        return new KPSTekoaly(io, true);
    }
    
    //template-metodi, toteutus eri pelimuotojen luokissa
    protected abstract void pelaa();

    protected String ekanPelaajanSiirto() {
        io.print("Ensimmäisen pelaajan siirto: ");
        return io.readLine();
    }
    
    protected String tokanPelaajanSiirto() {
        io.print("Toisen pelaajan siirto: ");
        return io.readLine();
    }
    
    protected void tarkistaTilanne(String ekanSiirto, String tokanSiirto) {
        tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
        io.print("" + tuomari);
    }
    
    protected void heippaJaKiitos() {
        io.print("\n Kiitos! \n" + tuomari);
    }
    
    protected boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }
}

