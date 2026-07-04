package ar.edu.unahur.obj2.cloud.primeraParte;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import ar.edu.unahur.obj2.cloud.modelos.Cluster;
import ar.edu.unahur.obj2.cloud.operaciones.*;
import ar.edu.unahur.obj2.cloud.excepciones.LimiteOverprovisioningException;

public class PlanificadorTest {

    @Test
    void unPlanDeDespliegueSeEjecutaCorrectamenteYVaciaLasPendientes() throws LimiteOverprovisioningException {
        Cluster cluster = new Cluster(999, 1000);
        Planificador planificador = new Planificador();
        PlanDeDespliegue plan = new PlanDeDespliegue();

        plan.agregarOperacion(new Asignacion(cluster, 300));
        plan.agregarOperacion(new Liberacion(cluster, 100));
        plan.agregarOperacion(new Asignacion(cluster, 200));

        planificador.ejecutarInmediato(plan);

        assertEquals(Integer.valueOf(600), cluster.getvCPUsDisponibles());
        assertEquals(Integer.valueOf(0), plan.getCantidadPendientes());
    }

    @Test
    void siFallaElPlanVuelveAlEstadoInicial() {
        Cluster cluster = new Cluster(777, 100);
        Planificador planificador = new Planificador();
        PlanDeDespliegue plan = new PlanDeDespliegue();

        plan.agregarOperacion(new Asignacion(cluster, 150)); 
        plan.agregarOperacion(new Liberacion(cluster, 50)); 
        plan.agregarOperacion(new Asignacion(cluster, 300)); 

        assertThrows(LimiteOverprovisioningException.class, () -> {
            planificador.ejecutarInmediato(plan);
        });

        assertEquals(Integer.valueOf(100), cluster.getvCPUsDisponibles());
        
        assertEquals(Integer.valueOf(3), plan.getCantidadPendientes());
    }
}