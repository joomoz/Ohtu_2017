package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;


public class KauppaTest {
    
    Varasto varasto;
    Pankki pankki;
    Viitegeneraattori viite;
    
    // luodaan mock-oliot
    @Before
    public void setUp() {
        varasto = mock(Varasto.class);
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);   
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), anyInt());
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    
    //aloitetaan asiointi, koriin lisätään tuote, jota varastossa on ja suoritetaan ostos, eli kutsutaan metodia kaupan tilimaksu(). 
    //varmistettava että kutsutaan pankin metodia tilisiirto oikealla asiakkaalla, tilinumerolla ja summalla
    //tämä siis on muuten copypaste esimerkistä, mutta verify:ssä on tarkastettava että parametreilla on oikeat arvot
    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaArvoilla() {
        when(viite.uusi()).thenReturn(123);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 10));

        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(123), eq("12345"), eq("33333-44455"), eq(10));        
    }
    
    //aloitetaan asiointi, koriin lisätään kaksi eri tuotetta, joita varastossa on ja suoritetaan ostos. 
    //varmistettava että kutsutaan pankin metodia tilisiirto oikealla asiakkaalla, tilinumerolla ja summalla
    @Test
    public void ostetaanKaksiTuotettaJaTilisiirronTiedotOnOikein() {
        when(viite.uusi()).thenReturn(123);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(5);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 10));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "olut", 20));

        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2); 
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(123), eq("12345"), eq("33333-44455"), eq(30));        
    }
    
    //aloitetaan asiointi, koriin lisätään kaksi samaa tuotetta jota on varastossa tarpeeksi ja suoritetaan ostos. 
    //varmistettava että kutsutaan pankin metodia tilisiirto oikealla asiakkaalla, tilinumerolla ja summalla
    @Test
    public void ostetaanKaksiSamaaTuotettaJaTilisiirronTiedotOnOikein() {
        when(viite.uusi()).thenReturn(123);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(1)).thenReturn(9);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 10));

        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1); 
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(123), eq("12345"), eq("33333-44455"), eq(20));        
    }
    
    //aloitetaan asiointi, koriin lisätään tuote jota on varastossa tarpeeksi ja tuote joka on loppu ja suoritetaan ostos. 
    //varmistettava että kutsutaan pankin metodia tilisiirto oikealla asiakkaalla, tilinumerolla ja summalla
    @Test
    public void koitetaanOstaaKahtaEriTuotettaMuttaToinenOnLoppu() {
        when(viite.uusi()).thenReturn(123);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(10)).thenReturn(0);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 10));
        when(varasto.haeTuote(10)).thenReturn(new Tuote(10, "La Ferrari", 2000000));

        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(10); 
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(123), eq("12345"), eq("33333-44455"), eq(10));        
    }
    
    //varmistettava, että metodin aloitaAsiointi kutsuminen nollaa edellisen ostoksen tiedot 
    //(eli edellisen ostoksen hinta ei näy uuden ostoksen hinnassa), katso tarvittaessa apua projektin MockitoDemo testeistä!
    @Test
    public void aloitaAsiointiNollaaEdellisenOstoksenTiedot() {
        when(viite.uusi()).thenReturn(123);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(6)).thenReturn(100);
        when(varasto.saldo(9)).thenReturn(8);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 10));
        when(varasto.haeTuote(6)).thenReturn(new Tuote(6, "leipä", 5));
        when(varasto.haeTuote(9)).thenReturn(new Tuote(9, "nakki", 3));

        Kauppa k = new Kauppa(varasto, pankki, viite);

        //Ensimmäinen asikas
        k.aloitaAsiointi();
        k.lisaaKoriin(1); 
        k.tilimaksu("pekka", "12345");
        verify(pankki).tilisiirto(eq("pekka"), eq(123), eq("12345"), eq("33333-44455"), eq(10));
                
        //Toinen asikas
        k.aloitaAsiointi();
        k.lisaaKoriin(6); 
        k.lisaaKoriin(9); 
        k.tilimaksu("joonas", "54321");
        verify(pankki).tilisiirto(eq("joonas"), eq(123), eq("54321"), eq("33333-44455"), eq(8));
    }
    
    //varmistettava, että kauppa pyytää uuden viitenumeron jokaiselle maksutapahtumalle, 
    //katso tarvittaessa apua projektin MockitoDemo testeistä!
    @Test
    public void uusiViitenumeroJokaiselleMaksulle() {
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 10));
        
        Kauppa k = new Kauppa(varasto, pankki, viite);
        
        //Ensimmäinen asikas
        k.aloitaAsiointi();
        k.lisaaKoriin(1); 
        k.tilimaksu("pekka", "12345");
        // tarkistetaan että tässä vaiheessa viitegeneraattorin metodia seuraava()
        // on kutsuttu kerran
        verify(viite, times(1)).uusi();
                
        //Toinen asikas
        k.aloitaAsiointi();
        k.lisaaKoriin(1); 
        k.tilimaksu("jns", "54321");
        // tarkistetaan että tässä vaiheessa viitegeneraattorin metodia seuraava()
        // on kutsuttu kerran
        verify(viite, times(2)).uusi();
        
        //Kolmas asikas
        k.aloitaAsiointi();
        k.lisaaKoriin(1); 
        k.tilimaksu("joku", "11155");
        // tarkistetaan että tässä vaiheessa viitegeneraattorin metodia seuraava()
        // on kutsuttu kerran
        verify(viite, times(3)).uusi();
    }
    
    @Test
    public void testaaTuotteenPoistoaOstokoristaJaPalauttamistaVarastoon() {
        Tuote maito = new Tuote(1, "maito", 10);
        when(viite.uusi()).thenReturn(123);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(maito);
        Kauppa k = new Kauppa(varasto, pankki, viite);

        //Ensimmäinen asikas
        k.aloitaAsiointi();
        k.lisaaKoriin(1); 
        k.poistaKorista(1);
        k.tilimaksu("pekka", "12345");
        verify(varasto, times(1)).palautaVarastoon(maito);
    }
}
