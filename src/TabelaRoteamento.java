import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class TabelaRoteamento {

    private ArrayList<Rota> tabela;
    private Semaphore registrySemaphore;
    
    public TabelaRoteamento() throws FileNotFoundException, IOException {
    	registrySemaphore = new Semaphore(1);
    	importarEnderecos("IPVizinhos.txt");
    }
    
    private void importarEnderecos(String caminho) throws IOException{
        tabela = new ArrayList<>();
        try {
            FileReader fr = new FileReader(caminho);
            BufferedReader br = new BufferedReader(fr);
            String stringRead = br.readLine();
            while (stringRead != null) {
            	String ipOrigem = "*";
            	String ipDestino = stringRead;
            	int metrica = 1;
            	Rota rota = new Rota(ipOrigem, ipDestino, metrica);
            	tabela.add(rota);
                // próxima linha
                stringRead = br.readLine();
            }
        } catch (FileNotFoundException ex) {
            
        }
    }

    public void update_tabela(String tabela_s, InetAddress IPAddress) {
    	
    	registrySemaphore.acquireUninterruptibly();
        /* Atualize a tabela de rotamento a partir da string recebida. */
    	// esse metodo vai pegar o que tu receber dos vizinho: uma lista de rotas q eles tem
    	//sharedRoutes é a lista de rotas "parseamos" no this.parse...
    	ArrayList<Rota> sharedRoutes = this.parseEndereco(tabela_s, IPAddress);
    	// para cada rota shareada, precisamos ver se já temos, pq se nós já temos, temos que ver a melhor métrica
    	// se não tivermos, adicionar pra tabela: private ArrayList<Rota> tabela;
    	for(Rota r : sharedRoutes){
    		Rota findResult = this.findEndereco(r.getIpDestino());
    		// achou
    		if(findResult != null){
    			if(findResult.getMetrica() < r.getMetrica()){
    				findResult.setIpOrigem(IPAddress.getAddress().toString());
    				findResult.setMetrica(r.getMetrica() + 1);
    			}
    		}
    		//nao achou
    		else{
    			r.setMetrica(r.getMetrica() + 1);
    			tabela.add(r); //quando tu conseguir rodar essa classe, tu ganha o oscar de dev, e vamo enche de chat o codigo mesmo, q fodace se ela ler
    		}
    	}
    	
        System.out.println(IPAddress.getHostAddress() + ": " + tabela_s);
        registrySemaphore.release();
    }

    public String get_tabela_string() {
    	/* Tabela de roteamento vazia conforme especificado no protocolo */
        String tabela_string_vazia = "!";          
        
        /* Converta a tabela de rotamento para string, conforme formato definido no protocolo . */
        String rotas = "";
        for(Rota r : tabela){
        	rotas += r.toString() + ";";
        }
        
        if(tabela.isEmpty()){
        	return tabela_string_vazia;
        }
        
        return rotas;
    }
    
    public ArrayList<Rota> parseEndereco(String in, InetAddress IPAddress){
    	ArrayList<Rota> result = new ArrayList<>();
    	
    	String[] routes = in.split("\\*");
    	String add;
    	String metrica;
    	String[] rota;
    	Rota obj;
    	for(int i = 1; i < routes.length; i++){
    		rota = routes[i].split(";");
    		add = rota[0];
    		metrica = rota[1];
    		obj = new Rota(IPAddress.getHostAddress(), add, Integer.parseInt(metrica));
    		result.add(obj);
    	}
    	return result;
    }
    
    public Rota findEndereco(String endereco){
    	for(Rota r : tabela){
    		if(r.getIpDestino().equalsIgnoreCase(endereco)){
    			return r;
    		}
    	}
    	return null;
    }
}