package ar.edu.unahur.obj2.cloud.notificaciones;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.cloud.modelos.Cluster;

public class RegistroAuditoria implements ObservadorCluster{
    private final List<String> logs = new ArrayList<>();

    @Override
    public void reaccionar(Cluster cluster, String tipoOperacion, Integer cantidad) {
        String log = "Auditoría: Se han " + tipoOperacion + " " + cantidad + 
                     " vCPUs en el clúster " + cluster.getId() + 
                     ". Capacidad resultante: " + cluster.getvCPUsDisponibles();
        this.logs.add(log);
    }

    public List<String> getLogs() {
        return this.logs;
    }
}
