package org.sarc.asthma.database.controllers;

import io.github.ossnass.simplejpa.EntityController;
import org.sarc.asthma.database.entities.FamilyEntity;

public class FamilyController extends EntityController<FamilyEntity, Integer> {
    public FamilyController() {
        super(FamilyEntity.class, Integer.class);
    }
}
