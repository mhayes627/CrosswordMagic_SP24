package edu.jsu.mcis.cs408.crosswordmagic.model;

import android.content.Context;
import android.util.Log;

import edu.jsu.mcis.cs408.crosswordmagic.controller.CrosswordMagicController;
import edu.jsu.mcis.cs408.crosswordmagic.model.dao.DAOFactory;
import edu.jsu.mcis.cs408.crosswordmagic.model.dao.PuzzleDAO;

public class CrosswordMagicModel extends AbstractModel {

    private final int DEFAULT_PUZZLE_ID = 1;

    private Puzzle puzzle;

    public CrosswordMagicModel(Context context) {

        DAOFactory daoFactory = new DAOFactory(context);
        PuzzleDAO puzzleDAO = daoFactory.getPuzzleDAO();

        this.puzzle = puzzleDAO.find(DEFAULT_PUZZLE_ID);

    }

    public void getGridDimension() {
        Integer[] dimension = new Integer[2];

        dimension[0] = puzzle.getHeight();
        dimension[1] = puzzle.getWidth();

        firePropertyChange(CrosswordMagicController.GRID_DIMENSION_PROPERTY, null, dimension);
    }

    public void getGridLetters() {
        firePropertyChange(CrosswordMagicController.GRID_LETTERS_PROPERTY, null, puzzle.getLetters());
    }

    public void getGridNumbers() {
        firePropertyChange(CrosswordMagicController.GRID_NUMBERS_PROPERTY, null, puzzle.getNumbers());
    }

    public void getCluesAcross() {
        firePropertyChange(CrosswordMagicController.CLUE_ACROSS_PROPERTY, null, puzzle.getCluesAcross());
    }

    public void getCluesDown() {
        firePropertyChange(CrosswordMagicController.CLUE_DOWN_PROPERTY, null, puzzle.getCluesDown());
    }

}