package MidProject;

public class Main {
    public static void main(String[] args) {

//        int iterations = 1000;
//        int allRounds = 0;
//        int allWars = 0;
//        int allDoubleWars = 0;
//        long start = System.currentTimeMillis();
//        for (int i = 0; i < iterations; i++) {
//            Player p1 = new Player("Player 1");
//            Player p2 = new Player("Player 2");
//            Player p3 = new Player("Player 3");
//            War war = new War(p1, p2, p3);
//            allRounds += war.getRound();
//            allWars += war.getWarCount();
//            allDoubleWars += war.getDoubleWarCount();
//        }
//        for (int i = 0; i < iterations; i++) {
//            Player p1 = new Player("Player 1");
//            Player p2 = new Player("Player 2");
//            War war = new War(p1, p2);
//            allRounds += war.getRound();
//            allWars += war.getWarCount();
//            allDoubleWars += war.getDoubleWarCount();
//        }
//////
//        long end = System.currentTimeMillis();
//        System.out.println("Runtime (ms) of " + iterations + " games: " + (end-start));
//        System.out.println("Total Rounds in " + iterations + " games: " + allRounds);
//        System.out.println("Total Wars in " + iterations + " games: " + allWars);
//        System.out.println("Total Double Wars in " + iterations + " games: " + allDoubleWars);
//        System.out.println("Average Rounds per Game in " + iterations + "s: " + allRounds/iterations);
//        System.out.println("Average Wars per Game in " + iterations + "s: " + allWars/iterations);
//        System.out.println("Average Double Wars per Game in " + iterations + "s: " + allDoubleWars/iterations);

        Player p1 = new Player("Player 1");
        Player p2 = new Player("Player 2");
        Player p3 = new Player("Player 3");
        War war = new War(p1, p2, p3);

//        Player p1 = new Player("Player 1");
//        Player p2 = new Player("Player 2");
//        War war = new War(p1, p2);
    }
}
