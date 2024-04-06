package edu.jsu.mcis.cs408.crosswordmagic.model.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;

import edu.jsu.mcis.cs408.crosswordmagic.model.Word;

public class WordDAO {

    private final DAOFactory daoFactory;

    WordDAO(DAOFactory daoFactory) {
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

        String puzzleid = daoFactory.getProperty("sql_field_puzzleid");
        String row = daoFactory.getProperty("sql_field_row");
        String column = daoFactory.getProperty("sql_field_column");
        String box = daoFactory.getProperty("sql_field_box");
        String direction = daoFactory.getProperty("sql_field_direction");
        String word = daoFactory.getProperty("sql_field_word");
        String clue = daoFactory.getProperty("sql_field_clue");

        ContentValues values = new ContentValues();
        values.put(puzzleid, Integer.parseInt(params.get(puzzleid)));
        values.put(row, Integer.parseInt(params.get(row)));
        values.put(column, Integer.parseInt(params.get(column)));
        values.put(box, Integer.parseInt(params.get(box)));
        values.put(direction, Integer.parseInt(params.get(direction)));
        values.put(word, params.get(word));
        values.put(clue, params.get(clue));

        int key = (int) db.insert(daoFactory.getProperty("sql_table_words"), null, values);

        return key;

    }

    public Word find(int id) {

        /* use this method if there is NOT already a SQLiteDatabase open */

        SQLiteDatabase db = daoFactory.getWritableDatabase();
        Word result = find(db, id);
        db.close();
        return result;

    }

    public Word find(SQLiteDatabase db, int id) {

        /* use this method if there is NOT already a SQLiteDatabase open */

        Word w = null;

        /* get list of words (if any) to add to puzzle */

        String query = daoFactory.getProperty("sql_get_word");
        Cursor cursor = db.rawQuery(query, new String[]{ String.valueOf(id) });

        if (cursor.moveToFirst()) {

            cursor.moveToFirst();

            HashMap<String, String> params;

            do {

                params = new HashMap<>();

                /* get data for the next word in the puzzle */

                String wordid = daoFactory.getProperty("sql_field_id");
                String puzzleid = daoFactory.getProperty("sql_field_puzzleid");
                String row = daoFactory.getProperty("sql_field_row");
                String column = daoFactory.getProperty("sql_field_column");
                String box = daoFactory.getProperty("sql_field_box");
                String direction = daoFactory.getProperty("sql_field_direction");
                String word = daoFactory.getProperty("sql_field_word");
                String clue = daoFactory.getProperty("sql_field_clue");

                params.put(wordid, String.valueOf(id));
                params.put(puzzleid, cursor.getString(1));
                params.put(row, cursor.getString(2));
                params.put(column, cursor.getString(3));
                params.put(box, cursor.getString(4));
                params.put(direction, cursor.getString(5));
                params.put(word, cursor.getString(6));
                params.put(clue, cursor.getString(7));

                if (!params.isEmpty())
                    w = new Word(params);

            }
            while ( cursor.moveToNext() );

            cursor.close();

        }

        return w;

    }

}
