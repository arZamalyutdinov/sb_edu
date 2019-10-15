package helpers;

public class Stopwatch {

    private double startTime;

    public Stopwatch() {
        startTime = System.currentTimeMillis();
    }

    public double getElapsedTime() {
        return (System.currentTimeMillis()- startTime) / 1000 ;
    }
}
