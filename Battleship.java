// package battleship;
import java.util.Scanner;
public class Main {

    public static char[][] arr = new char[10][10];
    public static char[][] arr1 = new char[10][10];
    public static int[] A_C = new int[5];
    public static int[] Bat = new int[5];
    public static int[] sub = new int[5];
    public static int[] cur = new int[5];
    public static int[] dest = new int[5];
    public static int[] A_C1 = new int[5];
    public static int[] Bat1 = new int[5];
    public static int[] sub1 = new int[5];
    public static int[] cur1 = new int[5];
    public static int[] dest1 = new int[5];
    public static int nb = 0;
    public static int nb1 = 0;
    public static void main(String[] args) {
        // Write your code here
        intliaze();
        System.out.println("Player 1, place your ships on the game field");
        display(arr);
        fill(arr, 1);
        System.out.println("Press Enter and pass the move to another player");
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println("Player 2, place your ships to the game field");
        display(arr1);
        fill(arr1, 2);
        int player = 1;
        int val = 0;
        while(val == 0) {
            System.out.println("Press Enter and pass the move to another player");
            s = sc.nextLine();
            if (player == 1) {
                display2(arr1);
                System.out.println("---------------------");
                display(arr);
                System.out.println("Player 1, it's your turn:");
                val = play(player);
                player = 2;
            } else {
                display2(arr);
                System.out.println("---------------------");
                display(arr1);
                System.out.println("Player 2, it's your turn:");
                val = play(player);
                player = 1;
            }
        }
        //play(arr);
    }
    
