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

    @Override
    public String toDot(StringBuilder sb) {
        String my = "node" + System.identityHashCode(this);
        sb.append(my + " [label=\"ArrayAccess\"];\n");
        
        String arrayNode = "node" + System.identityHashCode(array);
        sb.append(my + " -> " + arrayNode + ";\n");
        array.toDot(sb);
        
        String indexNode = "node" + System.identityHashCode(index);
        sb.append(my + " -> " + indexNode + ";\n");
        index.toDot(sb);
        
        return my;
    }
}
