package org.worldofscala.app.race

import com.raquo.laminar.api.L.*
import org.scalajs.dom

object RacePage {
  def apply(): HtmlElement = {
    val game       = new RaceGame(Track(), 4)
    val inputState = Var(InputState())

    // Keyboard input handling
    val keyDownObserver = Observer[String] { key =>
      inputState.update { state =>
        key match {
          case "ArrowLeft"     => state.copy(left = true)
          case "ArrowRight"    => state.copy(right = true)
          case "ArrowUp" | " " => state.copy(accelerate = true)
          case "ArrowDown"     => state.copy(brake = true)
          case _               => state
        }
      }
    }

    val keyUpObserver = Observer[String] { key =>
      inputState.update { state =>
        key match {
          case "ArrowLeft"     => state.copy(left = false)
          case "ArrowRight"    => state.copy(right = false)
          case "ArrowUp" | " " => state.copy(accelerate = false)
          case "ArrowDown"     => state.copy(brake = false)
          case _               => state
        }
      }
    }

    div(
      cls := "race-page",
      // Game canvas would go here (Three.js integration)
      div(
        cls := "race-ui",
        child <-- game.stateSignal.map { state =>
          state.status match {
            case RaceStatus.Waiting =>
              button(
                cls := "start-button",
                "Start Race",
                onClick --> { _ => game.startRace() }
              )
            case RaceStatus.Racing =>
              div(cls := "race-status", "Racing...")
            case RaceStatus.Completed =>
              div(
                cls := "race-complete",
                "Race Complete!",
                button(
                  cls := "restart-button",
                  "Restart",
                  onClick --> { _ => game.restartRace() }
                )
              )
          }
        }
      ),

      // Keyboard event listeners
      onMountCallback { _ =>
        dom.window.addEventListener(
          "keydown",
          (e: dom.KeyboardEvent) => {
            keyDownObserver.onNext(e.key)
          }
        )
        dom.window.addEventListener(
          "keyup",
          (e: dom.KeyboardEvent) => {
            keyUpObserver.onNext(e.key)
          }
        )
      }
    )
  }
}
