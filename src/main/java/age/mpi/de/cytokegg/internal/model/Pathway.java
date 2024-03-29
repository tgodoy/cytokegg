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
package age.mpi.de.cytokegg.internal.model;

import org.cytoscape.model.CyNetwork;


public class Pathway {

	private String name, title, dataSet;
	//private CSPathwayMapper mapper;
	private CyNetwork network;
	
	public Pathway(String name, String title, String dataSet, /*CSPathwayMapper mapper,*/ CyNetwork network) {
		this.name = name;
		this.title = title;
		//this.mapper = mapper;
		this.network = network;
		this.dataSet = dataSet;
	}

	public String getDataSet() {
		return dataSet;
	}

	public void setDataSet(String dataSet) {
		this.dataSet = dataSet;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	/*public CSPathwayMapper getMapper() {
		return mapper;
	}
	
	public void setMapper(CSPathwayMapper mapper) {
		this.mapper = mapper;
	}*/
	
	public CyNetwork getNetwork() {
		return network;
	}
	
	public void setNetwork(CyNetwork network) {
		this.network = network;
	}
}
