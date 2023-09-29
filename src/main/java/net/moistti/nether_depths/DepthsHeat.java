package net.moistti.nether_depths;

/**
 * Responsible for all the methods involved in the calculations for the depths heat mechanic.
 * */
public interface DepthsHeat {
    float getHeat();
    void heat(float amt);
    void cool(float amt);
}