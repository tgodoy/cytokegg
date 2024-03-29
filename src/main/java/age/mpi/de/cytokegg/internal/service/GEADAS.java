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
package age.mpi.de.cytokegg.internal.service;

import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.JDOMException;

import age.mpi.de.cytokegg.internal.util.ParameterNameValue;

public class GEADAS extends XMLService {
	
	private final String BASE_URL = "http://www.ebi.ac.uk/gxa/das/s4/features";
	
	public GEADAS(){
		super();
	}
	
	public Document getSummary(String accession) throws JDOMException, IOException{
		ParameterNameValue[] params = new ParameterNameValue[]{new ParameterNameValue("segment", accession)};
        return getDocument(buildURL(BASE_URL, params));
	}	
}
