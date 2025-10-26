package org.worldofscala.app.race

import com.raquo.laminar.api.L.*
import org.scalajs.dom

/**
 * Main UI component for the car racing game. Provides the race interface with
 * controls, status display, and keyboard input handling.
 */
object RacePage {

  /**
   * Creates the race page component with game controls and UI. Initializes game
   * state and sets up keyboard event listeners.
   */
  def apply(): HtmlElement = {
    val game       = new RaceGame(Track(), 4)
    val inputState = Var(InputState())

    // Keyboard input handling observers with error handling
    val keyDownObserver = Observer[String] { key =>
      try {
        inputState.update { state =>
          key match {
            case "ArrowLeft"     => state.copy(left = true)
            case "ArrowRight"    => state.copy(right = true)
            case "ArrowUp" | " " => state.copy(accelerate = true)
            case "ArrowDown"     => state.copy(brake = true)
            case _               => state
          }
        }
      } catch {
        case ex: Exception =>
          // Log error but don't crash the game
          dom.console.error(s"Error handling key down: $key", ex)
      }
    }

    val keyUpObserver = Observer[String] { key =>
      try {
        inputState.update { state =>
          key match {
            case "ArrowLeft"     => state.copy(left = false)
            case "ArrowRight"    => state.copy(right = false)
            case "ArrowUp" | " " => state.copy(accelerate = false)
            case "ArrowDown"     => state.copy(brake = false)
            case _               => state
          }
        }
      } catch {
        case ex: Exception =>
          // Log error but don't crash the game
          dom.console.error(s"Error handling key up: $key", ex)
      }
    }

    // Set up game loop for continuous updates during racing
    val gameLoop = Observer[Unit] { _ =>
      inputState.now() match {
        case input if input.left || input.right || input.accelerate || input.brake =>
          game.updateInput(input)
        case _ => // No input, still update for AI cars if any
      }
    }

    div(
      cls := "race-page",
      // Game canvas area (Three.js integration would go here)
      div(cls := "race-canvas", "Race Track (3D rendering placeholder)"),

      // UI controls and status
      div(
        cls := "race-ui",
        child <-- game.stateSignal.map { state =>
          state.status match {
            case RaceStatus.Waiting =>
              div(
                h2("Car Race Game"),
                p("Use arrow keys to control your car. Reach the finish line first!"),
                button(
                  cls := "start-button",
                  "Start Race",
                  onClick --> { _ => game.startRace() }
                )
              )
            case RaceStatus.Racing =>
              div(
                cls := "race-status",
                "🏎️ Racing...",
                p("Arrow keys: Steer | Up/Space: Accelerate | Down: Brake")
              )
            case RaceStatus.Completed =>
              div(
                cls := "race-complete",
                h2("🎉 Race Complete!"),
                p("Congratulations! You finished the race."),
                button(
                  cls := "restart-button",
                  "Race Again",
                  onClick --> { _ => game.restartRace() }
                )
              )
          }
        }
      ),

      // Keyboard event listeners and game loop
      onMountCallback { _ =>
        // Set up keyboard listeners
        dom.window.addEventListener(
          "keydown",
          (e: dom.KeyboardEvent) => {
            keyDownObserver.onNext(e.key)
            // Prevent default behavior for game keys
            if (Set("ArrowLeft", "ArrowRight", "ArrowUp", "ArrowDown", " ").contains(e.key)) {
              e.preventDefault()
            }
          }
        )
        dom.window.addEventListener(
          "keyup",
          (e: dom.KeyboardEvent) => {
            keyUpObserver.onNext(e.key)
          }
        )

        // Start game loop using setInterval for simplicity
        val _ = dom.window.setInterval(() => gameLoop.onNext(()), 1000 / 60) // 60 FPS
        // Store the interval ID for cleanup (simplified - in real app would use a more robust approach)
      }
    )
  }
}
