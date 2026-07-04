package ar.edu.unahur.obj2.cloud.primeraParte;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import ar.edu.unahur.obj2.cloud.modelos.Cluster;
import ar.edu.unahur.obj2.cloud.operaciones.*;
import ar.edu.unahur.obj2.cloud.excepciones.*;

public class ClusterTest {

    @Test
    void unClusterPuedeAsignarYLiberarCapacidadYDeshacerCambios() throws LimiteOverprovisioningException {
        Cluster cluster = new Cluster(1234, 1000);
        Operacion asignacion = new Asignacion(cluster, 200);
        Operacion liberacion = new Liberacion(cluster, 50);

        asignacion.ejecutar();//Espera valor 800 ya que 1000-200 = 800
        assertEquals(800, cluster.getvCPUsDisponibles());

        liberacion.ejecutar();//Espera valor 850 ya que 800 + 50 = 850
        assertEquals(850, cluster.getvCPUsDisponibles());
        
        asignacion.deshacer();//Espera valor 1050 ya que 850 + 200 = 1050
        assertEquals(1050, cluster.getvCPUsDisponibles());
    }

    @Test
    void instanciarOperacionConValorInvalidoLanzaExcepcionUnchecked() {
        Cluster cluster = new Cluster(1234, 1000);
        
        assertThrows(ValorInvalidoException.class, () -> {
            new Asignacion(cluster, 0); 
        });
        
        assertThrows(ValorInvalidoException.class, () -> {
            new Liberacion(cluster, -50); 
        });
    }

    @Test
    void superarLimiteDeOverprovisioningLanzaExcepcionChecked() {
        Cluster cluster = new Cluster(1234, 100);
        Operacion asignacionExtrema = new Asignacion(cluster, 350);

        assertThrows(LimiteOverprovisioningException.class, () -> {
            asignacionExtrema.ejecutar();
        });
    }
}