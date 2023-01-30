package edu.greenriver.sdev.saasproject.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A UFO object.
 * @author Keny Dutton-Gillespie
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UFO
{
    private int id;
    private String shape;
    private String description;
    private double encounterLength;

    /**
     * Returns the assigned id number of the UFO encounter
     * @return the assigned id number of the UFO encounter
     */
    public int getId()
    {
        return id;
    }

    /**
     * Assigns an id number to the UFO encounter
     * @param id the id number to assign
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * Returns the shape of the UFO
     * @return the shape of the encountered UFO
     */
    public String getShape()
    {
        return shape;
    }

    /**
     * Sets the shape of the encountered UFO
     * @param shape the shape of the encountered UFO
     */
    public void setShape(String shape)
    {
        this.shape = shape;
    }

    /**
     * Returns the reported description of the encounter
     * @return the description of the encounter
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Sets the description of an encounter
     * @param description the description of the encounter
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * Returns the reported length of the encounter in number of seconds
     * @return the length of the encounter in seconds
     */
    public double getEncounterLength()
    {
        return encounterLength;
    }

    /**
     * Sets the length of the encounter in number of seconds
     * @param encounterLength the length of the encounter in seconds
     */
    public void setEncounterLength(double encounterLength)
    {
        this.encounterLength = encounterLength;
    }
}
