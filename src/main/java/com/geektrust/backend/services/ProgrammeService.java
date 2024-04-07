package com.geektrust.backend.services;

import com.geektrust.backend.entities.Cart;
import com.geektrust.backend.entities.Programme;

public class ProgrammeService {
    private static final int CERTIFICATION_COST = 3000;
    private static final int DEGREE_COST = 5000;
    private static final int DIPLOMA_COST = 2500;
    public Programme createProgramme(String type) {
        switch (type) {
            case "CERTIFICATION":
                return new Programme(type, CERTIFICATION_COST);
            case "DEGREE":
                return new Programme(type, DEGREE_COST);
            case "DIPLOMA":
                return new Programme(type, DIPLOMA_COST);
            default:
                throw new IllegalArgumentException("Invalid programme type selected: " + type);
        }
    }

    public void addProgrammeToCart(Cart cart, Programme programme, int quantity) {
        for (int i = 0; i < quantity; i++) {
            cart.getProgrammes().add(programme);
        }
    }
}
