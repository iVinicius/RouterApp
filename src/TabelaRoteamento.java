import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;

public class TabelaRoteamento {

    private ArrayList<Rota> tabela;
    

    public TabelaRoteamento() throws FileNotFoundException, IOException {
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
        /* Atualize a tabela de rotamento a partir da string recebida. */
    	ArrayList<Rota> sharedRoutes = this.parseEndereco(tabela_s, IPAddress);
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
    			tabela.add(r);
    		}
    	}
    	
    	
        System.out.println(IPAddress.getHostAddress() + ": " + tabela_s);

    }

    public String get_tabela_string() {
        String tabela_string = "!";
        /* Tabela de roteamento vazia conforme especificado no protocolo */
        return tabela_string;
        
        /* Converta a tabela de rotamento para string, conforme formato definido no protocolo . */
        
        
        
    }
    
    public ArrayList<Rota> parseEndereco(String in, InetAddress IPAddress){
    	ArrayList<Rota> result = new ArrayList<>();
    	
    	String[] routes = in.split("*");
    	String add;
    	String metrica;
    	String[] rota;
    	Rota obj;
    	for(int i = 0; i < routes.length; i++){
    		rota = routes[i].split(";");
    		add = rota[0];
    		metrica = rota[1];
    		obj = new Rota(IPAddress.getAddress().toString(), add, Integer.parseInt(metrica));
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