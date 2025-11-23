package compiler.irt;

import compiler.ast.*;
import java.util.*;

public class IRTGenerator {
    private List<String> irtCode = new ArrayList<>();

    public List<String> generate(Program program) {
        irtCode.clear();
        for (Decl decl : program.decls) {
            if (decl instanceof FuncDecl) {
                generateFunc((FuncDecl) decl);
            } else if (decl instanceof VarDecl) {
                generateVar((VarDecl) decl);
            }
        }
        return irtCode;
    }

    private void generateFunc(FuncDecl func) {
        irtCode.add("FUNC " + func.id);
        for (Param param : func.params) {
            irtCode.add("PARAM " + param.id);
        }
        for (AstNode node : func.body) {
            if (node instanceof VarDecl) {
                generateVar((VarDecl) node);
            } else if (node instanceof Stmt) {
                generateStmt((Stmt) node);
            }
        }
        irtCode.add("END_FUNC " + func.id);
    }

    private void generateVar(VarDecl var) {
        irtCode.add("VAR " + var.id + " : " + var.type);
    }

    private void generateStmt(Stmt stmt) {
        if (stmt instanceof AssignStmt) {
            AssignStmt assign = (AssignStmt) stmt;
            irtCode.add("ASSIGN " + assign.id + " = " + exprToString(assign.expr));
        } else if (stmt instanceof ReturnStmt) {
            ReturnStmt ret = (ReturnStmt) stmt;
            irtCode.add("RETURN " + exprToString(ret.expr));
        } else if (stmt instanceof IfStmt) {
            IfStmt ifs = (IfStmt) stmt;
            irtCode.add("IF " + exprToString(ifs.cond));
            if (ifs.thenNode != null && ifs.thenNode instanceof Stmt) generateStmt((Stmt) ifs.thenNode);
            if (ifs.elseNode != null && ifs.elseNode instanceof Stmt) generateStmt((Stmt) ifs.elseNode);
            irtCode.add("END_IF");
        } else if (stmt instanceof WhileStmt) {
            WhileStmt ws = (WhileStmt) stmt;
            irtCode.add("WHILE " + exprToString(ws.cond));
            if (ws.body != null && ws.body instanceof Stmt) generateStmt((Stmt) ws.body);
            irtCode.add("END_WHILE");
        } else if (stmt instanceof ExprStmt) {
            ExprStmt es = (ExprStmt) stmt;
            if (es.nodes != null) {
                for (AstNode n : es.nodes) {
                    if (n instanceof Stmt) generateStmt((Stmt) n);
                    if (n instanceof VarDecl) generateVar((VarDecl) n);
                }
            }
        }
    }

    private String exprToString(Expr expr) {
        if (expr == null) return "null";
        return expr.toString(); // Puedes mejorar esto para mostrar la expresión en formato IRT
    }
}
