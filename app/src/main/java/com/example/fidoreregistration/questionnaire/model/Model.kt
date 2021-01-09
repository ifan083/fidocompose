package com.example.fidoreregistration.questionnaire.ui

import androidx.compose.runtime.Immutable
import java.io.Serializable

@Immutable
data class EUser(
    val username: String = "username",
    val efUserId: String = "efUserId",
    val userOid: String = "userOid",
    val etn: String = "etn"
) : Serializable