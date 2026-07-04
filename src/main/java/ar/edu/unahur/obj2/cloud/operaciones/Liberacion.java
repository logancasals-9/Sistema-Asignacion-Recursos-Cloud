package ar.edu.unahur.obj2.cloud.operaciones;

import ar.edu.unahur.obj2.cloud.excepciones.LimiteOverprovisioningException;
import ar.edu.unahur.obj2.cloud.excepciones.ValorInvalidoException;
import ar.edu.unahur.obj2.cloud.modelos.Cluster;

public class Liberacion implements Operacion{
    private Cluster cluster;
    private Integer cantidad;

    public Liberacion(Cluster cluster, Integer cantidad) {
        if (cantidad <=0) {
            throw new ValorInvalidoException("La cantidad de vCPUs a liberar debe ser mayor a 0");
        }
        this.cluster = cluster;
        this.cantidad = cantidad;
    }

    @Override
    public void ejecutar() throws LimiteOverprovisioningException {
        this.cluster.liberar(this.cantidad);
    }

    @Override
    public void deshacer() {
        try {
            this.cluster.asignar(this.cantidad);
        } catch (LimiteOverprovisioningException e) {
            throw new RuntimeException("Error al revertir la liberación", e );
        }
    }


}
