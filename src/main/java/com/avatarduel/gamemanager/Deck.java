package com.avatarduel.gamemanager;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import com.avatarduel.cards.*;
import com.avatarduel.exceptions.EmptyDeckException;

/**
 * This class store ArrayList of Card use to draw from a player.
 */
public class Deck {
    private ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
    }

    private Card getCard(final int index) {
        return this.cards.get(index);
    }

    private void addCard(final Card card) {
        this.cards.add(card);
    }

    private void removeCard(final Card card) {
        this.cards.remove(card);
    }

    public void loadDeck(ArrayList<Card> cardsList) {
        for (Card card: cardsList) {
            addCard(card);
        }
    }

    /**
     * Take random position in valid deck size range then get and remove the card from the array list.
     * @return Card the random card available in array list.
     */
    public Card drawCard() throws EmptyDeckException {
        if (cards.size() == 0) {
            throw new EmptyDeckException("The deck is empty");
        }
        final Random rand = new Random();
        final int idxDraw = rand.nextInt(cards.size()); // mengambil posisi random pada deck dari 0-(cards.size-1)

        Card randomCard = getCard(idxDraw); // mengakses cards pada posisi ke-idxDraw
        removeCard(randomCard); // me-remove cards pada posisi ke-idxDraw dari deck

        return randomCard;
    }
    /**
     * Testing Deck
     */
    public static void main(String[] args) throws IOException, URISyntaxException {
        try {
            CardLoader cl = new CardLoader();
//            cl.loadLandCardsFromFile("../card/data/land.csv");
//            cl.loadCharacterCardsFromFile("../card/data/character.csv");
//            cl.loadAuraSkillFromFile("../card/data/skill_aura.csv");

            Deck deck = new Deck();

            deck.loadDeck(cl.getLoadedCards());

            Card testCard1 = deck.drawCard();
            Card testCard2 = deck.drawCard();
            Card testCard3 = deck.drawCard();
            testCard1.printInfo();
            testCard2.printInfo();
            testCard3.printInfo();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}