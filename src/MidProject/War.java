/**
 * This program will print out a single game of War when ran.
 * In order to run this program without showing the results
 * of each round, you have to comment out the calls to the
 * printBattle and roundSummary methods as well as the winner
 * strings printed on lines 42 and 62
 */

package MidProject;

import java.util.ArrayList;
import java.util.Collections;

public class War {
    private final Player p1;
    private final Player p2;
    private Player p3;
    private Player winner;
    private final Deck deck;
    private final ArrayList<Card> pile;
    private int round = 0;
    private int wars = 0;
    private int warCount = 0;
    private int doubleWarCount = 0;

    public War(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        deck = new Deck();
        pile = new ArrayList<>();
        while (!deck.getCards().isEmpty()) {
            p1.getPile().add(deck.dealCard());
            p2.getPile().add(deck.dealCard());
        }

        winner = null;
        while(winner == null) {
            battle(p1, p2);
        }
        winner.getPile().addAll(pile);
        pile.clear();
        System.out.println("\nTHE WINNER IS " + winner.getName().toUpperCase() + " \uD83C\uDFC6");
        roundSummary();
    }

    public War(Player p1, Player p2, Player p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        deck = new Deck();
        pile = new ArrayList<>();
        while (!deck.getCards().isEmpty()) {
            p1.getPile().add(deck.dealCard());
            p2.getPile().add(deck.dealCard());
            p3.getPile().add(deck.dealCard());
        }
        while (winner == null) {
            battle(p1, p2, p3);
        }
        winner.getPile().addAll(pile);
        pile.clear();
        System.out.println("\nTHE WINNER IS " + winner.getName().toUpperCase() + " \uD83C\uDFC6");
        roundSummary();
    }

    public int getRound() { return round; }

    public int getWarCount() {
        return warCount;
    }

    public int getDoubleWarCount() {
        return doubleWarCount;
    }

    public boolean battle(Player p1, Player p2, Player p3) {
        if (p1.getPile().isEmpty()) {
            return battle(p2, p3);
        } else if (p2.getPile().isEmpty()) {
            return battle(p1, p3);
        } else if (p3.getPile().isEmpty()) {
            return battle(p1, p2);
        }

        Card p1Card = p1.playCard();
        Card p2Card = p2.playCard();
        Card p3Card = p3.playCard();
        printBattle(p1Card, p2Card, p3Card);
        round++;
        roundSummary();
        pile.add(p1Card);
        pile.add(p2Card);
        pile.add(p3Card);
        Collections.shuffle(pile);

        if (p1.getPile().isEmpty()) {
            return battle(p2, p3);
        } else if (p2.getPile().isEmpty()) {
            return battle(p1, p3);
        } else if (p3.getPile().isEmpty()) {
            return battle(p1, p2);
        }
        if (p1Card.isGreaterThan(p2Card, p3Card)) {
            p1.getPile().addAll(pile);
            pile.clear();
            return true;
        } else if (p2Card.isGreaterThan(p3Card, p1Card)) {
            p2.getPile().addAll(pile);
            pile.clear();
            return true;
        } else if (p3Card.isGreaterThan(p1Card, p2Card)) {
            p3.getPile().addAll(pile);
            pile.clear();
            return true;
        }
        if (p1Card.isEqualTo(p2Card, p3Card)) {
            warsCounter();
            round--;
            if (p1.getPile().size() > 0 && p1.getPile().size() < 4) {
                pile.addAll(p1.cantContinue(p2, p3));
            } else if (p2.getPile().size() > 0 && p2.getPile().size() < 4) {
                pile.addAll(p2.cantContinue(p1, p3));
            } else if (p3.getPile().size() > 0 && p3.getPile().size() < 4) {
                pile.addAll(p3.cantContinue(p1, p2));
            } else {
                pile.addAll(p1.play3());
                pile.addAll(p2.play3());
                pile.addAll(p3.play3());
            }
            return battle(p1, p2, p3);

        } else if (p1Card.isEqualTo(p2Card)) {
            warsCounter();
            round--;
            if ((p1.getPile().size() > 0 && p1.getPile().size() < 4)
                    && (p2.getPile().size() > 0 && p2.getPile().size() < 4)) {
                pile.addAll(p1.cantContinue(p3));
                pile.addAll(p2.cantContinue(p3));
                return battle(p1, p2, p3);
            }
            if (p1.getPile().size() > 0 && p1.getPile().size() < 4) {
                pile.addAll(p1.cantContinue(p2));
            } else if (p2.getPile().size() > 0 && p2.getPile().size() < 4) {
                pile.addAll(p2.cantContinue(p1));
            } else {
                pile.addAll(p1.play3());
                pile.addAll(p2.play3());
            }
            return battle2P(p1, p2, p3);

        } else if (p2Card.isEqualTo(p3Card)) {
            warsCounter();
            round--;
            if ((p2.getPile().size() > 0 && p2.getPile().size() < 4)
                    && (p3.getPile().size() > 0 && p3.getPile().size() < 4)) {
                pile.addAll(p3.cantContinue(p1));
                pile.addAll(p2.cantContinue(p1));
                return battle(p1, p2, p3);
            }
            if (p2.getPile().size() > 0 && p2.getPile().size() < 4) {
                pile.addAll(p2.cantContinue(p3));
            } else if (p3.getPile().size() > 0 && p3.getPile().size() < 4) {
                pile.addAll(p3.cantContinue(p2));
            } else {
                pile.addAll(p2.play3());
                pile.addAll(p3.play3());
            }
            return battle2P(p2, p3, p1);

        } else if (p3Card.isEqualTo(p1Card)) {
            warsCounter();
            round--;
            if ((p3.getPile().size() > 0 && p3.getPile().size() < 4)
                    && (p1.getPile().size() > 0 && p1.getPile().size() < 4)) {
                pile.addAll(p3.cantContinue(p2));
                pile.addAll(p1.cantContinue(p2));
                return battle(p1, p2, p3);
            }
            if (p3.getPile().size() > 0 && p3.getPile().size() < 4) {
                pile.addAll(p3.cantContinue(p1));
            } else if (p1.getPile().size() > 0 && p1.getPile().size() < 4) {
                pile.addAll(p1.cantContinue(p3));
            } else {
                pile.addAll(p3.play3());
                pile.addAll(p1.play3());
            }
            return battle2P(p3, p1, p2);
        }
        wars = 0;
        return true;
    }

