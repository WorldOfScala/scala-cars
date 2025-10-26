package org.worldofscala.app.race

/**
 * 3D vector for positions and directions (using Three.js Vector3 facade).
 * Provides basic vector operations for game physics.
 */
case class Vector3(x: Double, y: Double, z: Double) {

  /** Vector addition */
  def +(other: Vector3): Vector3 =
    Vector3(x + other.x, y + other.y, z + other.z)

  /** Scalar multiplication */
  def *(scalar: Double): Vector3 =
    Vector3(x * scalar, y * scalar, z * scalar)
}

object Vector3:
  /** Zero vector constant */
  val zero: Vector3 = Vector3(0, 0, 0)
