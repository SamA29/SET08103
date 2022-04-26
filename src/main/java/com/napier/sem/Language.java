package com.napier.sem;

public class Language {


    // Properties population
    /**
     * Population of Speakers
     */
    private long population;

    /**
     * Language
     */
    private String language;

    /**
     * Percentage of the world population
     */
    private float percentage;


    // Constructors

    /**
     * Blank Language Constructor
     */
    public Language() {
    }

    /**
     * Constructor for language class
     *
     * @param population Population of Speakers
     * @param language   Language Name
     * @param percentage Percentage of world population
     */
    public Language(long population, String language, float percentage) {
        this.population = population;
        this.language = language;
        this.percentage = percentage;
    }

    // Getters

    /**
     * Returns value of _population
     */
    public long getPopulation() {
        return population;
    }

    /**
     * Returns value of _language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Returns value of _percentage
     */
    public float getPercentage() {
        return percentage;
    }

    // Setters

    /**
     * Sets public population property
     *
     * @param population population of speakers
     */
    public void setPopulation(long population) {
        this.population = population;
    }

    /**
     * Sets public language property
     *
     * @param language language name
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Sets public population property
     *
     * @param percentage percentage of world population
     */
    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }
}
