import java.util.Arrays;
import java.util.Random;

public class BAI23W5 {

    static Random random = new Random();

    static int[] generateData(int n, String type) {
        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            if (type.equals("Random")) {
                a[i] = random.nextInt(100000);
            } else if (type.equals("Equal")) {
                a[i] = 5;
            } else if (type.equals("Sorted")) {
                a[i] = i;
            } else if (type.equals("Reverse")) {
                a[i] = n - i;
            }
        }

        return a;
    }

    static double measure(
            int[] original, String algorithm, int repetitions) {

        double total = 0;

        for (int r = 0; r < repetitions; r++) {
            int[] a = Arrays.copyOf(original, original.length);

            long start = System.nanoTime();

            switch (algorithm) {
                case "Insertion":
                    InsertionSort.sort(a);
                    break;

                case "Merge":
                    MergeSort.sort(a);
                    break;

                case "QuickShuffle":
                    QuickSort.sortShuffle(a);
                    break;

                case "QuickNoShuffle":
                    QuickSort.sortNoShuffle(a);
                    break;
            }

            long end = System.nanoTime();
            total += (end - start) / 1_000_000.0;

            // Kiểm tra kết quả sắp xếp
            if (!isSorted(a)) {
                throw new RuntimeException(
                    algorithm + " sorted incorrectly!");
            }
        }

        return total / repetitions;
    }

    static boolean isSorted(int[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i - 1] > a[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        int[] sizes = {1000, 3000, 5000};

        String[] types = {
            "Random", "Sorted", "Reverse", "Equal"
        };

        String[] algorithms = {
            "Insertion", "Merge",
            "QuickShuffle", "QuickNoShuffle"
        };

        System.out.printf(
            "%-10s %-8s %-18s %12s%n",
            "Data", "Size", "Algorithm", "Avg (ms)"
        );

        for (String type : types) {
            for (int n : sizes) {
                int[] data = generateData(n, type);

                for (String algorithm : algorithms) {
                    int repetitions =
                        type.equals("Random") ? 5 : 3;

                    double avg = measure(
                        data, algorithm, repetitions
                    );

                    System.out.printf(
                        "%-10s %-8d %-18s %12.3f%n",
                        type, n, algorithm, avg
                    );
                }

                System.out.println();
            }
        }
    }
}