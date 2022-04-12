package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;


/**
 *  Class containing the methods for the reports
 *  Last Modification: 24/03/2022
 *  @author Pablo Sanchez
 */
public class Reports {
    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect(String location, int delay) {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(delay);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location
                                + "/world?allowPublicKeyRetrieval=true&useSSL=false",
                        "root", "example");
                System.out.println("Successfully connected! Please wait");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " +                                  Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
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
                System.out.println("\nDisconnecting...");

            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

    /*
     *    *********************COUNTRY REPORTS*********************
     * @author Pablo Sanchez
     */

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
        if(n <= 0) {
            System.out.println("Invalid number specified");
            return null;
        }
        // query to get all countries in the world
        String query =
                "SELECT Code, country.Name, Continent, Region, country.Population, city.Name AS 'Capital'"
                        + "  FROM country LEFT JOIN city ON country.Capital = city.ID"
                        + " ORDER BY Population DESC "
                        + " LIMIT " + n;
        // execute the query
        ArrayList<Country> allCountries= processCountryQuery(query);
        if (allCountries != null) {
            if (allCountries.size() < n) {
                System.out.println("\n\n\nLimit is bigger than number of countries in the list.");
            }
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
        if(countriesInContinent != null) {
            if (countriesInContinent.isEmpty()) {
                System.out.println("Continent not valid");
                return null;
            }
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
        if(countriesInContinent != null) {
            if (countriesInContinent.isEmpty()) {
                System.out.println("Invalid continent specified.");
                return null;
            }
            if (countriesInContinent.size() < limit) {
                System.out.println("\n\n\nNot enough countries in continent for this ranking. Returning all in continent");
            }
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
        if(countriesInRegion != null) {
            if (countriesInRegion.isEmpty()) {
                System.out.println("Invalid region specified.");
                return null;
            }
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
        if(countriesInRegion != null) {
            if (countriesInRegion.isEmpty()) {
                System.out.println("Invalid region specified.");
                return null;
            }
            if (countriesInRegion.size() < n) {
                System.out.println("\n\n\nNot enough countries in region for this ranking. Returning all countries in region");
            }
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


    /*
     *    *********************CITY REPORTS*********************
     * @author Pablo Sanchez, Alejandro Vazquez
     */


    /**
     * returns a list of cities in the world
     * @return a list of cities objects
     */
    public ArrayList <City> getAllCitiesInWorld() {
        //  SELECT Name, CountryCode, District, Population FROM world.city ORDER BY  Population DESC ;
        String query = "SELECT city.Name as 'City',  country.Name as 'Country', District, city.Population as 'Population'" +
                "FROM world.city join world.country on city.CountryCode = country.code " +
                "ORDER BY city.Population DESC ;";
        ArrayList <City> cities = processCityQuery(query);
        return cities;
    }


    /**
     * returns a list of cities on a continent
     * @return a list of cities objects
     */
    public ArrayList <City> getAllCitiesInContinent(String continentName) {
        String query = "SELECT city.Name as 'City',  country.Name as 'Country', District, city.Population as 'Population' " +
                "FROM world.city join world.country on city.CountryCode = country.code " +
                "WHERE country.continent = '" + continentName + "' " +
                "ORDER BY Population DESC;";
        ArrayList <City> cities = processCityQuery(query);
        return cities;
    }


    /**
     * returns a list of cities in a region
     * @return a list of cities objects
     */
    public ArrayList <City> getAllCitiesInRegion(String regionName) {
        String query = "SELECT city.Name as 'City',  country.Name as 'Country', District, city.Population as 'Population' " +
                "FROM world.city join world.country on city.CountryCode = country.code " +
                "WHERE country.Region = '" + regionName +
                "' ORDER BY Population DESC;";
        ArrayList <City> cities = processCityQuery(query);
        return cities;
    }


    /**
     * returns a list of cities in a country
     * @return a list of cities objects
     */
    public ArrayList <City> getAllCitiesInCountry(String countryName) {
        String query = "SELECT city.Name as 'City',  country.Name as 'Country', District, city.Population as 'Population' " +
                "FROM world.city join world.country on city.CountryCode = country.code " +
                "WHERE country.Name = '" + countryName + "' " +
                "ORDER BY Population DESC;";
        ArrayList <City> cities = processCityQuery(query);
        return cities;
    }


    /**
     * returns a list of cities in a district
     * @return a list of cities objects
     */
    public ArrayList <City> getAllCitiesInDistrict(String districtName) {
        String query = "SELECT city.Name as 'city',  country.Name as 'country', District, city.Population " +
                "FROM world.city join world.country on city.CountryCode = country.code " +
                "WHERE city.District = '" + districtName +
                "'ORDER BY city.Population DESC;";
        ArrayList <City> cities = processCityQuery(query);
        return cities;
    }


    /**
     * returns a list of N cities on a continent
     * @param   c number of cities to return
     * @return a list of cities objects
     */
    public ArrayList <City> getNCitiesInWorld(int c) {
        String query = "SELECT city.Name AS 'city', country.Name AS 'country', District, city.Population " +
                "FROM world.city join world.country on city.CountryCode = country.Code " +
                "ORDER BY city.Population DESC LIMIT " + c + ";";
        ArrayList<City> cityList;
        cityList = processCityQuery(query);
        if(cityList == null)
        {
            System.out.println("Null list");
            return null;
        }
        else if (cityList.isEmpty()) {
            System.out.println("Empty list");
            return cityList;
        } else if (cityList.size() < c) {
            System.out.println("\n\n\nNot enough cities in the world for this ranking. Returning as many as there are in world");
        }

        return cityList;
    }


    /**
     * returns a list of N cities on a continent
     * @param continent to search on
     * @param c number of cities to return
     * @return a list of cities objects
     */
    public ArrayList <City> getNCitiesInContinent(int c, String continent) {
        String query = "SELECT city.Name as 'city',  country.Name as 'country', District, city.Population " +
                "FROM world.city join world.country on city.CountryCode = country.code " +
                "WHERE country.continent = '" + continent + "' " +
                "ORDER BY city.Population DESC LIMIT " + c + ";";
        ArrayList<City> cityList;
        cityList = processCityQuery(query);
        if(cityList == null)
        {
            System.out.println("Null list");
            return null;
        }
        else if (cityList.isEmpty()) {
            System.out.println("Empty list");
            return null;
        } else if (cityList.size() < c) {
            System.out.println("\n\n\nNot enough cities in the world for this ranking. Returning as many as there are in world");
        }

        return cityList;
    }


    /**
     * returns a list of N cities on a continent
     * @param  region to search on
     * @param c number of cities to return
     * @return a list of cities objects
     */
    public ArrayList <City> getNCitiesInRegion(int c, String region) {
        String query = "SELECT city.Name as 'city',  country.Name as 'country', District, city.Population " +
                "FROM world.city join world.country on city.CountryCode = country.code  " +
                "WHERE country.Region = '" + region + "' " +
                "ORDER BY city.Population DESC LIMIT " + c + ";";
        ArrayList<City> cityList;
        cityList = processCityQuery(query);
        if(cityList == null)
        {
            System.out.println("Null list");
            return null;
        }
        else if (cityList.isEmpty()) {
            System.out.println("Empty list");
            return null;
        } else if (cityList.size() < c) {
            System.out.println("\n\n\nNot enough cities in the world for this ranking. Returning as many as there are in world");
        }

        return cityList;
    }


    /**
     * returns a list of N cities on a country
     * @param  country to search through
     * @param c number of cities to return
     * @return a list of cities objects
     */
    public ArrayList <City> getNCitiesInCountry(int c, String country) {
        String query = "SELECT city.Name as 'city',  country.Name as 'country', District, city.Population " +
                "FROM world.city join world.country on city.CountryCode = country.code " +
                "WHERE country.Name = '" + country + "' " +
                " ORDER BY city.Population DESC LIMIT " + c + ";";
        ArrayList<City> cityList;
        cityList = processCityQuery(query);
        if(cityList == null)
        {
            System.out.println("Null list");
            return null;
        }
        else if (cityList.isEmpty()) {
            System.out.println("Empty list");
            return cityList;
        } else if (cityList.size() < c) {
            System.out.println("\n\n\nNot enough cities in the world for this ranking. Returning as many as there are in world");
        }

        return cityList;
    }


    /**
     * returns a list of N cities on a continent
     * @param  district to search through
     * @param  c number of cities to return
     * @return a list of cities objects
     */
    public ArrayList <City> getNCitiesInDistrict(int c, String district) {
        String query = "SELECT city.Name as 'city',  country.Name as 'country', District, city.Population " +
                "FROM world.city join world.country on city.CountryCode = country.Code " +
                "WHERE city.District = '" + district +
                "' ORDER BY Population DESC LIMIT " + c + ";";
        ArrayList<City> cityList;
        cityList = processCityQuery(query);
        if(cityList == null)
        {
            System.out.println("Null list");
            return null;
        }
        else if (cityList.isEmpty()) {
            System.out.println("Empty list");
            return cityList;
        } else if (cityList.size() < c) {
            System.out.println("\n\n\nNot enough cities in the world for this ranking. Returning as many as there are in world");
        }

        return cityList;
    }


    /**
     * Processes an SQL query to get a list of cities
     * @param query Query to process
     * @return  a list of City objects
     */

    public ArrayList <City> processCityQuery(String query) {
        ArrayList <City> cities = new ArrayList <City> ();
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(query);

            //Extract country information
            // while there are new rows in the result set, keep creating new city objects
            while (rset.next()) {

                City c = new City();

                c.setCountry(rset.getString("Country"));
                c.setName(rset.getString("City"));
                c.setDistrict(rset.getString("District"));
                c.setPopulation(Integer.parseInt(rset.getString("Population")));

                //add object to list
                cities.add(c);
            }
            // when no more rows, return list
            return cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city data");
            return null;
        }
    }

    /*
     *    *********************CAPITAL CITY REPORTS*********************
     * @author Sam Alman
     */


    /**
     * Display a list of capital cities organised largest to smallest population
     * @return list of capital cities
     * @Author Sam Alman
     */
    public ArrayList<City> getAllCapitalCities()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT ID, city.Name, country.name, District, city.Population " +
                    "FROM city JOIN country ON (country.code = city.CountryCode) WHERE (Capital = ID)" +
                    " ORDER BY city.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<City> cities = new ArrayList<>();
            while (rset.next())
            {
                City city = new City();
                city.setId(rset.getInt("ID"));
                city.setName(rset.getString("city.name"));
                city.setCountry(rset.getString("country.name"));
                city.setDistrict(rset.getString("District"));
                city.setPopulation(rset.getInt("city.Population"));

                cities.add(city);
            }
            return cities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
        }
        return null;
    }

    /**
     * Gets all the capital cities in a continent organised by largest population to smallest.
     * @param continent
     * @return list of capital cities
     * @Author Sam Alman
     */
    public ArrayList<City> getAllCapitalCitiesInContinent(String continent)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT ID, city.Name, country.name, District, city.Population " +
                    "FROM city JOIN country ON (country.code = city.CountryCode) WHERE (Capital = ID) && continent = '" + continent +
                    "' ORDER BY city.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<City> cities = new ArrayList<>();
            while (rset.next())
            {
                City city = new City();
                city.setId(rset.getInt("ID"));
                city.setName(rset.getString("city.name"));
                city.setCountry(rset.getString("country.name"));
                city.setDistrict(rset.getString("District"));
                city.setPopulation(rset.getInt("city.Population"));

                cities.add(city);
            }
            return cities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
        }
        return null;
    }

