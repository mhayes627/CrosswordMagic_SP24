package edu.jsu.mcis.cs408.crosswordmagic.controller;

public class CrosswordMagicController extends AbstractController {

    public static final String TEST_PROPERTY = "TestProperty";
    public static final String GRID_DIMENSION_PROPERTY = "Dimension";
    public static final String GRID_LETTERS_PROPERTY = "Letters";
    public static final String GRID_NUMBERS_PROPERTY = "Numbers";

    public void getTestProperty(String value) {
        getModelProperty(TEST_PROPERTY);
    }
    public void getGridDimensions() { getModelProperty(GRID_DIMENSION_PROPERTY); }
    public void getGridLetters() { getModelProperty(GRID_LETTERS_PROPERTY); }

    public void getGridNumbers() { getModelProperty(GRID_NUMBERS_PROPERTY); }

}