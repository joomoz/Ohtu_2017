package ohtu;

import static org.junit.Assert.*;
import org.junit.Test;

public class MultiplierTest {

    @Test
    public void kertominenToimii() {
        Multiplier kaksi = new Multiplier(2);

        assertEquals(2, kaksi.multipliedBy(1));
        assertEquals(0, kaksi.multipliedBy(0));
    }

}
