package compiler.ast;

public class StringLiteral extends Expr {
    public String value;

    public StringLiteral(String v){
        super("StringLiteral");
        this.value = v;
    }

    @Override
    public String toDot(StringBuilder sb) {
        String my = "node" + System.identityHashCode(this);
        // Escapar comillas para Graphviz
        String escaped = value.replace("\"", "\\\"");
        sb.append(my + " [label=\"" + escaped + "\"];\n");
        return my;
    }
}
