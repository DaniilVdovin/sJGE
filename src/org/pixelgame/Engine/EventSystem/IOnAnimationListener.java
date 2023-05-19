package org.pixelgame.Engine.EventSystem;

import org.pixelgame.Engine.AnimationCore.AnimationState;

public interface IOnAnimationListener {
    void OnStateChange(AnimationState state);
    void OnComplete();
}
