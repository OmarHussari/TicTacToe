import java.util.Scanner;
public class TicTacToe {
    static int xCount = 0;
    static int oCount = 0;
    static int sCount = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] twoDimArryedList = {{'_', '_', '_'}, {'_', '_', '_'}, {'_', '_', '_'}};
        printGrid(twoDimArryedList);
        String[] coordinates = new String[2];
        int xCor;
        int yCor;
        countXOs(twoDimArryedList);
        char input = 'X';
        boolean xTurn = true;
        while ((!oWins(twoDimArryedList)|| !xWins(twoDimArryedList)) || !draw(twoDimArryedList)) {
            boolean again = xWins(twoDimArryedList) || oWins(twoDimArryedList) || draw(twoDimArryedList) ? false : true;
            if (again == false) {
                break;
            }
            while (again) {
                try {
                    System.out.println("Enter the coordinates: ");
                    String cordz = scanner.nextLine(); // Reads a whole line (String)
                    coordinates = cordz.split(" ", 2); // Splits above string into 2 and places the values into the array
                    xCor = Integer.parseInt(coordinates[0]);
                    yCor = Integer.parseInt(coordinates[1]);
                    if ((xCor > 3 || xCor <= 0) || (yCor > 3 || yCor <= 0)) { // Checks to see if value is less than 1 or more than 3
                        System.out.println("Coordinates should be from 1 to 3!");
                        again = true;
                    } else if (twoDimArryedList[xCor - 1][yCor - 1] != '_') { // Checks to see if cell is occupied
                        System.out.println("This cell is occupied! Choose another one!");
                        again = true;
                    } else { // The value of input is substituted on the grid
                        twoDimArryedList[xCor - 1][yCor - 1] = input;
                        xTurn = !xTurn;
                        input = xTurn ? 'X' : 'O';     
                        again = false;
                    }
                } catch (Exception ex) {
                    System.out.println("You should enter numbers!");
                    again = true;
                }
            countXOs(twoDimArryedList);
            printGrid(twoDimArryedList);
            decideWinner(twoDimArryedList);
            if ((xWins(twoDimArryedList) || oWins(twoDimArryedList)) || draw(twoDimArryedList)) {
                again = false;   
                }
            }
        }
        scanner.close();
    }
    public static boolean draw (char[][] grid) {
        countXOs(grid);
        if ((xWins(grid) == false && oWins(grid) == false) && sCount == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean oWins (char[][] grid) {  
        int streak = 0; // To check horizontals
        for (int i = 0; i < 3; i++) {
            streak = 0;
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == 'O' && grid[i][0] == grid[i][j] ) {
                    streak++;
                    if (streak == 3) {
                        return true;
                    }
                } 
            }
        }
        for (int x = 0; x < 3; x++) { // To check verticals
            streak = 0;
            for (int y = 0; y < 3; y++) {
                if (grid[y][x] == 'O' && grid[0][x] == grid[y][x]) {
                    streak++;
                    if (streak == 3) {
                        return true;
                    }
                }
            }
        } 
        streak = 0;
        for(int a = 0; a < 3; a++) { // To check main diagonal
            for(int b = 0; b < 3; b++) { 
                if (a == b && grid[a][b] == 'O') {
                    streak++;
                    if (streak == 3) {
                        return true;
                    }
                } 
            }
        }
        streak = 0;
        for (int q = 0; q < 3; q++) { // To check opposite diagonal
            for (int w = 0; w < 3; w++) {
                if (q + w == 3 - 1 && grid[q][w] == 'O') {
                    streak++;
                    if (streak == 3) {
                        return true;
                    }
                }
            }
        }

    return false;
    }

    public static boolean xWins (char[][] grid) {
        int streak = 0; // To check horizontals
        for (int i = 0; i < 3; i++) {
            streak = 0;
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == 'X' && grid[i][0] == grid[i][j] ) {
                    streak++;
                    if (streak == 3) {
                        return true;
                    }
                } 
            }
        }
        for (int x = 0; x < 3; x++) { // To check verticals
            streak = 0;
            for (int y = 0; y < 3; y++) {
                if (grid[y][x] == 'X' && grid[0][x] == grid[y][x]) {
                    streak++;
                    if (streak == 3) {
                        return true;
                    }
                }
            }
        } 
        streak = 0;
        for(int a = 0; a < 3; a++) { // To check main diagonal
            for(int b = 0; b < 3; b++) { 
                if (a == b && grid[a][b] == 'X') {
                    streak++;
                    if (streak == 3) {
                        return true;
                    }
                } 
            }
        }
        streak = 0;
        for (int q = 0; q < 3; q++) { // To check opposite diagonal
            for (int w = 0; w < 3; w++) {
                if (q + w == 3 - 1 && grid[q][w] == 'X') {
                    streak++;
                    if (streak == 3) {
                        return true;
                    }
                }
            }
        }

    return false;
    }

    public static void printGrid (char[][] grid) { // prints the game grid 
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(grid[i][j] + " ");
                if (j >= 2) {
                    System.out.print("|");
                    System.out.println();
                }
            }
        }
        System.out.println("---------");
    }

    public static void countXOs (char[][] grid) {  // To count number of X, O, and Spaces
        sCount = 0;
        oCount = 0;
        xCount = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
            switch (grid[i][j]) {
                case ('O'):
                    oCount++;
                    break;
                case ('X'):
                    xCount++;
                    break;
                default:
                    sCount++;
            }
        }
    }

    public static void decideWinner(char[][] grid) { // Decides winner based on a flow stated below..
        if (draw(grid)) {
            System.out.println("Draw");
        } else if (xWins(grid)) {
            System.out.println("X wins");
        } else if (oWins(grid)) {
            System.out.println("O wins");
        }
    }
}
