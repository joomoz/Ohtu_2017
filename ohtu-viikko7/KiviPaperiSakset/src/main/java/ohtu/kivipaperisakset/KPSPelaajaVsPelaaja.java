package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSPelaajaVsPelaaja extends KPS{

    public KPSPelaajaVsPelaaja(IO io) {
        super(io);
    }

    @Override
    protected void pelaa() {
        String ekanSiirto = super.ekanPelaajanSiirto();
        String tokanSiirto = super.tokanPelaajanSiirto();

        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
            tarkistaTilanne(ekanSiirto, tokanSiirto);

            ekanSiirto = super.ekanPelaajanSiirto();
            tokanSiirto = super.tokanPelaajanSiirto();
        }
        heippaJaKiitos();
    }
 
}