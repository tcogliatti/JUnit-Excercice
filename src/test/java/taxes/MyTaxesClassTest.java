package taxes;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import taxes.MyTaxesClass;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyTaxesClassTest {
    MyTaxesClass tc;

    @BeforeEach
    void setUp() {
        this.tc = new MyTaxesClass();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Test of added taxes to avalue")
    void addIVA() {
        float value = 10;
        double valueWithTaxes = 12.1;
        assertEquals(valueWithTaxes, tc.addIVA(value));
    }
}