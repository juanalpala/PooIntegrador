
package Parqueadero;

public class ParqueaderoException extends Exception{
   
    private String matricula;

    public ParqueaderoException(String matricula) {
    }
    public ParqueaderoException(String matricula, String mensaje){
        super(mensaje);
        this.matricula=matricula;
        
    }
    public String getMatricula(){
        return matricula;
    }
    
}


