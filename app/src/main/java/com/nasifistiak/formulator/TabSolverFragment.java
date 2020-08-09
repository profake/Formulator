package com.nasifistiak.formulator;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class TabSolverFragment extends Fragment {
    Activity activity;
    Context context;
    EditText formulaBox;
    String TAG = "Nasif";
    boolean haveEqual;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getContext();
        activity = getActivity();
        return inflater.inflate(R.layout.fragment_two, container, false);
    }

    public void takeInput(){
        View view = getView();
        TextView selectFormula = view.findViewById(R.id.noSelectedFormula);
        selectFormula.setVisibility(View.GONE);
        ViewGroup viewGroup = (ViewGroup) view;
        View popup = View.inflate(viewGroup.getContext(), R.layout.takeinput_layout, viewGroup);
        EditText formulaInput = popup.findViewById(R.id.formulaInput);
        formulaInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_EQUALS) {
                    haveEqual = true;
                    return false;
                }
                return true;
            }
        });
    }
}