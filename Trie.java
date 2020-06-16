import java.util.HashMap;

public class Trie {
  private TrieNode root;
  
  public Trie() {
    root = new TrieNode();
  }
  
  public void insert(String word) {
    TrieNode current = root;
    
    for(int i = 0; i < word.length(); ++i) {
      Character c = word.charAt(i);
      
      if (current.getChildren().containsKey(c)) {
        current = current.getChildren().get(c);
      } else {
        TrieNode newNode = new TrieNode(c);
        current.getChildren().put(c, newNode);
        current = current.getChildren().get(c);
      }
      
    }
    
    current.setLeaf(true);
  }
  
  
  
  
  
  public class TrieNode {
    private char c;
    private HashMap<Character, TrieNode> children = new HashMap<>();
    private boolean isLeaf;

    public TrieNode() {}

    public TrieNode(char c){
        this.c = c;
    }

    public HashMap<Character, TrieNode> getChildren() {
        return children;
    }

    public void setChildren(HashMap<Character, TrieNode> children) {
        this.children = children;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }
}
}
