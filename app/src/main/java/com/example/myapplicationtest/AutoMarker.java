package com.example.myapplicationtest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AutoMarker {
    Button[] grid;
    AutoMarker(Button[] grid) {
        this.grid = grid;
    }
    public Boolean checkColumn(int columnIndex) {
        Button[] column = new Button[3];
        for (int i = 0; i < 3; i++) {
            column[i] = grid[i+columnIndex*3];
        }
        if (!(column[0].getText().equals("-")) && !(column[1].getText().equals("-")) && !(column[2].getText().equals("-"))) {
            return column[0].getText().equals(column[1].getText()) && column[1].getText().equals(column[2].getText());
        }
        else {
            return false;
        }
    }
    public Boolean checkRow(int rowIndex) {
        Button[] row = new Button[3];
        int j=0;
        for (int i = rowIndex; i < 9; i=i+3) {
            row[j] = grid[i];
            j++;
        }
        if (!(row[0].getText().equals("-")) && !(row[1].getText().equals("-")) && !(row[2].getText().equals("-"))) {
            return row[0].getText().equals(row[1].getText()) && row[1].getText().equals(row[2].getText());
        }
        else {
            return false;
        }
    }
    public Boolean checkAscendingSlant() {
        if (!(grid[2].getText().equals("-")) && !(grid[4].getText().equals("-")) && !(grid[6].getText().equals("-"))) {
            return grid[2].getText().equals(grid[4].getText()) && grid[4].getText().equals(grid[6].getText());
        }
        else {
            return false;
        }
    }
    public Boolean checkDescendingSlant() {
        if (!(grid[0].getText().equals("-")) && !(grid[4].getText().equals("-")) && !(grid[8].getText().equals("-"))) {
            return grid[0].getText().equals(grid[4].getText()) && grid[4].getText().equals(grid[8].getText());
        }
        else {
            return false;
        }
    }
    public Boolean fullCheck() {
        return checkColumn(0) || checkColumn(1) || checkColumn(2) || checkRow(0) || checkRow(1) || checkRow(2) || checkAscendingSlant() || checkDescendingSlant();
    }
}
