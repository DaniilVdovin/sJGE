package org.pixelgame.Engine.physics;

import java.util.Objects;

enum PState{
    Non,
    Stay
}
public class PhysicsState {
    public Collider collider;
    public Physics physics;
    public PState state;
    public PhysicsState(Collider collider,Physics physics, PState state) {
        this.collider = collider;
        this.physics = physics;
        this.state = state;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhysicsState that = (PhysicsState) o;
        return Objects.equals(physics, that.physics) & Objects.equals(collider,that.collider);
    }
    @Override
    public int hashCode() {
        return Objects.hash(physics);
    }
}
