public class InvalidExpression  extends Exception{

    public InvalidExpression (){
        super("Invalid expression. Please enter your expression in format Fraction1 ' ' sign ' ' Fraction2");
    }

    public InvalidExpression(String errorMessage){
        super(errorMessage);
    }
}