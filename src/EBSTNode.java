
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author PRO
 */
public class EBSTNode implements Comparable<EBSTNode> {

    public String E_word;
    EBSTNode left, right;
    ArrayList<String> V_word;

    public EBSTNode(String E_word, EBSTNode left, EBSTNode right, ArrayList<String> V_word) {
        this.E_word = E_word;
        this.left = left;
        this.right = right;
        this.V_word = V_word;
    }

    public EBSTNode(String E_word, ArrayList<String> V_word) {
        this.E_word = E_word;
        this.V_word = V_word;
        this.left = null;
        this.right = null;
    }

    @Override
    public int compareTo(EBSTNode o) {
        return this.E_word.compareTo(o.E_word);
    }

    @Override
    public String toString() {
        return E_word + ":" + getV_word(V_word);
    }
    public String getV_word(ArrayList V_word) {
        String result = "";
        for (int i = 0; i < V_word.size(); i++) {
            result = result + V_word.get(i);
            if(i<(V_word.size()-1)){
                result = result + ",";
            }
        }
        return result;
    }
}

class EBST {

    EBSTNode root;

    public EBST() {
        root = null;
    }

    public void visit(EBSTNode p) {
        if (p != null) {
            System.out.print(p.E_word + ": " + getV_word(p.V_word));
            System.out.println("");
        }
    }
    
    

    public String getV_word(ArrayList V_word) {
        String result = "";
        for (int i = 0; i < V_word.size(); i++) {
            result = result + V_word.get(i);
            if(i<(V_word.size()-1)){
                result = result + ",";
            }
        }
        return result;
    }

    public void Insert(EBSTNode el) {
        EBSTNode p = root;
        EBSTNode parent = null;
        while (p != null) {
            parent = p;
            if (el.E_word.compareTo(p.E_word) < 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (root == null) {
            root = new EBSTNode(el.E_word, el.V_word);
        } else {
            if (el.E_word.compareTo(parent.E_word) < 0) {
                parent.left = new EBSTNode(el.E_word, el.V_word);
            } else {
                parent.right = new EBSTNode(el.E_word, el.V_word);
            }
        }
    }

    public EBSTNode Search(String word) {
        EBSTNode p = root;
        while (p != null) {
            if (word.equals(p.E_word)) {
                return p;
            } else if (word.compareTo(p.E_word) < 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return null;
    }

    public void Preorder(EBSTNode p) {
        if (p != null) {
            visit(p);
            Preorder(p.left);
            Preorder(p.right);
        }
    }
    
    

    public void Preorder() {
        Preorder(root);
    }

    public void AddWord(String E_word, String V_word) {
        EBSTNode a = Search(E_word);
        int check = 0;
        if (a == null) {
            ArrayList arr = new ArrayList();
            arr.add(V_word);
            EBSTNode b = new EBSTNode(E_word, arr);
            Insert(b);
        } else {
            for (int i = 0; i < a.V_word.size(); i++) {
                if (V_word.equals(a.V_word.get(i).trim())) {
                    check++;
                }
            }
            if (check != 0) {
                System.out.println("da ton tai");
            } else {
                a.V_word.add(V_word);
            }
        }

    }

}

class run {

    String filenamel = "E-V.txt";
    String filename = "abc.txt";
    EBST E_V = new EBST();

    public void LoadFile() {
        try {
            FileReader f = new FileReader(filenamel);
            BufferedReader bf = new BufferedReader(f);
            String S;
            String s;
            String[] v= null;
            while ((S = bf.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(S, ":");
                String E_word = stk.nextToken().trim();  
                ArrayList arr = new ArrayList();
                v=stk.nextToken().trim().split(",");
                for(int i = 0; i<v.length; i++){
                    arr.add(v[i]);
                }
                EBSTNode E = new EBSTNode(E_word, arr);
                E_V.Insert(E);
            }
            
            E_V.Preorder();  
            E_V.AddWord("gold", "vang");
            
            E_V.Preorder();  
            bf.close();
            f.close();
            System.out.println(E_V.Search("find"));
            System.out.println(E_V.Search("gold"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    public String getV_word(ArrayList V_word) {
        String result = "";
        for (int i = 0; i < V_word.size(); i++) {
            result = result + V_word.get(i);
            if(i<(V_word.size()-1)){
                result = result + ",";
            }
        }
        return result;
    }
    public String visitToPrint(EBSTNode p){
        if(p!=null){
            String node = p.E_word + ":"  +getV_word(p.V_word) ;
            return node;
        }
        else
            return null;
    }
    public void PreorderToPrint(EBSTNode p, PrintWriter pw) {
        if (p != null) {
            pw.println(p.toString());
            
            PreorderToPrint(p.left, pw);
            PreorderToPrint(p.right, pw);
        }
    }
 
    public void SaveToFile(){
        try {
            PrintWriter pw = new PrintWriter(filename);
            PreorderToPrint(E_V.root, pw);
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
class Test {

    public static void main(String[] args) {
//        EBST E_V = new EBST();
//
//        ArrayList<String> a1 = new ArrayList();
//        a1.add("ai");
//        ArrayList<String> a2 = new ArrayList();
//        a2.add("biet");
//        ArrayList<String> a3 = new ArrayList();
//        a3.add("va");
//        a3.add("cung");
//
//        EBSTNode E1 = new EBSTNode("who", a1);
//        EBSTNode E2 = new EBSTNode("know", a2);
//        EBSTNode E3 = new EBSTNode("and", a3);
//        E_V.Insert(E1);
//        E_V.Insert(E2);
//        E_V.Insert(E3);
//        E_V.Preorder();
//        EBSTNode EF = E_V.Search("know");
//        System.out.println(EF.toString());
//        E_V.AddWord("and", "voi");
//        E_V.Preorder();
//        E_V.AddWord("gold", "vang");
//        E_V.Preorder();
//          E_V1.LoadFile();
        run r = new run();
        r.LoadFile();
        r.SaveToFile();
    }
}
