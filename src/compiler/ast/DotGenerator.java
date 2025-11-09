package compiler.ast;

public class DotGenerator {

    public String generate(AstNode root) {
    StringBuilder sb = new StringBuilder();
    sb.append("digraph AST {\n");
    root.toDot(sb);
    sb.append("}\n");
    return sb.toString();
}

}
