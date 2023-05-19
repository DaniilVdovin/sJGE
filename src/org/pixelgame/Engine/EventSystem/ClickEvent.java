package org.pixelgame.Engine.EventSystem;

import org.pixelgame.Engine.physics.Physics;

// TODO: 17.05.2023
public class ClickEvent extends Event<IOnClickListener> implements IOnClickListener{
    @Override
    public void OnClick() {for (IOnClickListener c:_listeners) c.OnClick();}
}
