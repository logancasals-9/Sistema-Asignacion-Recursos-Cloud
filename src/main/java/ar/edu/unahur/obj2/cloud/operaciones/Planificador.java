package ar.edu.unahur.obj2.cloud.operaciones;

import ar.edu.unahur.obj2.cloud.excepciones.LimiteOverprovisioningException;

public class Planificador {
    
    public void ejecutarInmediato(Operacion operacion) throws LimiteOverprovisioningException {
        operacion.ejecutar();
    }
}
