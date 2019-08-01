/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.propertyEditor
 * @FileName: ReplaceSpaceEditor.java
 * @Author: Avishek Das
 * @CreatedDate: 19-07-2019
 * @Modified_By avishek.das @Last_On 19-Jul-2019 9:12:24 PM
 */

package com.aashayein.employee.propertyEditor;

import java.beans.PropertyEditorSupport;

public class ReplaceSpaceEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String field) throws IllegalArgumentException {
		field = field.trim().replaceAll("\\s{2,}", " ");
		setValue(field);
	}
}
