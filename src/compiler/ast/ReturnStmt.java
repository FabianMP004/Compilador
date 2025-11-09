package compiler.ast;
public class ReturnStmt extends Stmt {
public Expr expr;

public ReturnStmt(Expr e){
    super("ReturnStmt");
    this.expr = e;
    if(e != null) add(e);
}


}