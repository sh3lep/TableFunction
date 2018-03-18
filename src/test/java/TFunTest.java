import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;


class TFunTest {


    @Test
    public void addPair() {
        TFun table = new TFun();
        table.addPair(2.28, 13.37);
        assertEquals(2.28, table.X.get(0));
    }

    @Test
    public void deletePair() {
        TFun table = new TFun();
        assertTrue(table.deletePair(2.28, 13.37));
    }

}