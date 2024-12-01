package chess;

import java.util.Scanner;

public class Main {
    public static ChessBoard buildBoard() {
        ChessBoard board = new ChessBoard("White");

        // Белые фигуры
        board.board[0][0] = new Rook("White");
        board.board[0][1] = new Horse("White");
        board.board[0][2] = new Bishop("White");
        board.board[0][3] = new Queen("White");
        board.board[0][4] = new King("White");
        board.board[0][5] = new Bishop("White");
        board.board[0][6] = new Horse("White");
        board.board[0][7] = new Rook("White");
        for (int i = 0; i < 8; i++) {
            board.board[1][i] = new Pawn("White");
        }

        // Черные фигуры
        board.board[7][0] = new Rook("Black");
        board.board[7][1] = new Horse("Black");
        board.board[7][2] = new Bishop("Black");
        board.board[7][3] = new Queen("Black");
        board.board[7][4] = new King("Black");
        board.board[7][5] = new Bishop("Black");
        board.board[7][6] = new Horse("Black");
        board.board[7][7] = new Rook("Black");
        for (int i = 0; i < 8; i++) {
            board.board[6][i] = new Pawn("Black");
        }

        return board;
    }


    public static void main(String[] args) {
        ChessBoard board = buildBoard();
        Scanner scanner = new Scanner(System.in);
        board.printBoard();
        while (true) {
            System.out.print("Input: ");
            String input = scanner.nextLine();
            if (input.equals("exit")) break;
            if (input.equals("replay")) {
                System.out.println("Restarting game...");
                board = buildBoard();
                board.printBoard();
                continue;
            }
            if (input.startsWith("move")) {
                try {
                    String[] parts = input.split(" ");
                    if (parts.length != 5) {
                        throw new IllegalArgumentException("Неверный формат команды перемещения");
                    }
                    int startLine = Integer.parseInt(parts[1]);
                    int startColumn = Integer.parseInt(parts[2]);
                    int endLine = Integer.parseInt(parts[3]);
                    int endColumn = Integer.parseInt(parts[4]);
                    if (board.moveToPosition(startLine, startColumn, endLine, endColumn)) {
                        System.out.println("Успешное перемещение!");
                    } else {
                        System.out.println("Ход не удался. Неправильный ход или нарушение правил");
                    }
                } catch (Exception e) {
                    System.out.println("Неверный ввод. Правильный формат: move <startLine> <startColumn> <endLine> <endColumn>. Например: move 1 0 3 0");
                }
            } else {
                System.out.println("Неизвестная команда. Допустимые команды: move, castling0, castling7, replay, exit.");
            }
            board.printBoard();
        }
        scanner.close();
    }
}
