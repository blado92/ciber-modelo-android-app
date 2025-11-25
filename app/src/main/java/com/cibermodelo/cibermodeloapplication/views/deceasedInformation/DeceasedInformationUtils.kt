package com.cibermodelo.cibermodeloapplication.views.deceasedInformation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cibermodelo.base.model.Deceased
import com.cibermodelo.base.model.Field
import com.cibermodelo.base.model.FieldType

@Composable
fun GetFieldDeceasedComponent(
    deceased: Deceased,
    field: Field
) {
    when (field.id) {
        FieldType.DECEASED_ADDRESS -> DeceasedFieldComponent(field.name, deceased.address)
        FieldType.DECEASED_ARL -> DeceasedFieldComponent(field.name, deceased.arl)
        FieldType.DECEASED_BANKINGINSTITUTION -> DeceasedFieldComponent(field.name, deceased.bankingInstitution)
        FieldType.DECEASED_BIRTHDAY -> DeceasedFieldComponent(field.name, deceased.birthday)
        FieldType.DECEASED_EMAIL -> DeceasedFieldComponent(field.name, deceased.email)
        FieldType.DECEASED_EPS -> DeceasedFieldComponent(field.name, deceased.eps)
        FieldType.DECEASED_IDENTITYDOCUMENT -> DeceasedFieldComponent(field.name, deceased.identityDocument.toString())
        FieldType.DECEASED_IDENTITYDOCUMENTTYPE -> DeceasedFieldComponent(field.name, deceased.identityDocumentType)
        FieldType.DECEASED_LASTNAME -> DeceasedFieldComponent(field.name, deceased.lastName)
        FieldType.DECEASED_MARITALESTATUS -> DeceasedFieldComponent(field.name, deceased.maritalStatus)
        FieldType.DECEASED_NAME -> DeceasedFieldComponent(field.name, deceased.name)
        FieldType.DECEASED_NATIONALITY -> DeceasedFieldComponent(field.name, deceased.nationality)
        FieldType.DECEASED_RETAIREMENTFUND -> DeceasedFieldComponent(field.name, deceased.retirementFund)
        FieldType.DECEASED_SEVERANCEFUND -> DeceasedFieldComponent(field.name, deceased.severanceFund)
        FieldType.DESEASED_DECEASEDDATE -> DeceasedFieldComponent(field.name, deceased.deceasedDate)
        FieldType.DECEASED_GENDER -> DeceasedFieldComponent(field.name, deceased.gender)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeceasedFieldComponent(
    label: String,
    value: String
) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            modifier = Modifier.padding(top = 8.dp),
            fontSize = 16.sp,
            color = Black,
            text = label
        )
        OutlinedTextField(
            modifier = Modifier.padding(top = 4.dp).fillMaxWidth(),
            value = value,
            readOnly = true,
            onValueChange = {},
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Black,
                unfocusedTextColor = Black
            )
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun DeceasedFieldComponentPreview() {
    DeceasedFieldComponent(
        label = "Email",
        value = "juanito.perez@gmail.com"
    )
}

