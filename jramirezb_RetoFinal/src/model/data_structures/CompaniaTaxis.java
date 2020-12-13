package model.data_structures;

public class CompaniaTaxis 
{
	/**
	 * Numero de taxis de la compa�ia
	 */
	int cantTaxis; 
	
	/**
	 * Numero de viajes que tiene esta compania en total
	 */
	int cantidadViajes; 
	
	/**
	 * Numero de taxis de la compa�ia
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
	 * Da la cantidad de taxis de esta compa�ia
	 * @return cantidad taxis
	 */
	public int darCantidadTaxis()
	{
		return cantTaxis; 
	}
	
	/**
	 * Da el nombre de esta compa�ia
	 * @return nombre
	 */
	public String darNombreCompania()
	{
		return nombreCompania; 
	}
	
	/**
	 * Agrega un taxi a la cantidad de taxis de la compa��a
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
