package ar.edu.unahur.obj2.cloud.notificaciones;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.cloud.modelos.Cluster;

public class NotificacionSRE implements ObservadorCluster{
    private final List<String> mensajes = new ArrayList<>();

    @Override
    public void reaccionar(Cluster cluster, String tipoOperacion, Integer cantidad) {
        String mensaje = "Se han " + tipoOperacion + " " + cantidad + " vCPUs en el clúster " + cluster.getId();
        this.mensajes.add(mensaje);
    }

    public List<String> getMensajes() {
        return this.mensajes;
    }
}
