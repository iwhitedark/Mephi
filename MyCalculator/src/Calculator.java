import java.util.Scanner;

/**
 * 2.6 Итоговый проект «Калькулятор»
 * Доступные операции: сложение, вычитание, умножение и деление
 * Дополнительные команды: c - сброс результата и начать сначала, s - завершение работы
 */

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Введите первое число: ");
            double operand1 = scanner.nextDouble();

            char operation;
            while (true) {
                System.out.print("Выберите операцию (+, -, *, /): ");
                operation = scanner.next().charAt(0);
                if (operation == '+' || operation == '-' || operation == '*' || operation == '/') {
                    break;
                } else {
                    System.out.println("Ошибка: Неверный операнд. Попробуйте еще раз.");
                }
            }

            System.out.print("Введите второе число: ");
            double operand2 = scanner.nextDouble();

            double result = performOperation(operand1, operation, operand2);
            System.out.println("Результат: " + Math.round(result * 10.0) / 10.0);

            System.out.println("Хотите продолжить вычисления? c - Сброс, начать сначала, s - Выход))");
            String answer = scanner.next();
            if ("s".equalsIgnoreCase(answer)) {
                System.out.println("Завершение работы");
                System.out.println("-----------------");
                System.out.println("До новых вычислений!");
                break;

            } else if ("c".equalsIgnoreCase(answer)) {
            } else if ("с".equalsIgnoreCase(answer)) {
                continue;
            } else if (!"y".equalsIgnoreCase(answer)) {
                break;
            }
        }
        scanner.close();
    }

    private static double performOperation(double operand1, char operation, double operand2) {
        double result = 0;
        switch (operation) {
            case '+':
                result = operand1 + operand2;
                break;
            case '-':
                result = operand1 - operand2;
                break;
            case '*':
                result = operand1 * operand2;
                break;
            case '/':
                if (operand2 != 0) {
                    result = operand1 / operand2;
                } else {
                    System.err.println("Деление на ноль не допускается!");
                    return 0;
                }
                break;
            default:
                System.err.println("Неизвестная операция!");
                return 0;
        }
        return result;
    }
}