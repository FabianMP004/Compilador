package compiler;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;

import compiler.parser.Parser;
import compiler.scanner.Scanner;
import compiler.ast.AstNode;
import compiler.ast.DotGenerator;
import java_cup.runtime.Symbol;

public class Main {

    public static void main(String[] args) {
    if (args.length == 0) {
        System.err.println("Uso: java compiler.Main <archivo.minic>");
        System.exit(1);
    }

    String path = args[0];

    try (Reader reader = new FileReader(path)) {

        Scanner scanner = new Scanner(reader);
        Parser parser = new Parser(scanner);

        Symbol result = parser.parse();
        AstNode ast = (AstNode) result.value;

        if (ast == null) {
            System.err.println("Error: AST nulo");
            System.exit(4);
        }

        DotGenerator gen = new DotGenerator();
        String dot = gen.generate(ast);

        FileWriter fw = new FileWriter("ast.dot");
        fw.write(dot);
        fw.close();

        System.out.println("Archivo generado: ast.dot");

    } catch (Exception e) {
        System.err.println("Error: " + e.getMessage());
        e.printStackTrace();
        System.exit(3);
    }
    }

}
