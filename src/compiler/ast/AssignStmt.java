package compiler.ast;
public class AssignStmt extends Stmt {
    public String id;
    public Expr expr;

    public AssignStmt(String id, Expr e){
        super("AssignStmt");
        this.id = id;
        this.expr = e;
        add(e);
    }

    @Override
    public String toDot(StringBuilder sb) {
        String my = "node" + System.identityHashCode(this);
        sb.append(my + " [label=\"Assign\\n" + id + "\"];\n");
        
        String exprNode = "node" + System.identityHashCode(expr);
        sb.append(my + " -> " + exprNode + ";\n");
        expr.toDot(sb);
        
        return my;
    }
}
