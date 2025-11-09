package compiler.ast;
public class UnaryOp extends Expr {
public String op;
public Expr expr;

public UnaryOp(String op, Expr e){
    super("UnaryOp");
    this.op = op;
    this.expr = e;
    add(e);
}


}