/**
 * Original code:
 * Copyright © 2000–2017, Robert Sedgewick and Kevin Wayne.
 * <p>
 * Modifications:
 * Copyright (c) 2017. Phasmid Software
 */
package edu.neu.coe.info6205.union_find;

import java.util.Arrays;

/**
 * Depth-weighted Quick Union
 */
public class UF_DWQU implements UF {
    /**
     * Ensure that site p is connected to site q,
     *
     * @param p the integer representing one site
     * @param q the integer representing the other site
     */
    public void connect(int p, int q) {
        if (!isConnected(p, q)) union(p, q);
    }

    /**
     * Initializes an empty union–find data structure with {@code n} sites
     * {@code 0} through {@code n-1}. Each site is initially in its own
     * component.
     *
     * @param n the number of sites
     * @throws IllegalArgumentException if {@code n < 0}
     */
    public UF_DWQU(int n) {
        count = n;
        parent = new int[n];
        depth = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            depth[i] = 0;
        }
//        this.pathCompression = pathCompression;
    }

    /**
     * Initializes an empty union–find data structure with {@code n} sites
     * {@code 0} through {@code n-1}. Each site is initially in its own
     * component.
     * This data structure uses path compression
     * <p>
     * //     * @param n the number of sites
     *
     * @throws IllegalArgumentException if {@code n < 0}
     */
//    public UF_DWQU(int n) {
//        this(n, true);
//    }
    public void show() {
        for (int i = 0; i < parent.length; i++) {
            System.out.printf("%d: %d, %d\n", i, parent[i], depth[i]);
        }
    }

    /**
     * Returns the number of components.
     *
     * @return the number of components (between {@code 1} and {@code n})
     */
    public int components() {
        return count;
    }

    /**
     * Returns the component identifier for the component containing site {@code p}.
     *
     * @param p the integer representing one site
     * @return the component identifier for the component containing site {@code p}
     * @throws IllegalArgumentException unless {@code 0 <= p < n}
     */
    public int find(int p) {
        validate(p);
        int root = p;
        while (root != parent[root]) {

//            if (this.pathCompression) {
//                doPathCompression(root);
//            }
            root = parent[root];
        }

        return root;
    }

    /**
     * Returns true if the the two sites are in the same component.
     *
     * @param p the integer representing one site
     * @param q the integer representing the other site
     * @return {@code true} if the two sites {@code p} and {@code q} are in the same component;
     * {@code false} otherwise
     * @throws IllegalArgumentException unless
     *                                  both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * Merges the component containing site {@code p} with the
     * the component containing site {@code q}.
     *
     * @param p the integer representing one site
     * @param q the integer representing the other site
     * @throws IllegalArgumentException unless
     *                                  both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    public void union(int p, int q) {

        mergeComponents(find(p), find(q));
        count--;
    }

    @Override
    public int size() {
        return parent.length;
    }

    /**
     * Used only by testing code
     * <p>
     * //     * @param pathCompression true if you want path compression
     */
//    public void setPathCompression(boolean pathCompression) {
//        this.pathCompression = pathCompression;
//    }
    @Override
    public String toString() {
        return "UF_DWQU:" + "\n  count: " + count +
//                "\n  path compression? " + pathCompression +
                "\n  parents: " + Arrays.toString(parent)
                + "\n  depth: " + Arrays.toString(depth);
    }

    // validate that p is a valid index
    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
        }
    }

//    private void updateParent(int p, int x) {
//        parent[p] = x;
//    }

//    private void updateHeight(int p, int x) {
//        height[p] += height[x];
//    }

    /**
     * Used only by testing code
     *
     * @param i the component
     * @return the parent of the component
     */
    private int getParent(int i) {
        return parent[i];
    }

    private final int[] parent;   // parent[i] = parent of i
    private final int[] depth;   // depth[i] = depth of subtree rooted at i
    private int count;  // number of components
//    private boolean pathCompression;

    private void mergeComponents(int p, int q) {

        int rP = find(p);
        int rQ = find(q);
        if (rP == rQ) {
            return;
        }
        if (depth[rP] < depth[rQ]) {
            parent[rP] = rQ;
        } else if (depth[rP] > depth[rQ]) {
            parent[rQ] = rP;
        } else {
            parent[rQ] = rP;
            depth[rP]++;
        }
//        System.out.println(Arrays.toString(depth));
    }


    /**
     * This implements the single-pass path-halving mechanism of path compression
     */
//    private void doPathCompression(int i) {
//        int y =parent[i];
//        parent[i] = parent[y];
//        updateParent(i, parent[parent[i]]);


    public static void main(String [] args){

        UF_DWQU x=new UF_DWQU(4);

    }


}


