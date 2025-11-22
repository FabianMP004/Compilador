package compiler.ast;
public class UnaryOp extends Expr {
    public String op;
    public Expr expr;

    public UnaryOp(String op, Expr e){
        super("UnaryOp");
        this.op = op;
        this.expr = e;
        add(e);
    }

    @Override
    public String toDot(StringBuilder sb) {
        String my = "node" + System.identityHashCode(this);
        sb.append(my + " [label=\"" + op + "\"];\n");
        
        String exprNode = "node" + System.identityHashCode(expr);
        sb.append(my + " -> " + exprNode + ";\n");
        expr.toDot(sb);
        
        return my;
    }
}
