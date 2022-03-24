package com.napier.sem;

import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static com.napier.sem.PrintReport.*;

class AppTest
{


    /**
     * Tests for display countries (PrintReport)
     */
    ArrayList<Country> countries;

    @Test
    void displayCountriesBasic() {
        countries = new ArrayList<>();
        Country c = new Country();
        c.setCode("ABW");
        c.setName("Aruba");
        c.setContinent("North America");
        c.setPopulation(103000);
        c.setCapital("Oranjestad");
        c.setRegion("Caribbean");
        countries.add(c);
        displayCountries(countries);
    }

    @Test
    void displayCountriesTestNull() {
        displayCountries(null);
    }

    @Test
    void displayCountriesTestEmpty() {
        countries = new ArrayList<>();
        displayCountries(countries);
    }

    @Test
    void displayCountriesContainsNull() {
        countries = new ArrayList<>();
        countries.add(null);
        displayCountries(countries);
    }

    /**
     * Tests for display Top countries (PrintReport)
     */

    @Test
    void displayTopCountriesBasic() {
        countries = new ArrayList<>();
        Country c = new Country();
        c.setCode("AFG");
        c.setName("Afghanistan");
        countries.add(c);
        displayTopCountries(countries);
    }

    @Test
    void displayTopCountriesTestNull() {
        displayTopCountries(null);
    }

    @Test
    void displayTopCountriesTestEmpty() {
        countries = new ArrayList<>();
        displayTopCountries(countries);
    }

    @Test
    void displayTopCountriesContainsNull() {
        countries = new ArrayList<>();
        countries.add(null);
        displayTopCountries(countries);
    }


    /**
     * Tests for display Cities (PrintReport)
     */

    @Test
    void displayCitiesTestStandard() {
        cities = new ArrayList < > ();
        City city = new City();
        city.setCountry("France");
        city.setName("Paris");
        city.setPopulation(90);
        city.setDistrict("Île-de-France");
        cities.add(city);
        displayCities(cities);
    }

    @Test
    void displayCitiesTestNullSet() {

        displayCountries(null);
    }

    @Test
    void displayCitiesTestEmptySet() {
        cities = new ArrayList <> ();
        displayCities(cities);
    }

    @Test
    void displayCitiesTestSetContainsNull() {
        cities = new ArrayList <> ();
        cities.add(null);
        displayCities(cities);
    }


    /**
     * Tests for display Top Cities (PrintReport)
     */

    @Test
    void displayTopCitiesTestStandard() {
        cities = new ArrayList < > ();
        City city = new City();
        city.setName("Paris");
        city.setPopulation(400);
        city.setDistrict("Île-de-France");
        city.setCountry("France");
        cities.add(city);
        displayTopCountries(countries);
    }
    @Test
    void displayTopCitiesTestContainsNull() {
        cities = new ArrayList <City> ();
        cities.add(null);
        displayTopCountries(countries);
    }

    @Test
    void displayTopCitiesTestEmptySet() {
        cities = new ArrayList <> ();
        displayTopCities(cities);
    }
    @Test
    void displayTopCitiesTestNull() {
        displayTopCountries(null);
    }


    /**
     * Tests for get Cities (Reports)
     */

    ArrayList <City> cities;
    Reports a = new Reports();

    @Test
    void getAllCitiesInContinentTestFalse() {
        cities = new ArrayList<>();
        String continent = "Foo";
        cities = a.getAllCitiesInContinent(continent);
    }
    @Test
    void getAllCitiesInContinentTestTrue() {
        cities = new ArrayList<>();
        String continent = "Africa";
        cities = a.getAllCitiesInContinent(continent);
    }
    @Test
    void getAllCitiesInDistrictTestFalse() {
        cities = new ArrayList<>();
        String district = "Foo";
        cities = a.getAllCitiesInDistrict(district);
    }
    @Test
    void getAllCitiesInDistrictTestTrue() {
        cities = new ArrayList<>();
        String district = "Bretagne";
        cities = a.getAllCitiesInDistrict(district);
    }
    @Test
    void getAllCitiesInCountryTestFalse() {
        cities = new ArrayList<>();
        String country = "Foo";
        cities = a.getAllCitiesInCountry(country);
    }
    @Test
    void getAllCitiesInCountryTestTrue() {
        cities = new ArrayList<>();
        String country = "Spain";
        cities = a.getAllCitiesInCountry(country);
    }
    @Test
    void getAllCitiesInRegionTestFalse() {
        cities = new ArrayList<>();
        String region = "Foo";
        cities = a.getAllCitiesInRegion(region);
    }
    @Test
    void getAllCitiesInRegionTestTrue() {
        cities = new ArrayList<>();
        String region = "Middle East";
        cities = a.getAllCitiesInRegion(region);
    }
    @Test
    void getAllCitiesInWorldTestTrue() {
        cities = new ArrayList<>();
        cities = a.getAllCitiesInWorld();
    }

