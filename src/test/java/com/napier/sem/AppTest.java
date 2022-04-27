package com.napier.sem;

import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static com.napier.sem.PrintReport.*;

class AppTest
{
    // Tests for display countries (PrintReport)
    ArrayList<Country> countries;

    /**
     * Test for displaying a Country's attributes
     */
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

    /**
     * Test for displaying a Country's attributes when input is null
     */
    @Test
    void displayCountriesTestNull() {
        displayCountries(null);
    }

    /**
     * Test for displaying a Country's attributes when empty
     */
    @Test
    void displayCountriesTestEmpty() {
        countries = new ArrayList<>();
        displayCountries(countries);
    }

    /**
     * Test for displaying a Country's attributes when country is null
     */
    @Test
    void displayCountriesContainsNull() {
        countries = new ArrayList<>();
        countries.add(null);
        displayCountries(countries);
    }

    // Tests for display Top countries (PrintReport)


    /**
     * Test for displaying top Country's attributes
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

    /**
     * Test for displaying top Country's attributes when input is null
     */
    @Test
    void displayTopCountriesTestNull() {
        displayTopCountries(null);
    }

    /**
     * Test for displaying top Country's attributes when empty
     */
    @Test
    void displayTopCountriesTestEmpty() {
        countries = new ArrayList<>();
        displayTopCountries(countries);
    }

    /**
     * Test for displaying top Country's attributes when country is null
     */
    @Test
    void displayTopCountriesContainsNull() {
        countries = new ArrayList<>();
        countries.add(null);
        displayTopCountries(countries);
    }


    //Tests for display Cities (PrintReport)

    /**
     * Test for displaying a City's attributes
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

    /**
     * Test for displaying a City's attributes when input is null
     */
    @Test
    void displayCitiesTestNullSet() {
        displayCountries(null);
    }

    /**
     * Test for displaying a City's attributes when empty
     */
    @Test
    void displayCitiesTestEmptySet() {
        cities = new ArrayList <> ();
        displayCities(cities);
    }

    /**
     * Test for displaying a City's attributes when city is null
     */
    @Test
    void displayCitiesTestSetContainsNull() {
        cities = new ArrayList <> ();
        cities.add(null);
        displayCities(cities);
    }


    // Tests for display Top Cities (PrintReport)

    /**
     * Test for displaying top City's attributes
     */
    @Test
    void displayTopCitiesTestStandard() {
        cities = new ArrayList <> ();
        City city = new City();
        city.setName("Paris");
        city.setPopulation(400);
        city.setDistrict("Île-de-France");
        city.setCountry("France");
        cities.add(city);
        displayTopCountries(countries);
    }

    /**
     * Test for displaying top City's attributes when city is null
     */
    @Test
    void displayTopCitiesTestContainsNull() {
        cities = new ArrayList <City> ();
        cities.add(null);
        displayTopCountries(countries);
    }

    /**
     * Test for displaying top City's attributes when empty
     */
    @Test
    void displayTopCitiesTestEmptySet() {
        cities = new ArrayList <> ();
        displayTopCities(cities);
    }

    /**
     * Test for displaying top City's attributes when input is null
     */
    @Test
    void displayTopCitiesTestNull() {
        displayTopCountries(null);
    }


    // Tests for get Cities (Reports)

    ArrayList <City> cities;
    Reports a = new Reports();

    /**
     * Test for getting all Cities in a non-existent Continent
     */
    @Test
    void getAllCitiesInContinentTestFalse() {
        cities = new ArrayList<>();
        String continent = "Foo";
        cities = a.getAllCitiesInContinent(continent);
    }

    /**
     * Test for getting all Cities in a Continent
     */
    @Test
    void getAllCitiesInContinentTestTrue() {
        cities = new ArrayList<>();
        String continent = "Africa";
        cities = a.getAllCitiesInContinent(continent);
    }

    /**
     * Test for getting all Cities in a non-existent District
     */
    @Test
    void getAllCitiesInDistrictTestFalse() {
        cities = new ArrayList<>();
        String district = "Foo";
        cities = a.getAllCitiesInDistrict(district);
    }

    /**
     * Test for getting all Cities in a District
     */
    @Test
    void getAllCitiesInDistrictTestTrue() {
        cities = new ArrayList<>();
        String district = "Bretagne";
        cities = a.getAllCitiesInDistrict(district);
    }

    /**
     * Test for getting all Cities in a non-existent Country
     */
    @Test
    void getAllCitiesInCountryTestFalse() {
        cities = new ArrayList<>();
        String country = "Foo";
        cities = a.getAllCitiesInCountry(country);
    }

    /**
     * Test for getting all Cities in a Country
     */
    @Test
    void getAllCitiesInCountryTestTrue() {
        cities = new ArrayList<>();
        String country = "Spain";
        cities = a.getAllCitiesInCountry(country);
    }

