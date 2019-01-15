/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.entity;

/**
 *  clase para el resultado de una operacion
 * @author Javier Gonzalez Rives
 */
public class Total {
    
    private int maximo;
    private double media;

    public Total(float sumatoria, double media) {
        this.maximo = (int)sumatoria;
        this.media = media;
    }

    public Total() {
    }
    
    
    
    public int getSumatoria() {
        return maximo;
    }

    public void setSumatoria(int sumatoria) {
        this.maximo = sumatoria;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }
    
    
    
}
