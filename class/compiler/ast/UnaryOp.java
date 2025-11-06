package compiler.ast;
public class UnaryOp extends Expr {
    public String op; public Expr expr;
    public UnaryOp(String op, Expr e){ this.op=op; this.expr=e; }
    @Override public String toDot(StringBuilder sb){
        String my="node"+System.identityHashCode(this); sb.append(my+" [label=\"Unary\\n"+op+"\"];\n");
        String c = expr.toDot(sb); sb.append(my+" -> "+c+";\n"); return my;
    }
}
