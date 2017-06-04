import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;

public class TabelaRoteamento {

    private ArrayList<Rota> tabela;

    public TabelaRoteamento() throws FileNotFoundException, IOException {
    	importarEnderecos();
    }
    
    private void importarEnderecos() throws IOException{
        tabela = new ArrayList<>();
        try {
            FileReader fr = new FileReader("IPVizinhos.txt");
            BufferedReader br = new BufferedReader(fr);
            String stringRead = br.readLine();
            while (stringRead != null) {
            	String ipOrigem = "*";
            	String ipDestino = stringRead;
            	int metrica = 1;
            	Rota rota = new Rota(ipOrigem, ipDestino, metrica);
            	tabela.add(rota);
                // pr�xima linha
                stringRead = br.readLine();
            }
        } catch (FileNotFoundException ex) {
            
        }
    }

    public void update_tabela(String tabela_s, InetAddress IPAddress) {
        /* Atualize a tabela de rotamento a partir da string recebida. */

        System.out.println(IPAddress.getHostAddress() + ": " + tabela_s);

    }

    public String get_tabela_string() {
        String tabela_string = "";
        
        /* Tabela de roteamento vazia conforme especificado no protocolo */
        if(tabela.isEmpty()){
            return "!";
        }
        /* Converta a tabela de rotamento para string, conforme formato definido no protocolo . */
        String[] aux;
        for(int i = 0; i < tabela.size(); i++){
            aux = tabela.get(i)[0].split(";");
            tabela_string += "*" + aux[0] + ";" + aux[1];
        }
        return tabela_string;
    }

}