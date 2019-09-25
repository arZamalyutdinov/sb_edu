public class Point {
    private double x;
    private double y;

    public Point(double x, double y) throws MyCheckedException {
        if (x < MyCheckedException.getMin() || y < MyCheckedException.getMin()) {
            throw  new MyCheckedException(Math.min(x,y));
        } else {
            this.x = x;
            this.y = y;
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
