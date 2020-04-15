package com.avatarduel.gamemanager;

import com.avatarduel.cards.*;
import com.avatarduel.cards.characters.CharacterCard;
import com.avatarduel.cards.characters.Position;
import com.avatarduel.cards.skills.AuraSkill;
import com.avatarduel.cards.skills.DestroySkill;
import com.avatarduel.exceptions.EmptyDeckException;
import com.avatarduel.exceptions.InvalidFieldIndexException;
import com.avatarduel.cards.skills.SkillCard;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class Player {
    // atribut
    private String nama;
    private int hp;
    private Deck deck;
    private ArrayList<Card> cardsInHand;
    private Field field;
    private Map<Element, Integer> currPower; // untuk menyimpan nilai power saat main
    private Map<Element, Integer> power; // untuk menyimpan nilai maksimal power

    // ctor 
    public Player(String nama) {
        this.nama = nama;
        this.hp = 0;
        this.deck = new Deck();
        this.cardsInHand = new ArrayList<Card>();
        this.field = new Field();
        this.power = new HashMap<Element, Integer>();
        this.currPower = new HashMap<Element, Integer>();
        for (Element el: Element.values()) {
            this.power.put(el, 0);
            this.currPower.put(el, 0);
        }
        try {
            CardLoader cl = new CardLoader();
            cl.loadLandCardsFromFile("../card/data/land.csv");
            cl.loadCharacterCardsFromFile("../card/data/character.csv");
            cl.loadAuraSkillFromFile("../card/data/skill_aura.csv");

            deck.loadDeck(cl.getLoadedCards());
            System.out.println(deck.size());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        for (int i = 0; i < 7; i++) {
            try {
                this.cardsInHand.add(this.deck.drawCard());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Player() {
        this.nama = "default";
        this.hp = 0;
        this.deck = new Deck();
        this.cardsInHand = new ArrayList<Card>();
        this.field = new Field();
        this.power = new HashMap<Element, Integer>();
        this.currPower = new HashMap<Element, Integer>();
        for (Element el: Element.values()) {
            this.power.put(el, 0);
            this.currPower.put(el, 0);
        }
        try {
            CardLoader cl = new CardLoader();
            cl.loadLandCardsFromFile("../card/data/land.csv");
            cl.loadCharacterCardsFromFile("../card/data/character.csv");
            cl.loadAuraSkillFromFile("../card/data/skill_aura.csv");

            deck.loadDeck(cl.getLoadedCards());
            System.out.println(deck.size());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        for (int i = 0; i < 7; i++) {
            try {
                this.cardsInHand.add(this.deck.drawCard());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    // getter
    /**
     * @return the nama
     */
    public String getNama() {
        return nama;
    }
    /**
     * @return the hp
     */
    public int getHp() {
        return hp;
    }
    /**
     * @return the deck
     */
    public Deck getDeck() {
        return deck;
    }
    /**
     * @return the cardsInHand
     */
    public ArrayList<Card> getCardsInHand() {
        return cardsInHand;
    }
    /**
     * @return the field
     */
    public Field getField() {
        return field;
    }
    /**
     * @return the power
     */
    public Map<Element, Integer> getPower() {
        return power;
    }
    /**
     * @return the currPower
     */
    public Map<Element, Integer> getCurrPower() {
        return currPower;
    }
    public int getAttackAtPos(int pos) {
        int ret = 0;
        try {
            ret = this.field.getCharacterInColumn(pos).getAttack();
        } catch (InvalidFieldIndexException e) {
            System.out.println(e.getMessage());
        }
        return ret;
    }
    public int getDefenseAtPos(int pos) {
        int ret = 0;
        try {
            ret = this.field.getCharacterInColumn(pos).getDefense();
        } catch (InvalidFieldIndexException e) {
            System.out.println(e.getMessage());
        }
        return ret;
    }
    public Position getPositionAtPos(int pos) {
        Position ret = Position.ATTACK;
        try {
            ret = this.field.getCharacterInColumn(pos).getPosition();
        } catch (InvalidFieldIndexException e) {
            System.out.println(e.getMessage());
        }
        return ret;
    }
    public CharacterCard getCharacterAtPos(int pos) {
        CharacterCard ret = new CharacterCard();
        try {
            ret = this.field.getCharacterInColumn(pos);
        } catch (InvalidFieldIndexException e) {
            System.out.println(e.getMessage());
        }
        return ret;
    }
    public SkillCard getSkillAtPos(int pos) {
        SkillCard ret = new SkillCard();
        try {
            ret = this.field.getSkillInColumn(pos);
        } catch (InvalidFieldIndexException e) {
            System.out.println(e.getMessage());
        }
        return ret;
    }
    public boolean getIsPowerUpAtPos(int pos) {
        boolean ret = true;
        try {
            ret = this.field.getCharacterInColumn(pos).getIsPowerUp();
        } catch (InvalidFieldIndexException e) {
            System.out.println(e.getMessage());
        }
        return ret;
    }
    // setter
    /**
     * @param nama the nama to set
     */
    public void setNama(String nama) {
        this.nama = nama;
    }
    /**
     * @param hp the hp to set
     */
    public void setHp(int hp) {
        this.hp = hp;
    }
    /**
     * @param deck the deck to set
     */
    public void setDeck(Deck deck) {
        this.deck = deck;
    }
    /**
     * @param cardsInHand the cardsInHand to set
     */
    public void setCardsInHand(ArrayList<Card> cardsInHand) {
        this.cardsInHand = cardsInHand;
    }
    /**
     * @param field the field to set
     */
    public void setField(Field field) {
        this.field = field;
    }
    /**
     * @param power the power to set
     */
    public void setPower(Map<Element, Integer> power) {
        this.power = power;
    }
    /**
     * @param currPower the currPower to set
     */
    public void setCurrPower(Map<Element, Integer> currPower) {
        this.currPower = currPower;
    }

    // method
    public void printNama() {
        System.out.print(this.nama);
        System.out.println(" sedang bermain.");
    }
    public void printCardsInHand() {
        for (int i = 0; i < this.getCardsInHand().size(); i++) {
            System.out.println();
            System.out.print("index ");
            System.out.println(i);
            this.getCardsInHand().get(i).printInfo();
        }
    }
    public void substractHp(int val) {
        this.hp -= val;
    }
    public void draw() {
        Card card = this.deck.drawCard();
        this.cardsInHand.add(card);
    }
    public void addPower(LandCard land) {
        this.power.replace(land.getElement(), this.power.get(land.getElement()) + 1);
    }
    public void resetPower() {
        for (Element el: Element.values()) {
            this.currPower.replace(el, this.power.get(el));
        }
    }
    public boolean isPowerEnough(CharacterCard character) {
        return this.currPower.get(character.getElement()) >= character.getPower();
    }
    public boolean isPowerEnough(SkillCard skill) {
        return this.currPower.get(skill.getElement()) >= skill.getPower();
    }
    public void usePower(CharacterCard character) {
        if (isPowerEnough(character)) {
            this.currPower.replace(character.getElement(), this.currPower.get(character.getElement())-character.getPower());
        }
    }
    public void usePower(SkillCard skill) {
        if (isPowerEnough(skill)) {
            this.currPower.replace(skill.getElement(), this.currPower.get(skill.getElement())-skill.getPower());
        }
    }
    public Card removeFromHand(int idxCard) {
        return this.cardsInHand.remove(idxCard);
    }
    public void removeCharacter(int idxCard) {
        this.field.removeCharacter(idxCard);
    }
    public void removeSkill(int idxCard) {
        this.field.removeSkill(idxCard);
    }
    public boolean canAttack(int position) throws InvalidFieldIndexException {
        CharacterCard card;
        card = this.field.getCharacterInColumn(position);
        boolean ret = card.getJustSummoned();
        return !ret;
    }
    public boolean canChangePos(int position) throws InvalidFieldIndexException {
        CharacterCard card;
        card = this.field.getCharacterInColumn(position);
        boolean ret = card.getHasAttacked();
        return !ret;
    }
    public boolean isCharacterFieldEmpty() {
        return this.field.isCharacterEmpty();
    }
}