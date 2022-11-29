package Actividad;

import java.io.*;
import java.util.*;

public class Main {

    static Scanner leer = new Scanner (System.in);

    public static void main(String[] args) {

        Scanner leer = new Scanner(System.in);
        boolean salir = false;
        int opcion;

        while (!salir) {  //menú principal

            System.out.println(" ");
            System.out.println("1. Máximo y mínimo . ");
            System.out.println("2. Renombrar carpetas.");
            System.out.println("3. Ordenar personas .");    //apartados del menú
            System.out.println("4. Renombrar archivos.");
            System.out.println("5. Estadísticas de un archivo.");
            System.out.println("6. Salir.");

            try {
                System.out.print("Introduce un numero: ");
                opcion = leer.nextInt();

                switch (opcion) {
                    case 1:
                        maxymin();
                        break;                                    //dependiendo del numero introducido te llevara
                    case 2:                                        //a un sitio o a otro
                        renombrarCarpetas();
                        break;
                    case 3:
                        ordenar();
                        break;
                    case 4:
                        renombrarArchivos();
                        break;
                    case 5:
                        statsArchivo();
                        break;
                    case 6:
                        salir = true;
                        break;

                    default:
                        System.out.println("Las opciones estan entre 1 y 5"); //si no se introduce un número
                        // correcto en el menú se notifica
                }
            } catch (InputMismatchException e) {
                System.err.println("Debes introducir un numero");
                leer.next();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Programa finalizado. [29 - 04 - 2022]"); //final
    }

    public static void maxymin() throws FileNotFoundException {

        String linea = "";
        //lector del documento
        FileReader numeros = new FileReader("src\\DAW_PROG_Act12_Documentos\\numeros.txt");
        //lector de los bloques del documento
        BufferedReader lectura = new BufferedReader(numeros);
        //arrayList para guardar cada numero
        List numero = new ArrayList<Integer>();

        try {

            while(linea != null){                   //se sigue leyendo mientas que alguna no linea este vacia
                linea = lectura.readLine();

                if (linea != null){
                    numero.add(Integer.parseInt(linea));        //se pasan las lineas a enteros
                }
            }

            //el Collections muestra el mayor y el menor
            System.out.println("Mayor: "+Collections.max(numero));
            System.out.println("Menor: "+ Collections.min(numero));

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void renombrarCarpetas(){

        //se leen los archivos
        File Multimedia = new File("src\\DAW_PROG_Act12_Documentos\\Multimedia");
        File Libros = new File("src\\DAW_PROG_Act12_Documentos\\Multimedia\\Libros");
        File Fotografias = new File("src\\DAW_PROG_Act12_Documentos\\Multimedia\\Fotografias");

        //se crean los nombres nuevos
        File MEDIA = new File("src\\DAW_PROG_Act12_Documentos\\MEDIA");
        File LECTURA = new File("src\\DAW_PROG_Act12_Documentos\\Multimedia\\LECTURA");
        File FOTOS = new File("src\\DAW_PROG_Act12_Documentos\\Multimedia\\FOTOS");

        //con el renameTo() se intercambian los nombres
        if (Fotografias.renameTo(FOTOS) && Libros.renameTo(LECTURA) && Multimedia.renameTo(MEDIA)) {
            //se muestra el mensaje si se cumplen las 3 condiciones del if
            System.out.println("Los archivos han sido renombrados");
        } else {
            System.err.println("Error (Puede que el cambio ya se haya hecho)");
        }
    }

    public static void ordenar() throws FileNotFoundException {

        System.out.println(" ");
        System.out.println("Escriba el nombre de un archivo(sugerencia-> listado_personas.txt):");
        String archivo = leer.next();

        //mientras no sea ese se repetira en bucle lo de dentro del while
        while (!archivo.equals("listado_personas.txt")) {
            System.out.println("Escribe el nombre del archivo(sugerencia-> listado_personas.txt):");
            archivo = leer.nextLine();
            leer.next();
        }

        try{
            //en un TreeSet se guardan los nombres para usar el comparador con ellos
            TreeSet<Persona> guardado = new TreeSet(new Comparador());
            //se lee el archivo
            FileReader fr = new FileReader("src\\DAW_PROG_Act12_Documentos\\" +archivo);
            BufferedReader bf = new BufferedReader (fr);
            String [] nombreentero;
            String ilerna = " ";
            int cont = 0;

            //mientras no haya renglones vacios
            while (ilerna != null) {
                ilerna = bf.readLine();
                cont++;
                if(ilerna != null) {
                    nombreentero = ilerna.split(" ");

                    Persona p = new Persona (cont ,nombreentero[0], nombreentero[1]);
                    guardado.add(p);
                }

                //se crea un nuevo archivo en blanco en el que se va a escribir
                FileWriter nuevo = new FileWriter("src\\DAW_PROG_Act12_Documentos\\listado_personas_ordenado.txt");

                for (Persona persona: guardado) {
                    nuevo.write(persona.nombre + " " + persona.apellidos + " ");
                }

                nuevo.close();
            }
            System.out.println("Archivo nuevo creado");

        }catch(IOException w) {
            System.err.println("Error");
        }
    }

    public static void renombrarArchivos() {

        deLECTURA();
        deFOTOS();

        System.out.println("Extensiones eliminadas correctamente");
    }

    private static void deFOTOS() {

        //se localiza la carpeta de los archivos
        File ubi = new File("src\\DAW_PROG_Act12_Documentos\\MEDIA\\FOTOS");

        //se guarda cada archivo de la carpeta en una posicion
        File[] arraydeFiles = ubi.listFiles();
        for (int i = 0; i < arraydeFiles.length; i++) {

            //si en cada posicion hay archivo
            if (arraydeFiles[i].isFile()) {
                //se consigue su nombre
                String[] nuevo = new String[2];
                nuevo = arraydeFiles[i].getName().split("\\.");
                File fileantiguo = new File("src\\DAW_PROG_Act12_Documentos\\MEDIA\\FOTOS\\" + arraydeFiles[i].getName());
                File filenuevo = new File("src\\DAW_PROG_Act12_Documentos\\MEDIA\\FOTOS\\" + nuevo[0]);


                if (fileantiguo.renameTo(filenuevo)) {

                } else {
                    System.err.println("Error (Puede que el cambio ya se haya hecho)");
                }
            }
        }
    }

    private static void deLECTURA() {

        File ubi = new File("src\\DAW_PROG_Act12_Documentos\\MEDIA\\LECTURA");

        File[] arraydeFiles = ubi.listFiles();
        for (int i = 0; i < arraydeFiles.length; i++) {

            if (arraydeFiles[i].isFile()) {
                String[] nuevo = new String[2];
                nuevo = arraydeFiles[i].getName().split("\\.");
                File fileantiguo = new File("src\\DAW_PROG_Act12_Documentos\\MEDIA\\LECTURA\\" + arraydeFiles[i].getName());
                File filenuevo = new File("src\\DAW_PROG_Act12_Documentos\\MEDIA\\LECTURA\\" + nuevo[0]);


                if (fileantiguo.renameTo(filenuevo)) {

                } else {
                    System.err.println("Error (Puede que el cambio ya se haya hecho)");
                }
            }
        }
    }

        private static void statsArchivo () throws IOException {

            File archivo = new File("src\\DAW_PROG_Act12_Documentos\\MEDIA\\LECTURA");
            //se guardan los archivos de la dirección anterior en un array
            File[] archivoarray = archivo.listFiles();

            int n_palabras;

            //se separan los archivos del array
            for (File file: archivoarray) {
                int lineas = 0;
                String palabra = "";
                //se va a leer cada archivo
                FileReader lector = new FileReader(file);
                BufferedReader bf = new BufferedReader(lector);
                int cuentapalabras = 0;
                int caracteres = 0;
                //Map<String, Integer> ocurrences = new HashMap<String,Integer>();

                while (palabra != null) {
                    lineas++;
                    palabra = bf.readLine();
                    /*String[] words = palabra.nextLine().split(",");

                    for(String word: words) {
                        if(ocurrences.containsKey(word)) {
                            ocurrences.put(word,ocurrences.get(word)+1);
                        }else {
                            ocurrences.put(word,1);
                        }
                    }
                    for (Map.Entry<String, Integer> entry : ocurrences.entrySet()) {
                        System.out.println(entry.getKey() + " - " + entry.getValue());
                    }*/

                    if (palabra != null) {
                        //usamos StringTokenizer que nos ayuda a dividir un string en substrings
                        StringTokenizer stringTokenizer1 = new StringTokenizer(palabra);

                        //se cuentan esos tokens
                        n_palabras = stringTokenizer1.countTokens();
                        cuentapalabras = cuentapalabras + n_palabras;

                        //se lee cada caracter con .length()
                        caracteres = palabra.length() + caracteres;
                    }
                }
                System.out.println(" ");
                System.out.println("Estadistica de: " + file.getName());
                System.out.println("Lineas: " + lineas);
                System.out.println("Palabras: " + cuentapalabras);
                System.out.println("Caracteres: " + caracteres);
                System.out.println("10 palabras más comunes: ");

            }
        }
}