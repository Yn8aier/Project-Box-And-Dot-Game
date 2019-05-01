package Project;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MainFrame extends JFrame {

    private class GameMouseListener extends MouseInputAdapter {
        @Override
        public void mouseClicked(MouseEvent event){

            //detect the mouse event
            event = SwingUtilities.convertMouseEvent(MainFrame.this, event, getContentPane());
            Component component = getContentPane().getComponentAt(event.getPoint());
            if (component instanceof EdgeComponent) {
                EdgeComponent edgeComponent = (EdgeComponent) component;
                if (edgeComponent.isFree()) {
                    edgeComponent.setColor(currentColor);
                    edgeComponent.setFree(false);
                    edgeComponent.setVisible(true);
                    edgeComponent.repaint();

                    for(int i = 0; i < edges.size() ; i++){
                        if(edges.get(i).equals(edgeComponent)){
                            Already.add(i);
                        }
                    }
                    player--;
                    currentColor = currentColor == Color.RED ? Color.BLUE : Color.RED;

                }
            }
        }

        @Override
        public void mouseMoved(MouseEvent event) {
            event = SwingUtilities.convertMouseEvent(MainFrame.this, event, getContentPane());
            Component component = getContentPane().getComponentAt(event.getPoint());

            for (EdgeComponent e:edges) {
                if(e.isFree()){
                    if (e == component) {
                        e.setColor(currentColor);
                        e.setVisible(true);
                    } else {
                        e.setVisible(false);
                    }
                }

            }
//            edges.stream().filter(EdgeComponent::isFree).forEach((e) -> {
//                if (e == component) {
//                    e.setColor(currentColor);
//                    e.setVisible(true);
//                } else {
//                    e.setVisible(false);
//                }
//            });
        }
    }

    //    private List<EdgeComponent> edges = new ArrayList<>();
    private static ArrayList<EdgeComponent> edges = new ArrayList<>();
    private Color currentColor;
    private static int player = -1;
    private static ArrayList<Integer> Already = new ArrayList<>();

    public MainFrame() {
        initialize();
        currentColor = Color.RED;
        GameMouseListener mouseListener = new GameMouseListener();
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
    }

    public void initialize() {
        //The title of the window
        setTitle("CS102A Project Demo");
        //the windoe size
        setSize(400, 400);
        // Center the window.
        setLocationRelativeTo(null);
        //end the program if we click the X
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        // Add the component to the frame.
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                DotComponent dotComponent = new DotComponent(75 + 150 * i, 75 + 150 * j, 30);
                getContentPane().add(dotComponent);
            }
        }
        //Set all of the edges invisible, and then add to the frame
        edges.add(new EdgeComponent(100, 82, 150, 16));
        edges.add(new EdgeComponent(100, 232, 150, 16));
        edges.add(new EdgeComponent(82, 100, 16, 150));
        edges.add(new EdgeComponent(232, 100, 16, 150));
        for (EdgeComponent e:edges) {
            e.setVisible(false);
            getContentPane().add(e);
        }
//        edges.stream().peek(e -> e.setVisible(false)).forEach(getContentPane()::add);
    }

    public static void setEdgesFree(ArrayList Arr){
        //If computer play, set all the edges not free
        //If human play, set all the edges free and then set edges already full not free
        if(player == 1){
            for(int i = 0; i < edges.size(); i++){
                edges.get(i).setFree(false);
            }
        }else if(player == 2){
            for(int i = 0; i < edges.size(); i++){
                edges.get(i).setFree(true);
            }

            if(Already.isEmpty()){

            }else{
                for(int i = 0; i < Already.size(); i++){
                    edges.get(Already.get(i)).setFree(false);
                }
            }
        }
    }

    public static int getRandom() {
        int random = 0;
        Random rand = new Random();
        random = rand.nextInt(5);
        return random;
    }

    public static boolean isRepeat(int index) {
        boolean isRepeat = false;
        for (int target : Already) {
            if (target == index) {
                return true;
            }
        }
        return isRepeat;
    }

    private void humanplay(){

    }


    public static void main(String[] args) throws Exception{
//        Runnable runnable=new Runner();
//        SwingUtilities.invokeLater(runnable);
        Scanner in = new Scanner(System.in);
        System.out.println("Input the gamer player first. 1 for computer and 2 for human");
        player = in.nextInt();
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
        MainFrame.setEdgesFree(edges);
        int target = -1;
        while (true){
            if(player == 1){
                while(true){
                    while(true){
                        target = MainFrame.getRandom();
                        if(isRepeat(target) == false){
                            break;
                        }
                    }

                    edges.get(target).setFree(false);
                    edges.get(target).setColor(Color.BLACK);
                    edges.get(target).setVisible(true);
                    edges.get(target).repaint();
                    Already.add(target);
                    player++;
                    TimeUnit.SECONDS.sleep(2);
                    break;
                }
            }else{
                while(true){
                    if(player == 1){
                        break;
                    }


                }
            }
        }

//        SwingUtilities.invokeLater(() -> {
//            MainFrame mainFrame = new MainFrame();
//
//        });
    }

    static class Runner implements Runnable{
        @Override
        public void run() {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);

            Scanner in = new Scanner(System.in);
            int player = in.nextInt();

            if(player == 1){
                //make one edge visible and not free and without mouse clicked
                edges.get(1).setFree(false);
                edges.get(1).setColor(Color.BLACK);
                edges.get(1).setVisible(true);
                edges.get(1).repaint();
                mainFrame.setVisible(true);

            }else{
                player--;
            }


        }
    }
}
