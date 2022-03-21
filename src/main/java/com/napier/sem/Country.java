package com.napier.sem;

/**
 * Represents a Country. Class representation of the table created in world.sql
 * @author Pablo Sanchez
 */
public class Country {

    /**
     * Default empty constructor
     */
    public Country() {
    }

    /**
     * Code of the country
     */
    private String code;

    /**
     * Name of the country
     */
    private String name;

    /**
     * Population of the country
     */
    private int population;

    /**
     * Capital of the country
     */
    private String capital;

    /**
     * Continent of the country
     */
    private String continent;

    /**
     * Region of the country
     */
    private String region;

    /**
     * Gets the code of the country
     * @return  country code
     */
    public String getCode() {
        return code;
    }

    /**
     * Changes the value of the code of the country
     * @param code  country code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the country name
     * @return  country name
     */
    public String getName() {
        return name;
    }

    /**
     * Changes the name of the country
     * @param name  country name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the population of the country
     * @return  country's population
     */
    public int getPopulation() {
        return population;
    }

    /**
     * Changes the population of the country
     * @param population    population to set
     */
    public void setPopulation(int population) {
        this.population = population;
    }

    /**
     * Gets the capital of the country
     * @return  country's capital
     */
    public String getCapital() {
        return capital;
    }

    /**
     * Changes the capital of the country
     * @param capital   the country's capital to set
     */
    public void setCapital(String capital) {
        this.capital = capital;
    }

    /**
     * Gets the continent of the country
     * @return  country's continent
     */
    public String getContinent() {
        return continent;
    }

    /**
     * Changes the continent of the country
     * @param continent country's continent to set
     */
    public void setContinent(String continent) {
        this.continent = continent;
    }

    /**
     * Gets the region of the country
     * @return  region of the country
     */
    public String getRegion() {
        return region;
    }

    /**
     * Changes the region of the country
     * @param region region of the country to set
     */
    public void setRegion(String region) {
        this.region = region;
    }
}
