import edu.princeton.cs.algs4.StdRandom;

public class QuickSort {

    // Version 1: có shuffle
    public static void sortShuffle(int[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    // Version 2: không shuffle
    public static void sortNoShuffle(int[] a) {
        sort(a, 0, a.length - 1);
    }

    private static void sort(int[] a, int lo, int hi) {
        while (lo < hi) {
            int p = partition(a, lo, hi);

            // Xử lý nửa nhỏ bằng đệ quy để hạn chế tràn stack
            if (p - lo < hi - p) {
                sort(a, lo, p - 1);
                lo = p + 1;
            } else {
                sort(a, p + 1, hi);
                hi = p - 1;
            }
        }
    }

    private static int partition(int[] a, int lo, int hi) {
        int pivot = a[lo];
        int i = lo + 1;
        int j = hi;

        while (true) {
            while (i <= hi && a[i] < pivot) i++;
            while (j > lo && a[j] > pivot) j--;

            if (i >= j) break;

            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;

            i++;
            j--;
        }

        int temp = a[lo];
        a[lo] = a[j];
        a[j] = temp;

        return j;
    }
}
