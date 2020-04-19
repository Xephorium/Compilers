package parser;

import model.Token;
import model.Token.Type;
import model.nodes.*;
import scanner.Scanner;

/* Chris Cruzen
 * Compilers
 * 04.18.2020
 *
 * Parser represents the second step in the process of translating a custom
 * programming language. This class encapsulates the logic of using recursive
 * descent parsing to evaluate a program's grammatical correctness and build
 * a corresponding parse tree.
 */


public class Parser {


    /*--- Variable Declarations ---*/

    private Scanner scanner;
    private Token currentToken;


    /*--- Constructor ---*/

    public Parser(java.util.Scanner inputScanner) {
        scanner = new Scanner(inputScanner);
        currentToken = scanner.getNextToken();
    }


    /*--- Public Methods ---*/

    public Node parse() {
        return program();
    }


    /*--- Private Recursive Descent Methods ---*/

    private Node program() {
        ProgramNode node = new ProgramNode(0);
        node.setVariableNode(variables(node.getDepth()));
        node.setBlockNode(block(node.getDepth()));
        return node;
    }

    private Node block(int previousDepth) {
        BlockNode node = new BlockNode(previousDepth + 1);
        checkForToken(Type.OpenBrace);
        node.setVariableNode(variables(node.getDepth()));
        node.setStatementNode(statements(node.getDepth()));
        checkForToken(Type.CloseBrace);
        return node;
    }

    private Node variables(int previousDepth) {
        VariableNode node = new VariableNode(previousDepth + 1);
        if (currentToken.getType() == Type.Declare) {
            consumeToken();
        } else {
            // No More Variables - Do Nothing
            return null;
        }

        // Add all tokens
        node.setVariableName(checkForToken(Type.Identifier));
        checkForToken(Type.SassyColon);
        node.setInitialValue(checkForToken(Type.Number));
        checkForToken(Type.Semicolon);

        node.setVariableNode(variables(node.getDepth()));

        return node;
    }

    private void expression() {
        n();

        if (currentToken.getType() == Type.Subtraction) {
            consumeToken();
            expression();
            return;
        } else {
            // Standalone n - Do Nothing
            return;
        }
    }

    private void n() {
        a();

        if (currentToken.getType() == Type.Division) {
            consumeToken();
            n();
            return;
        } else if (currentToken.getType() == Type.Multiplication) {
            consumeToken();
            n();
            return;
        } else {
            // Standalone a - Do Nothing
            return;
        }
    }

    private void a() {
        m();

        if (currentToken.getType() == Type.Addition) {
            consumeToken();
            a();
            return;
        } else {
            // Standalone m - Do Nothing
            return;
        }
    }

    private void m() {
        if (currentToken.getType() == Type.Multiplication) {
            consumeToken();
            m();
            return;
        } else {
            r();
            return;
        }
    }

    private void r() {
        if (currentToken.getType() == Type.OpenParen) {
            consumeToken();
            expression();
            checkForToken(Type.CloseParen);
            return;

        } else if (currentToken.getType() == Type.Identifier) {
            consumeToken();
            return;

        } else if (currentToken.getType() == Type.Number) {
            consumeToken();
            return;

        } else {
            error(Type.OpenParen.toString() + " or "
                    + Type.Identifier.toString() + " or "
                    + Type.Number);
        }
    }

    private Node statements(int previousDepth) {
        StatementNode node = new StatementNode(previousDepth + 1);
        node.setStatementNode(statement(node.getDepth()));
        node.setOtherStatementNode(mStatement(node.getDepth()));
        return node;
    }

    private Node mStatement(int previousDepth) {
        StatementNode node = new StatementNode(previousDepth + 1);
        if (currentToken.getType() == Type.In
                || currentToken.getType() == Type.Out
                || currentToken.getType() == Type.OpenBrace
                || currentToken.getType() == Type.Iffy
                || currentToken.getType() == Type.Loop
                || currentToken.getType() == Type.Assign
                || currentToken.getType() == Type.Goto
                || currentToken.getType() == Type.Label
                ) {
            node.setStatementNode(statement(node.getDepth()));
            node.setOtherStatementNode(mStatement(node.getDepth()));
            return node;

        } else {
            // Empty - Do Nothing
            return null;
        }
    }

