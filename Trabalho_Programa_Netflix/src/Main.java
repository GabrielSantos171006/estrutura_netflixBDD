/*Gabriel Gomes dos Santos - 10748012, Kauê Trigolo Damaceno 10748319,  Henrique Orlando Rosa da Silva – 10753777  */

package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BST bst = new BST();
        int opcao = 0;
        int contadorIds = 1000;

        while (opcao != 8) {
            System.out.println("\n--- MENU NETFLIX BST ---");
            System.out.println("1. Ler dados de arquivo (titles.csv)");
            System.out.println("2. Analises de Dados (é necessário ler os dados primeiro)");
            System.out.println("3. Inserir Programa");
            System.out.println("4. Buscar Programa por ID");
            System.out.println("5. Remover Programa por ID");
            System.out.println("6. Exibir Altura da Arvore");
            System.out.println("7. Salvar dados em arquivo");
            System.out.println("8. Encerrar");
            System.out.print("Escolha uma opcao: ");
            
            String entrada = scanner.nextLine().trim();
            
            if (entrada.isEmpty()) {
                continue;
            }

            try {
                opcao = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Digite apenas numeros.");
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.print("Informe o nome do arquivo (ex: titles.csv): ");
                    String nomeArquivo = scanner.nextLine();
                    lerArquivoCSV(nomeArquivo, bst);
                    break;

                case 2:
                    System.out.println("Executando analises...\n");
                    bst.analiseTop10FilmesDrama();
                    bst.analiseTop10SeriesSeculo20();
                    bst.analiseTop5FilmesBritanicos();
                    bst.analiseTop5SeriesAmericanasLongas();
                    bst.analiseTop5SeriesNaoAmericanasCurtas();
                    break;

                case 3: {
                    System.out.println("--- Inserir Novo Programa ---");
                    System.out.print("Tipo (SHOW ou MOVIE): ");
                    String tipo = scanner.nextLine().toUpperCase();
                    
                    String novoId = (tipo.equals("SHOW") ? "ts" : "tm") + (++contadorIds);
                    
                    System.out.print("Titulo: ");
                    String titulo = scanner.nextLine();
                    
                    ProgramaNetFlix novoProg = new ProgramaNetFlix(
                        novoId, titulo, tipo, "Descricao inserida manualmente", 2026, 
                        "TV-MA", 120, "['drama']", "['BR']", 1.0, 
                        "tt0000", 8.5, 100, 50.0, 8.0
                    );
                    
                    bst.inserir(novoProg);
                    System.out.println("Programa inserido com sucesso! ID gerado: " + novoId);
                    break;
                }

                case 4:
                    System.out.print("Digite o ID do programa para busca (ex: tm123): ");
                    String idBusca = scanner.nextLine();
                    bst.buscarEContabilizar(idBusca);
                    break;

                case 5:
                    System.out.print("Digite o ID do programa para remover: ");
                    String idRemover = scanner.nextLine().trim();
                    bst.remover(idRemover);
                    System.out.println("Comando de remocao executado para o ID: " + idRemover);
                    break;

                case 6:
                    int altura = bst.calcularAltura();
                    System.out.println("A altura atual da arvore BST e: " + altura);
                    break;

                case 7:
                    System.out.print("Informe o nome do arquivo para salvar (ex: saida.csv): ");
                    String arquivoSaida = scanner.nextLine().trim();
                    bst.salvarEmArquivo(arquivoSaida);
                    System.out.println("Dados salvos com sucesso em " + arquivoSaida);
                    break;

                case 8:
                    System.out.println("Encerrando...");
                    bst = null;
                    break;

                default:
                    System.out.println("Opcao invalida.");
            }
        }
        scanner.close();
    }

    private static void lerArquivoCSV(String nomeArquivo, BST bst) {
        String linha = "";
        int linhasLidas = 0;
        int linhasValidas = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            linha = br.readLine(); 
            
            while ((linha = br.readLine()) != null) {
                linhasLidas++;
                
                // IMPORTANTE -> Linha abaixo ignora as vírgulas que estão dentro de textos com aspas
                String[] colunas = linha.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                if (colunas.length != 15) {
                    continue; 
                }

                // IMPORTANTE -> Linha abaixo preenche temporadas vazias de filmes com "0" para não serem descartados
                String tipo = colunas[2].replace("\"", "").trim();
                if (tipo.equalsIgnoreCase("MOVIE") && colunas[9].trim().isEmpty()) {
                    colunas[9] = "0"; 
                }

                boolean linhaValida = true;
                for (String coluna : colunas) {
                    if (coluna == null || coluna.trim().isEmpty()) {
                        linhaValida = false;
                        break;
                    }
                }

                if (linhaValida) {
                    try {
                        String id = colunas[0].replace("\"", "").trim();
                        String title = colunas[1].replace("\"", "").trim();
                        String type = colunas[2].replace("\"", "").trim();
                        String description = colunas[3].replace("\"", "").trim();
                        int releaseYear = Integer.parseInt(colunas[4].trim());
                        String ageCert = colunas[5].replace("\"", "").trim();
                        int runtime = (int) Double.parseDouble(colunas[6].trim());                    
                        String genres = colunas[7].replace("\"", "").trim();
                        String prodCountries = colunas[8].replace("\"", "").trim();
                        double seasons = Double.parseDouble(colunas[9].trim());
                        String imdbId = colunas[10].replace("\"", "").trim();
                        double imdbScore = Double.parseDouble(colunas[11].trim());
                        double imdbVotes = Double.parseDouble(colunas[12].trim());
                        double tmdbPop = Double.parseDouble(colunas[13].trim());
                        double tmdbScore = Double.parseDouble(colunas[14].trim());

                        ProgramaNetFlix programa = new ProgramaNetFlix(
                            id, title, type, description, releaseYear, ageCert, runtime, 
                            genres, prodCountries, seasons, imdbId, imdbScore, imdbVotes, tmdbPop, tmdbScore
                        );

                        bst.inserir(programa);
                        linhasValidas++;
                        
                    } catch (NumberFormatException e) {}
                }
            }
            System.out.println("Leitura concluida!");
            System.out.println("Linhas processadas: " + linhasLidas);
            System.out.println("Programas inseridos com sucesso na BST: " + linhasValidas);
            
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}