package ar.edu.unahur.obj2.cloud.notificaciones;

import ar.edu.unahur.obj2.cloud.modelos.Cluster;

public interface ObservadorCluster {
    void reaccionar(Cluster cluster, String tipoOperacion, Integer cantidad);
}
