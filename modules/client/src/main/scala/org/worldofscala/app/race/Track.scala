package org.worldofscala.app.race

case class Track(
  width: Double = 20.0,
  length: Double = 100.0,
  finishLine: Vector3 = Vector3(0, 0, 100)
) {
  val startPosition: Vector3 = Vector3(0, 0, 0)

  def constrainToBounds(position: Vector3): Vector3 = {
    val halfWidth = width / 2
    position.copy(
      x = position.x.max(-halfWidth).min(halfWidth),
      z = position.z.max(0).min(length)
    )
  }

  def isFinished(position: Vector3): Boolean =
    position.z >= finishLine.z
}

object Track:
  val startPosition: Vector3 = Vector3(0, 0, 0)
