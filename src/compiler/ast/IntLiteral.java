package compiler.ast;
public class IntLiteral extends Expr {
public int value;

public IntLiteral(int v){
    super("IntLiteral");
    this.value = v;
}


}