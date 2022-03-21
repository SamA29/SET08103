package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;


import static com.napier.sem.PrintReport.*;
import static com.napier.sem.CountryQuery.*;

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
        // Create new Application
        App a = new App();
        CountryQuery cq = new CountryQuery();

        // Connect to database
        cq.connect();
        // Produce a report of all countries in the world organised by largest population to smallest
        ArrayList<Country> allCountries = cq.getAllCountriesInWorld();
        System.out.println("Report on all the countries in the world organised by largest population to smallest.");
        displayCountries(allCountries); //Display results

        // Produce a report of all countries in a continent organised by largest population to smallest
        String continent = "Europe";
        ArrayList<Country> continentCountries = cq.getAllCountriesInContinent(continent);
        System.out.println("\n\nReport on all the countries in " + continent + " organised by largest population to smallest.");
        displayCountries(continentCountries);   //Display results

        // Produce a report of all countries in a region organised by largest population to smallest
        String region = "Nordic Countries";
        ArrayList<Country> regionCountries = cq.getAllCountriesInRegion(region);
        System.out.println("\n\nReport on all the countries in " + region + " organised by largest population to smallest.");
        displayCountries(regionCountries);   //Display results

        // Produce a report of top N populated countries in the world
        int n = 5;
        ArrayList<Country> topNInWorld = cq.getTopNCountriesInWorld(n);
        System.out.println("\n\nReport of top " + n + " populated countries in the world");
        displayTopCountries(topNInWorld);

        // Produce a report of top N populated countries in a continent
        int nTopPopCont = 5;
        String continentTopPop = "Europe";
        ArrayList<Country> topNInContinent = cq.getTopNCountriesInContinent(continentTopPop, nTopPopCont);
        System.out.println("\n\nReport of top " + nTopPopCont + " populated countries in " + continentTopPop);
        displayTopCountries(topNInContinent);

        // Produce a report of top N populated countries in a region
        int nTopPopReg = 5;
        String regionTopPop = "Baltic Countries";
        ArrayList<Country> topNInRegion = cq.getTopNCountriesInRegion(regionTopPop, nTopPopReg);
        System.out.println("\n\nReport of top " + nTopPopReg + " populated countries in " + regionTopPop);
        displayTopCountries(topNInRegion);

        // Disconnect from database
        cq.disconnect();
    }

    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect() {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            //Message to user
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            }
            //If does not work
            catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            //If does not work
            catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
                System.out.println("Disconnecting...");
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

}