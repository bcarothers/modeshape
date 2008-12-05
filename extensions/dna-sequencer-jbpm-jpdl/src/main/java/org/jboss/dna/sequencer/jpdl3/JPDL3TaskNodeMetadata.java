/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2008, Red Hat Middleware LLC, and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors. 
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.dna.sequencer.jpdl3;

import java.util.ArrayList;
import java.util.List;

/**
 * This a task node.
 * 
 * @author Serge Pagop.
 */
public class JPDL3TaskNodeMetadata {

    /**
     * The name.
     */
    private String name = "";

    /**
     * The tasks.
     */
    private List<JPDL3TaskMetadata> tasks = new ArrayList<JPDL3TaskMetadata>();

    /**
     * The transition.
     */
    private List<JPDL3TransitionMetadata> transitions = new ArrayList<JPDL3TransitionMetadata>();

    /**
     * @param name
     */
    public void setName( String name ) {
        this.name = name;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param tasks
     */
    public void setTasks( List<JPDL3TaskMetadata> tasks ) {
        this.tasks = tasks;
    }

    /**
     * @return tasks
     */
    public List<JPDL3TaskMetadata> getTasks() {
        return tasks;
    }

    /**
     * @param transitions
     */
    public void setTransitions( List<JPDL3TransitionMetadata> transitions ) {
        this.transitions = transitions;
    }

    /**
     * @return transitions
     */
    public List<JPDL3TransitionMetadata> getTransitions() {
        return transitions;
    }

}