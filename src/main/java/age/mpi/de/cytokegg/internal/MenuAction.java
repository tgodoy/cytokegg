/**
 * Copyright 2013 Jos� Mar�a Villaveces Max Planck institute for biology of
 * ageing (MPI-age)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package age.mpi.de.cytokegg.internal;

import java.awt.event.ActionEvent;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.AbstractCyAction;
import org.cytoscape.work.TaskIterator;

import age.mpi.de.cytokegg.internal.task.NetworkCreationTask;


/**
 * Creates a new menu item under Apps menu section.
 *
 */
public class MenuAction extends AbstractCyAction {

	
	public MenuAction(CyApplicationManager cyApplicationManager , final String menuTitle) {
		
		super(menuTitle, cyApplicationManager, null, null);
		setPreferredMenu("Apps.CytoKegg");
		
	}

	public void actionPerformed(ActionEvent e) {
		
		
			TaskIterator ti = new TaskIterator(new NetworkCreationTask("/Users/jvillaveces/Downloads/hsa04210.xml", false));
			//TaskIterator ti = new TaskIterator(new NetworkCreationTask(new URL("http://www.kegg.jp/kegg-bin/download?entry=hsa04210&format=kgml")));
			CKController.getInstance().getDialogTaskManager().execute(ti);
		
	}
}
