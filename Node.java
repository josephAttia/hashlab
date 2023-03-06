public class Node {

    private Node  right;
    private Node left;
    private Comparable value;


    public Node(Comparable  data) {
        this.value = data;
        left = null;
        right = null;
    }

    public Node(Comparable data, Node l, Node r){
        value = data;
        left = l;
        right = r;
    }

    public Node getLeft(){
        return left;
    }

    public Node getRight(){
        return right;
    }

    public Comparable getValue(){
        return value;
    }


    public Node getNode(){
        return new Node(value);
    }

    public void setRight(Node value){
        this.right = value;
    }

    public void setLeft(Node value){
        this.left = value;
    }

    public void setValue(Comparable hashCode){
        this.value = hashCode;
    }
}
