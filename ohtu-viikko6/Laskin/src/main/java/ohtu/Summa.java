package ohtu;

import javax.swing.JTextField;


public class Summa implements Komento {

    private final Sovelluslogiikka sovellus;
    private final JTextField tuloskentta;
    private final JTextField syotekentta;
    
    public Summa(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta) {
        this.sovellus = sovellus;
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
    }

    @Override
    public void suorita() {
        int edellinen = Integer.parseInt(tuloskentta.getText());
        int syote = Integer.parseInt(syotekentta.getText());
        sovellus.setEdellinen(edellinen);
        sovellus.setTulos(edellinen + syote);
        tuloskentta.setText("" + sovellus.getTulos());   
        syotekentta.setText("");
    }
    
}
