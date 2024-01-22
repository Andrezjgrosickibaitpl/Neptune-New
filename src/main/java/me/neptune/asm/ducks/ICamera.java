package me.neptune.asm.ducks;

public interface ICamera {
    float getCameraPitch();
    float getCameraYaw();
    void setRotations(float pitch, float yaw);
}