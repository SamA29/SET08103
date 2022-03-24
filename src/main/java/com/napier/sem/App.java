package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

import static com.napier.sem.PrintReport.*;


/**
 *  Created by Group 7: Pablo Sanchez, Alex Vazquez, Sam Alman, Valentina Kerecanina
 *  Main entry point of application
 *  Last Modification: 06/03/2022
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
         * @author Pablo Sanchez
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
        int n = 5;
        ArrayList<Country> topNInWorld = reports.getTopNCountriesInWorld(n);
        System.out.println("\n\nReport of top " + n + " populated countries in the world");
        displayTopCountries(topNInWorld);

        // Produce a report of top N populated countries in a continent
        int nTopPopCont = 5;
        String continentTopPop = "Europe";
        ArrayList<Country> topNInContinent = reports.getTopNCountriesInContinent(continentTopPop, nTopPopCont);
        System.out.println("\n\nReport of top " + nTopPopCont + " populated countries in " + continentTopPop);
        displayTopCountries(topNInContinent);

        // Produce a report of top N populated countries in a region
        int nTopPopReg = 5;
        String regionTopPop = "Baltic Countries";
        ArrayList<Country> topNInRegion = reports.getTopNCountriesInRegion(regionTopPop, nTopPopReg);
        System.out.println("\n\nReport of top " + nTopPopReg + " populated countries in " + regionTopPop);
        displayTopCountries(topNInRegion);


        /*
         *    *********************CITY  REPORTS*********************
         * @author Pablo Sanchez, Alejandro Vazquez
         */

        ArrayList<City> cityReports = new ArrayList<City>();
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

        // Produce a report of the top N populated cities in the world where N is provided by the user.
        cityReports = reports.getNCitiesInWorld(limit);
        System.out.println("\n\nReport of the top " + limit + " populated cities in the world");
        displayTopCities(cityReports);

        // Produce a report of the top N populated cities in the continent where N is provided by the user.
        cityReports = reports.getNCitiesInContinent(limit, continentName);
        System.out.println("\n\nReport of the top " + limit + " populated cities on the continent: " + continentName);
        displayTopCities(cityReports);

        // Produce a report of the top N populated cities in the region where N is provided by the user.
        cityReports = reports.getNCitiesInRegion(limit, regionName);
        System.out.println("\n\nReport of the top " + limit + " populated cities in the region: "+regionName);
        displayTopCities(cityReports);

        // Produce a report of the top N populated cities in the country where N is provided by the user.
        cityReports = reports.getNCitiesInCountry(limit, countryName);
        System.out.println("\n\nReport of the top " + limit + " populated cities in the country: " + countryName);
        displayTopCities(cityReports);

        // Produce a report of the top N populated cities in the district where N is provided by the user.
        cityReports = reports.getNCitiesInDistrict(limit, districtName);
        System.out.println("\n\nReport of the top " + limit + " populated cities in the district: " + districtName);
        displayTopCities(cityReports);


        /*
         *    *********************CAPITAL CITIES REPORTS*********************
         * @author Sam Alman
         */

        //Produce a report of the capital cities in the world organised by largest population to smallest.
        System.out.println("\n\nReport of all capital cities");
        cityReports = reports.getAllCapitalCities();
        reports.displayCapitals(cityReports);

        String continentCapCity = "Europe";
        //Produce a report of the capital cities in a continent organised by largest population to smallest
        System.out.println("\n\nReport of all capital cities");
        cityReports = reports.getAllCapitalCitiesInContinent(continentCapCity);
        reports.displayCapitals(cityReports);

        String regionCapCity = "Middle East";
        //Produce a report of the capital cities in a region organised by largest to smallest.
        System.out.println("\n\nReport of all capital cities");
        cityReports = reports.getAllCapitalCitiesInRegion(regionCapCity);
        reports.displayCapitals(cityReports);
        // Disconnect from database
        reports.disconnect();
    }


}