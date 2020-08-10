package com.nasifistiak.formulator;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.card.MaterialCardView;

import java.util.HashMap;
import java.util.Map;

public class TabSolverFragment extends Fragment {
    Activity activity;
    Context context;
    String TAG = "Nasif";
    MaterialCardView cardView;
    boolean cardNotOpen = true;
    Animation slide_down;
    Animation slide_up;
    String input = "";

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

        final Map<Character, Double> variables = new HashMap<>();
        disableSelection(formulaInput);

        formulaInput.addTextChangedListener(new TextWatcher() {
            String initialChar = null;
            int initCursorPosition = 0;

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int after) {
                char addedChar = 0;

                int finalCursorPosition = formulaInput.getSelectionStart();
                if (finalCursorPosition - initCursorPosition > 0) {
                    addedChar = charSequence.charAt(finalCursorPosition - 1);
                    Log.d(TAG, "onTextChanged added: " + addedChar);
                    //added char
                    if (!variables.containsKey(addedChar)) {
                        variables.put(addedChar, 0.0);
                        // TODO: implement addFromMapToView();
                    }
                    if (cardNotOpen)
                        animateAndOpenCardView();

                } else {
                    char delChar = initialChar.charAt(initCursorPosition - 1);
                    Log.d(TAG, "onTextChanged deletedChar: " + delChar);
                    if (!variables.containsKey(delChar)) {
                        variables.remove(delChar);
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int after) {
                Log.d(TAG, "textChange beforeTextChanged: " + charSequence);
                Log.d(TAG, "textChange cursorPosition: " + formulaInput.getSelectionStart());
                initialChar = String.valueOf(charSequence);
                initCursorPosition = formulaInput.getSelectionStart();
            }
        });

    }

    private void disableSelection(EditText formulaInput) {
        formulaInput.setCustomSelectionActionModeCallback(new ActionMode.Callback() {

            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            public boolean onActionItemClicked(ActionMode actionMode, MenuItem item) {
                return false;
            }

            public void onDestroyActionMode(ActionMode actionMode) {
            }
        });

        formulaInput.setLongClickable(false);
        formulaInput.setTextIsSelectable(false);
    }

    private void animateAndOpenCardView() {
        cardView.setVisibility(View.VISIBLE);
        cardView.startAnimation(slide_down);
        cardNotOpen = false;
    }
}