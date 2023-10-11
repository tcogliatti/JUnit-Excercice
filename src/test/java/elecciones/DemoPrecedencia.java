package elecciones;

import org.junit.*;

import static org.junit.jupiter.api.Assertions.fail;

public class DemoPrecedencia {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        System.out.println("\telecciones.DemoPrecedencia -> BeforeClass ");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        System.out.println("\telecciones.DemoPrecedencia -> AfterClass ");
    }


    @Before
    public void setUp() throws Exception {
        System.out.println("\t|\telecciones.DemoPrecedencia -> Before");

    }
    /**
     * Metodo tearDown para instancias de test
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        System.out.println("\t|\telecciones.DemoPrecedencia -> After");
    }

    @Test
    public void testFuncion1() {
        System.out.println("\t|\t|\telecciones.DemoPrecedencia -> testFuncion1");
//        fail("Falla porque lo forcé");
        Assert.assertTrue("no, no son iguales",2+2==4);
    }

    @Test
    public void testFuncion2() {
        System.out.println("\t|\t|\telecciones.DemoPrecedencia -> TestFuncion2");
    }
    @Test
    public void testFuncion3() {
        System.out.println("\t|\t|\telecciones.DemoPrecedencia -> TestFuncion3");
    }

}