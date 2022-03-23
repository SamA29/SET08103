package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

public class CityQuery
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

            try {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");

                break;
            }
            //If does not work
            catch (SQLException sqle) {
            //    System.out.println("Failed to connect to database attempt " + Integer.toString(i));
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
                System.out.println("***********READ***********");
                System.out.println("More coming!! Now, city reports ***(Please wait)***");
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }
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
        ArrayList < City > cities = processCityQuery(query);
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
        ArrayList < City > cities = processCityQuery(query);
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
        ArrayList < City > cities = processCityQuery(query);
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
        ArrayList < City > cities = processCityQuery(query);
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
        ArrayList<City> cityList = new ArrayList<City>();
        cityList = processCityQuery(query);
        if(cityList == null)
        {
            System.out.println("Null list");
            return cityList;
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
        ArrayList<City> cityList = new ArrayList<City>();
        cityList = processCityQuery(query);
        if(cityList == null)
        {
            System.out.println("Null list");
            return cityList;
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
     * @param  region to search on
     * @param c number of cities to return
     * @return a list of cities objects
     */
    public ArrayList <City> getNCitiesInRegion(int c, String region) {
        String query = "SELECT city.Name as 'city',  country.Name as 'country', District, city.Population " +
                "FROM world.city join world.country on city.CountryCode = country.code  " +
                "WHERE country.Region = '" + region + "' " +
                "ORDER BY city.Population DESC LIMIT " + c + ";";
        ArrayList<City> cityList = new ArrayList<City>();
        cityList = processCityQuery(query);
        if(cityList == null)
        {
            System.out.println("Null list");
            return cityList;
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
     * @param  country to search through
     * @param c number of cities to return
     * @return a list of cities objects
     */
    public ArrayList <City> getNCitiesInCountry(int c, String country) {
        String query = "SELECT city.Name as 'city',  country.Name as 'country', District, city.Population " +
                "FROM world.city join world.country on city.CountryCode = country.code " +
                "WHERE country.Name = '" + country + "' " +
                " ORDER BY city.Population DESC LIMIT " + c + ";";
        ArrayList<City> cityList = new ArrayList<City>();
        cityList = processCityQuery(query);
        if(cityList == null)
        {
            System.out.println("Null list");
            return cityList;
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
        ArrayList<City> cityList = new ArrayList<City>();
        cityList = processCityQuery(query);
        if(cityList == null)
        {
            System.out.println("Null list");
            return cityList;
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

}