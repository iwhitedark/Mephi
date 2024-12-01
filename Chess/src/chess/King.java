package chess;

public class King extends ChessPiece {

    public King(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) return false;
        return Math.abs(line - toLine) <= 1 && Math.abs(column - toColumn) <= 1;
    }

    @Override
    public String getSymbol() {
        return "K";
    }
}
