package edu.jsu.mcis.cs408.crosswordmagic.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.beans.PropertyChangeEvent;

import edu.jsu.mcis.cs408.crosswordmagic.R;
import edu.jsu.mcis.cs408.crosswordmagic.controller.CrosswordMagicController;
import edu.jsu.mcis.cs408.crosswordmagic.databinding.ActivityMainBinding;
import edu.jsu.mcis.cs408.crosswordmagic.model.CrosswordMagicModel;

public class MainActivity extends AppCompatActivity implements AbstractView {

    private final String TAG = "MainActivity";

    private ActivityMainBinding binding;

    private CrosswordMagicController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        /* Create Controller and Model */

        controller = new CrosswordMagicController();

        Integer puzzleid = 0;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            puzzleid = extras.getInt("puzzleid");
        }

        CrosswordMagicModel model = new CrosswordMagicModel(this, puzzleid);

        /* Register View(s) and Model(s) with Controller */

        controller.addModel(model);
        controller.addView(this);

    }

    public CrosswordMagicController getController() { return controller; }

    @Override
    public void modelPropertyChange(final PropertyChangeEvent evt) {

        String name = evt.getPropertyName();
        Object value = evt.getNewValue();

        String message;

        if(name.equals(CrosswordMagicController.GUESS_PROPERTY)){

            if (value.toString().isEmpty()) {
                message = getResources().getString(R.string.wrong_guess);
            }
            else {
                message = getResources().getString(R.string.right_guess);
            }
            Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
            toast.show();
        }

    }

}