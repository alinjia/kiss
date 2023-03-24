import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class FractionOperationStack {

    private ArrayList<String> myStringList;
    private Stack<Fraction> myStack = new Stack<>();

    public FractionOperationStack(String myString){
        this.myStringList = new ArrayList<>(Arrays.asList(myString.split(" ")));
    }
    //дробь записываем в стэк знак исполняем
    public void calculate() throws InvalidExpression {
        for (int i = 0; i < myStringList.size(); i++){
            if (isFraction(myStringList.get(i))){
                myStack.push(new Fraction(myStringList.get(i)));
            }
            else{
                Fraction temp1 = (myStack.pop());
                Fraction temp2 = (myStack.pop());
                switch(myStringList.get(i)){
                    case "+":
                        myStack.push(Fraction.sum(temp1, temp2));
                        break;
                    case "-":
                        myStack.push(Fraction.subtract(temp2, temp1));
                        break;
                    case "*":
                        myStack.push(Fraction.multiply(temp1, temp2));
                        break;
                    case ":":
                        myStack.push(Fraction.divide(temp2, temp1));
                        break;
                }
            }
        }
        if (myStack.empty()){
            System.out.println("Something went bad, check the number of variables and operators");
        }
        else{
            System.out.println("Your answer is: " + myStack.peek());
        }
    }

    public void solve() throws InvalidExpression{
        if (checkMyExpression()){
            calculate();
        }
        else {
            System.out.println("Something went bad");
        }
    }

    private boolean checkMyExpression(){ //проверка на правильность введенного выражения
        boolean trigger = true;
        int digitCounter = 0; //проверка на правильность количества операторов и дробей
        int operatorCounter = 0;
        if(!(isFraction(myStringList.get(0)) | isFraction(myStringList.get(1)))){
            trigger = false;
        }
        for (int i = 0; i < myStringList.size(); i ++){
            if (!(isFraction(myStringList.get(i))) && !(myStringList.get(i).equals("+"))
                    && !(myStringList.get(i).equals("-"))
                    && !(myStringList.get(i).equals("*"))
                    && !(myStringList.get(i).equals(":"))){
                trigger = false;
                break;
            }
            if (isFraction(myStringList.get(i))){
                digitCounter++;
            }
            else operatorCounter++;
        }
        if (digitCounter <= operatorCounter) trigger = false;
        return trigger;
    }
    private static boolean isFraction(String myString) { //проверка на дробь
        if (!(myString.equals("+"))
                && !(myString.equals("-"))
                && !(myString.equals("*"))
                && !(myString.equals(":"))){
            return true;
        }
        else{
            return false;
        }
    }

    public static String convertToReversePolishNotation(String expression){ //перевод в обратную польскую нотацию
        ArrayList<String> myStringExpression = new ArrayList<>(Arrays.asList(expression.split(" "))); //сплитим по пробелу
        Stack<String> myStringStack = new Stack<>(); //создаем два типа данных строка и стек
        StringBuilder myString = new StringBuilder(); //используем строку которую можно изменить
        int priority;

        for (String s : myStringExpression) { //для каждого элемента смотрим приоритетность
            priority = getSymbolPriority(s);
            if (priority == 0) { //приоритет 0 для дроби
                myString.append(" ").append(s);
            } else if (priority == 1) {
                myStringStack.push(s);
            }
            if (priority > 1) {
                while (!myStringStack.empty()) {
                    if (getSymbolPriority(myStringStack.peek()) >= priority) {
                        myString.append(" ").append(myStringStack.pop());
                    } else {
                        break;
                    }
                }
                myStringStack.push(s);
            }
            if (priority == -1) {
                while (getSymbolPriority(myStringStack.peek()) != 1) {
                    myString.append(" ").append(myStringStack.pop());

                }
                myStringStack.pop();
            }
        }
        while (!myStringStack.empty()) {
            myString.append(" ").append(myStringStack.pop());
        }

        myString.deleteCharAt(0);
        return myString.toString();
    }

    private static int getSymbolPriority(String symbol){
        return switch (symbol) {
            case ("*"), (":") -> 3;
            case ("+"), ("-") -> 2;
            case ("(") -> 1;
            case (")") -> -1;
            default -> 0;
        };
    }

    public Fraction getAnswer(){ //возврат дроби
        return myStack.peek();
    }
}