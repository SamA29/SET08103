package com.napier.sem;

import java.util.ArrayList;

/**
 * Class created to print reports for Countries
 * @author Pablo Sanchez
 * Last date of modification 21/03/2022
 */
public class PrintReport {
    /**
     * Displays a list of countries
     * @param listOfCountries
     */
    public static void displayCountries(ArrayList<Country> listOfCountries) {
        if (listOfCountries == null || listOfCountries.isEmpty()) {
            System.out.println("No countries to print");
        }
        else {
            // Print titles
            System.out.println(String.format("%-5s %-44s %-14s %-25s %-10s %-34s", "Code", "Name", "Continent", "Region", "Population", "Capital"));
            // print details of all countries in the list
            for (Country c : listOfCountries) {
                if(c == null) {
                    continue;
                }
                String country = String.format("%-4s %-44s %-14s %-25s %-10d %-34s",
                        c.getCode(), c.getName(), c.getContinent(), c.getRegion(), c.getPopulation(), c.getCapital());
                System.out.println(country);
            }
        }
    }

    /**
     * Display list of top countries
     * @param listOfCountries
     */
    public static void displayTopCountries(ArrayList<Country> listOfCountries) {
        if (listOfCountries == null || listOfCountries.isEmpty()) {
            System.out.println("No countries to print");
        }
        else {
            // print titles
            System.out.println(String.format("%4s %-4s %-44s %-14s %-25s %-10s %-34s", "Num", "Code", "Name", "Continent", "Region", "Population", "Capital"));
            // print details of all countries in the list
            int counter = 1;
            for (Country c : listOfCountries) {
                if(c == null) {
                    continue;
                }
                String country = String.format("%2s. %-4s %-44s %-14s %-25s %-10d %-34s", counter,
                        c.getCode(), c.getName(), c.getContinent(), c.getRegion(), c.getPopulation(), c.getCapital());
                System.out.println(country);
                counter++;
            }
        }
    }

    /**
     * Displays a list of cities
     * @param listOfCities countries to display
     */
    public static void displayCities(ArrayList <City> listOfCities) {
        if (listOfCities == null || listOfCities.isEmpty()) {
            System.out.println("No cities to print");
        } else {
            // print a header
            System.out.println(String.format("%-20s   %-48s   %-47s   %-25s", "Name", "Country", "District", "Population"));
            // print details of all countries in the list
            for (City c: listOfCities) {
                if (c == null) {
                    continue;
                }
                String city = String.format("%-20s   %-50s   %-47s   %-25d",
                        c.getName(), c.getCountry(), c.getDistrict(), c.getPopulation());
                System.out.println(city);
            }
        }
    }
    /**
     * Displays a list of cities
     * @param listOfCities countries to display
     */
    public static void displayCitiesWorld(ArrayList <City> listOfCities) {
        if (listOfCities == null || listOfCities.isEmpty()) {
            System.out.println("No cities to print");
        } else {
            // print a header
            System.out.println(String.format("%3s. %-20s   %-50s   %-47s   %-25s", "Id", "Name", "Country", "District", "Population"));
            // print details of all countries in the list
            for (City c: listOfCities) {
                if (c == null) {
                    continue;
                }
                String city = String.format("%2d. %-20s   %-50s   %-47s   %-25d", c.getId(),
                        c.getName(), c.getCountry(), c.getDistrict(), c.getPopulation());
                System.out.println(city);
            }
        }
    }

    /**
     * Displays a list of cities with numbering
     * @param listOfCities   cities to display
     */
    public static void displayTopCities(ArrayList <City> listOfCities) {
        int counter = 1;
        if (listOfCities == null || listOfCities.isEmpty()) {
            System.out.println("No cities to print");
        } else {
            // print a header
            System.out.println(String.format("%3s. %-20s   %-50s   %-47s   %-25s", "Num", "Name", "Country", "District", "Population"));
            // print details of all countries in the list
            for (City c: listOfCities) {
                if (c == null) {
                    continue;
                }

                String city = String.format("%2d. %-20s   %-50s   %-47s   %-25d", counter,
                        c.getName(), c.getCountry(), c.getDistrict(), c.getPopulation());
                System.out.println(city);
                counter++;
            }
        }
    }
}
