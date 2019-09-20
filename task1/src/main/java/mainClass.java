import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class mainClass {
    public static void main(String[] Args) {
        int n = Integer.parseInt(Args[0]);
        StopWatch tm = new StopWatch();
        int a = 0;
        int b = 1;
        for (int i = 0; i < n; ++i) {
            System.out.print(b + " ");
            int s = a + b;
            a = b;
            b = s;
        }
        System.out.println();
        Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());
        log.info("Execution time: " + tm.getElapsedTime() + " seconds");
    }
}
