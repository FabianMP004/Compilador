package compiler.ast;
import java.util.*;
public class WhileStmt extends Stmt {
    public Expr cond; public List<Stmt> body;
    public WhileStmt(Expr cond, List<Stmt> body){ this.cond=cond; this.body=body; }
    @Override
    public String toDot(StringBuilder sb){
        String my="node"+System.identityHashCode(this);
        sb.append(my+" [label=\"While\"];\n");
        String c=cond.toDot(sb); sb.append(my+" -> "+c+";\n");
        String bodyN=my+"_body"; sb.append(bodyN+" [label=\"Body\"];\n"); sb.append(my+" -> "+bodyN+";\n");
        for(Stmt s: body){ String ch="node"+System.identityHashCode(s); sb.append(bodyN+" -> "+ch+";\n"); s.toDot(sb); }
        return my;
    }
}
