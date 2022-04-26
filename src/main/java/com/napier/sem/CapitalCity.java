package com.napier.sem;
/**
 * Represents a Capital City
 * Class representation of the columns created in world.sql
 */

public class CapitalCity {
    
    /**
     * ID of the city
     */
    String ID;
    /**
     * Name of the city
     */
    String Name;
    /**
     * Code of the country
     */
    String CountryCode;
    /**
     * Distric of the city
     */
    String District;
    /**
     * Population of the city
     */
    String Population;

    /**
     * Changes the value of the ID of the city
     * @param ID  city ID to set
     */
    public void setID(String ID) { this.ID = ID; }

    /**
     * Gets the name of the city
     * @return  city name
     */
    public String getName() {return Name;}

    /**
     * Changes the value of the name of the city
     * @param Name  city name to set
     */
    public void setName(String Name) { this.Name = Name;}

    /**
     * Changes the value of the code of the country
     * @param CountryCode  country code to set
     */
    public void setCountryCode(String CountryCode) {this.CountryCode = CountryCode;}

    /**
     * Changes the value of the district of the city
     * @param District  city district to set
     */
    public void setDistrict(String District) {this.District = District;}

    /**
     * Gets the population of the city
     * @return  city population
     */
    public String getPopulation() {return Population;}

    /**
     * Changes the value of the population of the city
     * @param Population  city population to set
     */
    public void setPopulation(String Population) {this.Population = Population;}
}
