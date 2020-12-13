package model.logic;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

import javax.sound.midi.SysexMessage;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvValidationException;

import controller.Controller;
import model.data_structures.ArregloDinamico;
import model.data_structures.CompaniaTaxis;
import model.data_structures.IArregloDinamico;
import model.data_structures.ListaEncadenadaSinComparable;
import model.data_structures.Taxi;
import model.data_structures.tablaHashLinearProbing;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo {
	
	/**
	 * Declaración de la ubicación del archivo pequeño
	 */
	private static String archivoPequeno = "taxi-trips-wrvz-psew-subset-small.csv";
	
	/**
	 * Declaración de la ubicación del archivo mediano
	 */
	private static String archivoMediano = "taxi-trips-wrvz-psew-subset-medium.csv";
	
	/**
	 * Declaración de la ubicación del archivo grande
	 */
	private static String archivoGrande = "taxi-trips-wrvz-psew-subset-large.csv";
	
	
	
	/**
	 * Atributos del modelo del mundo
	 */
	private Controller controller;
	private tablaHashLinearProbing<String, CompaniaTaxis> companias; 
	private int cantidadTaxis;
	private boolean data;
	
	

	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public Modelo(Controller pController)
	{
		controller = pController; 
	}
	
	
	/**
	 * Metodo para cargar la base de datos grande
	 */
	public void loadLarge()
	{
		companias = new tablaHashLinearProbing<String, CompaniaTaxis>(1000);
		cantidadTaxis = 0; 
		data = false;
		Path path = FileSystems.getDefault().getPath("data/", archivoGrande); 
		Reader reader;
		
		try 
		{
			reader = Files.newBufferedReader(path);
			
			CSVParser parser = new CSVParserBuilder().withSeparator(',').withIgnoreQuotations(true).build();
			CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).withCSVParser(parser).build();
			
		    String[] line;
		    while ((line = csvReader.readNext()) != null) 
		    {
		  
		    	if(line[12].equals("Chicago Independents"))
		    	{
		    		if((companias.contains("Independent Owner"))==false)
		    		{
		    			CompaniaTaxis nueva = new CompaniaTaxis("Independent Owner"); 
		    			Taxi taxiAct = new Taxi(line[1]); 
			    		nueva.agregarTaxi(taxiAct);    
			    		companias.put(nueva.darNombreCompania(), nueva);
			    	}
		    		else
		    		{
		    			if((companias.get("Independent Owner").existeTaxi(line[1]))==false)
		    			{
		    				Taxi taxiAct = new Taxi(line[1]);
		    				companias.get("Independent Owner").agregarTaxi(taxiAct);
		    			
		    			}
		    		}
		    	}
		    	else if((companias.contains(line[12])== false))
		    	{
		    		CompaniaTaxis nueva = new CompaniaTaxis(line[12]); 
		    		Taxi taxiAct = new Taxi(line[1]);
		    		nueva.agregarTaxi(taxiAct);
		    		companias.put(nueva.darNombreCompania(), nueva);
		    	}
		    	else
		    	{
		    		if((companias.get(line[12]).existeTaxi(line[1])==false))
		    		{
		    			Taxi taxiAct = new Taxi(line[1]);
		    			companias.get(line[12]).agregarTaxi(taxiAct);
		    			
		    		}
		    	}
		    }
		    reader.close();
		    csvReader.close();  
		    data = true;
		} 
		catch (IOException | NumberFormatException | CsvValidationException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo para cargar la base de datos mediana
	 */
	public void loadMedium()
	{
		companias = new tablaHashLinearProbing<String, CompaniaTaxis>(1000);
		cantidadTaxis = 0; 
		data = false;
		Path path = FileSystems.getDefault().getPath("data/", archivoMediano); 
		Reader reader;
		
		try 
		{
			reader = Files.newBufferedReader(path);
			
			CSVParser parser = new CSVParserBuilder().withSeparator(',').withIgnoreQuotations(true).build();
			CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).withCSVParser(parser).build();
			
		    String[] line;
		    while ((line = csvReader.readNext()) != null) 
		    {
		  
		    	if(line[13].equals("Chicago Independents"))
		    	{
		    		if((companias.contains("Independent Owner"))==false)
		    		{
		    			CompaniaTaxis nueva = new CompaniaTaxis("Independent Owner"); 
		    			Taxi taxiAct = new Taxi(line[1]); 
			    		nueva.agregarTaxi(taxiAct);    
			    		companias.put(nueva.darNombreCompania(), nueva);
			    	}
		    		else
		    		{
		    			if((companias.get("Independent Owner").existeTaxi(line[1]))==false)
		    			{
		    				Taxi taxiAct = new Taxi(line[1]);
		    				companias.get("Independent Owner").agregarTaxi(taxiAct);
		    			
		    			}
		    		}
		    	}
		    	else if((companias.contains(line[13])== false))
		    	{
		    		CompaniaTaxis nueva = new CompaniaTaxis(line[13]); 
		    		Taxi taxiAct = new Taxi(line[1]);
		    		nueva.agregarTaxi(taxiAct);
		    		companias.put(nueva.darNombreCompania(), nueva);
		    	}
		    	else
		    	{
		    		if((companias.get(line[13]).existeTaxi(line[1])==false))
		    		{
		    			Taxi taxiAct = new Taxi(line[1]);
		    			companias.get(line[13]).agregarTaxi(taxiAct);
		    			
		    		}
		    	}
		    }
		    reader.close();
		    csvReader.close();
		    data = true;
		} 
		catch (IOException | NumberFormatException | CsvValidationException e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * Metodo para cargar la base de datos pequena 
	 */
	public void loadSmall() {
		companias = new tablaHashLinearProbing<String, CompaniaTaxis>(1000);
		cantidadTaxis = 0; 
		data = false;
		Path path = FileSystems.getDefault().getPath("data/", archivoPequeno); 
		Reader reader;
		
		try 
		{
			reader = Files.newBufferedReader(path);
			
			CSVParser parser = new CSVParserBuilder().withSeparator(',').withIgnoreQuotations(true).build();
			CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).withCSVParser(parser).build();
			
		    String[] line;
		    while ((line = csvReader.readNext()) != null) 
		    {
		  
		    	if(line[14].equals("Chicago Independents"))
		    	{
		    		if((companias.contains("Independent Owner"))==false)
		    		{
		    			CompaniaTaxis nueva = new CompaniaTaxis("Independent Owner"); 
		    			Taxi taxiAct = new Taxi(line[1]); 
			    		nueva.agregarTaxi(taxiAct);    
			    		nueva.agregarViaje();
			    		companias.put(nueva.darNombreCompania(), nueva);
			    	}
		    		else
		    		{
		    			if((companias.get("Independent Owner").existeTaxi(line[1]))==false)
		    			{
		    				Taxi taxiAct = new Taxi(line[1]);
		    				companias.get("Independent Owner").agregarTaxi(taxiAct);
		    				companias.get("Independent Owner").agregarViaje();
		    			}
		    		}
		    	}
		    	else if((companias.contains(line[14])== false))
		    	{
		    		CompaniaTaxis nueva = new CompaniaTaxis(line[14]); 
		    		Taxi taxiAct = new Taxi(line[1]);
		    		nueva.agregarTaxi(taxiAct);
		    		nueva.agregarViaje();
		    		companias.put(nueva.darNombreCompania(), nueva);
		    	}
		    	else
		    	{
		    		if((companias.get(line[14]).existeTaxi(line[1])==false))
		    		{
		    			Taxi taxiAct = new Taxi(line[1]);
		    			companias.get(line[14]).agregarTaxi(taxiAct);
		    			companias.get(line[14]).agregarViaje();
		    		}
		    	}
		    }
		    reader.close();
		    csvReader.close(); 
		    data = true;
		} 
		catch (IOException | NumberFormatException | CsvValidationException e) 
		{
			e.printStackTrace();
		}
	}

	public void printMessage(String message) {
		controller.printMessage(message);
	}
	
	
	public ListaEncadenadaSinComparable<String> darCompanias()
	{
		return companias.keySet(); 
	}
	
	public boolean darCarga() {
		return data;
	}

	
	
	
	
	public int darCantidadTaxis()
	{
		ListaEncadenadaSinComparable<CompaniaTaxis> companiasTaxis = companias.valueSet(); 
		for (int i = 0; i < companias.valueSet().contarDatos(); i++) 
		{
			CompaniaTaxis act = companiasTaxis.darElemento(i); 
			cantidadTaxis += act.darCantidadTaxis();
		}
		return cantidadTaxis; 
	}

	public CompaniaTaxis[] darCompaniasConMasTaxis()
	{
		ListaEncadenadaSinComparable<CompaniaTaxis> listacomp = companias.valueSet();
		CompaniaTaxis[] rta = new CompaniaTaxis[listacomp.contarDatos()]; 
				
		for (int i = 0; i < listacomp.contarDatos(); i++) 
		{
			rta[i] = listacomp.darElemento(i); 
		}
		quickSortListaTaxis(rta);
		
		for (int i = 0; i < rta.length; i++) 
		{
			CompaniaTaxis act = rta[i]; 
			for (int j = i+1; j < rta.length; j++) 
			{
				if(act.darCantidadTaxis()< rta[j].darCantidadTaxis())
				{
					if(rta[i].darNombreCompania().equals(rta[j].darNombreCompania()) == false)
					{
						CompaniaTaxis temp = rta[j];
						rta[j] = rta[i];
						rta[i] = temp; 
					}	
				}
			}
		}
		
		return rta;
		
	}
	
	public CompaniaTaxis[] darCompaniasConMasViajes()
	{
		ListaEncadenadaSinComparable<CompaniaTaxis> listacomp = companias.valueSet();
		CompaniaTaxis[] rta = new CompaniaTaxis[listacomp.contarDatos()]; 
				
		for (int i = 0; i < listacomp.contarDatos(); i++) 
		{
			rta[i] = listacomp.darElemento(i); 
		}
		quickSortListaViajes(rta);
		quickSortListaViajes(rta);
		for (int i = 0; i < rta.length; i++) 
		{
			CompaniaTaxis act = rta[i]; 
			for (int j = i+1; j < rta.length; j++) 
			{
				if(act.darCantidadViajes()< rta[j].darCantidadViajes())
				{
					if(rta[i].darNombreCompania().equals(rta[j].darNombreCompania()) == false)
					{
						CompaniaTaxis temp = rta[j];
						rta[j] = rta[i];
						rta[i] = temp; 
					}	
				}
			}
		}
		
		return rta;
		
	}
	
	
	public void quickSortListaTaxis(CompaniaTaxis[] lista)
	{
	    quickSortPropioTaxis(lista, 0, lista.length-1);
	}
	
	public void quickSortPropioTaxis(CompaniaTaxis[] lista, int low, int high)
	{
		if(low<high+1)
		{
			int p = partitionTaxis(lista, low, high);
			quickSortPropioTaxis(lista, low, p-1);
			quickSortPropioTaxis(lista, p+1, high);
		}
	}
	
	public void swapTaxis(CompaniaTaxis[] lista, int index1, int index2)
	{
		CompaniaTaxis temp = lista[index1];
		lista[index1] = lista[index2];
		lista[index2] = temp;
	}
	
	public int getPivotTaxis(int low, int high)
	{
		Random rand = new Random();
		return rand.nextInt((high-low)+1)+low; 
	}
	
	private int partitionTaxis(CompaniaTaxis[] lista, int low, int high)
	{
		swapTaxis(lista, low, getPivotTaxis(low, high));
		int border = low+1;
		for (int i = border; i < high; i++) 
		{
			if(lista[i].darCantidadTaxis() > lista[low].darCantidadTaxis())
			{
				swapTaxis(lista, i, border++);
			}
		}
		swapTaxis(lista, low, border-1);
		return border-1; 
	}
	
	
	public void quickSortListaViajes(CompaniaTaxis[] lista)
	{
	    quickSortPropioViajes(lista, 0, lista.length-1);
	}
	
	public void quickSortPropioViajes(CompaniaTaxis[] lista, int low, int high)
	{
		if(low<high+1)
		{
			int p = partitionViajes(lista, low, high);
			quickSortPropioViajes(lista, low, p-1);
			quickSortPropioViajes(lista, p+1, high);
		}
	}
	
	public void swapViajes(CompaniaTaxis[] lista, int index1, int index2)
	{
		CompaniaTaxis temp = lista[index1];
		lista[index1] = lista[index2];
		lista[index2] = temp;
	}
	
	public int getPivotViajes(int low, int high)
	{
		Random rand = new Random();
		return rand.nextInt((high-low)+1)+low; 
	}
	
	private int partitionViajes(CompaniaTaxis[] lista, int low, int high)
	{
		swapViajes(lista, low, getPivotViajes(low, high));
		int border = low+1;
		for (int i = border; i < high; i++) 
		{
			if(lista[i].darCantidadViajes() > lista[low].darCantidadViajes())
			{
				swapViajes(lista, i, border++);
			}
		}
		swapViajes(lista, low, border-1);
		return border-1; 
	}

	
}
