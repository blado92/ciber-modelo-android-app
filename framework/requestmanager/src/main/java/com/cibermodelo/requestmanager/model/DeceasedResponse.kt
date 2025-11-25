package com.cibermodelo.requestmanager.model

data class DeceasedResponse(
    val id: Int,
    val name: String,
    val lastName: String,
    val email: String,
    val address: String,
    val eps: String,
    val birthday: String,
    val deceasedDate: String,
    val arl: String,
    val bankingInstitution: String,
    val identityDocument: Int,
    val identityDocumentType: String,
    val maritalStatus: String,
    val nationality: String,
    val retirementFund: String,
    val severanceFund: String,
    val gender: String
)