    /**
     * Test for getting all Cities in a non-existent Region
     */
    @Test
    void getAllCitiesInRegionTestFalse() {
        cities = new ArrayList<>();
        String region = "Foo";
        cities = a.getAllCitiesInRegion(region);
    }

    /**
     * Test for getting all Cities in a Region
     */
    @Test
    void getAllCitiesInRegionTestTrue() {
        cities = new ArrayList<>();
        String region = "Middle East";
        cities = a.getAllCitiesInRegion(region);
    }

    /**
     * Test for getting all Cities in the World
     */
    @Test
    void getAllCitiesInWorldTestTrue() {
        cities = new ArrayList<>();
        cities = a.getAllCitiesInWorld();
    }

    /**
     * Test for top user provided (N) amount Cities in the world if input is big
     */
    @Test
    void topNCitiesInWorldBigNumber(){
        cities = new ArrayList<>();
        int pass = 99999999;
        cities = a.getNCitiesInWorld(pass);
        // assertEquals(cities, null);

    }

    /**
     * Test for top user provided (N) amount Cities in the world if input is small
     */
    @Test
    void topNCitiesInWorldSmallNumber(){
        cities = new ArrayList<>();
        int pass = -99999999;
        cities = a.getNCitiesInWorld(pass);


    }

    /**
     * Test for top user provided (N) amount Cities in the world if input is 0
     */
    @Test
    void topNCitiesInWorldNull(){
        cities = new ArrayList<>();
        int pass = 0;
        cities =  a.getNCitiesInWorld(pass);

    }
    @Test

    /**
     * Test for top user provided (N) amount Cities in the world if input is normal
     */
    void topNCitiesInWorldNormal(){
        cities = new ArrayList<>();
        int pass = 5;
        cities = a.getNCitiesInWorld(pass);

    }

    /**
     * Test for top user provided (N) amount Cities in a District if input is big
     */
    @Test
    void topNCitiesInDistrictBigNumber(){
        cities = new ArrayList<>();
        int pass = 99999999;
        cities =  a.getNCitiesInDistrict(pass, "");

    }

    /**
     * Test for top user provided (N) amount Cities in a District if input is small
     */
    @Test
    void topNCitiesInDistrictSmallNumber(){
        cities = new ArrayList<>();
        int pass = -99999999;
        cities = a.getNCitiesInDistrict(pass, "");

    }

    /**
     * Test for top user provided (N) amount Cities in a District if input is 0
     */
    @Test
    void topNCitiesInDistrictNull(){
        cities = new ArrayList<>();
        int pass = 0;
        cities = a.getNCitiesInDistrict(pass, "");

    }

    /**
     * Test for top user provided (N) amount Cities in a District if input is normal
     */
    @Test
    void topNCitiesInDistrictNormal(){
        cities = new ArrayList<>();
        int pass = 5;
        cities = a.getNCitiesInDistrict(pass, "");

    }

    /**
     * Test for top user provided (N) amount Cities in a Region if input is big
     */
    @Test
    void topNCitiesInRegionBigNumber(){
        cities = new ArrayList<>();
        int pass = 99999999;
        cities =  a.getNCitiesInRegion(pass, "");

    }

    /**
     * Test for top user provided (N) amount Cities in a Region if input is small
     */
    @Test
    void topNCitiesInRegionSmallNumber(){
        cities = new ArrayList<>();
        int pass = -99999999;
        cities = a.getNCitiesInRegion(pass, "");

    }

    /**
     * Test for top user provided (N) amount Cities in a Region if input is 0
     */
    @Test
    void topNCitiesInRegionNull(){
        cities = new ArrayList<>();
        int pass = 0;
        cities = a.getNCitiesInRegion(pass, "");

    }

    /**
     * Test for top user provided (N) amount Cities in a Region if input is normal
     */
    @Test
    void topNCitiesInRegionNormal(){
        cities = new ArrayList<>();
        int pass = 5;
        cities = a.getNCitiesInRegion(pass, "");

    }

    /**
     * Test for top user provided (N) amount Cities in a Country if input is big
     */
    @Test
    void topNCitiesInCountryBigNumber(){
        cities = new ArrayList<>();
        int pass = 70000000;
        cities = a.getNCitiesInCountry(pass, "");

    }

    /**
     * Test for top user provided (N) amount Cities in a Country if input is small
     */
    @Test
    void topNCitiesInCountrySmallNumber(){
        cities = new ArrayList<>();
        int pass = -70000000;
        cities = a.getNCitiesInCountry(pass, "");

    }

    /**
     * Test for top user provided (N) amount Cities in a Country if input is 0
     */
    @Test
    void topNCitiesInCountryNull(){
        cities = new ArrayList<>();
        int pass = 0;
        cities = a.getNCitiesInCountry(pass, "");

    }

