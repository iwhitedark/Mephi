package chess;

public class Pawn extends ChessPiece {

    public Pawn(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) return false;
        int direction = color.equals("White") ? 1 : -1;
        if (line + direction == toLine && column == toColumn && chessBoard.board[toLine][toColumn] == null) {
            return true;
        }
        if (line == (color.equals("White") ? 1 : 6) && line + 2 * direction == toLine && column == toColumn) {
            return true;
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "P";
    }
}
