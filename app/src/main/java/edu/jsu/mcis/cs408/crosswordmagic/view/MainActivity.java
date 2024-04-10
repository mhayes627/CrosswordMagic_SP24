package edu.jsu.mcis.cs408.crosswordmagic.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.beans.PropertyChangeEvent;

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

        CrosswordMagicModel model = new CrosswordMagicModel(this);

        /* Register View(s) and Model(s) with Controller */

        controller.addModel(model);
        controller.addView(this);

    }

    public CrosswordMagicController getController() { return controller; }

    @Override
    public void modelPropertyChange(final PropertyChangeEvent evt) {


    }

}