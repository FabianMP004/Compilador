import compiler.parser.Parser;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

import compiler.scanner.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Uso: java compiler.Main <archivo.minic>");
            System.exit(1);
        }

        String path = args[0];

        try (Reader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {

            // Instancia tu scanner generado por JFlex
            Scanner scanner = new Scanner(reader);
            Parser parser = new Parser(scanner);
            parser.parse();

        } catch (java.io.FileNotFoundException e) {
            System.err.println("No se encontró el archivo: " + path);
            System.exit(2);
        } catch (Exception e) {
            System.err.println("Error ejecutando el scanner: " + e.getMessage());
            e.printStackTrace();
            System.exit(3);
        }
    }
}