package com.napier.sem;

/**
 * Represents a Capital City
 */
public class CapitalCity {
    String ID;
    String Name;
    String CountryCode;
    String District;
    String Population;

    public void setID(String ID) { this.ID = ID; }

    public String getName() {return Name;}

    public void setName(String Name) { this.Name = Name;}

    public void setCountryCode(String CountryCode) {this.CountryCode = CountryCode;}

    public void setDistrict(String District) {this.District = District;}

    public String getPopulation() {return Population;}

    public void setPopulation(String Population) {this.Population = Population;}
}
