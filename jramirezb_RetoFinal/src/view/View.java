package view;

import model.logic.Modelo;

public class View 
{
	    /**
	     * Metodo constructor
	     */
	    public View()
	    {

	    }
	    
		public void printMenu()
		{
			System.out.println("1. Cargar Datos de Taxis parte A");
			System.out.println(" ");
		}

		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		
}
