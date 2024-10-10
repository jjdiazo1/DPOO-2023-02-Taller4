# Lights Out Game Java Swing

[![License](https://img.shields.io/badge/License-GPLv3-blue.svg?style=for-the-badge)](https://github.com/your-repository/LICENSE)

## Overview

**Lights Out** is a classic puzzle game where the objective is to turn off all the lights on the board. The game is implemented in Java as part of a project for the **Object-Oriented Programming** course. It includes two difficulty modes (Easy and Hard) and supports various board sizes. The game tracks the top 10 best players based on their performance.

## UML Diagram

The game is structured using the **Model-View-Controller (MVC)** design pattern. The UML diagram shows the relationship between different classes:

<img width="925" alt="UML LightsOut" src="https://github.com/user-attachments/assets/da5cb191-b8c3-4fd1-8116-cac445f8bdbd">

- **Modelo**: Contains the classes responsible for managing the game logic and data. The key classes include:
  - `Top10`: Manages the top 10 player scores.
  - `Tablero`: Manages the game board and game state.
  - `RegistroTop10`: Stores information about the best players.
  
- **Vista**: Contains the graphical user interface (GUI) and user interactions. The key classes include:
  - `LightsOutGUI`: The main class that initializes the game window, handles user input, and displays the game state.
  - `TableroPanel`: Manages the visual representation of the game board.

- **Interface**: Handles interaction between the board (`Tablero`) and the graphical representation (`TableroPanel`). It also captures user inputs through the `MouseListener` interface, allowing the player to interact with the game.

The diagram shows how each class interacts to manage the game state, display the game, and record high scores.

## Game Modes

1. **Easy Mode**:
   - The player has fewer lights to turn off, and the board is simpler to navigate.
   - Suitable for quick games or new players learning the mechanics.

2. **Hard Mode**:
   - The board size increases, and the patterns of light changes are more complex.
   - Provides a greater challenge for experienced players.

Players can choose the game mode using the difficulty selector in the GUI.

## How to Run the Game

To run the **Lights Out** game, follow these steps:

1. **Clone the repository** or download the project files.
2. **Open the project** in an IDE such as **Eclipse** or **IntelliJ**.
3. **Compile and run** the `LightsOutGUI.java` class. This class is responsible for initializing the game window and starting the game.

The game interface allows you to select the board size and difficulty level, start a new game, reset the board, and view the top 10 scores.

## Features

- **Top 10 Scores**: The game maintains a list of the top 10 scores, which can be reset by the user.
- **Adjustable Board Sizes**: Choose between multiple board sizes, from 4x4 to 10x10.
- **Interactive GUI**: The player interacts with the board by clicking on tiles, which toggles the lights.

## Screenshots

Here are some screenshots of the game in action:

![Lights Out Screenshot 1](path/to/screenshot1.png)
![Lights Out Screenshot 2](path/to/screenshot2.png)

## License

This project is licensed under the GPLv3 License - see the [LICENSE](LICENSE) file for details.

