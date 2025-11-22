package compiler.ast;
public class VarDecl extends Decl {
    public TypeNode type;
    public String id;
    public Integer size;

    public VarDecl(TypeNode t, String id){
        super("VarDecl");
        this.type = t;
        this.id = id;
        this.size = null;
    }

    public VarDecl(TypeNode t, String id, int size){
        super("VarDecl");
        this.type = t;
        this.id = id;
        this.size = size;
    }

    @Override
    public String toDot(StringBuilder sb) {
        String my = "node" + System.identityHashCode(this);
        String label = "VarDecl\\n" + id + " : " + type;
        if(size != null) {
            label += "[" + size + "]";
        }
        sb.append(my + " [label=\"" + label + "\"];\n");
        return my;
    }
}
