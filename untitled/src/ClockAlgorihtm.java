import java.util.ArrayList;
import java.util.Scanner;

public class ClockAlgorihtm {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int num = 4;

        System.out.printf("Write something(write 0 for the stop): ");
        final ArrayList<String> arrayList = new ArrayList<>();
        while (true) {
            final String names = input.next();
            if ("O".equals(names) || "0".equals(names)) {
                break;
            }
            arrayList.add(names);
        }
        final String[] array = new String[arrayList.size()];
        arrayList.toArray(array);

        final Frame frames[] = new Frame[num];
        for (int i = 0; i < num; ++i) {
            frames[i] = new Frame();
        }

        Clock(frames, num, array);

    }

    private static int Clock(final Frame frames[], final int numberOfFrames, final String... arrayNames) {
        int queue = 0;
        int aa = 0;

        for (int i = 0; i < numberOfFrames; ++i) {
            final Frame frame = frames[i];
            System.out.print("Stack :" + frame.reference + " " + frame.change);
            System.out.printf(" %s:      ", (i == queue) ? "    (step)" : "");
            System.out.println();
        }
        System.out.println();

        for (int n = 0; n < arrayNames.length; ++n) {
            System.out.println("           ---- Phase: " + (n + 1) + " ----");
            final String referans = arrayNames[n];
            if (referans == null || referans.equalsIgnoreCase("null")) {
                System.out.printf(" Nothing is find... ");
                continue;
            }
            System.out.println("Input names ---> " + referans + "\n");

            boolean fault = true;
            for (int i = 0; i < numberOfFrames; ++i) {
                final Frame frame = frames[i];
                if (referans.equals(frame.reference)) {

                    frame.change = 1;
                    fault = false;
                    break;
                }
            }

            if (fault) {

                while (frames[queue].change == 1) {
                    frames[queue].change = 0;
                    queue = (queue + 1) % numberOfFrames;
                }

                frames[queue].reference = referans;
                frames[queue].change = 1;

                queue = (queue + 1) % numberOfFrames;

                ++aa;
            }
            for (int i = 0; i < numberOfFrames; ++i) {
                final Frame frame = frames[i];
                System.out.print("Stack :" + frame.reference + " " + frame.change);
                System.out.printf(" %s:      ", (i == queue) ? "    (step)" : "");
                System.out.println();
            }
            System.out.println();
        }
        return aa;
    }

}

class Frame {

    String reference;
    byte change;

    Frame() {
        reference = null;
        change = 0;
    }
}