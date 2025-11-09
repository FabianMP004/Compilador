package compiler.ast;
public class ArrayAssignStmt extends Stmt {
public String id;
public Expr index;
public Expr expr;

public ArrayAssignStmt(String id, Expr index, Expr expr){
    super("ArrayAssignStmt");
    this.id = id;
    this.index = index;
    this.expr = expr;
    add(index);
    add(expr);
}


}