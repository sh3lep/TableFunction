import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.util.Pair;

import java.util.*;

public class TFun {
    public Map<Double,Double> table;

    TFun(Map<Double,Double>table){
        this.table = table;
    }

    private Boolean hasPair(Double x, Double y){
        return table.containsKey(x) && table.containsValue(y);
    }

    public void addPair(Double x,Double y){
        if (!hasPair(x, y)) {
            table.put(x, y);
        } else throw new IllegalArgumentException("Пара уже существует");
    }

    public void deletePair (Double x, Double y){
        if (hasPair(x, y)){
            table.remove(x, y);
        } else throw new IllegalArgumentException("Пара не найдена");
    }

    public Map<Double, Double> getTable() {
        return table;
    }

    public Pair<Double,Double> getClosest (Double x){
        Double closestX = Double.MAX_VALUE;
        for (Double current : table.keySet()){
            if (current - x < closestX - x) {
                closestX = current;
            }

        }
        return new Pair<>(closestX,table.get(closestX));
    }

    public Double getApproximate (Double x) {
        if (hasPair(x, table.get(x))) {
            System.out.print("Существует точное значение: ");
            return table.get(x);
        }
        Double leftX = Double.MAX_VALUE;
        Double rightX = Double.MAX_VALUE;
        for (Double current : table.keySet()) {
            if (current - x < leftX - x && current < x) {
                leftX = current;
            }
        }
        for (Double current : table.keySet()) {
            if (current - x < rightX - x && current > x) {
                rightX = current;
            }
        }
        return table.get(leftX) + (x - leftX) / (rightX - leftX) * (table.get(rightX) - table.get(leftX));
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

}
