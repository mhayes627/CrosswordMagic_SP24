package edu.jsu.mcis.cs408.crosswordmagic.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.beans.PropertyChangeEvent;

import edu.jsu.mcis.cs408.crosswordmagic.controller.CrosswordMagicController;
import edu.jsu.mcis.cs408.crosswordmagic.databinding.FragmentClueBinding;

public class ClueFragment extends Fragment implements AbstractView{

    FragmentClueBinding binding;
    private CrosswordMagicController controller;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentClueBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.controller = ((MainActivity)getContext()).getController();
        controller.addView(this);

        controller.getCluesAcross();
        controller.getCluesDown();

    }

    @Override
    public void modelPropertyChange(PropertyChangeEvent evt) {
        String name = evt.getPropertyName();
        Object value = evt.getNewValue();



        if (name.equals(CrosswordMagicController.CLUE_ACROSS_PROPERTY)) {

            binding.aContainer.setText(value.toString());

        }

        if (name.equals(CrosswordMagicController.CLUE_DOWN_PROPERTY)) {

            binding.dContainer.setText(value.toString());

        }

    }
}
