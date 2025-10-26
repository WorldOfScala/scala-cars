package org.worldofscala.app.race

class CarSuite {
  def testCarMovesForward(): Unit = {
    val car   = Car("test", Vector3(0, 0, 0), color = "#ff0000", isPlayer = true)
    val track = Track()
    val input = InputState(accelerate = true)

    val movedCar = car.move(1.0, input, track)

    assert(movedCar.position.z > car.position.z)
  }

  def testCarStaysWithinBounds(): Unit = {
    val car   = Car("test", Vector3(0, 0, 0), color = "#ff0000", isPlayer = true)
    val track = Track(width = 10.0)
    val input = InputState(right = true)

    val movedCar = car.move(1.0, input, track)

    assert(movedCar.position.x <= 5.0)
    assert(movedCar.position.x >= -5.0)
  }
}
