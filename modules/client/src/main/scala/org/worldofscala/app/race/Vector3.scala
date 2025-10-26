package org.worldofscala.app.race

case class Vector3(x: Double, y: Double, z: Double) {
  def +(other: Vector3): Vector3 =
    Vector3(x + other.x, y + other.y, z + other.z)

  def *(scalar: Double): Vector3 =
    Vector3(x * scalar, y * scalar, z * scalar)
}

object Vector3:
  val zero: Vector3 = Vector3(0, 0, 0)
