# Implementation Plan: Add Simple Car Race

**Branch**: `001-add-car-race` | **Date**: 2025-10-26 | **Spec**: specs/001-add-car-race/spec.md
**Input**: Feature specification from `/specs/001-add-car-race/spec.md`

**Note**: This template is filled in by the `/speckit.plan` command. See `.specify/templates/commands/plan.md` for the execution workflow.

## Summary

Add a simple car race game to the existing client module using ThreeScalaJS for 3D rendering, allowing users to start races, control cars with keyboard input, and see race completion.

## Technical Context

**Language/Version**: Scala 3.7.3 with Scala.js for client-side development
**Primary Dependencies**: ThreeScalaJS (0.0.7) for 3D rendering, Laminar for UI, Frontroute for routing
**Storage**: N/A (client-side game, no persistent storage needed)
**Testing**: MUnit for unit tests, ScalaTest for integration tests
**Target Platform**: Web browsers (Chrome, Firefox, Safari) via Scala.js compilation to JavaScript
**Project Type**: Web application (existing client module)
**Performance Goals**: 60 FPS for smooth 3D rendering, <100ms response to user input
**Constraints**: Client-side only, no server communication required, must work in modern browsers
**Scale/Scope**: Single-page application with 3D race game, ~500-1000 LOC addition

## Constitution Check

*GATE: Must pass before Phase 0 research. Re-check after Phase 1 design.*

### Post-Design Review

**Design Completeness**: ✓ PASS
- Data models defined with clear validation rules and relationships
- API contracts specified for client-side game interactions
- Implementation guide provides complete code examples and testing

**Technical Feasibility**: ✓ PASS
- ThreeScalaJS integration confirmed compatible with existing Scala.js setup
- Laminar UI framework already in use, no conflicts expected
- Performance requirements (60fps) achievable with planned architecture

### Core Principles Compliance

**I. Clean Code**: ✓ PASS
- Data models use immutable case classes following Scala best practices
- Component-based architecture with clear separation of concerns
- Comprehensive code examples with meaningful naming and documentation

**II. Simple UX**: ✓ PASS
- Keyboard-only controls (arrow keys) provide intuitive gameplay
- Clear state transitions (Waiting → Racing → Completed)
- Visual feedback through status displays and restart functionality

### Development Workflow Compliance

**Code Quality Gates**: ✓ PASS
- Unit tests specified for core game logic (CarSuite, TrackSuite)
- Integration with existing scalafmt and linting configuration
- Follows existing project structure and package organization

**Version Control**: ✓ PASS
- Feature branch `001-add-car-race` properly scoped
- Implementation broken into logical commits following quickstart guide
- Preserves existing codebase patterns and conventions

### Security Requirements

**Data Protection**: ✓ PASS
- Client-side only implementation, no server data storage
- No user authentication or personal data handling required
- Game state remains local to browser session

**Compliance**: ✓ PASS
- No GDPR considerations as no user data is collected or stored
- Browser security handled by Scala.js compilation and modern web standards

### Governance

✓ PASS - Post-design review confirms implementation plan fully complies with constitution principles. All technical decisions are justified and aligned with project standards. Ready for implementation.

## Project Structure

### Documentation (this feature)

```text
specs/[###-feature]/
├── plan.md              # This file (/speckit.plan command output)
├── research.md          # Phase 0 output (/speckit.plan command)
├── data-model.md        # Phase 1 output (/speckit.plan command)
├── quickstart.md        # Phase 1 output (/speckit.plan command)
├── contracts/           # Phase 1 output (/speckit.plan command)
└── tasks.md             # Phase 2 output (/speckit.tasks command - NOT created by /speckit.plan)
```

### Source Code (repository root)

```text
modules/client/src/main/scala/org/worldofscala/app/
├── race/
│   ├── RacePage.scala          # Main race page component
│   ├── RaceGame.scala          # Core game logic and 3D scene
│   ├── Car.scala               # Car entity with 3D model and physics
│   ├── Track.scala             # Race track with boundaries and finish line
│   └── RaceState.scala         # Game state management
└── Router.scala                # Updated to include race route

modules/client/src/test/scala/org/worldofscala/app/race/
├── RaceGameSuite.scala         # Unit tests for game logic
├── CarSuite.scala              # Tests for car behavior
└── TrackSuite.scala            # Tests for track boundaries
```

**Structure Decision**: Extends existing client module structure. Race feature is contained within `modules/client/src/main/scala/org/worldofscala/app/race/` following the existing package organization. Tests follow the same pattern in the test directory.

## Complexity Tracking

> **Fill ONLY if Constitution Check has violations that must be justified**

| Violation | Why Needed | Simpler Alternative Rejected Because |
|-----------|------------|-------------------------------------|
| [e.g., 4th project] | [current need] | [why 3 projects insufficient] |
| [e.g., Repository pattern] | [specific problem] | [why direct DB access insufficient] |
