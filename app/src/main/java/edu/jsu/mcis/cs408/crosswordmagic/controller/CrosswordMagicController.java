package edu.jsu.mcis.cs408.crosswordmagic.controller;

public class CrosswordMagicController extends AbstractController {

    public static final String GRID_DIMENSION_PROPERTY = "GridDimension";
    public static final String GRID_LETTERS_PROPERTY = "GridLetters";
    public static final String GRID_NUMBERS_PROPERTY = "GridNumbers";
    public static final String CLUE_ACROSS_PROPERTY = "CluesAcross";
    public static final String CLUE_DOWN_PROPERTY = "CluesDown";

    public void getGridDimension() { getModelProperty(GRID_DIMENSION_PROPERTY); }

    public void getGridLetters() { getModelProperty(GRID_LETTERS_PROPERTY); }

    public void getGridNumbers() { getModelProperty(GRID_NUMBERS_PROPERTY); }

    public void getCluesAcross() { getModelProperty(CLUE_ACROSS_PROPERTY);}

    public void getCluesDown() { getModelProperty(CLUE_DOWN_PROPERTY);}


}