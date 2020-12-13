package model.data_structures;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class tablaHashLinearProbing <K, V> implements ITablaSimbolos <K, V>{
	

	K[] listaLlaves;
	V[] listaValores;
	int M;
	int N;
	
	public tablaHashLinearProbing (int pCapacidadInicial) {
		M = pCapacidadInicial;
		listaValores = (V[]) new Object [pCapacidadInicial];
		listaLlaves = (K[]) new Object[pCapacidadInicial];
	}
	
	private int hash (K key) {return ((key.hashCode() & 0x7fffffff) % M);}

	public void put(K key, V value) {
		if (N >= M/2) reHash();  // double M (see text)
		   int i;
		   for (i = hash(key); listaLlaves[i] != null; i = (i + 1) % M)
		if (listaLlaves[i].equals(key)) { listaValores[i] = value; return; } listaLlaves[i] = key;
		listaValores[i] = value;
		N++;
	}

	private void reHash() {
		tablaHashLinearProbing<K, V> t;
	      t = new tablaHashLinearProbing<K, V>(M*2);
	      for (int i = 0; i < M; i++)
	if (listaLlaves[i] != null) t.put(listaLlaves[i], listaValores[i]);
	      listaLlaves = t.listaLlaves; listaValores = t.listaValores; M = t.M;
		System.out.println("Se ha realizado un rehash de la tabla con manejo de colisiones linear probing");
	}

	
	public V get(K key) {
		int hashKey = hash(key);
		int cantidadDeDatosPasados = 0;
		while(cantidadDeDatosPasados<M) {
			if(listaLlaves[hashKey]==null) 
			{
				return null;
			}
			if(listaLlaves[hashKey].equals(key)) {
				return listaValores[hashKey];
			}
			else {
				hashKey = (hashKey+1)%M;
				cantidadDeDatosPasados ++;
			}
		}
		return null;
	}

	
	public V remove(K key) {
		int hashKey = hash(key);
		int cantidadDeDatosPasados = 0;
		while(cantidadDeDatosPasados<M) {
			if(listaLlaves[hashKey].equals(key)) {
				V borrado = listaValores[hashKey];
				listaValores[hashKey] = null;
				listaLlaves[hashKey] = null;
				return borrado;
			}else {
				hashKey = (hashKey+1)%M;
				cantidadDeDatosPasados ++;
			}
		}
		return null;
	}

	
	public boolean contains(K key) {
		
		int hashKey = hash(key);
		int cantidadDeDatosPasados = 0;
		while(cantidadDeDatosPasados<M) 
		{
			if(listaLlaves[hashKey]== null)
			{
				return false; 
			}
			if(listaLlaves[hashKey].equals(key)) {
				return true;
			}else {
				hashKey = (hashKey+1)%M;
				cantidadDeDatosPasados ++;
			}
			
		}
		return false;
	}

	
	public boolean isEmpty() {
		for (int i = 0; i<M; i++) {
			if(listaValores[i]!=null) return false;
		}
		return true;
	}

	
	public int size() {
		int rta =0;
		for (int i = 0; i<M; i++) {
			if(listaValores[i]!=null || listaLlaves[i]!=null){
				rta++;
				if(!(listaValores[i]!=null && listaLlaves[i]!=null)) System.out.println("CAUTION: Existe una inconsistencia en los datos");
			}
		}
		return rta;
	}

	
	public ListaEncadenadaSinComparable<K> keySet() {
		ListaEncadenadaSinComparable<K> listaRta  = new ListaEncadenadaSinComparable<K>();
		for (K k : listaLlaves) 
		{
			if(k!=null)listaRta.agregarAlPrincipio(k);
		}
		return listaRta;
	}

	
	public ListaEncadenadaSinComparable<V> valueSet() {
		ListaEncadenadaSinComparable<V> listaRta  = new ListaEncadenadaSinComparable<V>();
		for (V v : listaValores) {
			if(v!=null)listaRta.agregarAlPrincipio(v);
		}
		return listaRta;
	}
	
	public V darPrimerElemento() {
		for(int i =0 ; i < N; i++)
		{
			if(listaValores[i] != null) return listaValores[i];
		}
		return null;
	}
	
	public V darUltimoElemento() {
		for(int i =M-1 ; i >0; i--)
		{
			if(listaValores[i] != null)
				{return listaValores[i];}
		}
		return null;
	}
	
	public int[] pruebaInexistentes()
	{
		int[] container = new int[200];
		boolean seLLeno = false;
		int j = 0; 
		
		for(int i =0; i<M && j <200; i ++)
		{
			if(listaValores[i]==null)
			{
				container[j] = i;
				j++;
			}
			i++;
		}
		return container;
	
	}
	public String[] pruebaExistentes()
	{
		String[] container = new String[800];
		boolean seLLeno = false;
		int j = 0; 
		
		for(int i =0; i<M && j <800; i ++)
		{
			if(listaValores[i]!=null)
			{
				container[j] = (String)listaLlaves[i];
				j++;
			}
			i++;
		}
		return container;
	}
	

}
