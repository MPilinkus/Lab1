package edu.ktu.ds.lab1b.pilinkus;

import edu.ktu.ds.lab1b.util.Ks;
import edu.ktu.ds.lab1b.util.Parsable;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class House implements Parsable<House> {
    private String city;
    private String address;
    private int year;
    private double price;
    private double area;

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    public double getArea() {
        return area;
    }

    public House() {
    }

    public House(String city, String address, int year, double price, double area) {
        this.city = city;
        this.address = address;
        this.year = year;
        this.price = price;
        this.area = area;
    }

    public House(String data) {
        parse(data);
    }

    @Override
    public final void parse(String data) {
        try {
            Scanner ed = new Scanner(data);
            city = ed.next();
            address = ed.next();
            year = ed.nextInt();
            price = ed.nextDouble();
            area = ed.nextDouble();
        } catch (InputMismatchException e) {
            Ks.ern("Blogas duomenų formatas apie namą -> " + data);
        } catch (NoSuchElementException e) {
            Ks.ern("Trūksta duomenų apie namą -> " + data);
        }
    }

    @Override
    public String toString() {
        return String.format("%-8s %-8s %4d %9.1f %8.1f",
                city, address, year, price, area);
    }

    @Override
    public int compareTo(House otherHouse) {
        double otherPrice = otherHouse.getPrice();
        if (price < otherPrice) {
            return -1;
        }
        if (price > otherPrice) {
            return +1;
        }
        return 0;
    }
}
