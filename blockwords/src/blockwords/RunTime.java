package blockwords;

import java.util.Arrays;

public class RunTime {
	
	static double[] medianTime = new double[11];

	public static void main(String[] args) {
			for (int i = 0; i < 11; i++) {
				long time_prev = System.nanoTime();
				Main.main(null);
				double time = (System.nanoTime() - time_prev) / 1000000000.0;
				medianTime[i] = time;
			}
			System.out.println(median(medianTime));
		
	}

	public static double median(double[] a) {
		Arrays.sort(a);
		return a[a.length / 2];
	}
}

