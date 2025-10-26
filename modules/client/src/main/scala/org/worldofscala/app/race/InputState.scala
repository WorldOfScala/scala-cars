package org.worldofscala.app.race

/**
 * Represents user input for car control. Tracks the state of movement keys for
 * game physics calculations.
 */
case class InputState(
  left: Boolean = false,       // Left arrow key pressed
  right: Boolean = false,      // Right arrow key pressed
  accelerate: Boolean = false, // Up arrow or space pressed
  brake: Boolean = false       // Down arrow pressed
)
