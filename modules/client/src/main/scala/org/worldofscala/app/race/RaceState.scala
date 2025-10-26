package org.worldofscala.app.race

case class RaceState(
  status: RaceStatus,
  cars: List[Car],
  track: Track,
  startTime: Option[Long] = None
)

enum RaceStatus:
  case Waiting, Racing, Completed

object RaceState:
  def initial(track: Track, carCount: Int): RaceState =
    RaceState(
      status = RaceStatus.Waiting,
      cars = (1 to carCount).map(i => Car(s"car-$i", Track.startPosition, color = s"#${i}F${i}F${i}F")).toList,
      track = track
    )
