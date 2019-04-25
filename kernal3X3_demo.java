package Project;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class kernal3X3_demo {
    //    private List<Edge> edges = new ArrayList<>();
    //    private List<Dot> dots = new ArrayList<>();
    private ArrayList<Edge> edges = new ArrayList<>();
    private ArrayList<Dot> dots = new ArrayList<>();
    private Color currentColor = Color.RED;
    //      sthe coordinrray
    //      private Arate of current edge in array NO.2
    private static int currX = 0;
    private static int currY = 0;
    //    The first arayList<String> coordinates = new ArrayList<>();

    //    The second array
    private static int[][] vectors2D ={
            {0,-1,0,-1,0},
            {-1,0,-1,0,-1},
            {0,-1,0,-1,0},
            {-1,0,-1,0,-1},
            {0,-1,0,-1,0}
    };

    //The third array
    private int [] vectors1D = new int[12];

    //The forth array
    //private ArrayList<Integer> AlreadyFull = new ArrayList<>();

    //The fifth array
    private static ArrayList<Integer> order = new ArrayList<>();

    //To determine player NO.1 or NO.2
    //default: 0 for human (player 1)
    private static int player;
    //points of both player
    //default: Player1 is human
    private static int scoreOfPlayer1 = 0;
    //default: Player2 is computer
    private static int scoreOfPlayer2 = 0;

    public void updateArrays(int index){
        switch (index){
            case 0:{
                vectors2D[4][1] = 1;
                currX = 4;
                currY = 1;
                break;
            }
            case 1:{
                vectors2D[4][3] = 1;
                currX = 4;
                currY = 3;
                break;
            }
            case 2:{
                vectors2D[2][1] = 1;
                currX = 2;
                currY = 1;
                break;
            }
            case 3:{
                vectors2D[2][3] = 1;
                currX = 2;
                currY = 3;
                break;
            }
            case 4:{
                vectors2D[0][1] = 1;
                currX = 0;
                currY = 1;
                break;
            }
            case 5:{
                vectors2D[0][3] = 1;
                currX = 0;
                currY = 3;
                break;
            }
            case 6:{
                vectors2D[3][0] = 1;
                currX = 3;
                currY = 0;
                break;
            }
            case 7:{
                vectors2D[1][0] = 1;
                currX = 1;
                currY = 0;
                break;
            }
            case 8:{
                vectors2D[3][2] = 1;
                currX = 3;
                currY = 2;
                break;
            }
            case 9:{
                vectors2D[1][2] = 1;
                currX = 1;
                currY = 2;
                break;
            }
            case 10:{
                vectors2D[3][4] = 1;
                currX = 3;
                currY = 4;
                break;
            }
            case 11:{
                vectors2D[1][4] = 1;
                currX = 1;
                currY = 4;
                break;
            }
        }
    }

    public boolean testFour(int x, int y,int player){
        boolean isFour = false;
        //testleft
        if(x % 2 != 0 && y != 0){
            if(vectors2D[x][y-2] ==1){
                if(vectors2D[x-1][y-1] == 1 && vectors2D[x+1][y-1] == 1){
                    isFour = true;
                    if(player == 1){
                        scoreOfPlayer1++;
                    }else{
                        scoreOfPlayer2++;
                    }
                }
            }else{
                isFour = false;
            }
        }
        //testright
        if(x % 2 != 0 && y != 4){
            if(vectors2D[x][y+2] == 1){
                if(vectors2D[x-1][y+1] == 1 && vectors2D[x+1][y+1] == 1){
                    isFour = true;
                    if(player == 1){
                        scoreOfPlayer1++;
                    }else{
                        scoreOfPlayer2++;
                    }
                }
            }else{
                isFour = false;
            }
        }
        //testup
        if(x % 2 == 0 && x != 0){
            if(vectors2D[x-2][y] == 1){
                if(vectors2D[x-1][y-1] == 1 && vectors2D[x-1][y+1] == 1){
                    isFour = true;
                    if(player == 1){
                        scoreOfPlayer1++;
                    }else{
                        scoreOfPlayer2++;
                    }
                }
            }else{
                isFour = false;
            }
        }
        //testdown
        if(x % 2 == 0 && x != 4){
            if(vectors2D[x+2][y] == 1){
                if(vectors2D[x+1][y-1] == 1 && vectors2D[x+1][y+1] == 1){
                    isFour = true;
                    if(player == 1){
                        scoreOfPlayer1++;
                    }else{
                        scoreOfPlayer2++;
                    }
                }
            }else{
                isFour = false;
            }
        }
        return isFour;
    }

    public static boolean isRepeat(int index){
        boolean isRepeat = false;
        for (int target : order){
            if(target == index){
                return true;
            }
        }
        return isRepeat;
    }

    public static boolean isfull(ArrayList order){
        boolean isFull = false;
        if(order.size() == 12){
            return true;
        }
        return isFull;
    }

    public int getRandom(){
        int random = 0;
        Random rand = new Random();
        random = rand.nextInt(12);
        return random;
    }

    public void computer1Play(){
        int index = -1;
        for(;;){
            index = getRandom();
            if(isRepeat(index) == true){
                continue;
            }else{
                updateArrays(index);
                order.add(index);
                break;
            }
        }
        if(testFour(currX,currY,player) == true){

        }else{
            player++;
        }
    }

    public void computer2Play(){
        int index = -1;
        for(;;){
            index = getRandom();
            if(isRepeat(index) == true){
                continue;
            }else{
                updateArrays(index);
                order.add(index);
                break;
            }
        }
        if(testFour(currX,currY,player) == true){

        }else{
            player--;
        }
    }

    public static void show(int[][] vectors2D){
        for(int i = 0; i < vectors2D.length; i++){
            for(int j = 0; j < vectors2D[0].length; j++){
                System.out.printf("%-4d",vectors2D[i][j]);
                if(j == 4){
                    System.out.print("\n");
                }
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        kernal3X3_demo game = new kernal3X3_demo();
        System.out.println("Which one do you want to play first?\n" +
            "1 for computer1 and 2 for computer 2");
        Scanner in = new Scanner(System.in);
        player = in.nextInt();
        for(;;){
            if(player == 1){
                game.computer1Play();
//                TimeUnit.SECONDS.sleep(1);
            }else{
                game.computer2Play();
//                TimeUnit.SECONDS.sleep(1);
            }
            game.show(vectors2D);
            System.out.println("\n");
            if(isfull(order) == true){
                break;
            }
        }
        System.out.println(scoreOfPlayer1 +"\n");
        System.out.println(scoreOfPlayer2 +"\n");
        System.out.println("Game Over");

    }
}
