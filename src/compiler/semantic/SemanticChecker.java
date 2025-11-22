package compiler.semantic;

import compiler.ast.*;
import java.util.*;

public class SemanticChecker {
    private Map<String, Type> symbolTable = new HashMap<>();

    public void check(Program program) {
        for (Decl decl : program.decls) {
            if (decl instanceof FuncDecl) {
                checkFuncDecl((FuncDecl) decl);
            } else if (decl instanceof VarDecl) {
                checkVarDecl((VarDecl) decl);
            }
        }
    }

    private void checkFuncDecl(FuncDecl func) {
        // Example: add function name to symbol table
        symbolTable.put(func.id, toSemanticType(func.type));
        for (Param param : func.params) {
            symbolTable.put(param.id, toSemanticType(param.type));
        }
        for (AstNode node : func.body) {
            if (node instanceof VarDecl) {
                checkVarDecl((VarDecl) node);
            } else if (node instanceof Stmt) {
                checkStmt((Stmt) node);
            }
        }
    }

    private void checkVarDecl(VarDecl var) {
        if (symbolTable.containsKey(var.id)) {
            throw new SemanticException("Variable redeclarada: " + var.id);
        }
        symbolTable.put(var.id, toSemanticType(var.type));
    }

    private void checkStmt(Stmt stmt) {
        if (stmt instanceof AssignStmt) {
            AssignStmt assign = (AssignStmt) stmt;
            if (!symbolTable.containsKey(assign.id)) {
                throw new SemanticException("Variable no declarada: " + assign.id);
            }
            // Aquí podrías agregar chequeo de tipos
        } else if (stmt instanceof ReturnStmt) {
            // Chequeo de return
        } else if (stmt instanceof IfStmt) {
            IfStmt ifs = (IfStmt) stmt;
            // Recursivo para ramas
            if (ifs.thenNode != null && ifs.thenNode instanceof Stmt) checkStmt((Stmt) ifs.thenNode);
            if (ifs.elseNode != null && ifs.elseNode instanceof Stmt) checkStmt((Stmt) ifs.elseNode);
        } else if (stmt instanceof WhileStmt) {
            WhileStmt ws = (WhileStmt) stmt;
            if (ws.body != null && ws.body instanceof Stmt) checkStmt((Stmt) ws.body);
        } else if (stmt instanceof ExprStmt) {
            ExprStmt es = (ExprStmt) stmt;
            if (es.nodes != null) {
                for (AstNode n : es.nodes) {
                    if (n instanceof Stmt) checkStmt((Stmt) n);
                    if (n instanceof VarDecl) checkVarDecl((VarDecl) n);
                }
            }
        }
    }

    private Type toSemanticType(TypeNode typeNode) {
        if (typeNode == null) return Type.ERROR;
        switch (typeNode.toString()) {
            case "int": return Type.INT;
            case "float": return Type.FLOAT;
            case "void": return Type.VOID;
            default: return Type.ERROR;
        }
    }
}
