package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriEiLuoNegatiivistaVarastoa() {
        Varasto varasto = new Varasto(-5);

        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void monimutkaisempiKonstruktoriTest1() {

        Varasto varasto = new Varasto(0, 5);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void monimutkaisempiKonstruktoriTest2() {

        Varasto varasto = new Varasto(5, 0);
        assertEquals(5, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void monimutkaisempiKonstruktoriTest3() {

        Varasto varasto = new Varasto(-5, -5);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void monimutkaisempiKonstruktoriTest4() {

        Varasto varasto = new Varasto(10, 15);
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void monimutkaisempiKonstruktoriTest5() {

        Varasto varasto = new Varasto(10, 5);
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisataanLiikaaSaldoa() {

        varasto.lisaaVarastoon(100);

        assertEquals(varasto.getTilavuus(), varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisataanInvalidiMäärä() {

        varasto.lisaaVarastoon(5);

        double expected = varasto.getSaldo();

        varasto.lisaaVarastoon(-1);

        assertEquals(expected, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void otetaanLiikaa() {

        varasto.lisaaVarastoon(5);

        assertEquals(5.0, varasto.otaVarastosta(10), vertailuTarkkuus);
    }

    @Test
    public void otetaanInvalidiMäärä() {

        varasto.lisaaVarastoon(5);

        assertEquals(0, varasto.otaVarastosta(-1), vertailuTarkkuus);
    }


    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ToStringToimii() {

        fail();

        varasto.lisaaVarastoon(5);

        assertEquals("saldo = 5.0, vielä tilaa 5.0", varasto.toString());
    }
}