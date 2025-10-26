# Tasks: Add Simple Car Race

**Input**: Design documents from `/specs/001-add-car-race/`
**Prerequisites**: plan.md (required), spec.md (required for user stories), research.md, data-model.md, contracts/

**Tests**: Unit tests included using MUnit as specified in plan.md

**Organization**: Tasks are grouped by user story to enable independent implementation and testing of each story.

## Format: `[ID] [P?] [Story] Description`

- **[P]**: Can run in parallel (different files, no dependencies)
- **[Story]**: Which user story this task belongs to (e.g., US1, US2, US3)
- Include exact file paths in descriptions

## Path Conventions

- **Client module**: `modules/client/src/main/scala/org/worldofscala/app/race/`
- **Test module**: `modules/client/src/test/scala/org/worldofscala/app/race/`
- **Shared types**: `modules/shared/src/main/scala/org/worldofscala/`

---

## Phase 1: Setup (Shared Infrastructure)

**Purpose**: Project initialization and basic structure

- [x] T001 Create race package structure in modules/client/src/main/scala/org/worldofscala/app/race/
- [x] T002 Create test package structure in modules/client/src/test/scala/org/worldofscala/app/race/
- [x] T003 [P] Verify ThreeScalaJS dependency in project/Dependencies.scala
- [x] T004 [P] Update Router.scala to include race route

---

## Phase 2: Foundational (Blocking Prerequisites)

**Purpose**: Core data models and utilities that MUST be complete before ANY user story can be implemented

**⚠️ CRITICAL**: No user story work can begin until this phase is complete

- [ ] T005 [P] Implement Vector3 case class in modules/client/src/main/scala/org/worldofscala/app/race/Vector3.scala
- [ ] T006 [P] Implement InputState case class in modules/client/src/main/scala/org/worldofscala/app/race/InputState.scala
- [ ] T007 [P] Implement RaceStatus enum in modules/client/src/main/scala/org/worldofscala/app/race/RaceState.scala
- [ ] T008 Implement Track case class in modules/client/src/main/scala/org/worldofscala/app/race/Track.scala
- [ ] T009 Implement Car case class in modules/client/src/main/scala/org/worldofscala/app/race/Car.scala

**Checkpoint**: Foundation ready - user story implementation can now begin in parallel

---

## Phase 3: User Story 1 - Start a Car Race (Priority: P1) 🎯 MVP

**Goal**: Enable users to start a simple car race game with basic initialization and race beginning

**Independent Test**: Navigate to race page, click start button, verify race begins with cars positioned at start

### Tests for User Story 1 ⚠️

> **NOTE: Write these tests FIRST, ensure they FAIL before implementation**

- [ ] T010 [P] [US1] Unit test for race initialization in modules/client/src/test/scala/org/worldofscala/app/race/RaceGameSuite.scala
- [ ] T011 [P] [US1] Unit test for start race functionality in modules/client/src/test/scala/org/worldofscala/app/race/RaceGameSuite.scala

### Implementation for User Story 1

- [ ] T012 [US1] Implement RaceState case class in modules/client/src/main/scala/org/worldofscala/app/race/RaceState.scala
- [ ] T013 [US1] Implement RaceGame class with startRace method in modules/client/src/main/scala/org/worldofscala/app/race/RaceGame.scala
- [ ] T014 [US1] Implement RacePage component with start button in modules/client/src/main/scala/org/worldofscala/app/race/RacePage.scala
- [ ] T015 [US1] Add race status display to RacePage component
- [ ] T016 [US1] Add CSS styling for race page in modules/client/css/style.css

**Checkpoint**: At this point, User Story 1 should be fully functional and testable independently

---

## Phase 4: User Story 2 - Control Car Movement (Priority: P2)

**Goal**: Enable users to control their car's movement during the race using keyboard inputs

**Independent Test**: Start race, use arrow keys to move car left/right, verify car stays within track bounds and responds to input

### Tests for User Story 2 ⚠️

- [ ] T017 [P] [US2] Unit test for car movement physics in modules/client/src/test/scala/org/worldofscala/app/race/CarSuite.scala
- [ ] T018 [P] [US2] Unit test for track boundary constraints in modules/client/src/test/scala/org/worldofscala/app/race/TrackSuite.scala
- [ ] T019 [P] [US2] Unit test for input state handling in modules/client/src/test/scala/org/worldofscala/app/race/RaceGameSuite.scala

### Implementation for User Story 2

- [ ] T020 [US2] Add movement logic to Car.move method in modules/client/src/main/scala/org/worldofscala/app/race/Car.scala
- [ ] T021 [US2] Add boundary checking to Track.constrainToBounds method in modules/client/src/main/scala/org/worldofscala/app/race/Track.scala
- [ ] T022 [US2] Add keyboard input handling to RacePage component in modules/client/src/main/scala/org/worldofscala/app/race/RacePage.scala
- [ ] T023 [US2] Update RaceGame.updateInput method to process player input in modules/client/src/main/scala/org/worldofscala/app/race/RaceGame.scala
- [ ] T024 [US2] Integrate input processing with game loop in RaceGame.update method

