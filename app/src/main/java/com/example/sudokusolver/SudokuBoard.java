package com.example.sudokusolver;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class SudokuBoard extends View {

    private final int boardColor;
    private final int selectedCellColor;
    private final int highLightColor;
    private final int numberColor;
    private final Paint boardColorPaint = new Paint();
    private final Paint selectedCellColorPaint = new Paint();
    private final Paint highLightColorPaint = new Paint();
    private final Paint numberColorPaint = new Paint();
    private final Rect numberPaintBounds = new Rect();
    int cellSize;

    private final Solver solver = new Solver();

    public SudokuBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray attribs = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SudokuBoard,
                                                                        0, 0);

        try {
            boardColor = attribs.getInteger(R.styleable.SudokuBoard_boardColor, 0);
            selectedCellColor = attribs.getInteger(R.styleable.SudokuBoard_selectedCellColor, 0);
            highLightColor = attribs.getInteger(R.styleable.SudokuBoard_highLightColor, 0);
            numberColor = attribs.getInteger(R.styleable.SudokuBoard_numberColor, 0);
        } finally {
            attribs.recycle();
        }
    }

    public Solver getSolver() {
        return solver;
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

        selectedCellColorPaint.setStyle(Paint.Style.FILL);
        selectedCellColorPaint.setColor(selectedCellColor);
        selectedCellColorPaint.setAntiAlias(true);

        highLightColorPaint.setStyle(Paint.Style.FILL);
        highLightColorPaint.setColor(highLightColor);
        highLightColorPaint.setAntiAlias(true);

        numberColorPaint.setStyle(Paint.Style.FILL);
        numberColorPaint.setAntiAlias(true);
        numberColorPaint.setTextSize(cellSize);
        numberColorPaint.setColor(numberColor);

        highLightCell(canvas, solver.getSelectedRow(), solver.getSelectedCol());
        canvas.drawRect(0, 0, getWidth(), getHeight(), boardColorPaint);
        drawBoard(canvas);
        drawNumbers(canvas);
    }

    private void drawBoard(Canvas canvas){
        final int THIN_LINE = 4;
        final int THICK_LINE = 10;

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

    private void drawNumbers(Canvas canvas){

        for (int row = 0; row < 9; row++){
            for (int col = 0; col < 9; col++){
                if (solver.getBoard()[row][col] != -1) {
                    String num = Integer.toString((solver.getBoard()[row][col]));
                    numberColorPaint.getTextBounds(num, 0, num.length(), numberPaintBounds);
                    float width = numberColorPaint.measureText(num);
                    float height = numberPaintBounds.height();

                    canvas.drawText(num, (col*cellSize) + ((cellSize - width)/2), ((row + 1)*cellSize) - ((cellSize - height)/2), numberColorPaint);
                }
            }
        }
    }

    private void highLightCell(Canvas canvas, int row, int col){
        if(solver.getSelectedRow() != -1 && solver.getSelectedCol() != -1){
            canvas.drawRect((col - 1) * cellSize, 0, col * cellSize,
                    cellSize * 9, highLightColorPaint);

            canvas.drawRect(0, (row - 1) * cellSize, cellSize * 9,
                    row * cellSize, highLightColorPaint);


            canvas.drawRect((col - 1) * cellSize, (row - 1) * cellSize, col * cellSize,
                    row * cellSize, selectedCellColorPaint);
        }
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        boolean isValid = false;

        float x = event.getX();
        float y = event.getY();
        int action = event.getAction();

        if (action == MotionEvent.ACTION_DOWN) {
            solver.setSelectedRow((int)Math.ceil(y/cellSize));
            solver.setSelectedCol((int)Math.ceil(x/cellSize));
            isValid = true;
        }

        return isValid;
    }
}
