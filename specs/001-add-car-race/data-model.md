# Data Model: Add Simple Car Race

**Date**: 2025-10-26
**Feature**: 001-add-car-race

## Overview

The car race feature requires client-side data models for game entities. All models are immutable case classes following Scala best practices.

## Core Entities

### Car

Represents a racing vehicle with position, movement, and visual properties.

```scala
case class Car(
  id: String,
  position: Vector3,        // Current 3D position (x, y, z)
  velocity: Vector3,        // Current movement vector
  rotation: Double,         // Rotation around Y-axis (radians)
  color: String,            // Hex color code for visual representation
  isPlayer: Boolean = false // Whether this is the player-controlled car
) {
  def move(deltaTime: Double, input: InputState): Car = {
    // Calculate new position based on input and physics
    // Implementation in Car.scala
  }

  def checkBoundaries(track: Track): Car = {
    // Ensure car stays within track boundaries
    // Implementation in Car.scala
  }
}
```

**Fields**:
- `id`: Unique identifier for the car
- `position`: Current 3D coordinates using Three.js Vector3
- `velocity`: Movement vector for physics calculations
- `rotation`: Y-axis rotation for directional movement
- `color`: Visual color (e.g., "#ff0000" for red)
- `isPlayer`: Flag for player vs AI cars

**Validation Rules**:
- Position must be within track boundaries
- Velocity magnitude cannot exceed maximum speed
- Rotation must be normalized to 0-2π range

### Track

Represents the racing environment with boundaries and checkpoints.

```scala
case class Track(
  width: Double,           // Track width in world units
  length: Double,          // Track length (finish line distance)
  boundaries: List[Vector3], // Boundary points defining track shape
  finishLine: Vector3       // Position of finish line
) {
  def isWithinBounds(position: Vector3): Boolean = {
    // Check if position is inside track boundaries
    // Implementation in Track.scala
  }

  def getProgress(carPosition: Vector3): Double = {
    // Calculate race progress (0.0 to 1.0)
    // Implementation in Track.scala
  }
}
```

**Fields**:
- `width`: Lateral extent of the track
- `length`: Distance from start to finish
- `boundaries`: List of points defining track shape
- `finishLine`: 3D position of the finish line

**Validation Rules**:
- Width and length must be positive
- Boundaries must form a closed shape
- Finish line must be within track boundaries

### Race

Represents the overall race state and configuration.

```scala
case class Race(
  id: String,
  cars: List[Car],         // All cars in the race
  track: Track,            // Race track
  status: RaceStatus,      // Current race state
  startTime: Option[Long], // Unix timestamp when race started
  duration: Option[Long]   // Race duration in milliseconds
) {
  def update(deltaTime: Double, input: InputState): Race = {
    // Update all cars and check race completion
    // Implementation in RaceGame.scala
  }

  def isComplete: Boolean = {
    // Check if any car has crossed finish line
    // Implementation in RaceGame.scala
  }
}
```

**Fields**:
- `id`: Unique race identifier
- `cars`: List of all participating cars
- `track`: Track configuration
- `status`: Current race status (Waiting, Racing, Completed)
- `startTime`: When the race began
- `duration`: How long the race took

**Validation Rules**:
- Must have at least one car
- Status transitions must follow valid sequence
- Duration only set when race is complete

## Supporting Types

### RaceStatus

Enumeration for race states.

```scala
enum RaceStatus:
  case Waiting    // Race not started
  case Racing     // Race in progress
  case Completed  // Race finished
  case Paused     // Race temporarily stopped
```

### InputState

Represents user input for car control.

```scala
case class InputState(
  left: Boolean = false,   // Left arrow key pressed
  right: Boolean = false,  // Right arrow key pressed
  accelerate: Boolean = false, // Up arrow or space pressed
  brake: Boolean = false   // Down arrow pressed
)
```

### Vector3

3D vector for positions and directions (using Three.js Vector3 facade).

```scala
// Facade for Three.js Vector3
case class Vector3(x: Double, y: Double, z: Double)
```

## Relationships

- **Race** contains multiple **Car** instances
- **Race** references one **Track**
- **Car** references **Track** for boundary checking
- **InputState** affects **Car** movement calculations

## State Transitions

### Car Movement
1. Input received → Velocity calculated
2. Position updated → Boundary check applied
3. Visual representation updated

### Race Flow
1. Waiting → Racing (start button pressed)
2. Racing → Completed (car crosses finish line)
3. Completed → Waiting (restart requested)

## Data Flow

1. User input captured → InputState created
2. Game loop updates → Race state modified
3. Three.js scene updated → Visual changes rendered
4. UI components updated → Status display changed