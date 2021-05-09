package com.example.sudokusolver;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class SudokuBoard extends View {

    private final int boardColor;
    private final Paint boardColorPaint = new Paint();
    int cellSize = 5;

    public SudokuBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray attribs = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SudokuBoard,
                                                                        0, 0);

        try {
            boardColor = attribs.getInteger(R.styleable.SudokuBoard_boardColor, 0);
        } finally {
            attribs.recycle();
        }
    }

    @Override
    protected void onMeasure(int width, int height){
        super.onMeasure(width, height);
        int sideLength = Math.min(this.getMeasuredWidth(), this.getMeasuredHeight());
        cellSize = sideLength/9;
        setMeasuredDimension(sideLength, sideLength);
    }

    @Override
    protected void onDraw(Canvas canvas){
        boardColorPaint.setStyle(Paint.Style.STROKE);
        boardColorPaint.setStrokeWidth(16);
        boardColorPaint.setColor(boardColor);
        boardColorPaint.setAntiAlias(true);

        canvas.drawRect(0, 0, getWidth(), getHeight(), boardColorPaint);
        drawBoard(canvas);
    }

    private void drawBoard(Canvas canvas){
        final int THIN_LINE = 4;
        final int THICK_LINE = 10;

        boardColorPaint.setStyle(Paint.Style.STROKE);
        boardColorPaint.setColor(boardColor);

        // Draw all columns
        for (int col = 0; col < 10; col++){
            if(col%3 != 0){
                boardColorPaint.setStrokeWidth(THIN_LINE);
                canvas.drawLine(cellSize * col, 0, cellSize * col,
                        getWidth(), boardColorPaint);
            } else {
                boardColorPaint.setStrokeWidth(THICK_LINE);
            }
            canvas.drawLine(cellSize * col, 0, cellSize * col,
                    getWidth(), boardColorPaint);
        }

        // Draw all rows
        for (int row = 0; row < 10; row++){
            if(row%3 != 0){
                boardColorPaint.setStrokeWidth(THIN_LINE);
            } else {
                boardColorPaint.setStrokeWidth(THICK_LINE);
            }
            canvas.drawLine(0, cellSize * row, getHeight(),
                    cellSize * row, boardColorPaint);
        }

    }
}
