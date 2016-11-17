
/*
Clase modelo
 */
public class Tarea2 {
    private String mascota;
    private String nombre;
    private int punto_vida;

    // constructor
    // asiganmos la vida dependiendo de la mascota
    public Tarea2(String mascota,String nombre){
        if (mascota== "perro"){
            setPunto_vida(50);
        }else {
            setPunto_vida(30);
        }
        setMascota(mascota);
        setNombre(nombre);
    }

    /**
     * metodo administraremos todas las acciones de las mascotas
     * @param animal recibimos el tipo de mascola
     * @param accion recibimos la accion a realizar
     */
    public void acciones(String animal,String accion){
        System.out.println();
        int pts=0;
        if(animal.equals("perro")){
            //perro
            switch (accion){
                case "ladrar":
                    System.out.println(animal+" Ladrando....");
                    pts = getPunto_vida()-2;
                    break;
                case "comer":
                    System.out.println(animal+" Comiendo ....");
                    pts = getPunto_vida() +10;
                    break;
                case "correr":
                    System.out.println(animal+" Corriendo ....");
                    pts = getPunto_vida() -15;
                    break;
                default:
                    System.out.println("Accion no Disponible");
                    break;
            }

        }else{
            //gato
            switch (accion){
                case "dormir":
                    System.out.println(animal+" Durmiendo....");
                    pts = getPunto_vida()+2;
                    break;
                case "comer":
                    System.out.println(animal+" Comiendo ....");
                    pts = getPunto_vida() +5;
                    break;
                case "caminar":
                    System.out.println(animal+" Caminando ....");
                    pts = getPunto_vida() -5;
                    break;
                default:
                    System.out.println("Accion no Disponible");
                    break;
            }
        }
        setPunto_vida(pts);
    }

    public String getMascota() {
        return mascota;
    }

    public void setMascota(String mascota) {
        this.mascota = mascota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPunto_vida() {
        return punto_vida;
    }

    public void setPunto_vida(int punto_vida) {
        this.punto_vida = punto_vida;
    }
}
