package model.data_structures;

public class CompaniaTaxis 
{
	/**
	 * Numero de taxis de la compañia
	 */
	int cantTaxis; 
	
	/**
	 * Numero de viajes que tiene esta compania en total
	 */
	int cantidadViajes; 
	
	/**
	 * Numero de taxis de la compañia
	 */
	String nombreCompania; 
	
	/**
	 * Lista con los taxis
	 */
	tablaHashLinearProbing<String, Taxi> listaTaxis; 
	
	/**
	 * Constructor
	 */
	public CompaniaTaxis(String pNombre)
	{
		cantTaxis = 0;
		nombreCompania = pNombre; 
		listaTaxis = new tablaHashLinearProbing<String, Taxi>(100000); 
		cantidadViajes = 0; 
	}
	
	/**
	 * Da la cantidad de taxis de esta compañia
	 * @return cantidad taxis
	 */
	public int darCantidadTaxis()
	{
		return cantTaxis; 
	}
	
	/**
	 * Da el nombre de esta compañia
	 * @return nombre
	 */
	public String darNombreCompania()
	{
		return nombreCompania; 
	}
	
	/**
	 * Agrega un taxi a la cantidad de taxis de la compañía
	 */
	public void agregarTaxi(Taxi taxi)
	{
		if((listaTaxis.contains(taxi.darId())== false))
		{
			listaTaxis.put(taxi.darId(), taxi);
			cantTaxis++; 
		}
	}
	
	public boolean existeTaxi(String id)
	{
		if(listaTaxis.contains(id))
		{
			return true; 
		}
		else
		{
			return false; 
		}
	}
	
	public void agregarViaje()
	{
		cantidadViajes++;
	}
	
	public int darCantidadViajes()
	{
		return cantidadViajes; 
	}
}
