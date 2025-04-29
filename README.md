Memory Match Game created for an Object-Oriented Programming course project in Java. Features a clean GUI where players click to match pairs of cards on a 4x4 grid. The game is designed to be interactive and modular, utilizing core OOP principles such as interfaces, abstract classes, multithreading, and custom exception handling.

---

### How It Works  
- The game starts with a grid of face-down cards, each hiding a unique image.  
- Players click on two cards to reveal their images, aiming to find a matching pair.
- If the cards match, they stay face-up; if not, they flip back after a short delay.
- The project utilizes interfaces, abstract classes, custom exceptions, multithreading, and Java Swing, demonstrating the application of core OOP principles.

---

### Key Features
- **Matchable Interface**: Makes it easy to check if two cards match.
- **Multithreading**: Adds a delay before flipping non-matching cards.
- **Custom Exception Handling**: Catches invalid moves like trying to flip a card that’s already face-up.
- **Image-Based Design**: Each card has front and back images.

---

### Getting Started
To get this running, first clone the repo to your local machine, and make sure you have Java installed. The game images are in an "images" directory, with each card image named according to its ID.

### Clone the Repository
Open your terminal and enter:

```bash
# Clone the repo with HTTPS
git clone https://github.com/enigmatronix13/Memory-Match-Game.git

# or with SSH if you have SSH set up
git clone git@github.com:enigmatronix13/Memory-Match-Game.git
```

Once it’s cloned, navigate to the project folder:

```bash
cd Memory-Match-Game
```

From the project directory, compile and run the Java file to start the game!

```bash
javac MemoryMatchGame.java
java MemoryMatchGame
