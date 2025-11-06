package compiler.ast;
public class ArrayAccess extends Expr {
    public Expr array; public Expr index;
    public ArrayAccess(Expr array, Expr index){ this.array=array; this.index=index; }
    @Override public String toDot(StringBuilder sb){
        String my="node"+System.identityHashCode(this); sb.append(my+" [label=\"ArrayAccess\"];\n");
        String a = array.toDot(sb); sb.append(my+" -> "+a+";\n");
        String i = index.toDot(sb); sb.append(my+" -> "+i+";\n");
        return my;
    }
}
