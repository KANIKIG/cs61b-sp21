package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HER
        SLList<Integer> N = new SLList<>();
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();

        int tick = 0;
        int ops = 10000;
        for (int i = 0; i < 128000; i += 1) {
            N.addLast(i);
            if (N.size() == Math.pow(2, tick) * 1000) {
                Stopwatch sw = new Stopwatch();
                for (int j = 0; j < ops; j += 1){
                    N.getLast();
                }
                Ns.addLast(N.size());
                times.addLast(sw.elapsedTime());
                opCounts.addLast(ops);
                tick += 1;
            }
        } 
        printTimingTable(Ns, times, opCounts);
    
    }
}
