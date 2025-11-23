package compiler;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;

import compiler.parser.Parser;
import compiler.scanner.Scanner;
import compiler.ast.AstNode;
import compiler.ast.Program;
import compiler.ast.DotGenerator;
import compiler.irt.IRTGenerator;
import compiler.semantic.SemanticChecker;
import compiler.semantic.SemanticException;
import java_cup.runtime.Symbol;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Uso: java compiler.Main <archivo.minic>");
            System.exit(1);
        }

        String path = args[0];
        
        System.out.println("=== COMPILADOR MINIC ===");
        System.out.println("Archivo: " + path);
        System.out.println();

        try (Reader reader = new FileReader(path)) {

            Scanner scanner = new Scanner(reader);
            Parser parser = new Parser(scanner);

            System.out.println("[1] Iniciando analisis lexico y sintactico...");
            Symbol result = parser.parse();
            System.out.println("[2] Parsing completado");
            
            System.out.println();
            System.out.println("Informacion del resultado:");
            System.out.println("  - Symbol: " + result);
            System.out.println("  - Symbol.sym: " + result.sym);
            System.out.println("  - Symbol.value: " + result.value);
            
            if (result.value != null) {
                System.out.println("  - Tipo: " + result.value.getClass().getName());
                System.out.println("  - Es Program? " + (result.value instanceof Program));
                System.out.println("  - Es AstNode? " + (result.value instanceof AstNode));
            } else {
                System.out.println("  - PROBLEMA: result.value es NULL");
            }
            System.out.println();

            if (result.value == null) {
                System.err.println("ERROR CRITICO: El parser no genero ningun AST");
                System.err.println("Posibles causas:");
                System.err.println("  1. Error de sintaxis no detectado");
                System.err.println("  2. RESULT no asignado en parser.cup");
                System.err.println("  3. Problema en la gramatica");
                System.exit(4);
            }

            AstNode ast = null;
            if (result.value instanceof Program) {
                ast = (Program) result.value;
                System.out.println("[3] AST obtenido correctamente (tipo Program)");
            } else if (result.value instanceof AstNode) {
                ast = (AstNode) result.value;
                System.out.println("[3] AST obtenido correctamente (tipo AstNode)");
            } else {
                System.err.println("ERROR: result.value no es AstNode");
                System.err.println("Es: " + result.value.getClass().getName());
                System.exit(5);
            }

            System.out.println("[4] Iniciando analisis semantico...");
            try {
                SemanticChecker checker = new SemanticChecker();
                checker.check((Program) ast);
                System.out.println("[5] Analisis semantico completado correctamente");
            } catch (SemanticException se) {
                System.err.println("ERROR SEMANTICO: " + se.getMessage());
                System.exit(6);
            }

            System.out.println("[4] Generando IRT...");
            IRTGenerator irtGen = new IRTGenerator();
            java.util.List<String> irtCode = irtGen.generate((Program) ast);
            System.out.println("[5] Codigo IRT generado:");
            for (String line : irtCode) {
                System.out.println("  " + line);
            }

            System.out.println("[6] Generando representacion DOT...");
            DotGenerator gen = new DotGenerator();
            String dot = gen.generate(ast);

            System.out.println("[7] Escribiendo archivo ast.dot...");
            FileWriter fw = new FileWriter("ast.dot");
            fw.write(dot);
            fw.close();

            System.out.println();
            System.out.println("============================");
            System.out.println("EXITO! Archivo generado: ast.dot");
            System.out.println("============================");

        } catch (java.io.FileNotFoundException e) {
            System.err.println("ERROR: Archivo no encontrado: " + path);
            System.exit(2);
        } catch (Exception e) {
            System.err.println("ERROR INESPERADO: " + e.getMessage());
            System.err.println();
            System.err.println("Stack trace:");
            e.printStackTrace();
            System.exit(3);
        }
    }
}
