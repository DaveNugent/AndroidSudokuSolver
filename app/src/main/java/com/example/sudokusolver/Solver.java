package com.example.sudokusolver;

import java.util.ArrayList;

public class Solver {

    int selectedCol;
    int selectedRow;


    int[][] board;

    Solver(){
        selectedCol = -1;
        selectedRow = -1;

        board = new int[9][9];
        for (int row = 0; row < 9; row++){
            for (int col = 0; col < 9; col++){
                board[row][col] = -1;
            }
        }
    }

    public void setSelectedNumber(int num){
        if (this.selectedCol != -1 && this.selectedRow != -1){
            // clear the number if it was already selected
            if (this.board[this.selectedRow - 1][this.selectedCol - 1] == num){
                this.board[this.selectedRow - 1][this.selectedCol - 1] = -1;
            }
            else {
                this.board[this.selectedRow - 1][this.selectedCol - 1] = num;
            }
        }
    }

    public int[][] getBoard() {
        return this.board;
    }
    public int getSelectedRow() {
        return this.selectedRow;
    }

    public void setSelectedRow(int selectedRow) {
        this.selectedRow = selectedRow;
    }


    public int getSelectedCol() {
        return this.selectedCol;
    }

    public void setSelectedCol(int selectedCol) {
        this.selectedCol = selectedCol;
    }
}
