/*
 * Copyright (C) 2011-2012 Jos� Mar�a Villaveces Max Planck institute for
 * biology of ageing (MPI-age)
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