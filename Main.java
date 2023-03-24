import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNext("quit")){

            FractionOperationStack expression = new FractionOperationStack( //переводим в обратную польскую запись и потом считаем
                    FractionOperationStack.convertToReversePolishNotation(scanner.nextLine()));
            try{
                expression.solve();
            }
            catch (InvalidExpression invalidExpression){
                invalidExpression.printStackTrace();
            }
        }
    }
}