public class MyCheckedException extends Exception {

    private double x;
    private static double min = 10.5;

    public static double getMin() {
        return min;
    }

    public MyCheckedException(double x) {
        this.x = x;
    }

    public double getX() {
        return x;
    }
}
