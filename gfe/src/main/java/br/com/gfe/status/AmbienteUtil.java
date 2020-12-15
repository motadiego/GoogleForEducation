package br.com.gfe.status;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.function.Supplier;

/**
 * Classe utilitária para obter informações do ambiente de execução da
 * aplicação.
 * 
 *
 */
public class AmbienteUtil {

	/**
	 * Retorna a instância atual do Tomcat em que o sistema está rodando.
	 * 
	 * Property br.empresa.tomcat.instanceName
	 * 
	 * @return
	 */
	public static String getInstanceName() {
		String intanceName = System.getProperty("br.empresa.tomcat.instanceName");
		if (intanceName == null) {
			return getNomeServidor();
		} else {
			return intanceName;
		}
	}

	/**
	 * Retorna o nome do servidor + nome instância do Tomcat que o sistema está
	 * sendo executado.
	 * 
	 * @return
	 */
	public static String getNomeServidorComInstancia() {
		String nomeServidor = getNomeServidor();
		String nomeInstancia = getInstanceName();
		if (nomeInstancia != null)
			nomeServidor += "." + nomeInstancia;
		return nomeServidor;
	}

	/**
	 * Recupera o nome do servidor em que a aplicação está executando. Quando o nome
	 * do servidor não puder ser recuperado, retorna "Unknown Host".
	 * 
	 * @return Nome do Servidor ou "Unknown host".
	 * 
	 */
	public static String getNomeServidor() {
		return getNomeServidor(() -> "Unknown Host");
	}

	/**
	 * Recupera o nome do servidor e, quando não possível recuperar, retorna o nome
	 * informado pelo Supplier.
	 * 
	 * @param supplier
	 * @return
	 */
	public static String getNomeServidor(Supplier<String> supplier) {
		String hostName = null;
		try {
			hostName = getLocalName();
		} catch (Exception e) {
			return supplier.get();
		}

		if (hostName == null) {
			return supplier.get();
		}

		return hostName;
	}

	/**
	 * Retorna o nome da maquina em que o matodo a executado
	 *
	 * @return
	 */
	public static String getLocalName() {
		try {
			return InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			return null;
		}
	}

}
