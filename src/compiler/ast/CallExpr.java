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


}