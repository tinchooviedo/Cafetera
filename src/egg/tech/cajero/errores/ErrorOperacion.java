package egg.tech.cajero.errores;

public class ErrorOperacion extends Exception{
    public ErrorOperacion(String mensaje){
        super("Se produjo un error al conectarse a la red de bancos. " + mensaje); 
    }
    
}
