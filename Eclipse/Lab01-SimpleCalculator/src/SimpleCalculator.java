
/**
 * @author jake
 * @version 2017.01.29
 */
public class SimpleCalculator {
    private double operand1;
    private double operand2;
    private char operator;

    /**
     * @param inOperand1
     *            sets operand1
     * @param inOperand2
     *            sets operand2
     * @param inOperator
     *            sets operator
     */
    public SimpleCalculator(double inOperand1, double inOperand2,
            char inOperator) {
        operand1 = inOperand1;
        operand2 = inOperand2;
        operator = inOperator;
    }

    /**
     * @return operand1
     */
    public double getOperand1() {
        return operand1;
    }

    /**
     * @param operand1
     *            sets operand1
     */
    public void setOperand1(double operand1) {
        this.operand1 = operand1;
    }

    /**
     * @return operand2
     */
    public double getOperand2() {
        return operand2;
    }

    /**
     * @param operand2
     *            sets operand2
     */
    public void setOperand2(double operand2) {
        this.operand2 = operand2;
    }

    /**
     * @return operator
     */
    public char getOperator() {
        return operator;
    }

    /**
     * @param operator
     *            the operator to set
     */
    public void setOperator(char operator) {
        this.operator = operator;
    }

    /**
     * @return the equation
     */
    public double computeOperation() {
        if (operator == '+') {
            return operand1 + operand2;
        }
        else if (operator == '-') {
            return operand1 - operand2;
        }
        else if (operator == '*') {
            return operand1 * operand2;
        }
        else {
            return operand1 / operand2;
        }
    }
}
