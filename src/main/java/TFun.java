import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.util.Pair;

import java.util.*;

import static java.lang.Math.abs;

public class TFun {
    public ArrayList<Double> X;
    public ArrayList<Double> Y;

    TFun(){
        X = new ArrayList<>();
        Y = new ArrayList<>();
    }

    TFun(ArrayList<Double> X, ArrayList<Double> Y) {
        this.X = X;
        this.Y = Y;
    }

    private Boolean hasPair(Double x, Double y) {
        return X.contains(x) && Y.contains(y);
    }

    public void addPair(Double x, Double y) {
        if (!hasPair(x, y)) {
            X.add(x);
            Y.add(y);
        } else throw new IllegalArgumentException("Пара уже существует");
    }

    public void deletePair(Double x, Double y) {
        if (hasPair(x, y)) {
            X.remove(x);
            Y.remove(y);
        } else throw new IllegalArgumentException("Пара не найдена");
    }

    public ArrayList<Pair<Double,Double>> getTable() {
        ArrayList<Pair<Double,Double>> list = new ArrayList<>();
        for (int i =0; i < X.size();i++){
            list.add(new Pair<>(X.get(i),Y.get(i)));
        }
        return list;
    }

    public Pair<Double, Double> getClosest(Double x) {
        Double closestX = Double.MAX_VALUE;
        for (Double current : X) {
            if (abs(current - x) < abs(closestX - x)) {
                closestX = current;
            }

        }
        return new Pair<>(closestX,Y.get(X.indexOf(closestX)));
    }

    public Double getApproximate(Double x) {
        if (hasPair(x, Y.get(X.indexOf(x)))) {
            throw new IllegalArgumentException("Существует точное значение");
        }
        Double leftX = Double.MAX_VALUE;
        Double rightX = Double.MAX_VALUE;
        for (Double current : X) {
            if (current - x < leftX - x && current < x) {
                leftX = current;
            }
        }
        for (Double current : X) {
            if (current - x < rightX - x && current > x) {
                rightX = current;
            }
        }
        return X.get(X.indexOf(leftX)) + (x - leftX) / (rightX - leftX) * (X.get(X.indexOf(rightX)) - X.get(X.indexOf(leftX)));
    }

    @Override
    public String toString() {
        return "TFun{" +
                "X=" + X +
                ", Y=" + Y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TFun tFun = (TFun) o;

        if (X != null ? !X.equals(tFun.X) : tFun.X != null) return false;
        return Y != null ? Y.equals(tFun.Y) : tFun.Y == null;
    }

    @Override
    public int hashCode() {
        int result = X != null ? X.hashCode() : 0;
        result = 31 * result + (Y != null ? Y.hashCode() : 0);
        return result;
    }
}
