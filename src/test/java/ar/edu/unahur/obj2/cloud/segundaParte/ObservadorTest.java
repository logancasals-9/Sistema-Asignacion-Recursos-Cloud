package ar.edu.unahur.obj2.cloud.segundaParte;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import ar.edu.unahur.obj2.cloud.modelos.Cluster;
import ar.edu.unahur.obj2.cloud.operaciones.*;
import ar.edu.unahur.obj2.cloud.notificaciones.*;
import ar.edu.unahur.obj2.cloud.excepciones.LimiteOverprovisioningException;

public class ObservadorTest {

    @Test
    void sistemasReaccionanAlCambioDeCapacidad() throws LimiteOverprovisioningException {
        Cluster cluster = new Cluster(101, 100);
        
        RegistroAuditoria auditoria = new RegistroAuditoria();
        NotificacionSRE sre = new NotificacionSRE();
        AlarmaSaturacionCritica alarma = new AlarmaSaturacionCritica();

        cluster.registrarObservador(auditoria);
        cluster.registrarObservador(sre);
        cluster.registrarObservador(alarma);

        Operacion asignacion = new Asignacion(cluster, 150);
        asignacion.ejecutar();

        assertEquals(Integer.valueOf(1), auditoria.getLogs().size());
        assertEquals("Se han asignado 150 vCPUs en el clúster 101", sre.getMensajes().get(0));
        assertTrue(alarma.isAlarmaActivada());

        Operacion liberacion = new Liberacion(cluster, 100);
        liberacion.ejecutar();

        assertEquals(Integer.valueOf(2), auditoria.getLogs().size());
        assertEquals("Se han liberado 100 vCPUs en el clúster 101", sre.getMensajes().get(1));
        assertFalse(alarma.isAlarmaActivada());
    }
}