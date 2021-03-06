/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package de.jaxenter.eesummit.caroline.gui.beans.employee;


import de.jaxenter.eesummit.caroline.backend.api.CustomerService;
import de.jaxenter.eesummit.caroline.entities.Customer;
import de.jaxenter.eesummit.caroline.gui.viewconfig.EmployeePages;
import org.apache.myfaces.extensions.cdi.core.api.config.view.ViewConfig;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.WindowScoped;
import org.apache.myfaces.extensions.cdi.jsf.api.Jsf;
import org.apache.myfaces.extensions.cdi.message.api.MessageContext;
import org.apache.myfaces.extensions.cdi.message.api.payload.MessageSeverity;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * @author <a href="mailto:struberg@yahoo.de">Mark Struberg</a>
 */
@WindowScoped
@Named("customerSearch")
public class SearchCustomerView implements Serializable
{

    private @Inject CustomerService custSvc;
    private @Inject @Jsf MessageContext messageContext;

    private List<Customer> customers;

    private String firstName;
    private String lastName;

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public List<Customer> getCustomers()
    {
        return customers;
    }

    public void setCustomers(List<Customer> customers)
    {
        this.customers = customers;
    }

    /**
     * Typesafe navigation sample for searching customers.
     */
    public Class<? extends ViewConfig> searchCustomer()
    {
        customers = custSvc.searchCustomers(lastName, firstName);

        if (customers == null || customers.size() == 0)
        {
            messageContext.message().text("{nothing_found}").
                    payload(MessageSeverity.INFO).add();
        }

        return null;
    }

    /**
     * Typesafe navigation sample for searching customers.
     */
    public Class<? extends ViewConfig> createCustomer()
    {
        return EmployeePages.EditCustomer.class;
    }

}

