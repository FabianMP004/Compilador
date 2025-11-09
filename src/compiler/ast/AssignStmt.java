package compiler.ast;
public class AssignStmt extends Stmt {
public String id;
public Expr expr;

public AssignStmt(String id, Expr e){
    super("AssignStmt");
    this.id = id;
    this.expr = e;
    add(e);
}


}