package compiler.ast;
public class StringLiteral extends Expr {
public String value;

public StringLiteral(String v){
    super("StringLiteral");
    this.value = v;
}


}