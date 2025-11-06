package compiler.ast;
public class IntLiteral extends Expr {
    public int value; public IntLiteral(int v){ this.value=v; }
    @Override public String toDot(StringBuilder sb){ String my="node"+System.identityHashCode(this); sb.append(my+" [label=\"Int\\"+value+"\"];\n"); return my; }
}
