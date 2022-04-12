package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static com.napier.sem.PrintReport.*;


/**
 *  Created by Group 7: Pablo Sanchez, Alex Vazquez, Sam Alman, Valentina Kerecanina
 *  Main entry point of application
 *  Last Modification: 11/042
 *  /2022
 */
public class App {
    /**
     * Main function
     */
    public static void main(String[] args) throws SQLException {

        Reports reports = new Reports();
        // Connect to database
        if(args.length < 1){
            reports.connect("localhost:33060", 30000);
        }else{
            reports.connect(args[0], Integer.parseInt(args[1]));
        }

        /*
         *    *********************COUNTRY REPORTS*********************
         *  @author Pablo Sanchez
         */

        // Produce a report of all countries in the world organised by largest population to smallest
        ArrayList<Country> allCountries = reports.getAllCountriesInWorld();
        System.out.println("Report on all the countries in the world organised by largest population to smallest.");
        displayCountries(allCountries); //Display results

        // Produce a report of all countries in a continent organised by largest population to smallest
        String continent = "Europe";
        ArrayList<Country> continentCountries = reports.getAllCountriesInContinent(continent);
        System.out.println("\n\nReport on all the countries in " + continent + " organised by largest population to smallest.");
        displayCountries(continentCountries);   //Display results

        // Produce a report of all countries in a region organised by largest population to smallest
        String region = "Nordic Countries";
        ArrayList<Country> regionCountries = reports.getAllCountriesInRegion(region);
        System.out.println("\n\nReport on all the countries in " + region + " organised by largest population to smallest.");
        displayCountries(regionCountries);   //Display results

        // Produce a report of top N populated countries in the world
        Scanner limitPopulatedCountriesWorld = new Scanner(System.in);
        System.out.println("\nPlease input the limit for the most populated countries");
        //Try - catch to pass GitHub Action
        try
        {
            String nText =  limitPopulatedCountriesWorld.nextLine();
            int n;
            if(nText.isEmpty())
            {
                n = 3;
            }
            else
            {
                n = Integer.parseInt(nText);
            }
            ArrayList<Country> topNInWorld = reports.getTopNCountriesInWorld(n);
            System.out.println("\n\nReport of top " + n + " populated countries in the world");
            displayTopCountries(topNInWorld);
        }
        catch(NoSuchElementException e)
        {
            int n = 3;
            ArrayList<Country> topNInWorld = reports.getTopNCountriesInWorld(n);
            System.out.println("\n\nReport of top " + n + " populated countries in the world");
            displayTopCountries(topNInWorld);
        }

        // Produce a report of top N populated countries in a continent where N is provided by the user.
        Scanner limitPopulatedC = new Scanner(System.in);
        System.out.println("\nPlease input the limit fot the most populated countries in Europe");
        //Try - catch to pass GitHub Action
        try
        {
            String nTopPopContText = limitPopulatedC.nextLine();
            int nTopPopCont;
            if(nTopPopContText.isEmpty())
            {
                nTopPopCont = 3;
            }
            else
            {
                nTopPopCont = Integer.parseInt(nTopPopContText);
            }
            String continentTopPop = "Europe";
            ArrayList<Country> topNInContinent = reports.getTopNCountriesInContinent(continentTopPop, nTopPopCont);
            System.out.println("\n\nReport of top " + nTopPopCont + " populated countries in " + continentTopPop);
            displayTopCountries(topNInContinent);
        }
        catch (NoSuchElementException e)
        {
            int nTopPopCont = 3;
            String continentTopPop = "Europe";
            ArrayList<Country> topNInContinent = reports.getTopNCountriesInContinent(continentTopPop, nTopPopCont);
            System.out.println("\n\nReport of top " + nTopPopCont + " populated countries in " + continentTopPop);
            displayTopCountries(topNInContinent);
        }

        // Produce a report of top N populated countries in a region
        Scanner limitPopulatedCountriesRegion = new Scanner(System.in);
        System.out.println("\nPlease input the limit for the most populated countries in the Baltic Countries");
        //Try - catch to pass GitHub Action
        try
        {
            String nTopPopRegText = limitPopulatedCountriesRegion.nextLine();
            int nTopPopReg;
            if(nTopPopRegText.isEmpty())
            {
                nTopPopReg = 3;
            }
            else
            {
                nTopPopReg = Integer.parseInt(nTopPopRegText);
            }
            String regionTopPop = "Baltic Countries";
            ArrayList<Country> topNInRegion = reports.getTopNCountriesInRegion(regionTopPop, nTopPopReg);
            System.out.println("\n\nReport of top " + nTopPopReg + " populated countries in " + regionTopPop);
            displayTopCountries(topNInRegion);
        }
        catch (NoSuchElementException e)
        {
            int nTopPopReg = 3;
            String regionTopPop = "Baltic Countries";
            ArrayList<Country> topNInRegion = reports.getTopNCountriesInRegion(regionTopPop, nTopPopReg);
            System.out.println("\n\nReport of top " + nTopPopReg + " populated countries in " + regionTopPop);
            displayTopCountries(topNInRegion);
        }



        /*
         *    *********************CITY  REPORTS*********************
         * @author Pablo Sanchez, Alejandro Vazquez
         */

        ArrayList<City> cityReports;
        int limit = 5;

        // Produce a report of all the cities in the world organised by largest population to smallest.
        cityReports = reports.getAllCitiesInWorld();
        System.out.println("\n\nReport of all the cities in the world organised by largest population to smallest.");
        displayCities(cityReports);

        String continentName = "Africa";
        // Produce a report of all the cities in a continent organised by largest population to smallest.
        cityReports = reports.getAllCitiesInContinent(continentName);
        System.out.println("\n\nReport of all the cities on the continent "+ continentName +" organised by largest population to smallest.");
        displayCities(cityReports);

        String regionName = "Middle East";
        // Produce a report of all the cities in a region organised by largest population to smallest.
        cityReports = reports.getAllCitiesInRegion(regionName);
        System.out.println("\n\nReport of all the cities in the region " + regionName+ " organised by largest population to smallest.");
        displayCities(cityReports);

        String countryName = "France";
        // Produce a report of all the cities in a country organised by largest population to smallest.
        cityReports = reports.getAllCitiesInCountry(countryName);
        System.out.println("\n\nReport of all the cities in a country " + countryName+ " organised by largest population to smallest.");
        displayCities(cityReports);

        String districtName = "Île-de-France";
        // Produce a report of all the cities in a district organised by largest population to smallest.
        cityReports = reports.getAllCitiesInDistrict(districtName);
        System.out.println("\n\nReport of all the cities in the district  " + districtName + " organised by largest population to smallest. ");
        displayCities(cityReports);

        // Produce a report of the top N populated cities in the world where N is provided by the user where N is provided by the user.
        Scanner limitPopulatedCityWorld = new Scanner(System.in);
        System.out.println("\nPlease input the limit for the most populated cities in the world:");
        //Try - catch to pass GitHub Action
        try
        {
            limit = limitPopulatedCityWorld.nextInt();
            if(limit == 0)
            {
                limit = 3;
            }
            cityReports = reports.getNCitiesInWorld(limit);
            System.out.println("\n\nReport of the top " + limit + " populated cities in the world");
            displayTopCities(cityReports);
        }
        catch (NoSuchElementException e)
        {
            cityReports = reports.getNCitiesInWorld(limit);
            System.out.println("\n\nReport of the top " + limit + " populated cities in the world");
            displayTopCities(cityReports);
        }


        // Produce a report of the top N populated cities in the continent where N is provided by the user where N is provided by the user.
        Scanner limitPopulatedCityContinent = new Scanner(System.in);
        System.out.println("\nPlease input the limit for the most populated cities in Africa:");
        //Try - catch to pass GitHub Action
        try
        {
            limit = limitPopulatedCityContinent.nextInt();
            if(limit == 0)
            {
                limit = 3;
            }
            cityReports = reports.getNCitiesInContinent(limit, continentName);
            System.out.println("\n\nReport of the top " + limit + " populated cities on the continent: " + continentName);
            displayTopCities(cityReports);
        }
        catch (NoSuchElementException e)
        {
            cityReports = reports.getNCitiesInContinent(limit, continentName);
            System.out.println("\n\nReport of the top " + limit + " populated cities on the continent: " + continentName);
            displayTopCities(cityReports);
        }

        // Produce a report of the top N populated cities in the region where N is provided by the user where N is provided by the user.
        Scanner limitPopulatedCityRegion = new Scanner(System.in);
        System.out.println("\nPlease input the limit for the most populated cities in Middle East:");
        //Try - catch to pass GitHub Action
        try
        {
            limit = limitPopulatedCityRegion.nextInt();
            if(limit == 0)
            {
                limit = 3;
            }
            cityReports = reports.getNCitiesInRegion(limit, regionName);
            System.out.println("\n\nReport of the top " + limit + " populated cities in the region: "+regionName);
            displayTopCities(cityReports);
        }
        catch (NoSuchElementException e)
        {
            cityReports = reports.getNCitiesInRegion(limit, regionName);
            System.out.println("\n\nReport of the top " + limit + " populated cities in the region: "+regionName);
            displayTopCities(cityReports);
        }


        // Produce a report of the top N populated cities in the country where N is provided by the user where N is provided by the user.
        Scanner limitPopulatedCityCountry = new Scanner(System.in);
        System.out.println("\nPlease input the limit for the most populated cities in France:");
        //Try - catch to pass GitHub Action
        try
        {
            limit = limitPopulatedCityCountry.nextInt();
            if(limit == 0)
            {
                limit = 3;
            }
            cityReports = reports.getNCitiesInCountry(limit, countryName);
            System.out.println("\n\nReport of the top " + limit + " populated cities in the country: " + countryName);
            displayTopCities(cityReports);
        }
        catch (NoSuchElementException e)
        {
            cityReports = reports.getNCitiesInCountry(limit, countryName);
            System.out.println("\n\nReport of the top " + limit + " populated cities in the country: " + countryName);
            displayTopCities(cityReports);
        }


        // Produce a report of the top N populated cities in the district where N is provided by the user where N is provided by the user.
        Scanner limitPopulatedCityDistrict = new Scanner(System.in);
        System.out.println("\nPlease input the limit for the most populated cities in Ile-De-France:");
        //Try - catch to pass GitHub Action
        try
        {
            limit = limitPopulatedCityDistrict.nextInt();
            if(limit == 0)
            {
                limit = 3;
            }
            cityReports = reports.getNCitiesInDistrict(limit, districtName);
            System.out.println("\n\nReport of the top " + limit + " populated cities in the district: " + districtName);
            displayTopCities(cityReports);
        }
        catch (NoSuchElementException e)
        {
            cityReports = reports.getNCitiesInDistrict(limit, districtName);
            System.out.println("\n\nReport of the top " + limit + " populated cities in the district: " + districtName);
            displayTopCities(cityReports);
        }



        /*
         *    *********************CAPITAL CITIES REPORTS*********************
         * @author Sam Alman
         */

        //Produce a report of the capital cities in the world organised by largest population to smallest.
        System.out.println("\n\nReport of all capital cities organised by largest population to smallest");
        cityReports = reports.getAllCapitalCities();
        reports.displayCapitals(cityReports);

        String continentCapCity = "Europe";
        //Produce a report of the capital cities in a continent organised by largest population to smallest
        System.out.println("\n\nReport of all capital cities in a continent organised by largest population to smallest");
        cityReports = reports.getAllCapitalCitiesInContinent(continentCapCity);
        reports.displayCapitals(cityReports);

        String regionCapCity = "Middle East";
        //Produce a report of the capital cities in a region organised by largest to smallest.
        System.out.println("\n\nReport of all capital cities in a region organised by largest to smallest.");
        cityReports = reports.getAllCapitalCitiesInRegion(regionCapCity);
        reports.displayCapitals(cityReports);

        //Produce a report of the top N populated capital cities in a region where N is provided by the user
        Scanner limitPopulatedCapitalCityRegion = new Scanner(System.in);
        System.out.println("\nPlease input the limit for the most populated capital cities in Southern Europe:");
        //Try - catch to pass GitHub Action
        try
        {
            limit = limitPopulatedCapitalCityRegion.nextInt();
            if(limit == 0)
            {
                limit = 3;
            }
            ArrayList <CapitalCity> getNCapitalCitiesInRegion = reports.getNCapitalCitiesInRegion("Southern Europe", limit);
            System.out.println("Report on the top " + limit + " populated capital cities in Southern Europe");
            displayCapitalCities(getNCapitalCitiesInRegion);
        }
        catch (NoSuchElementException e)
        {
            ArrayList <CapitalCity> getNCapitalCitiesInRegion = reports.getNCapitalCitiesInRegion("Southern Europe", limit);
            System.out.println("Report on the top " + limit + " populated capital cities in Southern Europe");
            displayCapitalCities(getNCapitalCitiesInRegion);
        }


        // Produce a report of the top N populated capital cities
        Scanner limitPopulatedCapitalCityWorld = new Scanner(System.in);
        System.out.println("\nPlease input the limit for the most populated capital cities in the world:");
        //Try - catch to pass GitHub Action
        try
        {
            limit = limitPopulatedCapitalCityWorld.nextInt();
            if(limit == 0)
            {
                limit = 3;
            }
            ArrayList <CapitalCity> getNCapitalCitiesPopulation = reports.getNCapitalCitiesPopulation(limit);
            System.out.println("Report on the top " + limit + " populated capital cities in the world");
            displayCapitalCities(getNCapitalCitiesPopulation);
        }
        catch (NoSuchElementException e)
        {
            ArrayList <CapitalCity> getNCapitalCitiesPopulation = reports.getNCapitalCitiesPopulation(limit);
            System.out.println("Report on the top " + limit + " populated capital cities in the world");
            displayCapitalCities(getNCapitalCitiesPopulation);
        }


        // Produce a report of the top N populated capital cities in a continent
        Scanner limitPopulatedCapitalCityContinent = new Scanner(System.in);
        System.out.println("\nPlease input the limit for the most populated capital cities in Asia:");
        //Try - catch to pass GitHub Action
        try
        {
            limit = limitPopulatedCapitalCityContinent.nextInt();
            if(limit == 0)
            {
                limit = 3;
            }
            ArrayList <CapitalCity> getNCapitalCitiesInContinent = reports.getNCapitalCitiesInContinent("Asia", limit);
            System.out.println("Report on top " + limit + " populated capital cities in a continent");
            displayCapitalCities(getNCapitalCitiesInContinent);
        }
        catch (NoSuchElementException e)
        {
            ArrayList <CapitalCity> getNCapitalCitiesInContinent = reports.getNCapitalCitiesInContinent("Asia", limit);
            System.out.println("Report on top " + limit + " populated capital cities in a continent");
            displayCapitalCities(getNCapitalCitiesInContinent);
        }


        /*
         *    *********************POPULATION AND REST OF REPORTS*********************
         * @author Pablo Sanchez
         */
        // Call population by country
        ArrayList<Population> allPopulationsCountry;
        allPopulationsCountry = reports.getPopulationInCityByCountry();
        System.out.println("\nA report of the population of the people living in cities and outside cities in each country");
        displayPopulations(allPopulationsCountry, "Country");
        System.out.println("");
        System.out.println("");


        // Call population by continent
        ArrayList<Population> allPopulationsContinent;
        allPopulationsContinent = reports.getPopulationInCityByContinent();
        System.out.println("\nA report of the population of the people living in cities and outside cities in each continent");
        displayPopulations(allPopulationsContinent, "Continent");
        System.out.println("");
        System.out.println("");

        // Call population by region
        ArrayList<Population> allPopulationsRegion;
        allPopulationsRegion = reports.getPopulationInCityByRegion();
        System.out.println("\nA report of the population of the people living in cities and outside cities in each region");
        displayPopulations(allPopulationsRegion, "Region");
        System.out.println("");
        System.out.println("");

        // Call the language query
        reports.getLanguage();

        // Disconnect from database
        reports.disconnect();

    }


}