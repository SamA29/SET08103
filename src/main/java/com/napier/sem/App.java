package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

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

        // Connect to database
        a.connect();

        // Produce a report of all countries in the world organised by largest population to smallest
        ArrayList<Country> allCountries = new ArrayList<>();
        allCountries = a.getAllCountriesInWorld();

        // Display results
        System.out.println("Report on all the countries in the world organised by largest population to smallest.");
        a.displayCountries(allCountries);

        // Disconnect from database
        a.disconnect();
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

    /**
     * Returns all countries in the world, organised by the population from largest to smallest
     * @return  list of Country objects
     * @author Pablo Sanchez
     */
    public ArrayList<Country> getAllCountriesInWorld() {
        ArrayList<Country> allCountries= new ArrayList<>(); // array to store all countries

        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Code, country.Name, Continent, Region, country.Population, city.Name AS 'Capital'"
                            + "  FROM country LEFT JOIN city ON country.Capital = city.ID"
                            + " ORDER BY Population DESC";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            // while there are new rows in the result set, keep creating new country objects
            while (rset.next())
            {
                Country c = new Country();
                c.setCode(rset.getString("code"));
                c.setName(rset.getString("name"));
                c.setPopulation(Integer.parseInt(rset.getString("population")));
                c.setContinent(rset.getString("continent"));
                c.setRegion(rset.getString("region"));
                c.setCapital(rset.getString("capital"));
                allCountries.add(c);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country data");
            return null;
        }
        return allCountries;
    }

    /**
     * Displays a list of countries
     * @param countryList
     * @author Pablo Sanchez
     */
    public void displayCountries(ArrayList<Country> countryList) {
        String h1 = "Code", h2 = "Name", h3 = "Continent", h4 = "Region", h5 = "Population", h6 = "Capital";
        // print a header
        System.out.println(String.format("%-4s %-44s %-14s %-25s %-10s %-34s", "Code", "Name", "Continent", "Region", "Population", "Capital"));
        // print details of all countries in the list
        for(Country c : countryList) {
            String country = String.format("%-4s %-44s %-14s %-25s %-10d %-34s",
                    c.getCode(), c.getName(), c.getContinent(), c.getRegion(), c.getPopulation(), c.getCapital());
            System.out.println(country);
        }
    }

    /**
     * Create a Country object based on its code
     * @param code  Country code
     * @return      Country object created
     * @author Pablo Sanchez
     */
    public Country getCountry(String code)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT code, name, population "
                            + "FROM world.country "
                            + "WHERE Code = " + code;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new country if valid.
            // Check one is returned
            if (rset.next())
            {
                Country ctry = new Country();
                ctry.setCode(rset.getString("code"));
                ctry.setName(rset.getString("name"));
                ctry.setPopulation(Integer.parseInt(rset.getString("population")));
                return ctry;
            }
            else
                return null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country data");
            return null;
        }
    }

    /**
     * Display the country object's data
     * @param c  Country object to display
     * @author Pablo Sanchez
     */
    public void displayCountry(Country c)
    {
        if (c != null)
        {
            System.out.println(
                    c.getCode() + " "
                            + c.getName() + " "
                            + c.getPopulation() + "\n");
        }
    }
}