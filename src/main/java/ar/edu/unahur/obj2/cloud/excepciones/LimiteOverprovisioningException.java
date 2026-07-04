package ar.edu.unahur.obj2.cloud.excepciones;

public class LimiteOverprovisioningException extends Exception{
    public LimiteOverprovisioningException(String mensaje) {
        super(mensaje);
    }
}
