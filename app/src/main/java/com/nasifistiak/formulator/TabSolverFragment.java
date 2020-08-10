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

public class TabSolverFragment extends Fragment {
    Activity activity;
    Context context;
    EditText formulaBox;
    String TAG = "Nasif";
    int numberOfEquals = 0;
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
        ArrayList <Float> variables;
        cardView = popup.findViewById(R.id.cardView);
        slide_up = AnimationUtils.loadAnimation(context, R.anim.slide_up);
        slide_down = AnimationUtils.loadAnimation(context, R.anim.slide_down);

        formulaInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                input = formulaInput.getText().toString();
                if (keyCode == KeyEvent.KEYCODE_EQUALS || keyCode == KeyEvent.KEYCODE_NUMPAD_EQUALS)
                    numberOfEquals++;
                if(cardNotOpen)
                    animateAndOpenCardView();
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