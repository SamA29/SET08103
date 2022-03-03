package com.napier.sem;

import java.sql.*;

public class App {
    /**
     * Main function
     */
    public static void main(String[] args) throws SQLException {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();

        // Get Employee
        //World w = a.getCountry();
        // Display results
        //a.displayCountry(w);

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
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

    /**
     * Get Country.
     * @return World object
     */
    public World getCountry() {
        try {
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT world.country "
                            + "FROM world "
                            + "ORDER BY world.population DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset.next()) {
                World w = new World();
                w.country = rset.getString("world.country");
                w.city = rset.getString("world.city");
                w.countryLanguage = rset.getString("world.countryLanguage");

                return w;
            } else
                return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }

    /**
     * Display countries sorted by pop. largest to smallest
     *
     */
    public void displayCountry(World w)
    {
        if (w != null)
        {
            //Display countries from world
            System.out.println(w.country + " " + "\n");
        }
    }
}