    public static void intliaze() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                arr[i][j] = '~';
                arr1[i][j] = '~';
            }
        }
    }
    
    public static void display(char arr[][]) {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        char c = 'A';
        for (int i = 0; i < 10; i++) {
            System.out.print((char)(c + i) + " ");
            for (int j = 0; j < 9; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println(arr[i][9]);
        }
    }
    
    public static void fill(char[][] arr, int player) {
        Scanner sc = new Scanner(System.in);
        String s;
        char c1;
        char c2;
        int n1;
        int n2;
        int val;
        int nb;
        String error = "";
        int ok = 0;
        for (int i = 5; i > 0; i--) {
            if (i == 5) {
                error = "Aircraft Carrier";
                nb = 5;
            } else if (i == 4) {
                error = "Battleship";
                nb = 4;
            } else if (i == 3) {
                error = "Submarine";
                nb = 3;
            } else if (i == 2) {
               nb = 3; 
               error = "Cruiser";
            } else {
                nb = 2;
                error = "Destroyer";
            }
            if (ok == 0) {
                if (nb == 5) {
                    System.out.println("Enter the coordinates of the Aircraft Carrier (" + nb +" cells):");
                }
                if (nb == 4) {
                    System.out.println("Enter the coordinates of the Battleship (4 cells):");
                }
                if (nb == 3 && i == 3) {
                    System.out.println("Enter the coordinates of the Submarine (3 cells):");
                } else if (nb == 3) {
                    System.out.println("Enter the coordinates of the Cruiser (3 cells):");
                }
                if (nb == 2) {
                    System.out.println("Enter the coordinates of the Destroyer (2 cells):");
                }
                ok = 1;
            }
            s = sc.next();
            c1 = s.charAt(0);
            n1 = Integer.parseInt(s.substring(1));
            s = sc.next();
            c2 = s.charAt(0);
            n2 = Integer.parseInt(s.substring(1));
            val = Math.abs((c1 - 'A') - (c2 - 'A')) + Math.abs(n1 - n2) + 1;
            if (c1 < 'A' || c1 > 'J' || c2 < 'A' || c2 > 'J' || n1 < 1 || n1 > 10 || n2 > 10 || n2 < 0 || (Math.abs((c1 - 'A') - (c2 - 'A')) != 0 && Math.abs(n1 - n2) != 0)) {
                System.out.println("Error! Wrong ship location! Try again:");
                ok = 1;
                i++;
            } else if (val != nb) {
                System.out.println("Error! Wrong length of the " + error + "! Try again:");
                i++;
                ok = 1;
            } else if ((c1 - 'A' + 1 < 10 && n1 - 2 > 0 && arr[c1 - 'A' + 1][n1 - 2] == 'O') || (n1 < 10 && c1 - 'A' - 1 > 0 && arr[c1 - 'A' - 1][n1] == 'O') || (n2 < 10 && c2 - 'A' - 1 > 0 && arr[c2 - 'A' - 1][n2] == 'O') || (c2 - 'A' + 1 < 10 && n2 - 2 > 0 && arr[c2 - 'A' + 1][n2 - 2] == 'O')) {
                System.out.println("Error! You placed it too close to another one. Try again:");
                i++;
                ok = 1;
            } else {
                ok = 0;
                if (n1 > n2) {
                    int temp = n1;
                    n1 = n2;
                    n2 = temp;
                }
                if (c1 - 'A' > c2 - 'A') {
                    char temp = c1;
                    c1 = c2;
                    c2 = temp;
                }
                if (error.equals("Aircraft Carrier")) {
                    if (player == 2) {
                        A_C1[0] = c1 - 'A';
                        A_C1[1] = c2 - 'A';
                        A_C1[2] = n1 - 1;
                        A_C1[3] = n2 - 1;
                        A_C1[4] = 0;
                    } else {
                        A_C[0] = c1 - 'A';
                        A_C[1] = c2 - 'A';
                        A_C[2] = n1 - 1;
                        A_C[3] = n2 - 1;
                        A_C[4] = 0;
                    }
                } else if (error.equals("Battleship")) {
                    if (player == 2) {
                        Bat1[0] = c1 - 'A';
                        Bat1[1] = c2 - 'A';
                        Bat1[2] = n1 - 1;
                        Bat1[3] = n2 - 1;
                        Bat1[4] = 0;
                    } else {
                        Bat[0] = c1 - 'A';
                        Bat[1] = c2 - 'A';
                        Bat[2] = n1 - 1;
                        Bat[3] = n2 - 1;
                        Bat[4] = 0;
                    }
                } else if (error.equals("Submarine")) {
                    if (player == 2) {
                        sub1[0] = c1 - 'A';
                        sub1[1] = c2 - 'A';
                        sub1[2] = n1 - 1;
                        sub1[3] = n2 - 1;
                        sub1[4] = 0;                        
                    } else {
                        sub[0] = c1 - 'A';
                        sub[1] = c2 - 'A';
                        sub[2] = n1 - 1;
                        sub[3] = n2 - 1;
                        sub[4] = 0;
                    }
                } else if (error.equals("Cruiser")) {
                    if (player == 2) {
                        cur1[0] = c1 - 'A';
                        cur1[1] = c2 - 'A';
                        cur1[2] = n1 - 1;
                        cur1[3] = n2 - 1;
                        cur1[4] = 0;                        
                    } else {
                        cur[0] = c1 - 'A';
                        cur[1] = c2 - 'A';
                        cur[2] = n1 - 1;
                        cur[3] = n2 - 1;
                        cur[4] = 0;
                    }
                } else {
                    if (player == 2) {
                        dest1[0] = c1 - 'A';
                        dest1[1] = c2 - 'A';
                        dest1[2] = n1 - 1;
                        dest1[3] = n2 - 1;
                        dest1[4] = 0;                        
                    } else {
                        dest[0] = c1 - 'A';
                        dest[1] = c2 - 'A';
                        dest[2] = n1 - 1;
                        dest[3] = n2 - 1;
                        dest[4] = 0;
                    }
                }
                for (int j = c1 - 'A'; j <= c2 - 'A'; j++) {
                    for (int k = n1 - 1; k < n2; k++) {
                        arr[j][k] = 'O';
                    }
                }
                display(arr);
            }
        }
    }
    
    public static int play(int player) {
        Scanner sc = new Scanner(System.in);
        String s;
        while(true) {
            s = sc.next();
            if (s.charAt(0) < 'A' || s.charAt(0) > 'J' || Integer.parseInt(s.substring(1)) < 0 || Integer.parseInt(s.substring(1)) > 10) {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
            } else {
                if (player == 1) {
                    if (arr1[s.charAt(0) - 'A'][Integer.parseInt(s.substring(1)) - 1] == 'O') {
                        arr1[s.charAt(0) - 'A'][Integer.parseInt(s.substring(1)) - 1] = 'X';
                        // test for end of the game
                        nb1++;
                        if (nb1 == 17) {
                            System.out.println("You sank the last ship. You won. Congratulations!");
                            return 1;
                        }
                        if (check_v1() == 0) {
                            System.out.println("You hit a ship!");
                        } else {
                            System.out.println("You sank a ship! Specify a new target:");
                        }
                    } else if (arr1[s.charAt(0) - 'A'][Integer.parseInt(s.substring(1)) - 1] == '~') {
                        arr1[s.charAt(0) - 'A'][Integer.parseInt(s.substring(1)) - 1] = 'M';
                        System.out.println("You missed!");
                    }
                } else {
                    if (arr[s.charAt(0) - 'A'][Integer.parseInt(s.substring(1)) - 1] == 'O') {
                        arr[s.charAt(0) - 'A'][Integer.parseInt(s.substring(1)) - 1] = 'X';
                        nb++;
                        if (nb == 17) {
                            System.out.println("You sank the last ship. You won. Congratulations!");
                            return 0;
                        }
                        if (check() == 0) {
                            System.out.println("You hit a ship!");
                        } else {
                            System.out.println("You sank a ship! Specify a new target:");
                        }
                    } else if (arr[s.charAt(0) - 'A'][Integer.parseInt(s.substring(1)) - 1] == '~'){
                        arr[s.charAt(0) - 'A'][Integer.parseInt(s.substring(1)) - 1] = 'M';
                        System.out.println("You missed!");
                    }
                }
                return 0;
            }
        }
    }
    
    public static void display2(char [][] arr) {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        char c = 'A';
        for (int i = 0; i < 10; i++) {
            System.out.print((char)(c + i) + " ");
            for (int j = 0; j < 10; j++) {
                if (arr[i][j] == 'O') {
                    System.out.print("~ ");
                } else {
                    System.out.print(arr[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
    
    public static int check() {
        int nb = 0;
        for (int i = A_C[0]; i <= A_C[1] && A_C[4] == 0; i++) {
            for (int j = A_C[2]; j <= A_C[3]; j++) {
                if (arr[i][j] == 'X') {
                    nb++;
                }
            }
        }
        if (nb == 1 + A_C[1] - A_C[0] + A_C[3] - A_C[2]) {
            A_C[4] = 1;
            return 1;
        }
        nb = 0;
        for (int i = Bat[0]; i <= Bat[1] && Bat[4] == 0; i++) {
            for (int j = Bat[2]; j <= Bat[3]; j++) {
                if (arr[i][j] == 'X') {
                    nb++;
                }
            }
        }
        if (nb == 1 + Bat[1] - Bat[0] + Bat[3] - Bat[2]) {
            Bat[4] = 1;
            return 1;
        }
        nb = 0;
        for (int i = cur[0]; i <= cur[1] && cur[4] == 0; i++) {
            for (int j = cur[2]; j <= cur[3]; j++) {
                if (arr[i][j] == 'X') {
                    nb++;
                }
            }
        }
        if (nb == 1 + cur[1] - cur[0] + cur[3] - cur[2]) {
            cur[4] = 1;
            return 1;
        }
        nb = 0;
        for (int i = sub[0]; i <= sub[1] && sub[4] == 0; i++) {
            for (int j = sub[2]; j <= sub[3]; j++) {
                if (arr[i][j] == 'X') {
                    nb++;
                }
            }
        }
        if (nb == 1 + sub[1] - sub[0] + sub[3] - sub[2]) {
            sub[4] = 1;
            return 1;
        }
        nb = 0;
        for (int i = sub[0]; i <= sub[1] && sub[4] == 0; i++) {
            for (int j = sub[2]; j <= sub[3]; j++) {
                if (arr[i][j] == 'X') {
                    nb++;
                }
            }
        }
        if (nb == 1 + sub[1] - sub[0] + sub[3] - sub[2]) {
            sub[4] = 1;
            return 1;
        }
        nb = 0;
        for (int i = dest[0]; i <= dest[1] && dest[4] == 0; i++) {
            for (int j = dest[2]; j <= dest[3]; j++) {
                if (arr[i][j] == 'X') {
                    nb++;
                }
            }
        }
        if (nb == 1 + dest[1] - dest[0] + dest[3] - dest[2]) {
            dest[4] = 1;
            return 1;
        }
        return 0;
    }
    
    public static int check_v1() {
        int nb = 0;
        for (int i = A_C1[0]; i <= A_C1[1] && A_C1[4] == 0; i++) {
            for (int j = A_C1[2]; j <= A_C1[3]; j++) {
                if (arr1[i][j] == 'X') {
                    nb++;
                }
            }
        }
        if (nb == 1 + A_C1[1] - A_C1[0] + A_C1[3] - A_C1[2]) {
            A_C1[4] = 1;
            return 1;
        }
        nb = 0;
        for (int i = Bat1[0]; i <= Bat1[1] && Bat1[4] == 0; i++) {
            for (int j = Bat1[2]; j <= Bat1[3]; j++) {
                if (arr1[i][j] == 'X') {
                    nb++;
                }
            }
        }
        if (nb == 1 + Bat1[1] - Bat1[0] + Bat1[3] - Bat1[2]) {
            Bat1[4] = 1;
            return 1;
        }
        nb = 0;
        for (int i = cur1[0]; i <= cur1[1] && cur1[4] == 0; i++) {
            for (int j = cur1[2]; j <= cur1[3]; j++) {
                if (arr1[i][j] == 'X') {
                    nb++;
                }
            }
        }
        if (nb == 1 + cur1[1] - cur1[0] + cur1[3] - cur1[2]) {
            cur1[4] = 1;
            return 1;
        }
        nb = 0;
        for (int i = sub1[0]; i <= sub1[1] && sub1[4] == 0; i++) {
            for (int j = sub1[2]; j <= sub1[3]; j++) {
                if (arr1[i][j] == 'X') {
                    nb++;
                }
            }
        }
        if (nb == 1 + sub1[1] - sub1[0] + sub1[3] - sub1[2]) {
            sub1[4] = 1;
            return 1;
        }
        nb = 0;
        for (int i = sub1[0]; i <= sub1[1] && sub1[4] == 0; i++) {
            for (int j = sub1[2]; j <= sub1[3]; j++) {
                if (arr1[i][j] == 'X') {
                    nb++;
                }
            }
        }
        if (nb == 1 + sub1[1] - sub1[0] + sub1[3] - sub1[2]) {
            sub1[4] = 1;
            return 1;
        }
        nb = 0;
        for (int i = dest1[0]; i <= dest1[1] && dest1[4] == 0; i++) {
            for (int j = dest1[2]; j <= dest1[3]; j++) {
                if (arr1[i][j] == 'X') {
                    nb++;
                }
            }
        }
        if (nb == 1 + dest1[1] - dest1[0] + dest1[3] - dest1[2]) {
            dest1[4] = 1;
            return 1;
        }
        return 0;
    }
}
