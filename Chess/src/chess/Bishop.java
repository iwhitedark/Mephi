package chess;

public class Bishop extends ChessPiece {

    public Bishop(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) return false;
        if (Math.abs(line - toLine) != Math.abs(column - toColumn)) return false;
        return true;
    }

    @Override
    public String getSymbol() {
        return "B";
    }
}
