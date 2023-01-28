package org.example;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static int SIZE = 4;
    public static int DOTS_TO_WIN = 3;
    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static char[][] map;
    public static Scanner sc = new Scanner(System.in);
    public static Random rand = new Random();

    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {
            userTurn();
            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("Победили обезьяны");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
            aiTurn();
            printMap();
            if (checkWin(DOT_O)) {
                System.out.println("Победил скайнет");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
    }

    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + "\t");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + "\t");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void userTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[x][y] = DOT_X;
    }

    public static void aiTurn() {
        int x, y;
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (!isCellValid(x, y));
        map[x][y] = DOT_O;
    }

    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        return map[x][y] == DOT_EMPTY;
    }

    public static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }

    public static boolean checkWin(char c) {
        int dotsInRow = 0;

        // Горизонтали
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == c) {
                    dotsInRow++;
                    if (dotsInRow >= DOTS_TO_WIN) break;
                } else {
                    dotsInRow = 0;
                }
            }
            if (dotsInRow >= DOTS_TO_WIN) {
                return true;
            } else {
                dotsInRow = 0;
            }
        }

        // Вертикали
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[j][i] == c) {
                    dotsInRow++;
                    if (dotsInRow >= DOTS_TO_WIN) break;
                } else {
                    dotsInRow = 0;
                }
            }
            if (dotsInRow >= DOTS_TO_WIN) {
                return true;
            } else {
                dotsInRow = 0;
            }
        }

        // Главная диагональ
        for (int i = 0; i < SIZE; i++) {
            if (map[i][i] != c) {
                dotsInRow++;
                if (dotsInRow >= DOTS_TO_WIN) break;
            } else {
                dotsInRow = 0;
            }
        }
        if (dotsInRow >= DOTS_TO_WIN) {
            return true;
        } else {
            dotsInRow = 0;
        }

        // Побочная диагональ
        for (int i = 0; i < SIZE; i++) {
            if (map[i][SIZE - i - 1] == c) {
                dotsInRow++;
                if (dotsInRow >= DOTS_TO_WIN) break;
            } else {
                dotsInRow = 0;
            }
        }
        if (dotsInRow >= DOTS_TO_WIN) return true;
        return false;
    }
}