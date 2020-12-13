package model.data_structures;

/**
 * 2019-01-23
 * Estructura de Datos Arreglo Dinamico de Strings.
 * El arreglo al llenarse (llegar a su maxima capacidad) debe aumentar su capacidad.
 * @author Fernando De la Rosa
 *
 */
public class ArregloDinamico implements IArregloDinamico {
		/**
		 * Capacidad maxima del arreglo
		 */
        private int tamanoMax;
		/**
		 * Numero de elementos presentes en el arreglo (de forma compacta desde la posicion 0)
		 */
        private int tamanoAct;
        /**
         * Arreglo de elementos de tamaNo maximo
         */
        private String elementos[ ];

        /**
         * Construir un arreglo con la capacidad maxima inicial.
         * @param max Capacidad maxima inicial
         */
		public ArregloDinamico( int max )
        {
               elementos = new String[max];
               tamanoMax = max;
               tamanoAct = 0;
        }
        
		public void agregar( String dato )
        {
               if ( tamanoAct == tamanoMax )
               {  // caso de arreglo lleno (aumentar tamaNo)
                    tamanoMax = 2 * tamanoMax;
                    String [ ] copia = elementos;
                    elementos = new String[tamanoMax];
                    for ( int i = 0; i < tamanoAct; i++)
                    {
                     	 elementos[i] = copia[i];
                    } 
            	    System.out.println("Arreglo lleno: " + tamanoAct + " - Arreglo duplicado: " + tamanoMax);
               }	
               elementos[tamanoAct] = dato;
               tamanoAct++;
       }

		public int darCapacidad() {
			return tamanoMax;
		}

		public int darTamano() {
			return tamanoAct;
		}

		public String darElemento(int i) {
			return elementos[i];
		}

		public String buscar(String dato) {
			String res = null;
			for (int i = 0; i<tamanoAct;i++) {
				if (dato.compareTo(elementos[i])==0) {
					res = elementos[i];
					break;
				}
			}
			return res;
		}
		
		public String get(int index) {
			if (index<=tamanoAct) {
				return elementos[index];
			}
			return null;
		}

		public String eliminar(String dato) {
			int index = 0;
			String res = null;
			while(index < tamanoAct) {
				if (elementos[index].compareTo(dato)==0) {
					res = elementos[index];
					break;
				}
				else {
					index++;
				}
			}
			if (res!=null) {
				for (;index<tamanoAct;index++) {
					elementos[index]=elementos[index+1];
				}
				elementos[tamanoAct-1] = null;
			}
			return res;
		}

}
