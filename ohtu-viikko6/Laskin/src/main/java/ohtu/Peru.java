package ohtu;

import javax.swing.JTextField;

public class Peru implements Komento {
    private final Sovelluslogiikka sovellus;
    private final JTextField tuloskentta;
    private final JTextField syotekentta;

    
    public Peru(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta) {
        this.sovellus = sovellus;
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
    }

    @Override
    public void suorita() {
        sovellus.setTulos(sovellus.getEdellinen());
        tuloskentta.setText("" + sovellus.getTulos());   
        syotekentta.setText("");
    }
    
}