    private Node statement(int previousDepth) {
        if (currentToken.getType() == Type.In) {
            Node node = in(previousDepth);
            checkForToken(Type.Semicolon);
            return node;

        } else if (currentToken.getType() == Type.Out) {
            Node node = out(previousDepth);
            checkForToken(Type.Semicolon);
            return node;

        } else if (currentToken.getType() == Type.OpenBrace) {
            return block(previousDepth);

        } else if (currentToken.getType() == Type.Iffy) {
            Node node = ifMethod(previousDepth);
            checkForToken(Type.Semicolon);
            return node;

        } else if (currentToken.getType() == Type.Loop) {
            Node node = loopMethod(previousDepth);
            checkForToken(Type.Semicolon);
            return node;

        } else if (currentToken.getType() == Type.Identifier) {
            Node node = assign(previousDepth);
            checkForToken(Type.Semicolon);
            return node;

        } else if (currentToken.getType() == Type.Goto) {
            Node node = gotoMethod(previousDepth);
            checkForToken(Type.Semicolon);
            return node;

        } else if (currentToken.getType() == Type.Label) {
            Node node = label(previousDepth);
            checkForToken(Type.Semicolon);
            return node;

        } else {
            error("statement or block");
            return null; // Unreachable
        }
    }

    private Node in(int previousDepth) {
        InNode node = new InNode(previousDepth + 1);
        checkForToken(Type.In);
        node.setName(checkForToken(Type.Identifier));
        return node;
    }

    private Node out(int previousDepth) {
        OutNode node = new OutNode(previousDepth + 1);
        checkForToken(Type.Out);
        //node.seExpression(expression());
        expression();
        return node;
    }

    private Node ifMethod(int previousDepth) {
        IfNode node = new IfNode(previousDepth + 1);
        checkForToken(Type.Iffy);
        checkForToken(Type.OpenBracket);
        //node.setExpressionOne(expression(node.getDepth()));
        expression();
        //node.setRelational(relational(node.getDepth()));
        relational();
        //node.setExpressionTwo(expression(node.getDepth()));
        expression();
        checkForToken(Type.CloseBracket);
        checkForToken(Type.Then);
        node.setStatement(statement(node.getDepth()));
        return node;
    }

    private Node loopMethod(int previousDepth) {
        LoopNode node = new LoopNode(previousDepth + 1);
        checkForToken(Type.Loop);
        checkForToken(Type.OpenBracket);
        //node.setExpressionOne(expression(node.getDepth()));
        expression();
        //node.setRelational(relational(node.getDepth()));
        relational();
        //node.setExpressionTwo(expression(node.getDepth()));
        expression();
        checkForToken(Type.CloseBracket);
        node.setStatement(statement(node.getDepth()));
        return node;
    }

    private Node assign(int previousDepth) {
        AssignNode node = new AssignNode(previousDepth + 1);
        node.setVariableName(checkForToken(Type.Identifier));
        checkForToken(Type.SassyColon);
        //node.setExpression(expression());
        expression();
        return node;
    }

    private Node gotoMethod(int previousDepth) {
        GoToNode node = new GoToNode(previousDepth + 1);
        checkForToken(Type.Goto);
        node.setName(checkForToken(Type.Identifier));
        return node;
    }

    private Node label(int previousDepth) {
        LabelNode node = new LabelNode(previousDepth + 1);
        checkForToken(Type.Label);
        node.setName(checkForToken(Type.Identifier));
        return node;
    }

    private void relational() {
        if (currentToken.getType() == Type.LessThan) {
            consumeToken();

            if (currentToken.getType() == Type.LessThan) {
                consumeToken();
                return;

            } else if (currentToken.getType() == Type.GreaterThan) {
                consumeToken();
                return;

            } else {
                // Standalone < - Do Nothing.
                return;
            }

        } else if (currentToken.getType() == Type.GreaterThan) {
            consumeToken();

            if (currentToken.getType() == Type.GreaterThan) {
                consumeToken();
                return;

            } else {
                // Standalone > - Do Nothing
                return;
            }

        } else if (currentToken.getType() == Type.Equality) {
            consumeToken();
            return;

        } else {
            error("relational operator");
        }
    }


    /*--- Private Utility Methods ---*/

    private Token checkForToken(Type type) {
        if (currentToken.getType() == type) {
            Token tempToken = currentToken;
            consumeToken();
            return tempToken;
        } else {
            error(type.toString());
            return null; // Unreachable
        }
    }

    private void consumeToken() {
        currentToken = scanner.getNextToken();
    }

    private void error(String expected) {
        System.out.println("Error (" + currentToken.getLine() + "): Expected " + expected + ", found \'"
                + currentToken.getInstance() + "\'."
        );
        System.exit(1);
    }

}
