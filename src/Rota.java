
public class Rota {

	private String ipOrigem;
	private String ipDestino;
	private String metrica;
	
	public Rota(String ipOrigem, String ipDestino, String metrica) {
		this.ipOrigem = ipOrigem;
		this.ipDestino = ipDestino;
		this.metrica = metrica;
	}

	public String getIpOrigem() {
		return ipOrigem;
	}

	public void setIpOrigem(String ipOrigem) {
		this.ipOrigem = ipOrigem;
	}

	public String getIpDestino() {
		return ipDestino;
	}

	public void setIpDestino(String ipDestino) {
		this.ipDestino = ipDestino;
	}

	public String getMetrica() {
		return metrica;
	}

	public void setMetrica(String metrica) {
		this.metrica = metrica;
	}

	@Override
	public String toString() {
		return "Rota [ipOrigem=" + ipOrigem + ", ipDestino=" + ipDestino + ", metrica=" + metrica + "]";
	}
	
	
}
