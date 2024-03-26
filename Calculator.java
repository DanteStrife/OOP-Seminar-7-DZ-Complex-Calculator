import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.*;

public class Calculator {
    private static final String LOG_FILE_NAME = "LogCalc.log";
    private static final Logger logger = Logger.getLogger(Calculator.class.getName());
    private static final ConsoleHandler consoleHandler = new ConsoleHandler();
    private static final Scanner scanner = new Scanner(System.in);
    private static Complex currentResult = new Complex(0, 0);
    public static void main(String[] args) {
        logger.addHandler(getFileHandler());
        consoleHandler.setLevel(Level.FINE);
        logger.addHandler(consoleHandler);
        logger.setLevel(Level.ALL);

        System.out.println("Hello. This Complex Calculator");

        while (true) {
            System.out.println("Enter 'real' part of the complex number or 'Exit' to quit: ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("Exit")) {
                break;
            }
            double real = parseDouble(input);

            System.out.println("Enter 'imaginary' part of the complex number: ");
            input = scanner.nextLine();

            double imaginary = parseDouble(input);

            Complex number = new Complex(real, imaginary);
            logger.fine("Number entered: " + number);

            int operation = askUserForOperation();

            switch (operation) {
                case 1:
                    currentResult = currentResult.add(number);
                    logger.info("Addition operation performed. Current result: " + currentResult);
                    break;
                case 2:
                    currentResult = currentResult.subtract(number);
                    logger.info("Subtraction operation performed. Current result: " + currentResult);
                    break;
                case 3:
                    currentResult = currentResult.multiply(number);
                    logger.info("Multiplication operation performed. Current result: " + currentResult);
                    break;
                case 4:
                    currentResult = currentResult.divide(number);
                    logger.info("Division operation performed. Current result: " + currentResult);
                    break;
                default:
                    System.out.println("Error: Invalid operation!");
                    logger.warning("Invalid operation attempted: " + operation);
            }

        }
        System.out.println("The end using Complex Calculator.");

    }
    private static int askUserForOperation() {
        System.out.println("Select the operation (1-4): ");
        System.out.println("\t1. Add");
        System.out.println("\t2. Subtract");
        System.out.println("\t3. Multiply");
        System.out.println("\t4. Divide");
        String input = scanner.nextLine();

        int operation = parseint(input, -1);
        logger.fine("User selected operation" + operation);
        System.out.println(currentResult);
        return operation;
    }
    private static int parseint(String input, int defaultValue) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            logger.warning("Invalid number format entered: " + input);
            return defaultValue;
        }
    }
    private static double parseDouble(String input) {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException ex) {
            System.out.println("Error. Invalid number format!");
            logger.warning("Invalid number format entered: " + input);
            return Double.NaN;
        }
    }
    private static Handler getFileHandler() {
        try {
            FileHandler handler = new FileHandler(LOG_FILE_NAME, true);
            handler.setFormatter(new Formatter() {
                @Override
                public String format(LogRecord record) {
                    LocalDateTime time = LocalDateTime.now();
                    String formattedTime =
                            time.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                                    .withLocale(Locale.getDefault()));
                    return formattedTime + ": " + record.getMessage() + "\n";
                }
            });
            return handler;
        } catch (IOException ex) {
            logger.severe("Failed to initialize file handler for logger!");
            return null;
        }
    }
}
