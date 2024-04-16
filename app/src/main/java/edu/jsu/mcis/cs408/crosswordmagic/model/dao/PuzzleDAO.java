package edu.jsu.mcis.cs408.crosswordmagic.model.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import edu.jsu.mcis.cs408.crosswordmagic.model.Puzzle;
import edu.jsu.mcis.cs408.crosswordmagic.model.PuzzleListItem;
import edu.jsu.mcis.cs408.crosswordmagic.model.Word;
import edu.jsu.mcis.cs408.crosswordmagic.model.WordDirection;

public class PuzzleDAO {

    private final DAOFactory daoFactory;

    PuzzleDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public int create(HashMap<String, String> params) {

        /* use this method if there is NOT already a SQLiteDatabase open */

        SQLiteDatabase db = daoFactory.getWritableDatabase();
        int result = create(db, params);
        db.close();
        return result;

    }

    public int create(SQLiteDatabase db, HashMap<String, String> params) {

        /* use this method if there IS already a SQLiteDatabase open */

        String name = daoFactory.getProperty("sql_field_name");
        String description = daoFactory.getProperty("sql_field_description");
        String height = daoFactory.getProperty("sql_field_height");
        String width = daoFactory.getProperty("sql_field_width");

        ContentValues values = new ContentValues();
        values.put(name, params.get(name));
        values.put(description, params.get(description));
        values.put(height, Integer.parseInt(params.get(height)));
        values.put(width, Integer.parseInt(params.get(width)));

        int key = (int)db.insert(daoFactory.getProperty("sql_table_puzzles"), null, values);

        return key;

    }

    public Puzzle find(int id) {

        /* use this method if there is NOT already a SQLiteDatabase open */

        SQLiteDatabase db = daoFactory.getWritableDatabase();
        Puzzle result = find(db, id);
        db.close();
        return result;

    }

    public Puzzle find(SQLiteDatabase db, int id) {

        /* use this method if there is NOT already a SQLiteDatabase open */

        Puzzle puzzle = null;

        String query = daoFactory.getProperty("sql_get_puzzle");
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});

        if (cursor.moveToFirst()) {

            cursor.moveToFirst();

            HashMap<String, String> params = new HashMap<>();

            /* get data for new puzzle */

            params.put(daoFactory.getProperty("sql_field_name"), cursor.getString(1));
            params.put(daoFactory.getProperty("sql_field_description"), cursor.getString(2));
            params.put(daoFactory.getProperty("sql_field_height"), cursor.getString(3));
            params.put(daoFactory.getProperty("sql_field_width"), cursor.getString(4));

            if (!params.isEmpty())
                puzzle = new Puzzle(params);

            /* get list of words (if any) to add to puzzle */

            query = daoFactory.getProperty("sql_get_words");
            cursor = db.rawQuery(query, new String[]{ String.valueOf(id) });

            if (cursor.moveToFirst()) {

                cursor.moveToFirst();

                do {

                    int wordid = cursor.getInt(0);

                    WordDAO wordDAO = new WordDAO(daoFactory);

                    Word word = wordDAO.find(db, wordid);

                    if (word != null)
                        puzzle.addWordToPuzzle(word);

                }
                while (cursor.moveToNext());

                cursor.close();

            }

           /* get list of already-guessed words (if any) from "guesses" table */

            query = daoFactory.getProperty("sql_get_guesses");
            cursor = db.rawQuery(query, new String[]{String.valueOf(id)});

            if (cursor.moveToFirst()) {

                cursor.moveToFirst();

                do {

                    Integer box = cursor.getInt(4);
                    WordDirection direction = WordDirection.values()[cursor.getInt(5)];

                    if (puzzle != null)
                        puzzle.addWordToGuessed(box + direction.toString());

                }
                while (cursor.moveToNext());

                cursor.close();

            }

        }

        return puzzle;

    }

    public PuzzleListItem[] list() {

        /* use this method if there is NOT already a SQLiteDatabase open */

        SQLiteDatabase db = daoFactory.getWritableDatabase();
        PuzzleListItem[] result = list(db);
        db.close();
        return result;

    }

    public PuzzleListItem[] list(SQLiteDatabase db) {
        ArrayList<PuzzleListItem> puzzles = new ArrayList<>();

        String query = daoFactory.getProperty("sql_get_puzzles");
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {

            cursor.moveToFirst();
            do {

                int id = cursor.getInt(0);
                String name = cursor.getString(1);

                PuzzleListItem puzzle = new PuzzleListItem(id, name);

                puzzles.add(puzzle);

            }
            while (cursor.moveToNext());

            cursor.close();

        }

        return puzzles.toArray(new PuzzleListItem[]{});
    }

}