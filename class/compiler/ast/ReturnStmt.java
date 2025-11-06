package compiler.ast;
public class ReturnStmt extends Stmt {
    public Expr expr; public ReturnStmt(Expr e){ this.expr=e; }
    @Override
    public String toDot(StringBuilder sb){
        String my="node"+System.identityHashCode(this);
        sb.append(my+" [label=\"Return\"];\n");
        if (expr!=null){ String c=expr.toDot(sb); sb.append(my+" -> "+c+";\n"); }
        return my;
    }
}
