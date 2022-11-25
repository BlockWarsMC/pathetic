package org.patheloper.api.wrapper;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.patheloper.api.util.NumberUtils;

import java.util.Objects;

@AllArgsConstructor
public class PathPosition implements Cloneable {

    @NonNull
    private PathDomain pathDomain;
    private double x;
    private double y;
    private double z;

    /**
     * Checks to see if the two positions are in the same block
     *
     * @param otherPosition The other position to check against
     * @return True if the positions are in the same block
     */
    public boolean isInSameBlock(PathPosition otherPosition) {
        return this.getBlockX() == otherPosition.getBlockX() && this.getBlockY() == otherPosition.getBlockY() && this.getBlockZ() == otherPosition.getBlockZ();
    }

    /**
     * Gets the manhattan distance between the current and another position
     * @param otherPosition the other {@link PathPosition} to get the distance to
     * @return the distance
     */
    public int manhattanDistance(PathPosition otherPosition) {
        return Math.abs(this.getBlockX() - otherPosition.getBlockX()) + Math.abs(this.getBlockY() - otherPosition.getBlockY()) + Math.abs(this.getBlockZ() - otherPosition.getBlockZ());
    }

    /**
     * Gets the octile distance between the current and another position
     * @param otherPosition the other {@link PathPosition} to get the distance to
     * @return the distance
     */
    public double octileDistance(PathPosition otherPosition) {

        double dx = Math.abs(this.x - otherPosition.x);
        double dy = Math.abs(this.y - otherPosition.y);
        double dz = Math.abs(this.z - otherPosition.z);

        double smallest = Math.min(Math.min(dx, dz), dy);
        double highest = Math.max(Math.max(dx, dz), dy);
        double mid = Math.max(Math.min(dx,dz), Math.min(Math.max(dx,dz), dy));

        double D1 = 1;
        double D2 = 1.4142135623730951;
        double D3 = 1.7320508075688772;

        return (D3 - D2) * smallest + (D2 - D1) * mid + D1 * highest;
    }

    /**
     * Gets the distance squared between the current and another position
     * @return The distance squared
     */
    public double distanceSquared(PathPosition otherPosition) {
        return NumberUtils.square(this.x - otherPosition.x) + NumberUtils.square(this.y - otherPosition.y) + NumberUtils.square(this.z - otherPosition.z);
    }

    /**
     * Gets the distance between the current and another position
     * @return The distance
     */
    public double distance(PathPosition otherPosition) {
        return NumberUtils.sqrt(this.distanceSquared(otherPosition));
    }

    /**
     * Sets the X coordinate of the {@link PathPosition}
     *
     * @param x The new X coordinate
     * @return A new {@link PathPosition}
     */
    public PathPosition setX(double x) {
        return new PathPosition(this.pathDomain, x, this.y, this.z);
    }

    /**
     * Sets the Y coordinate of the {@link PathPosition}
     *
     * @param y The new Y coordinate
     * @return A new {@link PathPosition}
     */
    public PathPosition setY(double y) {
        return new PathPosition(this.pathDomain, this.x, y, this.z);
    }

    /**
     * Sets the Z coordinate of the {@link PathPosition}
     *
     * @param z The new Z coordinate
     * @return A new {@link PathPosition}
     */
    public PathPosition setZ(double z) {
        return new PathPosition(this.pathDomain, this.x, this.y, z);
    }

    /**
     * Gets the X coordinate of the block the position is in
     *
     * @return The X coordinate of the block
     */
    public int getBlockX() {
        return (int) Math.floor(this.x);
    }

    /**
     * Gets the Y coordinate of the block the position is in
     *
     * @return The Y coordinate of the block
     */
    public int getBlockY() {
        return (int) Math.floor(this.y);
    }

    /**
     * Gets the Z coordinate of the block the position is in
     *
     * @return The Z coordinate of the block
     */
    public int getBlockZ() {
        return (int) Math.floor(this.z);
    }

    /**
     * Adds x,y,z values to the current values
     * @param x The value to add to the x
     * @param y The value to add to the y
     * @param z The value to add to the z
     * @return A new {@link PathPosition}
     */
    @NonNull
    public PathPosition add(final double x, final double y, final double z) {
        return new PathPosition(this.pathDomain, this.x + x, this.y + y, this.z + z);
    }

    /**
     * Adds the values of a vector to the position
     * @param vector The {@link PathVector} who's values will be added
     * @return A new {@link PathPosition}
     */
    @NonNull
    public PathPosition add(final PathVector vector) {
        return add(vector.getX(), vector.getY(), vector.getZ());
    }

    /**
     * Subtracts x,y,z values from the current values
     * @param x The value to subtract from the x
     * @param y The value to subtract from the y
     * @param z The value to subtract from the z
     * @return A new {@link PathPosition}
     */
    @NonNull
    public PathPosition subtract(final double x, final double y, final double z) {
        return new PathPosition(this.pathDomain, this.x - x, this.y - y, this.z - z);
    }

    /**
     * Subtracts the values of a vector from the position
     * @param vector The {@link PathVector} who's values will be subtracted
     * @return A new {@link PathPosition}
     */
    @NonNull
    public PathPosition subtract(final PathVector vector) {
        return subtract(vector.getX(), vector.getY(), vector.getZ());
    }

    /**
     * Converts the positions x,y,z to a {@link PathVector}
     *
     * @return A {@link PathVector} of the x,y,z
     */
    @NonNull
    public PathVector toVector() {
        return new PathVector(this.x, this.y, this.z);
    }

    /**
     * Rounds the x,y,z values to the floor of the values
     *
     * @return A new {@link PathPosition}
     */
    public PathPosition floor() {
        return new PathPosition(this.pathDomain, this.getBlockX(), this.getBlockY(), this.getBlockZ());
    }

    /**
     * Sets the coordinates to the middle of the block
     * @return A new {@link PathPosition}
     */
    public PathPosition mid() {
        return new PathPosition(this.pathDomain, this.getBlockX() + 0.5, this.getBlockY() + 0.5, this.getBlockZ() + 0.5);
    }

    /**
     * Gets the {@link PathDomain} the position is in
     *
     * @return The {@link PathDomain} the position is in
     */
    public @NonNull PathDomain getPathDomain() {
        return this.pathDomain;
    }

    /**
     * Gets the x value of the position
     *
     * @return The x value of the position
     */
    public double getX() {
        return this.x;
    }

    /**
     * Gets the y value of the position
     *
     * @return The y value of the position
     */
    public double getY() {
        return this.y;
    }

    /**
     * Gets the z value of the position
     *
     * @return The z value of the position
     */
    public double getZ() {
        return this.z;
    }

    @Override
    public PathPosition clone() {

        final PathPosition clone;
        try {
            clone = (PathPosition) super.clone();
        } catch (CloneNotSupportedException ex) {
            throw new IllegalStateException("Superclass messed up", ex);
        }

        clone.pathDomain = this.pathDomain;

        clone.x = this.x;
        clone.y = this.y;
        clone.z = this.z;

        return clone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PathPosition that = (PathPosition) o;
        return Double.compare(that.x, x) == 0 && Double.compare(that.y, y) == 0 && Double.compare(that.z, z) == 0 && pathDomain.equals(that.pathDomain);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pathDomain, x, y, z);
    }

    public String toString() {
        return "PathPosition(pathDomain=" + this.getPathDomain() + ", x=" + this.getX() + ", y=" + this.getY() + ", z=" + this.getZ() + ")";
    }
}
