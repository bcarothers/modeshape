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
package org.jboss.dna.search;

import java.io.IOException;
import java.util.List;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.jboss.dna.graph.ExecutionContext;
import org.jboss.dna.graph.Location;
import org.jboss.dna.graph.Node;
import org.jboss.dna.graph.connector.RepositorySource;
import org.jboss.dna.graph.property.Path;
import org.jboss.dna.graph.query.QueryContext;
import org.jboss.dna.graph.query.QueryResults;
import org.jboss.dna.graph.query.model.QueryCommand;
import org.jboss.dna.graph.query.validate.Schemata;
import org.jboss.dna.graph.request.ChangeRequest;

/**
 * A stateful session that maintains {@link IndexReader}, {@link IndexWriter} and {@link IndexSearcher} resources to the indexes
 * of a particular source and workspace.
 */
public interface IndexSession {

    /**
     * Get the name of the {@link RepositorySource repository source} for which this session exists. A session instance will
     * always return the same name.
     * 
     * @return the source name; never null
     */
    String getSourceName();

    /**
     * Get the name of the workspace for which this session exists. A session instance will always return the same name.
     * 
     * @return the workspace name; never null
     */
    String getWorkspaceName();

    /**
     * Get the execution context in which this session is operating.
     * 
     * @return the execution context; never null
     */
    ExecutionContext getContext();

    /**
     * Return whether this session made changes to the indexed state.
     * 
     * @return true if change were made, or false otherwise
     */
    boolean hasChanges();

    /**
     * Perform a full-text search given the supplied query.
     * 
     * @param context the context in which the search should be executed; may not be null
     * @param fullTextString the full-text query; never null or blank
     * @param maxResults the maximum number of results that are to be returned; always positive
     * @param offset the number of initial results to skip, or 0 if the first results are to be returned
     * @param results the list where the results should be accumulated; never null
     * @throws IOException if there is a problem indexing or using the indexes
     * @throws ParseException if there is a problem parsing the query
     */
    void search( ExecutionContext context,
                 String fullTextString,
                 int maxResults,
                 int offset,
                 List<Location> results ) throws IOException, ParseException;

    /**
     * Perform a query of the content. The {@link QueryCommand query} is supplied in the form of the Abstract Query Model, with
     * the {@link Schemata} that defines the tables and views that are available to the query, and the set of index readers (and
     * writers) that should be used.
     * 
     * @param queryContext the context in which the query should be executed; may not be null
     * @param query the query; never null
     * @return the results of the query; never null
     * @throws IOException if there is a problem indexing or using the indexes
     * @throws ParseException if there is a problem parsing the query
     */
    QueryResults query( QueryContext queryContext,
                        QueryCommand query ) throws IOException, ParseException;

    /**
     * Index the node given the index writers. Note that implementors should simply just use the writers to add documents to the
     * index(es), and should never call any of the writer lifecycle methods (e.g., {@link IndexWriter#commit()},
     * {@link IndexWriter#rollback()}, etc.).
     * 
     * @param node the node to be indexed; never null
     * @throws IOException if there is a problem indexing or using the writers
     */
    void index( Node node ) throws IOException;

    /**
     * Update the indexes to reflect the supplied changes to the graph content. Note that implementors should simply just use the
     * writers to add documents to the index(es), and should never call any of the writer lifecycle methods (e.g.,
     * {@link IndexWriter#commit()}, {@link IndexWriter#rollback()}, etc.).
     * 
     * @param changes the set of changes to the content
     * @return the (approximate) number of nodes that were affected by the changes
     * @throws IOException if there is a problem indexing or using the writers
     */
    int apply( Iterable<ChangeRequest> changes ) throws IOException;

    /**
     * Remove from the index(es) all of the information pertaining to the nodes at or below the supplied path. Note that
     * implementors should simply just use the writers to add documents to the index(es), and should never call any of the writer
     * lifecycle methods (e.g., {@link IndexWriter#commit()}, {@link IndexWriter#rollback()}, etc.).
     * 
     * @param path the path identifying the graph content that is to be removed; never null
     * @return the (approximate) number of nodes that were affected by the changes
     * @throws IOException if there is a problem indexing or using the writers
     */
    int deleteBelow( Path path ) throws IOException;

    /**
     * Optimize the indexes, if required.
     * 
     * @throws IOException if there is a problem optimizing
     */
    void optimize() throws IOException;

    /**
     * Close this session by committing all of the changes. This session is no longer usable after this method is called.
     * 
     * @throws IOException if there is a problem committing
     */
    void commit() throws IOException;

    /**
     * Close this session by rolling back all of the changes that have been made. This session is no longer usable after this
     * method is called.
     * 
     * @throws IOException if there is a problem rolling back
     */
    void rollback() throws IOException;
}