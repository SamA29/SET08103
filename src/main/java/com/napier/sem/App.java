package com.napier.sem;

import java.sql.*;

/*
 *  Created by Group 7: Pablo Sanchez, Alex Vazquez, Sam Alman, Valentina Kerecanina
 *  Last Modification: 06/03/2022
 * */

public class App {
    /**
     * Main function
     */
    public static void main(String[] args) throws SQLException {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();

        // Get country
        Country c = a.getCountry();
        // Display results of the SQL query
        a.displayCountry(c);

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
     * Get Country.
     * @return Country object
     * @author Pablo Sanchez
     */
    public Country getCountry() {
        try {
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT name "
                            + "FROM country ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset.next()) {

                Country c = new Country();
                c.name = rset.getString("name");
               // c.population = rset.getInt("population");

                return c;
            } else
                return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }

    /**
     * Display information of the country object
     * @author Pablo Sanchez
     */
    public void displayCountry(Country c)
    {
        if (c != null)
        {
            //Display countries from world
            System.out.println(c.name + "\n");
        }
    }
}