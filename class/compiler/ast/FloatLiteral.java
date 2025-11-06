package compiler.ast;
public class FloatLiteral extends Expr {
    public double value; public FloatLiteral(double v){ this.value=v; }
    @Override public String toDot(StringBuilder sb){ String my="node"+System.identityHashCode(this); sb.append(my+" [label=\"Float\\"+value+"\"];\n"); return my; }
}
