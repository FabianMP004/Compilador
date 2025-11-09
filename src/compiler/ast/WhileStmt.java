package compiler.ast;
import java.util.List;
public class WhileStmt extends Stmt {
public Expr cond;
public List<Stmt> body;

public WhileStmt(Expr cond, List<Stmt> body){
    super("WhileStmt");
    this.cond = cond;
    this.body = body;
    add(cond);
    for(Stmt s: body) add(s);
}


}