    public boolean battle2P(Player p1, Player p2, Player p3) {
        if (p1.getPile().isEmpty()) {
            return battle(p2, p3);
        } else if (p2.getPile().isEmpty()) {
            return battle(p3, p1);
        }

        Card p1Card = p1.playCard();
        Card p2Card = p2.playCard();
        printBattle(p1, p2, p1Card, p2Card);
        round++;
        roundSummary();
        pile.add(p1Card);
        pile.add(p2Card);
        Collections.shuffle(pile);


        if (p1.getPile().isEmpty()) {
            return battle(p2, p3);
        } else if (p2.getPile().isEmpty()) {
            return battle(p1, p3);
        }

        if (p1Card.isEqualTo(p2Card)) {
            warsCounter();
            round--;
            if ((p1.getPile().size() > 0 && p1.getPile().size() < 4)
                    && (p2.getPile().size() > 0 && p2.getPile().size() < 4)) {
                pile.addAll(p1.cantContinue(p3));
                pile.addAll(p2.cantContinue(p3));
            } else if (p1.getPile().size() < 4 && p1.getPile().size() > 0) {
                pile.addAll(p1.cantContinue(p2));
            } else if (p2.getPile().size() < 4 && p2.getPile().size() > 0) {
                pile.addAll(p2.cantContinue(p1));
            } else {
                pile.addAll(p1.play3());
                pile.addAll(p2.play3());
            }
            return battle2P(p1, p2, p3);
        } else if (p1Card.isGreaterThan(p2Card)) {
            p1.getPile().addAll(pile);
            pile.clear();
        } else if (p2Card.isGreaterThan(p1Card)) {
            p2.getPile().addAll(pile);
            pile.clear();
        }
        wars = 0;
        return true;
    }

    public boolean battle(Player p1, Player p2) {
        if (p1.getPile().isEmpty()) {
            winner = p2;
            return false;
        } else if (p2.getPile().isEmpty()) {
            winner = p1;
            return false;
        }

        Card p1Card = p1.playCard();
        Card p2Card = p2.playCard();
        printBattle(p1, p2, p1Card, p2Card);
        round++;
        roundSummary();
        pile.add(p1Card);
        pile.add(p2Card);
        Collections.shuffle(pile);

        if (p1Card.isEqualTo(p2Card)) {
            warsCounter();
            round--;
            if (p1.getPile().isEmpty()) {
                winner = p2;
                return false;
            } else if (p2.getPile().isEmpty()) {
                winner = p1;
                return false;
            } else if (p1.getPile().size() < 4 && p1.getPile().size() > 0) {
                pile.addAll(p1.cantContinue(p2));
                round++;
                return battle(p1, p2);
            } else if (p2.getPile().size() < 4 && p2.getPile().size() > 0) {
                pile.addAll(p2.cantContinue(p1));
                round++;
                return battle(p1, p2);

            } else {
                pile.addAll(p1.play3());
                pile.addAll(p2.play3());
            }
            return battle(p1, p2);
        } else if (p1Card.isGreaterThan(p2Card)) {
            p1.getPile().addAll(pile);
            pile.clear();
        } else if (p2Card.isGreaterThan(p1Card)) {
            p2.getPile().addAll(pile);
            pile.clear();
        }
        wars = 0;
        return true;
    }

