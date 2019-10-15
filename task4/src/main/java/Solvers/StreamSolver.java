package Solvers;

import java.util.List;
import java.util.stream.Collectors;

public class StreamSolver {
    public static List<Integer> solve(List<Integer> values) {
        return values.stream()
                .filter(a -> a != 255)
                .collect(Collectors.toList());
    }
}
