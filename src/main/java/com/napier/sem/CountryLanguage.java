package com.napier.sem;

/**
 * Represents a Language. Class representation of the table created in world.sql
 * @author Pablo Sanchez
 */


public class CountryLanguage {
    /**
     * Code of the language
     */
    private String code;

    /**
     * Name of the language
     */
    private String language;

    /**
     *  Status of the Language
     */
    private Boolean isOfficial;

    /**
     * Percentage of the language
     */
    private String percentage;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Boolean getOfficial() {
        return isOfficial;
    }

    public void setOfficial(Boolean official) {
        isOfficial = official;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }
}
