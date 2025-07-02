# Animal Simulation Game

## Overview
This project is an animal simulation game built with [libGDX](https://libgdx.com/). The main goal of this project was to practice and demonstrate the use of classic software Design Patterns in a real-world application.

Animals in the simulation move, eat, and interact with each other on a grid-based board. The simulation can be started, stopped, and sped up or slowed down using a simple menu. You can also add new animals to the simulation using JSON files.

## Purpose
The main purpose of this project is to show how different Design Patterns can be used together to create a flexible and maintainable simulation. The codebase is structured to make it easy to add new animal behaviors, states, and interactions.

## Design Patterns Used
This project uses several well-known design patterns, including:

- **Mediator Pattern**: Centralizes and manages all interactions between animals, reducing direct dependencies between them. (Implemented in `AnimalMediator` and `ConcreteAnimalMediator`)
- **State Pattern**: Allows animals to change their behavior based on their current state (e.g., Foraging, Hunting, Fleeing). Each state is a separate class implementing the `AnimalState` interface.
- **Strategy Pattern**: Lets animals change their movement and feeding behaviors at runtime. Different strategies (like `RandomMovement`, `FightMovement`, `FlightMovement`) can be swapped in and out.
- Other patterns in the codebase include Singleton, Command, Observer, Prototype, Decorator, Factory, and Memento.

### My Contributions
I contributed the following patterns:
- **Mediator Pattern**: For animal-to-animal interactions.
- **State Pattern**: For animal behavioral states (Foraging, Hunting, Fleeing, etc.).
- **Strategy Pattern**: For animal movement and feeding behaviors.

**Files I Authored:**
- `src/core/src/main/java/com/animalworld/animal/interactions/AnimalMediator.java`
- `src/core/src/main/java/com/animalworld/animal/interactions/ConcreteAnimalMediator.java`
- `src/core/src/main/java/com/animalworld/animal/traits/AnimalState.java`
- `src/core/src/main/java/com/animalworld/animal/traits/FleeingState.java`
- `src/core/src/main/java/com/animalworld/animal/traits/ForagingState.java`
- `src/core/src/main/java/com/animalworld/animal/traits/HuntingState.java`
- `src/core/src/main/java/com/animalworld/animal/traits/MovementBehavior.java`
- `src/core/src/main/java/com/animalworld/animal/traits/FightMovement.java`
- `src/core/src/main/java/com/animalworld/animal/traits/FlightMovement.java`
- `src/core/src/main/java/com/animalworld/animal/traits/RandomMovement.java`

## How to Run
1. Make sure you have Java and Gradle installed.
2. Clone the repository.
3. In the project root, run:
   ```
   ./gradlew lwjgl3:run
   ```
   This will start the simulation on your desktop.

## Controls
- **Speed Up**: Increases the simulation speed.
- **Slow Down**: Decreases the simulation speed.
- **Start/Stop**: Pauses or resumes the simulation.
- **Add Animal**: Lets you add a new animal from a JSON file.

## Project Structure
- `src/core/` - Main game logic and design pattern implementations.
- `src/assets/` - Images, sounds, and UI skins.
- `src/animal_data/` - Example animal JSON files.

## Credits
See `src/README.md` for a list of external sources and assets used.

---
This project is a demonstration of how design patterns can make code more organized, flexible, and easy to extend. Feel free to explore the code and try adding your own animals or behaviors! 
