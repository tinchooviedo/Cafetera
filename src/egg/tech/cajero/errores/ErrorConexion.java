package egg.tech.cajero.errores;

public class ErrorConexion extends Exception{
    public ErrorConexion(String mensaje){
        super("Se produjo un error al conectarse a la red de bancos. " + mensaje); 
    }
}
