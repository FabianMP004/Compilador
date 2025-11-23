package compiler.ast;

public class FloatLiteral extends Expr {
    public double value;

    // Constructor que recibe String (lo que manda el parser)
    public FloatLiteral(String v){
        super("FloatLiteral");
        this.value = Double.parseDouble(v);
    }

    @Override
    public String toDot(StringBuilder sb) {
        String my = "node" + System.identityHashCode(this);
        sb.append(my + " [label=\"" + value + "\"];\n");
        return my;
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }
}
