package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // init the field
        char[][] field = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = ' ';
            }
        }

        printField(field);

        boolean isXsTurn = true;
        do {
            while (true) {
                int y;
                int x;
                System.out.print("Enter the coordinates: ");
                try {
                    y = sc.nextInt() - 1;
                    x = sc.nextInt() - 1;
                    if (y > 2 || y < 0 || x > 2 || x < 0) {
                        System.out.println("Coordinates should be from 1 to 3!");
                        continue;
                    }
                } catch (Exception e) {
                    System.out.println("You should enter numbers!");
                    continue;
                }

                if (field[y][x] == 'X' || field[y][x] == 'O') {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    if (isXsTurn) {
                        field[y][x] = 'X';
                        isXsTurn = false;
                    } else {
                        field[y][x] = 'O';
                        isXsTurn = true;
                    }
                    break;
                }
            }
            printField(field);

        } while (checkState(field) != 0);
    }

    static void printField(char[][] field) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("|");
            for (int j = 0; j < 3; j++) {
                System.out.print(" " + field[i][j]);
            }
            System.out.println(" |");
        }
        System.out.println("---------");
    }

    static int checkState(char[][] field) {
        int numX = 0;
        int numO = 0;

        boolean isOWins = false;
        boolean isXWins = false;

        for (int i = 0; i < 3; i++) {
            // check x axis
            if (field[i][0] == field[i][1] && field[i][1] == field[i][2]) {
                if (field[i][0] == 'X') {
                    isXWins = true;
                } else if (field[i][0] == 'O') {
                    isOWins = true;
                }
            }
            // check y axis
            if (field[0][i] == field[1][i] && field[1][i] == field[2][i]) {
                if (field[0][i] == 'X') {
                    isXWins = true;
                } else if (field[0][i] == 'O') {
                    isOWins = true;
                }
            }

            // compare nums
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == 'X') {
                    numX++;
                } else if (field[i][j] == 'O') {
                    numO++;
                }
            }
        }

        // check diagonal
        if (field[0][0] == field[1][1] && field[1][1] == field[2][2]) {
            if (field[0][0] == 'X') {
                isXWins = true;
            } else if (field[0][0] == 'O') {
                isOWins = true;
            }
        }
        // check side diagonal
        if (field[0][2] == field[1][1] && field[1][1] == field[2][0]) {
            if (field[1][1] == 'X') {
                isXWins = true;
            } else if (field[1][1] == 'O') {
                isOWins = true;
            }
        }

        if (Math.abs(numX - numO) > 1 || isOWins && isXWins) {
            System.out.println("Impossible");
            return 0;
        } else if (isOWins) {
            System.out.println("O wins");
            return 0;
        } else if (isXWins) {
            System.out.println("X wins");
            return 0;
        } else if (numX + numO == 9) {
            System.out.println("Draw");
            return 0;
        }
        return 1;
    }
}
