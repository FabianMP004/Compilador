package compiler.ast;
public class AssignStmt extends Stmt {
    public String id; public Expr expr;
    public AssignStmt(String id, Expr e){ this.id=id; this.expr=e; }
    @Override
    public String toDot(StringBuilder sb) {
        String my = "node"+System.identityHashCode(this);
        sb.append(my + " [label=\"Assign\\n"+id+"\"];\n");
        String ch = expr.toDot(sb);
        sb.append(my + " -> " + ch + ";\n");
        return my;
    }
}
