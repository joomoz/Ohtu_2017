package ohtu;

import javax.swing.JTextField;

public class Nollaa implements Komento {
    private final Sovelluslogiikka sovellus;
    private final JTextField tuloskentta;
    private final JTextField syotekentta;

    public Nollaa(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta) {
        this.sovellus = sovellus;
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
    }

    @Override
    public void suorita() {
        sovellus.setTulos(0);
        tuloskentta.setText("");
        syotekentta.setText("");
    }
    
}
