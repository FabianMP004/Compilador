package compiler.ast;

public class IntLiteral extends Expr {
    public int value;

    // Constructor que recibe String (lo que manda el parser)
    public IntLiteral(String v){
        super("IntLiteral");
        this.value = Integer.parseInt(v);
    }

    @Override
    public String toDot(StringBuilder sb) {
        String my = "node" + System.identityHashCode(this);
        sb.append(my + " [label=\"" + value + "\"];\n");
        return my;
    }
}
