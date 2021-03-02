package edu.neu.coe.info6205.union_find;

import java.util.Random;

public class UnionFindClient {

    public static int count(int n){
        int conn = 0;
        int uni=0;
        Random rand = new Random();
        UF_HWQUPC client = new UF_HWQUPC(n);
        while (client.components() != 1) {
            int a = rand.nextInt(n);
            int b = rand.nextInt(n);
//            System.out.println("a:"+a+", b:"+b);

            if (client.connected(a, b)) {
                conn++;
            }else{
                client.union(a, b);
                uni++;
                conn++;

            }
//            System.out.println("Union count:"+uni);
        }
        return conn;
    }

    public static void main(String[] args) {

//
//        if (args.length == 0)
//            throw new RuntimeException("The number of connections required not entered");
//        int n = Integer.parseInt(args[0]);

        Random rand = new Random();

//        for (int i = 0; i < 20; i++) {

//            int n= rand.nextInt(20000);
            for (int n = 10000; n < 200000; n=n*2) {
            System.out.println("Number of sites/objects(n):" + n + ", Number of Pairs(m):" + count(n));

        }
    }

}
