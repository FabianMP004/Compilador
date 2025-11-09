package compiler.ast;
public class ArrayAccess extends Expr {
public Expr array;
public Expr index;

public ArrayAccess(Expr array, Expr index){
    super("ArrayAccess");
    this.array = array;
    this.index = index;
    add(array);
    add(index);
}


}