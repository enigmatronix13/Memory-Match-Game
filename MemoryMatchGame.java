import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.*;

// Interface to define matchable behavior
interface Matchable {
    boolean matches(Matchable other);
}

// Abstract class GameCard that implements Matchable
abstract class GameCard extends JButton implements Matchable {
    protected String name;
    protected boolean isFlipped;
    protected ImageIcon frontImage;
    protected ImageIcon backImage;

    public GameCard(String name) {
        this.name = name;
        this.isFlipped = false;
        this.setIcon(backImage); // Initial back image
    }

    public abstract void flipCard();

    public boolean isFlipped() {
        return isFlipped;
    }

    @Override
    public boolean matches(Matchable other) {
        if (other instanceof GameCard) {
            return this.name.equals(((GameCard) other).name);
        }
        return false;
    }
}

// Generic class to handle pairs of GameCards
class CardPair<T extends GameCard> {
    private T card1;
    private T card2;

    public CardPair(T card1, T card2) {
        this.card1 = card1;
        this.card2 = card2;
    }

    public boolean isMatch() {
        return card1.matches(card2);
    }
}

// Custom exception for invalid moves
class InvalidMoveException extends Exception {
    public InvalidMoveException(String message) {
        super(message);
    }
}

// Main Memory Match Game class
public class MemoryMatchGame extends JFrame {
    private List<Card> cards;
    private Card firstSelectedCard = null;
    private ExecutorService executor;
    private boolean isBusy = false;

    public MemoryMatchGame() {
        setTitle("Memory Match Game");
        setLayout(new GridLayout(4, 4)); // 4x4 grid layout
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);

        cards = new ArrayList<>();
        executor = Executors.newSingleThreadExecutor();

        // Initialize card images
        String[] cardImages = {"card0", "card1", "card2", "card3", "card4", "card5", "card6", "card7"};

        // Add two of each card for matching pairs
        for (String imageName : cardImages) {
            Card card1 = new Card(imageName);
            Card card2 = new Card(imageName);
            cards.add(card1);
            cards.add(card2);
        }

        // Shuffle the deck
        Collections.shuffle(cards);

        // Add each card to the frame
        for (Card card : cards) {
            card.addActionListener(new CardClickListener());
            add(card);
        }

        setVisible(true);
    }

    // Inner class Card, extends GameCard
    class Card extends GameCard {
        public Card(String name) {
            super(name);

            // Set front image
            ImageIcon icon = new ImageIcon("images/" + name + ".jpg");
            Image image = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            this.frontImage = new ImageIcon(image);

            // Set back image
            ImageIcon backIcon = new ImageIcon("images/back.jpg");
            Image backImageResized = backIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            this.backImage = new ImageIcon(backImageResized);
            this.setIcon(backImage); // Initially set to back image
        }

        @Override
        public void flipCard() {
            if (isFlipped) {
                setIcon(backImage);
            } else {
                setIcon(frontImage);
            }
            isFlipped = !isFlipped;
        }
    }

    // Inner class for handling card clicks
    class CardClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (isBusy) return; // Prevent action if busy with another flip
            try {
                handleCardClick((Card) e.getSource());
            } catch (InvalidMoveException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    // Method to handle card clicks with multithreading for delay
    private synchronized void handleCardClick(Card clickedCard) throws InvalidMoveException {
        if (clickedCard.isFlipped()) {
            throw new InvalidMoveException("Card is already flipped!");
        }

        clickedCard.flipCard(); // Flip the selected card

        if (firstSelectedCard == null) {
            firstSelectedCard = clickedCard; // First card selected
        } else {
            // Create a pair and check for match
            CardPair<Card> pair = new CardPair<>(firstSelectedCard, clickedCard);
            if (pair.isMatch()) {
                JOptionPane.showMessageDialog(this, "It's a match!");
                firstSelectedCard = null; // Reset first card selection
            } else {
                isBusy = true;
                // Delay before flipping back
                executor.execute(() -> {
                    try {
                        Thread.sleep(1000); // Wait 1 second before flipping back
                        SwingUtilities.invokeLater(() -> {
                            firstSelectedCard.flipCard();
                            clickedCard.flipCard();
                            firstSelectedCard = null; // Reset first card selection
                            isBusy = false; // Allow next moves
                        });
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                });
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MemoryMatchGame::new);
    }
}
