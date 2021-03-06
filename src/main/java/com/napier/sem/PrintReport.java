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

    /**
     * Displays a list of capital cities
     * @param cityList   capital cities to display
     */
    public static void displayCapitalCities(ArrayList <CapitalCity> cityList) {
        if (cityList == null || cityList.isEmpty()) {
            System.out.println("No cities to print");
        } else {
            // print a header
            System.out.println(String.format("%-25s %-25s", "Name", "Population"));
            // print details of all countries in the list
            for (CapitalCity c: cityList) {
                if (c == null) {
                    continue;
                }
                String country = String.format("%-25s %-25s ", c.getName(), c.getPopulation());
                System.out.println(country);
            }
        }
    }
    /**
     * Displays a list of populations
     * @param populations   list of Population objects
     * @param typeOfQuery
     */
    public static void displayPopulations(ArrayList<Population> populations, String typeOfQuery){
        if(typeOfQuery == null) {
            System.out.println("Invalid type of query specified to print header");
        }
        else {
            System.out.println(String.format("%-24s %-24s %-14s %-24s %-24s %-24s",  typeOfQuery , "Population", "City Population", "City Population %", "Non City Population", "Non City Population %"));
        }

        if(populations == null || populations.isEmpty()) {
            System.out.println("No populations to print");
        }
        else {
            for (Population population : populations) {
                if(population != null) {
                    System.out.println(String.format("%-24s %-24s %-14s %-24s %-24s %-24s",
                            population.getName(), population.getPopulation(), population.getCityPopulation(), population.getCityPopulationPercent(), population.getNotCityPopulation(), population.getNonCityPopulationPercent()));
                }
            }
        }
    }

    /**
     * Displays a report on languages
     * @param language   list of Language objects
     */
    public static void displayLanguage(ArrayList<Language> language)
    {
        // Check language is not null
        if (language == null)
        {
            System.out.println("No languages information available from the database!");
            return;
        }

        // Print header
        System.out.println(String.format("%-24s %-24s %-24s", "language", "Population", "Percentage"));
        // Loop over all languages in the list
        for (Language cnt : language)
        {
            if (cnt == null)
                continue;

            String cnt_string =
                    String.format("%-24s %-24s %-24s",
                            cnt.getLanguage(), cnt.getPopulation(), cnt.getPercentage());
            System.out.println(cnt_string);
        }
    }

    /**
     * Helper method to print city population
     * @param cities
     */
    public static void printCityPopulation(ArrayList<City> cities) {
        if (cities == null) {
            System.out.println("No city found");
            return;
        } else {
            // Print header
            System.out.println("Population of cities: ");
            for (City c : cities) {
                if (c == null) {
                    continue;
                }
                String c_string = c.getName() + " " + c.getPopulation();
                System.out.println(c_string);
            }
            System.out.println("\n");
        }
    }

    /**
     * Helper method to print country population
     * @param countries
     */
    public static void printCountryPopulation(Country countries)
    {
        if (countries == null)
        {
            System.out.println("No country found");
            return;
        } else
        {
            System.out.println("Population of country: ");
            System.out.println(countries.getName() + " - " + countries.getPopulation());
            System.out.println("\n");
        }
    }

    /**
     * Helper method to print continent population
     * @param population
     */
    public static void printContinentPopulation(Population population)
    {
        if (population == null)
        {
            System.out.println("No continent found");
            return;
        } else
        {
            System.out.println("Population of continent: ");
            System.out.println(population.getName() + " - " + population.getPopulation());
            System.out.println("\n");
        }
    }

    /**
     * Helper method to print region population
     * @param population
     */
    public static void printRegionPopulation(ArrayList<Population> population)
    {
        if (population == null)
        {
            System.out.println("No city found");
            return;
        } else
        {
            // Print header
            Population output;
            System.out.println("Population of region: ");
            output = population.get(population.size()-1);

            String c_string = output.getName() + " " + output.getPopulation();
            System.out.println(c_string);
            System.out.println("\n");
        }
    }

    /**
     * Helper method to print district population
     * @param population
     */
    public static void printDistrictPopulation(Population population)
    {
        if (population == null)
        {
            System.out.println("No district found");
            return;
        } else
        {
            System.out.println("Population of district: ");
            System.out.println(population.getName() + " - " + population.getPopulation());
            System.out.println("\n");
        }
    }
}
