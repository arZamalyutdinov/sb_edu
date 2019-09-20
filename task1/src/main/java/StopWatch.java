public class StopWatch {

    private double startTime;

    public StopWatch() {
        startTime = System.currentTimeMillis();
    }

    public double getElapsedTime() {
        return (System.currentTimeMillis()- startTime) / 1000 ;
    }
}
