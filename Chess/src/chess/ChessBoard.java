package chess;

public class ChessBoard {
    public ChessPiece[][] board = new ChessPiece[8][8];
    public String nowPlayer;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        if (checkPos(startLine) && checkPos(startColumn)) {
            if (!nowPlayer.equals(board[startLine][startColumn].getColor())) return false;
            if (board[startLine][startColumn].canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {
                board[endLine][endColumn] = board[startLine][startColumn];
                board[startLine][startColumn] = null;
                board[endLine][endColumn].check = false;
                this.nowPlayer = this.nowPlayerColor().equals("White") ? "Black" : "White";
                return true;
            }
        }
        return false;
    }

    public boolean castling0() {
        // Logic for castling on column 0
        return false; // Simplified for now
    }

    public boolean castling7() {
        // Logic for castling on column 7
        return false; // Simplified for now
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }

    public void printBoard() {
        System.out.println("Turn: " + nowPlayer);
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");
        for (int i = 7; i >= 0; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().charAt(0) + "\t");
                }
            }
            System.out.println();
        }
    }
}
