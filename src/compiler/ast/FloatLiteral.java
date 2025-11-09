package compiler.ast;
public class FloatLiteral extends Expr {
public double value;

public FloatLiteral(double v){
    super("FloatLiteral");
    this.value = v;
}


}