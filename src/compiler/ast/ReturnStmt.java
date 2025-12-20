package compiler.ast;
public class ReturnStmt extends Stmt {
    public Expr expr;

    public ReturnStmt(Expr e){
        super("ReturnStmt");
        this.expr = e;
        if(e != null) add(e);
    }

    @Override
    public String toDot(StringBuilder sb) {
        String my = "node" + System.identityHashCode(this);
        sb.append(my + " [label=\"Return\"];\n");
        
        if(expr != null){
            String exprNode = "node" + System.identityHashCode(expr);
            sb.append(my + " -> " + exprNode + ";\n");
            expr.toDot(sb);
        }
        
        return my;
    }
}
