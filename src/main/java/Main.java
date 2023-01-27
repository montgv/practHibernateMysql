import Clases.HospitalEntidad;
import Clases.MedicoEntidad;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("practHibernateMysql");
    static EntityManager entityManager = entityManagerFactory.createEntityManager();
    static EntityTransaction transaction = entityManager.getTransaction();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean salir = false;
        while (!salir) {
            System.out.println("");
            System.out.println("==========MENU==========");
            System.out.println("1. Insertar un registro para la tabla hospital.");
            System.out.println("2. Buscar un registro de la tabla hospital.");
            System.out.println("3. Actualizar un registro de la tabla hospital.");
            System.out.println("4. Borrar un registro de la tabla hospital.");
            System.out.println("5. Insertar un registro para la tabla medico.");
            System.out.println("6. Buscar un registro de la tabla medico.");
            System.out.println("7. Actualizar un registro de la tabla medico.");
            System.out.println("8. Borrar un registro de la tabla medico.");
            System.out.println("0. Salir.");
            System.out.print("Introduce la opcion que deseas realizar: ");
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    insertarHospital();
                    break;
                case 2:
                    buscarHospital();
                    break;
                case 3:
                    listarHospitales();
                    break;
                case 4:
                    modificarHospital();
                    break;
                case 5:
                    borrarHospital();
                    break;
                case 6:
                    insertarMedico();
                    break;
                case 7:
                    buscarMedico();
                    break;
                case 8:
                    listarMedicos();
                    break;
                case 9:
                    modificarMedico();
                    break;
                case 10:
                    borrarMedico();
                    break;
                case 0:
                    salir = true;
                    break;
                default:
                    System.out.println("No has introducido una opcion correcta.");
            }
        }
        entityManager.close();
        entityManagerFactory.close();
    }

    private static void insertarHospital() {
        String nombreHospital, localidad, telefono;
        int codigo ,numero_camas;

        System.out.println("Introduce el codigo del hospital: ");
        codigo = sc.nextInt();
        sc.nextLine();
        System.out.println("Introduce el nombre del hospital: ");
        nombreHospital = sc.nextLine();
        System.out.println("Introduce la localidad del hospital: ");
        localidad = sc.nextLine();
        System.out.println("Introduce el telefono del hospital: ");
        telefono = sc.nextLine();
        System.out.println("Introduce el numero de camas totales del hospital: ");
        numero_camas = sc.nextInt();
        sc.nextLine();

        HospitalEntidad hospital = new HospitalEntidad(codigo, nombreHospital, localidad, telefono, numero_camas);

        transaction.begin();

        entityManager.merge(hospital);

        transaction.commit();

        System.out.println("Hospital insertado.");
    }

    private static void buscarHospital() {
        HospitalEntidad hospital;
        System.out.println("Introduce el codigo del hospital que deseas buscar: ");
        int codigo = sc.nextInt();
        sc.nextLine();

        transaction.begin();

        hospital = entityManager.find(HospitalEntidad.class, codigo);

        if (hospital != null) {
            System.out.println(hospital);
        } else {
            System.out.println("No existe ese hospital.");
        }

        transaction.commit();
    }

    private static void listarHospitales() {
        transaction.begin();

        List<HospitalEntidad> listaHospitales;
        Query query = entityManager.createQuery("SELECT codigo FROM HospitalEntidad ");
        listaHospitales = query.getResultList();
        for (HospitalEntidad h : listaHospitales) {
            System.out.println(h.toString());
        }

        transaction.commit();
    }

    private static void modificarHospital() {
        String nombreHospital, localidad, telefono;
        int codigo ,numero_camas;

        System.out.println("Introduce el codigo del hospital que deseas modificar: ");
        codigo = sc.nextInt();
        sc.nextLine();

        transaction.begin();

        HospitalEntidad hospitalEncontrar = entityManager.find(HospitalEntidad.class, codigo);

        if (hospitalEncontrar != null) {
            System.out.println("Introduce el nombre del hospital: ");
            nombreHospital = sc.nextLine();
            System.out.println("Introduce la localidad del hospital: ");
            localidad = sc.nextLine();
            System.out.println("Introduce el telefono del hospital: ");
            telefono = sc.nextLine();
            System.out.println("Introduce el numero de camas totales del hospital: ");
            numero_camas = sc.nextInt();
            sc.nextLine();

            HospitalEntidad hospital = new HospitalEntidad(codigo, nombreHospital, localidad, telefono, numero_camas);

            entityManager.merge(hospital);
        } else {
            System.out.println("El hospital no existe.");
        }
        transaction.commit();

        System.out.println("Hospital modificado.");
    }

    private static void borrarHospital() {
        int codigo;

        System.out.println("Introduce el codigo del hospital que deseas borrar: ");
        codigo = sc.nextInt();
        sc.nextLine();

        transaction.begin();

        HospitalEntidad hospitalEncontrar = entityManager.find(HospitalEntidad.class, codigo);

        if (hospitalEncontrar != null) {
            entityManager.remove(hospitalEncontrar);

            System.out.println("Hospital eliminado.");
        } else {
            System.out.println("El hospital no existe.");
        }
        transaction.commit();
    }

    private static void insertarMedico() {
        int medico_cod, codigo_hospital;
        String nombreMedico, apellido, especialidad;

        System.out.println("Introduce el codigo del medico: ");
        medico_cod = sc.nextInt();
        sc.nextLine();
        System.out.println("Introduce el nombre del medico: ");
        nombreMedico = sc.nextLine();
        System.out.println("Introduce el apellido del medico: ");
        apellido = sc.nextLine();
        System.out.println("Introduce la especialidad del medico: ");
        especialidad = sc.nextLine();
        System.out.println("Introduce el codigo del hospital: ");
        codigo_hospital = sc.nextInt();
        sc.nextLine();

        MedicoEntidad medico = new MedicoEntidad(medico_cod, nombreMedico, apellido, especialidad, codigo_hospital, entityManager.find(HospitalEntidad.class, codigo_hospital));

        transaction.begin();

        entityManager.merge(medico);

        transaction.commit();

        System.out.println("Medico insertado.");
    }

    private static void buscarMedico() {
        MedicoEntidad medico;
        System.out.println("Introduce el codigo del medico que deseas buscar: ");
        int medico_cod = sc.nextInt();
        sc.nextLine();

        transaction.begin();

        medico = entityManager.find(MedicoEntidad.class, medico_cod);

        if (medico != null) {
            System.out.println(medico);
            System.out.println();
        } else {
            System.out.println("No existe ese medico.");
        }
        transaction.commit();
    }

    private static void listarMedicos() {
        transaction.begin();

        List<MedicoEntidad> listaMedicos;
        Query query = entityManager.createQuery("SELECT medicoCod FROM MedicoEntidad ");
        listaMedicos = query.getResultList();
        for (MedicoEntidad m : listaMedicos) {
            System.out.println(m.toString());
        }
        transaction.commit();
    }

    private static void modificarMedico() {
        int medico_cod, codigo_hospital;
        String nombreMedico, apellido, especialidad;

        System.out.println("Introduce el codigo del medico que deseas modificar: ");
        medico_cod = sc.nextInt();
        sc.nextLine();

        transaction.begin();

        MedicoEntidad medicoEncontrar = entityManager.find(MedicoEntidad.class, medico_cod);

        if (medicoEncontrar != null) {
            System.out.println("Introduce el nombre del medico: ");
            nombreMedico = sc.nextLine();
            System.out.println("Introduce el apellido del medico: ");
            apellido = sc.nextLine();
            System.out.println("Introduce la especialidad del medico: ");
            especialidad = sc.nextLine();
            System.out.println("Introduce el codigo del hospital: ");
            codigo_hospital = sc.nextInt();
            sc.nextLine();

            MedicoEntidad medico = new MedicoEntidad(medico_cod, nombreMedico, apellido, especialidad, codigo_hospital, entityManager.find(HospitalEntidad.class, codigo_hospital));

            entityManager.merge(medico);
        } else {
            System.out.println("El medico no existe.");
        }
        transaction.commit();
    }

    private static void borrarMedico() {
        int medico_cod;

        System.out.println("Introduce el codigo del medico que deseas borrar: ");
        medico_cod = sc.nextInt();
        sc.nextLine();

        transaction.begin();

        MedicoEntidad medicoEncontrar = entityManager.find(MedicoEntidad.class, medico_cod);

        if (medicoEncontrar != null) {
            entityManager.remove(medicoEncontrar);
        } else {
            System.out.println("El medico no existe.");
        }
        transaction.commit();
    }
}
