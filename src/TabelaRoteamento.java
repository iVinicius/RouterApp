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
    	for(Rota r : tabela){
    		
    	}
    	
    	
        System.out.println(IPAddress.getHostAddress() + ": " + tabela_s);

    }

    public String get_tabela_string() {
        String tabela_string = "!";
        /* Tabela de roteamento vazia conforme especificado no protocolo */
        return tabela_string;
        
        /* Converta a tabela de rotamento para string, conforme formato definido no protocolo . */
        
        
        
    }
    
    public ArrayList<Rota> parseEndereco(String in){
    	
    }
    
    public int findEndereco(String endereco){
    	for(int i = 0; i < tabela.size(); i++){
    		if(tabela.get(i).getIpDestino().equals(endereco)){
    			return i;
    		}
    	}
    	return -1;
    }

}