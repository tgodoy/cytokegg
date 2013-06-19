/*
 * Copyright (C) 2011-2012 Jos� Mar�a Villaveces Max Plank institute for biology
 * of ageing (MPI-age)
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.cytoscape.cytokegg.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.queryParser.ParseException;
import org.cytoscape.cytokegg.Plugin;
import org.cytoscape.cytokegg.Repository;
import org.cytoscape.cytokegg.task.PaintPathwayTask;
import org.cytoscape.cytokegg.ui.widget.AutoHighlightTextField;
import org.cytoscape.cytokegg.util.Item;

import cytoscape.task.Task;
import cytoscape.task.ui.JTaskConfig;
import cytoscape.task.util.TaskManager;

public class BrowsePathwayDialog extends JDialog{
	
	private Item[] pathways, orgs;
	private JList list;
	private JComboBox cBox;
	private DefaultComboBoxModel cBoxModel;
	private JButton select;
	private AutoHighlightTextField searchField;
	
	public BrowsePathwayDialog(JFrame owner){
    	super(owner, "File Import", true);
    	this.setSize(new Dimension(400, 400));
    	
		try {
			orgs = Repository.getInstance().getIndexedOrganisms();
		} catch (CorruptIndexException e2) {
			e2.printStackTrace();
		} catch (ParseException e2) {
			e2.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new TitledBorder("Browse Pathways"));
		
		//select button
		select = new JButton("Select");
		select.setEnabled(false);
		select.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Item i = (Item)list.getSelectedValue();
				String pathId = i.getId().substring(i.getId().lastIndexOf(":")+1,i.getId().length());
				
				Task task = new PaintPathwayTask(pathId);
		        JTaskConfig config = new JTaskConfig();
		        config.displayCancelButton(false);
		        config.displayStatus(true);
		        config.displayTimeElapsed(true);
		        
		        boolean success = TaskManager.executeTask(task, config);
		        if(success){
		        	setVisible(false);
		        	try {
						Plugin.getInstance().loadDataSet("");
						GUIManager.getInstance().setSidePanel();
					} catch (CorruptIndexException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
		        }
			}
			
		});
		
		//list
		list = new JList();
		list.setCellRenderer(new ItemRenderer());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.clearSelection();
		list.addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				select.setEnabled(true);
			}
		});
		
		//Combo model
		cBoxModel = new DefaultComboBoxModel(orgs);
		
		//Combo box
		cBox = new JComboBox(cBoxModel);
		cBox.setRenderer(new ItemRenderer());
		cBox.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					updateList();
				} catch (ParseException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
		});
		
		//Search Field
        searchField = new AutoHighlightTextField(20);
        searchField.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					updateList();
				} catch (ParseException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
			}
        	
        });
        
        {
        	JPanel aux = new JPanel();
        	aux.add(cBox);
        	aux.add(searchField);
        	panel.add(aux, BorderLayout.NORTH);
        }
        
        {
        	JPanel aux = new JPanel();
        	aux.add(select);
        	panel.add(aux, BorderLayout.SOUTH);
        }
        
        panel.add(new JScrollPane(list), BorderLayout.CENTER);
        setContentPane(panel);
	}
	
	public void setVisible(boolean bool){
		if(bool){
			try {
				orgs = Repository.getInstance().getIndexedOrganisms();
				
				//Combo model
				cBoxModel = new DefaultComboBoxModel(orgs);
				cBox.setModel(cBoxModel);
				updateList();
				
			} catch (CorruptIndexException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		super.setVisible(bool);
	}
	
	private void updateList() throws ParseException, IOException{
		String query = searchField.getText();
		Item item = (Item) cBox.getSelectedItem();
		
		if(item != null){
		
			if(query.length()<3)
				this.pathways = Repository.getInstance().getPathwaysByOrganism(item.getId());
			else
				this.pathways = Repository.getInstance().getPathwaysByOrganismAndText(item.getId(), "*"+query+"*");
			
			DefaultListModel model = new DefaultListModel();
			for(int i=0; i<pathways.length; i++){
				model.addElement(pathways[i]); 
			}
			list.setModel(model);
		}
		list.clearSelection();
		select.setEnabled(false);
	}
}