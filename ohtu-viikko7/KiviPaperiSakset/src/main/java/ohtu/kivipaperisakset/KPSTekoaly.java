package ohtu.kivipaperisakset;

public class KPSTekoaly extends KPS{
    
    Tekoaly tekoaly;

    public KPSTekoaly(IO io, boolean vaikea) {
        super(io);
        if(vaikea) {
            this.tekoaly = new TekoalyParannettu(20);
        } else {
           this.tekoaly = new Tekoaly(); 
        }
    }

    @Override
    protected void pelaa() {

        String ekanSiirto = super.ekanPelaajanSiirto();
        String tokanSiirto = tekoalynSiirto(ekanSiirto);

        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
            tarkistaTilanne(ekanSiirto, tokanSiirto);

            ekanSiirto = super.ekanPelaajanSiirto();
            tokanSiirto = tekoalynSiirto(ekanSiirto);
        }
        heippaJaKiitos(); 
    }
    
    private String tekoalynSiirto(String pelaajanSiirto) {
        String tekoalynSiirto = tekoaly.annaSiirto();
        io.print("Tietokone valitsi: " + tekoalynSiirto);
        tekoaly.asetaSiirto(pelaajanSiirto);
        return tekoalynSiirto;
    }

}