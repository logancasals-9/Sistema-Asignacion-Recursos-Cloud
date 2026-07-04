package ar.edu.unahur.obj2.cloud.modelos;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.cloud.excepciones.LimiteOverprovisioningException;
import ar.edu.unahur.obj2.cloud.notificaciones.ObservadorCluster;

public class Cluster {
   private Integer id;
   private Integer vCPUsDisponibles;
   private List<ObservadorCluster> observadores = new ArrayList<>();
   
   public Cluster(Integer id, Integer vCPUsDisponibles) {
        this.id = id;
        this.vCPUsDisponibles = vCPUsDisponibles;
    }

   public Integer getId() {
        return id;
    }

   public Integer getvCPUsDisponibles() {
        return vCPUsDisponibles;
    }

   public void registrarObservador(ObservadorCluster observador) {
        this.observadores.add(observador);
    }

   public void notificarObservadores(String tipoOperacion, Integer cantidad) {
        this.observadores.forEach(obs -> obs.reaccionar(this, tipoOperacion, cantidad));
    }

   public void liberar(Integer vCPUs) {
        this.vCPUsDisponibles += vCPUs;
        this.notificarObservadores("liberado", vCPUs);
    }

   public void asignar(Integer vCPUs) throws LimiteOverprovisioningException {
        if (this.vCPUsDisponibles - vCPUs < -200) {
            throw new LimiteOverprovisioningException("No se puede superar el límite de -200 vCPUs.");
        }
        this.vCPUsDisponibles -= vCPUs;
        this.notificarObservadores("asignado", vCPUs);
    }
}