    /**
     * Gets all the capital cities in a region organised by largest to smallest.
     * @param regionName
     * @return list of capital cities
     * @Author Sam Alman
     */
    public ArrayList<City> getAllCapitalCitiesInRegion(String regionName)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT ID, city.Name, country.name, District, city.Population " +
                    "FROM city JOIN country ON (country.code = city.CountryCode) WHERE (Capital = ID) && Region = '" + regionName +
                    "' ORDER BY city.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<City> cities = new ArrayList<>();
            while (rset.next())
            {
                City city = new City();
                city.setId(rset.getInt("ID"));
                city.setName(rset.getString("city.name"));
                city.setCountry(rset.getString("country.name"));
                city.setDistrict(rset.getString("District"));
                city.setPopulation(rset.getInt("city.Population"));

                cities.add(city);
            }
            return cities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
        }
        return null;
    }


    /**Display a list of Capital Cities
     * @Author Sam Alman
     */
    public void displayCapitals(ArrayList<City> allCapitals)
    {
        if (allCapitals == null)
        {
            System.out.println("No capital countries");
            return;
        }
        // Print header
        System.out.println(String.format("%-30s %-30s %-20s", "Name", "Country", "Population"));
        // Loop over all countries in the list
        for (City city : allCapitals)
        {
            if (city == null)
            {
                continue;
            }
            System.out.println(String.format("%-30s %-30s %-20s", city.getName(), city.getCountry(), city.getPopulation()));
        }
    }

    /**
     * Processes an SQL query to get a list of capital cities
     * @param query Query to process
     * @return  arraylist of capital cities
     */
    public ArrayList <CapitalCity> processCapitalCityQuery(String query) {
        ArrayList <CapitalCity> cities = new ArrayList <CapitalCity> ();

        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(query);

            //Extract country information
            // while there are new rows in the result set, keep creating new country objects
            while (rset.next()) {
                CapitalCity c = new CapitalCity();

                c.setID(rset.getString("ID"));
                c.setName(rset.getString("Name"));
                c.setCountryCode(rset.getString("CountryCode"));
                c.setDistrict(rset.getString("District"));
                c.setPopulation(rset.getString("Population"));

                cities.add(c);
            }
            return cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital city data");
            return null;
        }
    }



    /**
     * Returns the top N populated capital cities in a region where N is provided by the user
     * @return  list of CapitalCity objects
     */
    public ArrayList <CapitalCity> getNCapitalCitiesInRegion(String region, int limit) {
        // Prepare SQL query as string
        String query = "SELECT * FROM city JOIN country ON country.Capital = city.id WHERE country.Region = '" + region + "' ORDER BY city.Population DESC LIMIT " + limit;
        // Execute query
        ArrayList <CapitalCity> res = processCapitalCityQuery(query);

        if(res != null) {
            if (res.isEmpty()) {
                System.out.println("Invalid region specified.");
                return null;
            }
            if (res.size() < limit) {
                System.out.println("***Not enough capital cities in region for this ranking. Returning all capital's in region***");
            }
        }
        return res;
    }

    /**
     * Returns the top N populated capital cities
     * @return  list of CapitalCity objects
     */
    public ArrayList <CapitalCity> getNCapitalCitiesPopulation(int limit) {
        // Prepare SQL query as string
        String query = "SELECT * FROM city JOIN country ON country.Capital = city.id ORDER BY city.Population DESC LIMIT " + limit;
        // Execute query
        ArrayList <CapitalCity> res = processCapitalCityQuery(query);

        if(res != null) {
            if (res.size() < limit) {
                System.out.println("***Not enough capital cities for limit provided.***");
            }
        }
        return res;
    }


    /**
     * Returns the top N populated capital cities in a continent
     * @return  list of CapitalCity objects
     */
    public ArrayList <CapitalCity> getNCapitalCitiesInContinent(String continent, int limit) {
        // Prepare SQL query as string
        String query = "SELECT * FROM city JOIN country ON country.Capital = city.id WHERE country.Continent = '" + continent + "' ORDER BY city.Population DESC LIMIT " + limit;
        // Execute query
        ArrayList <CapitalCity> res = processCapitalCityQuery(query);

        if(res != null) {
            if (res.isEmpty()) {
                System.out.println("Invalid continent specified.");
                return null;
            }
            if (res.size() < limit) {
                System.out.println("***Not enough capital cities in continent for this ranking. Returning all capital's in continent***");
            }
        }

        return res;
    }

    /**
     * Returns a list of Populations of people living in cities in each continent
     * @return   arrayList of Population objects
     */
    public ArrayList<Population> getPopulationInCityByContinent() {
        try {
            ArrayList<Population> output = new ArrayList<>();

            // Creates an SQL statement.
            Statement stmt = con.createStatement();

            // Creates an SQL statement, stored as a STRING.
            String strSelect =
                    "SELECT country.continent, SUM(DISTINCT country.population), SUM(city.population) "
                            + "FROM city JOIN country ON CountryCode=code "
                            + "GROUP BY country.continent ";

            // Sends the SQL statement to the database.
            ResultSet rset = stmt.executeQuery(strSelect);

            // Indicates which columns on the database align to which attributes within "country".
            while (rset.next()) {
                Population popReport = new Population();
                popReport.setName(rset.getString("country.continent"));
                popReport.setPopulation(rset.getLong("SUM(DISTINCT country.population)"));
                double percentCity = Math.round((rset.getLong("SUM(city.population)") * 1D) / rset.getLong("SUM(DISTINCT country.population)") * 100);
                popReport.setCityPopulationPercent(percentCity);
                popReport.setCityPopulation(rset.getLong("SUM(city.population)"));
                long outCity = (rset.getLong("SUM(DISTINCT country.population)") - rset.getLong("SUM(city.population)"));
                popReport.setNotCityPopulation(outCity);
                double percentNonCity = Math.round((outCity * 1D) / rset.getLong("SUM(DISTINCT country.population)") * 100);
                popReport.setNonCityPopulationPercent(percentNonCity);


                // Adds this country (plus details) to the ArrayList.
                //System.out.println(popReport);
                output.add(popReport);

            }//end while

            return output;

        }//end try
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get information from database (City); check connection?");
            return null;
        }//end catch
    }//end getPopulationinCitybyContinent


    /**
     * Returns a list of Populations of people living in cities in each country
     * @return   arrayList of Population objects
     */
    public ArrayList<Population> getPopulationInCityByCountry() {
        try {
            ArrayList<Population> output = new ArrayList<>();

            // Creates an SQL statement.
            Statement stmt = con.createStatement();

            // Creates an SQL statement, stored as a STRING.
            String strSelect =
                    "SELECT country.Name, SUM(DISTINCT country.population), SUM(city.population) "
                            + "FROM city JOIN country ON CountryCode=code "
                            + "GROUP BY country.Name ";

            // Sends the SQL statement to the database.
            ResultSet rset = stmt.executeQuery(strSelect);

            // Indicates which columns on the database align to which attributes within "country".
            while (rset.next()) {
                Population popReport = new Population();
                popReport.setName(rset.getString("country.Name"));
                popReport.setPopulation(rset.getLong("SUM(DISTINCT country.population)"));
                double percentCity = Math.round((rset.getLong("SUM(city.population)") * 1D) / rset.getLong("SUM(DISTINCT country.population)") * 100);
                popReport.setCityPopulationPercent(percentCity);
                popReport.setCityPopulation(rset.getLong("SUM(city.population)"));
                long outCity = (rset.getLong("SUM(DISTINCT country.population)") - rset.getLong("SUM(city.population)"));
                popReport.setNotCityPopulation(outCity);
                double percentNonCity = Math.round((outCity * 1D) / rset.getLong("SUM(DISTINCT country.population)") * 100);
                popReport.setNonCityPopulationPercent(percentNonCity);


                output.add(popReport);
            }//end while

            return output;

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get information from database (City); check connection?");
            return null;
        }// end the exception handler
    }//end getPopulationinCitybyCountry


    /**
     * Returns a list of Populations of people living in cities in each region
     * @return   arrayList of Population objects
     */
    public ArrayList<Population> getPopulationInCityByRegion() {
        try {
            ArrayList<Population> output = new ArrayList<>();

            // Creates an SQL statement.
            Statement stmt = con.createStatement();

            // Creates an SQL statement, stored as a STRING.
            String strSelect =
                    "SELECT country.region, SUM(DISTINCT country.population), SUM(city.population) "
                            + "FROM city JOIN country ON CountryCode=code "
                            + "GROUP BY country.region ";

            // Sends the SQL statement to the database.
            ResultSet rset = stmt.executeQuery(strSelect);

            // Indicates which columns on the database align to which attributes within "country".
            while (rset.next()) {
                Population popReport = new Population();
                popReport.setName(rset.getString("country.region"));
                popReport.setPopulation(rset.getLong("SUM(DISTINCT country.population)"));
                double percentCity = Math.round((rset.getLong("SUM(city.population)") * 1D) / rset.getLong("SUM(DISTINCT country.population)") * 100);
                popReport.setCityPopulationPercent(percentCity);
                popReport.setCityPopulation(rset.getLong("SUM(city.population)"));
                long outCity = (rset.getLong("SUM(DISTINCT country.population)") - rset.getLong("SUM(city.population)"));
                popReport.setNotCityPopulation(outCity);
                double percentNonCity = Math.round((outCity * 1D) / rset.getLong("SUM(DISTINCT country.population)") * 100);
                popReport.setNonCityPopulationPercent(percentNonCity);


                output.add(popReport);
            }//end while

            return output;

        }//end try
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get information from database (City); check connection?");
            return null;
        }//end catch
    }//end getPopulationinCitybyContinent

    /**
     * Returns a report of people speaking Chinese, English, Hindi, Spanish and Arabic
     * @return  ResultSet from the query
     */
    public ResultSet getLanguage() {
        try {
                // Create string for SQL statement
                String strSelect =
                        "SELECT la.Language, ROUND(SUM((co.Population * la.Percentage) / 100)) AS 'Population', (((ROUND(SUM((co.Population * la.Percentage) / 100))) * 100) / (SELECT SUM(country.Population) FROM country)) AS 'WorldPercentage' "
                                + "FROM countrylanguage la, country co "
                                + "WHERE (la.Language = 'Chinese' "
                                + "OR la.Language = 'English' "
                                + "OR la.Language = 'Hindi' "
                                + "OR la.Language = 'Spanish' "
                                + "OR la.Language = 'Arabic') "
                                + "AND la.CountryCode = co.Code "
                                + "GROUP BY la.Language "
                                + "ORDER BY Population DESC ";
                // Creates an SQL statement.
                Statement stmt = con.createStatement();
                // Execute SQL statement
                ResultSet rset = stmt.executeQuery(strSelect);
                System.out.println("\nA report that returns the information about the wanted languages speakers and the percentage of the world population");
                System.out.println(String.format("%-14s %-24s %-24s", "Language", "Population of speakers", "% of world population"));

                while (rset.next()) {
                    ResultSetMetaData rsMetaData = rset.getMetaData();
                    int numberOfColumns = rsMetaData.getColumnCount();
                    ResultSetMetaData rsmd = rset.getMetaData();
                    // get the column names; column indexes start from 1
                    for (int i = 1; i <= 3; i++) {
                        if (i > 1) {
                        }
                        String columnValue = rset.getString(i);
                        System.out.print(columnValue + "             ");
                    }
                    System.out.println("");
                }
                return rset;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get language details");
            return null;
        }
    }
}
