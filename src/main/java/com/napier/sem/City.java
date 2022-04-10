package com.napier.sem;

/**
 * Represents a City. Class representation of the table created in world.sql
 * @author Pablo Sanchez
 */
public class City {
    /**
     * Id of the city
     */
    private int id;
    /**
     * Name of the city
     */
    private String name;
    /**
     * Country of the city
     */
    private String country;
    /**
     * District of the city
     */
    private String district;
    /**
     * Population of the city
     */
    private long population;

    /**
     * Constructor for the city
     */
    public City()
    {

    }

    /**
     * Gets the city name
     * @return city name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the city name
     * @param name of city to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the city country
     * @return city country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the city country
     * @param country name to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets the city district
     * @return city district
     */
    public String getDistrict() {
        return district;
    }

    /**
     * Sets the city district
     * @param district to set
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * Gets the city population
     * @return cities populations
     */
    public long getPopulation() {
        return population;
    }

    /**
     * Sets the city population
     * @param population to set
     */
    public void setPopulation(long population) {
        this.population = population;
    }

    public void setId(int id) {
        this.id = id;
    }
}

