package com.napier.sem;

import com.mysql.cj.protocol.Resultset;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.ResultSet;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest {
    static CityQuery app;

    @BeforeAll
    static void init() {
        app = new CityQuery();
        app.connect("localhost:33060", 30000);
    }


    @Test
    void testgetNCitiesInContinent() {
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
    void testgetNCitiesInWorld() {
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
    void testgetNCitiesInDistrict() {
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
}