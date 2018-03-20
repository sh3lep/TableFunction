import javafx.util.Pair;

import java.util.*;

import static java.lang.Math.abs;

public class TFun {

    private TreeMap<Double, Double> table;

    TFun() {
        table = new TreeMap<>();
    }

    public boolean addPair(double x, double y) {
        int currentSize = table.size();
        if (!((table.containsKey(x)) && (table.containsValue(y)))) {
            table.put(x, y);
        } else throw new IllegalArgumentException("Пара уже существует");
        return table.size() - currentSize == 1;
    }

    public boolean removePair(double x, double y) {
        int currentSize = table.size();
        if ((table.containsKey(x)) && (table.containsValue(y))) {
            table.remove(x, y);
        } else throw new IllegalArgumentException("Пара не существует");
        return currentSize - table.size() == 1;
    }

    public TreeMap<Double, Double> getTable() {
        return table;
    }

    public Pair<Double, Double> getClosest(double x) {
        double closestX = Double.MAX_VALUE;
        if (!table.containsKey(x)) {
            for (Double current : table.keySet()) { // Минимальная по модулю разница
                if (abs(current - x) < abs(closestX - x)) {
                    closestX = current;
                }
            }
        } else throw new IllegalArgumentException("Пара с таким значением x существует");
        return new Pair<>(closestX, table.get(closestX));
    }

    public double getApproximate(double x) { //Линейная интерполяция
        double leftX = Double.MAX_VALUE;
        double rightX = Double.MAX_VALUE;
        if (!table.containsKey(x)) { // Находим "соседей" заданного значения x
            for (Double current : table.keySet()) {
                if (abs(current - x) < abs(leftX - x) && current < x) {
                    leftX = current;
                }
                if (abs(current - x) < abs(rightX - x) && current > x) {
                    rightX = current;
                }
            }
        } else throw new IllegalArgumentException("Существует точное значение");
        return table.get(leftX) + (x - leftX) / (rightX - leftX) * (table.get(rightX) - table.get(leftX)); // Формула из вики
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TFun tFun = (TFun) o;

        return table != null ? table.equals(tFun.table) : tFun.table == null;
    }

    @Override
    public int hashCode() {
        return table != null ? table.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TFun{" +
                "table=" + table +
                '}';
    }

}
