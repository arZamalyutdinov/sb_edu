package Solvers;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class NonStreamSolver {
    public static List<Integer> solve(List<Integer> values) {
        values.removeIf(e -> e.equals(255));
        return values;
    }
}
