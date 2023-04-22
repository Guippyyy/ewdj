package com.project.ewdj.entity;

public class locatie {

    private int plaatsCode1;
    private int plaatsCode2;
    private String plaatsnaam;

    public locatie(int plaatsCode1, int plaatsCode2, String plaatsnaam) {
        this.plaatsCode1 = plaatsCode1;
        this.plaatsCode2 = plaatsCode2;
        this.plaatsnaam = plaatsnaam;
    }

    public int getPlaatsCode1() {
        return plaatsCode1;
    }

    public void setPlaatsCode1(int plaatsCode1) {
        this.plaatsCode1 = plaatsCode1;
    }

    public int getPlaatsCode2() {
        return plaatsCode2;
    }

    public void setPlaatsCode2(int plaatsCode2) {
        this.plaatsCode2 = plaatsCode2;
    }

    public String getPlaatsnaam() {
        return plaatsnaam;
    }

    public void setPlaatsnaam(String plaatsnaam) {
        this.plaatsnaam = plaatsnaam;
    }

}
