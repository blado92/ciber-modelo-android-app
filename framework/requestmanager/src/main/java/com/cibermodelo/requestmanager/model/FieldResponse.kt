package com.cibermodelo.requestmanager.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FieldResponse(
    val id: FieldTypeResponse,
    val name: String
)

enum class FieldTypeResponse {
    @Json(name = "deceased_address")
    DECEASED_ADDRESS,
    @Json(name = "deceased_arl")
    DECEASED_ARL,
    @Json(name = "deceased_bankingInstitution")
    DECEASED_BANKINGINSTITUTION,
    @Json(name = "deceased_birthday")
    DECEASED_BIRTHDAY,
    @Json(name = "deceased_email")
    DECEASED_EMAIL,
    @Json(name = "deceased_eps")
    DECEASED_EPS,
    @Json(name = "deceased_identityDocument")
    DECEASED_IDENTITYDOCUMENT,
    @Json(name = "deceased_identityDocumentType")
    DECEASED_IDENTITYDOCUMENTTYPE,
    @Json(name = "deceased_lastName")
    DECEASED_LASTNAME,
    @Json(name = "deceased_maritalEstatus")
    DECEASED_MARITALESTATUS,
    @Json(name = "deceased_name")
    DECEASED_NAME,
    @Json(name = "deceased_nationality")
    DECEASED_NATIONALITY,
    @Json(name = "deceased_retairementFund")
    DECEASED_RETAIREMENTFUND,
    @Json(name = "deceased_severanceFund")
    DECEASED_SEVERANCEFUND,
    @Json(name = "deseased_deceasedDate")
    DESEASED_DECEASEDDATE,
    @Json(name = "deceased_gender")
    DECEASED_GENDER,
    @Json(name = "deceased_documents")
    DECEASED_DOCUMENTS
}