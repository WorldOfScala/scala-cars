package org.worldofscala.app.race

/**
 * Represents the racing environment with boundaries and checkpoints. Defines
 * the track geometry and provides collision detection.
 */
case class Track(
  width: Double = 20.0,                    // Track width in world units
  length: Double = 100.0,                  // Track length (finish line distance)
  finishLine: Vector3 = Vector3(0, 0, 100) // Position of finish line
) {

  /** Starting position for all cars */
  val startPosition: Vector3 = Vector3(0, 0, 0)

  /**
   * Constrains a position to stay within track boundaries. Prevents cars from
   * driving off the track.
   */
  def constrainToBounds(position: Vector3): Vector3 = {
    val halfWidth = width / 2
    position.copy(
      x = position.x.max(-halfWidth).min(halfWidth),
      z = position.z.max(0).min(length)
    )
  }

  /**
   * Checks if a car has reached the finish line. Used to determine race
   * completion.
   */
  def isFinished(position: Vector3): Boolean =
    position.z >= finishLine.z
}

object Track:
  /** Default starting position constant */
  val startPosition: Vector3 = Vector3(0, 0, 0)
