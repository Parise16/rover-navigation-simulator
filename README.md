# Rover Navigation Simulator

A console-based Java application that simulates the navigation of a rover through different terrain conditions and obstacles.

The system analyzes factors such as obstacle type, terrain, inclination and distance to determine whether the rover can safely cross an obstacle or needs to change its route. Throughout the simulation, the application also monitors battery consumption and records the rover's mission history.

## Features

- Rover configuration with a customizable maximum inclination
- Input validation for user interactions
- Simulation of different terrain conditions
- Detection of rocks and holes
- Terrain-based navigation limitations
- Simulated distance measurement
- Automatic decision between crossing or avoiding obstacles
- Calculation of deviation angles
- Battery consumption based on terrain and movement
- Mission history tracking
- Final mission report

## How It Works

During the simulation, the rover evaluates the environment before deciding how to proceed.

1. The user configures the rover model and its maximum supported inclination.
2. The system checks whether an obstacle is present.
3. The terrain is classified as:
   - Flat
   - Rocky
   - Soft Sand
4. The obstacle inclination is evaluated according to the rover's capabilities and terrain conditions.
5. If the obstacle can be crossed safely, the rover moves over it.
6. If the inclination is too high, the system evaluates the available space on both sides.
7. The rover calculates a deviation angle and selects the shortest route around the obstacle.
8. Every movement affects battery consumption.
9. At the end of the simulation, a mission report is generated.

## Navigation Logic

Terrain conditions affect the rover's effective maximum inclination.

- **Flat terrain:** 100% of the configured inclination capacity
- **Rocky terrain:** 90% of the configured inclination capacity
- **Soft sand:** 80% of the configured inclination capacity

When a deviation is necessary, the rover calculates the turning angle using the obstacle width and its simulated distance.

```java
Math.toDegrees(Math.atan(largura / distancia));
```

This allows the program to determine the required direction change during obstacle avoidance.

## Project Structure

```text
src/
├── aplicacao/
│   └── NavegacaoRover.java
│
└── modelos/
    ├── Imagem.java
    ├── Obstaculo.java
    └── Rover.java
```

### `NavegacaoRover`

Application entry point responsible for user interaction, input validation and simulation flow.

### `Rover`

Represents the rover and contains the navigation logic, battery management, obstacle analysis and mission history.

### `Obstaculo`

Represents an obstacle and stores information such as its type, dimensions, inclination and terrain conditions.

### `Imagem`

Simulates the rover's environmental capture process, associating detected obstacles with distance information.

## Technologies

- Java
- Object-Oriented Programming
- Java Collections
- Mathematical calculations
- Console-based application

## Running the Project

### Requirements

Java JDK installed on your computer.

Clone the repository:

```bash
git clone https://github.com/YOUR-USERNAME/rover-navigation-simulator.git
```

Enter the project directory:

```bash
cd rover-navigation-simulator
```

Compile the project:

```bash
javac -d out src/modelos/Imagem.java src/modelos/Obstaculo.java src/modelos/Rover.java src/aplicacao/NavegacaoRover.java
```

Run:

```bash
java -cp out aplicacao.NavegacaoRover
```

You can also open the project using an IDE such as IntelliJ IDEA.

## Concepts Explored

This project was developed to explore concepts including:

- Object-oriented modeling
- Classes and objects
- Constructors
- Methods
- Collections
- Input validation
- Conditional logic
- Mathematical calculations
- Code organization
- Problem solving

## Future Improvements

Possible improvements for future versions include:

- Unit tests
- Improved encapsulation of rover attributes
- Separation between simulation and user interface logic
- Graphical visualization of the rover's route
- More terrain and obstacle types

## Academic Context

Academic project developed during my Computer Engineering studies at FIAP.
