package compiler.ast;
public class VarExpr extends Expr {
    public String id; public VarExpr(String id){ this.id=id; }
    @Override public String toDot(StringBuilder sb){ String my="node"+System.identityHashCode(this); sb.append(my+" [label=\"Var\\n"+id+"\"];\n"); return my; }
}
