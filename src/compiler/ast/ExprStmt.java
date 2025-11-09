package compiler.ast;
public class ExprStmt extends Stmt {
public Expr expr;

public ExprStmt(Expr e){
    super("ExprStmt");
    this.expr = e;
    add(e);
}


}