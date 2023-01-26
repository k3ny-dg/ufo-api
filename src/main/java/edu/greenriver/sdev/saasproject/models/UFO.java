package edu.greenriver.sdev.saasproject.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}
