
public class Rota {

	private String ipSaida;
	private String ipDestino;
	private int metrica;
	
	public Rota(String ipOrigem, String ipDestino, int metrica) {
		this.ipSaida = ipOrigem;
		this.ipDestino = ipDestino;
		this.metrica = metrica;
	}

	public String getIpOrigem() {
		return ipSaida;
	}

	public void setIpOrigem(String ipOrigem) {
		this.ipSaida = ipOrigem;
	}

	public String getIpDestino() {
		return ipDestino;
	}

	public void setIpDestino(String ipDestino) {
		this.ipDestino = ipDestino;
	}

	public int getMetrica() {
		return metrica;
	}

	public void setMetrica(int metrica) {
		this.metrica = metrica;
	}

	@Override
	public String toString() {
		return "Rota [ipOrigem=" + ipSaida + ", ipDestino=" + ipDestino + ", metrica=" + metrica + "]";
	}
	
}
