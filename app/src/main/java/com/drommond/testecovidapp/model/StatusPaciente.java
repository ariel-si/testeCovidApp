package com.drommond.testecovidapp.model;

public class StatusPaciente {
    private Double gripe;
    private Double resfriado;
    private Double covid19;

    public StatusPaciente() {
    }

    public Double getGripe() {
        return gripe;
    }

    public void setGripe(Double gripe) {
        this.gripe = gripe;
    }

    public Double getResfriado() {
        return resfriado;
    }

    public void setResfriado(Double resfriado) {
        this.resfriado = resfriado;
    }

    public Double getCovid19() {
        return covid19;
    }

    public void setCovid19(Double covid19) {
        this.covid19 = covid19;
    }

    @Override
    public String toString() {
        return "StatusPaciente{" +
                "gripe=" + gripe +
                ", resfriado=" + resfriado +
                ", covid19=" + covid19 +
                '}';
    }
}
