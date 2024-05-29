package Parqueadero;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

public class Parqueadero {
    
    private ArrayList<String> matriculas;
    private String nombre;
    private HashMap<String, LocalDateTime> tiemposEntrada;
    private String horaIngreso;

    public Parqueadero(String nombre, int numPlazas) {
        this.nombre = nombre;
        this.matriculas = new ArrayList<>(numPlazas);
        for (int i = 0; i < numPlazas; i++) {
            matriculas.add(null);
        }
        this.tiemposEntrada = new HashMap<>();
    }

    public String getNombre() {
        return nombre;
    }

    public int getPlazasTotales() {
        return matriculas.size();
    }

    public int getPlazasOcupadas() {
        int count = 0;
        for (String matricula : matriculas) {
            if (matricula != null) {
                count++;
            }
        }
        return count;
    }

    public int getPlazasDesocupadas() {
        return getPlazasTotales() - getPlazasOcupadas();
    }

    public Duration getTiempoEstacionado(String matricula) throws ParqueaderoException {
        if (!tiemposEntrada.containsKey(matricula)) {
            throw new ParqueaderoException("La matrícula no existente", matricula);
        }
        LocalDateTime tiempoEntrada = tiemposEntrada.get(matricula);
        return Duration.between(tiempoEntrada, LocalDateTime.now());
    }

    public void entrada(String matricula, int plaza) throws ParqueaderoException {
        if (matricula == null || matricula.length() < 5) {
            throw new ParqueaderoException(" La Matrícula  es Incorrecta", matricula);     
        }
        if (plaza < 0 || plaza >= matriculas.size()) {
            throw new ParqueaderoException("Plaza fuera de rango", matricula);
        }
        if (matriculas.get(plaza) != null) {
            throw new ParqueaderoException(" La Plaza esta ocupada", matricula);
        }
        if (matriculas.contains(matricula)) {
            throw new ParqueaderoException(" La Matrícula repetida", matricula);
        }
        matriculas.set(plaza, matricula);
        tiemposEntrada.put(matricula, LocalDateTime.now());
    }

    public int salida(String matricula) throws ParqueaderoException {
        if (!matriculas.contains(matricula)) {
            throw new ParqueaderoException("La Matrícula no existente", matricula);
        }
        int plaza = matriculas.indexOf(matricula);
        matriculas.set(plaza, null);
        tiemposEntrada.remove(matricula);
        return plaza;
        
    }
    @Override
    public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(nombre).append("\n-------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
    
    int columns = 5;
    int rows = 10;
    int totalPlazas = matriculas.size();
    
    for (int row = 0; row < rows; row++) {
        for (int col = 0; col < columns; col++) {
            int index = row * columns + col;
            if (index < totalPlazas) {
                sb.append("Plaza ").append(index).append("  : ");
                if (matriculas.get(index) == null) {
                    sb.append("(vacía)");
                } else {
                    sb.append(matriculas.get(index));
                    try {
                        Duration tiempo = getTiempoEstacionado(matriculas.get(index));
                        long hours = tiempo.toHours();
                        long minutes = tiempo.toMinutes() % 60;
                        long segundos = tiempo.toSeconds() % 60;
                        sb.append(" (").append(hours).append(" horas y ").append(minutes).append(" minutos ").append(segundos).append(" segundos)");
                    } catch (ParqueaderoException e) {
                        sb.append(" (error calculando tiempo)");
                    }
                }
                sb.append("    |    ");  // Separador entre columnas
                
            }
        }
        sb.append("\n");  // Nueva línea después de cada fila
    }
    return sb.toString();
    }
    public static void mostrarMensaje (){
           JOptionPane.showMessageDialog(null, "\n                                              TERMINOS Y CONDICIONES DEL PARQUEADERO  \n\n " +
                            "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                            "RESPONSABILIDAD DEL USUARIO: El usuario es responsable de cualquier daño o pérdida de pertenencias dejadas en el vehículo \n" +
                            "\n" +
                            "HORARIO DE OPERACIÓN :  El parqueadero está disponible para uso durante los horarios especificados.\n" +
                            "\n" +
                            "TARIFAS Y PAGOS :  Las tarifas se aplican según el tiempo de estacionamiento y deben pagarse al momento de salida.\n" +
                            "\n" +
                            "SEGURIDAD :  El parqueadero no se responsabiliza por robos, daños o pérdidas de vehículos o pertenencias.\n" +
                            "\n" +
                            "DAÑOS A PROPIEDAD:   El usuario será responsable de cualquier daño causado a la propiedad del parqueadero.\n" +
                            "\n" +
                            "RECLAMACIONES : Cualquier reclamación debe hacerse antes de salir del parqueadero y proporcionar comprobante de pago y ticket de estacionamiento.\n" +
                            "\n" +
                            "REGLAMENTO : El usuario debe cumplir con todas las reglas y señales del parqueadero.\n" +
                            "\n" +
                            "MANTENIMIENTO DE BOLETA / TICKET : El ticket de estacionamiento debe conservarse y presentarse a la salida. Su pérdida puede incurrir en cargos adicionales.\n" +
                            "\n" +
                            "VEHÍCULOS NO RECLAMADOS : Vehículos abandonados o no reclamados en un tiempo determinado pueden ser remolcados a expensas del propietario.\n" +
                            "\n" +
                            "PROHIBICIONES : No se permite dejar vehículos con fugas de combustible, no asegurados, o en condiciones que presenten riesgos.\n" +
                            "\n" +
                            "Estos son términos y condiciones generales, y pueden variar según el parqueadero específico.");
    }     
    
   

    
    }
    
    
    
    
    

