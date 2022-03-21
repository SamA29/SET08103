package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

/**
 * Class created to do the country queries
 * Last date of modification 17/03/2022
 * @author Pablo Sanchez
 */
public class CountryQuery
{
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
     */
    public ArrayList<Country> getAllCountriesInWorld() {
        // query to get all countries in the world
        String query =
                "SELECT Code, country.Name, Continent, Region, country.Population, city.Name AS 'Capital'"
                        + "  FROM country LEFT JOIN city ON country.Capital = city.ID"
                        + " ORDER BY Population DESC";
        // execute the query
        ArrayList<Country> allCountries= processCountryQuery(query);
        return allCountries;
    }

    /**
     * Returns top N populated countries in the world
     * @param n number of countries to return
     * @return  list of Country objects
     */
    public ArrayList<Country> getTopNCountriesInWorld(int n) {
        // query to get all countries in the world
        String query =
                "SELECT Code, country.Name, Continent, Region, country.Population, city.Name AS 'Capital'"
                        + "  FROM country LEFT JOIN city ON country.Capital = city.ID"
                        + " ORDER BY Population DESC "
                        + " LIMIT " + n;
        // execute the query
        ArrayList<Country> allCountries= processCountryQuery(query);
        if(allCountries.size() < n) {
            System.out.println("Not enough countries for this ranking.");
        }
        return allCountries;
    }

    /**
     * Returns all countries in the continent specified, organised by the population from largest to smallest
     * @param continentName
     * @return List of countries in the continent
     */
    public ArrayList<Country> getAllCountriesInContinent(String continentName) {
        String query = "SELECT Code, country.Name, Continent, Region, country.Population, city.Name AS 'Capital' " +
                "FROM country LEFT JOIN city ON country.Capital = city.ID " +
                "WHERE Continent = '" + continentName + "' " +
                "ORDER BY Population DESC";
        ArrayList<Country> countriesInContinent = processCountryQuery(query);
        if (countriesInContinent.isEmpty()) {
            System.out.println("Invalid continent specified.");
            return null;
        }
        return countriesInContinent;
    }

    /**
     * Returns top N populated countries in the continent specified
     * @param continentName continent to extract
     * @param limit number of countries to extract
     * @return  list of Country objects
     */
    public ArrayList<Country> getTopNCountriesInContinent(String continentName, int limit) {
        String query = "SELECT Code, country.Name, Continent, Region, country.Population, city.Name AS 'Capital' " +
                "FROM country LEFT JOIN city ON country.Capital = city.ID " +
                "WHERE Continent = '" + continentName + "' " +
                "ORDER BY Population DESC " +
                "LIMIT " + limit;
        ArrayList<Country> countriesInContinent = processCountryQuery(query);
        if (countriesInContinent.isEmpty()) {
            System.out.println("Invalid continent specified.");
            return null;
        }
        if(countriesInContinent.size() < limit) {
            System.out.println("Not enough countries in continent for this ranking. Returning all in continent");
        }
        return countriesInContinent;
    }

    /**
     * Returns a list of all countries in the region specified
     * @param regionName    region to extract
     * @return  list of Country objects
     */
    public ArrayList<Country> getAllCountriesInRegion(String regionName) {
        String query = "SELECT Code, country.Name, Continent, Region, country.Population, city.Name AS 'Capital' " +
                "FROM country LEFT JOIN city ON country.Capital = city.ID " +
                "WHERE Region = '" + regionName + "' " +
                "ORDER BY Population DESC";
        ArrayList<Country> countriesInRegion = processCountryQuery(query);
        if (countriesInRegion.isEmpty()) {
            System.out.println("Invalid region specified.");
            return null;
        }
        return countriesInRegion;
    }

    /**
     * Returns top N populated countries in the region specified
     * @param regionName    region to extract
     * @param n     countries to extract
     * @return  list of Country objects
     */
    public ArrayList<Country> getTopNCountriesInRegion(String regionName, int n) {
        String query = "SELECT Code, country.Name, Continent, Region, country.Population, city.Name AS 'Capital' " +
                "FROM country LEFT JOIN city ON country.Capital = city.ID " +
                "WHERE Region = '" + regionName + "' " +
                "ORDER BY Population DESC " +
                "LIMIT " + n;
        ArrayList<Country> countriesInRegion = processCountryQuery(query);
        if (countriesInRegion.isEmpty()) {
            System.out.println("Invalid region specified.");
            return null;
        }
        if(countriesInRegion.size() < n) {
            System.out.println("Not enough countries in region for this ranking. Returning all countries in region");
        }
        return countriesInRegion;
    }

    /**
     * Processes an SQL query to get a list of countries
     * @param query Query to process
     * @return  a list of Country objects
     */
    public ArrayList<Country> processCountryQuery(String query) {
        ArrayList<Country> countries = new ArrayList<>();
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(query);

            //Extract country information
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
                countries.add(c);
            }
            return countries;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country data");
            return null;
        }
    }
}