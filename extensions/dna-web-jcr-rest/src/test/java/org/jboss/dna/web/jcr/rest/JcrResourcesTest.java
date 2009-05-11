/*
 * JBoss DNA (http://www.jboss.org/dna)
 * See the COPYRIGHT.txt file distributed with this work for information
 * regarding copyright ownership.  Some portions may be licensed
 * to Red Hat, Inc. under one or more contributor license agreements.
 * See the AUTHORS.txt file in the distribution for a full listing of 
 * individual contributors. 
 *
 * JBoss DNA is free software. Unless otherwise indicated, all code in JBoss DNA
 * is licensed to you under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * JBoss DNA is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.dna.web.jcr.rest;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.junit.Ignore;
import org.junit.Test;

public class JcrResourcesTest {

    private static final String SERVER_URL = "http://localhost:8080/resources";
    
    private ClientRequest request;
    private ClientResponse<?> response;
    
    private ClientRequest requestFor(String path) {
        return new ClientRequest(SERVER_URL + path);
    }
    
    @Ignore
    @Test
    public void shouldServeContentAtRoot() throws Exception {
        request = requestFor("/");
        
        response = request.get();
        
        assertThat(response.getStatus(), is(200));
        
    }

    @Test
    public void shouldServeListOfRepositories() throws Exception {
        request = requestFor("/repositories");
        
        response = request.get();
        
        assertThat(response.getStatus(), is(200));
        
    }

    @Test
    public void shouldServeListOfWorkspaces() throws Exception {
        String validRepositoryName = "foo"; // Stub this for now
        request = requestFor("/" + validRepositoryName + "/workspaces");
        
        response = request.get();

        assertThat(response.getStatus(), is(200));
    }

}