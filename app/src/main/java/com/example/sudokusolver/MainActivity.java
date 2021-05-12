package com.example.sudokusolver;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.sudokusolver.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private SudokuBoard gameBoard;
    private Solver solver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        gameBoard = findViewById(R.id.SudokuBoard);
        solver = gameBoard.getSolver();
    }

    public void buttonOnePress(View view){
        solver.setSelectedNumber(1);
        gameBoard.invalidate();
    }

    public void buttonTwoPress(View view){
        solver.setSelectedNumber(2);
        gameBoard.invalidate();
    }

    public void buttonThreePress(View view){
        solver.setSelectedNumber(3);
        gameBoard.invalidate();
    }

    public void buttonFourPress(View view){
        solver.setSelectedNumber(4);
        gameBoard.invalidate();
    }

    public void buttonFivePress(View view){
        solver.setSelectedNumber(5);
        gameBoard.invalidate();
    }

    public void buttonSixPress(View view){
        solver.setSelectedNumber(6);
        gameBoard.invalidate();
    }

    public void buttonSevenPress(View view){
        solver.setSelectedNumber(7);
        gameBoard.invalidate();
    }

    public void buttonEightPress(View view){
        solver.setSelectedNumber(8);
        gameBoard.invalidate();
    }

    public void buttonNinePress(View view){
        solver.setSelectedNumber(9);
        gameBoard.invalidate();
    }
}