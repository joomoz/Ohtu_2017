package ohtu.kivipaperisakset;

public class KPSPeli {

    private static final IO io = new KonsoliIO();
    KPS peli;

    public void kaynnista() {
        while (true) {
            String vastaus = kysyPelimuoto();          
            if (vastaus.contains("a") || vastaus.contains("b") || vastaus.contains("c")) {
                io.print("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
                if (vastaus.contains("a")) {
                    peli = KPS.kaksinpeli(io);
                } else if (vastaus.contains("b")) {
                    peli = KPS.helppoYksinpeli(io);
                } else { // (vastaus.endsWith("c")) {
                    peli = KPS.vaikeaYksinpeli(io);
                }
                peli.pelaa();
            } else {
                break;
            }
        }
    }
    
    private String kysyPelimuoto() {
        io.print("\n Valitse pelataanko"
                + "\n (a) ihmistä vastaan "
                + "\n (b) tekoälyä vastaan"
                + "\n (c) parannettua tekoälyä vastaan"
                + "\n muilla valinnoilla lopetataan");

        return io.readLine();
    }
    
}
