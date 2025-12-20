package compiler.ast;

public class WhileStmt extends Stmt {
    public Expr cond;
    public Stmt body;

    public WhileStmt(Expr cond, Stmt body){
        super("WhileStmt");
        this.cond = cond;
        this.body = body;
        add(cond);
        if(body != null) add(body);
    }

    @Override
    public String toDot(StringBuilder sb) {
        String my = "node" + System.identityHashCode(this);
        sb.append(my + " [label=\"WhileStmt\"];\n");
        
        String condNode = "node" + System.identityHashCode(cond);
        sb.append(my + " -> " + condNode + ";\n");
        cond.toDot(sb);
        
        if(body != null){
            String bodyNode = "node" + System.identityHashCode(body);
            sb.append(my + " -> " + bodyNode + ";\n");
            body.toDot(sb);
        }
        
        return my;
    }
}
