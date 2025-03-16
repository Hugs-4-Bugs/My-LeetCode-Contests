import java.util.*;

class Spreadsheet {
    private int[][] cells;

    public Spreadsheet(int rows) {
        cells = new int[rows][26]; // 26 columns (A-Z)
    }

    public void setCell(String cellRef, int value) {
        int column = cellRef.charAt(0) - 'A';
        int row = Integer.parseInt(cellRef.substring(1)) - 1;
        cells[row][column] = value;
    }

    public void resetCell(String cellRef) {
        int column = cellRef.charAt(0) - 'A';
        int row = Integer.parseInt(cellRef.substring(1)) - 1;
        cells[row][column] = 0;
    }

    public int getValue(String expression) {
        if (!expression.startsWith("=")) {
            return Integer.parseInt(expression);
        }

        String[] terms = expression.substring(1).split("\\+");
        int result = 0;

        for (String term : terms) {
            if (Character.isDigit(term.charAt(0))) {
                result += Integer.parseInt(term);
            } else {
                int column = term.charAt(0) - 'A';
                int row = Integer.parseInt(term.substring(1)) - 1;
                result += cells[row][column];
            }
        }

        return result;
    }
}
