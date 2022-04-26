package com.napier.sem;
/**
 * Represents a Population.
 * Class representation of the columns created in world.sql
 */

public class Population {

    /**
     * Name of the country
     */
    private String name;
    /**
     * Population of the country
     */
    private long population;
    /**
     * Population of the city
     */
    private long cityPopulation;
    /**
     * Population of the city in percentage
     */
    private double cityPopulationPercent;
    /**
     * Population of the non-city areas
     */
    private long notCityPopulation;
    /**
     * Population of the non-city areas in percentage
     */
    private double nonCityPopulationPercent;

    /**
     * Gets the name of the country
     * @return  country name
     */
    public String getName() {
        return name;
    }

    /**
     * Changes the value of the name of the country
     * @param name  country name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the country population
     * @return  country population
     */
    public long getPopulation() {
        return population;
    }

    /**
     * Changes the population of the country
     * @param population  country population to set
     */
    public void setPopulation(long population) {
        this.population = population;
    }

    /**
     * Gets the city population
     * @return  city population
     */
    public long getCityPopulation() {
        return cityPopulation;
    }

    /**
     * Changes the population of the city
     * @param cityPopulation  city population to set
     */
    public void setCityPopulation(long cityPopulation) {
        this.cityPopulation = cityPopulation;
    }

    /**
     * Gets the city population in percentage
     * @return  city population percent
     */
    public double getCityPopulationPercent() {
        return cityPopulationPercent;
    }

    /**
     * Changes the population of the city in percentage
     * @param cityPopulationPercent  city population percent to set
     */
    public void setCityPopulationPercent(double cityPopulationPercent) {
        this.cityPopulationPercent = cityPopulationPercent;
    }

    /**
     * Gets the non-city population
     * @return  non-city population
     */
    public long getNotCityPopulation() {
        return notCityPopulation;
    }

    /**
     * Changes the population of the non-city
     * @param notCityPopulation  non-city population to set
     */
    public void setNotCityPopulation(long notCityPopulation) {
        this.notCityPopulation = notCityPopulation;
    }

    /**
     * Gets the non-city population in percentage
     * @return  non-city population percent
     */
    public double getNonCityPopulationPercent() {
        return nonCityPopulationPercent;
    }

    /**
     * Changes the population of the non-city in percentage
     * @param nonCityPopulationPercent  non-city population percent to set
     */
    public void setNonCityPopulationPercent(double nonCityPopulationPercent) {
        this.nonCityPopulationPercent = nonCityPopulationPercent;
    }

    /**
     * Converts all listed information to string from database
     *  country name
     *  country population
     *  city population
     *  city population in percentage
     *  non-city population
     *  non-city population in percentage
     */
    @Override
    public String toString() {
        return name + " " + population + " " + cityPopulation + " " + cityPopulationPercent + "% " + notCityPopulation + " " + nonCityPopulationPercent  +"% \n";
    }

}

