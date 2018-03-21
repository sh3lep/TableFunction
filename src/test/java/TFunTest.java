import static org.junit.Assert.*;

import javafx.util.Pair;
import org.junit.jupiter.api.Test;

class TFunTest {

    @Test
    public void addPair() {
        TFun table = new TFun();
        assertTrue(table.addPair(2.28, 13.37));
        assertTrue(table.addPair(-27, 1703));
    }

    @Test
    public void removePair() {
        TFun table = new TFun();
        table.addPair(22.8, 1.337);
        assertTrue(table.removePair(22.8, 1.337));
    }

    @Test
    public void getTable() {
        TFun table = new TFun();
        table.addPair(22.8, 1.337);
        table.addPair(27, 1703);
        table.addPair(-5.1234, -777.666);
        System.out.println(table.getTable());
    }

    @Test
    public void getClosest() {
        TFun table = new TFun();
        table.addPair(1.0, 9.0);
        table.addPair(2.0, 8.0);
        table.addPair(3.0, 7.0);
        table.addPair(4.0, 6.0);
        assertEquals(new Pair<>(3.0, 7.0), table.getClosest(3.3));
    }

    @Test
    public void interpolate() {
        TFun table = new TFun();
        table.addPair(5000.0, 228);
        table.addPair(6000.0, 15.5);
        table.addPair(8000.0, 19.2);
        table.addPair(9000.0, 1337);
        assertEquals(16.1993, table.interpolate(6378.0), 1e-3);
    }


}