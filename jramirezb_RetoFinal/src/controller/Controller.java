package controller;

import java.util.Scanner;

import model.data_structures.CompaniaTaxis;
import model.data_structures.ListaEncadenadaSinComparable;
import model.logic.Modelo;
import view.View;

public class Controller {

	/* Instancia del Modelo*/
	private Modelo modelo;
	
	/* Instancia de la Vista*/
	private View view;
	
	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
		modelo = new Modelo(this);
	}
		
	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String dato = "";
		String respuesta = "";

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option){
				case 1:
					view.printMessage("--------- \nIndique cual data desea cargar: ");	
					view.printMessage("1. Data completa ");	
					view.printMessage("2. Data media");	
					view.printMessage("3. Data resumida");	
					int database = lector.nextInt();
					switch(database) {
					case 1:
						modelo.loadLarge();
						break;
					case 2:
						modelo.loadMedium();
						break;
					case 3:
						modelo.loadSmall();
						break;
					default:
						view.printMessage("Error, opcion incorrecta");	
						break;
					}
					if (modelo.darCarga()) {
						view.printMessage("---------\nLa cantidad de Taxis es: " + modelo.darCantidadTaxis());
						view.printMessage("---------\nLas compañias con más de un taxi son:\n---------");
						ListaEncadenadaSinComparable<String> companias = modelo.darCompanias(); 
						for (int i = 0; i < companias.contarDatos(); i++) 
						{
							view.printMessage(companias.darElemento(i)); 
						}	
						Scanner sc = new Scanner(System.in);
						view.printMessage("---------\n Compañías con mayor cantidad de taxis");
						view.printMessage("Ingrese de cuantas compañías quiere que sea el top :");
						int cantidad = sc.nextInt(); 
						view.printMessage("El top " + cantidad + " de compañias con más taxis son:\n---------");
						CompaniaTaxis[] topCompanias = modelo.darCompaniasConMasTaxis();
						for (int i = 0; i < cantidad; i++) 
						{
							int pos = i+1;
							CompaniaTaxis act = topCompanias[i];
							view.printMessage(pos+". " + act.darNombreCompania() + ": " + act.darCantidadTaxis() + " taxis");
							
						}
						
						view.printMessage("---------\n Compañías con mayor cantidad de viajes");
						view.printMessage("---------\n Ingrese de cuantas compañías quiere que sea el top :");
						int cantidad2 = sc.nextInt(); 
						view.printMessage("---------\nEl top " + cantidad2 + " de compañias con más viajes son:\n---------");
						CompaniaTaxis[] topCompanias2 = modelo.darCompaniasConMasViajes();
						for (int i = 0; i < cantidad2; i++) 
						{
							int pos = i+1;
							CompaniaTaxis act = topCompanias2[i];
							view.printMessage(pos+". " + act.darNombreCompania() + ": " + act.darCantidadViajes() + " viajes");
						}
						
						view.printMessage("---------\n");
					}
					else {
						view.printMessage("--------- \n ERROR: No se pudo cargar la base de datos");
					}
					break;
				default: 
					view.printMessage("--------- \n No puede elegir esa opción ya que no existe \n---------");
					break;
			}
		}
	}
			
	
	public void printMessage(String message) {
		view.printMessage(message);
	}
}
