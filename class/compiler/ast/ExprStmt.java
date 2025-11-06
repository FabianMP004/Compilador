package compiler.ast;
public class ExprStmt extends Stmt {
    public Expr expr; public ExprStmt(Expr e){ this.expr=e; }
    @Override
    public String toDot(StringBuilder sb){ String my="node"+System.identityHashCode(this); sb.append(my+" [label=\"ExprStmt\"];\n"); String c=expr.toDot(sb); sb.append(my+" -> "+c+";\n"); return my; }
}