    public void warsCounter() {
        wars++;
        if (wars > 1) {
            warCount--;
            doubleWarCount++;
        } else {
            warCount++;
        }
    }

    public void printBattle(Card a, Card b, Card c) {
        if (a.isGreaterThan(b, c)) {
            System.out.println(p1.getName() + " [" + a + "]\u2B50 \t "
                    + p2.getName() + " [" + b + "] \t "
                    + p3.getName() + " [" + c + "]");
        } else if (b.isGreaterThan(c, a)) {
            System.out.println(p1.getName() + " [" + a + "] \t "
                    + p2.getName() + " [" + b + "]\u2B50 \t "
                    + p3.getName() + " [" + c + "]");
        } else if (c.isGreaterThan(a, b)) {
            System.out.println(p1.getName() + " [" + a + "] \t "
                    + p2.getName() + " [" + b + "] \t "
                    + p3.getName() + " [" + c + "]\u2B50");
        } else if (a.isEqualTo(b, c)) {
            System.out.println("WAR!");
            System.out.println(p1.getName() + " [" + a + "]\u2694 \t "
                    + p2.getName() + " [" + b + "]\u2694 \t "
                    + p3.getName() + " [" + c + "]\u2694");
        } else if (a.isEqualTo(b)) {
            System.out.println("WAR!");
            System.out.println(p1.getName() + " [" + a + "]\u2694 \t "
                    + p2.getName() + " [" + b + "]\u2694 \t "
                    + p3.getName() + " [" + c + "]");
        } else if (b.isEqualTo(c)) {
            System.out.println("WAR!");
            System.out.println(p1.getName() + " [" + a + "] \t "
                    + p2.getName() + " [" + b + "]\u2694 \t "
                    + p3.getName() + " [" + c + "]\u2694");
        } else if (c.isEqualTo(a)) {
            System.out.println("WAR!");
            System.out.println(p1.getName() + " [" + a + "]\u2694 \t "
                    + p2.getName() + " [" + b + "] \t "
                    + p3.getName() + " [" + c + "]\u2694");
        }
    }

    public void printBattle(Player p1, Player p2, Card a, Card b) {
        if (a.isEqualTo(b)) {
            System.out.println("WAR! (Round will not increase until resolved)");
            System.out.println(p1.getName() + " [" + a + "]\u2694 \t " + p2.getName() + " [" + b + "]\u2694");
        } else if (a.isGreaterThan(b)) {
            System.out.println(p1.getName() + " [" + a + "]\u2B50 \t " + p2.getName() + " [" + b + "] ");
        } else if (b.isGreaterThan(a)) {
            System.out.println(p1.getName() + " [ " + a + "] \t " + p2.getName() + " [" + b + "]\u2B50");
        }
    }

    public void roundSummary() {
        if (this.p3 == null) {
            System.out.print(p1.getName() + "'s Deck" + ": (" + p1.getPile().size() + ") ");
            for (Card ignored : p1.getPile()) {
                System.out.print("\uD83C\uDCA0 ");
            }
            System.out.println();
            System.out.print(p2.getName() + "'s Deck" + ": (" + p2.getPile().size() + ")  ");
            for (Card ignored : p2.getPile()) {
                System.out.print("\uD83C\uDCA0 ");
            }
            System.out.println();
            System.out.println("Pile: " + pile);
            System.out.println("| Round: " + round + " | Wars: "
                    + warCount + " | Double Wars: "
                    + doubleWarCount + " |");
        } else {
            System.out.print(p1.getName() + "'s Deck" + ": (" + p1.getPile().size() + ") ");
            for (Card ignored : p1.getPile()) {
                System.out.print("\uD83C\uDCA0 ");
            }
            System.out.println();
            System.out.print(p2.getName() + "'s Deck" + ": ("+ p2.getPile().size() + ") ");
            for (Card ignored : p2.getPile()) {
                System.out.print("\uD83C\uDCA0 ");
            }
            System.out.println();
            System.out.print(p3.getName() + "'s Deck" + ": (" + p3.getPile().size() + ") ");
            for (Card ignored : p3.getPile()) {
                System.out.print("\uD83C\uDCA0 ");
            }
            System.out.println();
            System.out.println("Pile: " + pile);
            System.out.println("| Round: " + round + " | Wars: "
                    + warCount + " | Double Wars: "
                    + doubleWarCount + " |");
        }
        System.out.println();
    }
}