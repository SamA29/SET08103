package com.napier.sem;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest {
    static Reports app;

    @BeforeAll
    static void init() {
        app = new Reports();
        app.connect("localhost:33060", 30000);
    }


    @Test
    void testGetNCitiesInContinent() {
        ArrayList<City> top5 = app.getNCitiesInContinent(2, "Asia");
        assertEquals(top5.size(), 2);
        assertEquals(top5.get(0).getName(), "Mumbai (Bombay)");
        assertEquals(top5.get(0).getCountry(), "India");
        assertEquals(top5.get(0).getDistrict(), "Maharashtra");
        assertEquals(top5.get(0).getPopulation(), 10500000);
        assertEquals(top5.get(1).getName(), "Seoul");
        top5.get(1).setDistrict("exampleDistrict");
        top5.get(1).setName("myName");
        top5.get(1).setCountry("myCountry");
        top5.get(1).setPopulation(456);
        assertEquals(top5.get(1).getName(), "myName");
        assertEquals(top5.get(1).getCountry(), "myCountry");
        assertEquals(top5.get(1).getDistrict(), "exampleDistrict");
        assertEquals(top5.get(1).getPopulation(), 456);
    }

    @Test
    void testGetNCitiesInWorld() {
        ArrayList<City> top5 = app.getNCitiesInWorld(2);
        assertEquals(top5.size(), 2);
        assertEquals(top5.get(0).getName(), "Mumbai (Bombay)");
        assertEquals(top5.get(1).getName(), "Seoul");
    }

    @Test
    void testgetNCitiesInRegion() {
        ArrayList<City> top5 = app.getNCitiesInRegion(2, "Middle East");
        assertEquals(top5.size(), 2);
        assertEquals(top5.get(0).getName(), "Istanbul");
        assertEquals(top5.get(1).getName(), "Baghdad");
    }

    @Test
    void testGetAllCitiesInRegion() {
        ArrayList<City> top5 = app.getNCitiesInRegion(2, "Middle East");
        assertEquals(top5.size(), 2);
        assertEquals(top5.get(0).getName(), "Istanbul");
        assertEquals(top5.get(1).getName(), "Baghdad");
    }

    @Test
    void testGetAllCitiesInContinent() {
        ArrayList<City> top5 = app.getAllCitiesInContinent("Asia");
        // assertEquals(top5.size(), 2);
        assertEquals(top5.get(0).getName(), "Mumbai (Bombay)");
        assertEquals(top5.get(1).getName(), "Seoul");
    }

    @Test
    void testGetAllCitiesInWorld() {
        ArrayList<City> top5 = app.getAllCitiesInWorld();
        // assertEquals(top5.size(), 2);
        assertEquals(top5.get(0).getName(), "Mumbai (Bombay)");
        assertEquals(top5.get(1).getName(), "Seoul");
    }

    @Test
    void testGetNCitiesInDistrict() {
        ArrayList<City> top5 = app.getNCitiesInDistrict(2, "Île-de-France");
        assertEquals(top5.size(), 2);
        assertEquals(top5.get(0).getName(), "Paris");
        assertEquals(top5.get(1).getName(), "Boulogne-Billancourt");
    }

    @Test
    void testGetAllCitiesInDistrict() {
        ArrayList<City> top5 = app.getNCitiesInDistrict(2, "Île-de-France");
        assertEquals(top5.size(), 2);
        assertEquals(top5.get(0).getName(), "Paris");
        assertEquals(top5.get(1).getName(), "Boulogne-Billancourt");
    }
    @Test
    void testGetTopNCountriesInContinent() {
        ArrayList<Country> top5 = app.getTopNCountriesInContinent("Asia", 2);
        assertEquals(top5.size(), 2);
        assertEquals(top5.get(0).getName(), "China");
        assertEquals(top5.get(1).getName(), "India");
    }

    @Test
    void testGetTopNCountriesInWorld() {
        ArrayList<Country> top5 = app.getTopNCountriesInWorld(2);
        assertEquals(top5.size(), 2);
        assertEquals(top5.get(0).getName(), "China");
        assertEquals(top5.get(1).getName(), "India");
    }

    @Test
    void testGetTopNCountriesInWorldNull() {
        assertNull(app.getTopNCountriesInWorld(0));
    }

    @Test
    void testGetTopNCountriesInRegion() {
        ArrayList<Country> top5 = app.getTopNCountriesInRegion("Nordic Countries", 2);
        assertEquals(top5.size(), 2);
        assertEquals(top5.get(0).getName(), "Sweden");
        assertEquals(top5.get(1).getName(), "Denmark");
    }

    @Test
    void testGetAllCountriesInRegion() {
        ArrayList<Country> top5 = app.getAllCountriesInRegion("Nordic Countries");
        //assertEquals(top5.size(), 2);
        assertEquals(top5.get(0).getName(), "Sweden");
        assertEquals(top5.get(1).getName(), "Denmark");
    }

    @Test
    void testGetAllCountriesInContinent() {
        ArrayList<Country> top5 = app.getAllCountriesInContinent("Asia");
        //assertEquals(top5.size(), 2);
        assertEquals(top5.get(0).getName(), "China");
        assertEquals(top5.get(1).getName(), "India");
    }

    @Test
    void testGetAllCountriesInContinentNull() {
        assertNull(app.getAllCountriesInContinent("MockContinent"));
    }

    @Test
    void testGetAllCountriesInWorld() {
        ArrayList<Country> top5 = app.getAllCountriesInWorld();
        //assertEquals(top5.size(), 2);
        assertEquals(top5.get(0).getName(), "China");
        assertEquals(top5.get(1).getName(), "India");
    }

    @Test
    void testGetNCapitalCitiesInRegion() {
        ArrayList<CapitalCity> top5 = app.getNCapitalCitiesInRegion("Southern Europe", 5);
        //  assertEquals(top5.size(), 2);
        assertEquals(top5.get(0).getName(), "Madrid");
        assertEquals(top5.get(1).getName(), "Roma");
    }
    @Test
    void testGetNCapitalCitiesPopulation() {
        ArrayList<CapitalCity> top5 = app.getNCapitalCitiesPopulation(5);
        //  assertEquals(top5.size(), 2);
        assertEquals(top5.get(0).getName(), "Seoul");
        assertEquals(top5.get(1).getName(), "Jakarta");
    }
    @Test
    void testGetNCapitalCitiesInContinent() {
        ArrayList<CapitalCity> top5 = app.getNCapitalCitiesInContinent("Asia", 5);
        //  assertEquals(top5.size(), 2);
        assertEquals(top5.get(0).getName(), "Seoul");
        assertEquals(top5.get(1).getName(), "Jakarta");
    }

    @Test // first
    void testGetPopulationInCityByCountry(){
        ArrayList<Population> top5 = app.getPopulationInCityByCountry();
        assertEquals(top5.get(0).getName(), "Afghanistan");
        assertEquals(top5.get(1).getName(), "Albania");
    }

    @Test // second
    void testGetPopulationInCityByRegion(){
        ArrayList<Population> top5 = app.getPopulationInCityByRegion();
        assertEquals(top5.get(0).getName(), "Australia and New Zealand");
        assertEquals(top5.get(0).getPopulation(), 22753100);
        assertEquals(top5.get(0).getNotCityPopulation(), 9589664);
        assertEquals(top5.get(0).getNonCityPopulationPercent(), 42 );
        assertEquals(top5.get(0).getCityPopulation(), 13163436 );
        assertEquals(top5.get(0).getCityPopulationPercent(), 58 );
        assertEquals(top5.get(0).getName(), "Australia and New Zealand");
        assertEquals(top5.get(1).toString(), "Baltic Countries 7561900 2947140 39.0% 4614760 61.0% \n");
        assertEquals(top5.get(1).getName(), "Baltic Countries");
    }
    @Test // third
    void testGetLanguage(){
        ResultSet rset = app.getLanguage();
        assertNotNull(rset);
    }
    @Test //fourth
    void testGetPopulationInCityByContinent(){
        ArrayList<Population> output =  app.getPopulationInCityByContinent();

        assertEquals(output.get(0).getName(), "Asia");
        assertEquals(output.get(1).getName(), "Europe");
    }
    @Test //fifth
    void testLanguage(){
        Language l = new Language(100000, "Chinese", 10);
        Language n = new Language();
        l.setLanguage("Chinese");
        l.setPercentage(10);
        l.setPopulation(100000);
        assertEquals(l.getLanguage(), "Chinese");
        assertEquals(l.getPopulation(), 100000);
        assertEquals(l.getPercentage(), 10);
        assertEquals(n.getLanguage(), null);
        assertEquals(n.getPopulation(), 0);
        assertEquals(n.getPercentage(), 0);
    }
}