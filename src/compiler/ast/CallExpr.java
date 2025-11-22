package compiler.ast;
import java.util.List;

public class CallExpr extends Expr {
    public String id;
    public List<Expr> args;

    public CallExpr(String id, List<Expr> args){
        super("CallExpr");
        this.id = id;
        this.args = args;
        for(Expr e: args) add(e);
    }

    @Override
    public String toDot(StringBuilder sb) {
        String my = "node" + System.identityHashCode(this);
        sb.append(my + " [label=\"Call\\n" + id + "\"];\n");
        
        for(Expr arg : args){
            String argNode = "node" + System.identityHashCode(arg);
            sb.append(my + " -> " + argNode + ";\n");
            arg.toDot(sb);
        }
        
        return my;
    }
}
