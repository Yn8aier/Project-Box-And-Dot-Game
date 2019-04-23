package Project;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class kernel3X3 {
    //    private List<Edge> edges = new ArrayList<>();
//    private List<Dot> dots = new ArrayList<>();
    private ArrayList<Edge> edges = new ArrayList<>();
    private ArrayList<Dot> dots = new ArrayList<>();
    private Color currentColor = Color.RED;
    //the coordinate of current edge in array NO.2
    private static int currX = 0;
    private static int currY = 0;
    //The first array
//    private ArrayList<String> coordinates = new ArrayList<>();

//    The second array
    private int[][] vectors2D ={
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


    public kernel3X3(int canvasWidth, int canvasHeight) {
        StdDraw.setCanvasSize(canvasWidth, canvasHeight);
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 500);
        StdDraw.setYscale(0, 500);
        initializeGUI();
    }

    public void initializeArrays() {
        for(int i = 0; i < vectors1D.length; i++){
            vectors1D[i] = i;
        }
    }

    //get a random number
    public int getRandom(){
        int random = 0;
        Random rand = new Random();
        random = rand.nextInt(11);
        return random;
    }

    public void updateArrays(int index){
        switch (index){
            case 0:{
                vectors2D[4][1] = 1;
                break;
            }
            case 1:{
                vectors2D[4][3] = 1;
                break;
            }
            case 2:{
                vectors2D[2][1] = 1;
                break;
            }
            case 3:{
                vectors2D[2][3] = 1;
                break;
            }
            case 4:{
                vectors2D[0][1] = 1;
                break;
            }
            case 5:{
                vectors2D[0][3] = 1;
                break;
            }
            case 6:{
                vectors2D[3][0] = 1;
                break;
            }
            case 7:{
                vectors2D[1][0] = 1;
                break;
            }
            case 8:{
                vectors2D[3][2] = 1;
                break;
            }
            case 9:{
                vectors2D[1][2] = 1;
                break;
            }
            case 10:{
                vectors2D[3][4] = 1;
                break;
            }
            case 11:{
                vectors2D[1][4] = 1;
                break;
            }
        }
    }

    public boolean testFour(int x, int y){
        boolean isFour = false;
        //testleft
        if(x % 2 != 0 && y != 0){
            if(vectors2D[x][y-2] ==1){
                if(vectors2D[x-1][y-1] == 1 && vectors2D[x+1][y-1] == 1){
                    isFour = true;
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
                }
            }else{
                isFour = false;
            }
        }
        return isFour;
    }

    public void initializeGUI() {
        //row1
        edges.add(new Edge(50, 50, 220, 10));
        edges.add(new Edge(250, 50, 220, 10));
        //row2
        edges.add(new Edge(50, 250, 220, 10));
        edges.add(new Edge(250, 250, 220, 10));
        //row3
        edges.add(new Edge(50, 450, 220, 10));
        edges.add(new Edge(250, 450, 220, 10));
        //column1
        edges.add(new Edge(50, 50, 10, 220));
        edges.add(new Edge(50, 250, 10, 220));
        //column2
        edges.add(new Edge(250, 50, 10, 220));
        edges.add(new Edge(250, 250, 10, 220));
        //column3
        edges.add(new Edge(450, 50, 10, 220));
        edges.add(new Edge(450, 250, 10, 220));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                dots.add(new Dot(50 + 200 * i, 50 + 200 * j, 27));
            }
        }


    }

    public void update() {
        Point mousePoint = new Point((int) StdDraw.mouseX(), (int) StdDraw.mouseY());
        boolean isMousePressed = StdDraw.isMousePressed();
        boolean foundEdge = false;
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).isFree()) {
                if (!foundEdge && edges.get(i).getBounds().contains(mousePoint)) {
                    edges.get(i).setColor(currentColor);
                    edges.get(i).setVisible(true);

                    if (isMousePressed) {
                        currentColor = currentColor == Color.RED ? Color.BLUE : Color.RED;
                        edges.get(i).setFree(false);
                        //update arrays
                        updateArrays(i);
                    }
                    foundEdge = true; // Avoid multiple selections.
                } else {
                    edges.get(i).setVisible(false);
                }
            }
        }
    }

    public void paint() {
        StdDraw.clear();
        // Paint edges first, so dots will be on the top layer.
        for (Edge edge : edges) {
            edge.paint();
        }
        for (Dot dot : dots) {
            dot.paint();
        }
//      edges.forEach(Edge::paint);
//      dots.forEach(Dot::paint);
        StdDraw.show();
    }

    public void computerPlay(){
        int index = -1;
        for(;;){
            index = getRandom();
            if(isRepeat(index) == true){
                continue;
            }else{
                currentColor = currentColor == Color.RED ? Color.BLUE : Color.RED;
                edges.get(index).setFree(false);
                order.add(index);
                break;
            }
        }

        if(testFour(currX,currY) == true){
            scoreOfPlayer2++;
        }else{
            player++;
        }
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


    public static void main(String[] args) {
        System.out.println("who do you want to play first?" +
                "1 for you and 0 for computer");
        Scanner in = new Scanner(System.in);
        player = in.nextInt();
        //open and initialize the game window
        kernel3X3 game = new kernel3X3(500, 500);
        game.initializeArrays();
        while (true) {
            if(player == 1){
                game.update();
                game.paint();
            }else{
                game.computerPlay();
                game.paint();
            }

            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //the interior of the game

    }
}
