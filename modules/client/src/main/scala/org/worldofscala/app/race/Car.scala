package org.worldofscala.app.race

case class Car(
  id: String,
  position: Vector3,
  velocity: Vector3 = Vector3.zero,
  rotation: Double = 0.0,
  color: String,
  isPlayer: Boolean = false
) {
  def move(deltaTime: Double, input: InputState, track: Track): Car = {
    val acceleration = if (input.accelerate) 10.0 else (if (input.brake) -5.0 else 0.0)
    val turnSpeed    = if (input.left) -2.0 else (if (input.right) 2.0 else 0.0)

    val newVelocity = velocity.copy(
      x = velocity.x + acceleration * deltaTime,
      z = velocity.z
    )

    val newRotation = rotation + turnSpeed * deltaTime
    val direction   = Vector3(math.cos(newRotation), 0, math.sin(newRotation))
    val newPosition = position + (direction * newVelocity.x * deltaTime)

    val boundedPosition = track.constrainToBounds(newPosition)

    copy(
      position = boundedPosition,
      velocity = newVelocity,
      rotation = newRotation
    )
  }
}
