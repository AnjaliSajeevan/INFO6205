package edu.neu.coe.info6205.util;


import edu.neu.coe.info6205.union_find.*;


import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;



public class Benchmark_Alternatives {

//    public Benchmark_WQUPC(Config config) {
//        this.config = config;
//    }

    public static void main(String[] args) throws IOException {
        Benchmark_Timer<Integer> d;
        Benchmark_Timer<Integer> h;
        Benchmark_Timer<Integer> pc1;
        Benchmark_Timer<Integer> pc11;
        Benchmark_Timer<Integer> pc2;

        Consumer<Integer> dc = x -> depthWQU(x);
        Consumer<Integer> p1c = x -> onePath(x);
        Consumer<Integer> p11c = x -> onePathHeight(x);
        Consumer<Integer> hc = x -> heightWQU(x);
        Consumer<Integer> p2c = x -> twoPath(x);

        int m = 500;
        for(int n=500;n<=200000;n=n*2)
        {

        d = new Benchmark_Timer<>("Weighted Quick Union - Depth",null, dc,null);
        h = new Benchmark_Timer<>("Weighted Quick Union - Size", null, hc, null);
        pc1 = new Benchmark_Timer<>("Weighted Quick Union - One pass - path compression", null, p1c, null);
        pc11 = new Benchmark_Timer<>("Weighted Quick Union - One pass -path compression with size", null, p11c, null);
        pc2 = new Benchmark_Timer<>("Weighted Quick Union - Two pass - path compression", null, p2c, null);



            int t = n;
            Supplier<Integer> supplier = () -> t;

            double dt = d.runFromSupplier(supplier, m);
            double ht = h.runFromSupplier(supplier, m);
            double p1t = pc1.runFromSupplier(supplier, m);
            double p11t = pc11.runFromSupplier(supplier, m);
            double p2t = pc2.runFromSupplier(supplier, m);


            System.out.println();
            System.out.println("----------------------------------------------------------" + n +"-------------------------------------------------------------------");
            System.out.println("Weighted Quick Union by Depth - average time: " + dt + " for " + m +" runs. " );
            System.out.println("Weighted Quick Union by Size- average time: " + ht+ " for " + m +" runs. "  );
            System.out.println("Weighted Quick Union by Size - One pass - path compression - average time:: " + p11t + " for " + m +" runs. " );
            System.out.println("Weighted Quick Union - One pass - path compression - average time: " + p1t + " for " + m +" runs. " );
            System.out.println("Weighted Quick Union - Two pass - path compression - average time: " + p2t+ " for " + m +" runs. "  );
            System.out.println();

        }
    }

    // CONSIDER generifying common code (but it's difficult if not impossible)
    private static void depthWQU(int n) {
        UF_DWQU depth = new UF_DWQU(n);
        Random r = new Random();

        while (depth.components() != 1) {
            int a = r.nextInt(n);
            int b = r.nextInt(n);

            if (!depth.connected(a, b)) {
                depth.union(a, b);
            }
        }
    }

    public static void twoPath(int n) {
        WQUPC wupc = new WQUPC(n);
        Random r = new Random();

        while (wupc.count() != 1) {
            int a = r.nextInt(n);
            int b = r.nextInt(n);

            if (!wupc.connected(a, b)) {
                wupc.union(a, b);
            }
        }

    }

    private static void heightWQU(int n) {
        UF_HWQU height = new UF_HWQU(n);
        Random r = new Random();

        while (height.components() != 1) {
            int a = r.nextInt(n);
            int b = r.nextInt(n);

            if (!height.connected(a, b)) {
                height.union(a, b);
            }
        }
    }

    private static void onePathHeight(int n) {
        UF_HWQUPC wup = new UF_HWQUPC(n);
        Random r = new Random();

        while (wup.components() != 1) {
            int a = r.nextInt(n);
            int b = r.nextInt(n);

            if (!wup.connected(a, b)) {
                wup.union(a, b);
            }
        }
    }

    private static void onePath(int n) {
        WQUPC_OneLoop wup = new WQUPC_OneLoop(n);
        Random r = new Random();

        while (wup.count() != 1) {
            int a = r.nextInt(n);
            int b = r.nextInt(n);

            if (!wup.connected(a, b)) {
                wup.union(a, b);
            }
        }
    }

}
