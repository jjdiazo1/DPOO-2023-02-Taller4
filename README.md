# Lights Out Game Java Swing

https://github.com/user-attachments/assets/e3b8469e-a81e-476d-9120-1194f6f65818

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
  
  <img width="839" alt="Screenshot 2024-10-10 at 6 55 10 PM" src="https://github.com/user-attachments/assets/ed9b871e-1ee1-4159-b194-3de389873fdf">

2. **Medium Mode**
   - Intermediate difficulty

  <img width="898" alt="Screenshot 2024-10-10 at 7 19 15 PM" src="https://github.com/user-attachments/assets/45d7413d-4d7f-4334-9fbb-650d343d06e6">


3. **Hard Mode**:
   - The board size increases, and the patterns of light changes are more complex.
   - Provides a greater challenge for experienced players.
  
  <img width="839" alt="Screenshot 2024-10-10 at 6 55 56 PM" src="https://github.com/user-attachments/assets/3d2cc22b-6cd9-4e57-9717-f4d8583fcfe0">

Players can choose the game mode using the difficulty selector in the GUI.

## How to Run the Game

To run the **Lights Out** game, follow these steps:

1. **Clone the repository** or download the project files.
2. **Open the project** in an IDE such as **Eclipse** or **IntelliJ**.
3. **Run** the `LightsOutGUI.java` class. This class is responsible for initializing the game window and starting the game.

The game interface allows you to select the board size and difficulty level, start a new game, reset the board, and view the top 10 scores.

## Features

- **Top 10 Scores**: The game maintains a list of the top 10 scores, which can be reset by the user.

<img width="942" alt="Screenshot 2024-10-10 at 6 55 50 PM" src="https://github.com/user-attachments/assets/f4354993-87db-4500-bd16-15cc5082270b">

  
- **Adjustable Board Sizes**: Choose between multiple board sizes, from 4x4 to 10x10.

<img width="915" alt="Screenshot 2024-10-10 at 7 04 56 PM" src="https://github.com/user-attachments/assets/b4039a0a-8a04-4ccf-b471-197db1ac12b7">


- **Interactive GUI**: The player interacts with the board by clicking on tiles, which toggles the lights.

<img width="839" alt="Screenshot 2024-10-10 at 6 54 54 PM" src="https://github.com/user-attachments/assets/210ba0f3-1945-4878-aa26-e94183ce1ab6">





