import java.util.HashMap;
import java.util.Map;

class Spreadsheet {
    private Map<String, Integer> cellValues;

    public Spreadsheet(int rows) {
        // Rows are fixed at initialization, but not directly used here
        this.cellValues = new HashMap<>();
    }

    public void setCell(String cell, int value) {
        cellValues.put(cell, value);
    }

    public void resetCell(String cell) {
        cellValues.put(cell, 0);
    }

    public int getValue(String formula) {
        // Remove '=' from beginning
        formula = formula.substring(1);

        // Split by '+'
        String[] operands = formula.split("\\+");
        String leftOperand = operands[0];
        String rightOperand = operands[1];

        int result = 0;

        // Process left operand
        if (leftOperand.matches("\\d+")) {
            result += Integer.parseInt(leftOperand);
        } else {
            result += cellValues.getOrDefault(leftOperand, 0);
        }

        // Process right operand
        if (rightOperand.matches("\\d+")) {
            result += Integer.parseInt(rightOperand);
        } else {
            result += cellValues.getOrDefault(rightOperand, 0);
        }

        return result;
    }

   
}
