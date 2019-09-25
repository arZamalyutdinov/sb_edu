public class SequenceGenerator {
    private int val;

    public SequenceGenerator() {
        val = 1;
    }

    public int getNext() {
        return val++;
    }
}
