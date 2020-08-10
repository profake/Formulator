package com.nasifistiak.formulator;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class TabSolverFragment extends Fragment {
    Activity activity;
    Context context;
    String TAG = "Nasif";
    String input;
    MaterialCardView cardView;
    boolean cardNotOpen = true;
    Animation slide_down;
    Animation slide_up;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getContext();
        activity = getActivity();
        return inflater.inflate(R.layout.fragment_two, container, false);
    }

    public void takeInput() {
        View view = getView();
        TextView selectFormula = view.findViewById(R.id.noSelectedFormula);
        selectFormula.setVisibility(View.GONE);
        ViewGroup viewGroup = (ViewGroup) view;
        View popup = View.inflate(viewGroup.getContext(), R.layout.takeinput_layout, viewGroup);
        final EditText formulaInput = popup.findViewById(R.id.formulaInput);
        TextView variableText = popup.findViewById(R.id.variablesText);
        variableText.setVisibility(View.VISIBLE);
        cardView = popup.findViewById(R.id.cardView);
        slide_up = AnimationUtils.loadAnimation(context, R.anim.slide_up);
        slide_down = AnimationUtils.loadAnimation(context, R.anim.slide_down);

        final Map <Character, Double> variables = new HashMap<>();

        formulaInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                char typedCharacter = (char) event.getUnicodeChar();
                String typedCharacterStr = Character.toString(typedCharacter);
                // perhaps we should just check the entire string, much easier that way

                if(input.matches("[A-Za-z]+")){
                    // alphabet
                    if(variables.containsKey(typedCharacter))
                        formulaInput.setText(input); // already have this char
                    else
                        variables.put(typedCharacter, 0.0);
                }

                if(cardNotOpen)
                    animateAndOpenCardView();
                input = formulaInput.getText().toString();

                return true;
            }
        });
    }

    private void animateAndOpenCardView() {
        cardView.setVisibility(View.VISIBLE);
        cardView.startAnimation(slide_down);
        cardNotOpen = false;
    }
}