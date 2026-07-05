package com.gwolf.sandbox.core

class Engine(
    private val physicsEngineState: PhysicsEngineState
) : EngineState {

    private var accumulatedTime = 0f
    private val timeStep = 1f / 10f

    override fun update(deltaTime: Float) {
        accumulatedTime += deltaTime

        while (accumulatedTime >= timeStep) {
            physicsEngineState.updatePhysics()
            accumulatedTime -= timeStep
        }
    }
}