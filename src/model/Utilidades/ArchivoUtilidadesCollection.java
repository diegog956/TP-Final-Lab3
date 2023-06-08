package model.Utilidades;

import java.io.*;
import java.util.Collection;

public class ArchivoUtilidadesCollection<T extends Collection> {
    public void grabar(T coleccion, String nombre_archivo) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(nombre_archivo);
            ObjectOutputStream oos = null;
            try {
                oos = new ObjectOutputStream(fos);

                oos.writeObject(coleccion);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    oos.flush();
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } finally {
            try {
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public T leer(String nombre_archivo) {
        T coleccion = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(nombre_archivo);
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(fis);

                coleccion = (T) ois.readObject();

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return coleccion;
    }
}