    @Test
    void topNCitiesInWorldBigNumber(){
        cities = new ArrayList<>();
        int pass = 99999999;
        cities = a.getNCitiesInWorld(pass);
        // assertEquals(cities, null);

    }
    @Test
    void topNCitiesInWorldSmallNumber(){
        cities = new ArrayList<>();
        int pass = -99999999;
        cities = a.getNCitiesInWorld(pass);


    }
    @Test
    void topNCitiesInWorldNull(){
        cities = new ArrayList<>();
        int pass = 0;
        cities =  a.getNCitiesInWorld(pass);

    }
    @Test
    void topNCitiesInWorldNormal(){
        cities = new ArrayList<>();
        int pass = 5;
        cities = a.getNCitiesInWorld(pass);

    }
    @Test
    void topNCitiesInDistrictBigNumber(){
        cities = new ArrayList<>();
        int pass = 99999999;
        cities =  a.getNCitiesInDistrict(pass, "");

    }
    @Test
    void topNCitiesInDistrictSmallNumber(){
        cities = new ArrayList<>();
        int pass = -99999999;
        cities = a.getNCitiesInDistrict(pass, "");

    }
    @Test
    void topNCitiesInDistrictNull(){
        cities = new ArrayList<>();
        int pass = 0;
        cities = a.getNCitiesInDistrict(pass, "");

    }
    @Test
    void topNCitiesInDistrictNormal(){
        cities = new ArrayList<>();
        int pass = 5;
        cities = a.getNCitiesInDistrict(pass, "");

    }
    @Test
    void topNCitiesInRegionBigNumber(){
        cities = new ArrayList<>();
        int pass = 99999999;
        cities =  a.getNCitiesInRegion(pass, "");

    }
    @Test
    void topNCitiesInRegionSmallNumber(){
        cities = new ArrayList<>();
        int pass = -99999999;
        cities = a.getNCitiesInRegion(pass, "");

    }
    @Test
    void topNCitiesInRegionNull(){
        cities = new ArrayList<>();
        int pass = 0;
        cities = a.getNCitiesInRegion(pass, "");

    }
    @Test
    void topNCitiesInRegionNormal(){
        cities = new ArrayList<>();
        int pass = 5;
        cities = a.getNCitiesInRegion(pass, "");

    }
    @Test
    void topNCitiesInCountryBigNumber(){
        cities = new ArrayList<>();
        int pass = 70000000;
        cities = a.getNCitiesInCountry(pass, "");

    }
    @Test
    void topNCitiesInCountrySmallNumber(){
        cities = new ArrayList<>();
        int pass = -70000000;
        cities = a.getNCitiesInCountry(pass, "");

    }
    @Test
    void topNCitiesInCountryNull(){
        cities = new ArrayList<>();
        int pass = 0;
        cities = a.getNCitiesInCountry(pass, "");

    }
    @Test
    void topNCitiesInCountryNormal(){
        cities = new ArrayList<>();
        int pass = 5;
        cities = a.getNCitiesInCountry(pass, "");
    }
    @Test
    void testProcessCityQueryNull() {
        a.processCityQuery(null);
    }
    @Test
    void testProcessCityQuery() {
        a.processCityQuery("query");
    }

    /**
     * Tests for Countries (Reports)
     */
    @Test
    void testProcessCountryQueryNull(){
        a.processCountryQuery(null);
    }
    @Test
    void testProcessCountryQuery(){
        a.processCountryQuery("query");
    }

    @Test
    void testGetAllCountriesInWorld() {
        a.getAllCountriesInWorld();
    }
    @Test
    void testGetTopNCountriesInWorldNegative() {
        a.getTopNCountriesInWorld(-1);
    }
    @Test
    void testGetTopNCountriesInWorld0() {
        a.getTopNCountriesInWorld(0);
    }
    @Test
    void testGetTopNCountriesInWorldBasic() {
        a.getTopNCountriesInWorld(5);
    }
    @Test
    void testGetAllCountriesInContinentBasic() {
        a.getAllCountriesInContinent("Africa");
    }
    @Test
    void testGetAllCountriesInContinentNull() {
        a.getAllCountriesInContinent(null);
    }
    @Test
    void testGetTopNCountriesInContinentBothWrong() {
        a.getTopNCountriesInContinent(null, 0);
    }
    @Test
    void testGetTopNCountriesInContinentWrongLimit() {
        a.getTopNCountriesInContinent("Africa", -1);
    }
    @Test
    void testGetTopNCountriesInContinentWrongCont() {
        a.getTopNCountriesInContinent(null, 5);
    }
    @Test
    void testGetTopNCountriesInContinentBasic() {
        a.getTopNCountriesInContinent("Africa", 5);
    }
    @Test
    void testGetAllCountriesInRegionBasic() {
        a.getAllCountriesInRegion("Baltic Countries");
    }
    @Test
    void testGetAllCountriesInRegionNull() {
        a.getAllCountriesInRegion(null);
    }
    @Test
    void testGetTopNCountriesInRegionBothWrong() {
        a.getTopNCountriesInRegion(null, 0);
    }
    @Test
    void testGetTopNCountriesInRegionWrongLimit() {
        a.getTopNCountriesInRegion("Baltic Countries", -1);
    }
    @Test
    void testGetTopNCountriesInRegionWrongRegion() {
        a.getTopNCountriesInRegion(null, 4);
    }
    @Test
    void testGetTopNCountriesInRegionBasic() {
        a.getTopNCountriesInRegion("Baltic Countries", 5);
    }
    @Test
    void testGetAllCapitalCitiesTrue() {
        a.getAllCapitalCities();
    }
    @Test
    void testGetAllCapitalCitiesInContinent() {
        a.getAllCapitalCitiesInContinent("Africa");
    }
    @Test
    void testGetAllCapitalCitiesInRegion() {
        a.getAllCapitalCitiesInRegion("Caribbean");
    }


}