package org.worldofscala.app.race

import com.raquo.laminar.api.L.*
import org.scalajs.dom

class RaceGame(track: Track, carCount: Int) {
  private var state: RaceState = RaceState.initial(track, carCount)

  val stateSignal: Signal[RaceState] = Var(state).signal

  def startRace(): Unit =
    val now = dom.window.performance.now()
    state = state.copy(
      status = RaceStatus.Racing,
      startTime = Some(now.toLong)
    )

  def updateInput(input: InputState): Unit =
    if (state.status == RaceStatus.Racing) {
      val deltaTime   = 1.0 / 60.0 // Assume 60fps
      val updatedCars = state.cars.map { car =>
        if (car.isPlayer) car.move(deltaTime, input, state.track)
        else car // AI cars would have their own logic
      }

      val newStatus =
        if (updatedCars.exists(car => state.track.isFinished(car.position)))
          RaceStatus.Completed
        else RaceStatus.Racing

      state = state.copy(cars = updatedCars, status = newStatus)
    }

  def restartRace(): Unit =
    state = RaceState.initial(track, carCount)
}
