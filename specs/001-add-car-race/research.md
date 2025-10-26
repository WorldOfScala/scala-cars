# Research Findings: Add Simple Car Race

**Date**: 2025-10-26
**Feature**: 001-add-car-race

## Decision: ThreeScalaJS Integration

**Rationale**: ThreeScalaJS provides Scala.js bindings for Three.js, enabling 3D graphics in the browser. This is the most suitable choice for creating an engaging 3D car racing experience while staying within the Scala.js ecosystem.

**Alternatives considered**:
- Pure JavaScript Three.js: Would require JS interop, breaking Scala-only development
- Other Scala 3D libraries: Limited options, ThreeScalaJS is the most mature
- 2D Canvas-based racing: Would not provide the immersive 3D experience required

## Decision: Game Architecture Pattern

**Rationale**: Use a component-based architecture with separate concerns for game state, rendering, and input handling. This follows Scala best practices and allows for clean separation of UI (Laminar) and game logic (ThreeScalaJS).

**Alternatives considered**:
- Monolithic game class: Would violate single responsibility principle
- Actor-based (Akka): Overkill for client-side game, adds unnecessary complexity
- FRP-only approach: Would mix game logic with UI rendering concerns

## Decision: Input Handling Strategy

**Rationale**: Use Laminar's event handling combined with requestAnimationFrame for smooth 60fps updates. Keyboard events will be captured at the document level and processed in the game loop.

**Alternatives considered**:
- Direct DOM event listeners: Would bypass Laminar's reactive system
- Polling keyboard state: Less efficient than event-driven approach
- Custom event system: Unnecessary when Laminar provides adequate event handling

## Decision: Physics Implementation

**Rationale**: Implement simple physics with position updates based on time deltas. Use basic collision detection for track boundaries. Keep physics lightweight to maintain 60fps performance.

**Alternatives considered**:
- Full physics engine (e.g., Cannon.js bindings): Overkill for simple racing game
- No physics: Would result in unrealistic movement
- Complex AI pathfinding: Not needed for basic user-controlled racing

## Decision: State Management

**Rationale**: Use ZIO for state management with a simple Ref-based approach. Game state will include car positions, race status, and timing information.

**Alternatives considered**:
- Mutable state with vars: Violates functional programming principles
- Complex state machines: Overkill for simple race game
- Redux-style pattern: Too heavy for client-side game state

## Decision: Testing Strategy

**Rationale**: Unit test game logic components (car movement, collision detection) using MUnit. Integration tests for full game flow. Mock Three.js objects for isolated testing.

**Alternatives considered**:
- No testing: Would compromise code quality
- Full browser-based testing: Slow and complex for unit tests
- Property-based testing: Not necessary for deterministic game logic

## Decision: Performance Optimization

**Rationale**: Use object pooling for reusable game objects, minimize DOM updates, and leverage Three.js instancing for multiple cars. Target 60fps with <16ms frame time.

**Alternatives considered**:
- No optimization: Could lead to poor performance on lower-end devices
- Premature optimization: Would complicate initial implementation
- WebGL custom shaders: Overkill for simple 3D models

## Decision: Browser Compatibility

**Rationale**: Target modern browsers (Chrome 90+, Firefox 88+, Safari 14+) that support ES2020 features. Scala.js will handle transpilation and polyfills as needed.

**Alternatives considered**:
- Support legacy browsers: Would limit modern web APIs usage
- Progressive enhancement: Unnecessary for core gaming functionality
- WebAssembly compilation: Not needed when JS performance is sufficient