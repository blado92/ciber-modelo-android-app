package com.cibermodelo.requestmanager.mappers

import com.cibermodelo.base.common.ResourceApi
import com.cibermodelo.base.model.AccessLevel
import com.cibermodelo.base.model.Deceased
import com.cibermodelo.base.model.Field
import com.cibermodelo.base.model.FieldType
import com.cibermodelo.base.model.QueryTypes
import com.cibermodelo.base.model.Role
import com.cibermodelo.base.model.User
import com.cibermodelo.requestmanager.model.AccessLevelResponse
import com.cibermodelo.requestmanager.model.DeceasedResponse
import com.cibermodelo.requestmanager.model.FieldResponse
import com.cibermodelo.requestmanager.model.FieldTypeResponse
import com.cibermodelo.requestmanager.model.QueryTypesResponse
import com.cibermodelo.requestmanager.model.RoleResponse
import com.cibermodelo.requestmanager.model.UserResponse
import retrofit2.Response

fun <T> Response<T>.toResource(): ResourceApi<T> {
    return if (this.isSuccessful) {
        val body = this.body()
        if (body != null) {
            ResourceApi.Success(body)
        } else {
            ResourceApi.Error(errorCode = 1, errorMessage = "Empty body")
        }
    } else {
        ResourceApi.Error(errorCode = this.code(), this.message())
    }
}

fun UserResponse.toUser() = User(
    id,
    name,
    lastName,
    email
)

fun DeceasedResponse.toDeceased() = Deceased(
    id,
    name,
    lastName,
    email,
    address,
    eps,
    birthday,
    deceasedDate,
    arl,
    bankingInstitution,
    identityDocument,
    identityDocumentType,
    maritalStatus,
    nationality,
    retirementFund,
    severanceFund,
    gender
)

fun List<DeceasedResponse>.toDeceasedList() = map(DeceasedResponse::toDeceased)

fun RoleResponse.toRole() = Role(
    id = id,
    name = name,
    description = description
)

fun AccessLevelResponse.toAccessLevel() = AccessLevel(
    id = id,
    name = name
)

fun QueryTypesResponse.toQueryTypes() = QueryTypes(
    id = id,
    name = name,
    role = role.toRole(),
    accessLevel = accessLevel.toAccessLevel()
)

fun List<QueryTypesResponse>.toQueryTypesList() = map(QueryTypesResponse::toQueryTypes)

fun FieldTypeResponse.toFieldType(): FieldType {
    return when(this) {
        FieldTypeResponse.DECEASED_ADDRESS -> FieldType.DECEASED_ADDRESS
        FieldTypeResponse.DECEASED_ARL -> FieldType.DECEASED_ARL
        FieldTypeResponse.DECEASED_BANKINGINSTITUTION -> FieldType.DECEASED_BANKINGINSTITUTION
        FieldTypeResponse.DECEASED_BIRTHDAY -> FieldType.DECEASED_BIRTHDAY
        FieldTypeResponse.DECEASED_EMAIL -> FieldType.DECEASED_EMAIL
        FieldTypeResponse.DECEASED_EPS -> FieldType.DECEASED_EPS
        FieldTypeResponse.DECEASED_IDENTITYDOCUMENT -> FieldType.DECEASED_IDENTITYDOCUMENT
        FieldTypeResponse.DECEASED_IDENTITYDOCUMENTTYPE -> FieldType.DECEASED_IDENTITYDOCUMENTTYPE
        FieldTypeResponse.DECEASED_LASTNAME -> FieldType.DECEASED_LASTNAME
        FieldTypeResponse.DECEASED_MARITALESTATUS -> FieldType.DECEASED_MARITALESTATUS
        FieldTypeResponse.DECEASED_NAME -> FieldType.DECEASED_NAME
        FieldTypeResponse.DECEASED_NATIONALITY -> FieldType.DECEASED_NATIONALITY
        FieldTypeResponse.DECEASED_RETAIREMENTFUND -> FieldType.DECEASED_RETAIREMENTFUND
        FieldTypeResponse.DECEASED_SEVERANCEFUND -> FieldType.DECEASED_SEVERANCEFUND
        FieldTypeResponse.DESEASED_DECEASEDDATE -> FieldType.DESEASED_DECEASEDDATE
        FieldTypeResponse.DECEASED_GENDER -> FieldType.DECEASED_GENDER
    }
}

fun FieldResponse.toField() = Field(
    id = id.toFieldType(),
    name = name
)

fun List<FieldResponse>.toFieldList() = map(FieldResponse::toField)