package ar.edu.unahur.obj2.cloud.notificaciones;

import ar.edu.unahur.obj2.cloud.modelos.Cluster;

public class AlarmaSaturacionCritica implements ObservadorCluster {
    private Boolean alarmaActivada = false;

    @Override
    public void reaccionar(Cluster cluster, String tipoOperacion, Integer cantidad) {
        if (cluster.getvCPUsDisponibles() < 0) {
            this.alarmaActivada = true;
        } else {
            this.alarmaActivada = false;
        }
    }

    public Boolean isAlarmaActivada() {
        return this.alarmaActivada;
    }
}
