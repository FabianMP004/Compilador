package compiler.ast;
import java.util.*;
public class IfStmt extends Stmt {
    public Expr cond; public List<Stmt> thenStmts; public List<Stmt> elseStmts;
    public IfStmt(Expr cond, List<Stmt> thenStmts, List<Stmt> elseStmts){
        this.cond=cond; this.thenStmts=thenStmts; this.elseStmts=elseStmts;
    }
    @Override
    public String toDot(StringBuilder sb){
        String my = "node"+System.identityHashCode(this);
        sb.append(my + " [label=\"If\"];\n");
        String c = cond.toDot(sb); sb.append(my + " -> " + c + ";\n");
        String thenNode = my + "_then";
        sb.append(thenNode + " [label=\"Then\"];\n"); sb.append(my + " -> " + thenNode + ";\n");
        for(Stmt s: thenStmts){ String ch="node"+System.identityHashCode(s); sb.append(thenNode+" -> "+ch+";\n"); s.toDot(sb); }
        if (elseStmts!=null && !elseStmts.isEmpty()){
            String elseNode = my + "_else";
            sb.append(elseNode + " [label=\"Else\"];\n"); sb.append(my + " -> " + elseNode + ";\n");
            for(Stmt s: elseStmts){ String ch="node"+System.identityHashCode(s); sb.append(elseNode+" -> "+ch+";\n"); s.toDot(sb); }
        }
        return my;
    }
}