    /**
     * Test for top user provided (N) amount Cities in a Country if input is normal
     */
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

    // Tests for Countries (Reports)
    @Test
    void testProcessCountryQueryNull(){
        a.processCountryQuery(null);
    }
    @Test
    void testProcessCountryQuery(){
        a.processCountryQuery("query");
    }

    /**
     * Test for getting all Countries in the World
     */
    @Test
    void testGetAllCountriesInWorld() {
        a.getAllCountriesInWorld();
    }

    /**
     * Test for getting top user provided (N) amount Countries in the World if input is Null
     */
    @Test
    void testGetTopNCountriesInWorldNegative() {
        a.getTopNCountriesInWorld(-1);
    }

    /**
     * Test for getting top user provided (N) amount Countries in the World if input is 0
     */
    @Test
    void testGetTopNCountriesInWorld0() {
        a.getTopNCountriesInWorld(0);
    }

    /**
     * Test for getting top user provided (N) amount Countries in the World if input is normal
     */
    @Test
    void testGetTopNCountriesInWorldBasic() {
        a.getTopNCountriesInWorld(5);
    }

    /**
     * Test for getting all Countries in Continent
     */
    @Test
    void testGetAllCountriesInContinentBasic() {
        a.getAllCountriesInContinent("Africa");
    }

    /**
     * Test for getting all Countries in Continent if input is null
     */
    @Test
    void testGetAllCountriesInContinentNull() {
        a.getAllCountriesInContinent(null);
    }

    /**
     * Test for getting top user provided (N) amount Countries in Continent if input is null
     */
    @Test
    void testGetTopNCountriesInContinentBothWrong() {
        a.getTopNCountriesInContinent(null, 0);
    }

    /**
     * Test for getting top user provided (N) amount Countries in Continent if input is normal
     */
    @Test
    void testGetTopNCountriesInContinentWrongLimit() {
        a.getTopNCountriesInContinent("Africa", -1);
    }

    /**
     * Test for getting top user provided (N) amount Countries in Continent if input is null
     */
    @Test
    void testGetTopNCountriesInContinentWrongCont() {
        a.getTopNCountriesInContinent(null, 5);
    }

    /**
     * Test for getting top user provided (N) amount Countries in Continent if input is normal
     */
    @Test
    void testGetTopNCountriesInContinentBasic() {
        a.getTopNCountriesInContinent("Africa", 5);
    }

    /**
     * Test for getting all Countries in Region if input is normal
     */
    @Test
    void testGetAllCountriesInRegionBasic() {
        a.getAllCountriesInRegion("Baltic Countries");
    }

    /**
     * Test for getting all Countries in Region if input is null
     */
    @Test
    void testGetAllCountriesInRegionNull() {
        a.getAllCountriesInRegion(null);
    }

    /**
     * Test for getting top user provided (N) amount Countries in Region if input is null
     */
    @Test
    void testGetTopNCountriesInRegionBothWrong() {
        a.getTopNCountriesInRegion(null, 0);
    }

    /**
     * Test for getting top user provided (N) amount Countries in Region if input is normal
     */
    @Test
    void testGetTopNCountriesInRegionWrongLimit() {
        a.getTopNCountriesInRegion("Baltic Countries", -1);
    }

    /**
     * Test for getting top user provided (N) amount Countries in Region if input is null
     */
    @Test
    void testGetTopNCountriesInRegionWrongRegion() {
        a.getTopNCountriesInRegion(null, 4);
    }

    /**
     * Test for getting top user provided (N) amount Countries in Region if input is null
     */
    @Test
    void testGetTopNCountriesInRegionBasic() {
        a.getTopNCountriesInRegion("Baltic Countries", 5);
    }

    /**
     * Test for getting all Capital Cities
     */
    @Test
    void testGetAllCapitalCitiesTrue() {
        a.getAllCapitalCities();
    }

    /**
     * Test for getting all Capital Cities in Continent if input is normal
     */
    @Test
    void testGetAllCapitalCitiesInContinent() {
        a.getAllCapitalCitiesInContinent("Africa");
    }

    /**
     * Test for getting all Capital Cities in Continent if input is null
     */
    @Test
    void testGetAllCapitalCitiesInContinentWrongContinent() {
        a.getAllCapitalCitiesInContinent(null);
    }

    /**
     * Test for getting all Capital Cities in Region if input is normal
     */
    @Test
    void testGetAllCapitalCitiesInRegion() {
        a.getAllCapitalCitiesInRegion("Caribbean");
    }

    /**
     * Test for getting top user provided (N) amount Capital Cities in Region if input is null
     */
    @Test
    void testGetNCapitalCitiesInRegionWrongRegion() {
        a.getNCapitalCitiesInRegion(null, 4);
    }

