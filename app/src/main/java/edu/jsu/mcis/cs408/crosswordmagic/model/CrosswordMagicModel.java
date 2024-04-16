package edu.jsu.mcis.cs408.crosswordmagic.model;

import android.content.Context;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;

import java.util.HashMap;

import edu.jsu.mcis.cs408.crosswordmagic.controller.CrosswordMagicController;
import edu.jsu.mcis.cs408.crosswordmagic.model.dao.DAOFactory;
import edu.jsu.mcis.cs408.crosswordmagic.model.dao.GuessDAO;
import edu.jsu.mcis.cs408.crosswordmagic.model.dao.PuzzleDAO;

public class CrosswordMagicModel extends AbstractModel {

    private Puzzle puzzle;
    PuzzleDAO puzzleDAO;
    GuessDAO guessDAO;
    DAOFactory daoFactory;
    int puzzleid;

    public CrosswordMagicModel(Context context, int puzzleid) {

        daoFactory = new DAOFactory(context);
        puzzleDAO = daoFactory.getPuzzleDAO();
        guessDAO = daoFactory.getGuessDAO();

        this.puzzleid = puzzleid;

        this.puzzle = puzzleDAO.find(this.puzzleid);

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

    public void getPuzzleList() {
        PuzzleListItem[] list = puzzleDAO.list();

        firePropertyChange(CrosswordMagicController.PUZZLE_LIST_PROPERTY, null, list);
    }

    public void setGuess(String guessKey) {

        String[] guess = guessKey.split(" ");

        int box = Integer.parseInt(guess[0]);
        WordDirection direction = puzzle.checkGuess(box, guess[1]);

        if (direction != null){

            HashMap<String, String> params = new HashMap<>();

            String wordKey = box + direction.toString();

            String puzzleid = String.valueOf(this.puzzleid);
            String wordid = puzzle.getWord(wordKey).getId().toString();

            params.put(daoFactory.getProperty("sql_field_puzzleid"), puzzleid);
            params.put(daoFactory.getProperty("sql_field_wordid"), wordid);

            guessDAO.create(params);

            firePropertyChange(CrosswordMagicController.GUESS_PROPERTY, null, direction);
        }
        else {
            firePropertyChange(CrosswordMagicController.GUESS_PROPERTY, null, "");
        }

    }

}