package ar.edu.unahur.obj2.cloud.operaciones;

import java.util.ArrayList;
import java.util.List;
import ar.edu.unahur.obj2.cloud.excepciones.LimiteOverprovisioningException;

public class PlanDeDespliegue implements Operacion {
    private final List<Operacion> operaciones = new ArrayList<>();

    public void agregarOperacion(Operacion operacion) {
        this.operaciones.add(operacion);
    }

    public void vaciar() {
        this.operaciones.clear();
    }

    public Integer getCantidadPendientes() {
        return this.operaciones.size();
    }

    @Override
    public void ejecutar() throws LimiteOverprovisioningException {
        List<Operacion> yaEjecutadas = new ArrayList<>();
        
        for (Operacion operacion : this.operaciones) {
            try {
                operacion.ejecutar();
                yaEjecutadas.add(operacion);
            } catch (LimiteOverprovisioningException e) {
                this.revertir(yaEjecutadas);
                throw e;
            }
        }
        
        this.vaciar(); 
    }

    @Override
    public void deshacer() {
        this.revertir(this.operaciones);
    }

    private void revertir(List<Operacion> ejecutadas) {
        for (Integer i = ejecutadas.size() - 1; i >= 0; i--) {
            ejecutadas.get(i).deshacer();
        }
    }
}