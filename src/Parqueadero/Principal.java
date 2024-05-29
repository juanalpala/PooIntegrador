package Parqueadero;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
public class Principal {

    public static void main(String[] args) {
       
        boolean a = true;
        while (a) {
            String menu = "      BIENVENIDO A PARKING LOT   \n" +
                    "-----------------------------------------------------\n" +
                    "  Selecciona una opción \n" +
                    "1. Ingresar \n" +
                    "2. Conoce Términos y Condiciones \n" +
                    "3. Salir ";
            // Mostrar el menú en una ventana emergente para seleccionar la opción del usuario
            String opMenu = JOptionPane.showInputDialog(menu);
            if (opMenu == null) {
                break;
            }
            int opcion;
            try {
                opcion = Integer.parseInt(opMenu);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Opción inválida, por favor ingresa un número.");
                continue;
            }

            switch (opcion) {
                case 1:
                    ingresarAdministrador();
                    break;
                case 2:
                    // Opción para agregar términos y condiciones atravez de un metodo
                    Parqueadero.mostrarMensaje();
                    break;
                case 3:
                    a = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción incorrecta elija entre (1-3)");
                    break;
            }
        }
    }
    // Llamar el menú de la línea 34 [ingresarAdministrador()]
    public static void ingresarAdministrador() {
        String fechaActualizada;
        LocalDateTime fecha = LocalDateTime.now();
        DateTimeFormatter formatofecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        fechaActualizada=fecha.format(formatofecha);
        
        String horaActualizada="";
        LocalDateTime horaAhora = LocalDateTime.now();
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        horaActualizada = horaAhora.format(formatoHora);
       
         
        Parqueadero parqueadero = new Parqueadero ("                                                                Parking Lot ", 50);
                // Declaración de las variables fuera del switch
        int identificacionCliente = 0;
        String nombreCliente = "";
        String telefonoCliente = "";
        String emailCliente = "";
        
        int identificacionAdministrador = 0;
        String nombreAdministrador = "";
        String telefonoAdministrador = "";
        String emailAdministrador = "";
        String contraseñaAdministrador = "";
        String hora1="";
        String fecha1="";
        
        String matriculaSalida = "";
        double valorTarifa = 3000;
        String ciudad=("Popayan");

        boolean b = true;
        while (b) {
            // Creando menú para el registro de datos emergentes 
            String menu = ("                 PARKING LOT  \n" +
                    "---------------------------------------------\n" +
                    "1. Registrar Administrador \n" +
                    "2. Registrar Cliente \n" +
                    "3. Registrar Vehículo\n" +
                    "4. Retirar Vehículo\n" +
                    "5. Mostrar Parqueadero\n" +
                    "6. Imprimir recibo \n" +
                    "7. Salir ");

            String opMenu = JOptionPane.showInputDialog(menu);
            if (opMenu == null) {
                break;
            }
            int opcion;
            try {
                opcion = Integer.parseInt(opMenu);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Opción inválida, por favor ingresa un número.");
                continue;
            }

            switch (opcion) {
                case 1:
                    JOptionPane.showMessageDialog(null, "¡Bienvenido! ¡Eres Administrador!");
                    try {
                        identificacionAdministrador = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su identificación"));
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Identificación no válida, por favor ingrese un número.");
                        continue;
                    }
                    nombreAdministrador = JOptionPane.showInputDialog("Ingrese su nombre");
                    telefonoAdministrador =JOptionPane.showInputDialog("Ingrese su número telefónico");
                    emailAdministrador = JOptionPane.showInputDialog("Ingrese su email");
                    contraseñaAdministrador = JOptionPane.showInputDialog("Ingrese su contraseña");
                  
                    break;

                case 2:
                    // Crear esta opción para que el cliente ingrese sus datos
                    nombreCliente = JOptionPane.showInputDialog("Ingrese su nombre");
                    try {
                        identificacionCliente = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su identificación"));
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Identificación no válida, por favor ingrese un número.");
                        continue;
                    }
                    telefonoCliente =JOptionPane.showInputDialog("Ingrese su número de teléfono ");
                    emailCliente = JOptionPane.showInputDialog("Ingrese su email");
                    break;
                    

                case 3:
                    // Crear opciones para el ingreso de vehículos
                   //oh registro de vehiculos 
                    //LLAMAR METODO DE HORA PARA MIRAR A QUE TIEMPO INGRESO SU VEHICULO 
           
                    JOptionPane.showMessageDialog(null, "¡Registra el vehículo!");
                    String matriculaEntrada = JOptionPane.showInputDialog("Ingrese la matrícula del vehículo");
                    if (matriculaEntrada == null) 
                        break;
                      // Aqui debe colocar el numero de plaza que desea estacionar su vehiculo
                    String plazaEntradaStr = JOptionPane.showInputDialog("Ingrese el número de plaza entre (1-50)");
                    if (plazaEntradaStr == null) 
                        break;
                    try {
                        int plazaEntrada = Integer.parseInt(plazaEntradaStr);
                        parqueadero.entrada(matriculaEntrada, plazaEntrada);
                        JOptionPane.showMessageDialog(null, "Vehículo estacionado en la plaza " + plazaEntrada);
                    } catch (ParqueaderoException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage() + " - Matrícula: " + e.getMatricula());
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Número de plaza no válido");
                    }
                         fecha1=JOptionPane.showInputDialog("Ingrese la fecha de ingreso (dd/MM/yyyy)");
                    hora1=JOptionPane.showInputDialog("Ingrese la hora de ingreso  (HH:mmss) ");
                    
                    break;
                   
                case 4:
                    matriculaSalida = JOptionPane.showInputDialog("Ingrese la matrícula del Vehiculo");
                    if (matriculaSalida == null) break;
                    try {
                        int plazaSalida = parqueadero.salida(matriculaSalida);
                        JOptionPane.showMessageDialog(null, "Vehículo salió de la plaza " + plazaSalida);
                    } catch (ParqueaderoException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage() + " - Matrícula: " + e.getMatricula());
                    }                   
                    break;
                case 5:
                    //metodo donde aparece las matrices de las plazas del parqueadero
                    JOptionPane.showMessageDialog(null, parqueadero.toString());
                    break;
                case 6:
                    // Realizar la opción de recibo
                    JOptionPane.showMessageDialog(null,
                            "                                                            RECIBO PARQUEADERO  \n" +
                                    "-----------------------------------------------------------------------------------------------------------------------------------------------\n" +
                                    "\nNombre del parqueadero:    PARKING LOT" + "               Ciudad  : " + ciudad+ 
                                    " \nNombre del Administrador : "+nombreAdministrador+ "  "+"                    Teléfono del Administrador: " + telefonoAdministrador + 
                                    "\n---------------------------------------------------------------------------------------------------------------------------------------------------\n"+
                                    "\nNombre del Cliente: "+nombreCliente+"                                  Teléfono del Cliente: " + telefonoCliente + 
                                    "\nPlaca del Vehículo: "+matriculaSalida+
                                    "\nFecha de ingreso : "+fecha1+        
                                    "\nHora de ingreso : "+hora1+ "                                  Fecha  de salida : "+fechaActualizada+ "                          Hora de salida : "+horaActualizada+ 
                                    "\nTarifa por hora:  "+valorTarifa+  "          Total a pagar:  " +
                                    "\n--------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                                    "\nNOTA:  El parqueadero y su personal ofrecen al público un servicio de vigilancia y su responsabilidad \n" +
                                    " civil quedará limitada hasta el valor del servicio pagado por el cliente, ya sea por días o mensualidad.\n\n" +
                                    "Si el usuario quiere amparar los riesgos de pérdida, deberá contratar un seguro por su propia cuenta\n" +
                                    "por su vehículo y demás bienes dejados en custodia, porque el parqueadero no responde en caso de\n" +
                                    "incendio, terremoto, asonada, daños mecánicos y/o fuerza mayor.");
                    break;
                case 7:
                    b = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción incorrecta elija un opcion (1-7)");
                    break;
            }
        }
    }
}