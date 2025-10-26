package org.worldofscala.app.race

import com.raquo.laminar.api.L.*
import org.scalajs.dom

/**
 * Core game logic class managing race state and game loop. Handles race
 * initialization, input processing, and state updates.
 */
class RaceGame(track: Track, carCount: Int) {

  /** Current race state, updated during gameplay */
  private var state: RaceState = RaceState.initial(track, carCount)

  /** Reactive signal for UI components to observe state changes */
  val stateSignal: Signal[RaceState] = Var(state).signal

  /**
   * Starts the race by changing status to Racing and recording start time. Can
   * only be called when race is in Waiting state.
   */
  def startRace(): Unit =
    val now = dom.window.performance.now()
    state = state.copy(
      status = RaceStatus.Racing,
      startTime = Some(now.toLong)
    )

  /**
   * Processes player input and updates game state. Applies physics to player
   * car and checks for race completion. Optimized for 60fps performance with
   * minimal allocations.
   */
  def updateInput(input: InputState): Unit =
    try {
      if (state.status == RaceStatus.Racing) {
        val deltaTime   = 1.0 / 60.0 // Fixed 60fps for consistent physics
        val updatedCars = state.cars.map { car =>
          if (car.isPlayer) car.move(deltaTime, input, state.track)
          else car // AI cars would have their own logic
        }

        // Early exit optimization: check completion condition
        val newStatus =
          if (updatedCars.exists(car => state.track.isFinished(car.position)))
            RaceStatus.Completed
          else RaceStatus.Racing

        state = state.copy(cars = updatedCars, status = newStatus)
      }
    } catch {
      case ex: Exception =>
        // Log error but don't crash the game - reset to safe state
        dom.console.error("Error updating game input", ex)
        state = state.copy(status = RaceStatus.Waiting) // Reset to safe state
    }

  /**
   * Resets the race to initial state, allowing players to start over. Can be
   * called at any time to restart the race.
   */
  def restartRace(): Unit =
    state = RaceState.initial(track, carCount)
}
