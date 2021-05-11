package com.example.sudokusolver;

public class Solver {

    private static int selectedCol;
    private static int selectedRow;

    Solver(){
        selectedCol = -1;
        selectedRow = -1;
    }

    public static int getSelectedRow() {
        return selectedRow;
    }

    public static void setSelectedRow(int selectedRow) {
        Solver.selectedRow = selectedRow;
    }


    public static int getSelectedCol() {
        return selectedCol;
    }

    public static void setSelectedCol(int selectedCol) {
        Solver.selectedCol = selectedCol;
    }
}
