package Percolation;

import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    private double[] experiments;

    public PercolationStats(int n, int t) {
        if (n < 1 || t < 1) {
            throw new IllegalArgumentException("n and t must be >=1 ");
        }

        this.experiments = new double[t];

        for (int numExperiments = 0; numExperiments < t; numExperiments++) {
            Percolation percolation = new Percolation(n);
            long counter = 0L;
            while (!percolation.percolates()) {
                int i = StdRandom.uniform(n) + 1;
                int j = StdRandom.uniform(n) + 1;
                if (!percolation.isOpen(i, j)) {
                    percolation.open(i, j);
                    counter += 1L;
                }
            }
            this.experiments[numExperiments] = (double) counter / (n * n);
        }
    }

    public static void main(String[] args) {
        if (args.length != 2)
            throw new IllegalArgumentException();
        try {
            int gridSize = Integer.parseInt(args[0]);
            int numExperiments = Integer.parseInt(args[1]);
            PercolationStats stats = new PercolationStats(gridSize, numExperiments);
            double mean = stats.mean();
            double stddev = stats.stddev();
            double confidenceLo = stats.confidenceLo();
            double confidenceHi = stats.confidenceHi();

            System.out.printf("mean                    = %f\n", mean);
            System.out.printf("stddev                  = %f\n", stddev);
            System.out.printf("95%% confidence interval = %f, %f\n", confidenceLo, confidenceHi);
        } catch (NumberFormatException e){}
    }

    public double mean() {
        double sum = 0.0D;
        for (double threshold : this.experiments) {
            sum += threshold;
        }
        return sum / this.experiments.length;
    }

    public double stddev() {
        double mean = mean();
        double sum = 0.0D;
        for (double threshold : this.experiments) {
            double delta = threshold - mean;
            sum += delta * delta;
        }
        return Math.sqrt(sum / (this.experiments.length - 1));
    }

    public double confidenceLo() {
        return mean() - 1.96D * stddev() / Math.sqrt(this.experiments.length);
    }

    public double confidenceHi() {
        return mean() + 1.96D * stddev() / Math.sqrt(this.experiments.length);
    }
}