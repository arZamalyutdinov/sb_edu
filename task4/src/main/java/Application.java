import java.util.List;

import Solvers.NonStreamSolver;
import Solvers.StreamSolver;
import generators.*;
import helpers.Stopwatch;


public class Application {

    public static void main(String[] args) {
        List<Integer> lst1 = NumberGenerator.generate();
        List<Integer> lst2 = NumberGenerator.generate();
        Stopwatch sw1 = new Stopwatch();
        lst1 = StreamSolver.solve(lst1);
        System.out.println("Stream Time: " + sw1.getElapsedTime());
        Stopwatch sw2 = new Stopwatch();
        lst2 = NonStreamSolver.solve(lst2);
        System.out.println("Non-stream Time: " + sw2.getElapsedTime());
//        System.out.println(lst1);
//        System.out.println(lst2);
    }
}
