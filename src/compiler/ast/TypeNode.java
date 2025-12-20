package compiler.ast;

public class TypeNode extends AstNode {
    public String typeName;

    public TypeNode(String typeName){
        super("Type");
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return typeName;
    }

    @Override
    public String toDot(StringBuilder sb) {
        String my = "node" + System.identityHashCode(this);
        sb.append(my + " [label=\"" + typeName + "\"];\n");
        return my;
    }
}
