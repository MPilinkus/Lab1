package edu.ktu.ds.lab1b.pilinkus;

import edu.ktu.ds.lab1b.util.LinkedList;
import edu.ktu.ds.lab1b.util.Ks;

import java.util.Locale;

public class HouseTest {

    private LinkedList<House> allHouses;

    private HouseTest() {
        allHouses = new LinkedList<House>();
    }

    private void createHouses() {
        allHouses.add(new House(
                "Kaunas",
                "Berzu g. 1",
                2014,
                250000.00,
                150.75)
        );
        allHouses.add(new House(
                "Kaunas",
                "Rutu g. 1",
                2012,
                150000.00,
                200.50)
        );
        allHouses.add(new House(
                "Kaunas",
                "Plytu g. 1",
                2018,
                550000.00,
                300.00)
        );
        allHouses.add(new House(
                "Vilnius",
                "Rytu g. 1",
                2000,
                50000.00,
                125.20)
        );
        allHouses.add(new House(
                "Vilnius",
                "Metu g. 1",
                2015,
                700000.00,
                512.00)
        );
    }

    private void listHouses(LinkedList<House> houses)
    {
        for (House house:houses)
        {
            Ks.oun(house.toString());
        }
        Ks.oun(" ");
    }

    private LinkedList<House> getHousesInCity(String city)
    {
        LinkedList<House> houses = new LinkedList<House>();

        for (House house: allHouses)
        {
            if(house.getCity() == city)
            {
                houses.add(house);
            }
        }
        return houses;
    }

    private void run() {
        createHouses();
        listHouses(allHouses);
        allHouses.sortBuble();
        listHouses(allHouses);
        LinkedList<House> housesInKaunas = getHousesInCity("Kaunas");
        LinkedList<House> housesInVilnius = getHousesInCity("Vilnius");
        listHouses(housesInKaunas);
        listHouses(housesInVilnius);
        housesInKaunas.addAll(1, housesInVilnius);
        listHouses(housesInKaunas);
    }

    public static void main(String... args) {
        Locale.setDefault(new Locale("LT"));
        new HouseTest().run();
        Ks.oun("House test done");
    }
}
