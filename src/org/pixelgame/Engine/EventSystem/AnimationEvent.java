package org.pixelgame.Engine.EventSystem;

import org.pixelgame.Engine.AnimationCore.AnimationState;
import org.pixelgame.Engine.physics.Physics;

public class AnimationEvent extends Event<IOnAnimationListener> implements IOnAnimationListener{
    @Override
    public void OnStateChange(AnimationState state) {
        for (IOnAnimationListener c:_listeners) c.OnStateChange(state);
    }
    @Override
    public void OnComplete() {
        for (IOnAnimationListener c:_listeners) c.OnComplete();
    }
}
