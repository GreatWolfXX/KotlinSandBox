# 🌌 Kotlin SandBox (Falling Sand Game)

An experimental 2D "falling sand" physics engine written in **pure Kotlin** and powered by **Compose Multiplatform (Desktop)**. This project is built to deeply explore the capabilities of the Kotlin 2.4 compiler, low-level memory structures, and high-performance concurrency.

---

## 🚀 Core Concept & Architecture

The project is strictly separated into decoupled layers to ensure maximum rendering and computational efficiency:

* **Engine (Business Logic):** A 2D grid simulation powered by a flat array, managing particle physics rules and chemical/physical interactions. Written in pure Kotlin with zero external dependencies.
* **UI (Presentation):** A declarative user interface using **Compose Multiplatform**, rendering the pixel grid in real-time via a high-performance `Canvas` and tracking mouse input.
* **Optimization (Kotlin 2.4+):** Data packing using `Multi-field Value Classes`, critical path inlining, and parallel physics computation via Coroutines.

---

## 🗺 Interactive Project Backlog

### 🧱 Phase 1: Foundation, Grid, and Window
- [ ] **Project Initialization** — Set up the Compose Multiplatform Desktop environment.
- [ ] **Grid Data Structure** — Create a flat `IntArray` and inline helper functions for 1D-to-2D coordinate transformation.
- [ ] **Game Loop** — Implement an infinite simulation loop inside a `LaunchedEffect` targeting ~60 FPS.
- [ ] **Scalable Rendering** — Render logical pixels onto the `Canvas` with custom viewport scaling.

### ⏳ Phase 2: Sand Physics (Mitigating Artifacts)
- [ ] **Element Modeling** — Define core particle types: `EMPTY`, `SAND`, `WALL`.
- [ ] **"Double-Update" Bug Fix** — Implement a bottom-to-top vertical sweep with alternating X-axis directions to eliminate unnatural diagonal acceleration.
- [ ] **User Interaction** — Enable real-time particle painting using mouse chords via `pointerInput`.

### 💧 Phase 3: The Elements (Water, Wood, Fire)
- [ ] **Water Logic** — Program horizontal liquid spreading with a randomized (50/50) split.
- [ ] **Fire & Wood Logic** — Implement static wood blocks and erratic fire particles with a limited lifespan (fading into `EMPTY`).
- [ ] **Chemical Reactions** — Create a flammability mechanic where wood catches fire upon contact with flame particles.

### ⚡ Phase 4: Hardcore Optimization (Kotlin 2.4+)
- [ ] **Multi-field Value Classes** — Migrate the grid to a `LongArray` and pack metadata (Type, RGB Color, Lifespan) into a `@JvmInline value class Particle(val data: Long)` to completely avoid heap allocations.
- [ ] **Inline Optimization** — Inline neighbor-checking and array-traversal functions to let the K2 compiler embed the logic directly into the hot loops.
- [ ] **Coroutines & Concurrency** — Split the grid into vertical slices and compute physics in parallel using `async(Dispatchers.Default)`.

### 💤 Phase 5: Architectural Zen (Sleeping Zones)
- [ ] **Chunking** — Divide the simulation world into $16 \times 16$ logical chunks.
- [ ] **Active/Sleeping Zones** — Skip physics calculation for chunks with no moving particles to gain massive FPS boosts.

---

## 🌿 Git & Workflow Policies

This project strictly adheres to production-grade engineering standards based on the official [Conventional Commits](https://www.conventionalcommits.org/uk/v1.0.0/) (UK) and [Semantic Versioning](https://semver.org/lang/uk/) (UK) specifications.

### 1. Branching Strategy
* **`main`** — The production-ready, stable branch. Direct commits of features or bug fixes to `main` are strictly prohibited.
* **`feature/task-number-description`** — Isolated branches for developing specific backlog features (e.g., `feature/1.2-grid-structure`).
* **`fix/bug-description`** — Isolated branches used exclusively to address and repair bugs discovered in the `main` branch (e.g., `fix/sand-edge-collision`).

### 2. Versioning Rules (SemVer: MAJOR.MINOR.PATCH)
* **PATCH (0.0.X)** — Incremented automatically when a backward-compatible bug fix from a `fix/` branch is merged (e.g., `0.2.0` ➡️ `0.2.1`).
* **MINOR (0.X.0)** — Incremented whenever a complete development Phase from the backlog is finished or a new element is successfully introduced (e.g., `0.1.0` ➡️ `0.2.0`).
* **MAJOR (X.0.0)** — The `1.0.0` milestone, indicating a fully optimized, multithreaded stable release with chunks and value classes implemented.

### 3. Commit Message Convention
Every commit must specify an explicit type and a scope:
* `feat(physics): add liquid horizontal spreading for water`
* `fix(core): resolve sand duplicating artifact on diagonal movement`
* `perf(opt): migrate grid to LongArray and implement Particle value class`
* `chore(project): bump project version to 0.2.0 according to SemVer`

---

## 🛠 Tech Stack
* **Language:** Kotlin 2.4.0
* **UI Framework:** Compose Multiplatform (Desktop)
* **Asynchronous & Concurrency:** Kotlinx Coroutines
* **Build Tool:** Gradle (Kotlin DSL) + Version Catalogs (`libs.versions.toml`)