package compiler.ast;
import java.util.*;
public class CallExpr extends Expr {
    public String id; public List<Expr> args;
    public CallExpr(String id, List<Expr> args){ this.id=id; this.args=args; }
    @Override public String toDot(StringBuilder sb){
        String my="node"+System.identityHashCode(this); sb.append(my+" [label=\"Call\\n"+id+"\"];\n");
        for(Expr e: args){ String ch=e.toDot(sb); sb.append(my+" -> "+ch+";\n"); }
        return my;
    }
}
