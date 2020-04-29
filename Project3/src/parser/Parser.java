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
            // No More Variables - Return null
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

    private Node expression(int previousDepth) {
        ExpressionNode node = new ExpressionNode(previousDepth + 1);
        node.setLeftExpression(n(node.getDepth()));

        if (currentToken.getType() == Type.Subtraction) {
            node.setToken(currentToken);
            consumeToken();
            node.setRightExpression(expression(node.getDepth()));
            return node;
        } else {
            // Standalone n - Return Left Node
            node.recursivelyReduceDepth();
            return node.getLeftExpression();
        }
    }

    private Node n(int previousDepth) {
        ExpressionNode node = new ExpressionNode(previousDepth + 1);
        node.setLeftExpression(a(node.getDepth()));

        if (currentToken.getType() == Type.Division) {
            node.setToken(currentToken);
            consumeToken();
            node.setRightExpression(n(node.getDepth()));
            return node;
        } else if (currentToken.getType() == Type.Multiplication) {
            node.setToken(currentToken);
            consumeToken();
            node.setRightExpression(n(node.getDepth()));
            return node;
        } else {
            // Standalone a - Return Left Node
            node.recursivelyReduceDepth();
            return node.getLeftExpression();
        }
    }

    private Node a(int previousDepth) {
        ExpressionNode node = new ExpressionNode(previousDepth + 1);
        node.setLeftExpression(m(node.getDepth()));

        if (currentToken.getType() == Type.Addition) {
            node.setToken(currentToken);
            consumeToken();
            node.setRightExpression(a(node.getDepth()));
            return node;
        } else {
            // Standalone m - Return Left Node
            node.recursivelyReduceDepth();
            return node.getLeftExpression();
        }
    }

    private Node m(int previousDepth) {
        ExpressionNode node = new ExpressionNode(previousDepth + 1);
        if (currentToken.getType() == Type.Multiplication) {
            node.setToken(currentToken);
            consumeToken();
            node.setRightExpression(m(node.getDepth()));
            return node;
        } else {
            return r(previousDepth);
        }
    }

    private Node r(int previousDepth) {
        ExpressionNode node = new ExpressionNode(previousDepth + 1);
        if (currentToken.getType() == Type.OpenParen) {
            consumeToken();
            Node groupedNode = expression(node.getDepth());
            checkForToken(Type.CloseParen);
            groupedNode.recursivelyReduceDepth();
            return groupedNode;

        } else if (currentToken.getType() == Type.Identifier) {
            node.setToken(currentToken);
            consumeToken();
            return node;

        } else if (currentToken.getType() == Type.Number) {
            node.setToken(currentToken);
            consumeToken();
            return node;

        } else {
            error(Type.OpenParen.toString() + " or "
                    + Type.Identifier.toString() + " or "
                    + Type.Number);
            return null; // Unreachable
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
            // Empty - Return null
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
        node.seExpression(expression(node.getDepth()));
        return node;
    }

    private Node ifMethod(int previousDepth) {
        IfNode node = new IfNode(previousDepth + 1);
        checkForToken(Type.Iffy);
        checkForToken(Type.OpenBracket);
        node.setExpressionOne(expression(node.getDepth()));
        node.setRelational(relational(node.getDepth()));
        node.setExpressionTwo(expression(node.getDepth()));
        checkForToken(Type.CloseBracket);
        checkForToken(Type.Then);
        node.setStatement(statement(node.getDepth()));
        return node;
    }

    private Node loopMethod(int previousDepth) {
        LoopNode node = new LoopNode(previousDepth + 1);
        checkForToken(Type.Loop);
        checkForToken(Type.OpenBracket);
        node.setExpressionOne(expression(node.getDepth()));
        node.setRelational(relational(node.getDepth()));
        node.setExpressionTwo(expression(node.getDepth()));
        checkForToken(Type.CloseBracket);
        node.setStatement(statement(node.getDepth()));
        return node;
    }

    private Node assign(int previousDepth) {
        AssignNode node = new AssignNode(previousDepth + 1);
        node.setVariableName(checkForToken(Type.Identifier));
        checkForToken(Type.SassyColon);
        node.setExpression(expression(node.getDepth()));
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

    private Node relational(int previousDepth) {
        RelationalNode node = new RelationalNode(previousDepth + 1);

        if (currentToken.getType() == Type.LessThan) {
            node.setFirstToken(currentToken);
            consumeToken();

            if (currentToken.getType() == Type.LessThan) {
                node.setSecondToken(currentToken);
                consumeToken();
                return node;

            } else if (currentToken.getType() == Type.GreaterThan) {
                node.setSecondToken(currentToken);
                consumeToken();
                return node;

            } else {
                // Standalone < - Return Node
                return node;
            }

        } else if (currentToken.getType() == Type.GreaterThan) {
            node.setFirstToken(currentToken);
            consumeToken();

            if (currentToken.getType() == Type.GreaterThan) {
                node.setSecondToken(currentToken);
                consumeToken();
                return node;

            } else {
                // Standalone > - Return Node
                return node;
            }

        } else if (currentToken.getType() == Type.Equality) {
            node.setFirstToken(currentToken);
            consumeToken();
            return node;

        } else {
            error("relational operator");
            return null; // Unreachable
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
