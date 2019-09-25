import java.util.HashMap;
import java.util.Random;
import java.util.stream.Stream;

public class deviceGenerator {
    private static Integer maxComps;
    private static HashMap<Integer, device> devices;
    private static SequenceGenerator seq = new SequenceGenerator();

    public device generateDevices() {
        Random r = new Random();
        Integer comps = r.nextInt(maxComps) + 1;

        switch (comps) {
            case 1:
                return new atm(seq.getNext(), "atm", deviceStatus.Normal, )
        }
    }

}