    /**
     * Test for getting top user provided (N) amount Capital Cities Population if input is null
     */
    @Test
    void testGetNCapitalCitiesPopulationWrongLimit() {
        a.getNCapitalCitiesPopulation(-1);
    }

    /**
     * Test for getting top user provided (N) amount Capital Cities Population if input is normal
     */
    @Test
    void testGetNCapitalCitiesPopulationBasic() {
        a.getNCapitalCitiesPopulation(5);
    }

    /**
     * Test for getting top user provided (N) amount Capital Cities in Continent if input is null
     */
    @Test
    void testGetNCapitalCitiesInContinentBothWrong() { a.getNCapitalCitiesInContinent(null, 0); }

    /**
     * Test for getting top user provided (N) amount Capital Cities in Continent if input is normal
     */
    @Test
    void testGetNCapitalCitiesInContinentWrongLimit() {
        a.getNCapitalCitiesInContinent("Asia", -1);
    }

    /**
     * Test for getting top user provided (N) amount Capital Cities in Continent if input is null
     */
    @Test
    void testGetNCapitalCitiesInContinentWrongContinent() {
        a.getNCapitalCitiesInContinent(null, 5);
    }

    /**
     * Test for getting top user provided (N) amount Capital Cities in Continent if input is normal
     */
    @Test
    void testGetNCapitalCitiesInContinentBasic() {
        a.getNCapitalCitiesInContinent("Asia", 5);
    }

    // *** language tests ***

    /**
     * Test for getting Language
     */
    @Test
    void testLanguage(){
        a.getLanguage();
    }

    /**
     * Test for displaying Language when input is null
     */
    @Test
    void testDisplayLanguageNull(){
        displayLanguage(null);
    }

    /**
     * Test for displaying Language with no given input
     */
    @Test
    void testDisplayLanguageEmpty(){
        ArrayList languages = new ArrayList();
        displayLanguage(languages);
    }

    /**
     * Test for Class Language
     */
    @Test
    void testClassLanguage(){
        Language c = new Language();
        c.setLanguage("Chinese");
        c.setPercentage(20);
        c.setPopulation(15000);
        c.getLanguage();
        c.getPercentage();
        c.getPopulation();
    }

    /**
     * Test for displaying Language when input is normal
     */
    @Test
    void testDisplayLanguageNormal(){
        ArrayList <Language> languages = new ArrayList();
        Language l = new Language(900000, "Arabic", 17);

        languages.add(l);
        displayLanguage(languages);
    }

    /**
     * Test for displaying Language when input is null
     */
    @Test
    void testDisplayLanguageNullEntry(){
        ArrayList <Language> languages = new ArrayList();
        languages.add(null);
        displayLanguage(languages);
    }

    // *** Population reports ***

    /**
     * Test for getting City Population by Continent
     */
    @Test
    void getTestPopulationInCityByContinent(){
        a.getPopulationInCityByContinent();
    }

    /**
     * Test for getting City Population by Country
     */
    @Test
    void getTestPopulationInCityByCountry(){
        a.getPopulationInCityByCountry();
    }

    /**
     * Test for getting City Population by Region
     */
    @Test
    void getTestPopulationInCityByRegion(){
        a.getPopulationInCityByRegion();
    }

    /**
     * Test for getting City Population
     */
    @Test
    void getTestCityPopulation(){
        a.getCityPopulation("Madrid");
    }

    /**
     * Test for getting District Population
     */
    @Test
    void getTestDistrictPopulation(){
        a.getPopulationDistrict("Katsina");
    }

    /**
     * Test for getting Continent Population
     */
    @Test
    void getTestContinentPopulation(){
        a.getContinentPopulation("Europe");
    }

    /**
     * Test for getting Country Population
     */
    @Test
    void getTestCountryPopulation(){
        a.getCountryPopulation("Spain");
    }

    /**
     * Test for getting World Population
     */
    @Test
    void getTestWorldPopulation(){
        a.getWorldPopulation();
    }

    /**
     * Test for Language Class
     */
    @Test
    void testLanguageConstructor(){
        Language l = new Language();
        l.setLanguage("Russian");
        l.setPopulation(12);
        l.setPercentage(1);
        l.getLanguage();
        l.getPercentage();
        l.getPopulation();
        l.toString();

    }

    /**
     * Test for Population Class
     */
    @Test
    void testPopulationConstructor(){
        Population p = new Population();
        p.setCityPopulation(40000);
        p.setCityPopulationPercent(12);
        p.setName("Scottish");
        p.setNotCityPopulation(90000);
        p.setNonCityPopulationPercent(88);
        p.getCityPopulation();
        p.getCityPopulationPercent();
        p.getName();
        p.getNotCityPopulation();
        p.getNonCityPopulationPercent();
        p.toString();
    }

}