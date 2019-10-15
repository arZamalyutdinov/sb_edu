package generators;

public class SequenceGenerator {
    private int val;

    public SequenceGenerator() {
        this.val = 1;
    }

    public SequenceGenerator(int start_val) {
        val = start_val;
    }

    public int getNext() {
        return val++;
    }
}
