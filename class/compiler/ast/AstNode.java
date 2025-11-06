package compiler.ast;

import java.io.*;
public abstract class AstNode {
    public abstract String toDot(StringBuilder sb);
    // helper: writes full dot to a file
    public void writeDotFile(String filename) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph AST {\nnode [shape=box];\n");
        toDot(sb);
        sb.append("}\n");
        try (FileWriter fw = new FileWriter(filename)) { fw.write(sb.toString()); }
    }
}
