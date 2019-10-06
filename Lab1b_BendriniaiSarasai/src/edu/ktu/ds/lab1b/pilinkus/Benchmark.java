package edu.ktu.ds.lab1b.pilinkus;

import edu.ktu.ds.lab1b.util.Ks;

import java.util.*;

public class Benchmark {


    Random rg = new Random();
    int[] counts = {2_000, 4_000, 8_000, 16_000, 32_000, 64_000, 128_000, 256_000};
    int[] xIntNumbers;
    int[] yIntNumbers;
    double[] xDoubleNumbers;
    double[] yDoubleNumbers;
    ArrayList<Integer> Arr = new ArrayList<Integer>();
    ArrayList<Integer> Val = new ArrayList<Integer>();
    double result;

    void generateNumbers(int count) {
        xIntNumbers = new int[count];
        yIntNumbers = new int[count];
        xDoubleNumbers = new double[count];
        yDoubleNumbers = new double[count];
        rg.setSeed(2017);
        for(int i = 0; i < count; i++){
            xIntNumbers[i] = rg.nextInt(200);
            yIntNumbers[i] = rg.nextInt(200);
            xDoubleNumbers[i] = rg.nextDouble()*200;
            yDoubleNumbers[i] = rg.nextDouble()*200;
        }
        Collections.shuffle(Arrays.asList(xIntNumbers, yIntNumbers));
        Collections.shuffle(Arrays.asList(xDoubleNumbers, yDoubleNumbers));
    }

    void testSqrtVsHypot (int elementCount) {
        long t0 = System.nanoTime();
        generateNumbers(elementCount);
        long t1 = System.nanoTime();
        System.gc();
        System.gc();
        System.gc();
        long t2 = System.nanoTime();
        //nustatoma kiek laiko for sukasi tuscias
        for(int i = 0; i < elementCount; i++){}
        long t3 = System.nanoTime();
        for(int i = 0; i < elementCount; i++){
            result = Math.sqrt(xIntNumbers[i]*xIntNumbers[i]+yIntNumbers[i]*yIntNumbers[i]*1.0);
        }
        long t4 = System.nanoTime();
        for(int i = 0; i < elementCount; i++){
            result = Math.hypot(xIntNumbers[i],yIntNumbers[i]);
        }
        long t5 = System.nanoTime();
        for(int i = 0; i < elementCount; i++){
            result = Math.sqrt(xDoubleNumbers[i]*xDoubleNumbers[i]+yDoubleNumbers[i]*yDoubleNumbers[i]*1.0);
        }
        long t6 = System.nanoTime();
        for(int i = 0; i < elementCount; i++){
            result = Math.hypot(xDoubleNumbers[i],yDoubleNumbers[i]);
        }
        long t7 = System.nanoTime();
        Ks.ouf("%7d %7.4f %7.4f %7.4f %7.4f %7.4f %7.4f %7.4f \n", elementCount,
                (t1 - t0) / 1e9, (t2 - t1) / 1e9, (t3 - t2) / 1e9, (t4 - t3) / 1e9, (t5 - t4) / 1e9,
                (t6 - t5) / 1e9, (t7 - t6) / 1e9);
    }

    void testArrayList(int elementCount){
        long t0 = System.nanoTime();
        for(int i = 0; i<elementCount; i++){
            Arr.add(i);
        }
        for(int i = 0; i < elementCount*0.5; i++){
            Val.add(rg.nextInt(elementCount));
        }
        long t1 = System.nanoTime();
        System.gc();
        System.gc();
        System.gc();
        long t2 = System.nanoTime();
        for(int a:Val){
            Arr.indexOf(a);
        }
        long t3 = System.nanoTime();
        for(int a:Val){
            Arr.lastIndexOf(a);
        }
        long t4 = System.nanoTime();
        Ks.ouf("%7d %7.4f %7.4f %7.4f %7.4f \n", elementCount,
                (t1 - t0) / 1e9, (t2 - t1) / 1e9, (t3 - t2) / 1e9, (t4 - t3) / 1e9);
    }

    void execute() {
        long memTotal = Runtime.getRuntime().totalMemory();
        Ks.oun("memTotal= " + memTotal);
        Ks.oun("Sqrt vs Hypot");
        Ks.oun("1 - Pasiruošimas tyrimui - duomenų generavimas");
        Ks.oun("2 - Pasiruošimas tyrimui - šiukšlių surinkimas");
        Ks.oun("3 - Pasiruošimas tyrimui - sužinomas for ciklo laikas, iš rezultatų neatimta");
        Ks.oun("4 - Math.sqrt(x*x+y*y) - su int");
        Ks.oun("5 - Math.hypot(x, y) - su int");
        Ks.oun("6 - Math.sqrt(x*x+y*y) - su double");
        Ks.oun("7 - Math.hypot(x, y) - su double");
        Ks.ouf("%6d %7d %7d %7d %7d %7d %7d %7d \n", 0, 1, 2, 3, 4, 5, 6, 7);
        for (int n : counts) {
            testSqrtVsHypot(n);
        }
        Ks.oun("ArrayList.IndexOf vs ArrayList.LastIndexOf");
        Ks.oun("1 - Pasiruošimas tyrimui - duomenų generavimas");
        Ks.oun("2 - Pasiruošimas tyrimui - šiukšlių surinkimas");
        Ks.oun("3 - ArrayList.indexOf 5% galimų reikšmių");
        Ks.oun("4 - ArrayList.lastIndexOf 5% galimų reikšmių");
        Ks.ouf("%6d %7d %7d %7d %7d \n", 0, 1, 2, 3, 4);
        for (int n : counts) {
            testArrayList(n);
        }
        Ks.oun("Greitaveikos tyrimas baigtas");
    }

    public static void main(String[] args) {
        Locale.setDefault(new Locale("LT"));
        new Benchmark().execute();
    }
}
