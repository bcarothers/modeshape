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
package org.jboss.dna.graph.property;

import java.util.Calendar;
import org.jboss.dna.graph.property.ValueComparators;

/**
 * @author Randall Hauch
 */
public class DateValueComparatorTest extends AbstractValueComparatorsTest<Calendar> {

    private static final Calendar TODAY = Calendar.getInstance();
    private static final Calendar YESTERDAY;
    private static final Calendar TOMORROW;

    static {
        YESTERDAY = (Calendar)TODAY.clone();
        YESTERDAY.roll(Calendar.DAY_OF_YEAR, false);

        TOMORROW = (Calendar)TODAY.clone();
        TOMORROW.roll(Calendar.DAY_OF_YEAR, true);
    }

    public DateValueComparatorTest() {
        super(ValueComparators.CALENDAR_COMPARATOR, TODAY, TOMORROW, YESTERDAY);
    }
}