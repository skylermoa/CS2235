package MidProject;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

public class Player {
    private final String name;
    private final ArrayDeque<Card> pile;

    public Player(String name) {
        this.name = name;
        pile = new ArrayDeque<>();
    }

    public String getName() {
        return name;
    }

    public ArrayDeque<Card> getPile() {
        return pile;
    }

    public Card playCard() {
        return this.pile.poll();
    }

    public ArrayList<Card> play3() {
        ArrayList<Card> chest = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            chest.add(this.playCard());
        }
        Collections.shuffle(chest);
        return chest;
    }

    public ArrayList<Card> cantContinue(Player p2, Player p3) {
        ArrayList<Card> chest = new ArrayList<>(this.getPile());
        this.getPile().clear();
        chest.addAll(p2.play3());
        chest.addAll(p3.play3());
        Collections.shuffle(chest);
        return chest;
    }

    public ArrayList<Card> cantContinue(Player p2) {
        ArrayList<Card> chest = new ArrayList<>(this.getPile());
        this.getPile().clear();
        chest.addAll(p2.play3());
        Collections.shuffle(chest);
        return chest;
    }
}
