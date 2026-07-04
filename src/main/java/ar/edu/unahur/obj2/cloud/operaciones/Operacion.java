package ar.edu.unahur.obj2.cloud.operaciones;

import ar.edu.unahur.obj2.cloud.excepciones.LimiteOverprovisioningException;

public interface Operacion {
    void ejecutar() throws LimiteOverprovisioningException;
    void deshacer();
}
