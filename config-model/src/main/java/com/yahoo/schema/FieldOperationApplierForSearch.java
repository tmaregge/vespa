// Copyright Yahoo. Licensed under the terms of the Apache 2.0 license. See LICENSE in the project root.
package com.yahoo.schema;

import com.yahoo.document.Field;
import com.yahoo.schema.document.SDDocumentType;

/**
 * @author Einar M R Rosenvinge
 */
public class FieldOperationApplierForSearch extends FieldOperationApplier {

    @Override
    public void process(SDDocumentType sdoc) {
        //Do nothing
    }

    public void process(Schema schema) {
        for (Field field : schema.extraFieldList()) {
            apply(field);
        }
    }

}