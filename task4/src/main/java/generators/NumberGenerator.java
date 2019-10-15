package generators;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class NumberGenerator {
    public static List<Integer> generate() {
        List<Integer> values = new LinkedList<>();
        for (int i = 0; i < 3000 * 1000; ++i) {
            Random rnd = new Random();
            if (rnd.nextBoolean()) {
                values.add(255);
            } else {
                values.add(rnd.nextInt(255));
            }
        }
        return values;
    }
}
