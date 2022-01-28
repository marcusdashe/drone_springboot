package io.marcus.robot.interfaces;

public interface Constants {
    enum model { LightWeight, MiddleWeight, CruiserWeight, HeavyWeight };
    enum state { IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING };

    public void setModel(Constants.model model);
    public void setState(Constants.state state);
}
