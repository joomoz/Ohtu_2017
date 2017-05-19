package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;  // luotava uusi taulukko on näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] luvut;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        this(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0 || kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("annettu luku oli negatiivinen");
        }
        luvut = alustaLukujono(kapasiteetti);
        this.alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    private int[] alustaLukujono(int kapasiteetti) {
        luvut = new int[kapasiteetti];
        for (int i = 0; i < luvut.length; i++) {
            luvut[i] = 0;
        }
        return luvut;
    }

    public boolean lisaa(int luku) {
        if (kuuluu(luku)) {
            return false;
        }
        if (luvut.length == alkioidenLkm) {
            kasvataLuvutTaulukkoa();
        }
        luvut[alkioidenLkm] = luku;
        alkioidenLkm++;
        return true;
    }

    private void kasvataLuvutTaulukkoa() {
        int[] uusi = new int[alkioidenLkm + kasvatuskoko];
        System.arraycopy(luvut, 0, uusi, 0, luvut.length);
        luvut = uusi;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == luvut[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        if (!kuuluu(luku)) {
            return false;
        }
        int j = indeksi(luku);
        for (int i = j; i < alkioidenLkm - 1; i++) {
            luvut[i] = luvut[i + 1];
        }
        alkioidenLkm--;
        return true;
    }

    public int indeksi(int luku) {
        int j = 0;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == luvut[i]) {
                j = i;
            }  
        }
        return j;
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}"; 
        } else {
            String tuotos = "{" + luvut[0];
            for (int i = 1; i < alkioidenLkm; i++) {
                tuotos += ", " + luvut[i];
            }
            tuotos += "}";
            return tuotos;
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = luvut[i];
        }
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            x.lisaa(bTaulu[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    y.lisaa(bTaulu[j]);
                }
            }
        }
        return y;

    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            z.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            z.poista(i);
        }

        return z;
    }

}