**Checkpoint**: At this point, User Stories 1 AND 2 should both work independently

---

## Phase 5: User Story 3 - Race Completion (Priority: P3)

**Goal**: Detect when cars finish the race and provide completion status with restart functionality

**Independent Test**: Complete a full race cycle from start to finish, verify completion message displays and restart works

### Tests for User Story 3 ⚠️

- [ ] T025 [P] [US3] Unit test for race completion detection in modules/client/src/test/scala/org/worldofscala/app/race/RaceGameSuite.scala
- [ ] T026 [P] [US3] Unit test for restart functionality in modules/client/src/test/scala/org/worldofscala/app/race/RaceGameSuite.scala

### Implementation for User Story 3

- [ ] T027 [US3] Add finish line detection to Track.isFinished method in modules/client/src/main/scala/org/worldofscala/app/race/Track.scala
- [ ] T028 [US3] Update RaceGame.update method to detect race completion in modules/client/src/main/scala/org/worldofscala/app/race/RaceGame.scala
- [ ] T029 [US3] Add restart functionality to RaceGame.restartRace method in modules/client/src/main/scala/org/worldofscala/app/race/RaceGame.scala
- [ ] T030 [US3] Update RacePage component to show completion status and restart button in modules/client/src/main/scala/org/worldofscala/app/race/RacePage.scala
- [ ] T031 [US3] Add CSS styling for completion and restart UI in modules/client/css/style.css

**Checkpoint**: All user stories should now be independently functional

---

## Phase 6: Polish & Cross-Cutting Concerns

**Purpose**: Improvements that affect multiple user stories

- [x] T032 [P] Add comprehensive unit tests for all components in modules/client/src/test/scala/org/worldofscala/app/race/
- [x] T033 Code cleanup and documentation updates
- [x] T034 Performance optimization for 60fps rendering
- [x] T035 Run quickstart.md validation and update if needed
- [x] T036 Add error handling for edge cases (rapid key presses, navigation during race)

---

## Dependencies & Execution Order

### Phase Dependencies

- **Setup (Phase 1)**: No dependencies - can start immediately
- **Foundational (Phase 2)**: Depends on Setup completion - BLOCKS all user stories
- **User Stories (Phase 3-5)**: All depend on Foundational phase completion
  - User stories can then proceed in parallel (if staffed)
  - Or sequentially in priority order (P1 → P2 → P3)
- **Polish (Phase 6)**: Depends on all desired user stories being complete

### User Story Dependencies

- **User Story 1 (P1)**: Can start after Foundational (Phase 2) - No dependencies on other stories
- **User Story 2 (P2)**: Can start after Foundational (Phase 2) - Builds on US1 but independently testable
- **User Story 3 (P3)**: Can start after Foundational (Phase 2) - Builds on US1/US2 but independently testable

### Within Each User Story

- Tests (if included) MUST be written and FAIL before implementation
- Models before services
- Core implementation before UI integration
- Story complete before moving to next priority

### Parallel Opportunities

- All Setup tasks marked [P] can run in parallel
- All Foundational tasks marked [P] can run in parallel (within Phase 2)
- Once Foundational phase completes, all user stories can start in parallel (if team capacity allows)
- All tests for a user story marked [P] can run in parallel
- Models within a story marked [P] can run in parallel
- Different user stories can be worked on in parallel by different team members

---

## Parallel Example: User Story 1

```bash
# Launch all tests for User Story 1 together:
Task: "Unit test for race initialization in modules/client/src/test/scala/org/worldofscala/app/race/RaceGameSuite.scala"
Task: "Unit test for start race functionality in modules/client/src/test/scala/org/worldofscala/app/race/RaceGameSuite.scala"
```

---

## Implementation Strategy

### MVP First (User Story 1 Only)

1. Complete Phase 1: Setup
2. Complete Phase 2: Foundational (CRITICAL - blocks all stories)
3. Complete Phase 3: User Story 1
4. **STOP and VALIDATE**: Test User Story 1 independently
5. Deploy/demo if ready

### Incremental Delivery

1. Complete Setup + Foundational → Foundation ready
2. Add User Story 1 → Test independently → Deploy/Demo (MVP!)
3. Add User Story 2 → Test independently → Deploy/Demo
4. Add User Story 3 → Test independently → Deploy/Demo
5. Each story adds value without breaking previous stories

### Parallel Team Strategy

With multiple developers:

1. Team completes Setup + Foundational together
2. Once Foundational is done:
   - Developer A: User Story 1
   - Developer B: User Story 2
   - Developer C: User Story 3
3. Stories complete and integrate independently

---

## Notes

- [P] tasks = different files, no dependencies
- [Story] label maps task to specific user story for traceability
- Each user story should be independently completable and testable
- Verify tests fail before implementing
- Commit after each task or logical group
- Stop at any checkpoint to validate story independently
- Avoid: vague tasks, same file conflicts, cross-story dependencies that break independence