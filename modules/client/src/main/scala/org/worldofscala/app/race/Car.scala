package org.worldofscala.app.race

import THREE.Vector3

/**
 * Represents a racing vehicle with position, movement, and visual properties.
 * Handles physics calculations for car movement and collision detection.
 */
case class Car(
  id: String,                           // Unique identifier for the car
  position: Vector3,                    // Current 3D position
  velocity: Vector3 = Vector3(0, 0, 0), // Current movement vector
  rotation: Double = 0.0,               // Rotation around Y-axis (radians)
  color: String,                        // Hex color code for visual representation
  isPlayer: Boolean = false             // Whether this is the player-controlled car
) {

  /**
   * Updates car position and physics based on input and time delta. Applies
   * acceleration, steering, and boundary constraints.
   */
  def move(deltaTime: Double, input: InputState, track: Track): Car = {
    // Calculate acceleration based on input
    val acceleration = if (input.accelerate) 10.0 else (if (input.brake) -5.0 else 0.0)
    val turnSpeed    = if (input.left) -2.0 else (if (input.right) 2.0 else 0.0)

    // Update velocity (only forward/backward movement)
    val newVelocity = Vector3(
      velocity.x.map(_ + acceleration * deltaTime),
      velocity.y,
      velocity.z
    )

    // Update rotation based on steering input
    val newRotation = rotation + turnSpeed * deltaTime

    // Calculate movement direction and new position
    val direction   = Vector3(math.cos(newRotation), 0, math.sin(newRotation))
    val newPosition =
      Vector3(
        position.x.map(_ + (direction.x.getOrElse(0.0) * newVelocity.x.getOrElse(0.0) * deltaTime)),
        position.y,
        position.z
      )

    // Apply track boundary constraints
    val boundedPosition = track.constrainToBounds(newPosition)

    copy(
      position = boundedPosition,
      velocity = newVelocity,
      rotation = newRotation
    )
  }
}
