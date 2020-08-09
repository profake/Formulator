package com.nasifistiak.formulator;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FloatingActionButton fab;
    private TabFormulasFragment formulaFragment;
    private TabSolverFragment solverFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        adapter = new TabAdapter(getSupportFragmentManager());
        formulaFragment = new TabFormulasFragment();
        solverFragment = new TabSolverFragment();
        adapter.addFragment(formulaFragment, "Formulas");
        adapter.addFragment(solverFragment, "Solve");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        fab = findViewById(R.id.floating_action_button);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);
                solverFragment.takeInput();
                fab.setVisibility(View.GONE);
            }
        });
    }
}