/*Gabriel Gomes dos Santos - 10748012, Kauê Trigolo Damaceno 10748319,  Henrique Orlando Rosa da Silva – 10753777  */

package src;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BST {
    private Node root;

    public BST() {
        this.root = null;
    }

    public void inserir(ProgramaNetFlix programa) {
        root = inserirRecursivo(root, programa.getId(), programa);
    }

    private Node inserirRecursivo(Node root, String id, ProgramaNetFlix programa) {
        // árvore vazia = novo nó
        if (root == null) {
            return new Node(id, programa);
        }

        if (id.compareTo(root.id) < 0) {
            root.left = inserirRecursivo(root.left, id, programa);
        } 
        else if (id.compareTo(root.id) > 0) {
            root.right = inserirRecursivo(root.right, id, programa);
        }
        
        return root;
    }

    public void emOrdem(Node node) {
        if (node != null) {
            emOrdem(node.left);  // 1. Desce tudo para a ESQUERDA
            
            System.out.println(node.programa.getTitle()); 
            
            emOrdem(node.right); // 3. Desce tudo para a DIREITA
        }
    }

    // Opção 2 - Analises de Dados

    // 2.1 - Top 10 Filmes de Drama da última década - Percurso Em-Ordem
    public void analiseTop10FilmesDrama() {
        List<ProgramaNetFlix> filtrados = new ArrayList<>();
        percursoEmOrdemFiltro1(root, filtrados);

        // Ordena a lista filtrada pelo IMDB Score em ordem decrescente
        filtrados.sort(Comparator.comparingDouble(ProgramaNetFlix::getImdbScore).reversed());

        System.out.println("\n--- Top 10 Filmes de Drama (Ultima decada) ---");
        int limite = Math.min(10, filtrados.size());
        for (int i = 0; i < limite; i++) {
            ProgramaNetFlix p = filtrados.get(i);
            System.out.println((i+1) + ". " + p.getTitle() + " (" + p.getReleaseYear() + ") - IMDB: " + p.getImdbScore());
        }
    }
    private void percursoEmOrdemFiltro1(Node node, List<ProgramaNetFlix> lista) {
        if (node != null) {
            percursoEmOrdemFiltro1(node.left, lista); // Esquerda
            
            ProgramaNetFlix p = node.programa;
            // Filtro: Tipo = MOVIE, Gênero contém 'drama', Ano >= 2016
            if (p.getType().equalsIgnoreCase("MOVIE") && 
                p.getGenres().toLowerCase().contains("drama") && 
                p.getReleaseYear() >= 2015) {
                lista.add(p);
            }

            percursoEmOrdemFiltro1(node.right, lista); // Direita
        }
    }

    // 2.2 - Top 10 Séries Mais Populares do Século 20 - Percurso Pré-ordem
    public void analiseTop10SeriesSeculo20() {
        List<ProgramaNetFlix> filtrados = new ArrayList<>();
        percursoPreOrdemFiltro2(root, filtrados);

        filtrados.sort(Comparator.comparingDouble(ProgramaNetFlix::getImdbVotes).reversed());

        System.out.println("\n--- Top 10 Series mais populares (Seculo XX) ---");
        int limite = Math.min(10, filtrados.size());
        for (int i = 0; i < limite; i++) {
            ProgramaNetFlix p = filtrados.get(i);
            System.out.println((i+1) + ". " + p.getTitle() + " (" + p.getReleaseYear() + ") - Votos: " + p.getImdbVotes());
        }
    }

    private void percursoPreOrdemFiltro2(Node node, List<ProgramaNetFlix> lista) {
        if (node != null) {
            ProgramaNetFlix p = node.programa;
            if (p.getType().equalsIgnoreCase("SHOW") && p.getReleaseYear() <= 2000) {
                lista.add(p);
            }
            percursoPreOrdemFiltro2(node.left, lista);
            percursoPreOrdemFiltro2(node.right, lista);
        }
    }

    // 2.3 - Top 5 Filmes Britânicos Mais Bem Avaliados - Percurso Pós-ordem
    public void analiseTop5FilmesBritanicos() {
        List<ProgramaNetFlix> filtrados = new ArrayList<>();
        percursoPosOrdemFiltro3(root, filtrados);

        filtrados.sort(Comparator.comparingDouble(ProgramaNetFlix::getImdbScore).reversed());

        System.out.println("\n--- Top 5 Filmes Britanicos de Maior Sucesso ---");
        int limite = Math.min(5, filtrados.size());
        for (int i = 0; i < limite; i++) {
            ProgramaNetFlix p = filtrados.get(i);
            System.out.println((i+1) + ". " + p.getTitle() + " (" + p.getReleaseYear() + ") - IMDB Score: " + p.getImdbScore());
        }
    }

    private void percursoPosOrdemFiltro3(Node node, List<ProgramaNetFlix> lista) {
        if (node != null) {
            percursoPosOrdemFiltro3(node.left, lista);
            percursoPosOrdemFiltro3(node.right, lista);
            
            ProgramaNetFlix p = node.programa;
            if (p.getType().equalsIgnoreCase("MOVIE") && p.getProductionCountries().contains("GB")) {
                lista.add(p);
            }
        }
    }

    // 2.4 - Top 5 Séries Americanas Mais Longas do Século 21 - Percurso Pré-ordem
    public void analiseTop5SeriesAmericanasLongas() {
        List<ProgramaNetFlix> filtrados = new ArrayList<>();
        percursoPreOrdemFiltro4(root, filtrados); // Reutilizamos a arquitetura Pré-ordem

        filtrados.sort(Comparator.comparingDouble(ProgramaNetFlix::getSeasons).reversed());

        System.out.println("\n--- Top 5 Series Americanas Mais Longas (Seculo XXI) ---");
        int limite = Math.min(5, filtrados.size());
        for (int i = 0; i < limite; i++) {
            ProgramaNetFlix p = filtrados.get(i);
            System.out.println((i+1) + ". " + p.getTitle() + " - " + (int)p.getSeasons() + " temporadas (IMDB: " + p.getImdbScore() + ")");
        }
    }

    private void percursoPreOrdemFiltro4(Node node, List<ProgramaNetFlix> lista) {
        if (node != null) {
            ProgramaNetFlix p = node.programa;
            if (p.getType().equalsIgnoreCase("SHOW") && p.getReleaseYear() >= 2001 && p.getProductionCountries().contains("US")) {
                lista.add(p);
            }
            percursoPreOrdemFiltro4(node.left, lista);
            percursoPreOrdemFiltro4(node.right, lista);
        }
    }

    // 2.5 - Análise: Top 5 Séries Não-Americanas Mais Curtas do Século 21 - Percurso Em-ordem
    public void analiseTop5SeriesNaoAmericanasCurtas() {
        List<ProgramaNetFlix> filtrados = new ArrayList<>();
        percursoEmOrdemFiltro5(root, filtrados); // Reutilizando a arquitetura Em-ordem

        // Ordenando em ordem CRESCENTE (sem o .reversed())
        filtrados.sort(Comparator.comparingDouble(ProgramaNetFlix::getSeasons));

        System.out.println("\n--- Top 5 Series Nao-Americanas Mais Curtas (Seculo XXI) ---");
        int limite = Math.min(5, filtrados.size());
        for (int i = 0; i < limite; i++) {
            ProgramaNetFlix p = filtrados.get(i);
            System.out.println((i+1) + ". " + p.getTitle() + " [" + p.getProductionCountries() + "] - " + (int)p.getSeasons() + " temporadas (IMDB: " + p.getImdbScore() + ")");
        }
    }

    private void percursoEmOrdemFiltro5(Node node, List<ProgramaNetFlix> lista) {
        if (node != null) {
            percursoEmOrdemFiltro5(node.left, lista);
            
            ProgramaNetFlix p = node.programa;
            if (p.getType().equalsIgnoreCase("SHOW") && p.getReleaseYear() >= 2001 && !p.getProductionCountries().contains("US")) {
                // Remoção de programas com 0 temporadas (erros de dataset) para ser realista
                if (p.getSeasons() > 0) {
                    lista.add(p);
                }
            }

            percursoEmOrdemFiltro5(node.right, lista);
        }
    }

    // Opção 4: Buscar Programa contabilizando comparações
    public void buscarEContabilizar(String idProcurado) {
        long startTime = System.nanoTime();
        int comparacoes = 0;
        Node atual = root;
        boolean encontrado = false;

        while (atual != null) {
            comparacoes++;
            int comp = idProcurado.compareTo(atual.id);
            if (comp == 0) {
                encontrado = true;
                break;
            } else if (comp < 0) {
                atual = atual.left;
            } else {
                atual = atual.right;
            }
        }
        
        long endTime = System.nanoTime();
        long duracao = endTime - startTime;

        if (encontrado) {
            System.out.println("Titulo: " + atual.programa.getTitle());
        } else {
            System.out.println("Programa nao encontrado.");
        }
        System.out.println("Comparacoes: " + comparacoes);
        System.out.println("Tempo de execucao (ns): " + duracao);
    }

    // Opção 5: Remoção de filme ou serie
    public void remover(String id) {
        root = removerRecursivo(root, id);
    }

    private Node removerRecursivo(Node root, String id) {
        if (root == null) return root;

        // Navega pela árvore para encontrar o nó
        if (id.compareTo(root.id) < 0) {
            root.left = removerRecursivo(root.left, id);
        } else if (id.compareTo(root.id) > 0) {
            root.right = removerRecursivo(root.right, id);
        } else {            
            // Caso 1 e 2: Nó com apenas um filho ou nenhum filho
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;

            // Caso 3: Nó com dois filhos
            // Busca o menor valor na subárvore da direita para substituir
            Node minNode = encontrarMin(root.right);
            root.id = minNode.id;
            root.programa = minNode.programa;
            
            // Remove o nó duplicado lá embaixo
            root.right = removerRecursivo(root.right, root.id);
        }
        return root;
    }

    private Node encontrarMin(Node root) {
        Node minv = root;
        while (minv.left != null) {
            minv = minv.left;
        }
        return minv;
    }
  
    // Opção 6: Exibir a Altura da Árvore
    public int calcularAltura() {
        return alturaRecursiva(root);
    }

    private int alturaRecursiva(Node node) {
        if (node == null) return -1;
        int altEsq = alturaRecursiva(node.left);
        int altDir = alturaRecursiva(node.right);
        return Math.max(altEsq, altDir) + 1;
    }

    // Opção 7: Salvar
    public void salvarEmArquivo(String nomeArquivo) {
        try (java.io.PrintWriter writer = new java.io.PrintWriter(new java.io.FileWriter(nomeArquivo))) {
            // Escreve o cabeçalho original do CSV
            writer.println("id,title,type,description,release_year,age_certification,runtime,genres,production_countries,seasons,imdb_id,imdb_score,imdb_votes,tmdb_popularity,tmdb_score");
            
            // Varre a árvore inteira e grava no arquivo
            salvarEmOrdem(root, writer);
            
        } catch (java.io.IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }

    private void salvarEmOrdem(Node node, java.io.PrintWriter writer) {
        if (node != null) {
            salvarEmOrdem(node.left, writer);
            
            ProgramaNetFlix p = node.programa;
            // Remonta a linha em formato CSV
            writer.println(p.getId() + "," + p.getTitle() + "," + p.getType() + "," + 
                           p.getDescription() + "," + p.getReleaseYear() + "," + 
                           p.getAgeCertification() + "," + p.getRuntime() + "," + 
                           p.getGenres() + "," + p.getProductionCountries() + "," + 
                           p.getSeasons() + "," + p.getImdbId() + "," + 
                           p.getImdbScore() + "," + p.getImdbVotes() + "," + 
                           p.getTmdbPopularity() + "," + p.getTmdbScore());
                           
            salvarEmOrdem(node.right, writer);
        }
    }
}
