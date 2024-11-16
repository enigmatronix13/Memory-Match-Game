This is a Memory Match Game my teammates and I created for our Object Oriented Programming project in Java. The game has a clean GUI, and it’s pretty fun — you just click and match pairs of cards on a 4x4 grid. We aimed to make it interactive and modular, using OOP concepts to keep everything organized.

### How It Works
- The game starts with a grid of face-down cards, each hiding a unique image.
- Players click on two cards to reveal their images, trying to find a matching pair.
- If the cards match, they stay face-up; if not, they flip back after a short delay.
- We’ve used interfaces, abstract classes, custom exceptions, multi-threading, and java swing so it's a neat example of core OOP principles in action.

### Key Features
- **Matchable Interface**: Makes it easy to check if two cards match.
- **Multithreading**: Adds a delay before flipping non-matching cards.
- **Custom Exception Handling**: Catches invalid moves like trying to flip a card that’s already face-up.
- **Image-Based Design**: Each card has front and back images.

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
