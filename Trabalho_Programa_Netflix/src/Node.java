/*Gabriel Gomes dos Santos - 10748012, Kauê Trigolo Damaceno 10748319,  Henrique Orlando Rosa da Silva – 10753777  */

package src;

public class Node {
    String id; // A chave de busca
    ProgramaNetFlix programa; // Restante dos dados e tals
    Node left;
    Node right;

    public Node(String id, ProgramaNetFlix programa) {
        this.id = id;
        this.programa = programa;
        this.left = null;
        this.right = null;
    }
}