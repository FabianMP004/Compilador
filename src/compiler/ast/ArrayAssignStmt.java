package compiler.ast;

public class ArrayAssignStmt extends Stmt {
    public String id;
    public Expr index;
    public Expr expr;

    public ArrayAssignStmt(String id, Expr index, Expr expr){
        super("ArrayAssignStmt");
        this.id = id;
        this.index = index;
        this.expr = expr;
        add(index);
        add(expr);
    }

    @Override
    public String toDot(StringBuilder sb) {
        String my = "node" + System.identityHashCode(this);
        sb.append(my + " [label=\"ArrayAssign\\n" + id + "\"];\n");
        
        String indexNode = "node" + System.identityHashCode(index);
        sb.append(my + " -> " + indexNode + ";\n");
        index.toDot(sb);
        
        String exprNode = "node" + System.identityHashCode(expr);
        sb.append(my + " -> " + exprNode + ";\n");
        expr.toDot(sb);
        
        return my;
    }
}
