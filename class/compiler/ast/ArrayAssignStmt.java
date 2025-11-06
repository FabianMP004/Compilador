package compiler.ast;
public class ArrayAssignStmt extends Stmt {
    public String id; public Expr index; public Expr expr;
    public ArrayAssignStmt(String id, Expr index, Expr expr){ this.id=id; this.index=index; this.expr=expr; }
    @Override
    public String toDot(StringBuilder sb) {
        String my = "node"+System.identityHashCode(this);
        sb.append(my + " [label=\"ArrayAssign\\n"+id+"\"];\n");
        String ci = index.toDot(sb); sb.append(my + " -> " + ci + ";\n");
        String ce = expr.toDot(sb); sb.append(my + " -> " + ce + ";\n");
        return my;
    }
}
