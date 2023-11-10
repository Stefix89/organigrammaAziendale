package ui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import controller.ControllerOrganigramma;
import organigramma_aziendale.Dipartimento;
import organigramma_aziendale.GruppoDiLavoro;
import organigramma_aziendale.UnitaOrganizzativa;

public class OrganigrammaTree extends JPanel {
	
	//controller
	private ControllerOrganigramma controller = ControllerOrganigramma.getInstance();
	
	//riferimenti ai componenti del sistema
	private CreateDipartimento createDipartimento;
	private RemoveDipartimento removeDipartimento;
	private CreateGruppo createGruppo;
	private RemoveGruppo removeGruppo;
	private CreateTree createTree;
	
	//radice
	private DefaultMutableTreeNode root;
	private DefaultTreeModel treeModel;
	private JTree tree;
	
	public OrganigrammaTree(CreateDipartimento c, RemoveDipartimento r, CreateGruppo cg, RemoveGruppo rg, CreateTree ct) {
		
		createDipartimento = c;
		removeDipartimento = r;
		createGruppo = cg;
		removeGruppo = rg;
		createTree = ct;
		
		controller.setTree(this);
		
		root = new DefaultMutableTreeNode("Organigramma");
        treeModel = new DefaultTreeModel(root);
        tree = new JTree(treeModel);
        JScrollPane scrollPane = new JScrollPane(tree);
        add(scrollPane);
		
		createDipartimento.setTextistener(new TextListener() {

			@Override
			public void testoEmesso(String nome, String nomeSovrastante) {
                DefaultMutableTreeNode child = new DefaultMutableTreeNode(nome);
                treeModel.insertNodeInto(child, root, root.getChildCount());
                treeModel.reload();
			}
		});
		
		removeDipartimento.setTextistener(new TextListener() {

			@Override
			public void testoEmesso(String nome, String nomeSovrastante) {
				DefaultMutableTreeNode nodoDaEliminare = findNodeByName(nome);
				treeModel.removeNodeFromParent(nodoDaEliminare);
				treeModel.reload();
			}
		});
		
		createGruppo.setTextistener(new TextListener() {

			@Override
			public void testoEmesso(String nome, String nomeSovrastante) {
				DefaultMutableTreeNode nodoSovra = findNodeByName(nomeSovrastante);
				 DefaultMutableTreeNode child = new DefaultMutableTreeNode(nome);
				treeModel.insertNodeInto(child, nodoSovra, nodoSovra.getChildCount());
                treeModel.reload();
				
			}
		});
		
		removeGruppo.setTextistener(new TextListener() {

			@Override
			public void testoEmesso(String nome, String nomeSovrastante) {
				DefaultMutableTreeNode nodoDaEliminare = findNodeByName(nome);
				treeModel.removeNodeFromParent(nodoDaEliminare);
				treeModel.reload();
			}
		});
		
		createTree.setTextistener(new TextListener() {

			@Override
			public void testoEmesso(String nome, String nomeSovrastante) {
				 root = new DefaultMutableTreeNode(nome);
	                treeModel.setRoot(root);
	                treeModel.reload();
			}
		});

	}
	
	 private DefaultMutableTreeNode findNodeByName(String name) {
	        return findNodeByName(root, name);
	    }

	    private DefaultMutableTreeNode findNodeByName(DefaultMutableTreeNode node, String name) {
	        if (node == null || node.getUserObject().toString().equals(name)) {
	            return node;
	        }
	        
	        for (int i = 0; i < node.getChildCount(); i++) {
	            DefaultMutableTreeNode child = (DefaultMutableTreeNode) node.getChildAt(i);
	            DefaultMutableTreeNode foundNode = findNodeByName(child, name);
	            if (foundNode != null) {
	                return foundNode;
	            }
	        }
	        
	        return null; // Il nodo non è stato trovato
	    }

	    public void clear() {
	    	if(root != null) {
	    		root.removeAllChildren();
	    		root = new DefaultMutableTreeNode("Nuova radice");
	    		treeModel.reload();
	    	}
	    }
	    
	    public void riceviDaFile(UnitaOrganizzativa[] unita) {
	    	for(int i = 0; i < unita.length; i++) {
	    		if(unita[i].getNome().equalsIgnoreCase("Consiglio d'amministrazione")) {
	    			 root = new DefaultMutableTreeNode(unita[i].getNome());
		             treeModel.setRoot(root);
	    		}
	    	}
	    	for(int i = 0; i < unita.length; i++) {
	    		if(unita[i] instanceof Dipartimento) {
	    			DefaultMutableTreeNode child = new DefaultMutableTreeNode(unita[i].getNome());
	                treeModel.insertNodeInto(child, root, root.getChildCount());
	    		}
	    	}
	    	for(int i = 0; i < unita.length; i++) {
	    		if(unita[i] instanceof GruppoDiLavoro) {
	    			GruppoDiLavoro g = (GruppoDiLavoro)unita[i];
	    			DefaultMutableTreeNode nodoSovra = findNodeByName(g.getPadre().getNome());
					 DefaultMutableTreeNode child = new DefaultMutableTreeNode(g.getNome());
					treeModel.insertNodeInto(child, nodoSovra, nodoSovra.getChildCount());
	    		}
	    	}
	    	
	    	treeModel.reload();
	    	
	    }

}
