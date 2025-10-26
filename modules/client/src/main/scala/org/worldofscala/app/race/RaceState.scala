package org.worldofscala.app.race

/**
 * Represents the overall race state and configuration. Manages the current
 * status, participating cars, and timing information.
 */
case class RaceState(
  status: RaceStatus,            // Current race state
  cars: List[Car],               // All cars in the race
  track: Track,                  // Race track configuration
  startTime: Option[Long] = None // Unix timestamp when race started
)

/**
 * Enumeration for race states. Defines the possible states a race can be in
 * during its lifecycle.
 */
enum RaceStatus:
  /** Race not started, waiting for player to begin */
  case Waiting

  /** Race in progress, cars are moving */
  case Racing

  /** Race finished, winner determined */
  case Completed

object RaceState:
  /**
   * Creates an initial race state with the specified track and number of cars.
   * All cars start at the track's start position with default colors.
   */
  def initial(track: Track, carCount: Int): RaceState =
    RaceState(
      status = RaceStatus.Waiting,
      cars = (1 to carCount).map(i => Car(s"car-$i", Track.startPosition, color = s"#${i}F${i}F${i}F")).toList,
      track = track
    )
